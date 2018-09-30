package io.github.yuokada.helidon.sample;

import static io.helidon.config.ConfigSources.environmentVariables;
import static io.helidon.config.ConfigSources.file;

import io.helidon.config.Config;
import io.helidon.microprofile.server.Server;
import java.io.IOException;
import java.util.logging.LogManager;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Main {

    public static void main(String[] args) throws IOException {

        LogManager.getLogManager().readConfiguration(
            Main.class.getResourceAsStream("/logging.properties")
        );

        Server server = Server.builder()
            .addResourceClass(HelloWorldControler.class)
            .build()
            .start();
    }

    private static Config readConfig(){
        return Config.builder().sources(
            environmentVariables(),
            file("conf/dev.yaml").optional()
        ).build();
    }
}
