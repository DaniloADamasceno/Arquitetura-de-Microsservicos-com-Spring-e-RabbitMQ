package br.com.danilo.payments.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Settings {

    //%% --> ModelMapper
    @Bean
    public ModelMapper obtainModelMapper() {
        return new ModelMapper();
    }
}
