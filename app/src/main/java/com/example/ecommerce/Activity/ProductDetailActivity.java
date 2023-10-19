    package com.example.ecommerce.Activity;


    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.ImageButton;
    import android.widget.ImageView;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.appcompat.app.AppCompatActivity;

    import com.bumptech.glide.Glide;
    import com.example.ecommerce.Domain.populerDomain;
    import com.example.ecommerce.Helper.ManagmentCart;
    import com.example.ecommerce.R;

    import java.util.ArrayList;
    import java.util.List;

    public class ProductDetailActivity extends AppCompatActivity {
        boolean isFavourite = false;
        private ImageView wlbtn ;
        private  boolean isLike;
        private ImageView  backbtn;
        private Button addToCart;
        private TextView titleText , priceText , descriptionText , reviewText , ratingText ;
        private int numberOrder = 1 ;
        private ManagmentCart managmentCart;
        private TextView noOfItemTextView;
        private int quantity = 1;
        private TextView sizeS , sizeM , sizeL , sizeXL ;
        private TextView selectedSizeView;
        ImageButton selectedImageButton;
        private ImageView productPic;
        private populerDomain object ;
        private List<populerDomain> wishlist = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            ImageView saveButton = findViewById(R.id.savebtnLogo);
            setContentView(R.layout.activity_product_detail);
    //        addtocart button
            addToCart = findViewById(R.id.addtocart2);
            managmentCart = new ManagmentCart(this);
            initView();
            getBundle();

//            share button
            ImageView shareButton = findViewById(R.id.sharebtnlogo);
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





            backbtn = findViewById(R.id.backbtnLogo);

            ImageButton plusCartButton = findViewById(R.id.pluscart);
            ImageButton minusCartButton = findViewById(R.id.minuscart);
            noOfItemTextView = findViewById(R.id.noofitem);
            sizeS = findViewById(R.id.sizeS);
            sizeM = findViewById(R.id.sizeM);
            sizeL = findViewById(R.id.sizeL);
            sizeXL = findViewById(R.id.sizeXL);

            backbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Handle the selection of size M
                    startActivity(new Intent(ProductDetailActivity.this,HomePage.class));
                }
            });




            // Set the initial quantity text
            noOfItemTextView.setText(String.valueOf(quantity));

            // Add click listeners for the plus and minus buttons
            plusCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    incrementQuantity();
                }
            });


            minusCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    decrementQuantity();
                }
            });


            // Size managment
            sizeS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle the selection of size S
                    handleSizeSelection(sizeS);

                }
            });

            sizeM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle the selection of size M
                    handleSizeSelection(sizeM);

                }
            });

            sizeL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle the selection of size L
                    handleSizeSelection(sizeL);

                }
            });

            sizeXL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle the selection of size XL
                    handleSizeSelection(sizeXL);
                }
            });




    //        Color selection
            ImageButton color1 = findViewById(R.id.color1);
            ImageButton color2 = findViewById(R.id.color2);
            ImageButton color3 = findViewById(R.id.color3);
            ImageButton color4 = findViewById(R.id.color4);

            color1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleImageButtonClick(color1);
                }
            });

            color2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleImageButtonClick(color2);
                }
            });

            color3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleImageButtonClick(color3);
                }
            });

            color4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleImageButtonClick(color4);
                }
            });
            wlbtn = findViewById(R.id.savebtnLogo);
            wlbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isLike)
                    {
                        isLike = true;
                        wlbtn.setImageDrawable(getResources().getDrawable(R.drawable.love));
                        Toast.makeText(ProductDetailActivity.this,"Removed from WishList",Toast.LENGTH_SHORT).show();

                    }
                    else {
                        isLike = false;
                        wlbtn.setImageDrawable(getResources().getDrawable(R.drawable.love2));
                        Toast.makeText(ProductDetailActivity.this,"Added to WishList",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }




        private void handleImageButtonClick(ImageButton clickedImageButton) {
            if (selectedImageButton != null) {
                selectedImageButton.setBackgroundResource(R.drawable.custom_edit_text);
            }

            // Set the custom background on the clicked ImageButton
            clickedImageButton.setBackgroundResource(R.drawable.baseline_check_24);

            // Update the selectedImageButton reference
            selectedImageButton = clickedImageButton;

            // Handle the click action here
        }

        private void handleSizeSelection(TextView selectedTextView) {
            // Reset the background color of the previously selected TextView
            if (selectedSizeView != null) {
                selectedSizeView.setBackgroundResource(R.drawable.addtocart_background);
            }

            // Set the background color of the selected TextView
            selectedTextView.setBackgroundResource(R.drawable.blue_ovel_background);

            // Update the selectedSizeView reference
            selectedSizeView = selectedTextView;
        }

        private void incrementQuantity() {
            quantity++;
            noOfItemTextView.setText(String.valueOf(quantity));
        }

        private void decrementQuantity() {
            if (quantity > 1) {
                quantity--;
                noOfItemTextView.setText(String.valueOf(quantity));
            }
        }

    //    addtocart methods
        private void initView() {
            addToCart = findViewById(R.id.addtocart2);
            priceText = findViewById(R.id.priceCart);
            descriptionText = findViewById(R.id.descriptionText);
            productPic = findViewById(R.id.searchLogo);
            titleText = findViewById(R.id.titleText);
            reviewText = findViewById(R.id.reviewText);
            ratingText = findViewById(R.id.ratingText);

        }

        private void getBundle() {
            object = (populerDomain) getIntent().getSerializableExtra("object");
            int drawableResourceId = this.getResources().getIdentifier(object.getPicUrl(),"drawable",this.getPackageName());

            Glide.with(this).load(drawableResourceId).into(productPic);

            titleText.setText(object.getTitle());
            priceText.setText("₹"+object.getPrice());
            descriptionText.setText(object.getDiscription());
            ratingText.setText(object.getRatingtx()+"");
            reviewText.setText(object.getReview()+"");

            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    object.setNumberincart(numberOrder);
                    managmentCart.insertFood(object);
                }
            });




        }

    }