package www.nupter.org.nupter.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import www.nupter.org.nupter.R;

/**
 * Created by fangzhenyi on 16/2/20.
 */
public class LeftTable extends View {

    //屏幕宽度
    private int screenWidth;

    private int firstWidth;

    private int commonWidth;

    private int boxHeight=getdippx(45);

    private int commonHeight = getdippx(30);

    public LeftTable(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
        firstWidth = (int) (screenWidth * 0.1);
        commonWidth = (int) (screenWidth * 0.12857143);
        Log.i("fang", "控件初始化" + screenWidth);
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
        paintText.setTextSize(getsppx(15));
        paintText.setStyle(Paint.Style.FILL);
        paintText.setTextAlign(Paint.Align.CENTER);
        paintText.setAntiAlias(true);
        Path path = new Path();
        //画竖直横线
        path.moveTo(firstWidth,0);
        path.lineTo(firstWidth,12 * boxHeight);
        canvas.drawPath(path,paint);

        for (int i=0;i<12;i++) {
            path.moveTo(0, boxHeight * i);
            path.lineTo(firstWidth, boxHeight * i);
            canvas.drawPath(path, paint);
            canvas.drawText("" + (i + 1), (int) (firstWidth / 2), (int) (boxHeight / 1.8 + boxHeight * i), paintText);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(firstWidth, boxHeight*12);
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
