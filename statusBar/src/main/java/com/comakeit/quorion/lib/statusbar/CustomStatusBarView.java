package com.comakeit.quorion.lib.statusbar;

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
public class CustomStatusBarView extends LinearLayout  {

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
    public CustomStatusBarView(Context context, @Nullable AttributeSet attrs) {
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


        inflater.inflate(R.layout.custom_status_bar_view, this);

        grid = (GridView) findViewById(R.id.cust_status_bar_gridView);
        determineCustomAttributes(attrs);
        grid.setPadding(0, 0, 0, 0);
    }

    /**
     * Determine custom attributes.
     *
     * @param attrs the attrs
     */
    private void determineCustomAttributes(AttributeSet attrs ) {
        Integer width = grid.getWidth();
        Integer custNumColumns = 5;
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomStatusBarView);
        try {

            if ( typedArray.hasValue(R.styleable.CustomStatusBarView_csbNumColumnsPerRow) ) {
                String custNumColumnsStr = typedArray.getString(R.styleable.CustomStatusBarView_csbNumColumnsPerRow);
                custNumColumns = Integer.valueOf(custNumColumnsStr);
            }

            if ( typedArray.hasValue(R.styleable.CustomStatusBarView_csbColumnHeight) ) {

                custColumnHeight = Integer.valueOf(typedArray.getDimensionPixelSize(R.styleable.CustomStatusBarView_csbColumnHeight, 80));
            }

            if ( typedArray.hasValue(R.styleable.CustomStatusBarView_csbTextSize) ) {

                custTextSize = Integer.valueOf(typedArray.getDimensionPixelSize(R.styleable.CustomStatusBarView_csbTextSize, 10));
            }

        } finally {
            typedArray.recycle();
        }
        Integer colWidth = ((width -25) - (5 * custNumColumns)) / custNumColumns;
        grid.setColumnWidth(colWidth);
        grid.setNumColumns(custNumColumns);

    }


    private void assignClickHandlers() {

        grid.setOnItemClickListener(new CustomStatusBarItemClickListener());

        // long-pressing a day
        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> view, View cell, int position, long id) {
                // handle long-press
                if (eventHandler == null)
                    return false;

                eventHandler.onCustomStatusBarPress((CustomStatusBarItem) view.getItemAtPosition(position));
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
        ((CustomStatusBarAdapter) grid.getAdapter()).setEventHandler(eventHandler);
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
        void onCustomStatusBarPress(CustomStatusBarItem numKeyItem);

        void onCustomStatusBarPressItem(String identifier);
    }


    /**
     * Display dates correctly in grid
     */
    public void updateCustomStatusBarView(List<CustomStatusBarItem> events, String parentPackageName) {
        // update grid
        grid.setAdapter(new CustomStatusBarAdapter(getContext(), events, getResources(), parentPackageName, custColumnHeight, custTextSize));
        assignClickHandlers();
    }


    /**
     * The type Custom picture button item click listener.
     */
    public class CustomStatusBarItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // handle long-press
            if (eventHandler == null) {
                eventHandler.onCustomStatusBarPress((CustomStatusBarItem) parent.getItemAtPosition(position));
            }
        }
    }


}