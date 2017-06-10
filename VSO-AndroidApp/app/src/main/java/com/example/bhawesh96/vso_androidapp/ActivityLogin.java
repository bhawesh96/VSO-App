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
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityLogin extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button b;

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

        b = (Button) findViewById(R.id.button);
        String email = "bhansalibhawesh@yahoo.com";
        String password = "Clandestine@1996";

        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            finish();
                            startActivity(new Intent(ActivityLogin.this, AddProject.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("LOGIN-ACTIVITY", "signInWithEmail:failure", task.getException());
                            Toast.makeText(ActivityLogin.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
        if(mAuth.getCurrentUser() != null) {

        }

        TypeWriter typeWriter = (TypeWriter) findViewById(R.id.tv_type);
        typeWriter.setCharacterDelay(80);
        typeWriter.animateText("Let's touch the lives of others");

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
