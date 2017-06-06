package www.nupter.org.nupter.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by fangzhenyi on 16/4/5.
 */
public class PictureUtil {

    /**
     * 计算图片的缩放值
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }


    /**
     * 根据路径获得突破并压缩返回bitmap用于显示
     *
     * @param filePath
     * @return
     */
    public static byte[] getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        String fname=new File(filePath).getAbsolutePath();
        Bitmap bitmap = BitmapFactory.decodeFile(fname, options);


        int width = options.outWidth;
        int height = options.outHeight;

        Log.i("fang","图片原始宽度"+width);
        Log.i("fang","图片原始高度"+height);



        if (width >= 360) {
            height = 360 / width * height;
            options.inSampleSize = calculateInSampleSize(options,360, height);
            // Decode bitmap with isSampleSize set
            options.inJustDecodeBounds = false;
            ByteArrayOutputStream output = new ByteArrayOutputStream();//初始化一个流对象
            Bitmap bitmap1=BitmapFactory.decodeFile(fname, options);
            bitmap1.compress(Bitmap.CompressFormat.PNG, 70, output);//把bitmap100%高质量压缩 到 output对象里
            bitmap1.recycle();//自由选择是否进行回收
            byte[] result = output.toByteArray();//转换成功了
            return result;
        } else {
            options.inJustDecodeBounds = false;
            ByteArrayOutputStream output = new ByteArrayOutputStream();//初始化一个流对象
            Bitmap bitmap1=BitmapFactory.decodeFile(fname, options);
            bitmap1.compress(Bitmap.CompressFormat.PNG, 100, output);//把bitmap100%高质量压缩 到 output对象里
            bitmap1.recycle();//自由选择是否进行回收
            byte[] result = output.toByteArray();//转换成功了
            return result;
        }
    }

}
