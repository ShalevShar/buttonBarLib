package com.example.buttonbarlib;

import android.graphics.drawable.Drawable;
import android.view.View;

public class ButtonBarManager {
    private ButtonBarView buttonBarView;
    private ButtonAnimationManager animationManager;

    public ButtonBarManager(ButtonBarView buttonBarView) {
        this.buttonBarView = buttonBarView;
        this.animationManager = new ButtonAnimationManager(buttonBarView);
    }
    public void addButton(Drawable icon, View.OnClickListener listener, ButtonAnimationType animationType) {
        buttonBarView.addButton(icon, listener, animationType);
    }
    public void setBarOrientation(int orientation) {
        buttonBarView.setBarOrientation(orientation);
    }
    public void setLayoutMode(int layoutMode) {buttonBarView.setLayoutMode(layoutMode);}
    public void setBarBackgroundColor(int color) {buttonBarView.setBackgroundColor(color);}
    public void setButtonColor(int color) {buttonBarView.setButtonColor(color);}


}