package com.comakeit.quorion.lib.itemdisplayuint;

/**
 * The type Num key item.
 */
public class CustomItemDisplayUnitItem {


    /**
     * The enum Item text type.
     */
    public enum ItemTextType {

        /**
         * Count item text type.
         */
        COUNT,

        /**
         * Name item text type.
         */
        NAME,

        /**
         * Amount item text type.
         */
        AMOUNT
    }

    /**
     * The Id.
     */
    private int id;

    /**
     * The Id.
     */
    private String identifier;

    /**
     * The Status type.
     */
    private ItemTextType itemTextType;

    /**
     * The Background color.
     */
    private String backgroundColor;

    /**
     * The Background color.
     */
    private String textColor;

    /**
     * The Item count.
     */
    private Integer itemId;

    /**
     * The Item count.
     */
    private Integer itemCount;
    /**
     * The Item name.
     */
    private String itemName;
    /**
     * The Item amount.
     */
    private Double itemAmount;

    /**
     * The Is selected item.
     */
    private boolean isSelectedItem= false;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets item text type.
     *
     * @return the item text type
     */
    public ItemTextType getItemTextType() {
        return this.itemTextType;
    }

    /**
     * Sets item text type.
     *
     * @param itemTextTypeString the item text type
     */
    public void setItemTextType(String itemTextTypeString) {
        this.itemTextType = ItemTextType.valueOf(itemTextTypeString);
    }

    /**
     * Gets background color.
     *
     * @return the background color
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Sets background color.
     *
     * @param backgroundColor the background color
     */
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Gets text color.
     *
     * @return the text color
     */
    public String getTextColor() {
        return textColor;
    }

    /**
     * Sets text color.
     *
     * @param textColor the text color
     */
    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    /**
     * Gets identifier.
     *
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets identifier.
     *
     * @param identifier the identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Gets item count.
     *
     * @return the item count
     */
    public Integer getItemCount() {
        return itemCount;
    }

    /**
     * Sets item count.
     *
     * @param itemCount the item count
     */
    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    /**
     * Gets item name.
     *
     * @return the item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets item name.
     *
     * @param itemName the item name
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Gets item amount.
     *
     * @return the item amount
     */
    public Double getItemAmount() {
        return itemAmount;
    }

    /**
     * Sets item amount.
     *
     * @param itemAmount the item amount
     */
    public void setItemAmount(Double itemAmount) {
        this.itemAmount = itemAmount;
    }

    /**
     * Is selected item boolean.
     *
     * @return the boolean
     */
    public boolean isSelectedItem() {
        return isSelectedItem;
    }

    /**
     * Sets selected item.
     *
     * @param selectedItem the selected item
     */
    public void setSelectedItem(boolean selectedItem) {
        isSelectedItem = selectedItem;
    }


    /**
     * Gets item id.
     *
     * @return the item id
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * Sets item id.
     *
     * @param itemId the item id
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

}
