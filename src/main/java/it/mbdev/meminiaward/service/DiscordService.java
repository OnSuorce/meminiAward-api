package it.mbdev.meminiaward.service;

import it.mbdev.meminiaward.entity.User;
import it.mbdev.meminiaward.security.Roles;
import it.mbdev.meminiaward.service.model.DiscordOAuthResponse;
import it.mbdev.meminiaward.service.model.DiscordUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class DiscordService {

    @Value("${application.discord.client-id}")
    private String clientId;

    @Value("${application.discord.client-secret}")
    private String secretKey;

    @Autowired
    private UserService userService;

    public User completeAuthentication(String accessToken){
        DiscordOAuthResponse dor = getDiscordToken(accessToken).getBody();

        DiscordUser du = getDiscordUserInformation(dor.getAccessToken());
        User user;
        if(userService.getUser(du.getUsername()).isEmpty()){

            user = User.builder()
                    .username(du.getUsername())
                    .numberOfAwards(2)
                    .discordGlobalName(du.getGlobalName())
                    .discordId(du.getId())
                    .discordToken(dor.getAccessToken())
                    .discordAvatarUrl(getDiscordUserImageUrl(du))
                    .roles(new ArrayList<>())
                    .build();
            user.getRoles().addAll(List.of(new Roles[]{Roles.ROLE_USER, Roles.ROLE_VOTER}));
            userService.saveUser(user);
        }else {
            user = userService.getUser(du.getUsername()).get();
        }
        return user;
    }

    private ResponseEntity<DiscordOAuthResponse> getDiscordToken(String code){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("client_secret", secretKey);
        body.add("grant_type", "authorization_code");
        body.add("code", code);
        body.add("redirect_uri", "http://localhost:4200/login");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);


        ResponseEntity<DiscordOAuthResponse> responseEntity = restTemplate.exchange(
                DiscordApiUrl.BASE_URL.getUrl() + DiscordApiUrl.OAUTH.getUrl(),
                HttpMethod.POST,
                requestEntity,
                DiscordOAuthResponse.class
        );


        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            System.out.println(responseEntity.getBody());
        } else {
            throw new RuntimeException("ERROR! " + responseEntity.getStatusCode());
        }

        return responseEntity;
    }

    private RequestEntity<?> getRequest(String token, String url){


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(token);

        return new RequestEntity<>(headers, HttpMethod.GET,
                UriComponentsBuilder.fromUriString(url).build().toUri());
    }

    public DiscordUser getDiscordUserInformation(String token){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<DiscordUser> response = restTemplate.exchange(
                getRequest(token,DiscordApiUrl.BASE_URL.getUrl()+DiscordApiUrl.USER_INFO.getUrl()),
                DiscordUser.class);

        System.out.println(response);

        return response.getBody();
    }

    public String getDiscordUserImageUrl(DiscordUser du){
        return DiscordApiUrl.CDN_AVATAR.getUrl() + du.getId() + "/" + du.getAvatar() + ".png";
    }

}

