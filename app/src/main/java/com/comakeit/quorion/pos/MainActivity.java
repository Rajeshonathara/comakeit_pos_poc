package com.comakeit.quorion.pos;

import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.comakeit.quorion.lib.picturebutton.CustomPictureButtonItem;
import com.comakeit.quorion.lib.picturebutton.CustomPictureButtonView;
import com.comakeit.quorion.lib.numberpad.NumKeyItem;
import com.comakeit.quorion.lib.numberpad.NumberPad;
import com.comakeit.quorion.lib.statusbar.CustomStatusBarItem;
import com.comakeit.quorion.lib.statusbar.CustomStatusBarView;
import com.comakeit.quorion.lib.textbutton.CustomTextButtonItem;
import com.comakeit.quorion.lib.textbutton.CustomTextButtonView;
import com.comakeit.quorion.pos.ui.config.ViewConfig;

import java.util.List;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * The Tag.
     */
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateNumberPad();
        updateCustomPictureButtons();
        updateCustomTextButtons();
        updateCustomStatusBars();

    }

    /**
     * Update number pad.
     */
    private void updateNumberPad() {
        ViewConfig viewConfig = new ViewConfig();
        List<NumKeyItem> numKeyItems = viewConfig.determineNumKeys(getAssets());
        NumberPad numberPad = ((NumberPad)findViewById(R.id.numPad));
        numberPad.updateNumberPad(        numKeyItems, getPackageName());
        // assign event handler
        numberPad.setEventHandler(new NumberPad.EventHandler()
        {
            @Override
            public void onNumKeyPress(NumKeyItem numKeyItem) {
                TextView textView = ((TextView)findViewById(R.id.click_text_view_id));
                textView.setText(String.valueOf(numKeyItem.getId()));
            }

            @Override
            public void onNumKeyPressItem(String  identifier) {
                TextView textView = ((TextView)findViewById(R.id.click_text_view_id));
                textView.setText(identifier);
            }
        });
    }


    /**
     * Update custom picture buttons.
     */
    private void updateCustomPictureButtons() {
        ViewConfig viewConfig = new ViewConfig();
        List<CustomPictureButtonItem> customPictureButtonItems = viewConfig.determineCustomPictureButtonItems(getAssets());

        CustomPictureButtonView pictureButtonView = ((CustomPictureButtonView)findViewById(R.id.cust_picture_view));
        pictureButtonView.updateCustomPictureButtonView(        customPictureButtonItems, getPackageName());
        // assign event handler
        pictureButtonView.setEventHandler(new CustomPictureButtonView.EventHandler()
        {


            @Override
            public void onCustomPictureButtonPress(CustomPictureButtonItem customPictureButtonItem) {
                TextView textView = ((TextView)findViewById(R.id.click_text_view_id));
                textView.setText(String.valueOf(customPictureButtonItem.getId()));
            }

            @Override
            public void onCustomPictureButtonPressItem(String  identifier) {
                TextView textView = ((TextView)findViewById(R.id.click_text_view_id));
                textView.setText(identifier);
            }
        });
    }


    private void updateCustomTextButtons() {
        ViewConfig viewConfig = new ViewConfig();
        List<CustomTextButtonItem> customTextButtonItems = viewConfig.determineCustomTextButtonItems(getAssets());

        CustomTextButtonView textButtonView = ((CustomTextButtonView)findViewById(R.id.cust_text_view));
        textButtonView.updateCustomTextButtonView(        customTextButtonItems, getPackageName());

        // assign event handler
        textButtonView.setEventHandler(new CustomTextButtonView.EventHandler()
        {


            @Override
            public void onCustomTextButtonPress(CustomTextButtonItem customTextButtonItem) {
                TextView textView = ((TextView)findViewById(R.id.click_text_view_id));
                textView.setText(String.valueOf(customTextButtonItem.getId()));
            }

            @Override
            public void onCustomTextButtonPressItem(String  identifier) {
                TextView textView = ((TextView)findViewById(R.id.click_text_view_id));
                textView.setText(identifier);
            }
        });
    }
    private void updateCustomStatusBars() {
        ViewConfig viewConfig = new ViewConfig();
        List<CustomStatusBarItem> customStatusBarItems = viewConfig.determineCustomStatusBarItems(getAssets());

        CustomStatusBarView textButtonView = ((CustomStatusBarView)findViewById(R.id.cust_status_bar_view));
        textButtonView.updateCustomStatusBarView(        customStatusBarItems, getPackageName());

    }
}

