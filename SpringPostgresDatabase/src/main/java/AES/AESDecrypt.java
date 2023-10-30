package AES;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AESDecrypt {
    public Map<String, String> DataSourceList(){
        Logger logger = LogManager.getLogger(AESDecrypt.class);
        AESUtil aesUtil = new AESUtil();
        Map<String, String> map = new HashMap<>();
        try{
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(
                    new FileReader("files/aes_connection.json"));
            String driverClass = (String) jsonObject.get("driverClass");
            String url = (String) jsonObject.get("url");
            String username = (String) jsonObject.get("username");
            String password = (String) jsonObject.get("password");
            String key = (String) jsonObject.get("key");
            String decrypted = aesUtil.decryptTextUsingAES(password, key);
            logger.debug(decrypted);
            map.put("url", url);
            map.put("driverClass", driverClass);
            map.put("username", username);
            map.put("password",decrypted);
        } catch (ParseException e) {
            logger.error(e.getMessage());
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return map;
    }
}
