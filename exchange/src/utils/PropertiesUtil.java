package utils;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by william on 2017/7/1.
 */

public class PropertiesUtil {

    private static Properties props=null;

    /**
     * 获取上传路径的配置文件的某个参数值
     * @param name
     * @return
     */
    public static String getInfoPropertyByName(String name) {
        return getPropertyByNamePath("info.properties", name);
    }


    /**
     * 获取某位置的某属性值
     * @param path
     * @param name
     * @return
     */
    public static String getPropertyByNamePath(String path, String name) {
        String result = "";

        // 方法二：通过类加载目录getClassLoader()加载属性文件
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(path);
        Properties prop = new Properties();
        try {
            prop.load(in);
            result = new String(prop.getProperty(name).trim().getBytes("ISO8859-1"),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * test
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(getInfoPropertyByName( "item_image_path"));
    }

}




