package utils;

import action.ItemAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Date;

/**
 * Created by william on 2017/7/1.
 */
public class FileUtils {
    static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private static boolean upload(String path, File file, String filename) {
        InputStream  is;
        OutputStream os;
        try {
            is = new FileInputStream(file);
            File folder = new File(path);
            if (!folder.exists() && !folder.isDirectory()) {
                if (folder.mkdirs()) {
                    logger.debug("Create Direction(" + path + ") finish");
                } else {
                    logger.debug("Create Direction(" + path + ") fail");
                }
            }
            File toFile = new File(path, filename);
            os = new FileOutputStream(toFile);
            byte[] buffer = new byte[1024];
            for (int length = 0; (length = is.read(buffer)) > 0; ) {
                os.write(buffer, 0, length);
            }
            is.close();
            os.close();
            logger.debug("file write finish");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static String itemImgUpload(File file,String suffix) {
        String   item_image_path = PropertiesUtil.getInfoPropertyByName("item_image_path");
        String   newname         = new Date().getTime() + "." + suffix;
        if (upload(item_image_path, file, newname)) {
            return newname;
        } else {
            return null;
        }
    }




    private static byte[] download(String path,String filename) {
        File file=new File(path,filename);
        if(file.exists()&&file.isFile()){
            return fileToByteArrays(file);
        }else{
            logger.info("此文件不存在");
            return null;
        }
    }


    /**
     * 文件转换为byte数组
     *
     * @author jwang
     * @date 2013.4.24
     * @return
     */
    public static byte[] fileToByteArrays(File file) {
        byte[] buff = null;
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(file));
            buff = new byte[is.available()]; // available适合于本地文件读取，不适用于网络文件
            is.read(buff);
        } catch (IOException ex) {
            logger.debug("读取出错");
            ex.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                logger.debug("关闭文件流出错");
                ex.printStackTrace();
            }
        }
        return buff;
    }

    /**
     * 下载item文件
     * @param filename
     * @return
     */
    public static byte[] itemImgDownload(String filename) {
        String   item_image_path = PropertiesUtil.getInfoPropertyByName("item_image_path");
        return download(item_image_path,filename);
    }





}
