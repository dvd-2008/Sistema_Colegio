package Controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import Modelo.HorarioDocente;
import Modelo.SqlConexion;

public class HorarioDocenteDao {

    private SqlConexion conSQL = new SqlConexion();

    private Connection SQLConexion;

    public HorarioDocenteDao() {
        SQLConexion = conSQL.getConectarBD();
    }

    public boolean RegistrarHorarioDocente(HorarioDocente horD) {
        String sql = "INSERT INTO horario_docente(ID_HORARIO_DOCENTE, cod_docente, cod_T, cod_Aula, cod_secc, cod_curso, hora_ini_d, hora_fin_d) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            ps.setString(1, horD.getID_HORARIO_DOCENTE());
            ps.setString(2, horD.getCod_docente());
            ps.setString(3, horD.getCod_T());
            ps.setString(4, horD.getCod_Aula());
            ps.setString(5, horD.getCod_secc());
            ps.setString(6, horD.getCod_curso());
            ps.setTime(7, horD.getHora_ini_d());
            ps.setTime(8, horD.getHora_fin_d());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            closeResources(ps);
        }
    }

    public List<HorarioDocente> ListarHorarioD() {
        List<HorarioDocente> listaHorarioDocente = new ArrayList<>();
     String sql = "SELECT ha.ID_HORARIO_DOCENTE, ha.cod_docente, a.nom_docente, a.ape_docente, ha.cod_T, t.nom_turno, ha.cod_Aula, au.nom_Aula, ha.cod_secc, s.nom_secc, ha.cod_curso, cu.nom_curso, ha.hora_ini_d, ha.hora_fin_d FROM horario_docente ha "
                + "JOIN docente a ON ha.cod_docente = a.cod_docente "
                + "JOIN turno t ON ha.cod_T = t.cod_T "
                + "JOIN aula au ON ha.cod_Aula = au.cod_Aula "
                + "JOIN seccion s ON ha.cod_secc = s.cod_secc "
                + "JOIN curso cu ON ha.cod_curso = cu.cod_curso";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HorarioDocente horD = new HorarioDocente();
                horD.setID_HORARIO_DOCENTE(rs.getString("ID_HORARIO_DOCENTE"));
                horD.setCod_docente(rs.getString("cod_docente"));
                horD.setNom_docente(rs.getString("nom_docente"));
                horD.setApe_docente(rs.getString("ape_docente"));
                horD.setNom_turno(rs.getString("nom_turno"));
                horD.setNom_Aula(rs.getString("nom_Aula"));
                horD.setNom_secc(rs.getString("nom_secc"));
                horD.setNom_curso(rs.getString("nom_curso"));
                horD.setHora_ini_d(rs.getTime("hora_ini_d"));
                horD.setHora_fin_d(rs.getTime("hora_fin_d"));
                listaHorarioDocente.add(horD);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de horarios de docente: " + e.toString());
        } finally {
            closeResources(rs, ps);
        }
        return listaHorarioDocente;
    }

    public boolean ModificarHorarioDocente(HorarioDocente horD) {
        String sql = "UPDATE horario_docente SET cod_docente=?, cod_T=?, cod_Aula=?, cod_secc=?, cod_curso=?, hora_ini_d=?, hora_fin_d=? WHERE ID_HORARIO_DOCENTE=?";
        PreparedStatement ps = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            ps.setString(1, horD.getCod_docente());
            ps.setString(2, horD.getCod_T());
            ps.setString(3, horD.getCod_Aula());
            ps.setString(4, horD.getCod_secc());
            ps.setString(5, horD.getCod_curso());
            ps.setTime(6, horD.getHora_ini_d());
            ps.setTime(7, horD.getHora_fin_d());
            ps.setString(8, horD.getID_HORARIO_DOCENTE());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error al modificar el horario del Docente: " + e.toString());
            return false;
        } finally {
            closeResources(ps);
        }
    }

    public boolean EliminarHorarioDocente(String ID_HORARIO_DOCENTE) {
        String sql = "DELETE FROM horario_docente WHERE ID_HORARIO_DOCENTE = ?";
        PreparedStatement ps = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            ps.setString(1, ID_HORARIO_DOCENTE);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar el horario del docente: " + e.toString());
            return false;
        } finally {
            closeResources(ps);
        }
    }

    public void consultarTurno(JComboBox<String> comboBoxTurno, Map<String, String> mapTurnoId) {
        String sql = "SELECT cod_T, nom_turno FROM turno";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String idTurno = rs.getString("cod_T");
                String nombreTurno = rs.getString("nom_turno");
                comboBoxTurno.addItem(nombreTurno);
                mapTurnoId.put(nombreTurno, idTurno); // Asociar nombre con ID en el Map
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar los turnos: " + e.toString());
        } finally {
            closeResources(rs, ps);
        }
    }

    public void ConsultarAula(JComboBox<String> comboBoxAula, Map<String, String> mapAulaId) {
        String sql = "SELECT cod_Aula, nom_Aula FROM aula";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String idAula = rs.getString("cod_Aula");
                String nombreAula = rs.getString("nom_Aula");
                comboBoxAula.addItem(nombreAula);
                mapAulaId.put(nombreAula, idAula); // Asociar nombre con ID en el Map
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            // Asegúrate de cerrar los recursos
            closeResources(rs, ps);
        }
    }

    public void ConsultarSeccion(JComboBox<String> Seccion, Map<String, String> mapSeccionId) {
        String sql = "SELECT cod_secc, nom_secc FROM seccion";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String idSeccion = rs.getString("cod_secc");
                String nombreSeccion = rs.getString("nom_secc");
                Seccion.addItem(nombreSeccion);
                mapSeccionId.put(nombreSeccion, idSeccion); 
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
          
            closeResources(rs, ps);
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
                mapCursoId.put(nombreCurso, idCurso); 
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
          
            closeResources(rs, ps);
        }
    }

    public void consultarDocente(JComboBox<String> comboBoxDocente, Map<String, String> mapDocenteId) {
        comboBoxDocente.removeAllItems();
        String sql = "SELECT cod_docente, nom_docente FROM docente";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String idDocente = rs.getString("cod_docente");
                String nombreDocente = rs.getString("nom_docente");
                comboBoxDocente.addItem(nombreDocente);
                mapDocenteId.put(nombreDocente, idDocente); // Asociar nombre con ID en el Map
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
