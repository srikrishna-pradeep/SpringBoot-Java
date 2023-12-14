package AES;


import Config.SpringConfig;
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
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(args[0]));

            // Generates encrypted password
            String key = aesUtil.getSecretAESKeyAsString();
            String password = (String) jsonObject.get(SpringConfig.PASSWORD);
            String encrypted_password = aesUtil.encryptTextUsingAES(password, key);

            // User Details into JSON Object
            aesObject.put(SpringConfig.USERNAME, args[1]);
            aesObject.put(SpringConfig.KEY, key);
            aesObject.put(SpringConfig.PASSWORD, encrypted_password);

            // JSON Object writes to new file
            FileWriter fileWriter = new FileWriter(SpringConfig.CREDENTIALS_FILE_PATH);
            fileWriter.write(aesObject.toJSONString());
            fileWriter.close();

        } catch (ParseException e) {
            logger.error(e.getMessage());
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
