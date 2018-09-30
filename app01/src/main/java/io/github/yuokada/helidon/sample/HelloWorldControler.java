package io.github.yuokada.helidon.sample;

import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.eclipse.microprofile.metrics.annotation.Metered;

@Path("/hello")
@RequestScoped //allows us to use CDI injection
public class HelloWorldControler {

    @GET
    @Metered  // <= get metrics
//    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello(
        @Context HttpHeaders headers) {
        System.out.println(headers.getHeaderString("X-DEBUG"));
        Map<String, Object> mmap = new HashMap<>();
        mmap.put("foo", 100);
        mmap.put("bar", "babbab");

        return Response
            .ok(Json.createObjectBuilder(mmap).build())
            .cacheControl(CacheControl.valueOf("private,no-cache,max-age=0"))
            .header("X-DEBUG", "foo-bar-baz")
            .build();
    }

    @GET
    @Path(value = "/detail/{name:[a-z][a-z0-9\\-]+}")
    @Metered
    @Produces(MediaType.APPLICATION_JSON)
    public Response detail2(@PathParam("name") String name,
        @QueryParam("ex") String ex) {
        if (!ex.isEmpty()) {
            Map<String, Object> mm = new HashMap<String, Object>() {{
                put("Message", "invalid parameter");
            }};

            throw new WebApplicationException(
                Response.status(Status.BAD_REQUEST).entity(
                    Json.createObjectBuilder(mm).build()).build()
                );
        }
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("Message", "hello " + name + "!");
        }};
        return Response.ok(
            Json.createObjectBuilder(map).build()
        ).status(Status.CREATED).build();
    }

    @GET
    @Path(value = "{name:[a-z][a-z0-9\\-]+}")
    @Metered
    @Produces(MediaType.APPLICATION_JSON)
    public Response detail(@PathParam("name") String name) {
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("Message", "hello " + name + "!");
        }};
        return Response
            .ok()
            .entity(Json.createObjectBuilder(map).build())
            .build();
    }
}