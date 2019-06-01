package com.payment.util.adapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.payment.R;

public class BindingAdapters{

    @BindingAdapter("setImage")
    public static void setImage(ImageView imageview, boolean buttonState){
        imageview.setImageResource(R.drawable.dot2_24dp);
        if (buttonState){
            imageview.setImageResource(R.drawable.dot1_24dp);
        }else {
            imageview.setImageResource(R.drawable.dot2_24dp);
        }
    }
}
