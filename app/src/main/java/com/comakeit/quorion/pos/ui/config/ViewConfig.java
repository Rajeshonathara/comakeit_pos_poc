package com.comakeit.quorion.pos.ui.config;

import android.content.res.AssetManager;
import android.text.TextUtils;

import com.comakeit.quorion.lib.picturebutton.CustomPictureButtonItem;
import com.comakeit.quorion.lib.numberpad.NumKeyItem;
import com.comakeit.quorion.lib.statusbar.CustomStatusBarItem;
import com.comakeit.quorion.lib.textbutton.CustomTextButtonItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The type View config.
 */
public class ViewConfig {

    /**
     * Determine num keys list.
     *
     * @return the list
     * @param assets
     */
    public List<NumKeyItem> determineNumKeys(AssetManager assets) {
        List<NumKeyItem> result = new ArrayList<>();
        try {
            int id;
            String text;
            //String string_of_json_array = determineNumKeyJson();
            String string_of_json_array = readRawTextFile(assets,"viewConfig/AppConfig.txt");
            JSONObject obj = new JSONObject(string_of_json_array);

            JSONArray array = obj.getJSONArray("numKeys");
            JSONObject row = null;
            for (int i = 0; i < array.length(); i++) {
                row = array.getJSONObject(i);
                id = row.getInt("id");
                NumKeyItem numKeyItem = new NumKeyItem();
            //    numKeyItem.setId(Integer.valueOf(id));
                numKeyItem.setBackgroundColor(row.getString("backgroundColor"));
                numKeyItem.setTextColor(row.getString("textColor"));
                numKeyItem.setText(row.getString("text"));
                numKeyItem.setIdentifier(row.getString("identifier"));
                result.add(numKeyItem);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }



    public  String readRawTextFile( AssetManager assetManager , String fileName)
    {
    String result ="";

        // To load text file
        InputStream input;
        try {
            input = assetManager.open(fileName);

            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();

            // byte buffer into a string
            String text = new String(buffer);

            result =text;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;

    }

    public List<CustomPictureButtonItem> determineCustomPictureButtonItems(AssetManager assets) {

        List<CustomPictureButtonItem> result = new ArrayList<>();
        try {
            int id;
            String text;
            //String string_of_json_array = determineNumKeyJson();
            String string_of_json_array = readRawTextFile(assets,"viewConfig/AppConfigForPictureButtons.txt");
            JSONObject obj = new JSONObject(string_of_json_array);

            JSONArray array = obj.getJSONArray("pictureButtons");
            JSONObject row = null;
            for (int i = 0; i < array.length(); i++) {
                row = array.getJSONObject(i);
                id = row.getInt("id");
                CustomPictureButtonItem customPictureButtonItem = new CustomPictureButtonItem();
                //    customPictureButtonItem.setId(Integer.valueOf(id));
                customPictureButtonItem.setBackgroundColor(row.getString("backgroundColor"));

                customPictureButtonItem.setIdentifier(row.getString("identifier"));

                customPictureButtonItem.setSrc(row.getString("src"));


                result.add(customPictureButtonItem);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;

    }

    public List<CustomTextButtonItem> determineCustomTextButtonItems(AssetManager assets) {

        List<CustomTextButtonItem> result = new ArrayList<>();
        try {
            int id;
            String text;
            //String string_of_json_array = determineNumKeyJson();
            String string_of_json_array = readRawTextFile(assets,"viewConfig/AppConfigForTextButtons.txt");
            JSONObject obj = new JSONObject(string_of_json_array);

            JSONArray array = obj.getJSONArray("textButtons");
            JSONObject row = null;
            for (int i = 0; i < array.length(); i++) {
                row = array.getJSONObject(i);
                id = row.getInt("id");
                CustomTextButtonItem customTextButtonItem = new CustomTextButtonItem();
                //    customTextButtonItem.setId(Integer.valueOf(id));
                customTextButtonItem.setBackgroundColor(row.getString("backgroundColor"));

                customTextButtonItem.setIdentifier(row.getString("identifier"));

                if(row.has("src")) {
                    customTextButtonItem.setSrc(row.getString("src"));
                }
                customTextButtonItem.setTextColor(row.getString("textColor"));
                customTextButtonItem.setText(row.getString("text"));

                result.add(customTextButtonItem);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;

    }





    public List<CustomStatusBarItem> determineCustomStatusBarItems(AssetManager assets) {

        List<CustomStatusBarItem> result = new ArrayList<>();
        try {
            int id;
            String text;
            //String string_of_json_array = determineNumKeyJson();
            String string_of_json_array = readRawTextFile(assets,"viewConfig/AppConfigForStatusBarView.txt");
            JSONObject obj = new JSONObject(string_of_json_array);

            JSONArray array = obj.getJSONArray("statusBarItems");
            JSONObject row = null;
            for (int i = 0; i < array.length(); i++) {
                row = array.getJSONObject(i);
                id = row.getInt("id");
                CustomStatusBarItem customStatusBarItem = new CustomStatusBarItem();

                customStatusBarItem.setBackgroundColor(row.getString("backgroundColor"));

                customStatusBarItem.setIdentifier(row.getString("identifier"));

                if(row.has("text")) {
                    customStatusBarItem.setText(row.getString("text"));
                }
                customStatusBarItem.setTextColor(row.getString("textColor"));
                customStatusBarItem.setStatusType(row.getString("statusType"));

                if(row.has("dateTimePatterns")) {
                    customStatusBarItem.setDateTimePatterns(row.getString("dateTimePatterns"));
                }
                if(row.has("textAllign")){
                    customStatusBarItem.setTextAlign(row.getString("textAllign"));
                }
                result.add(customStatusBarItem);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;

    }
}
