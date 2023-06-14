package com.example.communityleader;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.communityleader.Api.ApiClient;
import com.example.communityleader.models.RegisterRequest;
import com.example.communityleader.models.RegisterResponse;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_AddCommunityMembers extends Fragment {

    EditText firstname, lastname, phone, email;
    AppCompatButton RegisterBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Twilio client
        Twilio.init("ACd8a3bc1036a690fdc8e2ff279906294c", "1381426ee2847b6ce032f30f7fc391ee");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__add_community_members, container, false);
        firstname = view.findViewById(R.id.FnameET);
        lastname = view.findViewById(R.id.LnameET);
        phone = view.findViewById(R.id.phoneET);
        email = view.findViewById(R.id.emailET);
        RegisterBtn =view.findViewById(R.id.RegisterBtn);

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterRequest registerRequest = new RegisterRequest();
                registerRequest.generateOtp(6); //Generate otp code
                String otp = registerRequest.getOtp(); // Get the generated OTP code

                registerRequest.setFname(firstname.getText().toString());
                registerRequest.setLname(lastname.getText().toString());
                registerRequest.setPnumber(phone.getText().toString());
                registerRequest.setEmail(email.getText().toString());
                registerRequest.setPassword(null);
                registerRequest.setOtp(otp);
                registerRequest.setIs_active(false);
                Register_User(registerRequest);

                // Send OTP code via SMS
                sendOtpCode(registerRequest.getPnumber(), otp);


            }
        });
        return view;
    }

    private void sendOtpCode(String pnumber, String otp) {
        String twilioPhoneNumber = "+13609972230"; // Replace with your Twilio phone number

        try {
            Message message = Message.creator(
                            new PhoneNumber(pnumber), // Recipient phone number
                            new PhoneNumber(twilioPhoneNumber), // Sender phone number
                            "Your OTP code is: " + otp) // SMS body
                    .create();

            Toast.makeText(getContext(), "OTP code sent successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            String message = "Failed to send OTP code";
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    private void Register_User(RegisterRequest registerRequest) {
        Call<RegisterResponse> RegisterResponseCall = ApiClient.GetService().RegisterUser(registerRequest);
        RegisterResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "successful", Toast.LENGTH_SHORT).show();
                }else{
                    String message = "An error occurred try again";
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();
            }
        });
    }




}