package com.comakeit.quorion.lib.picturebutton;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.HashSet;
import java.util.List;

/**
 * The type Numpad adapter.
 */
public class CustomPictureButtonAdapter extends ArrayAdapter<CustomPictureButtonItem> {

        /**
         * The Event handler.
         */
        private CustomPictureButtonView.EventHandler eventHandler;

        private String parentPackageName;

        private Integer custColumnHeight;

        private Integer custTextSize;


        /**
         * The Num key items.
         */
        private HashSet<CustomPictureButtonItem> CustomPictureButtonItems;

        /**
         * The Inflater.
         */
        private LayoutInflater inflater;

        /**
         * The Resources.
         */
        private Resources resources;

        /**
         * Instantiates a new Numpad adapter.
         *
         * @param context   the context
         * @param numKeys   the num keys
         * @param resources the resources
         */
        public CustomPictureButtonAdapter(Context context, List<CustomPictureButtonItem> numKeys, Resources resources, String parentPackageName, Integer custColumnHeight,  Integer custTextSize) {
                super(context, R.layout.custom_picture_button, numKeys);
                this.inflater = LayoutInflater.from(context);
                this.resources = resources;
                this.parentPackageName = parentPackageName;
                this.custColumnHeight =custColumnHeight;
                this.custTextSize = custTextSize;

        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
                CustomPictureButtonItem CustomPictureButtonItem = getItem(position);

                // inflate item if it does not exist yet
                if (view == null)
                        view = inflater.inflate(R.layout.custom_picture_button, parent, false);
                ImageButton imageButton = (ImageButton) view;
                imageButton.setOnClickListener(new CustomPictureButtonItemOnClickListener());
                imageButton.setTag(imageButton.getId(), CustomPictureButtonItem.getIdentifier());
                if (!TextUtils.isEmpty(CustomPictureButtonItem.getBackgroundColor())) {
                        imageButton.setBackgroundColor(getColor(CustomPictureButtonItem.getBackgroundColor()));
                }

                if (CustomPictureButtonItem.getSrc() != null) {
                        imageButton.setImageDrawable(getDrawable(CustomPictureButtonItem.getSrc()));
                }
                if(custColumnHeight!=null ) {
                        ViewGroup.LayoutParams params = imageButton.getLayoutParams();
                        params.height = custColumnHeight;
                        imageButton.setLayoutParams(params);
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

        public class CustomPictureButtonItemOnClickListener implements View.OnClickListener {
                @Override
                public void onClick(View v) {
                        if (eventHandler != null) {

                                eventHandler.onCustomPictureButtonPressItem((String) v.getTag(v.getId()));
                        }
                }
        }

        public void setEventHandler(CustomPictureButtonView.EventHandler eventHandler) {
                this.eventHandler = eventHandler;
        }
}