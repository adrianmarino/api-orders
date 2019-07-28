package com.navent.api.orders.config;

import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
public class InfoEndPointConfiguration {

    public static final String APP_INFO_YML = "app-info.yml";

    @Bean
    @SuppressWarnings("unchecked")
    public InfoEndpoint infoEndpoint() {
        Map<String, Map<String, String>> appInfo = loadInfo();
        return new InfoEndpoint(newArrayList(builder -> builder.withDetail("build", appInfo.get("build"))));
    }

    private Map<String, Map<String, String>> loadInfo() {
        return new Yaml().loadAs(this.getClass().getClassLoader().getResourceAsStream(APP_INFO_YML), Map.class);
    }
}
