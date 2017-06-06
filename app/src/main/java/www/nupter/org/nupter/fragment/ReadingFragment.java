package www.nupter.org.nupter.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import www.nupter.org.nupter.R;
import www.nupter.org.nupter.activity.ReadingDeatial;
import www.nupter.org.nupter.adapter.CommonAdapter;
import www.nupter.org.nupter.adapter.ReadingAdapter;
import www.nupter.org.nupter.http.HttpCallBack;
import www.nupter.org.nupter.http.HttpResquestUtil;
import www.nupter.org.nupter.http.RequestUrl;
import www.nupter.org.nupter.model.ReadingBean;
import www.nupter.org.nupter.utils.Common;
import www.nupter.org.nupter.utils.ViewHolder;
import www.nupter.org.nupter.view.MyBanner;
import www.nupter.org.nupter.view.NoScrollListview;
import www.nupter.org.nupter.widget.XScrollView;


public class ReadingFragment extends Fragment implements XScrollView.IXScrollViewListener {

    private ListView mListView;
    private XScrollView mScrollView;

    private ReadingAdapter readingAdapter;

    private List<ReadingBean.InfoEntity>infoEntities;

    private int start = 0;
    private int number = 5;

    public ReadingFragment() {
        super();
    }


    private MyBanner myBanner;

    private String[] imageUrls = {"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
            "http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reading, container, false);
        mScrollView = (XScrollView) view.findViewById(R.id.scroll_view);
        infoEntities=new ArrayList<>();
        View content = LayoutInflater.from(getActivity()).inflate(R.layout.viewscrope_content_reading, null);
        mListView = (ListView) content.findViewById(R.id.reading_list);
        myBanner = (MyBanner) content.findViewById(R.id.reading_mybanner);
        myBanner.setData(imageUrls);
        myBanner.setAutoScrool(true);
        mListView.setFocusable(false);
        mListView.setFocusableInTouchMode(false);

        mScrollView.setIXScrollViewListener(this);
        mScrollView.setPullRefreshEnable(true);
        mScrollView.setPullLoadEnable(true);
        mScrollView.setView(content);

        RequestParams requestParams = new RequestParams(RequestUrl.reading);
        requestParams.addBodyParameter("start", start + "");
        requestParams.addBodyParameter("number", number + "");
        HttpResquestUtil.postMethod(requestParams, ReadingBean.class, new HttpCallBack<ReadingBean>() {
            @Override
            public void success(ReadingBean data) {
                start=1;
                infoEntities.addAll(data.getInfo());
                readingAdapter = new ReadingAdapter(infoEntities, getActivity());
                mListView.setAdapter(readingAdapter);
                measureHeight();
            }

            @Override
            public void erro(String res) {
                Log.i("fang", res);
            }
        });

        InItVIEW();

        myBanner.setOnImagelistenner(new MyBanner.OnImagelistenner() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(), "lalllla" + position, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public void InItVIEW() {

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("ifReprint",infoEntities.get(position).getIfReprint());
                bundle.putString("content", infoEntities.get(position).getEditorValue());
                bundle.putString("title",infoEntities.get(position).getTitle());
                bundle.putString("initial_url",infoEntities.get(position).getInitial_url());
                Intent intent = new Intent();
                intent.putExtra("bun", bundle);
                intent.setClass(getActivity(), ReadingDeatial.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onRefresh() {


    }

    @Override
    public void onLoadMore() {
        RequestParams requestParams = new RequestParams(RequestUrl.reading);
        requestParams.addBodyParameter("start", start * number + "");
        requestParams.addBodyParameter("number",number + "");
        HttpResquestUtil.postMethod(requestParams, ReadingBean.class, new HttpCallBack<ReadingBean>() {
            @Override
            public void success(ReadingBean data) {
                start++;
                onLoad();
                infoEntities.addAll(data.getInfo());
                readingAdapter.notifyDataSetChanged();
                measureHeight();
            }
            @Override
            public void erro(String res) {
                Common.Toast(getActivity(),"没有更多数据了");
            }
        });

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
        params.height = totalHeight + ((mListView.getDividerHeight()+25) * (adapter.getCount() - 1));

        mListView.setLayoutParams(params);

        return params.height;
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
