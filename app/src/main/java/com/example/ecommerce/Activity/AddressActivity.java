package com.example.ecommerce.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerce.R;

public class AddressActivity extends AppCompatActivity {
    private Button saveAdd ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        saveAdd = findViewById(R.id.buttonSaveAddress);

        saveAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressActivity.this,CartActivity.class));
                Toast.makeText(AddressActivity.this,"Address Saved",Toast.LENGTH_SHORT).show();
            }
        });



    }
}