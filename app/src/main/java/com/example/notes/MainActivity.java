package com.example.notes;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes.LoggedInUseData.PreferenceManager;
import com.example.notes.fragments.HomeFragment;
import com.example.notes.fragments.LoginFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();

        PreferenceManager preferenceManager=new PreferenceManager(this);
        if(preferenceManager.isLoggedIn())
        {
            fragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment.class, null)
                    .setReorderingAllowed(true).commit();
        }
        else {

            fragmentManager.beginTransaction().replace(R.id.fragment_container, LoginFragment.class, null)
                    .setReorderingAllowed(true).commit();
        }

//        ActionBar actionBar;
//        actionBar = getSupportActionBar();
//
//        // Define ColorDrawable object and parse color
//        // using parseColor method
//        // with color hash code as its parameter
//        ColorDrawable colorDrawable
//                = new ColorDrawable(Color.parseColor("#FFFFC823"));
//
//        // Set BackgroundDrawable
//        actionBar.setBackgroundDrawable(colorDrawable);
    }

}
