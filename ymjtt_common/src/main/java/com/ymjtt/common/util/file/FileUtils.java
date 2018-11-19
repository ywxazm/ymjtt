package com.ymjtt.common.util.file;

import com.ymjtt.common.util.date.DateUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 文件处理 工具
 *
 * @author ywx
 * @date 2018/11/16 8:33
 */
public class FileUtils {

    /**
     * 当前目录路径
     *
     * @author ywx
     * @date 2018/11/16 8:33
     */
    public static String currentWorkDir = System.getProperty("user.dir") + "\\";

    /**
     * 生成 文件名 = 当前时间 + 2位随机数 + 扩展名
     *
     * @param extensionName
     * @return
     */
    public static String createFileName(String extensionName) {
        int num = new Random().nextInt(99);
        LocalDateTime localDateTime = DateUtils.getCurrentDateTime();
        return DateUtils.LocalDateTime2String(localDateTime, DateUtils.TIME_NOFUll_FORMAT) + String.format("%03d", num) + "." + extensionName;
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName
     * @return
     */
    public static String getExtensionName(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 向左侧填充不足的长度
     *
     * @param str    被填充字符串
     * @param length 要求长度
     * @param ch     填充字符
     * @return 被填充的字符串
     */
    public static String leftPad(String str, int length, char ch) {
        if (str.length() >= length) {
            return str;
        }
        char[] chs = new char[length];
        Arrays.fill(chs, ch);           //将字符ch填充到chs数组中
        char[] src = str.toCharArray();
        System.arraycopy(src, 0, chs, length - src.length, src.length);
        return new String(chs);

    }

    /**
     * 删除文件
     *
     * @param [fileName]
     * @return boolean
     * @author ywx
     * @date 2018/11/16 8:43
     */
    public static boolean delete(String fileName) {
        File f = new File(fileName);
        return f.exists() && f.delete() || !f.exists() && false;
    }

    /**
     * 递归获取指定目录下的所有的文件（不包括文件夹）
     *
     * @param [dirPath]
     * @return java.util.ArrayList<java.io.File>
     * @author ywx
     * @date 2018/11/16 8:46
     */
    public static List<File> getAllFiles(String dirPath) {
        File dir = new File(dirPath);
        List<File> files = new ArrayList<>();
        if (dir.isDirectory()) {
            File[] fileArr = dir.listFiles();
            for (File f : fileArr) {
                if (f.isFile()) {
                    files.add(f);
                } else {
                    files.addAll(getAllFiles(f.getPath()));
                }
            }
        }
        return files;
    }

    /**
     * 获取指定目录下的所有文件(不包括子文件夹)
     *
     * @param [dirPath]
     * @return java.util.ArrayList<java.io.File>
     * @author ywx
     * @date 2018/11/16 8:48
     */
    public static List<File> getDirFiles(String dirPath) {
        File path = new File(dirPath);
        File[] fileArr = path.listFiles();
        List<File> files = new ArrayList<>();
        for (File f : fileArr) {
            if (f.isFile()) {
                files.add(f);
            }
        }
        return files;
    }

    /**
     * 获取指定目录下特定文件后缀名的文件列表(不包括子文件夹)
     *
     * @param [dirPath, suffix]
     * @return java.util.ArrayList<java.io.File>
     * @author ywx
     * @date 2018/11/16 8:49
     */
    public static List<File> getDirFiles(String dirPath, final String suffix) {
        File path = new File(dirPath);
        File[] fileArr = path.listFiles((dir, name) -> {
            String lowerName = name.toLowerCase();
            String lowerSuffix = suffix.toLowerCase();
            return lowerName.endsWith(lowerSuffix);
        });
        List<File> files = new ArrayList<>();

        for (File f : fileArr) {
            if (f.isFile()) {
                files.add(f);
            }
        }
        return files;
    }

    /**
     * 读取文件内容(基于NIO)
     *
     * @param [fileName]
     * @return java.lang.String
     * @author ywx
     * @date 2018/11/16 8:57
     */
    public static String read(String fileName, Integer byteNum) throws IOException {
        File file = new File(fileName);
        FileInputStream fis = null;
        StringBuilder sb = new StringBuilder();

        try {
            fis = new FileInputStream(file);
            FileChannel channel = fis.getChannel();

            //允许读取文件最大字节数
            int capacity = byteNum;
            ByteBuffer bf = ByteBuffer.allocate(capacity);

            int length;
            while ((length = channel.read(bf)) != -1) {
                byte[] bytes = bf.array();
                sb.append(new String(bytes, StandardCharsets.UTF_8));
            }
            channel.close();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * 写文件
     *
     * @param fileName    目标文件名
     * @param fileContent 写入的内容
     * @return
     * @throws IOException
     */
    public static boolean write(String fileName, String fileContent) throws IOException {
        boolean result = false;
        File f = new File(fileName);
        FileOutputStream fs = new FileOutputStream(f);
        byte[] b = fileContent.getBytes();
        fs.write(b);
        fs.flush();
        fs.close();
        result = true;
        return result;
    }

    /**
     * 追加内容到指定文件
     *
     * @param fileName
     * @param fileContent
     * @return
     * @throws IOException
     */
    public static boolean append(String fileName, String fileContent) throws IOException {
        boolean result = false;
        File f = new File(fileName);
        if (f.exists()) {
            RandomAccessFile rFile = new RandomAccessFile(f, "rw");
            byte[] b = fileContent.getBytes();
            long originLen = f.length();
            rFile.setLength(originLen + b.length);
            rFile.seek(originLen);
            rFile.write(b);
            rFile.close();
        }
        result = true;
        return result;
    }

    /**
     * 拆分文件
     *
     * @param fileName 待拆分的完整文件名
     * @param byteSize 按多少字节大小拆分
     * @return 拆分后的文件名列表
     * @throws IOException
     */
    public List<String> splitBySize(String fileName, int byteSize) throws IOException {
        List<String> parts = new ArrayList<String>();
        File file = new File(fileName);
        int count = (int) Math.ceil(file.length() / (double) byteSize);
        int countLen = (count + "").length();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(count, count * 3, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(count * 2));

        for (int i = 0; i < count; i++) {
            String partFileName = file.getName() + "." + leftPad((i + 1) + "", countLen, '0') + ".part";
            threadPool.execute(new SplitRunnable(byteSize, i * byteSize, partFileName, file));
            parts.add(partFileName);
        }
        return parts;
    }

    /**
     * 合并文件
     *
     * @param dirPath        拆分文件所在目录名
     * @param partFileSuffix 拆分文件后缀名
     * @param partFileSize   拆分文件的字节数大小
     * @param mergeFileName  合并后的文件名
     * @throws IOException
     */
    public void mergePartFiles(String dirPath, String partFileSuffix, int partFileSize, String mergeFileName) throws IOException {
        List<File> partFiles = FileUtils.getDirFiles(dirPath, partFileSuffix);
        Collections.sort(partFiles, new FileComparator());

        RandomAccessFile randomAccessFile = new RandomAccessFile(mergeFileName, "rw");
        randomAccessFile.setLength(partFileSize * (partFiles.size() - 1) + partFiles.get(partFiles.size() - 1).length());
        randomAccessFile.close();

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(partFiles.size(), partFiles.size() * 3, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(partFiles.size() * 2));

        for (int i = 0; i < partFiles.size(); i++) {
            threadPool.execute(new MergeRunnable(i * partFileSize, mergeFileName, partFiles.get(i)));
        }

    }

    /**
     * 根据文件名，比较文件
     *
     * @author yjmyzz@126.com
     */
    private class FileComparator implements Comparator<File> {
        public int compare(File o1, File o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    }

    /**
     * 分割处理Runnable
     *
     * @author yjmyzz@126.com
     */
    private class SplitRunnable implements Runnable {
        int byteSize;
        String partFileName;
        File originFile;
        int startPos;

        public SplitRunnable(int byteSize, int startPos, String partFileName, File originFile) {
            this.startPos = startPos;
            this.byteSize = byteSize;
            this.partFileName = partFileName;
            this.originFile = originFile;
        }

        public void run() {
            RandomAccessFile rFile;
            OutputStream os;
            try {
                rFile = new RandomAccessFile(originFile, "r");
                byte[] b = new byte[byteSize];
                rFile.seek(startPos);// 移动指针到每“段”开头
                int s = rFile.read(b);
                os = new FileOutputStream(partFileName);
                os.write(b, 0, s);
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 合并处理Runnable
     *
     * @author yjmyzz@126.com
     */
    private class MergeRunnable implements Runnable {
        long startPos;
        String mergeFileName;
        File partFile;

        public MergeRunnable(long startPos, String mergeFileName, File partFile) {
            this.startPos = startPos;
            this.mergeFileName = mergeFileName;
            this.partFile = partFile;
        }

        public void run() {
            RandomAccessFile rFile;
            try {
                rFile = new RandomAccessFile(mergeFileName, "rw");
                rFile.seek(startPos);
                FileInputStream fs = new FileInputStream(partFile);
                byte[] b = new byte[fs.available()];
                fs.read(b);
                fs.close();
                rFile.write(b);
                rFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}