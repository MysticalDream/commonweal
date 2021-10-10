package team.skylinekids.commonweal.utils;


import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.UUID;


/**
 * 文件工具类
 *
 * @author MysticalDream
 */
public class FileUtils {

    /**
     * 1kb
     */
    public static final long ONE_KB = 1024;
    /**
     * 大整型类型1KB
     */
    public static final BigInteger ONE_KB_BI = BigInteger.valueOf(ONE_KB);
    /**
     * 1MB
     */
    public static final long ONE_MB = ONE_KB * ONE_KB;
    /**
     * 大整型类型1MB
     */
    public static final BigInteger ONE_MB_BI = ONE_KB_BI.multiply(ONE_KB_BI);

    /**
     * 1GB
     */
    public static final long ONE_GB = ONE_KB * ONE_MB;
    /**
     * 大整型类型1GB
     */
    public static final BigInteger ONE_GB_BI = ONE_KB_BI.multiply(ONE_MB_BI);

    /**
     * 文件复制缓存大小
     */
    private static final long FILE_COPY_BUFFER_SIZE = ONE_MB * 30;
    /**
     * 空文件数组
     */
    public static final File[] EMPTY_FILE_ARRAY = new File[0];
    /**
     * 头像地址
     */
    private static final String AVATAR_BASE_URL = "D:/commonweal/images/avatar/";
    /**
     * 视频地址
     */
    private static final String VIDEO_BASE_URL = "D:/commonweal/video/";


    /**
     * 私有化构造器
     */
    private FileUtils() {

    }

    /**
     * 获取指定文件
     *
     * @param path 文件路径
     * @return
     */
    public static File getFile(String path) {
        return new File(path);
    }

    /**
     * 创建文件，如果文件存在则更新时间；
     * 如果不存在，创建一个空文件
     *
     * @param file
     * @return
     */
    public static boolean touch(File file) {
        try {
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
                return true;
            } else {
                file.setLastModified(System.currentTimeMillis());
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 安静删除目录或文件，无法删除时也不会抛异常
     *
     * @param file
     */
    public static void deleteQuietly(File file) {
        Path path = file.toPath();
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println("visit:" + file);
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    System.out.println("Prev:" + dir);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    System.out.println("failed:" + file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    System.out.println("dir:" + dir);
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件名
     *
     * @param part
     * @return
     */
    public static String getFileName(Part part) {
        //类似于form-data; name="file"; filename="p1.png"
        // ie浏览器:form-data; name="file"; filename="D:\图片\p1.png"
        String contentDispositionHeader = part.getHeader("content-disposition");
        String[] elements = contentDispositionHeader.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return new File(element.substring(element.indexOf('=') + 1).trim().replace("\"", "")).getName();
            }
        }
        return null;
    }

    /**
     * 保存头像
     *
     * @param part
     * @return
     * @throws IOException
     */
    public static String saveAvatarByPart(Part part) throws IOException {
        //文件名
        String fileName = getRandomFileName(getFileName(part));
        //文件对象
        File file = new File(AVATAR_BASE_URL + fileName);
        //输出流
        FileOutputStream outputStream = new FileOutputStream(file);
        //输入流
        InputStream inputStream = part.getInputStream();
        //缓存区
        byte[] buff = new byte[1024];
        //读取长度
        int len = 0;

        while ((len = inputStream.read(buff)) > 0) {
            outputStream.write(buff, 0, len);
        }
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(outputStream);
        return fileName;

    }

    /**
     * 获取新的随机文件名
     *
     * @param fileName
     * @return
     */
    public static String getRandomFileName(String fileName) {
        if (fileName == null) {
            return null;
        }
        int index = fileName.lastIndexOf(".");
        //获取后缀名
        String suffix = fileName.substring(index);

        String uuidFileName = UUID.randomUUID().toString().replace("-", "") + suffix;
        return uuidFileName;
    }

}
