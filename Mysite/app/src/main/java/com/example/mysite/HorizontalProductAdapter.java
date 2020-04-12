package com.example.mysite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HorizontalProductAdapter extends RecyclerView.Adapter<HorizontalProductAdapter.ViewHolder> {
    private List<HorizontalProductModel> horizontalProductModelList;

    public HorizontalProductAdapter(List<HorizontalProductModel> horizontalProductModelList) {
        this.horizontalProductModelList = horizontalProductModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int resource=horizontalProductModelList.get(position).getProductImage();
        String title=horizontalProductModelList.get(position).getProductTitle();
        String description=horizontalProductModelList.get(position).getProductDescription();
        String price=horizontalProductModelList.get(position).getProductPrice();
        holder.setProductImage(resource);
        holder.setProductTitle(title);
        holder.setProductDescription(description);
        holder.setProductPrice(price);

    }

    @Override
    public int getItemCount() {
        if(horizontalProductModelList.size()>8)
        {
            return 8;
        }
        else {
            return horizontalProductModelList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView productTitle;
        private TextView productDescription;
        private TextView productPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.hsimage);
            productTitle=itemView.findViewById(R.id.hsproducttitle);
            productDescription=itemView.findViewById(R.id.hsproductdescription);
            productPrice=itemView.findViewById(R.id.hsproductprice);
        }
        private void setProductImage(int resource)
        {
            productImage.setImageResource(resource);
        }
        private void setProductTitle(String title)
        {
            productTitle.setText(title);
        }
        private void setProductDescription(String description)
        {
            productDescription.setText(description);
        }
        private void setProductPrice(String price)
        {
            productPrice.setText(price);
        }
    }
}
