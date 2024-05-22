package Modelo;

import java.math.BigDecimal;

public class Notas {

    private String cod_notas;
    private String cod_alumno;
    private String nom_alumno;
    private String ape_alumno;
    private String cod_curso;
    private String nom_curso;
    private BigDecimal not1;
    private BigDecimal not2;
    private BigDecimal not3;
    private BigDecimal not4;
    private BigDecimal exame_p;
        private BigDecimal exame_f;
        private BigDecimal promedio;

    public Notas() {
    }

    public Notas(String cod_notas, String cod_alumno, String nom_alumno, String ape_alumno, String cod_curso, String nom_curso, BigDecimal not1, BigDecimal not2, BigDecimal not3, BigDecimal not4, BigDecimal exame_P, BigDecimal exame_F, BigDecimal promedio) {
        this.cod_notas = cod_notas;
        this.cod_alumno = cod_alumno;
        this.nom_alumno = nom_alumno;
        this.ape_alumno = ape_alumno;
        this.cod_curso = cod_curso;
        this.nom_curso = nom_curso;
        this.not1 = not1;
        this.not2 = not2;
        this.not3 = not3;
        this.not4 = not4;
        this.exame_p = exame_P;
        this.exame_f = exame_F;
        this.promedio = promedio;
    }

    public String getCod_notas() {
        return cod_notas;
    }

    public void setCod_notas(String cod_notas) {
        this.cod_notas = cod_notas;
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

    public BigDecimal getNot1() {
        return not1;
    }

    public void setNot1(BigDecimal not1) {
        this.not1 = not1;
    }

    public BigDecimal getNot2() {
        return not2;
    }

    public void setNot2(BigDecimal not2) {
        this.not2 = not2;
    }

    public BigDecimal getNot3() {
        return not3;
    }

    public void setNot3(BigDecimal not3) {
        this.not3 = not3;
    }

    public BigDecimal getNot4() {
        return not4;
    }

    public void setNot4(BigDecimal not4) {
        this.not4 = not4;
    }

    public BigDecimal getExame_p() {
        return exame_p;
    }

    public void setExame_p(BigDecimal exame_P) {
        this.exame_p = exame_P;
    }

    public BigDecimal getExame_f() {
        return exame_f;
    }

    public void setExame_f(BigDecimal exame_F) {
        this.exame_f = exame_F;
    }

    public BigDecimal getPromedio() {
        return promedio;
    }

    public void setPromedio(BigDecimal promedio) {
        this.promedio = promedio;
    }

  
}
