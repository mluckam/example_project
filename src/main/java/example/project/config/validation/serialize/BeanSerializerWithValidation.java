package example.project.config.validation.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanSerializer;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.io.IOException;
import java.io.Serial;
import java.util.Set;

public class BeanSerializerWithValidation extends BeanSerializer {

    @Serial
    private static final long serialVersionUID = 7059735556619820252L;

    private final transient Validator validator;

    protected BeanSerializerWithValidation(BeanSerializerBase beanSerializerBase) {
        super(beanSerializerBase);
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }
    }

    //final method cannot be overridden
//    @Override
//    public void serialize(Object bean, JsonGenerator gen, SerializerProvider provider)
//        throws IOException {
//        validate(bean);
//        super.serialize(bean, gen, provider);
//    }

    private <T> void validate(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
