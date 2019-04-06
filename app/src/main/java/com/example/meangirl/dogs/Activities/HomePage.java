package com.example.meangirl.dogs.Activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.example.meangirl.dogs.GroupsList;
import com.example.meangirl.dogs.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomePage extends AppCompatActivity {

    FloatingActionButton addDogBtn;
    ImageSwitcher imageSwitcher;
    CircleImageView btnFci, btnPicker;
    Animation in,out; //animations for the image switching
    int currentIndex; //index tracker (for current image displayed)
    int [] imgIds = {R.drawable.slider, R.drawable.slider2, R.drawable.slider3,R.drawable.slider4}; //list of images for the slider
    private Handler mHandler = new Handler(); //handler to pass information in the thread

    //Runnable instance for the image switcher
    Runnable r = new Runnable() {
        // Override the run Method
        public void run() {
            try
            {
                updateImageSwitcherImage(); //set/change image on the image switcher
            }
            finally
            {
                mHandler.postDelayed(this, 3000); //rotation time can be set here
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectWithXml();
        currentIndex=-1;
        //setting up the view for the image switcher
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.FILL_PARENT));
                return imageView;
            }
        });
        //setting animations for the slider
        in = AnimationUtils.loadAnimation(this,android.R.anim.fade_in);
        out = AnimationUtils.loadAnimation(this,android.R.anim.fade_out);
        imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);
        mHandler.postDelayed(r, 1000); //initial img showing up
        //on click events for the Buttons
        /**
         * Onclick listner for the btnFci. On click, user gets redirected to the GroupsList
         */
        btnFci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GroupsList.class);
                startActivity(intent);
            }
        });
        /**
         * Onclick listner for the btnPicker, On click, user gets redirected to the DogPicker
         */
        btnPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DogPicker.class);
                startActivity(intent);
            }
        });
        /**
         * Onclick listener for the addDogBtn, On click, user gets redirected to the AddDog
         */
        addDogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddDog.class);
                startActivity(intent);
            }
        });
    }
    /**
     * Method to rotate imageSwitcher images
     * It adds the index, until the array of images is filled, then it starts over from 0
     */
    private void updateImageSwitcherImage(){
        currentIndex++;
        if(currentIndex==imgIds.length) currentIndex=0;
        imageSwitcher.setImageResource(imgIds[currentIndex]);
    }
    /**
     * Method to connect XML components with Java
     */
    private void connectWithXml() {

        imageSwitcher = findViewById(R.id.imgSwitcher);
        btnFci = findViewById(R.id.btnfci);
        btnPicker = findViewById(R.id.btnpicker);
        addDogBtn = findViewById(R.id.addDogBtn);
    }
}
