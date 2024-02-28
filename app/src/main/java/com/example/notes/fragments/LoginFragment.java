package com.example.notes.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.notes.LoggedInUseData.PreferenceManager;
import com.example.notes.R;
import com.example.notes.fragments.HomeFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class LoginFragment extends Fragment {
    private static final int RC_SIGN_IN = 123;
    private GoogleSignInClient mGoogleSignInClient;
    private PreferenceManager preferenceManager;
    private String userId;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceManager = new PreferenceManager(requireContext());
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);

        // Check if user is logged out
        if (getArguments() != null && getArguments().getBoolean("logged_out")) {
            mGoogleSignInClient.signOut();
            preferenceManager.setLoggedIn(false);
            preferenceManager.setUserId("");
        }

        // Check if user is already logged in
        if (preferenceManager.isLoggedIn()) {
            userId = preferenceManager.getUserId();
            Log.w("LoginFragment", "User already logged in with ID: " + userId);
            toHome();
        } else {
            // Initialize GoogleSignInClient

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        SignInButton signInButton = view.findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        return view;
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignIn.getSignedInAccountFromIntent(data)
                    .addOnSuccessListener(new OnSuccessListener<GoogleSignInAccount>() {
                        @Override
                        public void onSuccess(GoogleSignInAccount googleSignInAccount) {
                            preferenceManager.setLoggedIn(true);
                            preferenceManager.setUserId(googleSignInAccount.getId());
                            userId = googleSignInAccount.getId();
                            Log.w("LoginFragment", "User logged in with ID: " + userId);
                            toHome();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("LoginFragment", "Google sign-in failed", e);
                        }
                    });
        }
    }

    private void toHome() {
        Log.w("LoginFragment", "Navigating to HomeFragment");
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();
    }
}
