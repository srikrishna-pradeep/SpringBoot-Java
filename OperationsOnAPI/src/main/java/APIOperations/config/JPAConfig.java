package APIOperations.config;


import AES.AESDecrypt;
import Config.SpringConfig;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class JPAConfig {

    @Bean
    public static DataSource getDataSource(){
        Map<String, String> connectionMap = new AESDecrypt().DataSourceList();
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(connectionMap.get(SpringConfig.DRIVER_CLASS));
        dataSourceBuilder.url(connectionMap.get(SpringConfig.URL));
        dataSourceBuilder.username(connectionMap.get(SpringConfig.USERNAME));
        dataSourceBuilder.password(connectionMap.get(SpringConfig.PASSWORD));
        return dataSourceBuilder.build();
    }

}

