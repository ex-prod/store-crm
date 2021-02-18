package ru.exprod.crm.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/principal")
public class UserController {

    @GetMapping("user")
    public Principal retrievePrincipal(Principal principal) {
        return principal;
    }
}
