<div align="center">
    <h1>ButtonBar Library</h1>
    <p>A versatile customizable button bar library for Android.</p>
</div>

---

<div align="center">
    <h3>Design</h3>
    <p><b>Custom Orientation</b><br>
    Choose between <b>horizontal</b> bar or <b>vertical</b> bar orientation.</p>
</div>

<div align="center">
    <img width="200" alt="horizontal-vertical-bar" src="https://github.com/user-attachments/assets/6cebc497-84c4-4501-97e5-c67b3fa12d5c">
</div>

<div align="center">
    <p><b>Button and Bar Shape</b><br>
    Choose between <b>round</b> or <b>square</b> shapes for both the buttons and the bar.</p>
</div>

<div align="center">
    <img width="201" alt="round-bar" src="https://github.com/user-attachments/assets/6abce6c6-b6ed-46b8-b208-93af69e04ceb">
    <img width="201" alt="square-bar" src="https://github.com/user-attachments/assets/747c17a1-68e3-48d2-8575-c5b6304d1255">
</div>

<div align="center">
    <p><b>Custom Colors</b><br>
    Set custom colors for the bar, buttons, and button icons to match your app's theme.</p>
</div>

<div align="center">
    <img width="201" alt="any-colors" src="https://github.com/user-attachments/assets/a3a64be5-b671-46ce-955f-edaf41aa49f1">
</div>

<div align="center">
    <h3>Animation</h3>
    <p><b>Choose from three different animation types for button interactions:</b><br>
    	</br><b>1.Enlarging Icon Animation:</b></br> The button's icon enlarges when pressed.</b>
	</br><b>2.Fill Bar Animation:</b></br> The button expands to fill the bar's width or height on click.</b>
	</br><b>3.Oppose Color Animation:</b></br> The button swaps its icon and background colors upon interaction.<b></b></p>
</div>

<div align="center">
    <img width="200" alt="animation-example" src="https://github.com/user-attachments/assets/f16503df-53e9-4ba6-8562-3a82f82e1fe3">
</div>


---

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
