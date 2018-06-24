package cn.edu.haue.graduate.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;


/**
 * 转换json工具类
 * Created by 杨晋升 on 2018/6/21.
 */
public class JsonUtils {

    public static String loadJsonFile(String fileName) throws IOException {
        File file = ResourceUtils.getFile("classpath:" + fileName);
        return FileUtils.readFileToString(file, "utf-8");
    }
}
