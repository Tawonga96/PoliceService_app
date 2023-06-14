package com.example.updatednthandizips;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.updatednthandizips.api.ApiClient;
import com.example.updatednthandizips.models.RegisterRequest;
import com.example.updatednthandizips.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText pEditText, fEditText, lEditText, eEditText, passEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final TextView RegisterBtn = findViewById(R.id.createBtn);
        final TextView otherBtn = findViewById(R.id.otherBtn);
        fEditText = findViewById(R.id.FnameET);
        lEditText = findViewById(R.id.LnameET);
        pEditText = findViewById(R.id.phoneET);
        eEditText = findViewById(R.id.emailET);
        passEditText = findViewById(R.id.passwordET);

        //REGISTER BUTTON CLICKED
        RegisterBtn.setOnClickListener(v -> {
            RegisterRequest registerRequest = new RegisterRequest();

            registerRequest.setFname(fEditText.getText().toString());
            registerRequest.setLname(lEditText.getText().toString());
            registerRequest.setPnumber(pEditText.getText().toString());
            registerRequest.setEmail(eEditText.getText().toString());
            registerRequest.setPassword(passEditText.getText().toString());
            registerRequest.generateOTP();
            registerRequest.setIs_active(true);
            Register_User(registerRequest);
        });
    }

    private void Register_User(RegisterRequest registerRequest) {
        Call<RegisterResponse> RegisterResponseCall = ApiClient.GetService().RegisterUser(registerRequest);
        RegisterResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                }else{
                    String message = "An error occurred try again";
                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(RegisterActivity.this,message, Toast.LENGTH_SHORT).show();
            }
        });

    }
}