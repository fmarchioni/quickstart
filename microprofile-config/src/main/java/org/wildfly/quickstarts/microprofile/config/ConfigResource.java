package org.wildfly.quickstarts.microprofile.config;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

@Path("/config")
@ApplicationScoped
public class ConfigResource {

    @Inject
    @ConfigProperty(name = "config.prop")
    private String configValue;

    @GET
    @Path("/value")
    public String getValue() {
        return configValue;
    }

    @GET
    @Path("/kill")
    public void getKill() throws Exception {
        List<byte[]> memoryHog = new ArrayList<>();
        while (true) {
            memoryHog.add(new byte[10 * 1024 * 1024]); // Allocazione di blocchi da 10MB
            Thread.sleep(100);
        }
    }
    
    @Inject
    @ConfigProperty(name = "required.prop", defaultValue = "Default required prop value")
    private String requiredProp;

    @GET
    @Path("/required")
    public String getRequiredProp() {
        return requiredProp;
    }

    @Inject
    @ConfigProperty(name = "optional.prop")
    private Optional<String> optionalString;

    @GET
    @Path("/optional")
    public String getOptionalValue() {
        return optionalString.orElse("no optional value provided, use this as the default");
    }

    @Inject
    private Config config;

    @GET
    @Path("/all-props")
    public String getConfigPropertyNames() {
        return config.getPropertyNames().toString();
    }
}
