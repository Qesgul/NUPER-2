package www.nupter.org.nupter.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import www.nupter.org.nupter.R;
import www.nupter.org.nupter.adapter.CommonAdapter;
import www.nupter.org.nupter.http.HttpCallBack;
import www.nupter.org.nupter.http.HttpResquestUtil;
import www.nupter.org.nupter.http.RequestUrl;
import www.nupter.org.nupter.model.BookBean;
import www.nupter.org.nupter.model.BookInfoBean;
import www.nupter.org.nupter.utils.Common;
import www.nupter.org.nupter.utils.ViewHolder;
import www.nupter.org.nupter.widget.XListView;

public class SearchBookActivity extends Activity {
    private SearchView searchView;
    private ImageView quite_image;

    private XListView xListView;

    private String bookName = "";

    private CommonAdapter<BookBean.InfoEntity> commonAdapter;

    private List<BookBean.InfoEntity> bookList = new ArrayList<>();

    private int page = 1;

    private LinearLayout searchLinear;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);
        searchView = (SearchView) findViewById(R.id.searchView);
        quite_image = (ImageView) findViewById(R.id.quite);
        xListView = (XListView) findViewById(R.id.book_list);
        searchLinear=(LinearLayout)findViewById(R.id.search_info_linear);

        searchView.setQueryHint(Html.fromHtml("<font color = #ffffff>请输入你要查询的书的名字</font>"));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                bookName = searchView.getQuery().toString();
                getBooks(page, false);
                progressDialog.show();
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        commonAdapter = new CommonAdapter<BookBean.InfoEntity>(this, bookList, R.layout.book_list_item) {
            @Override
            public void convert(ViewHolder holder, BookBean.InfoEntity item, int position) {
                TextView titleText = holder.getView(R.id.title);
                TextView authorText = holder.getView(R.id.author);
                TextView addressText = holder.getView(R.id.address);
                TextView isBorrowText = holder.getView(R.id.is_borrow);
                titleText.setText(Html.fromHtml(bookList.get(position).getBookname().trim()));
                authorText.setText(Html.fromHtml(bookList.get(position).getAuthor().trim()));
                addressText.setText(Html.fromHtml("馆藏：    " + bookList.get(position).getGuancang().trim()));
                isBorrowText.setText(Html.fromHtml("可借：    " + bookList.get(position).getIsborrow().trim()));
            }
        };

        xListView.setAdapter(commonAdapter);
        quite_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        xListView.setPullRefreshEnable(false);
        xListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                page++;
                getBooks(page, true);
            }
        });


        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("正在努力加载，请等待");

        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                progressDialog.show();
                RequestParams requestParams = new RequestParams(RequestUrl.getBookInfo);
                requestParams.addBodyParameter("no",bookList.get(position).getBookurl());
                requestParams.addBodyParameter("android","android");
                HttpResquestUtil.postMethod(requestParams, BookInfoBean.class, new HttpCallBack<BookInfoBean>() {
                    @Override
                    public void success(BookInfoBean data) {
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("book", data);
                        Intent intent=new Intent();
                        int start=position-1;
                        intent.putExtra("book",bundle);
                        intent.putExtra("bookName",bookList.get(start).getBookname());
                        intent.putExtra("bookAuthor",bookList.get(start).getAuthor());
                        intent.putExtra("borrow","馆藏"+bookList.get(start).getGuancang()+"本/"+"可借"+bookList.get(position).getIsborrow()+"本");
                        intent.putExtra("chubanshe",bookList.get(start).getChubanshe());

                        intent.setClass(SearchBookActivity.this, BookInfoActivity.class);
                        startActivity(intent);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void erro(String res) {

                        Common.Toast(SearchBookActivity.this,res);
                    }
                });
            }
        });

    }


    private void hideInputMethod(){

    }

    public void getBooks(int page, final boolean isPull) {
        RequestParams requestParams = new RequestParams(RequestUrl.getBookUrl);
        requestParams.addBodyParameter("name", searchView.getQuery().toString());
        requestParams.addBodyParameter("page", String.valueOf(page));
        requestParams.addBodyParameter("android", "llala");
        HttpResquestUtil.postMethod(requestParams, BookBean.class, new HttpCallBack<BookBean>() {
            @Override
            public void success(BookBean data) {
                searchLinear.setVisibility(View.GONE);
                if (isPull) {
                    bookList.addAll(data.getInfo());
                } else {
                    bookList.clear();
                    bookList.addAll(data.getInfo());
                    xListView.setSelection(0);
                    progressDialog.dismiss();

                }
                commonAdapter.setData(bookList);
                commonAdapter.notifyDataSetChanged();
                xListView.stopLoadMore();

            }

            @Override
            public void erro(String res) {
                Common.Toast(SearchBookActivity.this,res);
                progressDialog.dismiss();

            }
        });
    }

}
