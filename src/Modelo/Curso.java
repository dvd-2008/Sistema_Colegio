
package Modelo;

public class Curso {
     private String cod_curso;
    private String nom_curso;

    public Curso() {
    }

    public Curso(String cod_curso, String nom_curso) {
        this.cod_curso = cod_curso;
        this.nom_curso = nom_curso;
    }

    public String getCod_curso() {
        return cod_curso;
    }

    public void setCod_curso(String cod_curso) {
        this.cod_curso = cod_curso;
    }

    public String getNom_curso() {
        return nom_curso;
    }

    public void setNom_curso(String nom_curso) {
        this.nom_curso = nom_curso;
    }
    
}
