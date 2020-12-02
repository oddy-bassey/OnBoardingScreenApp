package com.example.onboardingscreenapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    //Declaring a view context
    private Context context;

    //Declaring a layout inflator
    private LayoutInflater layoutInflater;

    //Declaring an Integer array containing the slide image icons
    public int[] slideImages = {R.drawable.eat_icon, R.drawable.sleep_icon, R.drawable.code_icon};

    //Declaring a String array containing the header names
    public String[] slideHeadings = {"EAT", "SLEEP", "CODE"};

    //Declaring a String array containing the heading descriptions
    public String[] slideDescriptions = {
            "In order to be ready for the task in front, one has to be deligent and steadfast in hardwork and faithfully forever in God...Hopefully things will turn out as hoped.",
            "Next year January by Gods grace, i would have gotten a job offer by november, for which i would process my visa documents and be ready to leave nigeria to netherlands.",
            "By febuary i would be working in netherlands, making over $10000 a month as my salary. Then i'll send my parents $1000 every month to handle their needs and expenses and share $500 amongst my siblings."
    };

    public SliderAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return slideHeadings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = view.findViewById(R.id.image_icon);
        TextView slideHeadingTextView = view.findViewById(R.id.text_heading);
        TextView slideDescTextView = view.findViewById(R.id.text_desc);

        slideImageView.setImageResource(slideImages[position]);
        slideHeadingTextView.setText(slideHeadings[position]);
        slideDescTextView.setText(slideDescriptions[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }


}
