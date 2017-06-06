package www.nupter.org.nupter.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;

import www.nupter.org.nupter.R;
import www.nupter.org.nupter.adapter.CommonAdapter;
import www.nupter.org.nupter.event.ClickListener;
import www.nupter.org.nupter.model.ClassBean;
import www.nupter.org.nupter.model.LessonBean;
import www.nupter.org.nupter.utils.Common;
import www.nupter.org.nupter.utils.DataUtils;
import www.nupter.org.nupter.utils.PreferenceKey;
import www.nupter.org.nupter.utils.ViewHolder;
import www.nupter.org.nupter.view.MyTable;

public class ClassTableActivity extends Activity implements View.OnClickListener {

    private MyTable myTable;

    private ImageView backImageView;

    private TextView settingText;

    private TextView weekText;

    private PopupWindow popupWindow;

    private RelativeLayout headRelative;

    private int popuWidth;

    private ListView popuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_table);
        setTitle("我的课表");
        myTable = (MyTable) findViewById(R.id.myTable);
        backImageView = (ImageView) findViewById(R.id.back);
        weekText = (TextView) findViewById(R.id.week);
        settingText = (TextView) findViewById(R.id.setting);
        settingText.setOnClickListener(this);
        headRelative = (RelativeLayout) findViewById(R.id.header);
        weekText.setOnClickListener(this);
        backImageView.setOnClickListener(this);
        String table = DataUtils.getPreferences(PreferenceKey.KEBIAO, "");

        Log.i("fang",table);

        Gson gson = new Gson();
        final ClassBean classBean = gson.fromJson(table, ClassBean.class);
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        popuWidth = width / 2;
        int popuHeight = height / 3;
        View popuView = getLayoutInflater().inflate(R.layout.popup_layout, null);
        popupWindow = new PopupWindow(popuView, popuWidth, popuHeight);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        popupWindow.setOutsideTouchable(true);
        popuList = (ListView) popuView.findViewById(R.id.popu_list);
        int[] week = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        DataUtils.putPreferences(PreferenceKey.WEEK, "2016-02-22");
        int weekCount = Common.getCurrentWeek(DataUtils.getPreferences(PreferenceKey.WEEK, ""));

        Log.i("fang","当前周"+weekCount);
        popuList.setAdapter(new popuAdapter(this, week, weekCount));
        popuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myTable.setData(classBean, position + 1);
                weekText.setText("第" + (position + 1) + "周(非本周)");
                popupWindow.dismiss();
            }
        });

        weekText.setText("第"+weekCount+"周");

        myTable.setData(classBean, weekCount);
        myTable.setClickListener(new ClickListener() {
            @Override
            public void listener(LessonBean lessonBean) {
                Common.Toast(ClassTableActivity.this, lessonBean.firstClassName + "@" + lessonBean.firstClassRoom);
                Intent intent=new Intent(ClassTableActivity.this,ClassInfoActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("lesson",lessonBean);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.setting:
                Intent intent = new Intent();
                intent.setClass(ClassTableActivity.this, SettingClassActivity.class);
                startActivity(intent);
                break;
            case R.id.week:
                popupWindow.showAsDropDown(headRelative, popuWidth / 2, 0);
                popuList.setSelection(7);
                break;
        }

    }

    private class popuAdapter extends BaseAdapter {
        private LayoutInflater layoutInflater;
        private int[] week;
        private int i;

        public popuAdapter(Context context, int[] week, int i) {
            layoutInflater = LayoutInflater.from(context);
            this.week = week;
            this.i = i;
        }

        @Override
        public int getCount() {
            return week.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = layoutInflater.inflate(R.layout.popu_list, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.popu_text);
            if (position == i -1) {
                textView.setText("第" + week[position] + "周(本周)");
                textView.setBackground(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
            } else {
                textView.setText("第" + week[position] + "周");

            }
            return convertView;
        }
    }

}
