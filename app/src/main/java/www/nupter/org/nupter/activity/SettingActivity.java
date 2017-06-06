package www.nupter.org.nupter.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import www.nupter.org.nupter.R;
import www.nupter.org.nupter.application.MyApplication;

public class SettingActivity extends BaseActivity {

    Button quitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle("设置");
        quitButton=(Button)findViewById(R.id.btn_back);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.quitUser();
                BaseToast("已经退出当前账号");
                finish();
            }
        });
    }

    @Override
    protected void HandleTitleBarEvent(TitleBar component, View v) {

        switch (component){
            case LEFT:
                finish();
                break;
        }

    }
}
