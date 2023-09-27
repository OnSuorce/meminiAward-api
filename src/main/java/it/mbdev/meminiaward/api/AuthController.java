package it.mbdev.meminiaward.api;

import it.mbdev.meminiaward.api.model.LoginModel;
import it.mbdev.meminiaward.entity.Token;
import it.mbdev.meminiaward.entity.User;
import it.mbdev.meminiaward.service.DiscordService;
import it.mbdev.meminiaward.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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


    public AuthController(DiscordService discordService, JwtService jwtService) {
        this.discordService = discordService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginModel login(@RequestBody LoginModel loginModel){


        User u = discordService.completeAuthentication(loginModel.getCode());

        String t =  jwtService.generateToken(u);

        jwtService.saveToken(t,u);

        return new LoginModel(t);

    }


}
