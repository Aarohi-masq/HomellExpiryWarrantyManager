package com.homell.Homell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.homell.Homell.R;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public  SliderAdapter(Context context){
        this.context = context;
    }

    // Arrays
     int[] images = {
            R.drawable.onboard1,
            R.drawable.onboard2,
            R.drawable.onboard3
    };
    int headings [] = {
            R.string.obh1,
            R.string.obh2,
            R.string.obh3
    };
    int descriptions [] = {
            R.string.obd1,
            R.string.obd2,
            R.string.obd3
    };







    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

      layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
      View view    = layoutInflater.inflate(R.layout.slides_layout, container,  false);


        TextView slideheadView = (TextView) view.findViewById(R.id.slider_heading);
        TextView slidedescView = (TextView) view.findViewById(R.id.slider_desc);

      ImageView slideImageView = (ImageView) view.findViewById(R.id.slider_image);

      slideImageView.setImageResource(images[position]);
      slidedescView.setText(descriptions[position]);
      slideheadView.setText(headings[position]);
      container.addView(view);
              return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }
}

