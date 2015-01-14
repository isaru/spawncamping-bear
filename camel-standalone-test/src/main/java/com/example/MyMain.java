package com.example;

import org.apache.camel.main.Main;

public class MyMain {
	public static void main(String args[]) throws Exception {
		Main main = new Main();
		main.enableHangupSupport();
		main.addRouteBuilder(new MyRoute());
		main.run();
	}
}