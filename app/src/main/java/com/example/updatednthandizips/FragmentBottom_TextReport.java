package com.example.updatednthandizips;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.updatednthandizips.api.ApiClient;
import com.example.updatednthandizips.models.CasesRequest;
import com.example.updatednthandizips.models.CasesResponse;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBottom_TextReport extends Fragment {

    TextInputLayout textInputLayout;
    TextView textViewTitle;
    RadioButton radioBtnRed, radioBtnGreen, radioBtnYellow,radioBtnBlue ;
    RadioGroup radioGroup;
    AppCompatButton appCompatButton;

    String color = ""; // Set this variable based on the selected radio button

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom__text_report, container, false);



        //Instantiate xml Ids
        textInputLayout = view.findViewById(R.id.Message_edittext_id);
        radioBtnRed = view.findViewById(R.id.radioBtn_optA_Id);
        radioBtnYellow=view.findViewById(R.id.radioBtn_optB_Id);
        radioBtnBlue = view.findViewById(R.id.radioBtn_optC_Id);
        radioBtnGreen =view.findViewById(R.id.radioBtn_optD_Id);
        appCompatButton =view.findViewById(R.id.SendTextReportBtn);
        radioGroup = view.findViewById(R.id.radioGroup_Id);

        radioBtnRed.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                color = "red";
                Toast.makeText(getActivity(), radioBtnRed.getText()+" Immediate attention", Toast.LENGTH_SHORT).show();
            }
        });


        radioBtnYellow.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                color = "yellow";
                Toast.makeText(getActivity(), radioBtnYellow.getText()+" start Mobilisation", Toast.LENGTH_SHORT).show();
            }
        });

        radioBtnGreen.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                color = "green";
                Toast.makeText(getActivity(), radioBtnGreen.getText()+" Warning", Toast.LENGTH_SHORT).show();

            }
        });

        radioBtnBlue.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                color = "blue";
                Toast.makeText(getActivity(), radioBtnBlue.getText()+" start Mobilisation", Toast.LENGTH_SHORT).show();

            }
        });

        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CasesRequest casesRequest = new CasesRequest();
                //Calling UserSession

                String message = textInputLayout.getEditText().getText().toString();

                casesRequest.setMessage(message);
                casesRequest.setCode(color);
//                casesRequest.setA_type();
//                casesRequest.setFalse_alarm();
//                casesRequest.setVoided_by();
                sendTextReport(casesRequest);

            }
        });

        return view;
    }

    private void sendTextReport(CasesRequest casesRequest) {
        Call<CasesResponse> casesResponseCall = ApiClient.GetService().SendReport(casesRequest);
        casesResponseCall.enqueue(new Callback<CasesResponse>() {
            @Override
            public void onResponse(Call<CasesResponse> call, Response<CasesResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "successful", Toast.LENGTH_SHORT).show();
                }else{
                    String message = "An error occurred try again";
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CasesResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void OnRadioBtnClicked(@NonNull View view) {
        RadioGroup radioGroup = view.findViewById(R.id.radioGroup_Id);
        RadioButton radioButton = view.findViewById(radioGroup.getCheckedRadioButtonId());
        Toast.makeText(getActivity(), radioButton.getText()+" is selected ", Toast.LENGTH_SHORT).show();
    }
}