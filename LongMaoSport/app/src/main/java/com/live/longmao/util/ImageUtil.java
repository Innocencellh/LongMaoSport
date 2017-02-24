package com.live.longmao.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;

import com.live.longmao.BaseApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2016/7/13.
 */
public class ImageUtil {
    // 将图片资源文件转出bitmap
    public static Bitmap convertResToBm(Context c, int id) {
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inSampleSize = 2; // 将原图缩小四分之一读取
        option.inJustDecodeBounds = false;
        Bitmap bm = BitmapFactory.decodeResource(c.getResources(), id, option);

        return ThumbnailUtils.extractThumbnail(bm, 480, 800); // 将图片的大小限定在480*800
    }

    // 把本地图片转换成bitmap
    public static Bitmap convertToBitmap(String path, int w, int h) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        // 设置为ture只获取图片大小
        opts.inJustDecodeBounds = true;
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
        // 返回为空
        BitmapFactory.decodeFile(path, opts);
        int width = opts.outWidth;
        int height = opts.outHeight;
        float scaleWidth = 0.f, scaleHeight = 0.f;
        if (width > w || height > h) {
            // 缩放
            scaleWidth = ((float) width) / w;
            scaleHeight = ((float) height) / h;
        }
        opts.inJustDecodeBounds = false;
        float scale = Math.max(scaleWidth, scaleHeight);
        opts.inSampleSize = (int) scale;
        WeakReference<Bitmap> weak = new WeakReference<Bitmap>(
                BitmapFactory.decodeFile(path, opts));
        return Bitmap.createScaledBitmap(weak.get(), w, h, true);
    }
    //压缩图片 360 720 960
    public static Bitmap imgUri2MatriBitmap(Context context, String pathurl,
                                            int bitmap_size) throws FileNotFoundException {
        BitmapFactory.Options option = new BitmapFactory.Options();
        // option.inSampleSize = 1;
        option.inJustDecodeBounds = true;
        // Bitmap bitmap = BitmapFactory.decodeFile(pathurl, option);
        FileInputStream fis = new FileInputStream(pathurl);
        Bitmap bitmap = BitmapFactory.decodeStream(fis, null, option);

        option.inJustDecodeBounds = false;
        // option.inPreferredConfig = Bitmap.Config.RGB_565;

        option.inPreferredConfig = Bitmap.Config.RGB_565;

        option.inPurgeable = true;

        option.inInputShareable = true;

        option.inSampleSize = calculateInSampleSize(option, bitmap_size,
                bitmap_size);

        if (option.inSampleSize <= 0)
            option.inSampleSize = 1;
        // bitmap = BitmapFactory.decodeFile(pathurl, option);

        try {
            fis = new FileInputStream(pathurl);
            bitmap = BitmapFactory.decodeStream(fis, null, option);
        } catch (OutOfMemoryError e) {
        }
        return bitmap;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {

        int height = options.outHeight;
        int width = options.outWidth;

        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    //判断照片角度
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }
    //旋转照片
    public static Bitmap rotateBitmap(Bitmap bitmap,int degress) {
        if (bitmap != null) {
            Matrix m = new Matrix();
            m.postRotate(degress);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), m, true);
            return bitmap;
        }
        return bitmap;
    }

    // 根据路径获得图片并压缩，返回bitmap用于显示
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 480, 800);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

}
