package Modelo;

public class Alumno {

    private String cod_alumno;
    private String nom_alumno;
    private String ape_alumno;
    private String sexo;

    public Alumno() {
    }

    public Alumno(String cod_alumno, String nom_alumno, String ape_alumno, String sexo) {
        this.cod_alumno = cod_alumno;
        this.nom_alumno = nom_alumno;
        this.ape_alumno = ape_alumno;
        this.sexo = sexo;
    }

    public String getCod_alumno() {
        return cod_alumno;
    }

    public void setCod_alumno(String cod_alumno) {
        this.cod_alumno = cod_alumno;
    }

    public String getNom_alumno() {
        return nom_alumno;
    }

    public void setNom_alumno(String nom_alumno) {
        this.nom_alumno = nom_alumno;
    }

    public String getApe_alumno() {
        return ape_alumno;
    }

    public void setApe_alumno(String ape_alumno) {
        this.ape_alumno = ape_alumno;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

}
