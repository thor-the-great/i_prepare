import java.net.URL;

public class TestCurrentPath {

    public static void main(String[] args) {
        URL location = TestCurrentPath.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(location.getFile());
        //String currentFolder = getClass().getProtectionDomain().getCodeSource().getLocation().getFile();
        //String exportFilePath = currentFolder + File.separator + testCSVFileName;
    }
}
