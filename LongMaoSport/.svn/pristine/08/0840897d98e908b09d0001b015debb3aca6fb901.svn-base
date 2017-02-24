package com.live.longmao.gifdlg;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class FileUtil {

    public static boolean exist(final String name) {
        return (null != name && new File(name).exists());
    }

    public static String linkFileName(final String... args) {
        if (null == args) {
            return null;
        }
        final StringBuilder sb = new StringBuilder(args[0]);
        for (int i = 1; i < args.length; i++) {
            sb.append(File.separator).append(args[i]);
        }
        return sb.toString();
    }

    /**
     * copy file from source to dest. impl of Files.copy(...)
     *
     * @param source
     * @param dest
     * @throws IOException
     */
    public static void copyFile(final String source, final String dest)
            throws IOException {
        if (!checkOrMakeDir(dest)) {
            return;
        }
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != input) {
                input.close();
            }
            if (null != output) {
                output.close();
            }
        }
    }

    public static boolean saveImageFile(final String filePath, final Bitmap bitmap) {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        final InputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        return writeFile(filePath, isBm);
    }

    /**
     * write file
     */
    public static boolean writeFile(final String filePath,
                                    final InputStream stream) {

        if (!checkOrMakeDir(filePath)) {
            return false;
        }
        OutputStream o = null;
        try {
            o = new FileOutputStream(filePath);
            final byte data[] = new byte[1024];
            int length = -1;
            while ((length = stream.read(data)) != -1) {
                o.write(data, 0, length);
            }
            o.flush();
            return true;
        } catch (Throwable e) {
            // just ignore
            return false;
        } finally {
            try {
                stream.close();
            } catch (Throwable e) {
            }
            if (o != null) {
                try {
                    o.close();
                } catch (Throwable e) {
                }
            }
        }
    }

    /**
     * write file
     *
     * @param filePath
     * @param content
     * @param append   is append, if true, write to the end of file, else clear
     *                 content of file and write into it
     * @return return true
     * @throws IOException if an error occurs while operator FileWriter
     */
    public static boolean writeFile(final String filePath,
                                    final String content, final boolean append) {

        if (!checkOrMakeDir(filePath)) {
            return false;
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath, append);
            fileWriter.write(content);
            fileWriter.close();
            return true;
        } catch (Throwable e) {
            // just ignore
            return false;
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (Throwable e) {
                }
            }
        }
    }

    public static void makeDir(final String dir) {
        if (null == dir) {
            return;
        }
        final File folder = new File(dir);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    /**
     * make dir if not exist
     */
    public static boolean checkOrMakeDir(final String filePath) {
        if (null == filePath) {
            return false;
        }
        final File folder = new File(filePath).getParentFile();
        return ((folder.exists() && folder.isDirectory()) || folder.mkdirs())
                ? true : false;
    }

    public static byte[] readBytesFromFile(final String filePath) {
        byte[] b = null;
        try {
            @SuppressWarnings("resource")
            final RandomAccessFile f = new RandomAccessFile(filePath, "r");
            b = new byte[(int) f.length()];
            f.read(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

    public static String readFile(final String filePath) {
        final StringBuilder sb = readFile(filePath, "utf-8");
        return (null != sb) ? sb.toString() : new String("");
    }

    public static String readFile(final File file) {
        final StringBuilder sb = readFile(file, "utf-8");
        return (null != sb) ? sb.toString() : new String("");
    }

    private static StringBuilder readFile(final String filePath,
                                          final String charsetName) {
        return readFile(new File(filePath), charsetName);
    }

    private static StringBuilder readFile(final File file,
                                          final String charsetName) {
        if (file == null || !file.isFile()) {
            return null;
        }
        final StringBuilder fileContent = new StringBuilder("");
        BufferedReader reader = null;
        try {
            final InputStreamReader is = new InputStreamReader(
                    new FileInputStream(
                            file),
                    charsetName);
            reader = new BufferedReader(is);
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (!fileContent.toString().equals("")) {
                    fileContent.append("\r\n");
                }
                fileContent.append(line);
            }
            reader.close();
            return fileContent;
        } catch (Throwable e) {
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * delete file or directory
     * <ul>
     * <li>if path is null or empty, return true</li>
     * <li>if path not exist, return true</li>
     * <li>if path exist, delete recursion. return true</li>
     * <ul>
     *
     * @param path
     */
    public static boolean deleteFile(final String path) {
        if (null == path || path.isEmpty()) {
            return true;
        }

        final File file = new File(path);
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (!file.isDirectory()) {
            return false;
        }
        for (File f : file.listFiles()) {
            if (f.isFile()) {
                f.delete();
            } else if (f.isDirectory()) {
                deleteFile(f.getAbsolutePath());
            }
        }
        return file.delete();
    }

    public static byte[] inputStreamToByte(final InputStream in) {

        final int BUFFER_SIZE =
                // PhoneInfo.isLowPhone() ?
                512
                // : 1024
                ;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        try {
            while ((count = in.read(data, 0, BUFFER_SIZE)) != -1) {
                outStream.write(data, 0, count);
            }
        } catch (IOException e) {

        } catch (OutOfMemoryError error) {
            System.gc();
        } finally {
            data = null;
            try {
                in.close();
            } catch (IOException e) {
            }
        }
        return outStream.toByteArray();
    }

    /**
     * copy asset's file of dir to storage
     */
    public static void unpackAssets(final Context context,
                                    final String assetsPath, final String path) {
        if (null == assetsPath || null == path) {
            return;
        }
        try {
            String items[] = context.getAssets().list(assetsPath);
            if (items.length > 0) { // 如果是目录
                makeDir(path);
                for (String item : items) {
                    unpackAssets(context,
                            assetsPath + "/" + item,
                            path + "/" + item);
                }
            } else {// 如果是文件
                writeFile(path, context.getAssets().open(assetsPath));
            }
        } catch (IOException e) {
            deleteFile(path);
        }
    }

    public static String readAssets(Context context, String fileName) {
        InputStream is = null;
        String content = null;
        try {
            is = context.getAssets().open(fileName);
            if (is != null) {

                byte[] buffer = new byte[1024];
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int readLength = is.read(buffer);
                    if (readLength == -1)
                        break;
                    arrayOutputStream.write(buffer, 0, readLength);
                }
                is.close();
                arrayOutputStream.close();
                content = new String(arrayOutputStream.toByteArray());

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            content = null;
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return content;
    }

    public static char[] readMagic(final Context ctx, final String path, final int length) {
        InputStreamReader inReader = null;
        InputStream is = null;
        final char[] buffer = new char[length];
        int readLength = 0;
        try {
            final File file = new File(path);
            if (null != file && file.isFile()) {
                is = new FileInputStream(file);
            } else if (null != ctx) {
                is = ctx.getAssets().open(path);
            }
            if (null != is) {
                inReader = new InputStreamReader(is);
                readLength = inReader.read(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
            if (null != inReader) {

                try {
                    inReader.close();
                } catch (IOException e) {
                }
            }
        }
        return (length == readLength) ? buffer : null;
    }

    public static boolean externalStorageExists() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
