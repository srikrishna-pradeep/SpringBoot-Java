package AES;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AESEncrypt {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(AESEncrypt.class);
        AESUtil aesUtil = new AESUtil();
        JSONObject aesObject = new JSONObject();
        try{
            aesObject.put("driverClass", "org.postgresql.Driver");
            aesObject.put("url", "jdbc:postgresql://localhost:5432/postgres");
            aesObject.put("username", "postgres");
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(
                    new FileReader("files/password.json"));
            String password = (String) jsonObject.get("password");
            logger.debug(password);
            String key  = aesUtil.getSecretAESKeyAsString();
            logger.debug(key);
            aesObject.put("key", key);
            String encrypted = aesUtil.encryptTextUsingAES(password, key);
            logger.debug(encrypted);
            aesObject.put("password", encrypted);
            FileWriter fileWriter = new FileWriter("files/aes_connection.json");
            fileWriter.write(aesObject.toJSONString());
            fileWriter.close();
        } catch (ParseException e) {
            logger.error(e.getMessage());
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
