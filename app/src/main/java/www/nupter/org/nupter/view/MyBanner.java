package www.nupter.org.nupter.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import www.nupter.org.nupter.R;
import www.nupter.org.nupter.http.HttpResquestUtil;

import static www.nupter.org.nupter.R.drawable.mybanner;

/**
 * Created by fangzhenyi on 15/11/18.
 */
public class MyBanner extends FrameLayout {
    private ViewPager mViewpage;
    private String imageUrl[];
    private String titles[];
    private Context mContext;
    private View[] mViews;
    private OnImagelistenner onImagelistenner;
    private boolean isScrool=true;
    private boolean isAutoScrool=false;
    private int imageIndex=0;



    private RadioButton[] mRbtn;

    public MyBanner(Context context) {
        super(context);
        mContext = context;
    }

    public MyBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public void setData(String[] imageUrl, String[] titles) {
        this.imageUrl = imageUrl;
        this.titles = titles;

        initView();
    }

    public void setData(String[] imageUrl) {
        this.imageUrl = imageUrl;
        initView();
        initBottom();
    }
    public void setOnImagelistenner(OnImagelistenner onImagelistenner){
        this.onImagelistenner=onImagelistenner;
    }

    public void setAutoScrool(boolean isAutoScrool){
        this.isAutoScrool=isAutoScrool;
    }



    private void initView() {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        if (imageUrl.length <= 1) {
            mViews = new View[imageUrl.length];
            for (int i = 0; i < imageUrl.length ; i++) {
                mViews[i] = mInflater.inflate(R.layout.mybanner, null);
                ImageView mImageView = (ImageView) mViews[i].findViewById(R.id.mybanner_image);
                if (i == 0) {
                    HttpResquestUtil.initImage(mImageView, imageUrl[imageUrl.length - 1]);
                } else if (i == imageUrl.length + 1) {
                    HttpResquestUtil.initImage(mImageView, imageUrl[0]);
                } else {
                    HttpResquestUtil.initImage(mImageView, imageUrl[i - 1]);
                }

                mImageView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onImagelistenner.onClick(imageIndex);
                    }
                });
            }
        } else {
            mViews = new View[imageUrl.length + 2];
            for (int i = 0; i < (imageUrl.length + 2); i++) {
                mViews[i] = mInflater.inflate(R.layout.mybanner, null);
                ImageView mImageView = (ImageView) mViews[i].findViewById(R.id.mybanner_image);
                if (i == 0) {
                    HttpResquestUtil.initImage(mImageView, imageUrl[imageUrl.length - 1]);
                } else if (i == imageUrl.length + 1) {
                    HttpResquestUtil.initImage(mImageView, imageUrl[0]);
                } else {
                    HttpResquestUtil.initImage(mImageView, imageUrl[i - 1]);
                }

                mImageView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onImagelistenner.onClick(imageIndex);
                    }
                });
            }
        }



        mViewpage = new ViewPager(mContext);

        mViewpage.setAdapter(new ViewPageAdapter());

        mViewpage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == imageUrl.length + 1) {
                    mRbtn[0].setChecked(true);
                    imageIndex=0;
                } else if (position == 0) {
                    mRbtn[imageUrl.length - 1].setChecked(true);
                    imageIndex=imageUrl.length-1;
                } else {
                    mRbtn[position - 1].setChecked(true);
                    imageIndex=position-1;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                //Log.i("监听page的状态",""+state);

                if (ViewPager.SCROLL_STATE_IDLE == state) {
                    if (mViewpage.getCurrentItem() == imageUrl.length + 1) {
                        mViewpage.setCurrentItem(1, false);

                    } else if (mViewpage.getCurrentItem() == 0) {
                        mViewpage.setCurrentItem(imageUrl.length, false);
                    }
                }
            }
        });
        LayoutParams mParams = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        addView(mViewpage, mParams);


    }

    private void initBottom() {
        RadioGroup mRadioGroup = new RadioGroup(mContext);
        mRadioGroup.setGravity(Gravity.CENTER);
        mRadioGroup.setOrientation(LinearLayout.HORIZONTAL);
        mRbtn = new RadioButton[imageUrl.length];
        RadioGroup.LayoutParams mRgParams = new RadioGroup.LayoutParams(dip2px(mContext, 15), LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < mRbtn.length; i++) {
            mRbtn[i] = new RadioButton(mContext);
            mRbtn[i].setGravity(Gravity.CENTER);
            mRbtn[i].setButtonDrawable(mybanner);
            mRadioGroup.addView(mRbtn[i], mRgParams);
        }
        mRbtn[0].setChecked(true);
        LayoutParams mFlParams = new LayoutParams(dip2px(mContext, 120), LayoutParams.WRAP_CONTENT);
        mFlParams.gravity = Gravity.END | Gravity.BOTTOM;
        mFlParams.bottomMargin = dip2px(mContext, 5);
        addView(mRadioGroup, mFlParams);


        if (imageUrl.length <= 1) {
            mRadioGroup.setVisibility(View.INVISIBLE);
        }

        mViewpage.setCurrentItem(1, false);

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        //每隔2秒钟切换一张图片
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 2, 5, TimeUnit.SECONDS);

     mViewpage.setOnTouchListener(new OnTouchListener() {
         @Override
         public boolean onTouch(View v, MotionEvent event) {
             if (MotionEvent.ACTION_MOVE==event.getAction()){
                    isScrool=false;
             }else {
                    isScrool=true;
             }
             return false;
         }
     });
    }

    private class ViewPagerTask implements Runnable {
        @Override
        public void run() {

            handler.obtainMessage().sendToTarget();
        }

        Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //设置当前页面
            if (isAutoScrool&&isScrool)
            mViewpage.setCurrentItem((mViewpage.getCurrentItem()+1)%(imageUrl.length+2));
        }
    };
}

    class ViewPageAdapter extends PagerAdapter

    {
        @Override
        public int getCount() {
            if (imageUrl.length <= 1) {
                return imageUrl.length;
            } else {
                return imageUrl.length + 2;
            }

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViews[position], 0);
            return mViews[position];
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViews[position]);
        }

    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public interface OnImagelistenner{
        public void onClick(int position);
    }


}
