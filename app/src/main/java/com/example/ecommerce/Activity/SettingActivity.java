package com.example.ecommerce.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ecommerce.R;
import com.google.android.material.navigation.NavigationView;

public class SettingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        /*---------------------Hooks------------------------*/
        drawerLayout = findViewById(R.id.drawwerlayoutsetting);
        navigationView = findViewById(R.id.navigation_viewsetting);
        toolbar = findViewById(R.id.toolbarSettings);
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
            startActivity(new Intent(SettingActivity.this,ProfileActivity.class));
        }else if (menuItem.getItemId() == R.id.navigation_home2) {
            startActivity(new Intent(SettingActivity.this,wishlistActivity.class));
        }else if (menuItem.getItemId() == R.id.navigation_home3) {
            startActivity(new Intent(SettingActivity.this,CartActivity.class));
        }else if (menuItem.getItemId() == R.id.navigation_home9) {
            startActivity(new Intent(SettingActivity.this,SettingActivity.class));
        }else if (menuItem.getItemId() == R.id.navigation_home10) {
            startActivity(new Intent(SettingActivity.this,SupportActivity.class));
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