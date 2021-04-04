package com.example.googleanalytics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button food;
    Button laptop;
    Button phone;
    long startTime;
    long endTime;
    String userId = "alaa123";
    String pageName = "Categories";
    private FirebaseAnalytics mFirebaseAnalytics;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        food = findViewById(R.id.btnFood);
        laptop = findViewById(R.id.btnLaptop);
        phone = findViewById(R.id.btnPhone);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        db = FirebaseFirestore.getInstance();

        tackScreen("MainActivity"); //SCREEN_VIEW Event

        startTime = System.currentTimeMillis();
        //xEvent("a12345","xName");
//-----------------------------------------------------------------------
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectContent("c1","category1","button"); //SELECT_CONTENT Event

                Intent i=new Intent(MainActivity.this,Products1.class);
                i.putExtra("img1", R.drawable.f1);
                i.putExtra("img2", R.drawable.f2);
                i.putExtra("img3", R.drawable.f3);
                i.putExtra("title","Foods");
                i.putExtra("product_details1","Name: PIZZA\n\nPrise: 10$\n\nIngredients: ingredients1, ingredients2 ,ingredients3, ingredients4, ingredients5");
                i.putExtra("product_details2","Name: BURGER\n\nPrise: 20$\n\nIngredients: ingredients1, ingredients2 ,ingredients3, ingredients4, ingredients5");
                i.putExtra("product_details3","Name: GRILL\n\nPrise: 30$\n\nIngredients: ingredients1, ingredients2 ,ingredients3, ingredients4, ingredients5");
                startActivity(i);
                //add food collection
                ArrayList<String> p = new ArrayList<String>();
                p.add("PIZZA");
                p.add("BURGER");
                p.add("GRILL");
                ArrayList<String> d = new ArrayList<String>();
                d.add("Prise: 10$\nIngredients: ingredients1, ingredients2 ,ingredients3, ingredients4, ingredients5");
                d.add("Prise: 20$\nIngredients: ingredients1, ingredients2 ,ingredients3, ingredients4, ingredients5");
                d.add("Prise: 30$\nIngredients: ingredients1, ingredients2 ,ingredients3, ingredients4, ingredients5");
                addCategory("Food",p,d);

                endTime = System.currentTimeMillis();

            }
        });
        laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectContent("c2","category2","button"); //SELECT_CONTENT Event

                Intent i=new Intent(MainActivity.this,Products1.class);
                i.putExtra("img1", R.drawable.l1);
                i.putExtra("img2", R.drawable.l2);
                i.putExtra("img3", R.drawable.l3);
                i.putExtra("title","HP Laptops");
                i.putExtra("product_details1","Name: hp1\n\nPrise: 800$\n\nSpecifications: AMD A4-Series APU processor\nChrome OS™ 64\n16 GB eMMC");
                i.putExtra("product_details2","Name: hp2\n\nPrise: 900$\n\nSpecifications: AMD A4-Series APU processor\nChrome OS™ 64\n16 GB eMMC");
                i.putExtra("product_details3","Name: hp3\n\nPrise: 950$\n\nSpecifications: AMD A4-Series APU processor\nChrome OS™ 64\n16 GB eMMC");
                startActivity(i);
                //add laptop collection
                ArrayList<String> p = new ArrayList<String>();
                p.add("hp1");
                p.add("hp2");
                p.add("hp3");
                ArrayList<String> d = new ArrayList<String>();
                d.add("Prise: 800$\n\nSpecifications: AMD A4-Series APU processor\nChrome OS™ 64\n16 GB eMMC");
                d.add("Prise: 900$\n\nSpecifications: AMD A4-Series APU processor\nChrome OS™ 64\n16 GB eMMC");
                d.add("Prise: 950$\n\nSpecifications: AMD A4-Series APU processor\nChrome OS™ 64\n16 GB eMMC");
                addCategory("Laptop",p,d);

                endTime = System.currentTimeMillis();

            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectContent("c3","category3","button"); //SELECT_CONTENT Event

                Intent i=new Intent(MainActivity.this,Products1.class);
                i.putExtra("img1", R.drawable.s1);
                i.putExtra("img2", R.drawable.s2);
                i.putExtra("img3", R.drawable.s3);
                i.putExtra("title","Samsung Phones");
                i.putExtra("product_details1","Name: A31\n\nPrise: 100$\n\nSpecifications: Android 10, One UI 2.5\nOcta-core (2x2.0 GHz Cortex-A75 & 6x1.7 GHz Cortex-A55)");
                i.putExtra("product_details2","Name: A51\n\nPrise: 250$\n\nIngredients: Android 10, One UI 2.5\\nOcta-core (2x2.0 GHz Cortex-A75 & 6x1.7 GHz Cortex-A55)\"");
                i.putExtra("product_details3","Name: A71\n\nPrise: 300$\n\nIngredients: Android 10, One UI 2.5\\nOcta-core (2x2.0 GHz Cortex-A75 & 6x1.7 GHz Cortex-A55)\"");
                startActivity(i);
                //add phone collection
                ArrayList<String> p = new ArrayList<String>();
                p.add("A31");
                p.add("A51");
                p.add("A71");
                ArrayList<String> d = new ArrayList<String>();
                d.add("Prise: 100$\n\nSpecifications: Android 10, One UI 2.5\nOcta-core (2x2.0 GHz Cortex-A75 & 6x1.7 GHz Cortex-A55)");
                d.add("Prise: 100$\n\nSpecifications: Android 10, One UI 2.5\nOcta-core (2x2.0 GHz Cortex-A75 & 6x1.7 GHz Cortex-A55)");
                d.add("Prise: 100$\n\nSpecifications: Android 10, One UI 2.5\nOcta-core (2x2.0 GHz Cortex-A75 & 6x1.7 GHz Cortex-A55)");
                addCategory("Phone",p,d);

                endTime = System.currentTimeMillis();

            }
        });
    }


    void selectContent (String id , String name , String contentType){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, contentType);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    void tackScreen(String screenName){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
    }

    public void addCategory(String name , ArrayList<String> products , ArrayList<String> details) {
        Map<String, Object> category = new HashMap<>();
        category.put("category_ name", name);
        category.put("category_products", products);
        category.put("products_details", details);

        db.collection("category")
                .add(category)
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
    //event custom
//    void  xEvent(String id , String name){
//        Bundle xEventBundle = new Bundle();
//        xEventBundle.putString("image_name", id);
//        xEventBundle.putString("share_to", name);
//        mFirebaseAnalytics.logEvent("xEvent",xEventBundle); // key كلها من عندي
//
//    }
}
