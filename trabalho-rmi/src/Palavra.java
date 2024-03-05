import java.io.Serializable;

public class Palavra implements Serializable {
    private static final long serialVersionUID = 1L;
    public String palavra;
    public String significado;

    public Palavra(String p, String s) {
        this.palavra = p;
        this.significado = s;
    }
}
