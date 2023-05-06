package com.app.jungsuri.infra.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;


@org.springframework.context.annotation.Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldAccessLevel(Configuration.AccessLevel.PUBLIC)
                .setSkipNullEnabled(true)
//                .setDestinationNameTokenizer(NameTokenizers.UNDERSCORE)
//                .setSourceNameTokenizer(NameTokenizers.UNDERSCORE)
                .setFieldMatchingEnabled(true);
        return modelMapper;
    }
}
