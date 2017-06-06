package www.nupter.org.nupter.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import www.nupter.org.nupter.R;

public class ReadingDeatial extends BaseActivity{
    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_detail);
        initView();
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
    }

    @Override
    protected void HandleTitleBarEvent(TitleBar component, View v) {
        if (component == TitleBar.LEFT) {
            finish();
        }
    }

    public void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bun");
        String content = "<!DOCTYPE html><head>\n" +
                "\t<meta charset=\"UTF-8\">\n" +
                "\t<title>Document</title>\n" +
                "<meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0\">" +
                "\t<style type=\"text/css\">\n" +
                "img{\twidth: 100% !important;\n" +
                "      \theight: 100% !important;}" +
                "\t</style><script type=\"text/javascript\" src=\"file:///android_asset/jquery.min.js\"></script>\n" +
                "</head>" +
                "<body id='la'>\n<div id=\"content\">" + bundle.getString("content") + "</div></body><script type=\"text/javascript\">\n" +
                "\n" +
                "\t(function(){\n" +
                "\n" +
                "        var array=new Array();\n" +
                "   \n" +
                "       $(\"img\").each(function(index){\n" +
                "        $(this).attr(\"id\",index);\n" +
                "        array.push($(this).attr(\"src\"));\n" +
                "\n" +
                "       })\n" +
                "\n" +
                "       var la=array.join(\"  \");\n" +
                "                var objs = document.getElementsByTagName(\"img\");\n" +
                "                for(var i=0;i<objs.length;i++) \n" +
                "                {\n" +
                "                    objs[i].onclick=function()  \n" +
                "                  {  \n" +
                "                        window.imagelistner.openImage(this.src,this.id,la); \n" +
                "                      // alert(this.id);\n" +
                "                    }  \n" +
                "                }\n" +
                "                })()</script></html>";

        webView = (WebView) findViewById(R.id.detali_webView);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if(newProgress >= 100){
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        JavascriptInterface javascriptInterface = new JavascriptInterface();
        webView.addJavascriptInterface(javascriptInterface, "imagelistner");

        if (bundle.getString("ifReprint").equals("1")){
            String initial_url=bundle.getString("initial_url");
            webView.loadUrl(initial_url);
        }else{
            webView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
        }

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                progressBar.setVisibility(View.VISIBLE);
                return true;
            }

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.d("WebView", "onPageStarted");
                super.onPageStarted(view, url, favicon);
            }

            public void onPageFinished(WebView view, String url) {
                Log.d("WebView", "onPageFinished ");

                //      view.loadUrl("javascript:window.show.showhtml(document.body.innerHTML);");
                super.onPageFinished(view, url);
                addImageClickListner();
            }


            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                view.setVisibility(View.GONE);

            }
        });

    }

    // 注入js函数监听
    private void addImageClickListner() {
        // 这段js函数的功能就是，遍历所有的img几点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
      webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\"); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "    objs[i].onclick=function()  " +
                "    {  "
                + "        window.imagelistner.openImage(this.src,i);  " +
                "    }  " +
                "}" +
                "})()");

    }

    class JavascriptInterface {
        @android.webkit.JavascriptInterface
        public void openImage(String img, String i, String la) {
            System.out.println(img);
            // Toast.makeText(ReadingDeatial.this, img + "$" + i+"$"+la, Toast.LENGTH_SHORT).show();
            Log.i("fang", img + "$" + i + "$" + la);
            Intent intent = new Intent();
            intent.putExtra("image", img);
            intent.putExtra("i", i);
            intent.putExtra("url_array", la);
            intent.setClass(ReadingDeatial.this, ImageDetail.class);
            startActivity(intent);

        }
    }


}
