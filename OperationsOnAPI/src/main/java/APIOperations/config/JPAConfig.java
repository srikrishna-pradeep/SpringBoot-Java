package APIOperations.config;


import AES.AESDecrypt;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class JPAConfig {

    @Bean
    public static DataSource getDataSource(){
        Map<String, String> map = new AESDecrypt().DataSourceList();
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(map.get("driverClass"));
        dataSourceBuilder.url(map.get("url"));
        dataSourceBuilder.username(map.get("username"));
        dataSourceBuilder.password(map.get("password"));
        return dataSourceBuilder.build();
    }

}

