package OAuth2Server;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/hello")
public class OAuth2Resource {

    @GetMapping
    public String hello(){
        return "Hello World";
    }

    @RequestMapping("/user")
    public Principal user(Principal principal){
        return principal;
    }

}
