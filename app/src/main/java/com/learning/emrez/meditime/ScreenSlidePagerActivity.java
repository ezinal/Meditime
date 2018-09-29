package com.learning.emrez.meditime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.learning.emrez.meditime.ViewpagerFragments.HowManyFragment;
import com.learning.emrez.meditime.ViewpagerFragments.MedicineNameFragment;
import com.learning.emrez.meditime.ViewpagerFragments.MedicinePhotoFragment;
import com.learning.emrez.meditime.ViewpagerFragments.SelectTimeFragment;
import com.learning.emrez.meditime.ViewpagerFragments.SelectWeekdaysFragment;

import java.util.ArrayList;

public class ScreenSlidePagerActivity extends FragmentActivity {
    // This holds all the currently displayable views, in order from left to right.
    private ArrayList<View> views = new ArrayList<>();
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    public static ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    /**
     * This button will open up the next viewpager page
     */
    private Button btnnext;

    /**
     * This button skips photo taking action in take_med_photo.xml
     */
    private Button btnskip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.viewpager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);



        // Create new fragment and transaction
        Fragment newFragment = new MedicineNameFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the viewpager view with this fragment,
        // and add the transaction to the back stack
        transaction.add(R.id.viewpager, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

        btnnext = findViewById(R.id.btnnext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : implement here to check if forms are empty
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
            }
        });

//        btnskip = findViewById(R.id.btnskip);
//        btnskip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //TODO : Implement photo as null or grey
//                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
//            }
//        });

    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ViewPager Fragment objects, in
     * sequence.
     */
    public static class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new MedicineNameFragment();
                case 1:
                    return new MedicinePhotoFragment();
                case 2:
                    return new SelectTimeFragment();
                case 3:
                    return new SelectWeekdaysFragment();
                case 4:
                    return new HowManyFragment();
                default:
                    return new MedicineNameFragment();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}