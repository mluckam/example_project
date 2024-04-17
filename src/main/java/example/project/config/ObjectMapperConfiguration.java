package example.project.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import example.project.config.validation.deserialize.BeanDeserializerModifierWithValidation;

public class ObjectMapperConfiguration {

    private final ObjectMapper objectMapper;

    public ObjectMapperConfiguration() {
        objectMapper = JsonMapper.builder()
                .addModule(getValidationDeserialize())
                .build();
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    private SimpleModule getValidationDeserialize() {
        SimpleModule validationModule = new SimpleModule("validation-deserialize");
        validationModule.setDeserializerModifier(new BeanDeserializerModifierWithValidation());
        return validationModule;
    }
}
