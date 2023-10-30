package OAuth2Google.config.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class OAuth2Controller {

    @RequestMapping(value = "/hello")
    public String hello(){
        return "Hello World";
    }

    @RequestMapping(value = "/user")
    @ResponseBody
    public Principal user(Principal principal){
        return principal;
    }
}
