package example.project.config.validation.deserialize;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;

public class BeanDeserializerModifierWithValidation extends BeanDeserializerModifier {

    @Override
    public JsonDeserializer<?> modifyDeserializer(DeserializationConfig deserializationConfig,
                                                  BeanDescription beanDescription,
                                                  JsonDeserializer<?> jsonDeserializer) {
        if (jsonDeserializer instanceof BeanDeserializer) {
            return new BeanDeserializerWithValidation((BeanDeserializer) jsonDeserializer);
        }

        return jsonDeserializer;
    }
}