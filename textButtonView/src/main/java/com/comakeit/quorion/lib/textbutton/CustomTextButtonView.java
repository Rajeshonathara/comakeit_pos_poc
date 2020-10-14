package com.comakeit.quorion.lib.textbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
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
public class CustomTextButtonView extends LinearLayout {


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
    private Integer custColumnHeight = null;
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
    public CustomTextButtonView(Context context, @Nullable AttributeSet attrs) {
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


        inflater.inflate(R.layout.custom_text_button_view, this);

        grid = (GridView) findViewById(R.id.cust_text_button_gridView);

        determineCustomAttributes(attrs);


        grid.setPadding(0, 10, 0, 10);
    }

    /**
     * Determine width integer.
     *
     * @param attrs the attrs
     */
    private void determineCustomAttributes(AttributeSet attrs) {
        Integer width = grid.getWidth();
        Integer custNumColumns = 6;
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomTextButtonView);
        try {
            String custNumColumnsStr = typedArray.getString(R.styleable.CustomTextButtonView_ctbNumColumnsPerRow);
            if (custNumColumnsStr != null && custNumColumnsStr.length() > 0) {
                custNumColumns = Integer.valueOf(custNumColumnsStr);
            }
            if (typedArray.hasValue(R.styleable.CustomTextButtonView_ctbColumnHeight)) {
                custColumnHeight = Integer.valueOf(typedArray.getDimensionPixelSize(R.styleable.CustomTextButtonView_ctbColumnHeight, 80));
            }

            if (typedArray.hasValue(R.styleable.CustomTextButtonView_ctbTextSize)) {
                custTextSize = Integer.valueOf(typedArray.getDimensionPixelSize(R.styleable.CustomTextButtonView_ctbTextSize, 10));
            }

        } finally {
            typedArray.recycle();
        }
        Integer colWidth = (width - (10 * custNumColumns)) / custNumColumns;
        grid.setColumnWidth(colWidth);
        grid.setNumColumns(custNumColumns);

    }


    private void assignClickHandlers() {

        grid.setOnItemClickListener(new CustomTextButtonItemClickListener());

        // long-pressing a day
        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> view, View cell, int position, long id) {
                // handle long-press
                if (eventHandler == null)
                    return false;

                eventHandler.onCustomTextButtonPress((CustomTextButtonItem) view.getItemAtPosition(position));
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
        ((CustomTextButtonAdapter) grid.getAdapter()).setEventHandler(eventHandler);
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
        void onCustomTextButtonPress(CustomTextButtonItem numKeyItem);

        void onCustomTextButtonPressItem(String identifier);
    }


    /**
     * Display dates correctly in grid
     */
    public void updateCustomTextButtonView(List<CustomTextButtonItem> events, String parentPackageName) {
        // update grid
        grid.setAdapter(new CustomTextButtonAdapter(getContext(), events, getResources(), parentPackageName, custColumnHeight, custTextSize));
        assignClickHandlers();
    }


    public class CustomTextButtonItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // handle long-press
            if (eventHandler == null) {
                eventHandler.onCustomTextButtonPress((CustomTextButtonItem) parent.getItemAtPosition(position));
            }
        }
    }

    public GridView getGrid() {
        return grid;
    }
}