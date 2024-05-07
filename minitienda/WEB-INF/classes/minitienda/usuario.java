package minitienda;
public class usuario {
    private String correo;
    private String contrasinal;
    private String tipoTargeta;
    private int numeroTargeta;


    // Métodos getter y setter para correo
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Métodos getter y setter para contrasinal
    public String getContrasinal() {
        return contrasinal;
    }

    public void setContrasinal(String contrasinal) {
        this.contrasinal = contrasinal;
    }

    // Métodos getter y setter para tipoTargeta
    public String getTipoTargeta() {
        return tipoTargeta;
    }

    public void setTipoTargeta(String tipoTargeta) {
        this.tipoTargeta = tipoTargeta;
    }

    // Métodos getter y setter para numeroTargeta
    public int getNumeroTargeta() {
        return numeroTargeta;
    }

    public void setNumeroTargeta(int numeroTargeta) {
        this.numeroTargeta = numeroTargeta;
    }

}
