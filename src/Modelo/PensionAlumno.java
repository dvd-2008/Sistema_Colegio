package Modelo;

import java.math.BigDecimal;
import java.util.Date;

public class PensionAlumno {

    private String cod_pension;
    private String cod_alumno;
    private String nom_alumno;
    private String cod_cate;
    private String nom_cate;
    private String cat_pension;
    private String cod_notas;
    private BigDecimal promedio;
    private BigDecimal pension;
    private Date fecha_cancelar;
    private BigDecimal pension_actual;
    private BigDecimal pension_nueva;

    public PensionAlumno() {
    }

    public PensionAlumno(String cod_pension, String cod_alumno, String nom_alumno, String cod_cate, String nom_cate, String cat_pension, String cod_notas, BigDecimal promedio, BigDecimal pension, Date fecha_cancelar, BigDecimal pension_actual, BigDecimal pension_nueva) {
        this.cod_pension = cod_pension;
        this.cod_alumno = cod_alumno;
        this.nom_alumno = nom_alumno;
        this.cod_cate = cod_cate;
        this.nom_cate = nom_cate;
        this.cat_pension = cat_pension;
        this.cod_notas = cod_notas;
        this.promedio = promedio;
        this.pension = pension;
        this.fecha_cancelar = fecha_cancelar;
        this.pension_actual = pension_actual;
        this.pension_nueva = pension_nueva;
    }

    public String getCod_pension() {
        return cod_pension;
    }

    public void setCod_pension(String cod_pension) {
        this.cod_pension = cod_pension;
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

    public String getCod_cate() {
        return cod_cate;
    }

    public void setCod_cate(String cod_cate) {
        this.cod_cate = cod_cate;
    }

    public String getNom_cate() {
        return nom_cate;
    }

    public void setNom_cate(String nom_cate) {
        this.nom_cate = nom_cate;
    }

    public String getCat_pension() {
        return cat_pension;
    }

    public void setCat_pension(String cat_pension) {
        this.cat_pension = cat_pension;
    }

    public String getCod_notas() {
        return cod_notas;
    }

    public void setCod_notas(String cod_notas) {
        this.cod_notas = cod_notas;
    }

    public BigDecimal getPromedio() {
        return promedio;
    }

    public void setPromedio(BigDecimal promedio) {
        this.promedio = promedio;
    }

    public BigDecimal getPension() {
        return pension;
    }

    public void setPension(BigDecimal pension) {
        this.pension = pension;
    }

    public Date getFecha_cancelar() {
        return fecha_cancelar;
    }

    public void setFecha_cancelar(Date fecha_cancelar) {
        this.fecha_cancelar = fecha_cancelar;
    }

    public BigDecimal getPension_actual() {
        return pension_actual;
    }

    public void setPension_actual(BigDecimal pension_actual) {
        this.pension_actual = pension_actual;
    }

    public BigDecimal getPension_nueva() {
        return pension_nueva;
    }

    public void setPension_nueva(BigDecimal pension_nueva) {
        this.pension_nueva = pension_nueva;
    }

   
}
