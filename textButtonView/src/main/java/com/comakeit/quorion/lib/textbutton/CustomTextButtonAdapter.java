package com.comakeit.quorion.lib.textbutton;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import java.util.HashSet;
import java.util.List;

/**
 * The type Numpad adapter.
 */
public class CustomTextButtonAdapter extends ArrayAdapter<CustomTextButtonItem> {

        /**
         * The Event handler.
         */
        private CustomTextButtonView.EventHandler eventHandler;

        private String parentPackageName;


        /**
         * The Num key items.
         */
        private HashSet<CustomTextButtonItem> CustomTextButtonItems;

        /**
         * The Inflater.
         */
        private LayoutInflater inflater;

        /**
         * The Resources.
         */
        private Resources resources;


        private Integer custColumnHeight;

        private Integer custTextSize;

        /**
         * Instantiates a new Numpad adapter.
         *
         * @param context   the context
         * @param numKeys   the num keys
         * @param resources the resources
         */
        public CustomTextButtonAdapter(Context context, List<CustomTextButtonItem> numKeys, Resources resources, String parentPackageName, Integer custColumnHeight,  Integer custTextSize) {
                super(context, R.layout.custom_text_button, numKeys);
                this.inflater = LayoutInflater.from(context);
                this.resources = resources;
                this.parentPackageName = parentPackageName;
                this.custColumnHeight =custColumnHeight;
                this.custTextSize = custTextSize;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
                CustomTextButtonItem CustomTextButtonItem = getItem(position);

                // inflate item if it does not exist yet
                if (view == null)
                        view = inflater.inflate(R.layout.custom_text_button, parent, false);
                Button button = (Button) view;
                button.setOnClickListener(new CustomTextButtonItemOnClickListener());

                //    button.setId(numKeyItem.getId());
                button.setTag(button.getId(), CustomTextButtonItem.getIdentifier());
                if (!TextUtils.isEmpty(CustomTextButtonItem.getTextColor())) {
                        button.setTextColor(getColor(CustomTextButtonItem.getTextColor()));
                }
                if (!TextUtils.isEmpty(CustomTextButtonItem.getBackgroundColor())) {
                        button.setBackgroundColor(getColor(CustomTextButtonItem.getBackgroundColor()));
                }
                if (!TextUtils.isEmpty(CustomTextButtonItem.getText())) {
                        button.setText(CustomTextButtonItem.getText());
                }
                if (CustomTextButtonItem.getSrc() != null) {
                        button.setBackground(getDrawable(CustomTextButtonItem.getSrc()));
                }
                button.setTransformationMethod(null);

                if(custColumnHeight!=null ) {
                        ViewGroup.LayoutParams params = button.getLayoutParams();
                        params.height = custColumnHeight;
                        button.setLayoutParams(params);
                }
                if(custTextSize != null ) {
                        button.setTextSize(TypedValue.COMPLEX_UNIT_PX ,custTextSize.floatValue());
                }

                return view;
        }

        /**
         * Gets color.
         *
         * @param colorStr the color str
         * @return the color
         */
        private int getColor(String colorStr) {
                return resources.getColor(resources.getIdentifier(colorStr, "color", parentPackageName), null);
        }

        /**
         * Gets drawable.
         *
         * @param drawableStr the drawable str
         * @return the drawable
         */
        private Drawable getDrawable(String drawableStr) {
                return resources.getDrawable(resources.getIdentifier(drawableStr, "drawable", parentPackageName), null);
        }

        public class CustomTextButtonItemOnClickListener implements View.OnClickListener {
                @Override
                public void onClick(View v) {
                        if (eventHandler != null) {

                                eventHandler.onCustomTextButtonPressItem((String) v.getTag(v.getId()));
                        }
                }
        }

        public void setEventHandler(CustomTextButtonView.EventHandler eventHandler) {
                this.eventHandler = eventHandler;
        }
}