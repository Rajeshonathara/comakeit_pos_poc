package com.comakeit.quorion.lib.order;

import com.comakeit.quorion.lib.master.Item;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Order {
    private Integer orderNumber=1;
    private Integer customerNumber=1;
    private String  customerName="Test Customer";
    private int currentPositionSerialNumber =0;
    private Map<Integer, OrderListItem> orderListItemMap = new HashMap<>();

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getCurrentPositionSerialNumber() {
        return currentPositionSerialNumber;
    }


    public Map<Integer, OrderListItem> getOrderListItemMap() {
        return orderListItemMap;
    }

    public void setOrderListItemMap(Map<Integer, OrderListItem> orderListItemMap) {
        this.orderListItemMap = orderListItemMap;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void addItem(Item item) {
        addItemIfNotPresent(item);
        OrderListItem orderListItem = this.orderListItemMap.get(item.getId());
        orderListItem.addItem();
        currentPositionSerialNumber = orderListItem.getSerialNumber();
    }

    public void addItem(Item item, int numberOfItems) {
        addItemIfNotPresent(item);
        OrderListItem orderListItem = this.orderListItemMap.get(item.getId());
        orderListItem.addItem(numberOfItems);
        currentPositionSerialNumber = orderListItem.getSerialNumber();
    }

    private void addItemIfNotPresent(Item item) {
        if (!this.orderListItemMap.containsKey(item.getId())) {
            OrderListItem orderListItem = new OrderListItem();
            orderListItem.setSerialNumber(getSerialNumberOfLastElement() + 1);
            orderListItem.setItem(item);
            orderListItem.setCount(0);
            orderListItem.setAmount(item.getPrice());
            this.orderListItemMap.put(item.getId(), orderListItem);
        }
    }


    public void removeItem(Item item) throws Exception {
        OrderListItem orderListItem = this.orderListItemMap.get(item.getId());
        if (orderListItem == null || orderListItem.getCount() < 1) {
            throw new Exception("trying to remove number of items more than present in the order");
        }
        orderListItem.removeItem();

        if (orderListItem.getCount() <= 0) {
            orderListItemMap.remove(item.getId());
        }

        currentPositionSerialNumber = getSerialNumberOfLastElement();
    }

    public void removeItem(Item item, int numberOfItems) throws Exception {

        OrderListItem orderListItem = this.orderListItemMap.get(item.getId());

        if (orderListItem == null || orderListItem.getCount() < numberOfItems) {
            throw new Exception("trying to remove number of items more than present in the order");
        }
        orderListItem.removeItem(numberOfItems);
        if (orderListItem.getCount() <= 0) {
            orderListItemMap.remove(item.getId());
        }

        currentPositionSerialNumber = getSerialNumberOfLastElement();

    }

    public List<OrderListItem> getOrderItemList() {
        List<OrderListItem> orderListItems = new ArrayList<>();
        orderListItems.addAll(orderListItemMap.values());
        orderListItems.sort(Comparator.comparing(OrderListItem::getSerialNumber));
        return orderListItems;
    }

    public Integer getSerialNumberOfLastElement() {
        List<OrderListItem> orderListItems = getOrderItemList();
        int result = 0;
        if (!orderListItems.isEmpty()) {
            OrderListItem orderListItem = orderListItems.get(orderListItems.size() - 1);
            result = orderListItem == null ? 0 : orderListItem.getSerialNumber();
        }
        return result;
    }


    public Double getTotalAmount(){
        return orderListItemMap.values().stream().mapToDouble(o -> o.getAmount().doubleValue()).sum();
    }

    public OrderListItem selectAboveItem() {
        OrderListItem result = null;
        if(!orderListItemMap.isEmpty()) {
            OrderListItem previosPosition = getOrderItemList().get(0);
            for (OrderListItem orderListItem : getOrderItemList()) {
                if(orderListItem.getSerialNumber().equals(currentPositionSerialNumber)){
                    currentPositionSerialNumber =previosPosition.getSerialNumber();
                    result = previosPosition;
                    break;
                }
                previosPosition = orderListItem;
            }
        }
        return result;
    }

    public OrderListItem selectbelowItem() {
        OrderListItem result = null;
        if(!orderListItemMap.isEmpty()) {
            List<OrderListItem> orderItemList = getOrderItemList();

            for (int index=0; index<  orderItemList.size()-1;index++) {
                OrderListItem orderListItem = orderItemList.get(index);
                if(orderListItem.getSerialNumber().equals(currentPositionSerialNumber)){
                    if (index< orderItemList.size()-1) {
                        OrderListItem nextPosition = orderItemList.get(index+1);
                        currentPositionSerialNumber = nextPosition.getSerialNumber();
                        result = nextPosition;
                        break;
                    }
                }
            }
        }
        return result;
    }

    public OrderListItem deleteCurrentSelectedItem() throws Exception {
        OrderListItem orderListItem = getCurrentSelectedItem();
        if(orderListItem != null){
            removeItem(orderListItem.getItem(),orderListItem.getCount());
        }
        return getCurrentSelectedItem();
    }

    private OrderListItem getCurrentSelectedItem() {
        return orderListItemMap.values().stream().filter(o -> o.getSerialNumber().equals(currentPositionSerialNumber)).findFirst().orElse(null);
    }
}
