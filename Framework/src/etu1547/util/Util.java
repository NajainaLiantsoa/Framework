package etu1547.util;

public class Util {
    public String processURL(String url_input,String context){
        context+="/";
        int context_ind_begin=url_input.indexOf(context);
        return url_input.substring(context_ind_begin + context.length());
    }
}
