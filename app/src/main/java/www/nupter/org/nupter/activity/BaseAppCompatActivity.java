package www.nupter.org.nupter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import www.nupter.org.nupter.R;

/**
 * Created by fangzhenyi on 16/3/12.
 */
public class BaseAppCompatActivity extends AppCompatActivity{
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
}
