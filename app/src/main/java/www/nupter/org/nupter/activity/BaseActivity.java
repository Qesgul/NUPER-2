package www.nupter.org.nupter.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import www.nupter.org.nupter.R;
import www.nupter.org.nupter.view.BaseTitleLayout;


/**
 * Created by Xuri on 2015/4/3.
 */
public abstract class BaseActivity extends AppCompatActivity{

    private BaseTitleLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    @Override
    public void setContentView(int layoutResID) {
        if (layout == null) {
            layout = new BaseTitleLayout(this, layoutResID);
        }
        super.setContentView(layout);
        this.setClickListener(new View[]{layout.left_image, layout.right_image,layout.rightText});
    }

    @Override
    public void setTitle(CharSequence title) {
        if (!TextUtils.isEmpty(title))
            layout.title.setText(title);
    }

    public void setCenterTitle(String title){
        layout.centerText.setText(title);
    }


    public void setRightText(String text){
        layout.rightText.setText(text);
    }


    public void setNav(int resId){
        layout.left_image.setImageResource(resId);
    }

    /**
     * @param views
     */
    private void setClickListener(View[] views) {
        for (View v : views) {
            v.setOnClickListener(listener);
        }
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.equals(layout.left_image)) {
                HandleTitleBarEvent(TitleBar.LEFT, view);
            } else if (view.equals(layout.right_image)) {
                HandleTitleBarEvent(TitleBar.RIGHT, view);
            }else if (view.equals(layout.rightText)){
                HandleTitleBarEvent(TitleBar.RightText,view);
            }
        }
    };

    public void BaseToast(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    /**
     * @param component
     * @param v
     */
    protected abstract void HandleTitleBarEvent(TitleBar component, View v);

    public enum TitleBar {
        LEFT,
        RIGHT,
        RightText
    }
}
