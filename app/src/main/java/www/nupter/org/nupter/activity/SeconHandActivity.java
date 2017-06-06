package www.nupter.org.nupter.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import www.nupter.org.nupter.R;
import www.nupter.org.nupter.http.HttpResquestUtil;
import www.nupter.org.nupter.http.RequestUrl;
import www.nupter.org.nupter.model.SecondHandBean;

public class SeconHandActivity extends AppCompatActivity {
    SecondHandBean.InfoEntity info;
    TextView txtdescription,txttitle,txttag,txtprice,txttime,txtname;
    ImageView img1,avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secon_hand);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar.setTitle("详情");

        info = (SecondHandBean.InfoEntity) getIntent().getBundleExtra("bun").get("sec");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabcall);
        txtdescription = (TextView) findViewById(R.id.textViewDescription);
        txttitle = (TextView)findViewById(R.id.textViewtitle);
        txttag = (TextView)findViewById(R.id.textType);
        txtprice = (TextView)findViewById(R.id.textViewmoney);
        txtname = (TextView)findViewById(R.id.textViewname);
        txttime = (TextView)findViewById(R.id.textViewtime);
        img1 = (ImageView)findViewById(R.id.imageView1);
        avatar = (ImageView)findViewById(R.id.imageViewavatar);

        txtname.setText(info.getUserName());

        txtdescription.setText(info.getDescription());
        txttime.setText(info.getCreateTime());
        txtprice.setText(info.getPrice());
        txttag.setText(info.getTag());
        txttitle.setText(info.getTitle());

        HttpResquestUtil.initImage(img1, RequestUrl.baseimgurl+ info.getImage_url1()+"-suolue");
        HttpResquestUtil.initImage(avatar,info.getAvatar_url());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
