package de.turing85.quarkus.camel.xpath;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

@QuarkusTest
class XPathRouteTest {
  @Test
  void testPost() {
    // @formatter:off
    RestAssured
        .given()
            .body("""
                <foo>
                  <bar>1</bar>
                  <bar>2</bar>
                  <bar>3</bar>
                </foo>""")
            .contentType(MediaType.APPLICATION_XML)
            .accept(MediaType.APPLICATION_XML)

        .when().post(XPathRoute.PATH)

        .then()
            .statusCode(Response.Status.OK.getStatusCode())
            .contentType(MediaType.APPLICATION_XML)
            .body(is("<bar>1</bar><bar>2</bar><bar>3</bar>"));
    // @formatter:on
  }
}
