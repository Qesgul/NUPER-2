package www.nupter.org.nupter.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.xutils.common.Callback;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;
import www.nupter.org.nupter.R;
import www.nupter.org.nupter.view.MyviewPager;

public class ImageDetail extends AppCompatActivity {
    private ImageView imageView;
    PhotoViewAttacher mAttacher;
    private MyviewPager viewPager;
    List<ImageView> imageViews;
    String[] arrayUrl;

    private int pagePosition;
    private ImageView back_imageView;
    private TextView detail_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        viewPager = (MyviewPager) findViewById(R.id.myViewPager);
        back_imageView=(ImageView)findViewById(R.id.detail_image_back);
        detail_textview=(TextView)findViewById(R.id.detail_image_text);
        Intent intent = getIntent();
        String url = intent.getStringExtra("image");
        int curentNumber = Integer.valueOf(intent.getStringExtra("i"));
        String image_url = intent.getStringExtra("url_array");
        Log.i("url_array", image_url);
        Log.i("number", "" + curentNumber);
        arrayUrl = image_url.split("  ");
        imageViews = new ArrayList<>();
        for (int i = 0; i < arrayUrl.length; i++) {
            ImageView imageView = new ImageView(ImageDetail.this);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            imageView.setLayoutParams(layoutParams);
            imageViews.add(imageView);
        }

        detail_textview.setText(""+(curentNumber+1)+"/"+arrayUrl.length);

        viewPager.setpagerCount(arrayUrl.length);
        viewPager.setAdapter(new ViewPageAdapter());
        viewPager.setCurrentItem(curentNumber);
        viewPager.setCurrentIndex(curentNumber);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentIndex(position);
                detail_textview.setText(""+(position+1)+"/"+arrayUrl.length);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        back_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    class ViewPageAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return arrayUrl.length;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViews.get(position), 0);
            return getImageView(imageViews.get(position), arrayUrl[position]);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViews.get(position));
        }

    }

    public ImageView getImageView(final ImageView imageView, String url) {
        x.image().bind(imageView, url, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                mAttacher = new PhotoViewAttacher(imageView);
                mAttacher.update();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        return imageView;
    }

}
