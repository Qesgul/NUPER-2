package www.nupter.org.nupter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;

import www.nupter.org.nupter.R;
import www.nupter.org.nupter.http.HttpResquestUtil;
import www.nupter.org.nupter.http.RequestUrl;
import www.nupter.org.nupter.model.LostBean;
import www.nupter.org.nupter.utils.DataUtils;
import www.nupter.org.nupter.view.ReactImageView;

public class LostDetailActivity extends BaseActivity {
    private ReactImageView avaImage;
    private TextView userName;
    private TextView timeText;
    private TextView desText;
    private TextView siteText;
    private TextView dateText;
    private TextView nameText;
    private TextView phoneText;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_detail);
        avaImage = (ReactImageView) findViewById(R.id.avatar_image);
        userName = (TextView) findViewById(R.id.userName);
        timeText = (TextView) findViewById(R.id.time);
        siteText = (TextView) findViewById(R.id.location);
        dateText = (TextView) findViewById(R.id.date);
        nameText = (TextView) findViewById(R.id.name);
        phoneText = (TextView) findViewById(R.id.phone);
        imageView1 = (ImageView) findViewById(R.id.image_url1);
        imageView2 = (ImageView) findViewById(R.id.image_url2);
        imageView3 = (ImageView) findViewById(R.id.image_url3);
        desText = (TextView) findViewById(R.id.des);
        Intent intent = getIntent();
        LostBean.InfoEntity infoEntity = intent.getParcelableExtra("lost");
        userName.setText(infoEntity.getUserName());
        timeText.setText(infoEntity.getCreateTime());
        dateText.setText(infoEntity.getTime());
        nameText.setText(infoEntity.getName());
        phoneText.setText(infoEntity.getPhone());
        desText.setText(infoEntity.getDescription());
        siteText.setText(infoEntity.getSite());
        HttpResquestUtil.initImage(avaImage, infoEntity.getAvatar_url());

        String baseUrl=RequestUrl.baseimgurl;
        switch (Integer.valueOf(infoEntity.getImgNum())) {
            case 0:
                imageView1.setVisibility(View.GONE);
                imageView2.setVisibility(View.GONE);
                imageView3.setVisibility(View.GONE);
                break;
            case 1:
                HttpResquestUtil.initImage(imageView1,baseUrl+infoEntity.getImage_url1()+"-lost");
                break;
            case 2:
                HttpResquestUtil.initImage(imageView1,baseUrl+infoEntity.getImage_url1()+"-lost");
                HttpResquestUtil.initImage(imageView2,baseUrl+infoEntity.getImage_url2()+"-lost");
                break;
            case 3:
                HttpResquestUtil.initImage(imageView1,baseUrl+infoEntity.getImage_url1()+"-lost");
                HttpResquestUtil.initImage(imageView2,baseUrl+infoEntity.getImage_url2()+"-lost");
                HttpResquestUtil.initImage(imageView3,baseUrl+infoEntity.getImage_url3()+"-lost");
                break;
        }

        setCenterTitle("详细信息");

    }

    @Override
    protected void HandleTitleBarEvent(TitleBar component, View v) {
        if (component == TitleBar.LEFT) {
            finish();
        }
    }
}
