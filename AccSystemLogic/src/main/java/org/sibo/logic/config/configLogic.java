package org.sibo.logic.config;


import org.sibo.translator.config.TransConfiguation;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({TransConfiguation.class})
@ComponentScan(basePackages = "org.sibo.logic")
public class configLogic {
}
