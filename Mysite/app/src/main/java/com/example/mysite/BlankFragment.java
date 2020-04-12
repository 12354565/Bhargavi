package com.example.mysite;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    public BlankFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private List<CategoryModel> categoryModelList;
    private RecyclerView testing;
    private FirebaseFirestore firebaseFirestore;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        categoryRecyclerView = view.findViewById(R.id.categoryrecyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(linearLayoutManager);
        categoryModelList = new ArrayList<CategoryModel>();
        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("CATEGORIES").orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        categoryModelList.add(new CategoryModel(documentSnapshot.get("icon").toString(), documentSnapshot.get("categoryName").toString()));
                    }
                    categoryAdapter.notifyDataSetChanged();
                } else {
                    String error = task.getException().getMessage();
                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                }
            }
        });


        //bannerslider
        List<SliderModel> sliderModelList = new ArrayList<SliderModel>();
        sliderModelList.add(new SliderModel(R.mipmap.newcup, "077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.notification, "077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.amazon, "077AE4"));

        sliderModelList.add(new SliderModel(R.mipmap.bell, "077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.blackcart, "077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.user, "077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.reward, "077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.newcup, "077AE4"));

        sliderModelList.add(new SliderModel(R.mipmap.notification, "077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.amazon, "077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.bell, "077AE4"));


        //horizontalproductview

        List<HorizontalProductModel> horizontalProductModelList = new ArrayList<>();
        horizontalProductModelList.add(new HorizontalProductModel(R.mipmap.phone, "Redmi 5A", "SD 625 Processor", "Rs.9000/-"));
        horizontalProductModelList.add(new HorizontalProductModel(R.mipmap.phone, "Redmi 5A", "SD 625 Processor", "Rs.9000/-"));
        horizontalProductModelList.add(new HorizontalProductModel(R.mipmap.phone, "Redmi 5A", "SD 625 Processor", "Rs.9000/-"));
        horizontalProductModelList.add(new HorizontalProductModel(R.mipmap.phone, "Redmi 5A", "SD 625 Processor", "Rs.9000/-"));
        horizontalProductModelList.add(new HorizontalProductModel(R.mipmap.phone, "Redmi 5A", "SD 625 Processor", "Rs.9000/-"));
        horizontalProductModelList.add(new HorizontalProductModel(R.mipmap.phone, "Redmi 5A", "SD 625 Processor", "Rs.9000/-"));
        horizontalProductModelList.add(new HorizontalProductModel(R.mipmap.phone, "Redmi 5A", "SD 625 Processor", "Rs.9000/-"));
        horizontalProductModelList.add(new HorizontalProductModel(R.mipmap.phone, "Redmi 5A", "SD 625 Processor", "Rs.9000/-"));
        horizontalProductModelList.add(new HorizontalProductModel(R.mipmap.phone, "Redmi 5A", "SD 625 Processor", "Rs.9000/-"));


         testing = view.findViewById(R.id.homepage_recyclerview);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0, sliderModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.newstripadd, "#000000"));
        homePageModelList.add(new HomePageModel(2, "Deals of the day ", horizontalProductModelList));
        homePageModelList.add(new HomePageModel(3, "Deals of the day ", horizontalProductModelList));

        homePageModelList.add(new HomePageModel(0, sliderModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.summersale, "#ffff00"));
        homePageModelList.add(new HomePageModel(0, sliderModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.newstripadd, "#ff0000"));
        HomePageAdapter homePageAdapter = new HomePageAdapter(homePageModelList);
        testing.setAdapter(homePageAdapter);
        homePageAdapter.notifyDataSetChanged();
        return view;
    }
    //bannerslider
}