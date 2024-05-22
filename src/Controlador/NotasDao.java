package Controlador;

import Modelo.Notas;
import Modelo.SqlConexion;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;

public class NotasDao {

    private SqlConexion conSQL = new SqlConexion();

    private Connection SQLConexion;

    public NotasDao() {
        SQLConexion = conSQL.getConectarBD();
    }

    public BigDecimal calcularPromedio(BigDecimal not1, BigDecimal not2, BigDecimal not3, BigDecimal not4, BigDecimal examP, BigDecimal examF) {
        // Calcular el promedio sumando todas las notas y dividiendo por la cantidad de notas
        BigDecimal suma = BigDecimal.ZERO;
        int cantidadNotas = 0;

        if (not1 != null) {
            suma = suma.add(not1);
            cantidadNotas++;
        }
        if (not2 != null) {
            suma = suma.add(not2);
            cantidadNotas++;
        }
        if (not3 != null) {
            suma = suma.add(not3);
            cantidadNotas++;
        }
        if (not4 != null) {
            suma = suma.add(not4);
            cantidadNotas++;
        }
        if (examP != null) {
            suma = suma.add(examP);
            cantidadNotas++;
        }
        if (examF != null) {
            suma = suma.add(examF);
            cantidadNotas++;
        }

        if (cantidadNotas > 0) {
            return suma.divide(BigDecimal.valueOf(cantidadNotas), 2, RoundingMode.HALF_UP);
        } else {
            return BigDecimal.ZERO;
        }
    }

    public void insertarNotas(Notas notas) {
      
        BigDecimal promedio = calcularPromedio(notas.getNot1(), notas.getNot2(), notas.getNot3(), notas.getNot4(), notas.getExame_p(), notas.getExame_f());

        String query = "INSERT INTO notas (cod_notas, cod_alumno, cod_curso, not1, not2, not3, not4, exame_p, exame_f, promedio) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = SQLConexion.prepareStatement(query)) {
            pstmt.setString(1, notas.getCod_notas());
            pstmt.setString(2, notas.getCod_alumno());
            pstmt.setString(3, notas.getCod_curso());
            pstmt.setBigDecimal(4, notas.getNot1());
            pstmt.setBigDecimal(5, notas.getNot2());
            pstmt.setBigDecimal(6, notas.getNot3());
            pstmt.setBigDecimal(7, notas.getNot4());
            pstmt.setBigDecimal(8, notas.getExame_p());
            pstmt.setBigDecimal(9, notas.getExame_f());
            pstmt.setBigDecimal(10, promedio);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al insertar notas: " + ex);
        }
    }

    public List<Notas> ListarNotas() {
        List<Notas> listaNotas = new ArrayList<>();
        String sql = "SELECT n.cod_notas, n.cod_alumno, a.nom_alumno, a.ape_alumno, n.cod_curso, c.nom_curso, n.not1, n.not2, n.not3, n.not4, n.exame_p, n.exame_f FROM notas n "
                + "JOIN alumno a ON n.cod_alumno = a.cod_alumno "
                + "JOIN curso c ON n.cod_curso = c.cod_curso";
        try (PreparedStatement ps = SQLConexion.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Notas nota = new Notas();
                nota.setCod_notas(rs.getString("cod_notas"));
                nota.setCod_alumno(rs.getString("cod_alumno"));
                nota.setNom_alumno(rs.getString("nom_alumno"));
                nota.setApe_alumno(rs.getString("ape_alumno"));
           
                nota.setNom_curso(rs.getString("nom_curso"));
                nota.setNot1(rs.getBigDecimal("not1"));
                nota.setNot2(rs.getBigDecimal("not2"));
                nota.setNot3(rs.getBigDecimal("not3"));
                nota.setNot4(rs.getBigDecimal("not4"));
                nota.setExame_p(rs.getBigDecimal("exame_p"));
                nota.setExame_f(rs.getBigDecimal("exame_f"));

               
                BigDecimal suma = nota.getNot1().add(nota.getNot2()).add(nota.getNot3()).add(nota.getNot4()).add(nota.getExame_p()).add(nota.getExame_f());
                int cantidadNotas = 6; 
                BigDecimal promedio = cantidadNotas > 0 ? suma.divide(BigDecimal.valueOf(cantidadNotas), 2, RoundingMode.HALF_UP) : BigDecimal.ZERO;
                nota.setPromedio(promedio);

                listaNotas.add(nota);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de notas: " + e.toString());
        }
        return listaNotas;
    }

    
    public boolean ModificarNotas(Notas not){
    
     String sql = "UPDATE notas SET cod_alumno=?, cod_curso=?, not1=?, not2=?, not3=?, not4=?, exame_p=?, exame_f=?, promedio=? WHERE cod_notas=?";
    PreparedStatement ps = null;
    try {
        ps = SQLConexion.prepareStatement(sql);
        ps.setString(1, not.getCod_alumno());
        ps.setString(2, not.getCod_curso());
        ps.setBigDecimal(3, not.getNot1());
        ps.setBigDecimal(4, not.getNot2());
        ps.setBigDecimal(5, not.getNot3());
        ps.setBigDecimal(6, not.getNot4());
        ps.setBigDecimal(7, not.getExame_p());
        ps.setBigDecimal(8, not.getExame_f());
        ps.setBigDecimal(9, not.getPromedio());
        ps.setString(10, not.getCod_notas());
        
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        System.out.println("Error al modificar las notas: " + e.toString());
        return false;
    } finally {
        closeResources(ps);
    }
    
    
    
    }
    
     public boolean EliminarHorarioNotas(String cod_notas) {
        String sql = "DELETE FROM notas WHERE cod_notas = ?";
        PreparedStatement ps = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            ps.setString(1, cod_notas);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar las notas de los alumnos: " + e.toString());
            return false;
        } finally {
            closeResources(ps);
        }
    }
    
    
    public void ConsultarCurso(JComboBox<String> Curso, Map<String, String> mapCursoId) {
        String sql = "SELECT cod_curso, nom_curso FROM curso";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String idCurso = rs.getString("cod_curso");
                String nombreCurso = rs.getString("nom_curso");
                Curso.addItem(nombreCurso);
                mapCursoId.put(nombreCurso, idCurso); // Asociar nombre con ID en el Map
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            // Asegúrate de cerrar los recursos
            closeResources(rs, ps);
        }
    }

    public void consultarAlumno(JComboBox<String> comboBoxAlumno, Map<String, String> mapAlumnoId) {
        // Elimina todos los elementos actuales del JComboBox
        comboBoxAlumno.removeAllItems();
        // Consulta la lista de alumnos
        String sql = "SELECT cod_alumno, nom_alumno FROM alumno";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String idAlumno = rs.getString("cod_alumno");
                String nombreAlumno = rs.getString("nom_alumno");
                comboBoxAlumno.addItem(nombreAlumno);
                mapAlumnoId.put(nombreAlumno, idAlumno); // Asocia el nombre con el ID en el Map
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar los alumnos: " + e.toString());
        } finally {
            closeResources(rs, ps);
        }
    }
    
    

    private void closeResources(ResultSet rs, Statement stmt) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar ResultSet: " + ex);
            }
        }
        closeResources(stmt);
    }

    private void closeResources(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar Statement: " + ex);
            }
        }
    }

    public void closeConnection() {
        if (SQLConexion != null) {
            try {
                SQLConexion.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex);
            }
        }
    }
}
