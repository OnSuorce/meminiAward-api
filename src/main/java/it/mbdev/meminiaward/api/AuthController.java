package it.mbdev.meminiaward.api;

import it.mbdev.meminiaward.api.model.LoginModel;
import it.mbdev.meminiaward.api.model.UserModel;
import it.mbdev.meminiaward.entity.Token;
import it.mbdev.meminiaward.entity.User;
import it.mbdev.meminiaward.service.DiscordService;
import it.mbdev.meminiaward.service.JwtService;
import it.mbdev.meminiaward.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/")
public class AuthController {

    final
    DiscordService discordService;

    final
    JwtService jwtService;

    final
    UserService userService;
    public static String getLoggedUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // The user is authenticated, you can access user details
            // Additional user details may be obtained from authentication.getAuthorities()

            return authentication.getName();
        } else {
            // No user is authenticated
            return null;
        }
    }
    public AuthController(DiscordService discordService, JwtService jwtService, UserService userService) {
        this.discordService = discordService;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginModel login(@RequestBody LoginModel loginModel){


        User u = discordService.completeAuthentication(loginModel.getCode());

        String t =  jwtService.generateToken(u);
        jwtService.expireAllTokensOfUser(u);
        jwtService.saveToken(t,u);

        return new LoginModel(t);

    }


}
