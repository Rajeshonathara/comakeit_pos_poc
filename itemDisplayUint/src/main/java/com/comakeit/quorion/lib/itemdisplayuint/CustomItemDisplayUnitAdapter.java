package com.comakeit.quorion.lib.itemdisplayuint;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.HashSet;
import java.util.List;
import com.comakeit.quorion.lib.itemdisplayuint.CustomItemDisplayUnitItem.ItemTextType;
/**
 * The type Numpad adapter.
 */
public class CustomItemDisplayUnitAdapter extends ArrayAdapter<CustomItemDisplayUnitItem> {

    /**
     * The Event handler.
     */
    private CustomItemDisplayUnitView.EventHandler eventHandler;

    Context context;

    private String parentPackageName;
    private Integer custTextSize;
    private Integer itemCountTextWidth;
    private Integer itemNameTextWidth;
    private Integer itemAmountTextWidth;
    private Integer selectionBackGroundColor;
    private Integer selectionTextColor;

    /**
     * The Num key items.
     */
    private HashSet<CustomItemDisplayUnitItem> CustomItemDisplayUnitItems;

    /**
     * The Inflater.
     */
    private LayoutInflater inflater;

    /**
     * The Resources.
     */
    private Resources resources;


    /**
     * Instantiates a new Custom item display unit adapter.
     *  @param context             the context
     * @param numKeys             the num keys
     * @param resources           the resources
     * @param parentPackageName   the parent package name
     * @param custTextSize        the cust text size
     * @param itemCountTextWidth  the item count text width
     * @param itemNameTextWidth   the item name text width
     * @param itemAmountTextWidth the item amount text width
     */
    public CustomItemDisplayUnitAdapter(Context context, List<CustomItemDisplayUnitItem> numKeys, Resources resources, String parentPackageName,
                                         Integer custTextSize, Integer itemCountTextWidth,
                                        Integer itemNameTextWidth, Integer itemAmountTextWidth,
                                                Integer selectionBackGroundColor, Integer selectionTextColor) {
        super(context, R.layout.custom_item_row, numKeys);
        this.context=context;
        this.inflater = LayoutInflater.from(context);
        this.resources = resources;
        this.parentPackageName = parentPackageName;
        this.custTextSize = custTextSize;
        this.itemCountTextWidth = itemCountTextWidth;
        this.itemNameTextWidth = itemNameTextWidth;
        this.itemAmountTextWidth = itemAmountTextWidth;
        this.selectionBackGroundColor =selectionBackGroundColor;
        this.selectionTextColor=selectionTextColor;

    }





    public View getView(int position, View convertView, ViewGroup parent) {

        CustomItemDisplayUnitItem customItemDisplayUnitItem = getItem(position);

        View gridView;

        if (convertView == null) {
            gridView = inflater.inflate(R.layout.custom_item_row, parent, false);
        } else {
            gridView = (View) convertView;
        }

        int totalWidth = parent.getWidth();
        TextView itemCountTextView = (TextView) gridView.findViewById(R.id.custom_item_count);
        itemCountTextView.setText(String.valueOf(customItemDisplayUnitItem.getItemCount()));
        itemCountTextView.setWidth(Integer.valueOf(totalWidth * (15 / 100)));
        itemCountTextView.setGravity(Gravity.LEFT);
        if (itemCountTextWidth != null) {
            itemCountTextView.setWidth(itemCountTextWidth);
        }
        setTextAreaViewAttributes(convertView, itemCountTextView, customItemDisplayUnitItem);

        TextView itemNameTextView = (TextView) gridView.findViewById(R.id.custom_item_name);
        itemNameTextView.setText(customItemDisplayUnitItem.getItemName());
        itemNameTextView.setWidth(Integer.valueOf(totalWidth * (70 / 100)));
        itemNameTextView.setGravity(Gravity.LEFT);
        if (itemNameTextWidth != null) {
            itemNameTextView.setWidth(itemNameTextWidth);
        }
        setTextAreaViewAttributes(convertView, itemNameTextView, customItemDisplayUnitItem);

        TextView itemAmountTextView = (TextView) gridView.findViewById(R.id.custom_item_amount);
        itemAmountTextView.setText(customItemDisplayUnitItem.getItemAmount().toString() + "    ");
        itemAmountTextView.setWidth(Integer.valueOf(totalWidth * (15 / 100)));
        itemAmountTextView.setGravity(Gravity.RIGHT);
        if (itemAmountTextWidth != null) {
            itemAmountTextView.setWidth(itemAmountTextWidth);
        }
        setTextAreaViewAttributes(convertView, itemAmountTextView, customItemDisplayUnitItem);


        return gridView;
    }

