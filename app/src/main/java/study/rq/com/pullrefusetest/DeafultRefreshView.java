package study.rq.com.pullrefusetest;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class DeafultRefreshView extends LinearLayout implements IPullRefreshView {

    private static final int ANIMATION_DURATION = 150;
    private static final Interpolator ANIMATION_INTERPOLATOR = new DecelerateInterpolator();

    private Animation mRotateAnimation;
    private Animation mResetRotateAnimation;
    private ImageView icon;
    private TextView text;

    public DeafultRefreshView(Context context) {
        this(context, null);
    }

    public DeafultRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setGravity(Gravity.CENTER);
        setOrientation(HORIZONTAL);

        if (icon == null) {
            LayoutParams lp = new LayoutParams(100, 100);
            icon = new ImageView(getContext());
//            icon.setImageResource(R.drawable.loading_1);
            icon.clearAnimation();
            AnimationDrawable drawable = (AnimationDrawable) getResources().getDrawable(R.drawable.dialog_progress_loading);
            drawable.start();
            icon.setImageDrawable(drawable);
            addView(icon, lp);
        }

//        if(text==null){
//            LayoutParams lp=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//            lp.leftMargin=DensityUtils.dp2px(8);
//            text=new TextView(getContext());
//            text.setTextSize(14);
//            text.setTextColor(Color.BLACK);
//            text.setText(R.string.pulling);
//            addView(text,lp);
//        }

        initAnimation();


    }

    private void initAnimation() {
        mRotateAnimation = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateAnimation.setInterpolator(ANIMATION_INTERPOLATOR);
        mRotateAnimation.setDuration(ANIMATION_DURATION);
        mRotateAnimation.setFillAfter(true);

        mResetRotateAnimation = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        mResetRotateAnimation.setInterpolator(ANIMATION_INTERPOLATOR);
        mResetRotateAnimation.setDuration(ANIMATION_DURATION);
        mResetRotateAnimation.setFillAfter(true);
    }

    @Override
    public void onPullHided() {
        icon.setVisibility(VISIBLE);
        icon.clearAnimation();
        icon.setImageResource(R.drawable.loading_2);
    }

    @Override
    public void onPullRefresh() {
        icon.setVisibility(VISIBLE);
        icon.clearAnimation();
        AnimationDrawable drawable = (AnimationDrawable) getResources().getDrawable(R.drawable.dialog_progress_loading);
        drawable.start();
        icon.setImageDrawable(drawable);
    }

    @Override
    public void onPullFreeHand() {
        icon.setVisibility(VISIBLE);
        icon.clearAnimation();
        if (icon.getAnimation() == null || icon.getAnimation() == mResetRotateAnimation) {
            icon.startAnimation(mRotateAnimation);
        }
        icon.setImageResource(R.drawable.loading_3);
    }

    @Override
    public void onPullDowning() {
        icon.setVisibility(VISIBLE);
        icon.clearAnimation();
        icon.setImageResource(R.drawable.loading_4);
    }

    @Override
    public void onPullFinished() {
        icon.setVisibility(GONE);
        icon.clearAnimation();
        AnimationDrawable drawable = (AnimationDrawable) getResources().getDrawable(R.drawable.dialog_progress_loading);
        drawable.start();
        icon.setImageDrawable(drawable);
    }

    @Override
    public void onPullProgress(float pullDistance, float pullProgress) {
        Log.e("DeafultRefreshView", "pullProgress:" + pullProgress);
    }
}
