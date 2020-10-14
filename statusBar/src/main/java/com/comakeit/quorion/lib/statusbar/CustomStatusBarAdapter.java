package com.comakeit.quorion.lib.statusbar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.HashSet;
import java.util.List;

/**
 * The type Numpad adapter.
 */
public class CustomStatusBarAdapter extends ArrayAdapter<CustomStatusBarItem> {

        /**
         * The Event handler.
         */
        private CustomStatusBarView.EventHandler eventHandler;

        private String parentPackageName;

        private Integer custColumnHeight;

        private Integer custTextSize;


        /**
         * The Num key items.
         */
        private HashSet<CustomStatusBarItem> CustomStatusBarItems;

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
        public CustomStatusBarAdapter(Context context, List<CustomStatusBarItem> numKeys, Resources resources, String parentPackageName, Integer custColumnHeight, Integer custTextSize) {
                super(context, R.layout.custom_status_bar_item, numKeys);
                this.inflater = LayoutInflater.from(context);
                this.resources = resources;
                this.parentPackageName = parentPackageName;
                this.custColumnHeight =custColumnHeight;
                this.custTextSize = custTextSize;

        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
                CustomStatusBarItem customStatusBarItem = getItem(position);

                // inflate item if it does not exist yet
                if (view == null){
                        if(CustomStatusBarItem.StatusType.CURRENT_TIME.equals(    customStatusBarItem.getStatusType())||
                                CustomStatusBarItem.StatusType.CURRENT_DAY.equals(    customStatusBarItem.getStatusType())) {
                                view = inflater.inflate(R.layout.custom_status_bar_time_item, parent, false);

                                TextClock textClock = (TextClock) view;
                                if(custColumnHeight!=null ) {
                                        ViewGroup.LayoutParams params = textClock.getLayoutParams();
                                        params.height = custColumnHeight;
                                        textClock.setLayoutParams(params);
                                }

                                if (!TextUtils.isEmpty(customStatusBarItem.getTextColor())) {
                                        textClock.setTextColor(getColor(customStatusBarItem.getTextColor()));
                                }
                                if (!TextUtils.isEmpty(customStatusBarItem.getDateTimePatterns())) {
                                        textClock.setFormat12Hour(customStatusBarItem.getDateTimePatterns());
                                }
                                if(custTextSize != null ) {
                                        textClock.setTextSize(TypedValue.COMPLEX_UNIT_PX ,custTextSize.floatValue());
                                }

                        } else {
                                view = inflater.inflate(R.layout.custom_status_bar_item, parent, false);


                                TextView textView = (TextView) view;

                                if(custColumnHeight!=null ) {
                                        ViewGroup.LayoutParams params = textView.getLayoutParams();
                                        params.height = custColumnHeight;
                                        textView.setLayoutParams(params);
                                }


                                if (!TextUtils.isEmpty(customStatusBarItem.getTextColor())) {
                                        textView.setTextColor(getColor(customStatusBarItem.getTextColor()));
                                }

                                if (!TextUtils.isEmpty(customStatusBarItem.getText())) {
                                        textView.setText(customStatusBarItem.getText());
                                }

                                if (!TextUtils.isEmpty(customStatusBarItem.getTextAlign())) {
                                        if(customStatusBarItem.getTextAlign().toLowerCase().equals("left")){
                                                textView.setGravity(Gravity.LEFT);
                                        } else   if(customStatusBarItem.getTextAlign().toLowerCase().equals("right")){
                                                textView.setGravity(Gravity.RIGHT);
                                        }else   if(customStatusBarItem.getTextAlign().toLowerCase().equals("center")){
                                                textView.setGravity(Gravity.CENTER);
                                        }
                                }
                                if(custTextSize != null ) {
                                        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX ,custTextSize.floatValue());
                                }
                        }
                        if (!TextUtils.isEmpty(customStatusBarItem.getBackgroundColor())) {
                                view.setBackgroundColor(getColor(customStatusBarItem.getBackgroundColor()));
                        }



                        view.setTag(view.getId(), customStatusBarItem.getIdentifier());

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

        public class CustomStatusBarItemOnClickListener implements View.OnClickListener {
                @Override
                public void onClick(View v) {
                        if (eventHandler != null) {

                                eventHandler.onCustomStatusBarPressItem((String) v.getTag(v.getId()));
                        }
                }
        }

        public void setEventHandler(CustomStatusBarView.EventHandler eventHandler) {
                this.eventHandler = eventHandler;
        }
}