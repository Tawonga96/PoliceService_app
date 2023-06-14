package com.example.updatednthandizips.models;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionUtility {
    private static final String SHARED_PREF_NAME = "user_session";

    public static String getSharedPrefName() {
        return SHARED_PREF_NAME;
    }

    public static UserSession getUserSession(Context context) {
        UserSession userSession = UserSession.getInstance();

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        userSession.setUid(String.valueOf(sharedPreferences.getInt("uid", -1)));
        userSession.setFirstName(sharedPreferences.getString("fname", ""));
        userSession.setLastName(sharedPreferences.getString("lname", ""));
        userSession.setPhoneNo(sharedPreferences.getString("pnumber", ""));
        userSession.setEmail(sharedPreferences.getString("email", ""));

        return userSession;
    }

    public static void logoutUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
