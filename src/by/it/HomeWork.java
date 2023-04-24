package by.it;

import java.io.*;
import java.util.Set;
import java.util.stream.Collectors;

public class HomeWork {
    String PACKAGE = "by.it.group151051.voronko.lesson09";

    public Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }

    public Class findClass(String name){
        var classes = findAllClassesUsingClassLoader(PACKAGE);
        return classes.stream().filter(c -> c.getSimpleName().equals(name)).findFirst().orElse(null);
    }
}