package www.nupter.org.nupter.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import www.nupter.org.nupter.R;
import www.nupter.org.nupter.activity.PublishLostActivity;
import www.nupter.org.nupter.http.HttpResquestUtil;


public class PublishLostFirstFragment extends Fragment {
    private GridView gridView;

    private static final int REQUEST_IMAGE = 2;

    private List<String> imgUrls = new ArrayList<>();

    private MyAdapter myAdapter;

    private TextView textInfoView;

    private EditText editTextDes;


    private String des;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_publish_lost_first, container, false);
        gridView = (GridView) view.findViewById(R.id.gridview);
        textInfoView = (TextView) view.findViewById(R.id.info_text);
        editTextDes = (EditText) view.findViewById(R.id.des);
        editTextDes.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return false;
            }
        });
        myAdapter = new MyAdapter();
        gridView.setAdapter(myAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("fang", "item" + i);

                switch (imgUrls.size()) {
                    case 0:
                        if (i == 0) {
                            startActivity();
                        }
                        break;
                    case 1:
                        if (i == 1) {
                            startActivity();
                        }
                        break;
                    case 2:
                        if (i == 2) {
                            startActivity();
                        }
                        break;
                }
            }
        });


        return view;
    }


    public void startActivity() {
        Intent intent = new Intent(getActivity(), MultiImageSelectorActivity.class);
        // 是否显示拍摄图片
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        // 选择模式
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_SINGLE);

        startActivityForResult(intent, REQUEST_IMAGE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == -1) {
                String la = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT).get(0);
                if (!imgUrls.contains(la))
                    imgUrls.add(la);
                myAdapter.notifyDataSetChanged();
                textInfoView.setVisibility(View.GONE);
            }
        }

    }



    public String getDesString(){
        des=editTextDes.getText().toString();
        return des;
    }


    public List<String> getImageUrls(){
        return imgUrls;
    }


    public class MyAdapter extends BaseAdapter {
        private LayoutInflater layoutInflater;

        public MyAdapter() {
            layoutInflater = LayoutInflater.from(getActivity());
        }

        @Override
        public int getCount() {
            if (imgUrls.size() <= 2)
                return 1 + imgUrls.size();
            else
                return 3;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = layoutInflater.inflate(R.layout.gridview_item, viewGroup, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.img);
            if (imgUrls.size() == 0) {
                imageView.setImageResource(R.drawable.icon_camera);
            } else {
                if (imgUrls.size() <= 2) {
                    if (i == imgUrls.size()) {
                        imageView.setImageResource(R.drawable.icon_camera);
                    } else {
                        HttpResquestUtil.initLocalImage(imageView, imgUrls.get(i));
                    }
                } else {
                    Log.i("fang", imgUrls.get(i));
                    HttpResquestUtil.initLocalImage(imageView, imgUrls.get(i));
                }
            }

            return view;
        }
    }
}
