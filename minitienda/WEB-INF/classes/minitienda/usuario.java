package minitienda;

import java.io.Serializable;

public class usuario implements Serializable{
    private String correo;
    private String contrasena;
    private String tipotarjeta;
    private String numerotarjeta;


    // Métodos getter y setter para correo
    protected String getCorreo() {
        return correo;
    }

    protected void setCorreo(String correo) {
        this.correo = correo;
    }

    // Métodos getter y setter para nombre
    protected String getContrasena() {
        return contrasena;
    }

    protected void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // Métodos getter y setter para tipotarjeta
    protected String getTipoTarjeta() {
        return tipotarjeta;
    }

    protected void setTipoTarjeta(String tipotarjeta) {
        this.tipotarjeta = tipotarjeta;
    }

    // Métodos getter y setter para numerotarjeta
    protected String getNumeroTarjeta() {
        return numerotarjeta;
    }

    protected void setNumeroTarjeta(String numerotarjeta) {
        this.numerotarjeta = numerotarjeta;
    }

}
