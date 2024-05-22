
package Modelo;


public class Seccion {
     private String cod_secc;
    private String nom_secc;

    public Seccion() {
    }

    public Seccion(String cod_secc, String nom_secc) {
        this.cod_secc = cod_secc;
        this.nom_secc = nom_secc;
    }

    public String getCod_secc() {
        return cod_secc;
    }

    public void setCod_secc(String cod_secc) {
        this.cod_secc = cod_secc;
    }

    public String getNom_secc() {
        return nom_secc;
    }

    public void setNom_secc(String nom_secc) {
        this.nom_secc = nom_secc;
    }
    
}
