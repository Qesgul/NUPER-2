package www.nupter.org.nupter.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;
import org.xutils.http.RequestParams;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import www.nupter.org.nupter.R;
import www.nupter.org.nupter.activity.LostDetailActivity;
import www.nupter.org.nupter.activity.PublishLostActivity;
import www.nupter.org.nupter.adapter.CommonAdapter;
import www.nupter.org.nupter.http.HttpCallBack;
import www.nupter.org.nupter.http.HttpResquestUtil;
import www.nupter.org.nupter.http.RequestUrl;
import www.nupter.org.nupter.model.LostBean;
import www.nupter.org.nupter.utils.ViewHolder;
import www.nupter.org.nupter.view.ReactImageView;
import www.nupter.org.nupter.widget.XListView;
/**
 * A simple {@link Fragment} subclass.
 */
public class LostFragment extends Fragment {
    private CommonAdapter<LostBean.InfoEntity> commonAdapter;
    private XListView mListView;
    private List<LostBean.InfoEntity> lostList;
    ImageButton btnpublish;
    private int num = 5;
    private int start = 0;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lost, container, false);
        mListView = (XListView) view.findViewById(R.id.list_view);
        lostList = new ArrayList<>();
        btnpublish = (ImageButton) view.findViewById(R.id.btnpublish);
        btnpublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PublishLostActivity.class);
                startActivity(intent);
            }
        });
        final RequestParams requestParams = new RequestParams(RequestUrl.lost);
        requestParams.addBodyParameter("start", start + "");
        requestParams.addBodyParameter("number", num + "");
        HttpResquestUtil.postMethod(requestParams, LostBean.class, new HttpCallBack<LostBean>() {
            @Override
            public void success(LostBean data) {
                start++;
                commonAdapter = new CommonAdapter<LostBean.InfoEntity>(getActivity(), data.getInfo(), R.layout.adapter_lost) {
                    @Override
                    public void convert(ViewHolder holder, LostBean.InfoEntity item, int position) {
                        TextView titleText = holder.getView(R.id.title);
                        TextView siteText = holder.getView(R.id.site);
                        TextView dateText = holder.getView(R.id.date);
                        TextView timeText = holder.getView(R.id.time);
                        ReactImageView reactImageView = holder.getView(R.id.avatar_image);
                        titleText.setText(item.getDescription());
                        siteText.setText(item.getSite());
                        dateText.setText(getTime(item.getCreateTime()));
                        timeText.setText(item.getTime());
                        HttpResquestUtil.initImage(reactImageView, item.getAvatar_url());
                    }
                };
                mListView.setAdapter(commonAdapter);
            }
            @Override
            public void erro(String res) {

            }
        });
        mListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                  requestData(false);
            }
            @Override
            public void onLoadMore() {
                 requestData(true);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent();
                intent.setClass(getActivity(), LostDetailActivity.class);
                intent.putExtra("lost",commonAdapter.getmDatas().get(i-1));
                startActivity(intent);
            }
        });

        return view;
    }
    private void requestData(final boolean isMore) {
        RequestParams requestParams = new RequestParams(RequestUrl.lost);
        requestParams.addBodyParameter("start", start*num + "");
        requestParams.addBodyParameter("number", num + "");
        HttpResquestUtil.postMethod(requestParams, LostBean.class, new HttpCallBack<LostBean>() {
            @Override
            public void success(LostBean data) {
                start++;
                if (isMore) {
                    commonAdapter.getmDatas().addAll(data.getInfo());
                } else {
                    commonAdapter.getmDatas().clear();
                    commonAdapter.getmDatas().addAll(data.getInfo());
                }
                commonAdapter.notifyDataSetChanged();
            }
            @Override
            public void erro(String res) {

            }
        });
    }
    private String getTime(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
        Date date = null;
        try {
            date = formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter.format(date);
    }
}
