package www.nupter.org.nupter.http;


import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.squareup.picasso.Picasso;

import junit.framework.Test;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import www.nupter.org.nupter.application.MyApplication;

/**
 * Created by fangzhenyi on 15/11/10.
 */
public class HttpResquestUtil {
    public static final String ERROR_STATUS = "error";
    public static final String SUCESS_STATUS = "success";

    public static <T> void getMethod(RequestParams requestParams, final Class<T> bean, final HttpCallBack<T> httpCallBack) {
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String status = jsonObject.getString("status");

                    if (status.equals(ERROR_STATUS)) {
                        httpCallBack.erro(jsonObject.getString("info"));
                    } else {
                        Gson gson = new Gson();
                        T t = gson.fromJson(result, bean);
                        httpCallBack.success(t);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
    }

    public static <T> void postMethod(RequestParams requestParams, final Class<T> bean, final HttpCallBack<T> httpCallBack) {
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println(ex.toString());

                httpCallBack.erro("网络错误");
            }

            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    Log.e("post return info", jsonObject.toString());
                    String status = jsonObject.getString("status");
                    if (status.equals(ERROR_STATUS)) {
                        httpCallBack.erro(jsonObject.getString("info"));
                    } else {
                        Gson gson = new Gson();
                        T t = gson.fromJson(result, bean);
                        httpCallBack.success(t);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    public static void getPostString(RequestParams requestParams, final HttpCallBack<String> httpCallBack) {
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                // Log.i("网络请求数据",result);
                httpCallBack.success(result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    Log.e("post return info", jsonObject.toString());
                    String status = jsonObject.getString("status");
                    if (status.equals(ERROR_STATUS)) {
                        httpCallBack.erro(jsonObject.getString("info"));
                    } else {
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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

    }

    public static void initImage(ImageView imageView, String imageUrl) {


       /* ImageOptions imageOptions = new ImageOptions.Builder()
                // 加载中或错误图片的ScaleType
                //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                // 默认自动适应大小
                // .setSize(...)
                .setIgnoreGif(false)
                .setImageScaleType(ImageView.ScaleType.MATRIX).build();
        x.image().bind(imageView, imageUrl);*/

       MyApplication.initImage(imageView,imageUrl);



      /*  ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .resetViewBeforeLoading(true)
                .cacheOnDisc(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(300))
                .build();
        imageLoader.displayImage(imageUrl, imageView, options);*/
    }


    public static void initLocalImage(ImageView imageView, String path) {
        MyApplication.initLocalImage(imageView, path);
    }

}
