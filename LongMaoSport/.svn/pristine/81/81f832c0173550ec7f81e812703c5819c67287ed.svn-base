package com.live.longmao.util;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2016/7/13.
 */
public class FileUtil {
    public static void CREATEFILE(String filename)
    {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            File destDir = new File(filename);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }
        }
    }
    //删除文件或者文件夹
    public static void DELETEFILE(File file) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                file.delete(); // delete()方法 你应该知道 是删除的意思;
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    files[i].delete(); // 把每个文件 用这个方法进行迭代
                }
            }
        } else {
            System.out.println("文件不存在");
        }
    }
}
