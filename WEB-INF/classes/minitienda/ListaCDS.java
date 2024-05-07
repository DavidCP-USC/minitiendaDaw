package minitienda;
import java.util.Vector;

public class ListaCDS {
    private Vector<CD> listaCD= new Vector<CD>();
    private float importe=new Float(0.f);

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
}
