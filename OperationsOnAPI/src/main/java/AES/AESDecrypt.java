package AES;


import Config.SpringConfig;
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
        Map<String, String> connectionMap = new HashMap<>();
        try{
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(
                    new FileReader(SpringConfig.CONNECTION_FILE_PATH));

            String decrypted_password = aesUtil.decryptTextUsingAES((String) jsonObject.get(SpringConfig.PASSWORD),
                    (String) jsonObject.get(SpringConfig.KEY));

            connectionMap.put(SpringConfig.DRIVER_CLASS, (String) jsonObject.get(SpringConfig.DRIVER_CLASS));
            connectionMap.put(SpringConfig.URL, (String) jsonObject.get(SpringConfig.URL));
            connectionMap.put(SpringConfig.USERNAME, (String) jsonObject.get(SpringConfig.USERNAME));
            connectionMap.put(SpringConfig.PASSWORD, decrypted_password);

        } catch (ParseException e) {
            logger.error(e.getMessage());
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return connectionMap;
    }
}
