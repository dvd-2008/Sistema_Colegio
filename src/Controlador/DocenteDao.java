package Controlador;

import Modelo.Docente;
import Modelo.SqlConexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DocenteDao {

    private SqlConexion conSQL = new SqlConexion();
    private Connection SQLConexion;

    public DocenteDao() {
        SQLConexion = conSQL.getConectarBD();
    }

    public boolean RegistrarDocente(Docente doc) {
        String sql = "INSERT INTO docente (cod_docente, nom_docente, ape_docente) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            ps.setString(1, doc.getCod_docente());
            ps.setString(2, doc.getNom_docente());
            ps.setString(3, doc.getApe_docente());

            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            closeResources(ps);
        }
    }

    public List<Docente> ListarDocente() {
        List<Docente> Listadoc = new ArrayList<>();
        String sql = "SELECT * FROM docente";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Docente doc = new Docente();
                doc.setCod_docente(rs.getString("cod_docente"));
                doc.setNom_docente(rs.getString("nom_docente"));
                doc.setApe_docente(rs.getString("ape_docente"));
                Listadoc.add(doc);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            closeResources(rs, ps);
        }
        return Listadoc;
    }

    public boolean ModificarDocente(Docente doc) {
        String sql = "UPDATE docente SET nom_docente=?, ape_docente=? WHERE cod_docente=?";
        PreparedStatement ps = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            ps.setString(1, doc.getNom_docente());
            ps.setString(2, doc.getApe_docente());
            ps.setString(3, doc.getCod_docente()); 
            int rowsAffected = ps.executeUpdate(); 
            return rowsAffected > 0; 
        } catch (SQLException e) {
            System.out.println("Error al actualizar docente: " + e.toString());
            return false;
        } finally {
            closeResources(ps);
        }
    }
    
    public boolean EliminarDocente(String cod_docente) {
        String sql = "DELETE FROM docente WHERE cod_docente = ?";
        PreparedStatement ps = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            ps.setString(1, cod_docente);
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
