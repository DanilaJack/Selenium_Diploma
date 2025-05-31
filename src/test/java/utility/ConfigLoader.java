package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

    private Properties properties = new Properties();
    public ConfigLoader(){
        try(FileInputStream fis = new FileInputStream("src/test/resources/config.properties")){
            properties.load(fis);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }

}
