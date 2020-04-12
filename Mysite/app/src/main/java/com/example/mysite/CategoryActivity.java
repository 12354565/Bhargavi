package com.example.mysite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView categoryRecyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        String title=getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        categoryRecyclerview=findViewById(R.id.category_recyclerview);

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


        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerview.setLayoutManager(testingLayoutManager);

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
        categoryRecyclerview.setAdapter(homePageAdapter);
        homePageAdapter.notifyDataSetChanged();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.mainsearch_icon)
        {
            return true;
        }
        else if(id==android.R.id.home)
        {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
