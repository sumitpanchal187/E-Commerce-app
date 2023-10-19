package com.example.ecommerce.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.ecommerce.R;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);

        // Find the root ConstraintLayout in your layout
        ConstraintLayout rootLayout = findViewById(R.id.mainConstrainnoti);

        // Count the total number of ConstraintLayout elements within the root layout
        int totalNotifications = countConstraintLayouts(rootLayout);

        // Find the numberNoti TextView
        TextView numberNotiTextView = findViewById(R.id.numberNoti);

        // Update the numberNoti TextView with the count
        numberNotiTextView.setText(String.valueOf(totalNotifications));
    }

    // Function to count the total number of ConstraintLayout elements within a layout
    private int countConstraintLayouts(ViewGroup viewGroup) {
        int count = 0;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof ConstraintLayout) {
                count++;
            } else if (child instanceof ViewGroup) {
                // If the child is a ViewGroup, recursively count ConstraintLayouts within it
                count += countConstraintLayouts((ViewGroup) child);
            }
        }
        return count;
    }
}
