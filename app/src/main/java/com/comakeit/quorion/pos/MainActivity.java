package com.comakeit.quorion.pos;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.comakeit.quorion.lib.itemdisplayuint.CustomItemDisplayUnitItem;
import com.comakeit.quorion.lib.itemdisplayuint.CustomItemDisplayUnitView;
import com.comakeit.quorion.lib.numberpad.NumericKeypad;
import com.comakeit.quorion.lib.master.Item;
import com.comakeit.quorion.lib.order.OrderListItem;
import com.comakeit.quorion.lib.picturebutton.CustomPictureButtonItem;
import com.comakeit.quorion.lib.picturebutton.CustomPictureButtonView;
import com.comakeit.quorion.lib.numberpad.NumKeyItem;
import com.comakeit.quorion.lib.singleitemdisplay.CustomSingleItemDisplayView;
import com.comakeit.quorion.lib.statusbar.CustomStatusBarItem;
import com.comakeit.quorion.lib.statusbar.CustomStatusBarView;
import com.comakeit.quorion.lib.textbutton.CustomTextButtonItem;
import com.comakeit.quorion.lib.textbutton.CustomTextButtonView;
import com.comakeit.quorion.lib.master.ItemStock;
import com.comakeit.quorion.lib.order.Order;
import com.comakeit.quorion.lib.util.NumericKeypadType;
import com.comakeit.quorion.pos.ui.config.ViewConfig;

import java.util.List;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {

    ItemStock itemStock = new ItemStock();

    Order order = new Order();

    CustomItemDisplayUnitView itemDisplayUnitView;

    CustomSingleItemDisplayView singleItemDisplayView;


    /**
     * The Tag.
     */
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fillStockInfo();
        createOrder();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateNumberPad();
        updateCustomPictureButtons();
        updateCustomTextButtons();
        updateCustomStatusBars();
        updateItemDisplayUnitView();
        updateSingleItemView();


    }


    /**
     * Update number pad.
     */
    private void updateNumberPad() {
        ViewConfig viewConfig = new ViewConfig();
        List<NumKeyItem> numKeyItems = viewConfig.determineNumKeys(getAssets());
        NumericKeypad numericKeypad = ((NumericKeypad) findViewById(R.id.numPad));
        numericKeypad.updateNumberPad(numKeyItems, getPackageName());
        // assign event handler
        numericKeypad.setEventHandler(new NumericKeypad.EventHandler() {
            @Override
            public void onNumKeyPress(NumKeyItem numKeyItem) {
               //
            }

            @Override
            public void onNumKeyPressItem(String identifier) {
                //
                try {
                    NumericKeypadType numericKeypadType = NumericKeypadType.valueOf(identifier);
                    OrderListItem orderListItem = null;
                    switch (numericKeypadType) {
                        case UP_ARROW:

                            orderListItem = order.selectAboveItem();
                            break;

                        case DOWN_ARROW:
                            orderListItem = order.selectbelowItem();
                            break;
                        case CLEAR:
                            orderListItem = order.deleteCurrentSelectedItem();
                            break;
                        case CROSS:
                            break;
                        case DOT:
                            break;
                        default:
                            break;
                    }
                    refreshDisplayUnit();
                    refreshSingleItemDisplayUnit(orderListItem !=null?orderListItem.getItem():null, orderListItem !=null?orderListItem.getCount():null);


                } catch (Exception exception) {
                    // TODO handle exception here

                }
            }
        });
    }


    /**
     * Update custom picture buttons.
     */
    private void updateCustomPictureButtons() {
        ViewConfig viewConfig = new ViewConfig();
        List<CustomPictureButtonItem> customPictureButtonItems = viewConfig.determineCustomPictureButtonItems(getAssets());

        CustomPictureButtonView pictureButtonView = ((CustomPictureButtonView) findViewById(R.id.cust_picture_view));
        pictureButtonView.updateCustomPictureButtonView(customPictureButtonItems, getPackageName());
        // assign event handler
        pictureButtonView.setEventHandler(new CustomPictureButtonView.EventHandler() {


            @Override
            public void onCustomPictureButtonPress(CustomPictureButtonItem customPictureButtonItem) {
               //
            }

            @Override
            public void onCustomPictureButtonPressItem(String identifier) {

            }
        });
    }


    private void updateCustomTextButtons() {
        ViewConfig viewConfig = new ViewConfig();
        List<CustomTextButtonItem> customTextButtonItems = viewConfig.determineCustomTextButtonItems(getAssets());

        CustomTextButtonView textButtonView = ((CustomTextButtonView) findViewById(R.id.cust_text_view));
        textButtonView.updateCustomTextButtonView(customTextButtonItems, getPackageName());

        // assign event handler
        textButtonView.setEventHandler(new CustomTextButtonView.EventHandler() {


            @Override
            public void onCustomTextButtonPress(CustomTextButtonItem customTextButtonItem) {

            }

            @Override
            public void onCustomTextButtonPressItem(String identifier) {
                Item item = itemStock.getItems().get(Integer.valueOf(identifier));
                order.addItem(item);

                refreshDisplayUnit();
                Integer numberOfItems = order.getOrderListItemMap().get(item.getId()).getCount();
                refreshSingleItemDisplayUnit(item, numberOfItems);

            }
        });
    }

    private void refreshSingleItemDisplayUnit(Item item, Integer numberOfItems) {

        singleItemDisplayView.refreshView(item,numberOfItems);
    }

    private void refreshDisplayUnit() {
        itemDisplayUnitView.refreshView( order);


    }

    private void updateCustomStatusBars() {
        ViewConfig viewConfig = new ViewConfig();
        List<CustomStatusBarItem> customStatusBarItems = viewConfig.determineCustomStatusBarItems(getAssets());

        CustomStatusBarView textButtonView = ((CustomStatusBarView) findViewById(R.id.cust_status_bar_view));
        textButtonView.updateCustomStatusBarView(customStatusBarItems, getPackageName());

    }

    private void updateSingleItemView() {

       singleItemDisplayView = ((CustomSingleItemDisplayView) findViewById(R.id.cust_single_item_view));


    }

    private void updateItemDisplayUnitView() {
        ViewConfig viewConfig = new ViewConfig();
        itemDisplayUnitView = ((CustomItemDisplayUnitView) findViewById(R.id.itemDisplay));
        itemDisplayUnitView.updateCustomItemDisplayUnitView(order, getPackageName());

        // assign event handler
        itemDisplayUnitView.setEventHandler(new CustomItemDisplayUnitView.EventHandler() {


            @Override
            public void onCustomItemDisplayUnitPress(CustomItemDisplayUnitItem customItemDisplayUnitItem) {
               //
            }

            @Override
            public void onCustomItemDisplayUnitPressItem(String identifier) {
               //
            }
        });
    }


    private void createOrder() {
        order.setOrderNumber(1);
        order.setCustomerNumber(2);
    }

    private void fillStockInfo() {
        ViewConfig viewConfig = new ViewConfig();
        viewConfig.fillItemStock(itemStock, getAssets());
    }
}