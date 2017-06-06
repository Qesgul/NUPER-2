package www.nupter.org.nupter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import www.nupter.org.nupter.R;
import www.nupter.org.nupter.model.LessonBean;
import www.nupter.org.nupter.utils.Common;

public class ClassInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("bundle");
        LessonBean lessonBean=(LessonBean)bundle.getSerializable("lesson");
        Common.Toast(this,lessonBean.firstTeacher);
    }

}
