package top.b0x0.mybatis.io;

import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * @author tlh Created By 2022-07-24 18:50
 **/
public class Resources {

    public static List<Reader> getResourceAsReaders(String packageName) throws IOException {
        List<Reader> readers = new ArrayList<>();
        for (ClassLoader classLoader : getClassLoader()) {
            if (classLoader != null) {
                String fileDir = "";
                Enumeration<URL> resources = classLoader.getResources(packageName);
                while (resources.hasMoreElements()) {
                    URL nextElement = resources.nextElement();
                    String path = nextElement.getPath();
                    fileDir = path.replaceFirst("/", "");
                }
                File file = new File(fileDir);
                if (file.isDirectory()) {
                    // TODO: 2022/7/25 read resource file
//                    Reader resourceAsReader = getResourceAsReader();
//                    readers.add(resourceAsReader);
                }
                return readers;
            }
        }
        return readers;
    }

    public static Reader getResourceAsReader(String resource) throws IOException {
        return new InputStreamReader(getResourceAsStream(resource));
    }

    public static InputStream getResourceAsStream(String resource) throws IOException {
        for (ClassLoader classLoader : getClassLoader()) {
            if (classLoader != null) {
                InputStream resourceAsStream = classLoader.getResourceAsStream(resource);
                if (resourceAsStream != null) {
                    return resourceAsStream;
                }
            }
        }
        throw new IOException("Could not find resource " + resource);
    }

    private static ClassLoader[] getClassLoader() {
        ClassLoader[] classLoader = new ClassLoader[]{
                // TODO: 2022/7/24 system classloader
//                ClassLoader.getSystemClassLoader(),
                Thread.currentThread().getContextClassLoader()
        };
        return classLoader;
    }

    public static Class<?> classForName(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }
}
