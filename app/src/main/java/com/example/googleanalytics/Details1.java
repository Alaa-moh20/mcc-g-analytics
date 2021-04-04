package com.example.googleanalytics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Details1 extends AppCompatActivity {

    ImageView img;
    TextView tv;
    long startTime;
    long endTime;
    String userId = "alaa123";
    String pageName = "Details";

    private FirebaseAnalytics mFirebaseAnalytics;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details1);

        img = findViewById(R.id.imgDetails);
        tv = findViewById(R.id.tvDetails);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        db = FirebaseFirestore.getInstance();

        tackScreen("Details"); //SCREEN_VIEW Event

        startTime = System.currentTimeMillis();

        Intent intent = getIntent();
        int i = intent.getIntExtra("imgD",1);
        String t = intent.getStringExtra("details");
        img.setImageResource(i);
        tv.setText(t);

    }
    void tackScreen(String screenName){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "Details1");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
    }

    public void add(float t , String id , String name) {
        Map<String, Object> user = new HashMap<>();
        user.put("time(ms)", t);
        user.put("user_ID", id);
        user.put("page_name", name);

        db.collection("store")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                    }
                });
    }
    @Override
    protected void onDestroy() {
        endTime = System.currentTimeMillis();
        long timeSpend = endTime - startTime;
        add(timeSpend,userId,pageName);
        super.onDestroy();
    }

}