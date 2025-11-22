package com.buddyNetwork.BuddyNetwork.controller;

import org.springframework.stereotype.Component;

@Component
public class RazorPay {

    public String pay() {
        String payment = "Razorpay Payment";
        System.out.println("Payment from: " + payment);
        return payment;
    }
}
