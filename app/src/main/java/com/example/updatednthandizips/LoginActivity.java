package com.example.updatednthandizips;

import static com.example.updatednthandizips.models.SessionUtility.getUserSession;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.updatednthandizips.api.ApiClient;
import com.example.updatednthandizips.models.LoginRequest;
import com.example.updatednthandizips.models.LoginResponse;
import com.example.updatednthandizips.models.SessionUtility;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.SharedPreferences;
import com.example.updatednthandizips.models.UserSession;


public class LoginActivity extends AppCompatActivity {
    private boolean passwordShowing = false;
    EditText usernameET, passwordET;
    ImageView passwordIcon;
    TextView signUpBtn;
    AppCompatButton signInBtn;

    private SharedPreferences sharedPreferences;

//    private static final String SHARED_PREF_NAME = SessionUtility.SH "user_session";
    private static final String SHARED_PREF_NAME = SessionUtility.getSharedPrefName();


    private static final String TAG = "LoginActivity"; // Define a tag for logging
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);
        passwordIcon = findViewById(R.id.passwordIcon);
        signUpBtn = findViewById(R.id.signUpBtn);
        signInBtn = findViewById(R.id.signInBtn);

        //Checking if user is logged already using shared preference
//        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        sharedPreferences = getSharedPreferences(SessionUtility.getSharedPrefName(), MODE_PRIVATE);
        if (sharedPreferences.contains("uid")) {
            // User is already logged in, start DashboardActivity or any other activity
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish(); // Finish LoginActivity to prevent going back when pressing back button
        }


        //WHEN PASSWORD ICON CLICKED
        passwordIcon.setOnClickListener(view -> {
            //checking showing and hiding of password
            if(passwordShowing){
                passwordShowing = false;

                passwordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                passwordIcon.setImageResource(R.drawable.show);
            }
            else{
                passwordShowing = true;

                passwordET.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                passwordIcon.setImageResource(R.drawable.hide);
            }
            //move the cursor at the last of the text
            passwordET.setSelection(passwordET.length());
        });


        //WHEN SIGNUP  BUTTON CLICKED
        signUpBtn.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
        });

        //LOGIN
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(usernameET.getText().toString()) || TextUtils.isEmpty(passwordET.getText().toString())){
                    String message = "Fill all inputs";
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                }else{
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setFname(usernameET.getText().toString());
                    loginRequest.setPassword(passwordET.getText().toString());

                    login_User(loginRequest);
                }
            }
        });


    }

    private void login_User(LoginRequest loginRequest) {
        Call<LoginResponse> loginResponseCall = ApiClient.GetService().loginUser(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, "Raw Response: " + response.raw());

                    LoginResponse loginResponseData = response.body();

                    if(loginResponseData !=null){
                        Log.d(TAG,"ResponseData : " + loginResponseData );

                        // Save user session data to SharedPreferences
                        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("uid", loginResponseData.getUid());
                        editor.putString("fname", loginResponseData.getFname());
                        editor.putString("lname", loginResponseData.getLname());
                        editor.putString("pnumber", loginResponseData.getPnumber());
                        editor.putString("email", loginResponseData.getEmail());
                        editor.apply();

                        // Log the values of user session data
                        Log.d(TAG, "UID: " + loginResponseData.getUid());
                        Log.d(TAG, "First Name: " + loginResponseData.getFname());
                        Log.d(TAG, "Last Name: " + loginResponseData.getLname());
                        Log.d(TAG, "Phone Number: " + loginResponseData.getPnumber());
                        Log.d(TAG, "Email: " + loginResponseData.getEmail());

//                        Log.d(TAG, "UID: " + userSession.getUid());
//                        Log.d(TAG, "First Name: " + userSession.getFirstName());

                        Toast.makeText(LoginActivity.this, "successful", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        startActivity(intent);

                    }else{
                        String message = "Failed to register1";
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }else{
                    String message = "Failed to register";
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(LoginActivity.this,message, Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private UserSession getUserSession() {
//        UserSession userSession = UserSession.getInstance();
//
//        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
//        userSession.setUid(String.valueOf(sharedPreferences.getInt("uid", -1)));
//        userSession.setFirstName(sharedPreferences.getString("fname", ""));
//        userSession.setLastName(sharedPreferences.getString("lname", ""));
//        userSession.setPhoneNo(sharedPreferences.getString("pnumber", ""));
//        userSession.setEmail(sharedPreferences.getString("email", ""));
//
//        return userSession;
//    }

}