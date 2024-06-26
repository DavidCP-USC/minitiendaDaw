package minitienda;
import java.util.Vector;
import java.io.Serializable;

public class ListaCDS implements Serializable {
    private Vector<CD> listaCD= new Vector<CD>();
    private float importe=new Float(0.f);
    private usuario user;

    // Getters y setters para autor
    public Vector<CD> getListaCD() {
        return listaCD;
    }

    public void setListaCD(CD cd) {
        listaCD.add(cd);
    }

    public Float getImporte() {
        return this.importe;
    }


    public void setImporte(Float importe) {
        this.importe = importe;
    }

    public void setUser(usuario user){
        this.user=user;
    }

    public usuario getUser(){
        return this.user;
    }
}
