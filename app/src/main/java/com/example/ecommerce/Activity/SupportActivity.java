package com.example.ecommerce.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ecommerce.R;
import com.google.android.material.navigation.NavigationView;

public class SupportActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private Button saveAdd ;

    Toolbar toolbar;
    Menu menu;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_support_activity);

                saveAdd = findViewById(R.id.supportbtn);

        saveAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SupportActivity.this,"mail on : panchalsumit187@gmail.com",Toast.LENGTH_SHORT).show();
            }
        });
        /*---------------------Hooks------------------------*/
        drawerLayout = findViewById(R.id.drawwerlayoutsupport);
        navigationView = findViewById(R.id.navigation_view_support);
        toolbar = findViewById(R.id.toolbarSupport);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.images);
        getSupportActionBar().setTitle("");




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
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.navigation_home);
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
            startActivity(new Intent(SupportActivity.this,ProfileActivity.class));
        }else if (menuItem.getItemId() == R.id.navigation_home2) {
            startActivity(new Intent(SupportActivity.this,wishlistActivity.class));
        }else if (menuItem.getItemId() == R.id.navigation_home3) {
            startActivity(new Intent(SupportActivity.this,CartActivity.class));
        }else if (menuItem.getItemId() == R.id.navigation_home9) {
            startActivity(new Intent(SupportActivity.this,SettingActivity.class));
        }else if (menuItem.getItemId() == R.id.navigation_home10) {
            startActivity(new Intent(SupportActivity.this,SupportActivity.class));
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
