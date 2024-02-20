package br.com.danilo.orders.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Settings {

    //%% --> ModelMapper
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
