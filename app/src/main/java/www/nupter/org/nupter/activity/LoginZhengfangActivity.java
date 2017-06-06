package www.nupter.org.nupter.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.http.RequestParams;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.jpush.android.api.JPushInterface;
import www.nupter.org.nupter.R;
import www.nupter.org.nupter.http.HttpCallBack;
import www.nupter.org.nupter.http.HttpResquestUtil;
import www.nupter.org.nupter.http.RequestUrl;
import www.nupter.org.nupter.model.CookieBean;
import www.nupter.org.nupter.utils.DataUtils;
import www.nupter.org.nupter.utils.PreferenceKey;

public class LoginZhengfangActivity extends AppCompatActivity {

    private ImageView verifyImage;

    private EditText stuNumEdit;

    private EditText passEdit;

    private EditText veryfyEdit;

    private Button loginButton;

    private String coookie = "";

    private TextView changeImageText;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bitmap bitmap = (Bitmap) msg.obj;
            verifyImage.setImageBitmap(bitmap);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_zhengfang);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        verifyImage = (ImageView) findViewById(R.id.verifyImage);
        stuNumEdit = (EditText) findViewById(R.id.stuNum);
        passEdit = (EditText) findViewById(R.id.password);
        veryfyEdit = (EditText) findViewById(R.id.verifyEdit);
        loginButton = (Button) findViewById(R.id.login);
        changeImageText = (TextView) findViewById(R.id.changeImage);


        stuNumEdit.setText(DataUtils.getPreferences("zhengFangAccount", ""));
        passEdit.setText(DataUtils.getPreferences("zhengFangPass", ""));

        RequestParams requestParams = new RequestParams(RequestUrl.cookieZhengFang);
        HttpResquestUtil.getMethod(requestParams, CookieBean.class, new HttpCallBack<CookieBean>() {
            @Override
            public void success(CookieBean data) {
                coookie = data.getInfo().getCookie_name();
                //HttpResquestUtil.initImage(verifyImage, RequestUrl.verifyImageUrl + data.getInfo().getCookie_name());
                displayImage(RequestUrl.verifyImageUrl + data.getInfo().getCookie_name());
            }

            @Override
            public void erro(String res) {

            }
        });

        changeImageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayImage(RequestUrl.verifyImageUrl + coookie);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stu = stuNumEdit.getText().toString();
                String pass = passEdit.getText().toString();
                String verifyCode = veryfyEdit.getText().toString();
                DataUtils.putPreferences("zhengFangAccount", stu);
                DataUtils.putPreferences("zhengFangPass", pass);
                final RequestParams requestParams = new RequestParams(RequestUrl.loginZhengFangUrl);
                requestParams.addBodyParameter("num", stu);
                requestParams.addBodyParameter("cookie", coookie);
                requestParams.addBodyParameter("code", verifyCode);
                requestParams.addBodyParameter("pass", pass);
                HttpResquestUtil.getPostString(requestParams, new HttpCallBack<String>() {
                    @Override
                    public void success(String data) {
                        DataUtils.putPreferences(PreferenceKey.KEBIAO, data);
                        Intent intent=new Intent(LoginZhengfangActivity.this,ClassTableActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    @Override
                    public void erro(String res) {
                        displayImage(RequestUrl.verifyImageUrl + coookie);
                    }
                });
            }
        });

        if (!DataUtils.getPreferences(PreferenceKey.KEBIAO,"").equals("")){
            Intent intent=new Intent();
            intent.setClass(LoginZhengfangActivity.this,ClassTableActivity.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    public void displayImage(final String urlString) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                URL imgUrl = null;
                Bitmap bitmap = null;
                byte[] gifbyte = null;
                try {
                    imgUrl = new URL(urlString);
                    HttpURLConnection urlConn = (HttpURLConnection) imgUrl
                            .openConnection();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    urlConn.setDoInput(true);
                    urlConn.connect();
                    // 将得到的数据转化成InputStream
                    InputStream in = urlConn.getInputStream();
                    // 将InputStream转换成Bitmap
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = in.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                    gifbyte = out.toByteArray();
                    bitmap = BitmapFactory.decodeByteArray(gifbyte, 0, gifbyte.length);
                    Message message = new Message();
                    message.obj = bitmap;
                    handler.sendMessage(message);

                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}


