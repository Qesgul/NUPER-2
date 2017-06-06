package www.nupter.org.nupter.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;


import org.xutils.http.RequestParams;

import java.util.Map;
import java.util.Set;

import www.nupter.org.nupter.R;
import www.nupter.org.nupter.application.Config;
import www.nupter.org.nupter.application.MyApplication;
import www.nupter.org.nupter.http.HttpCallBack;
import www.nupter.org.nupter.http.HttpResquestUtil;
import www.nupter.org.nupter.http.RequestUrl;
import www.nupter.org.nupter.model.BaseBean;
import www.nupter.org.nupter.model.UserBean;
import www.nupter.org.nupter.model.UserInfoBean;
import www.nupter.org.nupter.utils.Common;
import www.nupter.org.nupter.utils.DataUtils;
import www.nupter.org.nupter.utils.PreferenceKey;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoginActivity extends AppCompatActivity {

    private ImageView login_qq_imageView;
    private ImageView login_sina_imageView;
    private TextView cancel_login_textview;

    //友盟登陆
    private UMShareAPI umShareAPI;
    //友盟回调监听
    private UMAuthListener umAuthListener;

    private String openid = "";

    private String nickName = "";

    private String avatar_img = "";

    private String plat_form = "";

    private String gender="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_qq_imageView = (ImageView) findViewById(R.id.login_qq);
        login_sina_imageView = (ImageView) findViewById(R.id.login_sina);
        cancel_login_textview = (TextView) findViewById(R.id.cancel_login);
        cancel_login_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        login_sina_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(SHARE_MEDIA.SINA);
            }
        });





        login_qq_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(SHARE_MEDIA.QQ);
            }
        });
        umShareAPI = UMShareAPI.get(this);
        umAuthListener = new UMAuthListener() {
            @Override
            public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

                for (String key:data.keySet()){
                    Log.i("fang","获得的key"+key);
                    Log.i("fang","获得的值"+data.get(key));
                }

                if (platform==SHARE_MEDIA.SINA){
                    nickName=data.get("screen_name");
                    avatar_img=data.get("profile_image_url");
                    openid=data.get("uid");
                    plat_form="sina";
                    gender=data.get("gender");
                    updateUser(plat_form,openid,nickName,avatar_img);
                    Common.Toast(LoginActivity.this, "正在获取用户信息，请等待");
                }

                if (platform==SHARE_MEDIA.QQ&&action==2){
                    nickName=data.get("screen_name");
                    avatar_img=data.get("profile_image_url");
                    openid=data.get("openid");
                    plat_form="qq";
                    gender=data.get("gender");
                    Common.Toast(LoginActivity.this,"正在获取用户信息，请等待");
                    updateUser(plat_form, openid, nickName, avatar_img);
                }

               if (platform==SHARE_MEDIA.QQ&&action==0){
                    umShareAPI.getPlatformInfo(LoginActivity.this,platform,umAuthListener);
                }
            }

            @Override
            public void onError(SHARE_MEDIA platform, int action, Throwable t) {
                Toast.makeText(getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform, int action) {
                Toast.makeText(getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
            }
        };



    }

    private void login(final SHARE_MEDIA platform ) {
        if (SHARE_MEDIA.QQ==platform){
            umShareAPI.doOauthVerify(this, platform, umAuthListener);
        }else{
            umShareAPI.getPlatformInfo(this, platform, umAuthListener);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        umShareAPI.onActivityResult(requestCode, resultCode, data);
    }

    private void updateUser(final String plat_form, final String openid, final String nickName, final String avatar_img) {
        RequestParams requestParams = new RequestParams(RequestUrl.addUser);
        requestParams.addBodyParameter("platform", plat_form);
        requestParams.addBodyParameter("openId", openid);
        requestParams.addBodyParameter("avatar_url", avatar_img);
        requestParams.addBodyParameter("userName", nickName);
        HttpResquestUtil.postMethod(requestParams, UserInfoBean.class, new HttpCallBack<UserInfoBean>() {
            @Override
            public void success(UserInfoBean data) {
                UserBean userBean = new UserBean();
                userBean.setAvatar_url(avatar_img);
                userBean.setOpenId(openid);
                userBean.setPlatForm(plat_form);
                userBean.setUserName(nickName);
                userBean.setIsExistStuNum(data.getInfo().getIsExistStuNum());
                userBean.setIsExistTrueName(data.getInfo().getIsExistTrueName());
                userBean.setIsVerifyEmail(Integer.valueOf(data.getInfo().getIsVerifyEmail()));
                MyApplication.setCurrentUser(userBean);
                DataUtils.putPreferences("platform", plat_form);
                DataUtils.putPreferences("openId", openid);
                DataUtils.putPreferences("avatar_url", avatar_img);
                DataUtils.putPreferences("userName", nickName);
                DataUtils.putPreferences("uuid",data.getInfo().getInfo());
                DataUtils.putPreferences(PreferenceKey.isExistStuNum,data.getInfo().getIsExistStuNum());
                DataUtils.putPreferences(PreferenceKey.isExistTrueName,data.getInfo().getIsExistTrueName());
                DataUtils.putPreferences(PreferenceKey.isVerifyEmail,Integer.valueOf(data.getInfo().getIsVerifyEmail()));
                finish();
            }

            @Override
            public void erro(String res) {

            }
        });

    }

}
