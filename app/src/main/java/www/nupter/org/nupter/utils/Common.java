package www.nupter.org.nupter.utils;

import android.content.Context;
import android.widget.Toast;


import org.xutils.http.RequestParams;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import www.nupter.org.nupter.http.HttpCallBack;
import www.nupter.org.nupter.http.HttpResquestUtil;
import www.nupter.org.nupter.http.RequestUrl;

/**
 * Created by fangzhenyi on 16/2/15.
 */
public class Common {
    public static void Toast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static int getCurrentWeek(String time) {
        //今天的时间，减去第一周的时间，然后除以7得到当前周数
        int week = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Timestamp timestamp = new Timestamp(format.parse(time).getTime());
            Date date = new Date(timestamp.getTime());
            long timeMills;
            if (date.getDay() == 1) {
                timeMills = System.currentTimeMillis() - timestamp.getTime();
            } else {
                timeMills = System.currentTimeMillis() - timestamp.getTime()-24 * 3600 * 1000 * (date.getDay()+1);
            }
            week = (int) (timeMills / (24 * 3600 * 1000 * 7)) + 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return week;
    }


    public static String getTime(){
        Date date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(date.getTime());
    }
}
