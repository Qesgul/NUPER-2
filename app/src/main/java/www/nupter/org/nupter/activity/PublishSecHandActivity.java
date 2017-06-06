package www.nupter.org.nupter.activity;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.http.RequestParams;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import www.nupter.org.nupter.R;
import www.nupter.org.nupter.http.HttpCallBack;
import www.nupter.org.nupter.http.HttpResquestUtil;
import www.nupter.org.nupter.http.RequestUrl;
import www.nupter.org.nupter.model.BaseBean;

public class PublishSecHandActivity extends AppCompatActivity {
    TextView txtType;
    ImageButton btnAddImg;
    ImageView imageView[];
    Button btnpublish;
    String imgurl[];
    int imgNum = 0;
    String token;
    int completNum=0;
    ArrayList<String> keys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_sec_hand);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        txtType = (TextView)findViewById(R.id.textViewtype);

        imageView = new ImageView[3];
        imgurl = new String[]{"","",""};
        keys = new ArrayList<>();

        imageView[0] = (ImageView)findViewById(R.id.imageView1);
        imageView[1] = (ImageView)findViewById(R.id.imageView2);
        imageView[2] = (ImageView)findViewById(R.id.imageView3);

        RequestParams requestParams = new RequestParams(RequestUrl.uploadtoken);
        requestParams.addBodyParameter("nupter","android");
        HttpResquestUtil.postMethod(requestParams, BaseBean.class, new HttpCallBack<BaseBean>() {
            @Override
            public void success(BaseBean data) {


                token = data.getInfo().getInfo();

                Log.e("token", token);


            }

            @Override
            public void erro(String res) {

            }
        });
        btnAddImg = (ImageButton)findViewById(R.id.btnaddimg);
        btnpublish = (Button)findViewById(R.id.buttonpublish);

        btnAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PublishSecHandActivity.this);
                //builder.setIcon(R.drawable.ic_launcher);
                //builder.setTitle("选择一个城市");
                //    指定下拉列表的显示数据
                final String[] choices = {"添加图片", "删除图片"};
                //    设置一个下拉的列表选择项
                builder.setItems(choices, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                if (imgNum < 3) {
                                    Intent intent = new Intent();
                                 /* 开启Pictures画面Type设定为image */
                                    intent.setType("image/*");
                                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                                    intent.setAction(Intent.ACTION_GET_CONTENT);
                                /* 取得相片后返回本画面 */
                                    startActivityForResult(intent, 1);
                                }
                                break;
                            case 1:
                                if (imgNum > 0) {

                                    imgNum--;
                                    imgurl[imgNum] = "";
                                    imageView[imgNum].setImageDrawable(getResources().getDrawable(R.color.transparent));

                                }
                                break;

                        }
                    }
                });
                builder.show();

            }
        });

        txtType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PublishSecHandActivity.this);
                //builder.setIcon(R.drawable.ic_launcher);
                //builder.setTitle("选择一个城市");
                //    指定下拉列表的显示数据
                final String[] choices = {"电子产品", "书籍","其他"};
                //    设置一个下拉的列表选择项
                builder.setItems(choices, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        txtType.setText(choices[which]);
//                        switch (which) {
//                            case 0:
//                                break;
//                            case 1:
//                                break;
//                            default:
//                                break;
//                        }
                    }
                });
                builder.show();

            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();


            Log.e("uri", uri.toString());
            // imgurl=uri.toString();
            ContentResolver cr = this.getContentResolver();
            String[] proj = {MediaStore.Images.Media.DATA};

            //好像是android多媒体数据库的封装接口，具体的看Android文档

            Cursor cursor = managedQuery(uri, proj, null, null, null);

            //按我个人理解 这个是获得用户选择的图片的索引值

            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

            //将光标移至开头 ，这个很重要，不小心很容易引起越界

            cursor.moveToFirst();

            //最后根据索引值获取图片路径

            String path = cursor.getString(column_index);
            imgurl[imgNum]=path;

            try {
                //imageView 显示缩略图的ImageView
                //imageView.setImageBitmap(bitmap);
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));

                bitmap = ThumbnailUtils.extractThumbnail(bitmap, 150, 150);//利用Bitmap对象创建缩略图
                /* 将Bitmap设定到ImageView */

                imageView[imgNum].setImageBitmap(bitmap);
                imgNum++;
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(), e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
