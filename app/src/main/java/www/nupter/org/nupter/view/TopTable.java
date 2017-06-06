package www.nupter.org.nupter.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import www.nupter.org.nupter.R;

/**
 * Created by fangzhenyi on 16/2/19.
 */
public class TopTable extends View {

    private String TAG = "Table";

    //屏幕宽度
    private int screenWidth;

    private int firstWidth;

    private int commonWidth;

    private int commonHeight = getdippx(40);

    public TopTable(Context context) {
        super(context);
    }

    public TopTable(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
        firstWidth = (int) (screenWidth * 0.1);
        commonWidth = (int) (screenWidth * 0.12857143);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.blue));
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);//去锯齿
        paint.setStrokeWidth(1);

        Paint paintText = new Paint();
        paintText.setColor(getResources().getColor(R.color.blue));
        paintText.setTextSize(getsppx(13));
        paintText.setStyle(Paint.Style.FILL);
        paintText.setTextAlign(Paint.Align.CENTER);
        paintText.setAntiAlias(true);
        Path path = new Path();
        //画水平横线
        path.moveTo(0, commonHeight);
        path.lineTo(screenWidth, commonHeight);
        canvas.drawPath(path, paint);

        String[] week = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};

        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");


        canvasMon(canvas, paintText);


        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                path.moveTo(firstWidth, 0);
                path.lineTo(firstWidth, commonHeight);
                canvas.drawText(week[i], firstWidth + (int) (0.5 * commonWidth), commonHeight - getdippx(10), paintText);
                canvas.drawPath(path, paint);
                canvasDate(canvas, paintText, i);
            } else {
                canvas.drawText(week[i], firstWidth + commonWidth * i + (int) (0.5 * commonWidth), commonHeight - getdippx(10), paintText);
                path.moveTo(firstWidth + commonWidth * i, 0);
                path.lineTo(firstWidth + commonWidth * i, commonHeight);
                canvas.drawPath(path, paint);
                canvasDate(canvas, paintText, i);
            }
        }
    }

    public void canvasMon(Canvas canvas, Paint paintText) {
        SimpleDateFormat monFormatter = new SimpleDateFormat("MM");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String monStr = monFormatter.format(curDate);
        canvas.drawText(monStr + "月", (int) (firstWidth * 0.5), commonHeight - getdippx(20), paintText);
    }

    public void canvasDate(Canvas canvas, Paint paintText, int i) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd");
        Date curDate = new Date(System.currentTimeMillis() + i * 24 * 3600 * 1000);//获取当前时间
        String dateStr = dateFormatter.format(curDate);
        canvas.drawText(dateStr, firstWidth + commonWidth * i + (int) (0.5 * commonWidth), commonHeight - getdippx(25), paintText);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(screenWidth, commonHeight);
    }

    private int getdippx(int dpValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private int getsppx(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}
