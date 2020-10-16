package com.comakeit.quorion.lib.numberpad;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.comakeit.quorion.lib.util.NumericKeypadType;

/**
 * The type Num key item.
 */
public class NumKeyItem {

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
     * The Text color.
     */
    private String textColor;
    /**
     * The Text.
     */
    private String Text;
    /**
     * The Background image.
     */
    private Drawable backgroundImage;

    /**
     * The Numeric keypad type.
     */
    private NumericKeypadType numericKeypadType;

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
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return Text;
    }

    /**
     * Sets text.
     *
     * @param text the text
     */
    public void setText(String text) {
        Text = text;
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
     * Gets background image.
     *
     * @return the background image
     */
    public Drawable getBackgroundImage() {
        return backgroundImage;
    }

    /**
     * Sets background image.
     *
     * @param backgroundImage the background image
     */
    public void setBackgroundImage(Drawable backgroundImage) {
        this.backgroundImage = backgroundImage;
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
     * Gets numeric keypad type.
     *
     * @return the numeric keypad type
     */
    public NumericKeypadType getNumericKeypadType() {
        return numericKeypadType;
    }

    /**
     * Sets numeric keypad type.
     *
     * @param numericKeypadTypeStr the numeric keypad type str
     */
    public void setNumericKeypadType(String numericKeypadTypeStr) {
        this.numericKeypadType = NumericKeypadType.valueOf( numericKeypadTypeStr);
    }

}
