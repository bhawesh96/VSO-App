package com.example.bhawesh96.vso_androidapp;

import android.animation.Animator;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

public class ActivityLogin extends AppCompatActivity {

    public static Animation expand(final View view) {
        int matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(((View) view.getParent()).getWidth(), View.MeasureSpec.EXACTLY);
        int wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(matchParentMeasureSpec, wrapContentMeasureSpec);
        final int targetHeight = 1000;

        // Older versions of android (pre API 21) cancel animations for views with a height of 0 so use 1 instead.
        view.getLayoutParams().height = 1;
        view.setVisibility(View.VISIBLE);

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {

                view.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);

                view.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(2500);
        view.startAnimation(animation);

        return animation;
    }

    //static Interpolator easeInOutQuart = new LinearInterpolator();
    //PathInterpolatorCompat.create(0.77f, 0f, 0.175f, 1f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        TypeWriter typeWriter = (TypeWriter) findViewById(R.id.tv_type);
        typeWriter.setCharacterDelay(80);
        typeWriter.animateText("\"Let's touch the lives of others\"");

        typeWriter.animate().translationY(-500).setDuration(4000).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
                ViewPagerAdapterLogin adapter = new ViewPagerAdapterLogin(getApplicationContext(), getSupportFragmentManager());
                viewPager.setAdapter(adapter);
                TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
                tabLayout.setupWithViewPager(viewPager);
                LinearLayout ll = (LinearLayout) findViewById(R.id.tabs_container);
                expand(ll);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                findViewById(R.id.tabs_container).setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }

    /*private static int computeDurationFromHeight(View view) {
        // 1dp/ms * multiplier
        return (int) (view.getMeasuredHeight() / view.getContext().getResources().getDisplayMetrics().density);
    }*/
}
