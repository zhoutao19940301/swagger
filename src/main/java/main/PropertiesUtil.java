package main;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @Descriprion: TODO
 * @Author: zhoutao
 * @Date: 2019/11/10
 **/
public class PropertiesUtil {

    public static String getProperties(String keyWord){
        String suffix = null;
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties("generator.properties");
            suffix = properties.getProperty(keyWord);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return suffix;
    }

}
