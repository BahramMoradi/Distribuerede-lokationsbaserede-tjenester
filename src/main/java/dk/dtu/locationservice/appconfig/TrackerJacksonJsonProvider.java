package dk.dtu.locationservice.appconfig;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * /**
 * I have asked the author befor i use this class.
 *
 * @author from:
 * https://www.nabisoft.com/tutorials/java-ee/producing-and-consuming-json-or-xml-in-java-rest-services-with-jersey-and-jackson
 *
 * Jackson JSON processor could be controlled via providing a custom Jackson
 * ObjectMapper instance. This could be handy if you need to redefine the
 * default Jackson behavior and to fine-tune how your JSON data structures look
 * like (copied from Jersey web site).
 *
 * @see https://jersey.java.net/documentation/latest/media.html#d0e4799
 */
@Provider
public class TrackerJacksonJsonProvider implements ContextResolver<ObjectMapper> {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.setSerializationInclusion(Include.NON_EMPTY);
        MAPPER.disable(MapperFeature.USE_GETTERS_AS_SETTERS);

    }

    public TrackerJacksonJsonProvider() {
        System.out.println("Instantiate MyJacksonJsonProvider");
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        System.out.println("MyJacksonProvider.getContext() called with type: " + type);
        return MAPPER;
    }
}
