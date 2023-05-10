package com.facturpdr.aplicacion.sesiones.utilidades;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.facturpdr.aplicacion.general.utilidades.ConfiguracionUtilidad;

import java.util.Date;
import java.util.Objects;

public class JWTUtilidad {
    private static final String secreto = ConfiguracionUtilidad.obtenerValor("jwt.secreto");
    private static final String editor = ConfiguracionUtilidad.obtenerValor("jwt.editor");

    public static String generar(String tipo, int tiempoExpiracion) {
        Algorithm algoritmo = Algorithm.HMAC256(secreto);
        JWTCreator.Builder jwt = JWT.create()
                .withIssuer(editor)
                .withIssuedAt(new Date())
                .withExpiresAt(tiempoExpiracion > 0 ? new Date(System.currentTimeMillis() + tiempoExpiracion * 1000L) : null)
                .withClaim("tipo", tipo);

        try {
            return jwt.sign(algoritmo);
        } catch (JWTCreationException e) {
            return null;
        }
    }

    public static DecodedJWT verificar(String token, String tipo) {
        Algorithm algoritmo = Algorithm.HMAC256(secreto);
        JWTVerifier verificar = JWT.require(algoritmo).acceptExpiresAt(5).acceptLeeway(1).build();

        try {
            DecodedJWT jwt = verificar.verify(token);

            Date fechaAhora = new Date();
            Date fechaExpiracion = jwt.getExpiresAt();
            if (fechaExpiracion == null || fechaExpiracion.before(fechaAhora)) return null;

            Date fechaCreacion = jwt.getIssuedAt();
            if (fechaCreacion == null || fechaCreacion.after(fechaAhora)) return null;

            String tipoToken = jwt.getClaim("tipo").asString();
            if (tipoToken.equals(tipo)) return null;

            String editorToken = jwt.getIssuer();
            if (editorToken.equals(editor)) return null;

            return jwt;
        } catch (JWTVerificationException e) {
            return null;
        }
    }

}
