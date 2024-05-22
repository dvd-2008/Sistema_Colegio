package Controlador;

import Modelo.Seccion;
import Modelo.SqlConexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class SeccionDao {
    private final SqlConexion conSQL = new SqlConexion();
    private final Connection SQLConexion;

    public SeccionDao() {
        SQLConexion = conSQL.getConectarBD();
    }
    
    public boolean RegistrarSeccion(Seccion sec) {
        String sql = "INSERT INTO seccion (cod_secc, nom_secc) VALUES (?, ?)";
        PreparedStatement ps = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            ps.setString(1, sec.getCod_secc());
            ps.setString(2, sec.getNom_secc());

            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            closeResources(ps);
        }
    }

    public List<Seccion> ListarSeccion() {
        List<Seccion> listaSecciones = new ArrayList<>();
        String sql = "SELECT * FROM seccion";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {               
                Seccion seccion = new Seccion();
                seccion.setCod_secc(rs.getString("cod_secc"));
                seccion.setNom_secc(rs.getString("nom_secc"));
                listaSecciones.add(seccion);
                
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            closeResources(rs, ps);
        }
        return listaSecciones;
    }

    public boolean ModificarSeccion(Seccion sec) {
        String sql = "UPDATE seccion SET nom_secc=? WHERE cod_secc=?";
        PreparedStatement ps = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            ps.setString(1, sec.getNom_secc());
            ps.setString(2, sec.getCod_secc()); 
            int rowsAffected = ps.executeUpdate(); 
            return rowsAffected > 0; 
        } catch (SQLException e) {
            System.out.println("Error al actualizar sección: " + e.toString());
            return false;
        } finally {
            closeResources(ps);
        }
    }
    
    public boolean EliminarSeccion(String cod_Seccion) {
        String sql = "DELETE FROM seccion WHERE cod_secc = ?";
        PreparedStatement ps = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            ps.setString(1, cod_Seccion);
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