package com.example.ecommerce.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.ecommerce.Adapter.CategoryAdapter;
import com.example.ecommerce.Adapter.populerListAdapter;
import com.example.ecommerce.Domain.populerDomain;
import com.example.ecommerce.Helper.Constants;
import com.example.ecommerce.R;
import com.example.ecommerce.databinding.ActivityHomePageBinding;
import com.example.ecommerce.models.Category;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    private RecyclerView.Adapter adapterPolpuler;
    private RecyclerView recyclerviewpopuler;
    private ImageView profile;
    private ImageView wishList;
    private ImageView cartlg, homeLogo, notibtn;
    private ConstraintLayout searchView;
    private EditText searchedit;
    private ImageView clearButton;
    private ImageView profileLogo;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActivityHomePageBinding binding;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_page);

        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initCategories();

        drawerLayout = findViewById(R.id.drawwerlayout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbarnavigationss);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.images);



        profileLogo = findViewById(R.id.profilelogoh);

        profileLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open the navigation drawer
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle;
        toggle = new
                ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        navigationView.setCheckedItem(R.id.navigation_home);

        notibtn = findViewById(R.id.imageView9);


        notibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, NotificationActivity.class));
            }
        });

//         Implementation of search bar

        ImageView searchIcon = findViewById(R.id.imageView);
        searchView = findViewById(R.id.searchbartext);
        clearButton = findViewById(R.id.clearButton);
        searchedit = findViewById(R.id.searchEdit);


        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toggle the visibility of the search view
                if (searchView.getVisibility() == View.VISIBLE) {
                    searchView.setVisibility(View.GONE);
                    searchIcon.setImageDrawable(getResources().getDrawable(R.drawable.drawable_search));
                } else {
                    searchView.setVisibility(View.VISIBLE);
                    searchIcon.setImageDrawable(getResources().getDrawable(R.drawable.img_68));
                }
            }
        });


        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchedit.setText("");
            }
        });

        // Set a click listener for the clear button


        profile = findViewById(R.id.profilelogox);
        wishList = findViewById(R.id.wishList2);
        cartlg = findViewById(R.id.cartlogo);
        homeLogo = findViewById(R.id.homebutton);

        initRecyclerView();

        ImageSlider imageSlider = findViewById(R.id.sliderImage);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.img_45, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_46, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_47, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_44, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, ProfileActivity.class));
            }
        });

        wishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, wishlistActivity.class));
            }
        });

        cartlg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, CartActivity.class));
            }
        });
        homeLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, HomePage.class));
            }
        });
    }
    void getCategories() {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET, Constants.GET_CATEGORIES_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // Parse the JSON data
                    JSONObject jsonData = new JSONObject(response); // Replace yourJsonString with your JSON data

                    // Check the status
                    String status = jsonData.getString("status");
                    if (status.equals("success")) {
                        JSONArray categoriesArray = jsonData.getJSONArray("categories");
                        for (int i = 0; i < categoriesArray.length(); i++) {
                            JSONObject categoryObject = categoriesArray.getJSONObject(i);

                            // Extract data from the JSON object
                            String name = categoryObject.getString("name");
                            String icon = Constants.CATEGORIES_IMAGE_URL + categoryObject.getString("icon");
                            String description = categoryObject.getString("brief");
                            int id = categoryObject.getInt("id");

                            // Create a Category object and add it to the list
                            Category category = new Category(name, icon, description, id);
                            categories.add(category);
                        }

                        // Notify the adapter of the data change
                        categoryAdapter.notifyDataSetChanged();
                    } else {
                        // Handle the case when status is not "success"
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    // Handle JSON parsing error
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(request);
    }
    void initCategories() {
        categories = new ArrayList<>();
        categories.add(new Category("Men's","img_1","sumit panchal",1));
        categories.add(new Category("Womes's","img_2","sumit panchal",1));
        categories.add(new Category("Kid's","img_3","sumit panchal",1));
        categories.add(new Category("Electronics","img_4","sumit panchal",1));
        categories.add(new Category("Beauty","img_5","sumit panchal",1));
        categories.add(new Category("Home & More","img_6","sumit panchal",1));
        categories.add(new Category("Grocery","img_7","sumit panchal",1));
        categories.add(new Category("Sports","img_8","sumit panchal",1));
        categoryAdapter = new CategoryAdapter(this, categories);

//        getCategories();

        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        binding.categoryList.setLayoutManager(layoutManager);
        binding.categoryList.setAdapter(categoryAdapter);
    }
    private void initRecyclerView() {
        ArrayList<com.example.ecommerce.Domain.populerDomain> items = new ArrayList<>();
        items.add(new populerDomain(1, "Women's Tshirt", "Sumit Panchal", 5, "qwerqwr", 50, 4.8, 500, 1499, 1));
        items.add(new populerDomain(1, "Women's Dress", "Sumit Panchal", 5, "sumitssp", 85, 4.5, 500, 3999, 1));
        items.add(new populerDomain(1, "Women's Middi", "Sumit Panchal", 5, "smit18", 75, 4.2, 500, 1199, 1));
        items.add(new populerDomain(1, "Men's Jacket", "Sumit Panchal", 5, "pngwing", 24, 3.5, 500, 9999, 1));
        items.add(new populerDomain(1, "Men's Suit", "Sumit Panchal", 5, "aksha", 36, 3.9, 500, 7499, 1));
        items.add(new populerDomain(1, "Lenovo Laptop", "Sumit Panchal", 5, "img_18", 96, 4.5, 500, 44990, 1));
        items.add(new populerDomain(2, "Apple MacBook Laptop ", "Panchal SUmit", 9, "img_55", 56, 4.9, 500, 67990, 2));
        items.add(new populerDomain(3, "Apple iPhone 13 ", "Kevin LAD", 20, "img_56", 94, 5, 500, 49999, 3));
        items.add(new populerDomain(4, "OnePlus Nord  ", "", 10, "img_57", 93, 3.5, 500, 999, 4));
        items.add(new populerDomain(5, "Echo Amazon ", "", 50, "img_59", 44, 4.9, 500, 5999, 5));

        recyclerviewpopuler = findViewById(R.id.recyclerView1);
        recyclerviewpopuler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapterPolpuler = new populerListAdapter(items);
        recyclerviewpopuler.setAdapter(adapterPolpuler);
    }
    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.navigation_home) {

            // Code for navigation_home
        }
        else if (menuItem.getItemId() == R.id.navigation_home11) {
            Toast.makeText(this, "LogOut SuccessFully", Toast.LENGTH_SHORT).show();
        }  else if (menuItem.getItemId() == R.id.navigation_home5) {
            startActivity(new Intent(HomePage.this,ProfileActivity.class));
        }else if (menuItem.getItemId() == R.id.navigation_home2) {
            startActivity(new Intent(HomePage.this,wishlistActivity.class));
        }else if (menuItem.getItemId() == R.id.navigation_home3) {
            startActivity(new Intent(HomePage.this,CartActivity.class));
        }else if (menuItem.getItemId() == R.id.navigation_home9) {
            startActivity(new Intent(HomePage.this,SettingActivity.class));
        }else if (menuItem.getItemId() == R.id.navigation_home10) {
            startActivity(new Intent(HomePage.this,SupportActivity.class));
        }else if (menuItem.getItemId() == R.id.navigation_home4) {
            Toast.makeText(this, "My Orders", Toast.LENGTH_SHORT).show();
        }
//        else if (menuItem.getItemId() == R.id.navigation_home2) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).commit();
//        } else if (menuItem.getItemId() == R.id.navigation_home3) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShareFragment()).commit();
//        }
        else {
            throw new IllegalStateException("Unexpected value: " + menuItem.getItemId());
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
