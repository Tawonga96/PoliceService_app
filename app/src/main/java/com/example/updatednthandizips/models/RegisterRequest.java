package com.example.updatednthandizips.models;
import java.util.Random;

public class RegisterRequest {
    private String fname;

    private String lname;
    private String pnumber;
    private String email;
    private String password;

    private Integer is_active;
    private String otp;

    //to generate one time password value for all users.
    private Random random; // Declare the Random object

    public RegisterRequest() {
        random = new Random(); // Initialize the Random object
    }

    public void generateOTP(){
        // Generate a random numeric OTP with a length of 6 digits
        int otpValue = 1000 + random.nextInt(9000);
        otp = String.valueOf(otpValue);
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active ? 1 : 0;
    }
}
