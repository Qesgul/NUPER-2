package www.nupter.org.nupter.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import java.util.ArrayList;

import www.nupter.org.nupter.R;

/**
 * 轮播广告
 * Created by weixiaokang on 15/10/22.
 */
public class Banner extends FrameLayout {

    private Context mContext;
    private int mBannerImageNum;
    private RadioButton[] mRbtn;
    private ViewPager mViewPager;
    private ArrayList<String> mImageUrl;
    private int[] mImageResoure;
    private OnClickListener mOnClickListener;
    private String mTitle;
    public Banner(Context context) {
        super(context);
    }

   /* public Banner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }*//*

    *//*public Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Banner, defStyleAttr, 0);
        mBannerImageNum = a.getInteger(R.styleable.Banner_banner_image_num, 1);
        a.recycle();
    }*//*

    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
        this.mOnClickListener = l;
    }

    public void setBannerImageNum(int mBannerImageNum) {
        this.mBannerImageNum = mBannerImageNum;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setImageUrl(ArrayList<String> imageUrl) {
        this.mImageUrl = imageUrl;
        this.mBannerImageNum = imageUrl.size();
        initView();
    }

    public void setImageResoure(int[] imageIds) {
        this.mImageResoure = imageIds;
        this.mBannerImageNum = imageIds.length;
        initView();
    }

    public int getCurrentItem() {
        int position = mViewPager.getCurrentItem();
        if (position == 0) {
            return mBannerImageNum - 1;
        }
        if (position == mBannerImageNum + 1) {
            return 0;
        }
        return position - 1;
    }

    public void initView() {
        initViewPager();
        initBottom();
    }

    private void initViewPager() {
        ArrayList<View> mView = new ArrayList<>();
        View []mViews;
        if (mBannerImageNum <= 1) {
            mViews = new View[mBannerImageNum];
        } else {
            mViews = new View[mBannerImageNum + 2];
        }
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        for (int i = 0; i < mViews.length; i++) {
            mViews[i] = mInflater.inflate(R.layout.home_viewpager, null);
            ImageView mImageView = (ImageView) mViews[i].findViewById(R.id.pager_one_image);
            if (mImageUrl != null) {
                if (i == 0) {
                    ImageUtils.display(mImageView, mImageUrl.get(mBannerImageNum - 1));
                } else if (i == mViews.length - 1) {
                    ImageUtils.display(mImageView, mImageUrl.get(0));
                } else {
                    ImageUtils.display(mImageView, mImageUrl.get(i - 1));
                }
            } else if (mImageResoure.length > 0) {
                if (i == 0) {
                    mImageView.setImageResource(mImageResoure[mBannerImageNum - 1]);
                } else if (i == mViews.length - 1){
                    mImageView.setImageResource(mImageResoure[0]);
                } else {
                    mImageView.setImageResource(mImageResoure[i - 1]);
                }
            }
            mImageView.setOnClickListener(mOnClickListener);
            mView.add(mViews[i]);
        }

        CommonPagerAdapter mPagerAdapter = new CommonPagerAdapter();
        mPagerAdapter.setData(mView);

        mViewPager = new ViewPager(mContext);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(1, false);
        mViewPager.addOnPageChangeListener(mPageChangeListener);

        LayoutParams mParams = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(mViewPager, mParams);
    }

    private void initBottom() {

        RadioGroup mRadioGroup = new RadioGroup(mContext);
        mRadioGroup.setGravity(Gravity.CENTER);
        mRadioGroup.setOrientation(LinearLayout.HORIZONTAL);
        mRbtn = new RadioButton[mBannerImageNum];
        RadioGroup.LayoutParams mRgParams = new RadioGroup.LayoutParams(ConvertUtils.dip2px(mContext, 18), LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < mRbtn.length; i++) {
            mRbtn[i] = new RadioButton(mContext);
            mRbtn[i].setGravity(Gravity.CENTER);
            mRbtn[i].setButtonDrawable(R.drawable.radio_btn_bg);
            mRadioGroup.addView(mRbtn[i], mRgParams);
        }
        mRbtn[0].setChecked(true);
        LayoutParams mFlParams = new LayoutParams(ConvertUtils.dip2px(mContext, 90), LayoutParams.WRAP_CONTENT);
        mFlParams.gravity = Gravity.CENTER|Gravity.BOTTOM;
        addView(mRadioGroup, mFlParams);

        if (mBannerImageNum <= 1) {
            mRadioGroup.setVisibility(View.INVISIBLE);
        }
        if (mTitle != null) {
            LinearLayout mTitleLayout = new LinearLayout(mContext);
            mTitleLayout.setBackgroundColor(getResources().getColor(R.color.banner_title_bg));

            TextView mTitleTv = new TextView(mContext);
            mTitleTv.setText(mTitle);
            mTitleTv.setTextColor(getResources().getColor(R.color.white));
            mTitleTv.setTextSize(ConvertUtils.sp2px(mContext, 14));
            LinearLayout.LayoutParams mTParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mTParams.gravity = Gravity.CENTER|Gravity.START;
            mTParams.leftMargin = ConvertUtils.dip2px(mContext, 7);
            mTitleLayout.addView(mTitleTv, mTParams);

            LayoutParams mTLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, ConvertUtils.dip2px(mContext, 48));
            mTLayoutParams.gravity = Gravity.BOTTOM;
            addView(mTitleLayout, mTLayoutParams);
        }

    }

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            LogUtils.i("scrolled == " + position + " " + positionOffset + " " + positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {
            LogUtils.i("selected == " + position);
            if (position == mBannerImageNum + 1) {
                mRbtn[0].setChecked(true);
            } else if (position == 0) {
                mRbtn[mBannerImageNum - 1].setChecked(true);
            } else {
                mRbtn[position - 1].setChecked(true);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            LogUtils.i("state == " + state);
            switch (state) {
                case ViewPager.SCROLL_STATE_DRAGGING:
                    break;
                case ViewPager.SCROLL_STATE_IDLE:
                    if (mViewPager.getCurrentItem() == mBannerImageNum + 1) {
                        mViewPager.setCurrentItem(1, false);
                    } else if (mViewPager.getCurrentItem() == 0) {
                        mViewPager.setCurrentItem(mBannerImageNum, false);
                    }
                    break;
            }
        }
    };*/
}
