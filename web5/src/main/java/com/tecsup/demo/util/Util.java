package com.tecsup.demo.util;

import java.util.ResourceBundle;

public class Util {

    public static final Tipo OPC;
    public static final String ERROR1;
    public static final String ERROR2;

    static {
        ResourceBundle properties = ResourceBundle.getBundle("config");
        OPC = Tipo.valueOf(properties.getString("opc"));
        ERROR1 = properties.getString("error1");
        ERROR2 = properties.getString("error2");
    }

    public static final String RUTA = "/fotos/fotos.txt";
}
