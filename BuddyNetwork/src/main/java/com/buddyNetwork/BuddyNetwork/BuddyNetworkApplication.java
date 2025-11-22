package com.buddyNetwork.BuddyNetwork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.buddyNetwork.BuddyNetwork.controller.RazorPay;

@SpringBootApplication
public class BuddyNetworkApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BuddyNetworkApplication.class, args);
	}

	// Object
	// private RazorPay RazorpayPayment = new RazorPay();
	@Autowired
	private RazorPay RazorpayPayment; // Bean

	@Override
	public void run(String... args) throws Exception {
		String payment = RazorpayPayment.pay();
		System.out.println("Payment done: " + payment);
	}

}
