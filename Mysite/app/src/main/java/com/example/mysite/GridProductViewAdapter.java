package com.example.mysite;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GridProductViewAdapter extends BaseAdapter{
List<HorizontalProductModel> horizontalProductModelList;
    public GridProductViewAdapter(List<HorizontalProductModel> horizontalProductModelList) {
        this.horizontalProductModelList = horizontalProductModelList;
    }



    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView==null)
        {
           view=LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item,null);
           view.setElevation(0);
           view.setBackgroundColor(Color.parseColor("#ffffff"));
            ImageView productImage=view.findViewById(R.id.hsimage);
            TextView productTitle=view.findViewById(R.id.hsproducttitle);
            TextView productDescription=view.findViewById(R.id.hsproductdescription);
            TextView productPrice=view.findViewById(R.id.hsproductprice);
            productImage.setImageResource(horizontalProductModelList.get(position).getProductImage());
            productTitle.setText(horizontalProductModelList.get(position).getProductTitle());
            productDescription.setText(horizontalProductModelList.get(position).getProductDescription());
            productPrice.setText(horizontalProductModelList.get(position).getProductPrice());
        }else
        {
            view=convertView;
        }
        return view;
    }
}
