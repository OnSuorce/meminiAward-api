package it.mbdev.meminiaward.service;

import it.mbdev.meminiaward.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class DiscordService {

    @Value("${application.discord.client-id}")
    private String clientId;

    @Value("${application.discord.client-secret}")
    private String secretKey;

    @Value("${application.discord.api-uri}")
    private String apiUri;
    public User completeAuthentication(String accessToken){
        RestTemplate restTemplate = new RestTemplate();

        // Crea un oggetto HttpHeaders per impostare l'header Content-Type
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Crea un oggetto MultiValueMap per i dati del corpo della richiesta
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("client_secret", secretKey);
        body.add("grant_type", "authorization_code");
        body.add("code", accessToken);
        body.add("redirect_uri", "http://localhost:4200/login");

        // Crea un oggetto HttpEntity che contiene l'header e i dati del corpo
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        // Esegui la richiesta POST
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                apiUri + "/oauth2/token",
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        // Verifica la risposta
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            System.out.println(responseEntity.getBody());
        } else {
            throw new RuntimeException("Errore durante lo scambio del codice: " + responseEntity.getStatusCode());
        }
        return null;
    }
}
