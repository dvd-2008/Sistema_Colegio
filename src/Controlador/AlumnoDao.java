package Controlador;

import Modelo.Alumno;
import Modelo.SqlConexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AlumnoDao {
    private SqlConexion conSQL = new SqlConexion();
    private Connection SQLConexion;

    public AlumnoDao() {
        SQLConexion = conSQL.getConectarBD();
    }

    public boolean RegistrarAlumno(Alumno alu) {
        String sql = "INSERT INTO alumno (cod_alumno, nom_alumno, ape_alumno, sexo) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            ps.setString(1, alu.getCod_alumno());
            ps.setString(2, alu.getNom_alumno());
            ps.setString(3, alu.getApe_alumno());
            ps.setString(4, alu.getSexo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            closeResources(ps);
        }
    }

    public List<Alumno> ListarAlumno() {
        List<Alumno> Listaalu = new ArrayList<>();
        String sql = "SELECT * FROM alumno";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Alumno alu = new Alumno();
                alu.setCod_alumno(rs.getString("cod_alumno"));
                alu.setNom_alumno(rs.getString("nom_alumno"));
                alu.setApe_alumno(rs.getString("ape_alumno"));
                alu.setSexo(rs.getString("sexo"));
                Listaalu.add(alu);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            closeResources(rs, ps);
        }
        return Listaalu;
    }
    
    public boolean ModificarAlumno(Alumno alu){
    
  String sql = "UPDATE alumno SET nom_alumno=?, ape_alumno=?, sexo=? WHERE cod_alumno=?";
    PreparedStatement ps = null;
    try {
        ps = SQLConexion.prepareStatement(sql);
        ps.setString(1, alu.getNom_alumno());
        ps.setString(2, alu.getApe_alumno());
        ps.setString(3, alu.getSexo());
        ps.setString(4, alu.getCod_alumno()); 
        int rowsAffected = ps.executeUpdate(); 
        return rowsAffected > 0;
    } catch (SQLException e) {
        System.out.println("Error al actualizar alumno: " + e.toString());
        return false;
    } finally {
        closeResources(ps);
    }
    
    }
    
    
    

    public boolean EliminarAlumno(String cod_alumno) {
        String sql = "DELETE FROM alumno WHERE cod_alumno = ?";
        PreparedStatement ps = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            ps.setString(1, cod_alumno);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            closeResources(ps);
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
