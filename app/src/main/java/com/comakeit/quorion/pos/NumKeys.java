package com.comakeit.quorion.pos;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.GridView;


import androidx.annotation.Nullable;

public class NumKeys extends LinearLayout
{
    // internal components

    private GridView grid;

    public NumKeys(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initControl(context);
    }

    /**
     * Load component XML layout
     */
    private void initControl(Context context)
    {



        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.number_keys_component_1, this);
        grid = (GridView)findViewById(R.id.comp_num_pad_grid);


        
    }
}
