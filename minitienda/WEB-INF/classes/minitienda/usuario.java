package minitienda;
public class usuario {
    private String correo;
    private String nombre;
    private String tipoTargeta;
    private int numeroTargeta;


    // Métodos getter y setter para correo
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Métodos getter y setter para nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
