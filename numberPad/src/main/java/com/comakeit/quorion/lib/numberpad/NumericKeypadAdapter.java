package com.comakeit.quorion.lib.numberpad;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.HashSet;
import java.util.List;

/**
 * The type NumericKeypad adapter.
 */
public class NumericKeypadAdapter extends ArrayAdapter<NumKeyItem> {


        /**
         * The Event handler.
         */
        private NumericKeypad.EventHandler eventHandler;

        private String parentPackageName;


        /**
         * The Num key items.
         */
        private HashSet<NumKeyItem> numKeyItems;

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
         * Instantiates a new NumericKeypad adapter.
         *
         * @param context   the context
         * @param numKeys   the num keys
         * @param resources the resources
         */
        public NumericKeypadAdapter(Context context, List<NumKeyItem> numKeys, Resources resources, String parentPackageName, Integer custColumnHeight, Integer custTextSize) {
                super(context, R.layout.number_key, numKeys);
                this.inflater = LayoutInflater.from(context);
                this.resources = resources;
                this.parentPackageName = parentPackageName;
                this.custColumnHeight =custColumnHeight;
                this.custTextSize = custTextSize;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
                NumKeyItem numKeyItem = getItem(position);

                // inflate item if it does not exist yet
                if (view == null) {
                        view = inflater.inflate(R.layout.number_key, parent, false);
                }
                Button button = (Button) view;
                button.setOnClickListener(new NumPadItemOnClickListener());


                //    button.setId(numKeyItem.getId());
                button.setTag(button.getId(), numKeyItem.getNumericKeypadType().toString());
                if (!TextUtils.isEmpty(numKeyItem.getTextColor())) {
                        button.setTextColor(getColor(numKeyItem.getTextColor()));
                }
                if (!TextUtils.isEmpty(numKeyItem.getBackgroundColor())) {
                        button.setBackgroundColor(getColor(numKeyItem.getBackgroundColor()));
                }
                if (!TextUtils.isEmpty(numKeyItem.getText())) {
                        button.setText(numKeyItem.getText());
                }
                if (numKeyItem.getBackgroundImage() != null) {
                        button.setBackground(numKeyItem.getBackgroundImage());
                }
               /* if(parent instanceof GridView) {
                        int width = ((GridView)parent).getColumnWidth();
                        ViewGroup.LayoutParams params = button.getLayoutParams();
                        params.height = width;
                        button.setLayoutParams(params);
                }*/

                if(custColumnHeight!=null ) {
                        ViewGroup.LayoutParams params = button.getLayoutParams();
                        params.width = custColumnHeight;
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

        public class NumPadItemOnClickListener implements View.OnClickListener {
                @Override
                public void onClick(View v) {
                        if (eventHandler != null) {

                                eventHandler.onNumKeyPressItem( (String)v.getTag(v.getId()));
                        }
                }
        }

        public void setEventHandler(NumericKeypad.EventHandler eventHandler) {
                this.eventHandler = eventHandler;
        }
}