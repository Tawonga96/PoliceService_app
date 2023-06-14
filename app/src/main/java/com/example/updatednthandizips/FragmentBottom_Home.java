package com.example.updatednthandizips;

import static com.example.updatednthandizips.models.SessionUtility.getUserSession;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.updatednthandizips.models.UserSession;

public class FragmentBottom_Home extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom__home, container, false);


        TextView textView1 = view.findViewById(R.id.textView1);
        TextView textView2 = view.findViewById(R.id.textView2);

        UserSession userSession = getUserSession(requireContext());

        String uid = userSession.getUid();
        String firstName = userSession.getFirstName();
        String lastName = userSession.getLastName();
        String phoneNo = userSession.getPhoneNo();
        String email = userSession.getEmail();

        textView1.setText(firstName);
        textView2.setText(email);
        Log.d("TAG", "Value of firstName: " + firstName);
        Log.d("TAG", "Value of email: " + email);



        return view;
    }
}