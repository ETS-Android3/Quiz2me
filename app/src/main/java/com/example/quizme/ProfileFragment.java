package com.example.quizme;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    View rootview;
    FirebaseFirestore database;
    User user;
    TextView username,useremail,usercoins,userrank;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootview = inflater.inflate(R.layout.fragment_profile, container, false);

        useremail = rootview.findViewById(R.id.useremail);
        username = rootview.findViewById(R.id.username);
        usercoins = rootview.findViewById(R.id.quizCoins);
        userrank = rootview.findViewById(R.id.rank);

        database = FirebaseFirestore.getInstance();

        database.collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                user = documentSnapshot.toObject(User.class);
                useremail.setText(String.valueOf(user.getEmail()));
                username.setText(String.valueOf(user.getName()));
                userrank.setText(LeaderboardsAdapter.userRank);
                usercoins.setText(String.valueOf(user.getCoins()));



            }
        });




        return rootview;
    }
}