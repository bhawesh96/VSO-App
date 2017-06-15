package com.example.bhawesh96.vso_androidapp.LoginSignUp;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.FrameLayout;

import com.example.bhawesh96.vso_androidapp.ActivityHomeScreen;
import com.example.bhawesh96.vso_androidapp.R;
import com.example.bhawesh96.vso_androidapp.Utils.SessionManager;

public class ActivityLogin extends AppCompatActivity
{
//    private FirebaseUser user;
//    private FirebaseAuth mAuth;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        sessionManager = new SessionManager(this);

        /*String email = "bhansalibhawesh@yahoo.com";
        String password = "Clandestine@1996";

        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    // Sign in success, update UI with the signed-in user's information
                    //Log.d(TAG, "signInWithEmail:success");
                    user = mAuth.getCurrentUser();
                }
                else
                {
                    // If sign in fails, display a message to the user.
                    Log.w("LOGIN-ACTIVITY", "signInWithEmail:failure", task.getException());
                    Toast.makeText(ActivityLogin.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        TypeWriter typeWriter = (TypeWriter) findViewById(R.id.tv_type);
        typeWriter.setCharacterDelay(80);
        typeWriter.animateText("\"Let's touch the lives of others\"");

        typeWriter.animate().translationY(-500).setDuration(4000).setListener(new Animator.AnimatorListener()
        {
            @Override
            public void onAnimationStart(Animator animation) { }

            @Override
            public void onAnimationEnd(Animator animation)
            {
                /*if (user != null)
                {
                    finish();
                    startActivity(new Intent(ActivityLogin.this, ActivityHomeScreen.class));
                }*/

                if (sessionManager.isLoggedIn())
                {
                    finish();
                    startActivity(new Intent(ActivityLogin.this, ActivityHomeScreen.class));
                }

                final FrameLayout ll = (FrameLayout) findViewById(R.id.container_login);
                expand(ll);
                ll.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.container_login, new FragmentLogin(), FragmentLogin.TAG).commit();

            }

            @Override
            public void onAnimationCancel(Animator animation)
            {
                findViewById(R.id.container_login).setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animator animation) { }
        });
    }

    public static Animation expand(final View view)
    {
        int matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(((View) view.getParent()).getWidth(), View.MeasureSpec.EXACTLY);
        int wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(matchParentMeasureSpec, wrapContentMeasureSpec);
        final int targetHeight = 1000;

        // Older versions of android (pre API 21) cancel animations for views with a height of 0 so use 1 instead.
        view.getLayoutParams().height = 1;

        Animation animation = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t)
            {

                view.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.MATCH_PARENT
                        : (int) (targetHeight * interpolatedTime);

                view.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(1500);
        view.startAnimation(animation);

        return animation;
    }
}
