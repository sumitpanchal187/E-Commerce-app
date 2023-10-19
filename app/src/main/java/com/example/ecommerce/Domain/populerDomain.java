package com.example.ecommerce.Domain;

import java.io.Serializable;

public class populerDomain implements Serializable {
    private String title;
    private  String discription ;
    private  String picUrl ;
    private int review;
    private int offperce;
    private  int ProductId;
    private double ratingtx ;
    private int idhome;
    private double price ;
    private  int numberincart ;

//    chatGPT

    private int productId;



    public populerDomain(int productId,String title, String discription, int offperce,String picUrl, int review,double ratingtx, int numberincart , double price,int idhome) {
        this.title = title;
        this.productId=productId;
        this.discription = discription;
        this.picUrl = picUrl;
        this.offperce=offperce;
        this.review = review;
        this.price = price;
        this.ratingtx = ratingtx;
        this.idhome=idhome;
        this.numberincart = numberincart;
    }

    public int getOffperce() {
        return offperce;
    }

    public void setOffperce(int offperce) {
        this.offperce = offperce;
    }

    public populerDomain(String title, String picUrl, double price) {
        this.title = title;
        this.picUrl = picUrl;
        this.price = price;
    }
    public  double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public  int getProductId() {
        return ProductId;
    }
    public void setProductId(int productId) {
        ProductId = productId;
    }

    public double getRatingtx() {
        return ratingtx;
    }

    public void setRatingtx(double ratingtx) {
        this.ratingtx = ratingtx;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }


    public int getIdhome() {
        return idhome;
    }

    public void setIdhome(int idhome) {
        this.idhome = idhome;
    }


    public void setNumberincart(int numberincart) {
        this.numberincart = numberincart;
    }


    public int getNumberInCart() {
        return  numberincart;
    }
}


