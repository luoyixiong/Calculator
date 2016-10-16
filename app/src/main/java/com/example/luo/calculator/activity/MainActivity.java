package com.example.luo.calculator.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

import com.example.luo.calculator.R;
import com.example.luo.calculator.adapter.GuideFragmentAdapter;

public class MainActivity extends FragmentActivity implements OnClickListener {
    public static final int TAB_CAL = 0;
    public static final int TAB_TRANSFORM = 1;


    private ViewPager viewPager;
    private RadioButton main_tab_cal, main_tab_transform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        addListener();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        main_tab_cal = (RadioButton) findViewById(R.id.main_tab_cal);
        main_tab_transform = (RadioButton) findViewById(R.id.main_tab_transform);

        main_tab_cal.setOnClickListener(this);
        main_tab_transform.setOnClickListener(this);

        GuideFragmentAdapter adapter = new GuideFragmentAdapter(
                getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    private void addListener() {
        viewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int id) {
                switch (id) {
                    case TAB_CAL:
                        main_tab_cal.setChecked(true);
                        main_tab_cal.setTextColor(MainActivity.this.getResources().getColor(R.color.colorAccent));
                        main_tab_transform.setTextColor(MainActivity.this.getResources().getColor(R.color.colorPrimary));

                        break;
                    case TAB_TRANSFORM:
                        main_tab_transform.setChecked(true);
                        main_tab_cal.setTextColor(MainActivity.this.getResources().getColor(R.color.colorAccent));
                        main_tab_transform.setTextColor(MainActivity.this.getResources().getColor(R.color.colorPrimary));

                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_tab_cal:
                viewPager.setCurrentItem(TAB_CAL);
                break;
            case R.id.main_tab_transform:
                viewPager.setCurrentItem(TAB_TRANSFORM);
                break;


            default:
                break;
        }
    }
}