package com.example.mysite;

import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter {
    private List<HomePageModel> homePageModelList;

    public HomePageAdapter(List<HomePageModel> homePageModelList) {
        this.homePageModelList = homePageModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (homePageModelList.get(position).getType()) {
            case 0:
                return HomePageModel.BANNER_SLIDER;
            case 1:
                return HomePageModel.STRIP_AD_BANNER;
            case 2:
                return HomePageModel.HORIZONTAL_PRODUCT_VIEW;
            case 3:
                return HomePageModel.GRID_PRODUCT_VIEW;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case HomePageModel.BANNER_SLIDER:
                View bannersliderview = LayoutInflater.from(parent.getContext()).inflate(R.layout.slidiingad_banner, parent, false);
                return new BannerSliderViewHolder(bannersliderview);
            case HomePageModel.STRIP_AD_BANNER:
                View stripadview = LayoutInflater.from(parent.getContext()).inflate(R.layout.stripad_layout, parent, false);
                return new StripAdBannerViewHolder(stripadview);
            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                View horizontalproductview = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_layout, parent, false);
                return new HorizontalProductViewHolder(horizontalproductview);
            case HomePageModel.GRID_PRODUCT_VIEW:
                View gridproductview = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_product_layout, parent, false);
                return new GridProductViewHolder(gridproductview);
            default:
                return null;

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (homePageModelList.get(position).getType()) {
            case HomePageModel.BANNER_SLIDER:
                List<SliderModel> sliderModelList = homePageModelList.get(position).getSliderModelList();
                ((BannerSliderViewHolder) holder).setBannersliderviewpager(sliderModelList);
                break;
            case HomePageModel.STRIP_AD_BANNER:
                int resource = homePageModelList.get(position).getResource();
                String color = homePageModelList.get(position).getBackgroundcolor();
                ((StripAdBannerViewHolder) holder).setStripAd(resource, color);
                break;
            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                String horizontallayouttitle=homePageModelList.get(position).getTitle();
                List<HorizontalProductModel>horizontalProductModelList=homePageModelList.get(position).getHorizontalProductModelList();
                ((HorizontalProductViewHolder)holder).setHorizontalProductlayout(horizontalProductModelList,horizontallayouttitle);
                break;
            case HomePageModel.GRID_PRODUCT_VIEW:
                String gridlayouttitle=homePageModelList.get(position).getTitle();
                List<HorizontalProductModel>gridProductModelList=homePageModelList.get(position).getHorizontalProductModelList();
                ((GridProductViewHolder)holder).setGridProductlayout(gridProductModelList,gridlayouttitle);
                break;
            default:
                return;
        }

    }

    @Override
    public int getItemCount() {
        return homePageModelList.size();
    }

    public class BannerSliderViewHolder extends RecyclerView.ViewHolder {
        private ViewPager bannersliderviewpager;
        private int currentpage = 2;
        private Timer timer;
        private final long DELAY_TIME = 3000;
        private final long PERIOD_TIME = 3000;

        public BannerSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            bannersliderviewpager = itemView.findViewById(R.id.banner_slider_view_pager);


        }

        private void pageLooper(List<SliderModel> sliderModelList) {
            if (currentpage == sliderModelList.size() - 2) {
                currentpage = 2;
                bannersliderviewpager.setCurrentItem(currentpage, false);
            }
            if (currentpage == 1) {
                currentpage = sliderModelList.size() - 3;
                bannersliderviewpager.setCurrentItem(currentpage, false);
            }

        }

        private void startBannerSlideShow(final List<SliderModel> sliderModelList) {
            final Handler handler = new Handler();
            final Runnable update = new Runnable() {
                @Override
                public void run() {
                    if (currentpage >= sliderModelList.size()) {
                        currentpage = 1;
                    }
                    bannersliderviewpager.setCurrentItem(currentpage++, true);
                }


            };
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            }, DELAY_TIME, PERIOD_TIME);
        }

        private void stopBannerSllideShow() {
            timer.cancel();
        }

        //bannerslider
        private void setBannersliderviewpager(final List<SliderModel> sliderModelList) {
            SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
            bannersliderviewpager.setAdapter(sliderAdapter);
            bannersliderviewpager.setClipToPadding(false);
            bannersliderviewpager.setPageMargin(20);
            bannersliderviewpager.setCurrentItem(currentpage);
            //bannerslider
            ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    currentpage = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    if (state == ViewPager.SCROLL_STATE_IDLE) {
                        pageLooper(sliderModelList);
                    }

                }
            };
            bannersliderviewpager.addOnPageChangeListener(onPageChangeListener);
            startBannerSlideShow(sliderModelList);
            bannersliderviewpager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    pageLooper(sliderModelList);
                    stopBannerSllideShow();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        startBannerSlideShow(sliderModelList);
                    }
                    return false;
                }
            });

        }
    }

    public class StripAdBannerViewHolder extends RecyclerView.ViewHolder {
        private ImageView stripadimage;
        private ConstraintLayout stripadcontainer;

        public StripAdBannerViewHolder(@NonNull View itemView) {
            super(itemView);
            stripadimage = itemView.findViewById(R.id.stripad_image);
            stripadcontainer = itemView.findViewById(R.id.strip_add_container);
        }

        private void setStripAd(int resource, String color) {
            stripadimage.setImageResource(resource);
            stripadcontainer.setBackgroundColor(Color.parseColor(color));
        }
    }
    public class HorizontalProductViewHolder extends RecyclerView.ViewHolder{
        private TextView horizontallayoutTitle;
        private Button horizontallayoutviewall;
        private RecyclerView horizontalRecyclerview;
        public HorizontalProductViewHolder(@NonNull View itemView) {
            super(itemView);
            horizontallayoutTitle=itemView.findViewById(R.id.horizontal_scroll_layout_title);
            horizontallayoutviewall=itemView.findViewById(R.id.horizontal_scroll_layout_button);
            horizontalRecyclerview=itemView.findViewById(R.id.horizontal_scroll_recyclerview);
        }
        private void setHorizontalProductlayout(List<HorizontalProductModel> horizontalProductModelList,String title){
            horizontallayoutTitle.setText(title);
            if(horizontalProductModelList.size()>8)
            {
                horizontallayoutviewall.setVisibility(View.VISIBLE);
            }
            else
            {
                horizontallayoutviewall.setVisibility(View.INVISIBLE);
            }
            HorizontalProductAdapter horizontalProductAdapter=new HorizontalProductAdapter(horizontalProductModelList);
            LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(itemView.getContext());
            linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
            horizontalRecyclerview.setLayoutManager(linearLayoutManager1);
            horizontalRecyclerview.setAdapter(horizontalProductAdapter);
            horizontalProductAdapter.notifyDataSetChanged();
        }
    }
    public class GridProductViewHolder extends RecyclerView.ViewHolder{
        TextView gridlayouttitle;
        Button viewallbtn;
        GridView gridView;

        public GridProductViewHolder(@NonNull View itemView) {
            super(itemView);
             gridlayouttitle=itemView.findViewById(R.id.gridproducttitle);
             viewallbtn=itemView.findViewById(R.id.gridproductbutton);
             gridView=itemView.findViewById(R.id.gridproductgridview);
        }
        private void setGridProductlayout(List<HorizontalProductModel> horizontalProductModelList,String title){
            gridlayouttitle.setText(title);
            gridView.setAdapter(new GridProductViewAdapter(horizontalProductModelList));
        }
    }

}
