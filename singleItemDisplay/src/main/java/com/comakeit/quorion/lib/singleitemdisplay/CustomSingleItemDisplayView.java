package com.comakeit.quorion.lib.singleitemdisplay;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.List;

/**
 * The type Numer pad.
 */
public class CustomSingleItemDisplayView extends TableLayout {

    /**
     * Instantiates a new Numer pad.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public CustomSingleItemDisplayView(Context context, @Nullable AttributeSet attrs) {
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


        inflater.inflate(R.layout.custom_single_item_diaplay_view, this);
        determineCustomAttributes(attrs);
    }


    private void determineCustomAttributes(AttributeSet attrs ) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomSingleItemDisplayView);
        try {

            if ( typedArray.hasValue(R.styleable.CustomSingleItemDisplayView_sidItemName) ) {
                String itemNameStr = typedArray.getString(R.styleable.CustomSingleItemDisplayView_sidItemName);
                TextView  itemNameView= (TextView) findViewById(R.id.cust_single_item_display_item_name);
                itemNameView.setText(itemNameStr);
            }

            if ( typedArray.hasValue(R.styleable.CustomSingleItemDisplayView_sidItemCount) ) {
                Integer itemcount = typedArray.getInteger(R.styleable.CustomSingleItemDisplayView_sidItemCount,1);
                TextView  itemCountView= (TextView) findViewById(R.id.cust_single_item_display_item_count);
                itemCountView.setText(String.valueOf( itemcount) + " X");
            }
            if ( typedArray.hasValue(R.styleable.CustomSingleItemDisplayView_sidItemAmount) ) {
                Float amount = typedArray.getFloat(R.styleable.CustomSingleItemDisplayView_sidItemAmount, 0.0f);
                TextView  itemAmountView= (TextView) findViewById(R.id.cust_single_item_display_item_amount);
                itemAmountView.setText(String.valueOf(amount));
            }

        } finally {
            typedArray.recycle();
        }

    }




    /**
     * Display dates correctly in grid
     */
    public void updateCustomPictureButtonView(String itemName, Integer itemCount, Double amount ) {
        // update grid
        if (!TextUtils.isEmpty( itemName)) {
            TextView  itemNameView= (TextView) findViewById(R.id.cust_single_item_display_item_name);
            itemNameView.setText(itemName);
        }

        if ( itemCount != null) {
            TextView  itemCountView= (TextView) findViewById(R.id.cust_single_item_display_item_count);
            itemCountView.setText(String.valueOf( itemCount) + " X");
        }
        if ( amount!=null ) {
            TextView  itemAmountView= (TextView) findViewById(R.id.cust_single_item_display_item_amount);
            itemAmountView.setText(String.valueOf(amount));
        }
    }

}