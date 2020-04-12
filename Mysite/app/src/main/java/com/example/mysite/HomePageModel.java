package com.example.mysite;

import java.util.List;

public class HomePageModel {
    public static final int BANNER_SLIDER = 0;
    public static final int STRIP_AD_BANNER = 1;
    public static final int HORIZONTAL_PRODUCT_VIEW = 2;
    public static final int GRID_PRODUCT_VIEW = 3;

    private int type;
    //bannerslider
    private List<SliderModel> sliderModelList;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<SliderModel> getSliderModelList() {
        return sliderModelList;
    }

    public void setSliderModelList(List<SliderModel> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }

    public HomePageModel(int type, List<SliderModel> sliderModelList) {
        this.type = type;
        this.sliderModelList = sliderModelList;

    }

    //bannerslider
    //stripd
    private int resource;
    private String backgroundcolor;

    public HomePageModel(int type, int resource, String backgroundcolor) {
        this.type = type;
        this.resource = resource;
        this.backgroundcolor = backgroundcolor;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public String getBackgroundcolor() {
        return backgroundcolor;
    }

    public void setBackgroundcolor(String backgroundcolor) {
        this.backgroundcolor = backgroundcolor;
    }

    //stripad
    //Horizontalproducts && gridview
    private String title;
    private List<HorizontalProductModel> horizontalProductModelList;

    public HomePageModel(int type, String title, List<HorizontalProductModel> horizontalProductModelList) {
        this.type = type;
        this.title = title;
        this.horizontalProductModelList = horizontalProductModelList;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<HorizontalProductModel> getHorizontalProductModelList() {
        return horizontalProductModelList;
    }

    public void setHorizontalProductModelList(List<HorizontalProductModel> horizontalProductModelList) {
        this.horizontalProductModelList = horizontalProductModelList;
    }
    //Horizontalproducts && gridview
}
