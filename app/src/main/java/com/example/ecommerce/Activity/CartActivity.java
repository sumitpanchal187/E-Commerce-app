package com.example.ecommerce.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.Adapter.CartListAdapter;
import com.example.ecommerce.Domain.populerDomain;
import com.example.ecommerce.Helper.ChangeNumberItemListener;
import com.example.ecommerce.Helper.ManagmentCart;
import com.example.ecommerce.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements PaymentResultListener {
    TextView paybtn;
    TextView paystatus ;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private ManagmentCart managmentCart;
    private TextView totalfeeText, taxtxt, deliveryText, totalText, emptyText;
    private double tax;
    private ScrollView scrollView;
    private ImageView backbtn, addressLogo ,wishlistbtnn;

    private TextView textViewDeliveryAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Checkout.preload(getApplicationContext());

        paybtn = findViewById(R.id.paywithrazor);
        ImageView forpayment= findViewById(R.id.forwardbtnpaymnt);


        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentNow("999");
            }
        });

        forpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentNow("999");
            }
        });

        textViewDeliveryAddress = findViewById(R.id.textView12);
        wishlistbtnn = findViewById(R.id.wishlist2);

        wishlistbtnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this,wishlistActivity.class));
            }
        });

        addressLogo = findViewById(R.id.imageView3);
        addressLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, AddressActivity.class));
            }
        });


        textViewDeliveryAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, AddressActivity.class));
            }
        });

        Button orderNow = findViewById(R.id.button);

        orderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartActivity.this, "Order Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        managmentCart = new ManagmentCart(this);

        initView();
        setVariable();
        initList();
        calculateCart();
    }

    private void PaymentNow(String amount) {
        final Activity activity = this ;

        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_aoqybpcwQ8YrRQ");
        checkout.setImage(R.drawable.ic_launcher_background);

        double finalAmount = Float.parseFloat(amount)*100;
        try {
            JSONObject options = new JSONObject();
            options.put("name","SUMIT PANCHAL");
            options.put("description","Reference no : #74640875");
            options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("theme.color","#3399cc");
            options.put("Currency","INR");
            options.put("amount",finalAmount+"");
            options.put("prefill.email","panchalsumit187@gmail.com");
            options.put("prefill.contact","7043268671");

            checkout.open(activity,options);

        } catch (Exception e) {
            Log.e("TAG","Error in starting Razorpay Checkout",e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(getApplicationContext(), "Payment SuccessFul", Toast.LENGTH_SHORT).show();
        paystatus.setText(s);
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(), "Payment Failed", Toast.LENGTH_SHORT).show();
        paystatus.setText(s);
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managmentCart.getListCart(), this, new ChangeNumberItemListener() {
            @Override
            public void change() {
                calculateCart();
            }
        });
        recyclerView.setAdapter(adapter);


        // Enable swipe-to-delete functionality

        if (managmentCart.getListCart().isEmpty()) {
            emptyText.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyText.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
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
private void calculateCart() {
    double percentTax = 0.02; // Change this to your desired tax rate
    double delivery = 10.0; // Change this to your desired delivery charge
    double itemTotal = 0.0;

    ArrayList<populerDomain> cartItems = managmentCart.getListCart();

    for (populerDomain item : cartItems) {
        itemTotal += item.getPrice() * item.getNumberInCart();
    }

    // Calculate the tax based on the item total
    double tax = itemTotal * percentTax;

    // Calculate the total cost, including tax and delivery charge
    double total = itemTotal + tax + delivery;

    // Round the values to two decimal places
    itemTotal = Math.round(itemTotal * 100.0) / 100.0;
    tax = Math.round(tax * 100.0) / 100.0;
    total = Math.round(total * 100.0) / 100.0;


    // Update the TextViews with the calculated values
    totalfeeText.setText("$" + itemTotal);
    taxtxt.setText("$" + tax);
    deliveryText.setText("$" + delivery);
    totalText.setText("$" + total);
}

    private void setVariable() {
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        totalfeeText = findViewById(R.id.textView7);
        taxtxt = findViewById(R.id.textView9);
        deliveryText = findViewById(R.id.textView8);
        totalText = findViewById(R.id.textView11);
        recyclerView = findViewById(R.id.mycartRecyvleView);
        scrollView = findViewById(R.id.scrollView2);
        backbtn = findViewById(R.id.backbtnLogo);
        emptyText = findViewById(R.id.emptyCart);
    }
}
