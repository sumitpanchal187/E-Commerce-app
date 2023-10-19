package com.example.ecommerce.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.Adapter.WishListAdapter;
import com.example.ecommerce.Helper.ChangeNumberItemListener;
import com.example.ecommerce.Helper.ManagmentCart;
import com.example.ecommerce.R;

public class wishlistActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private ManagmentCart managmentCart;
    private ScrollView scrollView;
    private ImageView backbtn , noti;
    private Button goToCart ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        managmentCart = new ManagmentCart(this);

        goToCart = findViewById(R.id.gotocart);
        noti = findViewById(R.id.wishlistNoti);

        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(wishlistActivity.this,NotificationActivity.class));
            }
        });

        goToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(wishlistActivity.this,CartActivity.class));
            }
        });

        initView();
        setVariable();
        initList();
//        calculateCart();
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new WishListAdapter(managmentCart.getListCart(), this, new ChangeNumberItemListener() {
            @Override
            public void change() {
//                calculateCart();
            }
        });
        recyclerView.setAdapter(adapter);


        // Enable swipe-to-delete functionality
//
//        if (managmentCart.getListCart().isEmpty()) {
//            emptyText.setVisibility(View.VISIBLE);
//            scrollView.setVisibility(View.GONE);
//        } else {
//            emptyText.setVisibility(View.GONE);
//            scrollView.setVisibility(View.VISIBLE);
//        }
    }



    //    private void calculateCart() {
//        double percentTax = 0.02;
//        double delivery = 10;
//        double total = 0;
//        double itemTotal = managmentCart.getTotalFee();
//
//        if (!managmentCart.getListCart().isEmpty()){
//            // Calculate the new subtotal
//            itemTotal -= managmentCart.getListCart().get(managmentCart.getListCart().size() - 1).getPrice();
//            itemTotal = Math.round(itemTotal * 100) / 100;
//
//            // Recalculate tax and total
//            tax = Math.round((itemTotal * percentTax * 100.0)) / 100.0;
//            total = Math.round((itemTotal + tax + delivery) * 100) / 100;
//        }
//
//        totalfeeText.setText("$" + itemTotal);
//        taxtxt.setText("$" + tax);
//        deliveryText.setText("$" + delivery);
//        totalText.setText("$" + total);
//    }
//    private void calculateCart() {
//        double percentTax = 0.02; // Change this to your desired tax rate
//        double delivery = 10.0; // Change this to your desired delivery charge
//        double itemTotal = 0.0;
//
//        ArrayList<populerDomain> cartItems = managmentCart.getListCart();
//
//        for (populerDomain item : cartItems) {
//            itemTotal += item.getPrice() * item.getNumberInCart();
//        }
//
//        // Calculate the tax based on the item total
//        double tax = itemTotal * percentTax;
//
//        // Calculate the total cost, including tax and delivery charge
//        double total = itemTotal + tax + delivery;
//
//        // Round the values to two decimal places
//        itemTotal = Math.round(itemTotal * 100.0) / 100.0;
//        tax = Math.round(tax * 100.0) / 100.0;
//        total = Math.round(total * 100.0) / 100.0;
//
//
//        // Update the TextViews with the calculated values
//        totalfeeText.setText("$" + itemTotal);
//        taxtxt.setText("$" + tax);
//        deliveryText.setText("$" + delivery);
//        totalText.setText("$" + total);
//    }

    private void setVariable() {
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        recyclerView = findViewById(R.id.mywlRecyvleView);
        scrollView = findViewById(R.id.scrollView22);
        backbtn = findViewById(R.id.backbtnLogowl);
    }
}
