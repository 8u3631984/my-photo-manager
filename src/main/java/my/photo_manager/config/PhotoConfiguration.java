package my.photo_manager.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "photo")
public class PhotoConfiguration {

    @Getter
    private List<String> source;
}
