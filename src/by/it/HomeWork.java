package by.it;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class HomeWork {
    String PACKAGE = "by.it.group151051.voronko";

    public Set<Class> findAllClassesUsingClassLoader(String packageName) {
        var pkg = findPackageNamesStartingWith(PACKAGE).get(0);

        var stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(pkg.replaceAll("[.]", "/"));
        var reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, pkg))
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

    public List<String> findPackageNamesStartingWith(String prefix) {
        return Arrays.stream(Package.getPackages())
                .map(Package::getName)
                .filter(n -> n.startsWith(prefix))
                .collect(toList());
    }
}