package com.example.buttonbarlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;

public class ButtonBarView extends LinearLayout {
    private int layoutMode;
    private int barShape;// 0: Round, 1: Square
    private int buttonShape; // 0: Round, 1: Square
    private int barOrientation; // 0: Horizontal, 1: Vertical
    private int backgroundColor;
    int buttonColor;
    int iconColor;
    private Drawable backgroundDrawable;
    private int buttonWidth;
    private int buttonHeight;
    private ButtonAnimationManager animationManager;
    private Context context;
    // New field for layout mode

    public ButtonBarView(Context context) {
        super(context);
        this.context = context; // Initialize context
        init(context, null);
    }

    public ButtonBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context; // Initialize context
        init(context, attrs);
    }

    public ButtonBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context; // Initialize context
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ButtonBarView);
            barOrientation = typedArray.getInt(R.styleable.ButtonBarView_barOrientation, 0);
            buttonShape = typedArray.getInt(R.styleable.ButtonBarView_buttonShape, 0);
            barShape = typedArray.getInt(R.styleable.ButtonBarView_barShape, 0); // New barShape attribute
            backgroundColor = typedArray.getColor(R.styleable.ButtonBarView_backgroundColor, 0xFFFFFFFF);
            buttonColor = typedArray.getColor(R.styleable.ButtonBarView_buttonColor, 0xFF000000);
            iconColor = typedArray.getColor(R.styleable.ButtonBarView_iconColor, 0xFF000000);
            backgroundDrawable = typedArray.getDrawable(R.styleable.ButtonBarView_backgroundDrawable);
            buttonWidth = typedArray.getDimensionPixelSize(R.styleable.ButtonBarView_buttonWidth, LayoutParams.WRAP_CONTENT);
            buttonHeight = typedArray.getDimensionPixelSize(R.styleable.ButtonBarView_buttonHeight, LayoutParams.WRAP_CONTENT);
            layoutMode = typedArray.getInt(R.styleable.ButtonBarView_layoutMode, 0); // New attribute for layout mode
            typedArray.recycle();
        }

        setOrientation(barOrientation == 0 ? HORIZONTAL : VERTICAL);
        setBackgroundColor(backgroundColor);
        if (backgroundDrawable != null) {
            setBackground(backgroundDrawable);
        }

        applyBarShape(); // Apply the bar shape
        applyLayoutMode(); // Apply the layout mode

        animationManager = new ButtonAnimationManager(this);
    }

    private void applyBarShape() {
        if (barShape == 0) { // Round shape
            setBackgroundResource(R.drawable.round_bar);
        } else { // Square shape
            setBackgroundResource(R.drawable.square_bar);
        }
    }

    private void applyLayoutMode() {
        // Ensure the view has LayoutParams before applying changes
        if (getLayoutParams() == null) {
            setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }

        LayoutParams params = (LayoutParams) getLayoutParams();

        if (layoutMode == 0) { // startToEnd
            params.width = LayoutParams.MATCH_PARENT;
            params.height = LayoutParams.WRAP_CONTENT;
            setGravity(Gravity.START);
        } else if (layoutMode == 1) { // centerFloat
            if (barOrientation == HORIZONTAL) {
                params.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.6);
                params.height = LayoutParams.WRAP_CONTENT;
            } else {
                params.width = LayoutParams.WRAP_CONTENT;
                params.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.6);
            }
            setGravity(Gravity.CENTER);
        }

        // Update the LayoutParams of the view
        setLayoutParams(params);
    }

    public void addButton(Drawable icon, View.OnClickListener listener, ButtonAnimationType animationType) {
        AppCompatImageButton button = getAppCompatImageButton(icon, listener);

        // Apply the icon color
        setIconColor(iconColor, button.getDrawable());

        // Apply chosen animation
        switch (animationType) {
            case ENLARGING_ICON:
                animationManager.applyEnlargingIconAnimation(button);
                break;
            case FILL_BAR:
                animationManager.applyFillBarAnimation(button);
                break;
            case OPPOSE_COLOR:
                animationManager.applyOpposeColorAnimation(button);
                break;
        }

        Drawable background = button.getBackground();
        if (background != null) {
            background.setColorFilter(buttonColor, PorterDuff.Mode.SRC_IN);
        }

        LayoutParams params = new LayoutParams(buttonWidth, buttonHeight);
        params.weight = 1f;
        int marginValue = Integer.parseInt(context.getString(R.string.default_margins));
        params.setMargins(marginValue, marginValue, marginValue, marginValue);

        if (barOrientation == HORIZONTAL) {
            params.gravity = Gravity.CENTER_VERTICAL;
        } else {
            params.gravity = Gravity.CENTER_HORIZONTAL;
        }

        addView(button, params);
    }

    @NonNull
    private AppCompatImageButton getAppCompatImageButton(Drawable icon, OnClickListener listener) {
        AppCompatImageButton button = new AppCompatImageButton(getContext());
        button.setImageDrawable(icon);
        button.setScaleType(AppCompatImageButton.ScaleType.CENTER_INSIDE);

        // Apply drawable resource as background
        if (buttonShape == 0) {
            button.setBackgroundResource(R.drawable.round_button);
            Log.d("ButtonBarView", "Button shape is rounded");
        } else {
            button.setBackgroundResource(R.drawable.square_button);
            Log.d("ButtonBarView", "Button shape is squared");
        }

        button.setOnClickListener(listener);
        return button;
    }

    private void setIconColor(int color, Drawable icon) {
        if (icon != null) {
            Log.d("ButtonBarView", "Setting icon color: " + Integer.toHexString(color));
            icon.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        } else {
            Log.d("ButtonBarView", "Icon is null, cannot set color");
        }
    }

    public void setBarOrientation(int orientation) {
        setOrientation(orientation == 0 ? HORIZONTAL : VERTICAL);
    }

    public void setBackgroundColor(int color) {
        backgroundColor = color;
        super.setBackgroundColor(color);
        applyBarShape(); // Reapply the shape after setting the background color
    }

    public void setButtonColor(int color) {
        buttonColor = color;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child instanceof AppCompatImageButton) {
                Drawable background = child.getBackground();
                if (background != null) {
                    background.setColorFilter(buttonColor, PorterDuff.Mode.SRC_IN);
                }
            }
        }
    }

    public void setBackground(Drawable drawable) {
        backgroundDrawable = drawable;
        super.setBackground(drawable);  // This sets the background to the Drawable
    }
    public int getBarOrientation() {
        return barOrientation;
    }
}