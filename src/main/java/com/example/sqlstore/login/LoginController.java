package com.example.sqlstore.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping({"/public", "/"})
    public String publicPage(Map<String, Object> model) {
        logger.info("Odwiedzono publiczny endpoint");
        model.put("text", "A PUBLIC PAGE!");
        return "page";
    }

    @GetMapping("/admin")
    public String adminPage(Map<String, Object> model) {
        logger.warn("Ktoś wszedł na endpoint administracyjny");
        model.put("text", "AN ADMIN PAGE!");
        return "page";
    }

    @GetMapping("/restricted")
    public String restrictedPage(Map<String, Object> model) {
        logger.info("Odwiedzono endpoint wymagający logowania");
        model.put("text", "A RESTRICTED PAGE!");
        return "page";
    }
}
