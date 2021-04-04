package com.example.googleanalytics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Products1 extends AppCompatActivity {
    TextView tv;
    ImageView i1;
    ImageView i2;
    ImageView i3;
    int img1;
    int img2;
    int img3;
    String BD1;
    String BD2;
    String BD3;
    long startTime;
    long endTime;
    String userId = "alaa123";
    String pageName = "Products";
    private FirebaseAnalytics mFirebaseAnalytics;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products1);

        tv  = findViewById(R.id.tv);
        i1 = findViewById(R.id.img1);
        i2 = findViewById(R.id.img2);
        i3 = findViewById(R.id.img3);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        db = FirebaseFirestore.getInstance();

        tackScreen("Products"); //SCREEN_VIEW Event

        startTime = System.currentTimeMillis();

        Intent intent = getIntent();
        img1 = intent.getIntExtra("img1",1);
        img2 = intent.getIntExtra("img2",1);
        img3 = intent.getIntExtra("img3",1);
        tv.setText(intent.getStringExtra("title"));
         BD1 = intent.getStringExtra("product_details1");
         BD2 = intent.getStringExtra("product_details2");
         BD3 = intent.getStringExtra("product_details3");
        i1.setImageResource(img1);
        i2.setImageResource(img2);
        i3.setImageResource(img3);

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectContent("p1","product1","image"); //SELECT_CONTENT Event
                Intent i=new Intent(Products1.this,Details1.class);
                i.putExtra("imgD", img1);
                i.putExtra("details",BD1);
                startActivity(i);
                endTime = System.currentTimeMillis();

            }
        });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectContent("p2","product3","image"); //SELECT_CONTENT Event
                Intent i=new Intent(Products1.this,Details1.class);
                i.putExtra("imgD", img2);
                i.putExtra("details",BD2);
                startActivity(i);
                endTime = System.currentTimeMillis();

            }
        });
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectContent("p3","product3","image"); //SELECT_CONTENT Event
                Intent i=new Intent(Products1.this,Details1.class);
                i.putExtra("imgD", img3);
                i.putExtra("details",BD3);
                startActivity(i);
                endTime = System.currentTimeMillis();

            }
        });
    }
    void tackScreen(String screenName){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "Products1");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
    }
    void selectContent (String id , String name , String contentType){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, contentType);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
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