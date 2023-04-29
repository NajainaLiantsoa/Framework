package etu1547.framework.servlet;

import etu1547.util.Util;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

public class FrontServlet extends HttpServlet {

    String url;
    Util util;
    
    @Override
    public void init() throws ServletException {
        super.init();
        util=new Util();
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
