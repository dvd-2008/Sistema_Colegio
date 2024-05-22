
package Controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import Modelo.SqlConexion;
import Modelo.PensionAlumno;
import java.util.HashMap;
public class PensionAlumnoDao {
    
     private SqlConexion conSQL = new SqlConexion();

    private Connection SQLConexion;
    
     public PensionAlumnoDao() {
        SQLConexion = conSQL.getConectarBD();
    }
     Map<String, Double> mapCategoriaPension = new HashMap<>();

     
     public void ConsultarCategoria(JComboBox<String> Categoria, Map<String, String> mapCategoriaId, Map<String, Double> mapCategoriaPension) {
    String sql = "SELECT cod_cate, nom_cate, cat_pension FROM categoria";
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        ps = SQLConexion.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            String idCategoria = rs.getString("cod_cate");
            String nombreCategoria = rs.getString("nom_cate");
            double pensionCategoria = rs.getDouble("cat_pension");
            Categoria.addItem(nombreCategoria);
            mapCategoriaId.put(nombreCategoria, idCategoria); // Asociar nombre con ID en el Map
            mapCategoriaPension.put(nombreCategoria, pensionCategoria); // Asociar nombre con Pensión en el Map
        }
    } catch (Exception e) {
        System.out.println(e.toString());
    } finally {
        // Asegúrate de cerrar los recursos
        closeResources(rs, ps);
    }
}

      public double[] calcularPension(String categoriaSeleccionada, double promedio) {
        double[] resultado = new double[2]; // Array para almacenar la pensión actual y la nueva pensión

        double pensionActual = obtenerPensionCategoria(categoriaSeleccionada);
        double nuevaPension = calcularNuevaPension(pensionActual, promedio);

        resultado[0] = pensionActual;
        resultado[1] = nuevaPension;

        return resultado;
    }

    public double obtenerPensionCategoria(String categoriaSeleccionada) {
        return mapCategoriaPension.getOrDefault(categoriaSeleccionada, 0.0);
    }

    
    public void insertarPension(PensionAlumno pension) {
    String query = "INSERT INTO pension_alumno (cod_pension, cod_alumno, cod_cate, cod_notas, pension, pension_actual, pension_nueva, fecha_cancelar) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement pstmt = SQLConexion.prepareStatement(query)) {
        pstmt.setString(1, pension.getCod_pension());
        pstmt.setString(2, pension.getCod_alumno());
        pstmt.setString(3, pension.getCod_cate());
        pstmt.setString(4, pension.getCod_notas());
        pstmt.setBigDecimal(5, pension.getPension());
        pstmt.setBigDecimal(6, pension.getPension_actual());
        pstmt.setBigDecimal(7, pension.getPension_nueva());
        pstmt.setDate(8, pension.getFecha_cancelar() != null ? new java.sql.Date(pension.getFecha_cancelar().getTime()) : null);
        pstmt.executeUpdate();
    } catch (SQLException ex) {
        System.out.println("Error al insertar pensión: " + ex);
    }
}

    
    private double calcularNuevaPension(double pensionActual, double promedio) {
        double nuevaPension = pensionActual; // Por defecto, la nueva pensión es igual a la actual

        // Aplicar descuentos según el promedio
        if (promedio >= 14.00 && promedio <= 15.99) {
            nuevaPension *= 0.90; // Aplicar un 10% de descuento
        } else if (promedio >= 16.00 && promedio <= 17.99) {
            nuevaPension *= 0.88; // Aplicar un 12% de descuento
        } else if (promedio >= 18.00 && promedio <= 20.00) {
            nuevaPension *= 0.85; // Aplicar un 15% de descuento
        }

        return nuevaPension;
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
