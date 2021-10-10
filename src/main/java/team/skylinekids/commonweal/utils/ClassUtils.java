package team.skylinekids.commonweal.utils;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 类工具
 *
 * @author MysticalDream
 */
public final class ClassUtils {
    /**
     * 是否循环迭代
     */
    private static boolean recursive = true;

    private static Logger logger = Logger.getLogger(ClassUtils.class);

    /**
     * 获取包下的class
     *
     * @param packName
     * @return
     */
    public static Set<Class<?>> getClasses(String packName) {
        // 存放类的集合
        Set<Class<?>> classes = new LinkedHashSet<>();
        //将包名的.换成路径
        String packNameDir = packName.replace('.', File.separatorChar);
        //定义一个枚举的集合 并进行循环来处理这个目录下的文件
        Enumeration<URL> dirs = null;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packNameDir);
            while (dirs.hasMoreElements()) {
                //类似于:file:/E:/IDEA_Project/commonweal/target/classes/team/skylinekids/commonweal/controller
                URL url = dirs.nextElement();
                //获取协议
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    //类似于/E:/IDEA_Project/commonweal/target/classes/team/skylinekids/commonweal/controller
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    findAndAddClassesInPackageByFile(packName, filePath, classes);
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return classes;
    }

    /**
     * 以文件形式获取包下的所有Class
     *
     * @param packageName 包名
     * @param filePath    文件路径
     */
    public static void findAndAddClassesInPackageByFile(String packageName, String filePath, Set<Class<?>> set) {
        File dir = new File(filePath);
        //如果文件不存在或则不是一个目录直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            logger.debug("包:" + packageName + "下没有任何文件");
            return;
        }
        //文件过滤规则
        File[] files = dir.listFiles(file -> {
            //file类似于E:\IDEA_Project\commonweal\target\classes\team\skylinekids\commonweal\controller\TestController.class
            return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
        });

        for (File file :
                files) {
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), set);
            } else {
                //去掉.class留下类名
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    //加载类
                    Class<?> loadClass = Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className);
                    //将扫描到的类添加到集合
                    set.add(loadClass);

                } catch (ClassNotFoundException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 是否迭代循环
     *
     * @param recursive
     */
    public static void setRecursive(boolean recursive) {
        ClassUtils.recursive = recursive;
    }

}
