package Controlador;

import Modelo.Turno;
import Modelo.SqlConexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TurnoDao {
    private final SqlConexion conSQL = new SqlConexion();
    private final Connection SQLConexion;

    public TurnoDao() {
        SQLConexion = conSQL.getConectarBD();
    }
    
    public boolean Registrarturno(Turno tur) {
        String sql = "INSERT INTO turno (cod_T, nom_turno) VALUES (?, ?)";
        PreparedStatement ps = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            ps.setString(1, tur.getCod_T());
            ps.setString(2, tur.getNom_turno());

            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            closeResources(ps);
        }
    }

    public List<Turno> ListarTurno(Turno tur) {
        List<Turno> Listatur = new ArrayList<>();
        String sql = "SELECT * FROM turno";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {               
                Turno turno = new Turno();
                turno.setCod_T(rs.getString("cod_T"));
                turno.setNom_turno(rs.getString("nom_turno"));
                Listatur.add(turno);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            closeResources(rs, ps);
        }
        return Listatur;
    }

    public boolean ModificarTurno(Turno tur) {
        String sql = "UPDATE turno SET nom_turno=? WHERE cod_T=?";
        PreparedStatement ps = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            ps.setString(1, tur.getNom_turno());
            ps.setString(2, tur.getCod_T()); 
            int rowsAffected = ps.executeUpdate(); 
            return rowsAffected > 0; 
        } catch (SQLException e) {
            System.out.println("Error al actualizar turno: " + e.toString());
            return false;
        } finally {
            closeResources(ps);
        }
    }
    
    public boolean EliminarTurno(String cod_T) {
        String sql = "DELETE FROM turno WHERE cod_T = ?";
        PreparedStatement ps = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            ps.setString(1, cod_T);
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