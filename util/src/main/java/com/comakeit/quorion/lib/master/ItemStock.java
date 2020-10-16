package com.comakeit.quorion.lib.master;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Item stock.
 */
public class ItemStock {

    /**
     * The Current stock.
     */
    private Map<Integer,Integer> currentStock = new HashMap<>();
    /**
     * The Items.
     */
    private Map<Integer, Item> Items = new HashMap<>();

    /**
     * Gets current stock.
     *
     * @return the current stock
     */
    public Map<Integer, Integer> getCurrentStock() {
        return currentStock;
    }

    /**
     * Sets current stock.
     *
     * @param currentStock the current stock
     */
    public void setCurrentStock(Map<Integer, Integer> currentStock) {
        this.currentStock = currentStock;
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public Map<Integer, Item> getItems() {
        return Items;
    }

    /**
     * Sets items.
     *
     * @param items the items
     */
    public void setItems(Map<Integer, Item> items) {
        Items = items;
    }


    /**
     * Add item to stock.
     *
     * @param item          the item
     * @param numberOfItems the number of items
     */
    public void addItemToStock(@NonNull  Item item,@NonNull Integer numberOfItems){
        Integer itemId =item.getId();
        if(!currentStock.containsKey(itemId)){
            Items.put(itemId,item);
            currentStock.put(itemId,0);
        }
        currentStock.put(itemId,currentStock.get(itemId) + numberOfItems);
    }

    /**
     * Remove item to stock.
     *
     * @param itemId        the item id
     * @param numberOfItems the number of items
     * @throws Exception the exception
     */
    public void removeItemFromStock(Integer itemId, Integer numberOfItems) throws Exception {

        if(!currentStock.containsKey(itemId)){
           throw new Exception("item not present with give item id "+ itemId);
        }
        Integer currentItemCount = currentStock.get(itemId);
        if( numberOfItems> currentItemCount){
            throw new Exception("Required item not present in stock ");
        }
        currentStock.put(itemId,currentItemCount - numberOfItems);
    }

    /**
     * Remove one item to stock.
     *
     * @param itemId the item id
     * @throws Exception the exception
     */
    public void removeOneItemFromStock(Integer itemId) throws Exception {

        if(!currentStock.containsKey(itemId)){
            throw new Exception("item not present with give item id "+ itemId);
        }
        Integer currentItemCount = currentStock.get(itemId);
        if( currentItemCount == 0){
            throw new Exception("Required item not present in stock ");
        }
        currentStock.put(itemId,currentItemCount - 1);
    }

    /**
     * Add item.
     *
     * @param item the item
     */
    public void addItem(@NonNull Item item){
        Integer itemId =item.getId();
        if(!currentStock.containsKey(itemId)){
            Items.put(itemId,item);
            currentStock.put(itemId,0);
        }
    }

}
