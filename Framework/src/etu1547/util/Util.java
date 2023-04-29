package etu1547.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public String processURL(String url_input,String context){
        context+="/";
        int context_ind_begin=url_input.indexOf(context);
        return url_input.substring(context_ind_begin + context.length());
    }
    public List<Class<?>> getAllClass(String path, String tomPath) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        File directory = new File(path);

        if (!directory.exists()) {
            return classes;
        }

        File[] files = directory.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isDirectory()) {
                List<Class<?>> innerClasses = this.getAllClass(file.getAbsolutePath(), tomPath);
                classes.addAll(innerClasses);
            } else if (file.getName().endsWith(".class")) {
                String absolute_path_class = file.getPath().replace("\\", "/");
                int tom_int_path = absolute_path_class.indexOf(tomPath);

                String className = absolute_path_class.substring(tom_int_path + tomPath.length())
                        .replace(".class", "")
                        .replace("/", ".");
                Class<?> clazz = Class.forName(className);

                classes.add(clazz);
            }
        }
        return classes;
    }
}
