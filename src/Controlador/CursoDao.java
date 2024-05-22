package Controlador;
import Modelo.Curso;
import Modelo.SqlConexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CursoDao {
    private final SqlConexion conSQL = new SqlConexion();
    private final Connection SQLConexion;

    public CursoDao() {
        SQLConexion = conSQL.getConectarBD();
    }
    
    public boolean RegistrarCurso(Curso cs) {
        String sql = "INSERT INTO curso (cod_curso, nom_curso) VALUES (?, ?)";
        PreparedStatement ps = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            ps.setString(1, cs.getCod_curso());
            ps.setString(2, cs.getNom_curso());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            closeResources(ps);
        }
    }

    public List<Curso> ListarCurso() {
        List<Curso> listaCursos = new ArrayList<>();
        String sql = "SELECT * FROM curso";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {               
                Curso curso = new Curso();
                curso.setCod_curso(rs.getString("cod_curso"));
                curso.setNom_curso(rs.getString("nom_curso"));
                listaCursos.add(curso);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            closeResources(rs, ps);
        }
        return listaCursos;
    }

    public boolean ModificarCurso(Curso curso) {
        String sql = "UPDATE curso SET nom_curso=? WHERE cod_curso=?";
        PreparedStatement ps = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            ps.setString(1, curso.getNom_curso());
            ps.setString(2, curso.getCod_curso()); 
            int rowsAffected = ps.executeUpdate(); 
            return rowsAffected > 0; 
        } catch (SQLException e) {
            System.out.println("Error al actualizar curso: " + e.toString());
            return false;
        } finally {
            closeResources(ps);
        }
    }
    
    public boolean EliminarCurso(String cod_Curso) {
        String sql = "DELETE FROM curso WHERE cod_curso = ?";
        PreparedStatement ps = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            ps.setString(1, cod_Curso);
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