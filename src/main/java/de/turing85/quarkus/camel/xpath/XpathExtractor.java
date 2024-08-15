package de.turing85.quarkus.camel.xpath;

import java.io.IOException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.language.xpath.XPathBuilder;
import org.w3c.dom.NodeList;

public class XpathExtractor implements Processor {
  @Override
  public void process(Exchange exchange) throws IOException {
    try (XPathBuilder xpath = XPathBuilder.xpath("//bar")) {
      NodeList nodeList = xpath.evaluate(exchange, NodeList.class);
      exchange.getIn().setBody(nodeList);
    }
  }
}
