package example.project.config.validation.serialize;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

public class BeanSerializerModifierWithValidation extends BeanSerializerModifier {

    @Override
    public JsonSerializer<?> modifySerializer(SerializationConfig config,
                                              BeanDescription beanDesc, JsonSerializer<?> serializer) {
        return serializer;
    }
}
