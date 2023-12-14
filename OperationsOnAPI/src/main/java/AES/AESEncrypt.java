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
        try{

            JSONObject jsonObject = (JSONObject) new JSONParser().parse(
                    new FileReader(args[0]));

            JSONObject connectionObject = (JSONObject) new JSONParser().parse(
                    new FileReader(args[1]));

            String key  = aesUtil.getSecretAESKeyAsString();
            String encrypted_password = aesUtil.encryptTextUsingAES((String) jsonObject.get(SpringConfig.PASSWORD), key);

            connectionObject.put(SpringConfig.KEY, key);
            connectionObject.put(SpringConfig.PASSWORD, encrypted_password);

            FileWriter fileWriter = new FileWriter(args[1]);
            fileWriter.write(connectionObject.toJSONString());
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
