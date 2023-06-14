package com.example.communityleader.models;

public class RegisterRequest {
    private String fname;

    private String lname;
    private String pnumber;
    private String email;

    private String password;
    private String otp;

    private Integer is_active;

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
        if(password== null || password.isEmpty()){
            this.password = null;
        }else{
            this.password = password;
        }
    }


    public Integer getIs_active() {
        return is_active;
    }
    public void setIs_active(boolean is_active) {
        this.is_active = is_active ? 1 : 0;
    }



    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public void generateOtp(int length) {
        this.otp = OTPGenerator.generateOTP(length);
    }
}
