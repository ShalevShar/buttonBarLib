package com.example.buttonbarlib;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageButton;

public class ButtonAnimationManager {
    private ButtonBarView buttonBarView;
    private boolean isExpanded = false;
    private boolean isSwapped = false; // Track the toggle state
    private int originalWidth = 0; // Store the original width of the button
    private int originalHeight = 0; // Store the original height of the button
    private int barOrientation; // Store the orientation

    public ButtonAnimationManager(ButtonBarView buttonBarView) {
        this.buttonBarView = buttonBarView;
        this.barOrientation = buttonBarView.getBarOrientation();
    }

    // Enlarge the icon on button press
    public void applyEnlargingIconAnimation(AppCompatImageButton button) {
        button.setOnClickListener(v -> {
            // Enlarge the button icon
            button.animate()
                    .scaleX(1.2f)
                    .scaleY(1.2f)
                    .setDuration(150)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            // Reset the size after animation
                            button.animate()
                                    .scaleX(1f)
                                    .scaleY(1f)
                                    .setDuration(150)
                                    .start();
                        }
                    })
                    .start();
        });
    }

    // Make the icon fill the entire bar
    public void applyFillBarAnimation(AppCompatImageButton button) {
        button.setOnClickListener(v -> {
            // Ensure we are working with a fresh copy of layout parameters
            ViewGroup.LayoutParams params = button.getLayoutParams();

            if (originalWidth == 0 || originalHeight == 0) {
                // Set originalWidth and originalHeight if they are not set yet
                originalWidth = button.getWidth();
                originalHeight = button.getHeight();
            }

            // Determine the target width or height based on the orientation
            int targetSize;
            if (barOrientation == ButtonBarView.VERTICAL) {
                targetSize = buttonBarView.getHeight() - buttonBarView.getPaddingTop() - buttonBarView.getPaddingBottom(); // Adjust for padding
                if (params.height != originalHeight) {
                    params.height = isExpanded ? originalHeight : targetSize;
                }
            } else {
                targetSize = buttonBarView.getWidth() - buttonBarView.getPaddingLeft() - buttonBarView.getPaddingRight(); // Adjust for padding
                if (params.width != originalWidth) {
                    params.width = isExpanded ? originalWidth : targetSize;
                }
            }

            // Determine the starting and ending sizes for animation
            int startSize = isExpanded ? targetSize : (barOrientation == ButtonBarView.VERTICAL ? originalHeight : originalWidth);
            int endSize = isExpanded ? (barOrientation == ButtonBarView.VERTICAL ? originalHeight : originalWidth) : targetSize;

            ValueAnimator sizeAnimator = ValueAnimator.ofInt(startSize, endSize);
            sizeAnimator.addUpdateListener(animation -> {
                int animatedValue = (int) animation.getAnimatedValue();
                Log.d("ButtonAnimationManager", "Animated Value: " + animatedValue);

                if (barOrientation == ButtonBarView.VERTICAL) {
                    params.height = animatedValue;
                } else {
                    params.width = animatedValue;
                }

                button.setLayoutParams(params);
                button.requestLayout(); // Request layout update
            });

            sizeAnimator.setDuration(200);
            sizeAnimator.start();

            // Toggle the expanded state
            isExpanded = !isExpanded;
        });
    }


    // Oppose the color of the icon with the color of the button
    public void applyOpposeColorAnimation(AppCompatImageButton button) {
        button.setOnClickListener(v -> {
            // Get the current icon and button colors
            Drawable iconDrawable = button.getDrawable();
            Drawable background = button.getBackground();
            int originalIconColor = buttonBarView.iconColor;
            int originalButtonColor = buttonBarView.buttonColor;

            if (iconDrawable == null || background == null) {
                return;
            }

            // Determine current and target colors
            int targetIconColor = isSwapped ? originalIconColor : originalButtonColor;
            int targetButtonColor = isSwapped ? originalButtonColor : originalIconColor;

            // Set initial colors
            iconDrawable.setColorFilter(isSwapped ? originalButtonColor : originalIconColor, PorterDuff.Mode.SRC_IN);
            button.setImageDrawable(iconDrawable);
            background.setColorFilter(isSwapped ? originalIconColor : originalButtonColor, PorterDuff.Mode.SRC_IN);
            button.setBackground(background);

            // Create and start color animators
            ValueAnimator iconColorAnimator = ValueAnimator.ofArgb(
                    isSwapped ? originalButtonColor : originalIconColor,
                    targetIconColor
            );
            ValueAnimator buttonColorAnimator = ValueAnimator.ofArgb(
                    isSwapped ? originalIconColor : originalButtonColor,
                    targetButtonColor
            );

            iconColorAnimator.addUpdateListener(animation -> {
                int animatedIconColor = (int) animation.getAnimatedValue();
                iconDrawable.setColorFilter(animatedIconColor, PorterDuff.Mode.SRC_IN);
                button.setImageDrawable(iconDrawable); // Update drawable
            });

            buttonColorAnimator.addUpdateListener(animation -> {
                int animatedButtonColor = (int) animation.getAnimatedValue();
                background.setColorFilter(animatedButtonColor, PorterDuff.Mode.SRC_IN);
                button.setBackground(background);
            });

            iconColorAnimator.setDuration(200);
            buttonColorAnimator.setDuration(200);

            iconColorAnimator.start();
            buttonColorAnimator.start();

            // Toggle the swap state
            isSwapped = !isSwapped;
        });
    }
}
