package nl.craftsmen.conference.service.micronaut;

import io.micronaut.context.env.PropertySource;
import io.micronaut.context.env.PropertySourceLoader;
import io.micronaut.core.io.ResourceLoader;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

public class CustomPropertySource implements PropertySourceLoader {
    @Override
    public Optional<PropertySource> load(String resourceName, ResourceLoader resourceLoader, @Nullable String environmentName) {
        return Optional.empty();
    }

    @Override
    public Map<String, Object> read(String name, InputStream input) throws IOException {
        return null;
    }
}
