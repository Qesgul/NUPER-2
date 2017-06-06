package www.nupter.org.nupter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import www.nupter.org.nupter.R;
import www.nupter.org.nupter.adapter.CommonAdapter;
import www.nupter.org.nupter.model.BookInfoBean;
import www.nupter.org.nupter.utils.Common;
import www.nupter.org.nupter.utils.ViewHolder;
import www.nupter.org.nupter.view.NoScrollListview;

public class BookInfoActivity extends BaseActivity {


    private TextView bookNameText;

    private TextView authorText;

    private TextView publishText;

    private NoScrollListview noScrollListview;

    private TextView canBorrowNum;

    private BookInfoBean bookInfoBean;

    private TextView infoText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        setTitle("图书信息");

        bookNameText=(TextView)findViewById(R.id.book_name);
        authorText=(TextView)findViewById(R.id.author);
        publishText=(TextView)findViewById(R.id.publish);
        noScrollListview=(NoScrollListview)findViewById(R.id.noscrow);
        canBorrowNum=(TextView)findViewById(R.id.borrow_num);
        infoText=(TextView)findViewById(R.id.intro);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("book");
        String bookName = intent.getStringExtra("bookName");
        String author = intent.getStringExtra("bookAuthor");
        String borrowBook = intent.getStringExtra("borrow");
        String chubanshe=intent.getStringExtra("chubanshe");
        bookInfoBean = (BookInfoBean) bundle.getSerializable("book");

        bookNameText.setText(Html.fromHtml(bookName));

        authorText.setText(Html.fromHtml(author));

        canBorrowNum.setText(Html.fromHtml(borrowBook));



        if (bookInfoBean.getInfo().getDouban()!="") {
            infoText.setText(Html.fromHtml(bookInfoBean.getInfo().getDouban()));
        } else {
            infoText.setText(Html.fromHtml(bookInfoBean.getInfo().getTiwenpizhu()));
        }

        publishText.setText(Html.fromHtml(chubanshe));


        noScrollListview.setAdapter(new MyAdapter());

    }

    @Override
    protected void HandleTitleBarEvent(TitleBar component, View v) {
        if (component == TitleBar.LEFT) {
            finish();
        }
    }

    private class MyAdapter extends BaseAdapter {
        private LayoutInflater layoutInflater;

        public MyAdapter() {
            layoutInflater = LayoutInflater.from(BookInfoActivity.this);
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public int getCount() {

            if (bookInfoBean.getInfo().getSuoshuhao().size()==bookInfoBean.getInfo().getGuancangdi().size())
            {
                return bookInfoBean.getInfo().getSuoshuhao().size();
            }else {
                return 0;
            }

        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = layoutInflater.inflate(R.layout.book_site_item, parent, false);
            TextView suoText = (TextView) convertView.findViewById(R.id.suoshuhao);
            TextView guanText = (TextView) convertView.findViewById(R.id.guancangdi);
            suoText.setText(Html.fromHtml(bookInfoBean.getInfo().getSuoshuhao().get(position).trim()));
            guanText.setText(Html.fromHtml(bookInfoBean.getInfo().getGuancangdi().get(position).trim()));
            return convertView;
        }
    }

}
