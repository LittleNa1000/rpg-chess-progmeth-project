package util;

public class StringUtil {
    public static String getCss(String... css) {
        String cssOutput = "";
        for (String style : css) {
            cssOutput += style;
            cssOutput += "; ";
        }
        return cssOutput;
    }

    public static String getImageUrl(String fileName) {
        return "file:resources/" + fileName;
    }

}
