package com.facturpdr.aplicacion.sesiones.utilidades;

import java.io.IOException;
import java.net.InetAddress;

public class InternetUtilidad {
    public static boolean estaConectado() {
        try {
            InetAddress address = InetAddress.getByName("www.varimadrid.es");
            return address.isReachable(3000);
        } catch (IOException e) {
            return false;
        }
    }
}
