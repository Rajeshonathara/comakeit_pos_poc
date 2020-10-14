package com.comakeit.quorion.lib.numberpad;

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
public class NumberPad extends LinearLayout  {


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
    public NumberPad(Context context, @Nullable AttributeSet attrs) {
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


        inflater.inflate(R.layout.number_keys_component, this);

        grid = (GridView) findViewById(R.id.num_pad_lib_gridview);

        determineCustomAttributes(attrs);

        grid.setPadding(10, 10, 10, 10);
    }

    /**
     * Determine width integer.
     *
     * @param attrs the attrs
     */
    private void determineCustomAttributes(AttributeSet attrs) {
        Integer width = grid.getWidth();
        Integer custNumColumns = 6;
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.NumberPad);
        try {
            String custNumColumnsStr = typedArray.getString(R.styleable.NumberPad_cnpNumColumnsPerRow);
            if (custNumColumnsStr != null && custNumColumnsStr.length() > 0) {
                custNumColumns = Integer.valueOf(custNumColumnsStr);
            }
            if ( typedArray.hasValue(R.styleable.NumberPad_cnpColumnHeight) ) {
                custColumnHeight = Integer.valueOf(typedArray.getDimensionPixelSize(R.styleable.NumberPad_cnpColumnHeight, 80));
            }

            if ( typedArray.hasValue(R.styleable.NumberPad_cnpTextSize) ) {
                custTextSize = Integer.valueOf(typedArray.getDimensionPixelSize(R.styleable.NumberPad_cnpTextSize, 10));
            }

        } finally {
            typedArray.recycle();
        }
        Integer colWidth = (width - (10 * custNumColumns)) / custNumColumns;
        grid.setColumnWidth(colWidth);
        grid.setNumColumns(custNumColumns);

    }



    private void assignClickHandlers() {

        grid.setOnItemClickListener(new NumPadOnItemClickListener());
    /*new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // handle long-press
                if (eventHandler != null) {
                    eventHandler.onNumKeyPress((NumKeyItem) parent.getItemAtPosition(position));
                }
            }
        });
*/
        // long-pressing a day
        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> view, View cell, int position, long id) {
                // handle long-press
                if (eventHandler == null)
                    return false;

                eventHandler.onNumKeyPress((NumKeyItem) view.getItemAtPosition(position));
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
        ((NumberPadAdapter) grid.getAdapter()).setEventHandler(eventHandler);
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
        void onNumKeyPress(NumKeyItem numKeyItem);

        void onNumKeyPressItem(String  identifier);
    }


    /**
     * Display dates correctly in grid
     */
    public void updateNumberPad(List<NumKeyItem> events, String parentPackageName) {
        // update grid
        grid.setAdapter(new NumberPadAdapter(getContext(), events, getResources(), parentPackageName, custColumnHeight, custTextSize));
        assignClickHandlers();



    }


    public class NumPadOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // handle long-press
            if (eventHandler == null) {


                eventHandler.onNumKeyPress((NumKeyItem) parent.getItemAtPosition(position));
            }

        }
    }

}