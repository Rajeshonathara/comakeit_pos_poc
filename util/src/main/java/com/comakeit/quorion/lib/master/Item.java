package com.comakeit.quorion.lib.master;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * The type Item.
 */
public class Item {
    /**
     * The Id.
     */
    private Integer id;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Amount.
     */
    private double price;

    /**
     * Instantiates a new Item.
     */
    public Item() {
        super();
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj  instanceof Item)){
            return false;
        }
        if(this==obj)return true;
        if(obj==null||getClass()!=obj.getClass())return false;

        Item that=(Item)obj;

        if(Integer.compare(that.getId(),this.id)!=0)return false;

        return true;
    }



    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets amount.
     *
     * @param price the amount
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
