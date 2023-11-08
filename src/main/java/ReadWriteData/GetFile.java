package ReadWriteData;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class GetFile {

    private final File file;

    public GetFile() {
        URL url = this.getClass().getClassLoader().getResource("data.xml");
        String path = URLDecoder.decode(url == null ? "" : url.getFile(), StandardCharsets.UTF_8);
        file = new File(path);
    }

    public File getFile() {
        return file;
    }
}
