package com.example.updatednthandizips;

import static android.content.Context.MODE_PRIVATE;
import static com.example.updatednthandizips.models.SessionUtility.getUserSession;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.updatednthandizips.models.SessionUtility;
import com.example.updatednthandizips.models.UserSession;

public class Fragment_PersonProfile extends Fragment {

    private SharedPreferences sharedPreferences;

    //    private static final String SHARED_PREF_NAME = SessionUtility.SH "user_session";
    private static final String SHARED_PREF_NAME = SessionUtility.getSharedPrefName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
          // Inflate the layout for this fragment
          View view = inflater.inflate(R.layout.fragment__person_profile, container, false);

//        UserSession userSession = getUserSession(Fragment_PersonProfile.this);
//        sharedPreferences = getSharedPreferences(SessionUtility.getSharedPrefName(), MODE_PRIVATE);
//        String uid = userSession.getUid();
//        String firstName = userSession.getFirstName();
//        String lastName = userSession.getLastName();
//        String phoneNo = userSession.getPhoneNo();
//        String email = userSession.getEmail();

         // Retrieve user session data from SharedPreferences
         sharedPreferences = requireContext().getSharedPreferences(SessionUtility.getSharedPrefName(), Context.MODE_PRIVATE);
//         UserSession userSession = getUserSession();

        return view;

    }
}