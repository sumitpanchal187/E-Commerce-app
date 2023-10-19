package com.example.ecommerce.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.imageview.ShapeableImageView;

import com.example.ecommerce.R;

public class ProfileActivity extends AppCompatActivity {
    private static final int GALLERY_REQUEST_CODE = 123;
    private ImageView backtton;
    private ShapeableImageView shapeableImageView;
    private Button logout ;
    private ConstraintLayout addressprofile ,supportProfile , personalInfo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ConstraintLayout shareButton = findViewById(R.id.paymentprofile);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to share the app
                String shareLink = "https://www.wikipedia.com";

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My Awesome App");
                String shareMessage = "Check out this awesome app: Amazon \n\nDownload it now from PlayStore "+shareLink;
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);

                // Start the sharing activity
                startActivity(Intent.createChooser(shareIntent, "Share Via"));
            }
        });


        backtton = findViewById(R.id.backlogo);
        logout = findViewById(R.id.logoutBtn);
        addressprofile = findViewById(R.id.profileaddress);
        supportProfile = findViewById(R.id.supportprifile);
        shapeableImageView = findViewById(R.id.shapeableImageView);
        personalInfo = findViewById(R.id.personalprofile);
        ConstraintLayout login = findViewById(R.id.loginprofile);

        personalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,personalinfo.class));
            }
        });

        ConstraintLayout setting = findViewById(R.id.settingProfile);

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,SettingActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,LoginActivity.class));
            }
        });



        addressprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,AddressActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this,"Log Out SuccessFully",Toast.LENGTH_SHORT).show();
            }
        });

        supportProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,SupportActivity.class));
            }
        });


        backtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, HomePage.class));
            }
        });
    }
    public void selectImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // Get the selected image's URI
            Uri selectedImageUri = data.getData();

            // Update the ShapeableImageView with the selected image
            shapeableImageView.setImageURI(selectedImageUri);
        }
    }


}