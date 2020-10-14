package com.comakeit.quorion.lib.picturebutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.util.List;

/**
 * The type Numer pad.
 */
public class CustomPictureButtonView extends LinearLayout  {

    /**
     * The Grid.
     */
    private GridView grid;

    /**
     * The Event handler.
     */
    private EventHandler eventHandler = null;

    /**
     * The Cust column height.
     */
    private Integer custColumnHeight=null;
    /**
     * The Cust text size.
     */
    private Integer custTextSize;

    /**
     * Instantiates a new Numer pad.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public CustomPictureButtonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initControl(context, attrs);

    }

    /**
     * Init control.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    private void initControl(Context context, @Nullable AttributeSet attrs) {

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        inflater.inflate(R.layout.custom_picture_button_view, this);

        grid = (GridView) findViewById(R.id.cust_picture_button_gridView);
        determineCustomAttributes(attrs);
        grid.setPadding(0, 10, 0, 10);
    }

    /**
     * Determine custom attributes.
     *
     * @param attrs the attrs
     */
    private void determineCustomAttributes(AttributeSet attrs ) {
        Integer width = grid.getWidth();
        Integer custNumColumns = 6;
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomPictureButtonView);
        try {
            String custNumColumnsStr = typedArray.getString(R.styleable.CustomPictureButtonView_cpbNumColumnsPerRow);
            if (custNumColumnsStr != null && custNumColumnsStr.length() > 0) {
                custNumColumns = Integer.valueOf(custNumColumnsStr);
            }

            if ( typedArray.hasValue(R.styleable.CustomPictureButtonView_cpbColumnHeight) ) {

                custColumnHeight = Integer.valueOf(typedArray.getDimensionPixelSize(R.styleable.CustomPictureButtonView_cpbColumnHeight, 80));
            }

        } finally {
            typedArray.recycle();
        }
        Integer colWidth = (width - (10 * custNumColumns)) / custNumColumns;
        grid.setColumnWidth(colWidth);
        grid.setNumColumns(custNumColumns);

    }


    private void assignClickHandlers() {

        grid.setOnItemClickListener(new CustomPictureButtonItemClickListener());

        // long-pressing a day
        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> view, View cell, int position, long id) {
                // handle long-press
                if (eventHandler == null)
                    return false;

                eventHandler.onCustomPictureButtonPress((CustomPictureButtonItem) view.getItemAtPosition(position));
                return true;
            }
        });
    }


    /**
     * Sets event handler.
     *
     * @param eventHandler the event handler
     */
    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
        ((CustomPictureButtonAdapter) grid.getAdapter()).setEventHandler(eventHandler);
    }


    /**
     * The interface Event handler.
     */
    public interface EventHandler {
        /**
         * On num key press.
         *
         * @param numKeyItem the num key item
         */
        void onCustomPictureButtonPress(CustomPictureButtonItem numKeyItem);

        void onCustomPictureButtonPressItem(String identifier);
    }


    /**
     * Display dates correctly in grid
     */
    public void updateCustomPictureButtonView(List<CustomPictureButtonItem> events, String parentPackageName) {
        // update grid
        grid.setAdapter(new CustomPictureButtonAdapter(getContext(), events, getResources(), parentPackageName, custColumnHeight, custTextSize));
        assignClickHandlers();
    }


    /**
     * The type Custom picture button item click listener.
     */
    public class CustomPictureButtonItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // handle long-press
            if (eventHandler == null) {
                eventHandler.onCustomPictureButtonPress((CustomPictureButtonItem) parent.getItemAtPosition(position));
            }
        }
    }




    public static int dpToPx(float dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }


    public static int spToPx(float sp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }


    public static int dpToSp(float dp, Context context) {
        return (int) (dpToPx(dp, context) / context.getResources().getDisplayMetrics().scaledDensity);
    }
}