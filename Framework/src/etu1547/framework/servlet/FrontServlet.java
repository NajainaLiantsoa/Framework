package etu1547.framework.servlet;

import etu1547.framework.AnnotationMethod;
import etu1547.framework.Mapping;
import etu1547.util.Util;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public class FrontServlet extends HttpServlet {

    String url;
    Util util;
    protected HashMap<String, Mapping> mappingURL;
    @Override
    public void init() throws ServletException {
        super.init();
        util=new Util();
        mappingURL=new HashMap<>();
        try {
            final String tomPath = "/WEB-INF/classes/";
            String path = getServletContext().getRealPath(tomPath);
            List<Class<?>> allClass = util.getAllClass(path, tomPath);

            Mapping mapping;
            Method[] allMethods;
            for(Class<?> c : allClass) {
                allMethods = c.getMethods();

                for(Method m : allMethods) {
                    if(m.isAnnotationPresent(AnnotationMethod.class)) {
                        mapping = new Mapping();
                        mapping.setClassname(c.getName());
                        mapping.setMethod(m.getName());
                        mappingURL.put(m.getAnnotation(AnnotationMethod.class).url(), mapping);

                    }
                }
            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        processRequest(req, resp);

    }


    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        url = util.processURL(req.getRequestURL().toString(),req.getContextPath());

    }
}
