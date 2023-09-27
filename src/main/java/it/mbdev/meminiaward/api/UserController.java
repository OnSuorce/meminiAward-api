package it.mbdev.meminiaward.api;

import it.mbdev.meminiaward.api.model.UserModel;
import it.mbdev.meminiaward.entity.User;
import it.mbdev.meminiaward.exceptions.ForbiddenException;
import it.mbdev.meminiaward.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/users/")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/me")
    public UserModel getLoggedUserInformation(){

            return new UserModel(userService
                    .getUser(AuthController.getLoggedUsername())
                    .orElseThrow(ForbiddenException::new));

    }
}
