<div style="text-align: center;">
    <h1 align="center" style="font-size: 30px;">ButtonBar Library</h1>
</div>
<div align="center">
	A versatile costumizable button bar library for Android.
    <b>
	    <b>  
	    <br>
	    Functionality</b>
    <br>
    <br>
    <b>
	    Design:</b>
    <br>
	    <br>
	    <b>
	     Custom Orientation</br>
    <b>Choose between horizontal bar or vertical bar orientation.
    <b>
	     Button and Bar Shape</b>
    <br>
        Choose between round or square shapes for both the button and the bar.
      <br>
    <img width="201" alt="round-bar" src="https://github.com/user-attachments/assets/6abce6c6-b6ed-46b8-b208-93af69e04ceb">
    <img width="201" alt="square-bar" src="https://github.com/user-attachments/assets/747c17a1-68e3-48d2-8575-c5b6304d1255">
    <br>
       <b>
	     Costum Colors </b>
    <br>
        Set custom colors for the bar, buttons, and button icons to match your app's theme.
    <br>
    <img width="201" alt="any-colors" src="https://github.com/user-attachments/assets/a3a64be5-b671-46ce-955f-edaf41aa49f1">
 
<b>Animation:</br>
<b>
    Click Animations: Choose from three different animation types for button interactions:
    Enlarging Icon Animation: The button's icon enlarges when pressed.
    Fill Bar Animation: The button expands to fill the bar's width or height on click.
    Oppose Color Animation: The button swaps its icon and background colors upon interaction.
        <br align="center"> ![B9A33A2F-5D36-4B89-B962-71E4D7663150-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/f16503df-53e9-4ba6-8562-3a82f82e1fe3)</br>
	<b align="center"> ![ezgif cowm-animated-gif-maker](https://github.com/user-attachments/assets/731facb2-98af-4d12-a5b2-e5d0561e056f)</br>
</div>




# Usage
### XML Layout Example:
```java
 <com.example.buttonbarlib.ButtonBarView
    android:id="@+id/buttonBarView"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:barOrientation="horizontal"
    app:buttonShape="round"
    app:barShape="round"
    app:backgroundColor="@color/white"
    app:buttonColor="@color/black"
    app:iconColor="@color/white">
</com.example.buttonbarlib.ButtonBarView>
```

## Activity Implementation Example:
```java
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
```


## Credits
### Authors and Contributors
	Shalev Shar - Developer and maintainer of the buttonBarLib Library.
### Open Source Libraries Used
	Android SDK - Core libraries provided by Google for Android development.
	AppCompat Library - Part of Android Jetpack, providing backward-compatible versions of Android components.
-----

## License
	
-----

Enjoy using buttonBarLib for your Android projects!
