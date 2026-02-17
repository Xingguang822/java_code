import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class test {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("test_code//a.txt");
        String str="keguanlaoyezuishuai\r\n666";
        byte[] bytes=str.getBytes();
        fos.write(bytes);
        fos.close();
    }
}
