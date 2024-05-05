package minitienda;
import java.util.Vector;

public class ListaCDS {
    private Vector<CD> listaCD= new Vector<CD>();

    // Getters y setters para autor
    public Vector<CD> getListaCD() {
        return listaCD;
    }

    public void setListaCD(CD cd) {
        listaCD.add(cd);
    }
}
