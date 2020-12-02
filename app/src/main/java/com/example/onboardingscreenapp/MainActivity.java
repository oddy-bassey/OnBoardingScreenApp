package com.example.onboardingscreenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //declaring a viewPaer slider
    private ViewPager slideViewPager;

    //declaring a linearLayout to embedd the dots
    private LinearLayout dotsLayout;

    //declaring an array to create the dots
    private TextView[] slideDots;

    //declaring the previous and next buttons
    private Button nextButton, previousButton;

    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextButton = findViewById(R.id.next_button);
        previousButton = findViewById(R.id.previuos_button);
        previousButton.setVisibility(View.INVISIBLE);

        //instantiating the viewPager and the dotsLayout
        slideViewPager = findViewById(R.id.slideView);
        dotsLayout = findViewById(R.id.dotsLayout);

        //declaring and instantiating the slider adapter
        SliderAdapter sliderAdapter = new SliderAdapter(this);

        //passing a reference of the slideAdapter to the viewPager
        slideViewPager.setAdapter(sliderAdapter);
        //passing a reference of the pageChangeListener to the viewPager
        slideViewPager.addOnPageChangeListener(vpageChangeListener);

        addDotsIndicator(0);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideViewPager.setCurrentItem(currentPage+1);
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideViewPager.setCurrentItem(currentPage-1);
            }
        });
    }

    //declaring ma method to the dots
    public void addDotsIndicator(int position){
        slideDots = new TextView[3];
        dotsLayout.removeAllViews();

        for(int i=0; i<slideDots.length; i++){
            slideDots[i] = new TextView(this);
            slideDots[i].setText(Html.fromHtml("&#8226"));
            slideDots[i].setTextSize(35);
            slideDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            dotsLayout.addView(slideDots[i]);
        }
        if(slideDots.length > 0){
            slideDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    ViewPager.OnPageChangeListener vpageChangeListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDotsIndicator(position);
            currentPage = position;

            if(position == 0){
                nextButton.setEnabled(true);
                previousButton.setEnabled(false);
                previousButton.setVisibility(View.INVISIBLE);

                nextButton.setText("NEXT");
                previousButton.setText("");
            }else if(position == slideDots.length-1){
                previousButton.setEnabled(true);
                nextButton.setEnabled(false);
                nextButton.setVisibility(View.INVISIBLE);

                previousButton.setText("PREVIOUS");
                nextButton.setText("");
            }else{
                previousButton.setEnabled(true);
                nextButton.setEnabled(true);

                nextButton.setVisibility(View.VISIBLE);
                previousButton.setVisibility(View.VISIBLE);

                previousButton.setText("PREVIOUS");
                nextButton.setText("NEXT");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
