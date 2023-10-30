package OAuth2Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
public class SpringOAuth2ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringOAuth2ServerApplication.class, args);
	}

}
