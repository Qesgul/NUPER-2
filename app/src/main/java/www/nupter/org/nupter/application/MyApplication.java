package www.nupter.org.nupter.application;

import android.app.Application;
import android.util.ArraySet;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.qiniu.android.common.Zone;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;
import com.squareup.picasso.Picasso;
import com.umeng.socialize.PlatformConfig;

import org.xutils.x;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import www.nupter.org.nupter.model.UserBean;
import www.nupter.org.nupter.utils.DataUtils;
import www.nupter.org.nupter.utils.PreferenceKey;

/**
 * Created by fangzhenyi on 15/11/9.
 */
public class MyApplication extends Application {
    private String TAG="APPLICATION";
    private static MyApplication mInstance;
    private static UserBean currentUser;
    private Set<String>tag=new HashSet<>();
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        x.Ext.init(this);
        x.Ext.setDebug(true);
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);
        tag.add("pushScreen");
        JPushInterface.setTags(this, tag, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                switch (i) {
                    case 0:
                        Log.i(TAG, "设置标签成功");
                        break;
                    case 6002:
                        Log.i(TAG, "超时了");
                        break;
                }
            }
        });

        JPushInterface.setAlias(this, "1", new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                Log.i(TAG, "alia设置成功");
            }
        });

        UserBean userBean = new UserBean();
        userBean.setAvatar_url(DataUtils.getPreferences("avatar_url", null));
        userBean.setOpenId(DataUtils.getPreferences("openId", null));
        userBean.setPlatForm(DataUtils.getPreferences("platform", null));
        userBean.setUserName(DataUtils.getPreferences("userName", null));
        userBean.setUuid(DataUtils.getPreferences("uuid",null));

        if (userBean.getOpenId()!=null){
            MyApplication.setCurrentUser(userBean);
        }


        com.nostra13.universalimageloader.core.ImageLoader imageLoader= com.nostra13.universalimageloader.core.ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));

//        Configuration config = new Configuration.Builder()
//                .chunkSize(256 * 1024)  //分片上传时，每片的大小。 默认 256K
//                .putThreshhold(512 * 1024)  // 启用分片上传阀值。默认 512K
//                .connectTimeout(10) // 链接超时。默认 10秒
//                .responseTimeout(60) // 服务器响应超时。默认 60秒
//                .recorder(recorder)  // recorder 分片上传时，已上传片记录器。默认 null
//                .recorder(recorder, keyGen)  // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
//                .zone(Zone.zone0) // 设置区域，指定不同区域的上传域名、备用域名、备用IP。默认 Zone.zone0
//                .build();
//// 重用 uploadManager。一般地，只需要创建一个 uploadManager 对象
//        UploadManager uploadManager = new UploadManager(config);


        PlatformConfig.setSinaWeibo(Config.weibo_appID, Config.weibo_appKey);
        //新浪微博 appkey appsecret
        PlatformConfig.setQQZone(Config.qq_appID, Config.qq_appKey);

    }

    public static UserBean getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(UserBean currentUser) {
        MyApplication.currentUser = currentUser;
    }

    public static void quitUser(){
        MyApplication.currentUser=null;
        DataUtils.putPreferences("platform","");
        DataUtils.putPreferences("openId", "");
        DataUtils.putPreferences("avatar_url", "");
        DataUtils.putPreferences("userName", "");
        DataUtils.putPreferences("uuid","");
        DataUtils.putPreferences(PreferenceKey.isExistStuNum,0);
        DataUtils.putPreferences(PreferenceKey.isExistTrueName,0);
        DataUtils.putPreferences(PreferenceKey.isVerifyEmail,Integer.valueOf("0"));
    }

    public static  void initImage(ImageView imageView, String imageUrl) {
        Picasso.with(getContext()).load(imageUrl).into(imageView);
    }

    public static void initLocalImage(ImageView imageView,String path){
        Picasso.with(getContext()).load(new File(path)).resize(200, 200).centerCrop().into(imageView);
    }

    public static MyApplication getContext() {
        return mInstance;
    }
}
