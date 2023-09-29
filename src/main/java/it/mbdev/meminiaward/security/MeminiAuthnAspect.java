package it.mbdev.meminiaward.security;

import it.mbdev.meminiaward.api.AuthController;
import it.mbdev.meminiaward.service.UserService;
import it.mbdev.meminiaward.exceptions.ForbiddenException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class MeminiAuthnAspect {

    private final UserService userService;

    @Autowired
    public MeminiAuthnAspect(UserService userService) {
        this.userService = userService;
    }

    @Before("@annotation(it.mbdev.meminiaward.security.MeminiAuthn)")
    public void runBeforeCustomAuthorization() {
        // Your custom code here
        String loggedUsername = AuthController.getLoggedUsername();
        userService.getUser(loggedUsername)
                .orElseThrow(ForbiddenException::new);
    }
}

