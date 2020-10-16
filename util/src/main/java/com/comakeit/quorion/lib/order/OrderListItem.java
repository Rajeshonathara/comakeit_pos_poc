package com.comakeit.quorion.lib.order;

import com.comakeit.quorion.lib.master.Item;

public class OrderListItem  {
    private Integer serialNumber;
    private Item item;
    private Integer count;
    private Double amount;

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        updateAmount(amount);
    }
    public void addItem(){
        this.count ++;
        updateAmount(item.getPrice() * this.count);
    }

    public void addItem(int numberOfItems){
        this.count  += numberOfItems;
        updateAmount(item.getPrice() * this.count);
    }

    private void updateAmount(double v) {
        this.amount = v;
    }

    public void removeItem(int numberOfItems) {
        this.count  -= numberOfItems;
        updateAmount(item.getPrice() * this.count);
    }

    public void removeItem() {
        this.count --;
        updateAmount(item.getPrice() * this.count);
    }
}
