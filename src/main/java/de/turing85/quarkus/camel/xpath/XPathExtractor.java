package de.turing85.quarkus.camel.xpath;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.sf.saxon.xpath.XPathFactoryImpl;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.language.xpath.XPathBuilder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class XPathExtractor implements Processor {
  private static final XPathBuilder XPATH = XPathBuilder.xpath("/foo/*/local-name()");
  private static final XPathExtractor INSTANCE = new XPathExtractor();

  static {
    XPATH.setXPathFactory(new XPathFactoryImpl());
  }

  public static XPathExtractor instance() {
    return INSTANCE;
  }

  @Override
  public void process(Exchange exchange) {
    String nodeName = XPATH.evaluate(exchange.getContext(), exchange.getIn().getBody(String.class));
    exchange.getIn().setBody(nodeName);
  }
}
