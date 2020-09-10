import java.io.FileInputStream;
import java.io.IOException;

public class fileReader {
    String sendData(String fileName) throws IOException {
        int ch;
        String data = "";
        FileInputStream fin = new FileInputStream(fileName);
        while ((ch = fin.read()) != -1)
            data += (char) ch;
        fin.close();
        return data;
    }
}
