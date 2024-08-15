package de.turing85.quarkus.camel.xpath;

import jakarta.ws.rs.HttpMethod;
import jakarta.ws.rs.core.MediaType;

import org.apache.camel.builder.RouteBuilder;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.platformHttp;

public class XPathRoute extends RouteBuilder {
  public static final String PATH = "/process";

  private final XpathExtractor extractor = new XpathExtractor();

  @Override
  public void configure() {
    // @formatter:off
    from(
        platformHttp(PATH)
            .httpMethodRestrict(HttpMethod.POST)
            .consumes(MediaType.APPLICATION_XML)
            .produces(MediaType.APPLICATION_XML))
        .id("%s-%s".formatted(PATH, HttpMethod.POST))
        .process(extractor);
    // @formatter:on
  }
}
