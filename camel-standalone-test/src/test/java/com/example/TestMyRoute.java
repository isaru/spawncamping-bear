package com.example;

import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
 
public class TestMyRoute extends CamelTestSupport {
	@Test
	public void test() throws Exception {
		context.getRouteDefinitions().get(0).adviceWith(context, new AdviceWithRouteBuilder() {
			@Override
			public void configure() throws Exception {
				replaceFromWith("direct:input");
 
				interceptSendToEndpoint("file:/tmp/test/out")
					.skipSendToOriginalEndpoint()
					.to("mock:out");
			}
		});
 
		context.start();
 
		getMockEndpoint("mock:out").expectedBodiesReceived("Hello World foo");
 
		template.sendBody("direct:input", "Hello World");
 
		assertMockEndpointsSatisfied();
	}
 
	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new MyRoute();
	}
}