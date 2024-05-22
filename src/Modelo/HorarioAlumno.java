package Modelo;

import java.sql.Time;

public class HorarioAlumno {

    private String ID_HORARIO_ALUMNO;
    private String cod_alumno;
    private String cod_T;
    private String cod_Aula;
    private String cod_secc;
    private String nom_alumno;
    private String ape_alumno;
    private String nom_turno;
    private String nom_Aula;
    private String nom_secc;
    private Time hora_ini_a;
    private Time hora_fin_a;

    public HorarioAlumno() {
    }

    public HorarioAlumno(String ID_HORARIO_ALUMNO, String cod_alumno, String cod_T, String cod_Aula, String cod_secc, String nom_alumno, String ape_alumno, String nom_turno, String nom_Aula, String nom_secc, Time hora_ini_a, Time hora_fin_a) {
        this.ID_HORARIO_ALUMNO = ID_HORARIO_ALUMNO;
        this.cod_alumno = cod_alumno;
        this.cod_T = cod_T;
        this.cod_Aula = cod_Aula;
        this.cod_secc = cod_secc;
        this.nom_alumno = nom_alumno;
        this.ape_alumno = ape_alumno;
        this.nom_turno = nom_turno;
        this.nom_Aula = nom_Aula;
        this.nom_secc = nom_secc;
        this.hora_ini_a = hora_ini_a;
        this.hora_fin_a = hora_fin_a;
    }

    public String getID_HORARIO_ALUMNO() {
        return ID_HORARIO_ALUMNO;
    }

    public void setID_HORARIO_ALUMNO(String ID_HORARIO_ALUMNO) {
        this.ID_HORARIO_ALUMNO = ID_HORARIO_ALUMNO;
    }

    public String getCod_alumno() {
        return cod_alumno;
    }

    public void setCod_alumno(String cod_alumno) {
        this.cod_alumno = cod_alumno;
    }

    public String getCod_T() {
        return cod_T;
    }

    public void setCod_T(String cod_T) {
        this.cod_T = cod_T;
    }

    public String getCod_Aula() {
        return cod_Aula;
    }

    public void setCod_Aula(String cod_Aula) {
        this.cod_Aula = cod_Aula;
    }

    public String getCod_secc() {
        return cod_secc;
    }

    public void setCod_secc(String cod_secc) {
        this.cod_secc = cod_secc;
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

    public String getNom_turno() {
        return nom_turno;
    }

    public void setNom_turno(String nom_turno) {
        this.nom_turno = nom_turno;
    }

    public String getNom_Aula() {
        return nom_Aula;
    }

    public void setNom_Aula(String nom_Aula) {
        this.nom_Aula = nom_Aula;
    }

    public String getNom_secc() {
        return nom_secc;
    }

    public void setNom_secc(String nom_secc) {
        this.nom_secc = nom_secc;
    }

    public Time getHora_ini_a() {
        return hora_ini_a;
    }

    public void setHora_ini_a(Time hora_ini_a) {
        this.hora_ini_a = hora_ini_a;
    }

    public Time getHora_fin_a() {
        return hora_fin_a;
    }

    public void setHora_fin_a(Time hora_fin_a) {
        this.hora_fin_a = hora_fin_a;
    }

}
