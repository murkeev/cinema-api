package murkeev.cinemaApi.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import murkeev.cinemaApi.util.LocalDateTimeReadConverter;
import murkeev.cinemaApi.util.LocalDateTimeWriteConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableMongoAuditing
@AllArgsConstructor
public class MongoConfig {
    private final MappingMongoConverter mappingMongoConverter;

    @PostConstruct
    public void setUpMongoEscapeCharacterConversion() {
        mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
    }

//    @Bean
//    public MappingMongoConverter mappingMongoConverter(MongoDatabaseFactory factory, MongoMappingContext context) {
//        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
//        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, context);
//        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
//        return converter;
//    }
//
//    @Bean
//    public MongoCustomConversions mongoCustomConversions() {
//        List<Converter<?, ?>> converters = new ArrayList<>();
//        converters.add(new LocalDateTimeReadConverter());
//        converters.add(new LocalDateTimeWriteConverter());
//        return new MongoCustomConversions(converters);
//    }

}