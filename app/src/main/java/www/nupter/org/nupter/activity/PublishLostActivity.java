package www.nupter.org.nupter.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import www.nupter.org.nupter.R;
import www.nupter.org.nupter.application.MyApplication;
import www.nupter.org.nupter.fragment.PublishLostFirstFragment;
import www.nupter.org.nupter.fragment.PublishLostSecondFragment;
import www.nupter.org.nupter.http.HttpCallBack;
import www.nupter.org.nupter.http.HttpResquestUtil;
import www.nupter.org.nupter.http.RequestUrl;
import www.nupter.org.nupter.model.BasesBean;
import www.nupter.org.nupter.model.UserBean;
import www.nupter.org.nupter.utils.Common;
import www.nupter.org.nupter.utils.PictureUtil;


public class PublishLostActivity extends BaseActivity {


    private Fragment currentFragment;

    private PublishLostFirstFragment publishFirstFragment;

    private PublishLostSecondFragment publishSecondFragment;

    private boolean isFirst = true;


    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_lost);
        setCenterTitle("编辑失物");
        setRightText("下一项");

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("正在努力加载图片，请等待");

        BaseToast("亲，请填写你的描述信息。");

        if (publishFirstFragment == null) {
            publishFirstFragment = new PublishLostFirstFragment();
        }

        if (!publishFirstFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, publishFirstFragment).commit();
        }
        currentFragment = publishFirstFragment;


    }


    private void addOrShowFragment(FragmentTransaction transaction,
                                   Fragment fragment) {


        if (currentFragment == fragment)
            return;
        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            // transaction.setCustomAnimations(R.anim.fragment_slide_in_from_left, R.anim.fragment_slide_out_to_right);
            transaction.hide(currentFragment).add(R.id.container, fragment).commit();
        } else {
            //  transaction.setCustomAnimations(R.anim.fragment_slide_in_from_left, R.anim.fragment_slide_out_to_right);
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }


    @Override
    protected void HandleTitleBarEvent(TitleBar component, View v) {
        if (component == TitleBar.LEFT) {
            if (isFirst) {
                finish();
            } else {
                if (publishFirstFragment == null) {
                    publishFirstFragment = new PublishLostFirstFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), publishFirstFragment);
                setRightText("下一页");
                isFirst = true;
            }

        } else if (component == TitleBar.RightText) {

            if (isFirst) {
                if (publishFirstFragment.getDesString().trim().equals("")) {
                    // publishFirstFragment.showToast("亲，请填写你的描述信息。");
                    BaseToast("亲，请填写你的描述信息。");
                    return;
                }
                if (publishSecondFragment == null) {
                    publishSecondFragment = new PublishLostSecondFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), publishSecondFragment);
                isFirst = false;
                setRightText("完成");
            } else {

                if (publishSecondFragment.getName().equals("") || publishSecondFragment.getSite().equals("") || publishSecondFragment.getPhone().equals("") || publishSecondFragment.getTime().equals("")) {
                    BaseToast("亲，请把信息填写完整");
                    return;
                };

                progressDialog.show();

                RequestParams requestParams = new RequestParams(RequestUrl.getQiniu);
                requestParams.addBodyParameter("nupter", "nupter");

                HttpResquestUtil.getPostString(requestParams, new HttpCallBack<String>() {
                    @Override
                    public void success(String data) {

                        String token = "";

                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            token = jsonObject.getString("token");
                            Log.i("FANG", "取得的token值" + token);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        UploadManager uploadManager = new UploadManager();

                        final List<String> imageUrl = publishFirstFragment.getImageUrls();

                        final String des = publishFirstFragment.getDesString();

                        final String name = publishSecondFragment.getName();

                        final String phone = publishSecondFragment.getName();

                        final String site = publishSecondFragment.getSite();

                        final String time = publishSecondFragment.getTime();


                        final String[] keyArray = new String[imageUrl.size()];


                        if (imageUrl.size() == 0) {
                            insertLost(des, name, phone, site, time, keyArray);
                        } else {

                            for (int i = 0; i < imageUrl.size(); i++) {
                                final String key = "lostandfound/" + MyApplication.getCurrentUser().getUuid() + "&" + Common.getTime() + "&" + i + ".jpg";

                                Log.i("fang", "图片" + key);

                                byte[] pic = PictureUtil.getSmallBitmap(imageUrl.get(i));


                                final int l = i;
                                uploadManager.put(pic, key, token, new UpCompletionHandler() {
                                    @Override
                                    public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
                                        keyArray[l] = key;
                                        if (l == imageUrl.size() - 1) {
                                            insertLost(des, name, phone, site, time, keyArray);
                                        }

                                    }
                                }, null);
                            }


                        }

                    }

                    @Override
                    public void erro(String res) {

                    }
                });


            }
        }


    }

    public void insertLost(String des, String name, String phone, String site, String time, String[] imageUrls) {

        RequestParams requestParams = new RequestParams(RequestUrl.insertLost);
        UserBean userBean = MyApplication.getCurrentUser();


        requestParams.addBodyParameter("uuid", userBean.getUuid());
        requestParams.addBodyParameter("description", des);
        requestParams.addBodyParameter("name", name);
        requestParams.addBodyParameter("phone", phone);
        requestParams.addBodyParameter("site", site);
        requestParams.addBodyParameter("time", time);

        if (imageUrls.length == 0) {
            requestParams.addBodyParameter("image_url1", "");
            requestParams.addBodyParameter("image_url2", "");
            requestParams.addBodyParameter("image_url3", "");
        } else if (imageUrls.length == 1) {
            requestParams.addBodyParameter("image_url1", imageUrls[0]);
            requestParams.addBodyParameter("image_url2", "");
            requestParams.addBodyParameter("image_url3", "");
        } else if (imageUrls.length == 2) {
            requestParams.addBodyParameter("image_url1", imageUrls[0]);
            requestParams.addBodyParameter("image_url2", imageUrls[1]);
            requestParams.addBodyParameter("image_url3", "");
        } else if (imageUrls.length == 3) {
            requestParams.addBodyParameter("image_url1", imageUrls[0]);
            requestParams.addBodyParameter("image_url2", imageUrls[1]);
            requestParams.addBodyParameter("image_url3", imageUrls[2]);
        }

        HttpResquestUtil.postMethod(requestParams, BasesBean.class, new HttpCallBack<BasesBean>() {
            @Override
            public void success(BasesBean data) {

                BaseToast("发布成功");
                finish();

            }

            @Override
            public void erro(String res) {

            }
        });


    }

}
