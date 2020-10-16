package com.comakeit.quorion.lib.itemdisplayuint;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.comakeit.quorion.lib.master.Item;
import com.comakeit.quorion.lib.order.Order;
import com.comakeit.quorion.lib.order.OrderListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Numer pad.
 */
public class CustomItemDisplayUnitView extends LinearLayout  {

    /**
     * The Grid.
     */
    private GridView displayGrid;

    /**
     * The Grid.
     */
    private GridView headerGrid;

    /**
     * The Grid.
     */
    private GridView footerGrid;

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


    private Integer itemCountTextWidth;
    private Integer itemNameTextWidth;
    private Integer itemAmountTextWidth;
    private Integer selectionBackGroundColor;
    private Integer selectionTextColor;
    private Integer displayHeaderHeight = 80;
    private Integer displayFooterHeight=50;
    private Integer displayBodyHeight=300;
    private Integer headerBackgroundColor;
    private Integer headerTextColor;

    /**
     * Instantiates a new Numer pad.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public CustomItemDisplayUnitView(Context context, @Nullable AttributeSet attrs) {
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

        inflater.inflate(R.layout.custom_item_display_unit_view, this);

        displayGrid = (GridView) findViewById(R.id.cust_item_diaplay_unit_gridView);

        headerGrid = (GridView) findViewById(R.id.cust_item_diaplay_header_gridView);
        footerGrid = (GridView) findViewById(R.id.cust_item_diaplay_footer_gridView);

        determineCustomAttributes(attrs);


        ViewGroup.LayoutParams layoutParams = displayGrid.getLayoutParams();
        layoutParams.height =  displayBodyHeight; //this is in pixels
        displayGrid.setLayoutParams(layoutParams);


        layoutParams = headerGrid.getLayoutParams();
        layoutParams.height =  displayHeaderHeight; //this is in pixels
        headerGrid.setLayoutParams(layoutParams);

       layoutParams = footerGrid.getLayoutParams();
        layoutParams.height =  displayFooterHeight; //this is in pixels
        footerGrid.setLayoutParams(layoutParams);


        if(headerBackgroundColor!=null){
            headerGrid.setBackgroundColor(headerBackgroundColor);
            footerGrid.setBackgroundColor(headerBackgroundColor);
        }

        displayGrid.setPadding(0, 10, 0, 10);
    }

    /**
     * Determine custom attributes.
     *
     * @param attrs the attrs
     */
    private void determineCustomAttributes(AttributeSet attrs ) {
        Integer width = displayGrid.getWidth();
        Integer custNumColumns = 1;
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomItemDisplayUnitView);
        try {

            if ( typedArray.hasValue(R.styleable.CustomItemDisplayUnitView_iduColumnHeight) ) {

                custColumnHeight = Integer.valueOf(typedArray.getDimensionPixelSize(R.styleable.CustomItemDisplayUnitView_iduColumnHeight, 10));
            }

            if ( typedArray.hasValue(R.styleable.CustomItemDisplayUnitView_iduTextSize) ) {

                custTextSize = Integer.valueOf(typedArray.getDimensionPixelSize(R.styleable.CustomItemDisplayUnitView_iduTextSize, 12));
            }

            if ( typedArray.hasValue(R.styleable.CustomItemDisplayUnitView_iduItemCountTextWidth) ) {

                itemCountTextWidth = Integer.valueOf(typedArray.getDimensionPixelSize(R.styleable.CustomItemDisplayUnitView_iduItemCountTextWidth, 20));
            }

            if ( typedArray.hasValue(R.styleable.CustomItemDisplayUnitView_iduItemNameTextWidth) ) {

                itemNameTextWidth = Integer.valueOf(typedArray.getDimensionPixelSize(R.styleable.CustomItemDisplayUnitView_iduItemNameTextWidth, 200));
            }

            if ( typedArray.hasValue(R.styleable.CustomItemDisplayUnitView_iduItemAmountTextWidth) ) {

                itemAmountTextWidth = Integer.valueOf(typedArray.getDimensionPixelSize(R.styleable.CustomItemDisplayUnitView_iduItemAmountTextWidth, 10));
            }


            if ( typedArray.hasValue(R.styleable.CustomItemDisplayUnitView_iduSelectionBackGroundColor) ) {

                selectionBackGroundColor = typedArray.getColor(R.styleable.CustomItemDisplayUnitView_iduSelectionBackGroundColor,0);
            }
            if ( typedArray.hasValue(R.styleable.CustomItemDisplayUnitView_iduSelectionTextColor) ) {

                selectionTextColor = typedArray.getColor(R.styleable.CustomItemDisplayUnitView_iduSelectionTextColor, 0);
            }

            if ( typedArray.hasValue(R.styleable.CustomItemDisplayUnitView_iduHeaderHeight) ) {

                displayHeaderHeight = Integer.valueOf(typedArray.getDimensionPixelSize(R.styleable.CustomItemDisplayUnitView_iduHeaderHeight, 10));
            }
            if ( typedArray.hasValue(R.styleable.CustomItemDisplayUnitView_iduFooterHeight) ) {

                displayFooterHeight = Integer.valueOf(typedArray.getDimensionPixelSize(R.styleable.CustomItemDisplayUnitView_iduFooterHeight, 10));
            }
            if ( typedArray.hasValue(R.styleable.CustomItemDisplayUnitView_iduBodyHeight) ) {

                displayBodyHeight = Integer.valueOf(typedArray.getDimensionPixelSize(R.styleable.CustomItemDisplayUnitView_iduBodyHeight, 10));
            }


            if ( typedArray.hasValue(R.styleable.CustomItemDisplayUnitView_iduHeaderBackgroundColor) ) {

                headerBackgroundColor = typedArray.getColor(R.styleable.CustomItemDisplayUnitView_iduHeaderBackgroundColor, 0);
            }

            if ( typedArray.hasValue(R.styleable.CustomItemDisplayUnitView_iduHeaderTextColor) ) {

                headerTextColor = typedArray.getColor(R.styleable.CustomItemDisplayUnitView_iduHeaderTextColor, 0);
            }

        } finally {
            typedArray.recycle();
        }
        displayGrid.setNumColumns(custNumColumns);

    }


    private void assignClickHandlers() {

        displayGrid.setOnItemClickListener(new CustomItemDisplayUnitItemClickListener());

        // long-pressing a day
        displayGrid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> view, View cell, int position, long id) {
                // handle long-press
                if (eventHandler == null)
                    return false;

                eventHandler.onCustomItemDisplayUnitPress((CustomItemDisplayUnitItem) view.getItemAtPosition(position));
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
        ((CustomItemDisplayUnitAdapter) displayGrid.getAdapter()).setEventHandler(eventHandler);
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
        void onCustomItemDisplayUnitPress(CustomItemDisplayUnitItem numKeyItem);

        void onCustomItemDisplayUnitPressItem(String identifier);
    }


    /**
     * Display dates correctly in grid
     */
    public void updateCustomItemDisplayUnitView(Order order, String parentPackageName) {
        // update grid
        displayGrid.setAdapter(new CustomItemDisplayUnitAdapter(getContext(), determineItemDisplayUnitItems(order), getResources(), parentPackageName,  custTextSize,itemCountTextWidth,
          itemNameTextWidth,  itemAmountTextWidth, selectionBackGroundColor, selectionTextColor

                ));
        assignClickHandlers();


        List<String> headerList = new ArrayList<>();
        headerList.add("Customer Number :");
        headerList.add(String.valueOf( order.getCustomerNumber()));
        headerList.add(order.getCustomerName());
        headerList.add(" ");
        headerGrid.setAdapter(new DisplayUnitHeaderFooterAdapter(getContext(), headerList, getResources(), parentPackageName) );

        List<String> footerList= new ArrayList<>();
        footerList.add("Total");
        footerList.add("0.00");
        footerGrid.setAdapter(new DisplayUnitHeaderFooterAdapter(getContext(), footerList, getResources(), parentPackageName) );

    }


    /**
     * The type Custom picture button item click listener.
     */
    public class CustomItemDisplayUnitItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // handle long-press
            if (eventHandler == null) {
                eventHandler.onCustomItemDisplayUnitPress((CustomItemDisplayUnitItem) parent.getItemAtPosition(position));
            }
        }

    }

    public void refreshView( List<CustomItemDisplayUnitItem> events, Double total){
        ((CustomItemDisplayUnitAdapter) displayGrid.getAdapter()).updateViewItems(events);
        displayGrid.invalidate();

        List<String> footerList= new ArrayList<>();
        footerList.add("Total");
        footerList.add(String.valueOf(total));
        ((DisplayUnitHeaderFooterAdapter)footerGrid.getAdapter()).updateViewItems(footerList);
        footerGrid.invalidate();
    }



    public void refreshView(Order order) {
        ((CustomItemDisplayUnitAdapter) displayGrid.getAdapter()).updateViewItems(determineItemDisplayUnitItems(order));
        displayGrid.invalidate();

        List<String> footerList= new ArrayList<>();
        footerList.add("Total");
        footerList.add(String.valueOf(order.getTotalAmount()));
        ((DisplayUnitHeaderFooterAdapter)footerGrid.getAdapter()).updateViewItems(footerList);
        footerGrid.invalidate();
    }

    public List<CustomItemDisplayUnitItem> determineItemDisplayUnitItems( Order order) {
        List<CustomItemDisplayUnitItem> customItemDisplayUnitItems = new ArrayList<>();
        for (OrderListItem orderListItem : order.getOrderItemList()) {
            CustomItemDisplayUnitItem customItemDisplayUnitItem = new CustomItemDisplayUnitItem();
            Item item = orderListItem.getItem();
            customItemDisplayUnitItem.setItemId(item.getId());
            customItemDisplayUnitItem.setItemName(item.getName());
            customItemDisplayUnitItem.setItemCount(orderListItem.getCount());
            customItemDisplayUnitItem.setItemAmount(orderListItem.getAmount());
            customItemDisplayUnitItem.setSelectedItem(orderListItem.getSerialNumber().equals(order.getCurrentPositionSerialNumber()));
            customItemDisplayUnitItems.add(customItemDisplayUnitItem);
        }

        return customItemDisplayUnitItems;
    }

    public class DisplayUnitHeaderFooterAdapter extends ArrayAdapter<String>{


        Context context;

        /**
         * The Inflater.
         */
        private LayoutInflater inflater;

        /**
         * The Resources.
         */
        private Resources resources;
        public DisplayUnitHeaderFooterAdapter(@NonNull Context context, List<String> textValues, Resources resource , String parentPackageName) {
            super(context, R.layout.custom_item_text, textValues);
            this.context=context;
            this.inflater = LayoutInflater.from(context);
        }

        public View getView(int position, View view, ViewGroup parent) {

            String value  = getItem(position);
            if (view == null) {
                view = inflater.inflate(R.layout.custom_item_text, parent, false);

            }
            TextView  itemCountTextView = (TextView) view;

            itemCountTextView.setText(value);

            if (custTextSize != null) {
                itemCountTextView.setTextSize(custTextSize);
            }
            if(headerTextColor!=null){
                itemCountTextView.setTextColor(headerTextColor);
            }

            if(position % 2 ==1){
                itemCountTextView.setGravity(Gravity.RIGHT);
            }

            return view;
        }

        public void updateViewItems( List<String> values){
            clear();
            addAll(values);
            notifyDataSetChanged();
        }

    }




}