    private void setTextAreaViewAttributes(  View convertView, TextView textView, CustomItemDisplayUnitItem customItemDisplayUnitItem) {
        textView.setTag(textView.getId(), customItemDisplayUnitItem.getIdentifier());
        if (!TextUtils.isEmpty(customItemDisplayUnitItem.getBackgroundColor())) {
            textView.setBackgroundColor(getColor(customItemDisplayUnitItem.getBackgroundColor()));
        }

        if (custTextSize != null) {
            textView.setTextSize(custTextSize);
        }
        if(customItemDisplayUnitItem.isSelectedItem()){
            if(selectionBackGroundColor!=null){
                textView.setBackgroundColor(selectionBackGroundColor);
            }
            if(selectionTextColor!=null){
                textView.setTextColor(selectionTextColor);
            }
        } else {

            if(selectionBackGroundColor!=null){
                textView.setTextColor(selectionBackGroundColor);
            }
            if(selectionTextColor!=null){
                textView.setBackgroundColor(selectionTextColor);
            }

        }
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

    public class CustomItemDisplayUnitItemOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (eventHandler != null) {

                eventHandler.onCustomItemDisplayUnitPressItem((String) v.getTag(v.getId()));
            }
        }
    }

    public void setEventHandler(CustomItemDisplayUnitView.EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }






    // @Override
    public View getView1(int position, View view, ViewGroup parent) {
        CustomItemDisplayUnitItem CustomItemDisplayUnitItem = getItem(position);

        // inflate item if it does not exist yet
        if (view == null) {
            view = inflater.inflate(R.layout.custom_item_text, parent, false);
            TextView textView = (TextView) view;
            textView.setOnClickListener(new CustomItemDisplayUnitItemOnClickListener());
            textView.setTag(textView.getId(), CustomItemDisplayUnitItem.getIdentifier());
            if (!TextUtils.isEmpty(CustomItemDisplayUnitItem.getBackgroundColor())) {
                textView.setBackgroundColor(getColor(CustomItemDisplayUnitItem.getBackgroundColor()));
            }

            if (ItemTextType.COUNT.equals(CustomItemDisplayUnitItem.getItemTextType())) {
                textView.setGravity(Gravity.LEFT);
                if (itemCountTextWidth != null) {
                    textView.setWidth(itemCountTextWidth);
                }
            } else if (ItemTextType.NAME.equals(CustomItemDisplayUnitItem.getItemTextType())) {
                textView.setGravity(Gravity.LEFT);
                if (itemNameTextWidth != null) {
                    textView.setWidth(itemNameTextWidth);
                }
            } else if (ItemTextType.AMOUNT.equals(CustomItemDisplayUnitItem.getItemTextType())) {
                textView.setGravity(Gravity.RIGHT);
                if (itemAmountTextWidth != null) {
                    textView.setWidth(itemAmountTextWidth);
                }
            }

            if (custTextSize != null) {
                textView.setTextSize(custTextSize);
            }
        }


        return view;
    }

    public void updateViewItems( List<CustomItemDisplayUnitItem> events){
      clear();
        addAll(events);
        notifyDataSetChanged();
    }



}