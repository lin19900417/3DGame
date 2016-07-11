package ImageCompression;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by Administrator on 2016/7/7.
 */
public class Compression {
    public static Bitmap getCompressionBitmap(byte[] data, int picWidth, int picHeight) {
        Log.i("aaaa","Compression--data--"+data);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap benforeBitmap=BitmapFactory.decodeByteArray(data,0,data.length,options);
        int width=options.outWidth;
        int height=options.outHeight;
        Log.i("aaaa","原始图片的宽："+width+"原始图片的高："+height);


        options.inSampleSize = calculateSampleSize(options, picWidth, picHeight);
        options.inJustDecodeBounds = false;
        Bitmap resultBitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        Log.i("aaaa", "结果图片的大小：" + resultBitmap.getByteCount());
        return resultBitmap;
    }

    public static int calculateSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;
        if (width > reqWidth || height > reqHeight) {
            int reqWidthRadio = Math.round((float) width / reqWidth);
            int reqHeightRadio = Math.round((float) height / reqHeight);
            inSampleSize = reqWidthRadio < reqHeightRadio ? reqWidthRadio : reqHeightRadio;
        }
        Log.i("aaaa", "压缩比：" + inSampleSize);
        return inSampleSize;
    }
}
