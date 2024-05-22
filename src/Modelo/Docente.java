
package Modelo;

public class Docente {
     private String cod_docente;
    private String nom_docente;
    private String ape_docente;

    public Docente(String cod_docente, String nom_docente, String ape_docente) {
        this.cod_docente = cod_docente;
        this.nom_docente = nom_docente;
        this.ape_docente = ape_docente;
    }

    public Docente() {
    }

    public String getCod_docente() {
        return cod_docente;
    }

    public void setCod_docente(String cod_docente) {
        this.cod_docente = cod_docente;
    }

    public String getNom_docente() {
        return nom_docente;
    }

    public void setNom_docente(String nom_docente) {
        this.nom_docente = nom_docente;
    }

    public String getApe_docente() {
        return ape_docente;
    }

    public void setApe_docente(String ape_docente) {
        this.ape_docente = ape_docente;
    }
}
