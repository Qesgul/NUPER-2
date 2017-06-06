package www.nupter.org.nupter.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import www.nupter.org.nupter.R;
import www.nupter.org.nupter.http.HttpCallBack;
import www.nupter.org.nupter.http.HttpResquestUtil;
import www.nupter.org.nupter.http.RequestUrl;
import www.nupter.org.nupter.model.BorrowBookInfo;
import www.nupter.org.nupter.utils.DataUtils;
import www.nupter.org.nupter.utils.PreferenceKey;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginLibraryActivity extends BaseActivity {

    private EditText stuNumEdit;
    private EditText mPasswordView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_library);
        stuNumEdit = (EditText) findViewById(R.id.stuNum);
        mPasswordView = (EditText) findViewById(R.id.password);
        mLoginFormView = findViewById(R.id.login_button);

        stuNumEdit.setText(DataUtils.getPreferences(PreferenceKey.stuNum,""));
        mPasswordView.setText(DataUtils.getPreferences(PreferenceKey.libPass,""));

        setTitle("登陆我的图书馆");
        mLoginFormView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final String stu=stuNumEdit.getText().toString();
                final String pass=mPasswordView.getText().toString();

                RequestParams requestParams = new RequestParams(RequestUrl.loginLibrary);
                requestParams.addBodyParameter("stunum", stu);
                requestParams.addBodyParameter("pass", pass);
                requestParams.addBodyParameter("android","android");

                HttpResquestUtil.postMethod(requestParams, BorrowBookInfo.class, new HttpCallBack<BorrowBookInfo>() {
                    @Override
                    public void success(BorrowBookInfo data) {
                        Intent intent=new Intent();
                        intent.setClass(LoginLibraryActivity.this, BorrowBookInfoActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("borrow",data);
                        intent.putExtra("borrow", bundle);
                        DataUtils.putPreferences(PreferenceKey.stuNum,stu);
                        DataUtils.putPreferences(PreferenceKey.libPass,pass);
                        startActivity(intent);

                    }

                    @Override
                    public void erro(String res) {
                         BaseToast(res);
                    }
                });

            }
        });
    }

    @Override
    protected void HandleTitleBarEvent(TitleBar component, View v) {
        if (component==TitleBar.LEFT){
            finish();
        }
    }
}

