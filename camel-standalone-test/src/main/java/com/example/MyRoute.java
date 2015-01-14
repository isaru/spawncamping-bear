package com.example;

import org.apache.camel.builder.RouteBuilder;
 
public class MyRoute extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		from("file:/tmp/test/in")
			.log("Received: ${body}")
			.setBody().simple("${body} foo")
			.to("file:/tmp/test/out");
	}
}