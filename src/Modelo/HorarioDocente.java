
package Modelo;
import java.sql.Time;

public class HorarioDocente {
    private String ID_HORARIO_DOCENTE;
    private String cod_docente;
    private String cod_T;
    private String cod_Aula;
    private String cod_secc;
    private String cod_curso;
    private String nom_docente;
    private String ape_docente;
    private String nom_turno;
    private String nom_Aula;
    private String nom_secc;
     private String nom_curso;
    private Time hora_ini_d;
    private Time hora_fin_d;

    public HorarioDocente() {
    }

    public HorarioDocente(String ID_HORARIO_DOCENTE, String cod_docente, String cod_T, String cod_Aula, String cod_secc, String cod_curso, String nom_docente, String ape_docente, String nom_turno, String nom_Aula, String nom_secc, String nom_curso, Time hora_ini_d, Time hora_fin_d) {
        this.ID_HORARIO_DOCENTE = ID_HORARIO_DOCENTE;
        this.cod_docente = cod_docente;
        this.cod_T = cod_T;
        this.cod_Aula = cod_Aula;
        this.cod_secc = cod_secc;
        this.cod_curso = cod_curso;
        this.nom_docente = nom_docente;
        this.ape_docente = ape_docente;
        this.nom_turno = nom_turno;
        this.nom_Aula = nom_Aula;
        this.nom_secc = nom_secc;
        this.nom_curso = nom_curso;
        this.hora_ini_d = hora_ini_d;
        this.hora_fin_d = hora_fin_d;
    }

    public String getID_HORARIO_DOCENTE() {
        return ID_HORARIO_DOCENTE;
    }

    public void setID_HORARIO_DOCENTE(String ID_HORARIO_DOCENTE) {
        this.ID_HORARIO_DOCENTE = ID_HORARIO_DOCENTE;
    }

    public String getCod_docente() {
        return cod_docente;
    }

    public void setCod_docente(String cod_docente) {
        this.cod_docente = cod_docente;
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

    public String getCod_curso() {
        return cod_curso;
    }

    public void setCod_curso(String cod_curso) {
        this.cod_curso = cod_curso;
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

    public String getNom_curso() {
        return nom_curso;
    }

    public void setNom_curso(String nom_curso) {
        this.nom_curso = nom_curso;
    }

    public Time getHora_ini_d() {
        return hora_ini_d;
    }

    public void setHora_ini_d(Time hora_ini_d) {
        this.hora_ini_d = hora_ini_d;
    }

    public Time getHora_fin_d() {
        return hora_fin_d;
    }

    public void setHora_fin_d(Time hora_fin_d) {
        this.hora_fin_d = hora_fin_d;
    }
    
    
}
