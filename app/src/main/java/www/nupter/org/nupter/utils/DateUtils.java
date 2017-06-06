package www.nupter.org.nupter.utils;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fangzhenyi on 16/3/28.
 */
public class DateUtils {

    public static int OverTime(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Timestamp timestamp = new Timestamp(format.parse("2016-03-8").getTime());
            Date date=new Date();

            long timeInterval= timestamp.getTime()-date.getTime();
            if (timeInterval>0){
                int result=(int)(timeInterval/3600*24*1000);
                return result+1;
            }else{
                return 200;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


       return 200;
    }


}
