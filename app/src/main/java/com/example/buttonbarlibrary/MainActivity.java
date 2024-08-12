package com.example.buttonbarlibrary;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.buttonbarlib.ButtonAnimationType;
import com.example.buttonbarlib.ButtonBarManager;
import com.example.buttonbarlib.ButtonBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reference the ButtonBarView
        ButtonBarView buttonBarView = findViewById(R.id.main_BAR_bottom);
        ButtonBarManager manager = new ButtonBarManager(buttonBarView);

        // Load icons
        Drawable icon1 = ContextCompat.getDrawable(this, R.drawable.home);
        Drawable icon2 = ContextCompat.getDrawable(this, R.drawable.settings);
        Drawable icon3 = ContextCompat.getDrawable(this, R.drawable.profile);

        // Add buttons with click listeners
        manager.addButton(icon1, v -> handleHomeClick(), ButtonAnimationType.ENLARGING_ICON);
        manager.addButton(icon2, v -> handleSettingsClick(), ButtonAnimationType.FILL_BAR);
        manager.addButton(icon3, v -> handleProfileClick(), ButtonAnimationType.OPPOSE_COLOR);


        // Customize the bar and button colors
//        manager.setBarBackgroundColor(ContextCompat.getColor(this, R.color.grey_bar));
//        manager.setButtonColor(ContextCompat.getColor(this, R.color.blue_bar));
//        manager.setBarOrientation(0);
//        manager.setLayoutMode(0);

    }

    private void handleHomeClick() {
        // Handle Home Button Click
    }

    private void handleSettingsClick() {
        // Handle Settings Button Click
    }

    private void handleProfileClick() {
        // Handle Profile Button Click
    }
}