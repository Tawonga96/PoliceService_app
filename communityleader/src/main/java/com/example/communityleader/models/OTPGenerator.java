package com.example.communityleader.models;

import java.util.Random;

public class OTPGenerator {
    public static String generateOTP(int length){
        // Define characters to be used in the OTP code
        String characters = "0123456789";

        // Create a StringBuilder to store the generated OTP code
        StringBuilder otp = new StringBuilder();

        // Create a Random object
        Random random = new Random();

        // Generate the OTP code
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            otp.append(randomChar);
        }

        // Convert the StringBuilder to a String and return the OTP code
        return otp.toString();

    }
}
