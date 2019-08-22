package cn.fireim.base.framework.comm.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "frame.swagger"
)
@Data
public class SwaggerProperties {
    private String apis;
    private String tittle;
    private String description;
    private String termsOfServiceUrl;
    private String version;
}
