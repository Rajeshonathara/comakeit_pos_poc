package com.comakeit.quorion.lib.statusbar;

/**
 * The type Num key item.
 */
public class CustomStatusBarItem {


    /**
     * The enum Status type.
     */
    public enum StatusType {
        /**
         * Current time status type.
         */
        CURRENT_TIME,
        /**
         * Current day status type.
         */
        CURRENT_DAY,
        /**
         * Receipt status type.
         */
        RECEIPT,
        /**
         * Clerk id status type.
         */
        CLERK_ID,
        /**
         * Activated function mode status type.
         */
        ACTIVATED_FUNCTION_MODE
    }

    /**
     * The Id.
     */
    private int id;

    /**
     * The Status type.
     */
    private StatusType statusType;

    /**
     * The Id.
     */
    private String identifier;

    /**
     * The Background color.
     */
    private String backgroundColor;

    /**
     * The Text color.
     */
    private String textColor;

    /**
     * The Text.
     */
    private String text;

    /**
     * The Date time patterns.
     */
    private String dateTimePatterns;
    /**
     * The Right align.
     */
    private String textAlign;

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
     * Gets status type.
     *
     * @return the status type
     */
    public StatusType getStatusType() {
        return statusType;
    }

    /**
     * Sets status type.
     *
     * @param statusTypeText the status type text
     */
    public void setStatusType(String statusTypeText) {
        this.statusType = StatusType.valueOf(statusTypeText);
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
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets text.
     *
     * @param text the text
     */
    public void setText(String text) {
        this.text = text;
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
     * Gets date time patterns.
     *
     * @return the date time patterns
     */
    public String getDateTimePatterns() {
        return dateTimePatterns;
    }

    /**
     * Sets date time patterns.
     *
     * @param dateTimePatterns the date time patterns
     */
    public void setDateTimePatterns(String dateTimePatterns) {
        this.dateTimePatterns = dateTimePatterns;
    }


    /**
     * Gets text align.
     *
     * @return the text align
     */
    public String getTextAlign() {
        return textAlign;
    }

    /**
     * Sets text align.
     *
     * @param textAlign the text align
     */
    public void setTextAlign(String textAlign) {
        this.textAlign = textAlign;
    }
}
