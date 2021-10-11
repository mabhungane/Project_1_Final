package org.sibo.translator.config;


import org.sibo.repository.config.RepoConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RepoConfig.class})
@ComponentScan(basePackages = "org.sibo.translator")
public class TransConfiguation {
}
