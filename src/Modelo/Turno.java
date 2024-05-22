
package Modelo;

public class Turno {
      private String cod_T;
    private String nom_turno;

    public Turno() {
    }

    public Turno(String cod_T, String nom_turno) {
        this.cod_T = cod_T;
        this.nom_turno = nom_turno;
    }

    public String getCod_T() {
        return cod_T;
    }

    public void setCod_T(String cod_T) {
        this.cod_T = cod_T;
    }

    public String getNom_turno() {
        return nom_turno;
    }

    public void setNom_turno(String nom_turno) {
        this.nom_turno = nom_turno;
    }
    
}
