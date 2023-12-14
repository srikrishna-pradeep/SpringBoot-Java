package SpringSecurity.service;


import AES.AESUtil;
import Config.SpringConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LogManager.getLogger(MyUserDetailsService.class);
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String username="";
        String password="";
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(
                    new FileReader(SpringConfig.CREDENTIALS_FILE_PATH));
            username = (String) jsonObject.get(SpringConfig.USERNAME);
            String key = (String) jsonObject.get(SpringConfig.KEY);
            password = new AESUtil().decryptTextUsingAES((String) jsonObject.get(SpringConfig.PASSWORD), key);
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (ParseException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new User(username, password, new ArrayList<>());
    }
}
