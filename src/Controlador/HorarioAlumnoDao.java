package Controlador;

import Modelo.HorarioAlumno;
import Modelo.SqlConexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class HorarioAlumnoDao {

    private SqlConexion conSQL = new SqlConexion();
    
    private Connection SQLConexion;

    public HorarioAlumnoDao() {
        SQLConexion = conSQL.getConectarBD();
    }

    public boolean RegistrarHorarioAlumno(HorarioAlumno horA) {
        String sql = "INSERT INTO horario_alumno (ID_HORARIO_ALUMNO, cod_alumno, cod_T, cod_Aula, cod_secc, hora_ini_a, hora_fin_a) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            ps.setString(1, horA.getID_HORARIO_ALUMNO());
            ps.setString(2, horA.getCod_alumno());
            ps.setString(3, horA.getCod_T());
            ps.setString(4, horA.getCod_Aula());
            ps.setString(5, horA.getCod_secc());
            ps.setTime(6, horA.getHora_ini_a());
            ps.setTime(7, horA.getHora_fin_a());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            closeResources(ps);
        }
    }

    public List<HorarioAlumno> ListarHorarioA() {
        List<HorarioAlumno> listaHorarioAlumno = new ArrayList<>();
        String sql = "SELECT ha.ID_HORARIO_ALUMNO, ha.cod_alumno, a.nom_alumno, a.ape_alumno, ha.cod_T, t.nom_turno, ha.cod_Aula, au.nom_Aula, ha.cod_secc, s.nom_secc, ha.hora_ini_a, ha.hora_fin_a FROM horario_alumno ha "
                + "JOIN alumno a ON ha.cod_alumno = a.cod_alumno "
                + "JOIN turno t ON ha.cod_T = t.cod_T "
                + "JOIN aula au ON ha.cod_Aula = au.cod_Aula "
                + "JOIN seccion s ON ha.cod_secc = s.cod_secc";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HorarioAlumno horA = new HorarioAlumno();
                horA.setID_HORARIO_ALUMNO(rs.getString("ID_HORARIO_ALUMNO"));
                horA.setCod_alumno(rs.getString("cod_alumno"));
                horA.setNom_alumno(rs.getString("nom_alumno"));
                horA.setApe_alumno(rs.getString("ape_alumno"));

                horA.setNom_turno(rs.getString("nom_turno"));

                horA.setNom_Aula(rs.getString("nom_Aula"));

                horA.setNom_secc(rs.getString("nom_secc"));
                horA.setHora_ini_a(rs.getTime("hora_ini_a"));
                horA.setHora_fin_a(rs.getTime("hora_fin_a"));
                listaHorarioAlumno.add(horA);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de horarios de alumnos: " + e.toString());
        } finally {
            closeResources(rs, ps);
        }
        return listaHorarioAlumno;
    }

    public boolean ModificarHorarioAlumno(HorarioAlumno horA) {
        String sql = "UPDATE horario_alumno SET cod_alumno=?, cod_T=?, cod_Aula=?, cod_secc=?, hora_ini_a=?, hora_fin_a=? WHERE ID_HORARIO_ALUMNO=?";
        PreparedStatement ps = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            ps.setString(1, horA.getCod_alumno());
            ps.setString(2, horA.getCod_T());
            ps.setString(3, horA.getCod_Aula());
            ps.setString(4, horA.getCod_secc());
            ps.setTime(5, horA.getHora_ini_a());
            ps.setTime(6, horA.getHora_fin_a());
            ps.setString(7, horA.getID_HORARIO_ALUMNO());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error al modificar el horario del alumno: " + e.toString());
            return false;
        } finally {
            closeResources(ps);
        }
    }

    public boolean EliminarHorarioAlumno(String ID_HORARIO_ALUMNO) {
        String sql = "DELETE FROM horario_alumno WHERE ID_HORARIO_ALUMNO = ?";
        PreparedStatement ps = null;
        try {
            ps = SQLConexion.prepareStatement(sql);
            ps.setString(1, ID_HORARIO_ALUMNO);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar el horario del alumno: " + e.toString());
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

   public void consultarAlumno(JComboBox<String> comboBoxAlumno, Map<String, String> mapAlumnoId) {
  
    comboBoxAlumno.removeAllItems();
   
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
            mapAlumnoId.put(nombreAlumno, idAlumno); 
        }
    } catch (SQLException e) {
        System.out.println("Error al consultar los alumnos: " + e.toString());
    } finally {
        closeResources(rs, ps);
    }}
}
