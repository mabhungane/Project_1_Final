package org.sibo.webservice.config;


import org.sibo.logic.config.configLogic;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({configLogic.class})
@ComponentScan(basePackages = "org.sibo.webservice")
public class configWeb {
}
