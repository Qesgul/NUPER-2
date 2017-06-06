package www.nupter.org.nupter.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import junit.framework.Test;

import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import www.nupter.org.nupter.R;
import www.nupter.org.nupter.event.ClickListener;
import www.nupter.org.nupter.model.ClassBean;
import www.nupter.org.nupter.model.LessonBean;

/**
 * Created by fangzhenyi on 16/2/20.
 */
public class MyTable extends LinearLayout {

    //屏幕宽度
    private int screenWidth;

    private int firstWidth;

    private int commonWidth;

    private int boxHeight;

    private int commonHeight;


    private RelativeLayout monLinear;
    private RelativeLayout tuesLinear;
    private RelativeLayout wedLinear;
    private RelativeLayout thurLinear;
    private RelativeLayout friLinear;
    private RelativeLayout satLinear;
    private RelativeLayout sunLinear;
    private RelativeLayout.LayoutParams layoutParams;

    private float textSize = 11;
    private Context context;
    //课程信息
    private LessonBean[][] lessonBeans = new LessonBean[7][5];
    //当前周
    private int weekCount;
    //当前是否是单周
    private int isSingle;

    private ClickListener clickListener;

    public MyTable(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        boxHeight = getdippx(context, 45);
        commonHeight = getdippx(context, 30);

        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
        firstWidth = (int) (screenWidth * 0.1);
        commonWidth = (int) (screenWidth * 0.12857143);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_mytable, this, false);
        addView(view);
        monLinear = (RelativeLayout) view.findViewById(R.id.mon);
        tuesLinear = (RelativeLayout) view.findViewById(R.id.tues);
        wedLinear = (RelativeLayout) view.findViewById(R.id.wed);
        thurLinear = (RelativeLayout) view.findViewById(R.id.thur);
        friLinear = (RelativeLayout) view.findViewById(R.id.fri);
        satLinear = (RelativeLayout) view.findViewById(R.id.sat);
        sunLinear = (RelativeLayout) view.findViewById(R.id.sun);
    }


    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;

    }

    public void removeView(){
        monLinear.removeAllViews();
        tuesLinear.removeAllViews();
        wedLinear.removeAllViews();
        thurLinear.removeAllViews();
        friLinear.removeAllViews();
        satLinear.removeAllViews();
        sunLinear.removeAllViews();

    }

    public void setData(ClassBean classBean, int weekCount) {
        removeView();
        this.weekCount = weekCount;
        isSingle = weekCount % 2;

        String[][] classString = {
                {
                        classBean.getInfo().getInfo().getMon().getFirst(),
                        classBean.getInfo().getInfo().getMon().getSecond(),
                        classBean.getInfo().getInfo().getMon().getThird(),
                        classBean.getInfo().getInfo().getMon().getFourth(),
                        classBean.getInfo().getInfo().getMon().getFifth()
                },
                {
                        classBean.getInfo().getInfo().getTues().getFirst(),
                        classBean.getInfo().getInfo().getTues().getSecond(),
                        classBean.getInfo().getInfo().getTues().getThird(),
                        classBean.getInfo().getInfo().getTues().getFourth(),
                        classBean.getInfo().getInfo().getTues().getFifth()
                },
                {
                        classBean.getInfo().getInfo().getWed().getFirst(),
                        classBean.getInfo().getInfo().getWed().getSecond(),
                        classBean.getInfo().getInfo().getWed().getThird(),
                        classBean.getInfo().getInfo().getWed().getFourth(),
                        classBean.getInfo().getInfo().getWed().getFifth()
                },
                {
                        classBean.getInfo().getInfo().getThur().getFirst(),
                        classBean.getInfo().getInfo().getThur().getSecond(),
                        classBean.getInfo().getInfo().getThur().getThird(),
                        classBean.getInfo().getInfo().getThur().getFourth(),
                        classBean.getInfo().getInfo().getThur().getFifth()
                },
                {
                        classBean.getInfo().getInfo().getFri().getFirst(),
                        classBean.getInfo().getInfo().getFri().getSecond(),
                        classBean.getInfo().getInfo().getFri().getThird(),
                        classBean.getInfo().getInfo().getFri().getFourth(),
                        classBean.getInfo().getInfo().getFri().getFifth()
                },
                {
                        classBean.getInfo().getInfo().getSat().getFirst(),
                        classBean.getInfo().getInfo().getSat().getSecond(),
                        classBean.getInfo().getInfo().getSat().getThird(),
                        classBean.getInfo().getInfo().getSat().getFourth(),
                        classBean.getInfo().getInfo().getSat().getFifth()},
                {
                        classBean.getInfo().getInfo().getSun().getFirst(),
                        classBean.getInfo().getInfo().getSun().getSecond(),
                        classBean.getInfo().getInfo().getSun().getThird(),
                        classBean.getInfo().getInfo().getSun().getFourth(),
                        classBean.getInfo().getInfo().getSun().getFifth()
                }};

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                Log.i("fang", classString[i][j]);
                String[] lesson = classString[i][j].split("<br><br>");
                switch (lesson.length) {
                    case 0:
                        break;
                    case 1:
                        if (lesson[0].length() > 2) {
                            lessonBeans[i][j] = matchFirst(lesson[0], i, j);
                        }
                        break;
                    case 2:
                        lessonBeans[i][j] = matchSecond(lesson, i, j);
                        break;
                    default:
                }

            }
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                if (lessonBeans[i][j] != null) {
                    switch (i) {
                        case 0:
                            JudeWeek(monLinear, j, lessonBeans[i][j]);
                            break;
                        case 1:
                            JudeWeek(tuesLinear, j, lessonBeans[i][j]);
                            break;
                        case 2:
                            JudeWeek(wedLinear, j, lessonBeans[i][j]);
                            break;
                        case 3:
                            JudeWeek(thurLinear, j, lessonBeans[i][j]);
                            break;
                        case 4:
                            JudeWeek(friLinear, j, lessonBeans[i][j]);
                            break;
                        case 5:
                            JudeWeek(satLinear, j, lessonBeans[i][j]);
                            break;
                        case 6:
                            JudeWeek(sunLinear, j, lessonBeans[i][j]);
                            break;
                    }

                }

            }
        }


    }


    private void setTextViewText(RelativeLayout relativeLayout, int j, final LessonBean lessonBean, boolean isFirst) {
        TextView textView = new TextView(context);
        textView.setBackground(getResources().getDrawable(R.drawable.actionbar_background));
        textView.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        textView.setTextSize(textSize);
        if (isFirst) {
            textView.setText(lessonBean.firstClassName + "@" + lessonBean.firstClassRoom);
        } else {
            textView.setText(lessonBean.secondClassName + "@" + lessonBean.secondClassRoom);
        }
        layoutParams = new RelativeLayout.LayoutParams(commonWidth - 2, boxHeight * 2 - 2);
        if (j <= 1) {
            layoutParams.setMargins(1, boxHeight * 2 * j + 1, 1, 1);
        } else {
            layoutParams.setMargins(1, boxHeight * 2 * j + boxHeight + 1, 0, 0);
        }
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.listener(lessonBean);
            }
        });
        relativeLayout.addView(textView, layoutParams);
        Log.i("fang", "type值" + lessonBean.classType);
        Log.i("fang", "firstCode值" + lessonBean.firstClassCode);
        Log.i("fang", "上课周数" + lessonBean.firstWeek);
    }


    private void JudeWeek(RelativeLayout relativeLayout, int j, LessonBean lessonBean) {

        if (lessonBean.classType == 0) {//这个时间段只有一门课
            JudgeFirst(relativeLayout, j, lessonBean);
        } else {//这个时间段有两门课
            JudgeFirst(relativeLayout, j, lessonBean);
            JudgeSecond(relativeLayout, j, lessonBean);
        }

    }

    private void JudgeFirst(RelativeLayout relativeLayout, int j, LessonBean lessonBean) {
        int[] week = getWeek(lessonBean.firstWeek);
        if (week[0] <= weekCount && weekCount <= week[1]) {//要求课表显示是否属于当前周

            if (lessonBean.firstClassCode == 2) {//单双周都上

                setTextViewText(relativeLayout, j, lessonBean, true);

            } else if (lessonBean.firstClassCode == 1) {//双周上

                if (isSingle == 0)//当前双周
                    setTextViewText(relativeLayout, j, lessonBean, true);


            } else {//单周上
                if (isSingle == 1)
                    setTextViewText(relativeLayout, j, lessonBean, true);
            }

        }

    }

    private void JudgeSecond(RelativeLayout relativeLayout, int j, LessonBean lessonBean) {
        int[] week = getWeek(lessonBean.secondWeek);
        if (week[0] <= weekCount && weekCount <= week[1]) {//要求课表显示是否属于当前周
            if (lessonBean.secondClassCode == 2) {//单双周都上
                setTextViewText(relativeLayout, j, lessonBean, false);
            } else if (lessonBean.secondClassCode == 1) {//双周上


                if (isSingle == 0)
                    setTextViewText(relativeLayout, j, lessonBean, false);

            } else {//单周上
                if (isSingle == 1)
                    setTextViewText(relativeLayout, j, lessonBean, false);
            }

        }
    }

    private int[] getWeek(String weekString) {
        String[] week = weekString.split("-");
        int start = Integer.valueOf(week[0]);
        int end = Integer.valueOf(week[1]);
        int[] weekArray = {start, end};
        return weekArray;
    }

    private LessonBean matchFirst(String s, int i, int j) {
        String[] lessonInfo = s.split("<br>");
        LessonBean lessonBean = new LessonBean();
        lessonBean.classType = 0;
        lessonBean.firstClassName = lessonInfo[0];
        Pattern patternSingle = Pattern.compile("单周");
        Matcher matcherSingle = patternSingle.matcher(lessonInfo[1]);
        Pattern patternDouble = Pattern.compile("双周");
        Matcher matcherDouble = patternDouble.matcher(lessonInfo[1]);
        if (matcherSingle.find()) {
            lessonBean.firstClassCode = 0;
        } else if (matcherDouble.find()) {
            lessonBean.firstClassCode = 1;
        } else {
            lessonBean.firstClassCode = 2;
        }
        Pattern pattern = Pattern.compile("[0-9,－,-]+");
        Matcher matcher = pattern.matcher(lessonInfo[1]);
        int b = 0;
        while (matcher.find()) {
            b++;
            if (b == 1) {
                lessonBean.firstClassTime = matcher.group();
            }
            if (b == 2) {
                lessonBean.firstWeek = matcher.group();
            }
        }
        lessonBean.firstTeacher = lessonInfo[2];
        if (lessonInfo.length >= 4)
            lessonBean.firstClassRoom = lessonInfo[3];

        return lessonBean;
    }

    private LessonBean matchSecond(String[] s, int i, int j) {
        LessonBean lessonBean = matchFirst(s[0], i, j);
        lessonBean.classType = 1;
        String[] lessonInfo = s[1].split("<br>");
        lessonBean.secondClassName = lessonInfo[0];
        Pattern patternSingle = Pattern.compile("单周");
        Matcher matcherSingle = patternSingle.matcher(lessonInfo[1]);
        Pattern patternDouble = Pattern.compile("双周");
        Matcher matcherDouble = patternDouble.matcher(lessonInfo[1]);
        if (matcherSingle.find()) {
            lessonBean.secondClassCode = 0;
        } else if (matcherDouble.find()) {
            lessonBean.secondClassCode = 1;
        } else {
            lessonBean.secondClassCode = 2;
        }
        Pattern pattern = Pattern.compile("[0-9,－,-]+");
        Matcher matcher = pattern.matcher(lessonInfo[1]);
        int b = 0;
        while (matcher.find()) {
            b++;
            if (b == 1) {
                lessonBean.secondClassTime = matcher.group();
            }
            if (b == 2) {
                lessonBean.secondWeek = matcher.group();
            }
        }
        lessonBean.secondTeacher = lessonInfo[2];
        if (lessonInfo.length >= 4)
            lessonBean.secondClassRoom = lessonInfo[3];
        return lessonBean;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int getdippx(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
