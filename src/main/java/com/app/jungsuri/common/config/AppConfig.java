package com.app.jungsuri.common.config;

import org.modelmapper.ModelMapper;
//import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@org.springframework.context.annotation.Configuration

@Configuration
public class AppConfig {
    public AppConfig() {
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);
//                .setSkipNullEnabled(true)
//                .setDestinationNameTokenizer(NameTokenizers.UNDERSCORE)
//                .setSourceNameTokenizer(NameTokenizers.UNDERSCORE)
//                .setFieldMatchingEnabled(true);
        return modelMapper;
    }
}
