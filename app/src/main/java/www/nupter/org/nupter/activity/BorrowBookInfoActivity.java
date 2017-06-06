package www.nupter.org.nupter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.xutils.http.RequestParams;

import java.util.regex.Matcher;

import www.nupter.org.nupter.R;
import www.nupter.org.nupter.adapter.CommonAdapter;
import www.nupter.org.nupter.http.HttpCallBack;
import www.nupter.org.nupter.http.HttpResquestUtil;
import www.nupter.org.nupter.http.RequestUrl;
import www.nupter.org.nupter.model.BorrowBookInfo;
import www.nupter.org.nupter.model.ReBookBean;
import www.nupter.org.nupter.utils.DataUtils;
import www.nupter.org.nupter.utils.DateUtils;
import www.nupter.org.nupter.utils.PreferenceKey;
import www.nupter.org.nupter.utils.ViewHolder;

public class BorrowBookInfoActivity extends BaseActivity {

    private ListView listView;

    private TextView stuText;

    private TextView book_num;

    private BorrowBookInfo borrowBookInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_book_info);
        setTitle("我的借阅");
        stuText = (TextView) findViewById(R.id.stuNum);
        listView = (ListView) findViewById(R.id.book_list);
        book_num = (TextView) findViewById(R.id.book_num);
        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("borrow");
         borrowBookInfo = (BorrowBookInfo) bundle.getSerializable("borrow");
        stuText.setText(DataUtils.getPreferences(PreferenceKey.stuNum, ""));
        MyAdapter myAdapter = new MyAdapter(borrowBookInfo);
        listView.setAdapter(myAdapter);
        book_num.setText(String.valueOf(borrowBookInfo.getInfo().getName().size()));

    }

    @Override
    protected void HandleTitleBarEvent(TitleBar component, View v) {
        if (component == TitleBar.LEFT) {
            finish();
        }
    }

    private class MyAdapter extends BaseAdapter {
        private BorrowBookInfo borrowBookInfo;
        private LayoutInflater layoutInflater;

        public MyAdapter(BorrowBookInfo borrowBookInfo) {
            this.borrowBookInfo = borrowBookInfo;
            layoutInflater = LayoutInflater.from(BorrowBookInfoActivity.this);
        }

        @Override
        public int getCount() {
            return borrowBookInfo.getInfo().getName().size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = layoutInflater.inflate(R.layout.borrow_book_info_item, parent, false);
            TextView textNameView = (TextView) convertView.findViewById(R.id.book_name);
            TextView textDate = (TextView) convertView.findViewById(R.id.date);
            TextView borrowText = (TextView) convertView.findViewById(R.id.borrow);
            TextView overTimeText = (TextView) convertView.findViewById(R.id.time);
            textNameView.setText(Html.fromHtml(borrowBookInfo.getInfo().getName().get(position)));
            final String date1 = borrowBookInfo.getInfo().getDate1().get(position).trim();
            String date2 = borrowBookInfo.getInfo().getDate2().get(position).trim();
            textDate.setText(Html.fromHtml(date1 + "/" + date2));

            int overTime = DateUtils.OverTime(date2);

            if (overTime == 200) {
                overTimeText.setText("超期");
            } else {
                overTimeText.setText("余" + overTime + "天");
            }


            borrowText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    RequestParams requestParams = new RequestParams(RequestUrl.borrowBook);
                    requestParams.addBodyParameter("cookie", borrowBookInfo.getInfo().getCookie());
                    requestParams.addBodyParameter("check", borrowBookInfo.getInfo().getCheck().get(position).trim());
                    requestParams.addBodyParameter("barCode",borrowBookInfo.getInfo().getBarcode().get(position).trim());

                    HttpResquestUtil.postMethod(requestParams, ReBookBean.class, new HttpCallBack<ReBookBean>() {
                        @Override
                        public void success(ReBookBean data) {
                            BaseToast(data.getInfo().getInfo());
                        }

                        @Override
                        public void erro(String res) {

                        }
                    });

                }
            });
            return convertView;
        }
    }
}
