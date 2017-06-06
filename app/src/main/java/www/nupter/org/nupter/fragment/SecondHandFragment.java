package www.nupter.org.nupter.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import org.xutils.http.RequestParams;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import www.nupter.org.nupter.R;
import www.nupter.org.nupter.activity.PublishSecHandActivity;
import www.nupter.org.nupter.activity.ReadingDeatial;
import www.nupter.org.nupter.activity.SeconHandActivity;
import www.nupter.org.nupter.adapter.CommonAdapter;
import www.nupter.org.nupter.http.HttpCallBack;
import www.nupter.org.nupter.http.HttpResquestUtil;
import www.nupter.org.nupter.http.RequestUrl;
import www.nupter.org.nupter.model.ReadingBean;
import www.nupter.org.nupter.model.SecondHandBean;
import www.nupter.org.nupter.utils.ViewHolder;
import www.nupter.org.nupter.view.NoScrollListview;
import www.nupter.org.nupter.widget.XScrollView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondHandFragment extends Fragment implements XScrollView.IXScrollViewListener{

   // private NoScrollListview mylist;
    private CommonAdapter<SecondHandBean.InfoEntity> commonAdapter;
    private SecondHandBean secondHandBean;

   // private PullToRefreshScrollView scrollView;

    private ListView mListView;
    private XScrollView mScrollView;

    ImageButton btnPublish;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second_hand, container, false);

        mScrollView = (XScrollView) view.findViewById(R.id.scroll_view);
        View content = LayoutInflater.from(getActivity()).inflate(R.layout.viewscrope_content_sechand, null);
        mListView = (ListView) content.findViewById(R.id.sechandlist);
        mScrollView.setView(content);
        mScrollView.setIXScrollViewListener(this);
        RequestParams requestParams = new RequestParams(RequestUrl.secondHand);
        btnPublish = (ImageButton)view.findViewById(R.id.btnpublish);
        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PublishSecHandActivity.class);
                startActivity(intent);
            }
        });
        requestParams.addBodyParameter("start", "0");
        requestParams.addBodyParameter("number", "10");
        HttpResquestUtil.postMethod(requestParams, SecondHandBean.class, new HttpCallBack<SecondHandBean>() {
            @Override
            public void success(SecondHandBean data) {
              Log.e("fang", data.getInfo().get(0).getDescription());
                secondHandBean = data;
                commonAdapter = new CommonAdapter<SecondHandBean.InfoEntity>(getActivity(), data.getInfo(), R.layout.adapter_secondhand) {
                    @Override
                    public void convert(ViewHolder holder, SecondHandBean.InfoEntity item, int position) {
                        TextView txttype = holder.getView(R.id.textType);
                        txttype.setText(item.getTag());
                        TextView txtTitle = holder.getView(R.id.textViewtitle);
                        txtTitle.setText(item.getTitle());
                        ImageView imgMain = holder.getView(R.id.imageViewmain);
                        HttpResquestUtil.initImage(imgMain,RequestUrl.baseimgurl+item.getImage_url1()+"-suolue");
                        ImageView avatar = holder.getView(R.id.imageViewavatar);
                        HttpResquestUtil.initImage(avatar,item.getAvatar_url());
                        TextView txtusrname = holder.getView(R.id.textViewname);
                        TextView txttime = holder.getView(R.id.textViewtime);
                        TextView txtmoney = holder.getView(R.id.textViewmoney);
                        txtmoney.setText(item.getPrice());
                        txtusrname.setText(item.getUserName());
                        txttime.setText(item.getCreateTime());

                    }


                };
                mListView.setAdapter(commonAdapter);
                measureHeight();
            }

            @Override
            public void erro(String res) {
                Log.e("fang",res);
            }
        });
        InItVIEW();
        return view;

    }
    public void InItVIEW() {

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("sec",  secondHandBean.getInfo().get(position));
//                bundle.putString("content", secondHandBean.getInfo().get(position).getContent());
//                bundle.putString("title", readingBean.getInfo().get(position).getTitle());
                Intent intent = new Intent();
                intent.putExtra("bun", bundle);
                intent.setClass(getActivity(),SeconHandActivity.class);
                startActivity(intent);
            }
        });
//        scrollView.setMode(PullToRefreshBase.Mode.BOTH);
//        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
//            @Override
//            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//
//                requestRefresh(0,10);
//            }
//
//            @Override
//            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                requestRefresh(mylist.getAdapter().getCount(),10);
//
//            }
//        });

    }
    private int measureHeight() {
        // get ListView adapter
        ListAdapter adapter = mListView.getAdapter();
        if (null == adapter) {
            return 0;
        }

        int totalHeight = 0;

        for (int i = 0, len = adapter.getCount(); i < len; i++) {
            View item = adapter.getView(i, null, mListView);
            if (null == item) continue;
            // measure each item width and height
            item.measure(0, 0);
            // calculate all height
            totalHeight += item.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = mListView.getLayoutParams();

        if (null == params) {
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        // calculate ListView height
        params.height = totalHeight + (mListView.getDividerHeight() * (adapter.getCount() - 1));

        mListView.setLayoutParams(params);

        return params.height;
    }
    private void requestRefresh(final int start,int num){
        RequestParams requestParams = new RequestParams(RequestUrl.secondHand);
        requestParams.addBodyParameter("start", String.valueOf(start));
        requestParams.addBodyParameter("number", String.valueOf(num));
        HttpResquestUtil.postMethod(requestParams, SecondHandBean.class, new HttpCallBack<SecondHandBean>() {
            @Override
            public void success(SecondHandBean data) {
               // Log.e("fang", data.getInfo().get(0).getDescription());
               // secondHandBean = data;
                List<SecondHandBean.InfoEntity> list = commonAdapter.getmDatas();
           //     commonAdapter.setData(list);
                if (start>0){
                    for (SecondHandBean.InfoEntity entity : data.getInfo()){
                        list.add(entity);
                    }
                    commonAdapter.setData(list);
                }else {

                    commonAdapter.setData(data.getInfo());
                }


                commonAdapter.notifyDataSetChanged();
                measureHeight();
                onLoad();



            }

            @Override
            public void erro(String res) {
                Log.e("fang", res);
            }
        });
    }


    @Override
    public void onRefresh() {
        requestRefresh(0, 10);
    }

    @Override
    public void onLoadMore() {
        requestRefresh(mListView.getCount(),10);
    }
    private void onLoad() {
        mScrollView.stopRefresh();
        mScrollView.stopLoadMore();
        mScrollView.setRefreshTime(getTime());
    }
    private String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }
}
