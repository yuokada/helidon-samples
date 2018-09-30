package io.github.yuokada.helidon.sample;

import static io.helidon.config.ConfigSources.environmentVariables;
import static io.helidon.config.ConfigSources.file;

import io.helidon.common.http.MediaType;
import io.helidon.config.Config;
import io.helidon.metrics.MetricsSupport;
import io.helidon.metrics.RegistryFactory;
import io.helidon.microprofile.server.Server;
import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerConfiguration;
import io.helidon.webserver.WebServer;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.logging.LogManager;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.MetricRegistry;

@ApplicationScoped
public class Main {

    public static void main(String[] args) throws IOException {
        // startServer();

        LogManager.getLogManager().readConfiguration(
            Main.class.getResourceAsStream("/logging.properties")
        );

        Config config = readConfig();
        Map<String, String> configMap = config.asMap();
        ServerConfiguration serverConfiguration = ServerConfiguration.fromConfig(config.get("servers"));

//        Optional<String> conget = config.get("MESSAGE").asOptionalString();
//        System.out.println(conget.get());

        Server server = Server.builder()
            .addResourceClass(HelloWorldControler.class)
            .build()
            .start();
    }

    private static void startServer() {
        // create metric registry
        MetricsSupport metricsSupport = MetricsSupport.create();

        // get the registry
        MetricRegistry registry = RegistryFactory
            .getRegistryFactory()
            .get()
            .getRegistry(MetricRegistry.Type.APPLICATION);

        // create a counter
        Counter helloCounter = registry.counter("helloCounter");

        Routing routing = Routing.builder()
            // register metric support with web server
            .register(metricsSupport)
            .get("/hello", (req, res) -> {
                // increase counter
                helloCounter.inc();
                res.headers().contentType(MediaType.TEXT_PLAIN);
                res.headers().contentType(MediaType.APPLICATION_JSON);
                res.send("[\"Hello World\"]");
            })
            .build();

        CompletionStage<WebServer> webserver = WebServer.create(routing)
            .start();
        System.out.println(webserver);
    }

    private static Config readConfig(){
        return Config.builder().sources(
            // Read from environment
            environmentVariables(),

            // like this
            file("conf/base.yaml").optional(),
            file("conf/dev.yaml").optional()
        ).build();
    }
}
