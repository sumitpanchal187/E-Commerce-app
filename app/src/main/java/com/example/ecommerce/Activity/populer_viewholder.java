package com.example.ecommerce.Activity;


import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerce.R;

public class populer_viewholder extends AppCompatActivity {

    ImageButton wlbtn ;
    private  boolean isLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.populer_viewholder_list);

        //        like button for wishlist
//        wlbtn = findViewById(R.id.wllike2);
//        wlbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!isLike)
//                {
//                    isLike = true;
//                    wlbtn.setImageDrawable(getResources().getDrawable(R.drawable.love));
//                }
//                else {
//                    isLike = false;
//                    wlbtn.setImageDrawable(getResources().getDrawable(R.drawable.love2));
//                }
//            }
//        });






    }
}