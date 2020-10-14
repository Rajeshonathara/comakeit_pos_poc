package com.comakeit.quorion.lib.picturebutton;

/**
 * The type Num key item.
 */
public class CustomPictureButtonItem {

    /**
     * The Id.
     */
    private int id;


    /**
     * The Id.
     */
    private String identifier;


    /**
     * The Background color.
     */
    private String backgroundColor;




    /**
     * The Background image.
     */
    private String src;

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
     * Gets source .
     *
     * @return the scr the source
     */
    public String getSrc() {
        return src;
    }

    /**
     * Sets background image.
     *
     * @param src the source
     */
    public void setSrc(String src) {
        this.src = src;
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
}
