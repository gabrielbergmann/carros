package br.com.gabriel.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping()
    public String login() {
        return "API dos carros";
    }
}
