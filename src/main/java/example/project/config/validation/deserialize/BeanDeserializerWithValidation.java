package example.project.config.validation.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.io.IOException;
import java.io.Serial;
import java.util.Set;

public class BeanDeserializerWithValidation extends BeanDeserializer {

    @Serial
    private static final long serialVersionUID = 7059735556619820252L;



    private final transient Validator validator;

    protected BeanDeserializerWithValidation(BeanDeserializerBase beanDeserializerBase) {
        super(beanDeserializerBase);
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }
    }

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
        throws IOException {
        Object instance = super.deserialize(jsonParser, deserializationContext);
        validate(instance);
        return instance;
    }

    private <T> void validate(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}