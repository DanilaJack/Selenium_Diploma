package utility;

public class ProjectSettings {

    private static ConfigLoader config = new ConfigLoader();

    public static String PASSWORD = config.getProperty("PASSWORD");
    public static String USERNAME = config.getProperty("USER_NAME");
    public static boolean FILE_DETECTOR_INCLUDED =  Boolean.getBoolean(config.getProperty("FILE_DETECTOR"));

    public static String DOWNLOADS_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\downloads\\";
    public static String RESOURCES_PATH_FILES_FOR_SCENARIOS = System.getProperty("user.dir") + "/src/test/resources/filesForScenarios/";


}
