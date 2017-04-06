package com.epam.it_week;

import java.io.*;
import java.nio.file.Paths;

/**
 * Created by Sergey on 06.04.2017.
 */
public class NativeUtils {
    public interface LoaderWrap{
        void load(String Path);
    }

    private NativeUtils() {
    }

    public static void loadLibraryFromJar(String path, LoaderWrap wrap) throws IOException {
        File file = Paths.get(path).toFile();
        if(file.isDirectory()){
            throw new FileNotFoundException("Required to specify the path to the file inside JAR.");
        }

        String fileName = file.getName();
        int extensionIndex = fileName.lastIndexOf(".");
        String fileExtension = fileName.substring(extensionIndex + 1);
        String name = fileName.substring(0, extensionIndex);

        File temp = File.createTempFile(name, fileExtension);
        temp.deleteOnExit();

        if (!temp.exists()) {
            throw new FileNotFoundException("File " + temp.getAbsolutePath() + " does not exist.");
        }

        byte[] buffer = new byte[1024];
        int readBytes;

        InputStream is = NativeUtils.class.getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException("File " + path + " was not found inside JAR.");
        }

        OutputStream os = new FileOutputStream(temp);
        try {
            while ((readBytes = is.read(buffer)) != -1) {
                os.write(buffer, 0, readBytes);
            }
        } finally {
            os.close();
            is.close();
        }

        wrap.load(temp.getAbsolutePath());
    }
}
