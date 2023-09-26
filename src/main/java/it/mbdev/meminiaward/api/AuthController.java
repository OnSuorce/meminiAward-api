package it.mbdev.meminiaward.api;

import it.mbdev.meminiaward.api.model.LoginModel;
import it.mbdev.meminiaward.service.DiscordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/")
public class AuthController {

    @Autowired
    DiscordService discordService;
    @PostMapping("/login")
    public String login(@RequestBody LoginModel loginModel){

        //Complete oauth with discord
        discordService.completeAuthentication(loginModel.getAccessToken());
        //Make new account

        //Create jwt token

        //Send back jwt token

        System.out.println("EVERYTHIN IS FINE");
        return  "WOW";

    }
}
