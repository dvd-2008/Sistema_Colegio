package Vista;

import java.sql.Time;

import Controlador.AlumnoDao;
import Controlador.DocenteDao;
import Controlador.HorarioAlumnoDao;
import Controlador.HorarioDocenteDao;
import Controlador.NotasDao;
import Modelo.Alumno;
import Modelo.Docente;
import Modelo.HorarioAlumno;
import Modelo.HorarioDocente;
import Modelo.Notas;
import Reportes.Excel;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class JFmenu extends javax.swing.JFrame {

    Alumno alu = new Alumno();
    AlumnoDao alum = new AlumnoDao();

    Docente doc = new Docente();
    DocenteDao docen = new DocenteDao();

    HorarioAlumno horA = new HorarioAlumno();
    HorarioAlumnoDao horaRI = new HorarioAlumnoDao();

    HorarioDocente horD = new HorarioDocente();
    HorarioDocenteDao horDo = new HorarioDocenteDao();
    
    Notas not = new Notas();
    NotasDao nota = new NotasDao();

    DefaultTableModel modelo = new DefaultTableModel();
    Map<String, String> mapAlumnoId = new HashMap<>();
    Map<String, String> mapTurnoId = new HashMap<>();
    Map<String, String> mapAulaId = new HashMap<>();
    Map<String, String> mapSeccionId = new HashMap<>();
    Map<String, String> mapDocenteId = new HashMap<>();
    Map<String, String> mapCursoId = new HashMap<>();

    public JFmenu() {
        initComponents();
        this.setLocationRelativeTo(null);

        txtNomHA.setVisible(false);
        txtApeHA.setVisible(false);
        AutoCompleteDecorator.decorate(CboHorarioAlumno);
        AutoCompleteDecorator.decorate(CboHorarioAlumnoTurno);
        AutoCompleteDecorator.decorate(CboHorarioAlumnoAula);

        AutoCompleteDecorator.decorate(cboCodigoHD);
        AutoCompleteDecorator.decorate(cboTurnoHD);
        AutoCompleteDecorator.decorate(cboAulaHD);
        AutoCompleteDecorator.decorate(cboCursoHD);
        
         AutoCompleteDecorator.decorate(cboAlumnoN);
           AutoCompleteDecorator.decorate(cboCursoN);


        horaRI.consultarAlumno(CboHorarioAlumno, mapAlumnoId);
        horaRI.consultarTurno(CboHorarioAlumnoTurno, mapTurnoId);
        horaRI.ConsultarAula(CboHorarioAlumnoAula, mapAulaId);
        horaRI.ConsultarSeccion(CboHorarioAlumnoSeccion, mapSeccionId);

        horDo.consultarDocente(cboCodigoHD, mapDocenteId);
        horDo.ConsultarCurso(cboCursoHD, mapCursoId);
        horDo.ConsultarAula(cboAulaHD, mapAulaId);
        horDo.ConsultarSeccion(cboSeccionHD, mapSeccionId);
        horDo.consultarTurno(cboTurnoHD, mapTurnoId);
        
        nota.consultarAlumno(cboAlumnoN, mapAlumnoId);
        nota.ConsultarCurso(cboCursoN, mapCursoId);
    }

    public void LimpiarTabla() {
        modelo.setRowCount(0);
    }

    public void ListarAlumno() {
        List<Alumno> ListarAlu = alum.ListarAlumno();
        modelo = (DefaultTableModel) tablaAlu.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < ListarAlu.size(); i++) {
            ob[0] = ListarAlu.get(i).getCod_alumno();
            ob[1] = ListarAlu.get(i).getNom_alumno();
            ob[2] = ListarAlu.get(i).getApe_alumno();
            ob[3] = ListarAlu.get(i).getSexo();
            modelo.addRow(ob);
        }
        tablaAlu.setModel(modelo);
    }

    private void LimpiarAlumno() {
        txtcodigoAlumno.setText("");
        txtNombreAlumno.setText("");
        txtApellidoAlumno.setText("");
    }

    public void ListarDocente() {
        List<Docente> ListarDoc = docen.ListarDocente();
        modelo = (DefaultTableModel) TablaDocente.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < ListarDoc.size(); i++) {
            ob[0] = ListarDoc.get(i).getCod_docente();
            ob[1] = ListarDoc.get(i).getNom_docente();
            ob[2] = ListarDoc.get(i).getApe_docente();

            modelo.addRow(ob);
        }
        TablaDocente.setModel(modelo);
    }

    public void LimpiarDocente() {
        txtCodigoDoceente.setText("");
        txtNombreDocente.setText("");
        txtApellidoDocente.setText("");
    }

    public void ListarHorarioAlumno() {
        List<HorarioAlumno> listaHorarios = horaRI.ListarHorarioA();
        DefaultTableModel modelo = (DefaultTableModel) TablaHorarioAlumno.getModel();
        Object[] fila = new Object[12]; // Cambia el tamaño del array según la cantidad de columnas de tu tabla

        // Limpiar la tabla antes de agregar nuevos datos
        modelo.setRowCount(0);

        // Iterar sobre la lista de horarios y agregar cada objeto a la tabla
        for (HorarioAlumno horario : listaHorarios) {
            fila[0] = horario.getID_HORARIO_ALUMNO();
            fila[1] = horario.getCod_alumno();
            fila[2] = horario.getNom_alumno(); // Nombre del alumno
            fila[3] = horario.getApe_alumno(); // Apellido del alumno

            fila[4] = horario.getNom_turno(); // Nombre del turno

            fila[5] = horario.getNom_Aula(); // Nombre del aula

            fila[6] = horario.getNom_secc(); // Nombre de la sección
            fila[7] = horario.getHora_ini_a();
            fila[8] = horario.getHora_fin_a();

            modelo.addRow(fila);
        }

        // Establecer el modelo actualizado en la tabla
        TablaHorarioAlumno.setModel(modelo);
    }

    public void LimpiarHorarioAlumno() {
        txtIdHorarioAlumno.setText("");
        txtNomHA.setText(""); // Limpiar campo relacionado con el nombre del alumno
        txtApeHA.setText(""); // Limpiar campo relacionado con el apellido del alumno
        txtHorarioAlumnoIni.setText("");
        txtHorarioAlumnoFin.setText("");
        // Limpiar ComboBox de alumno
        CboHorarioAlumno.setSelectedIndex(-1); // Deseleccionar cualquier ítem seleccionado
        // Limpiar ComboBox de turno
        CboHorarioAlumnoTurno.setSelectedIndex(-1); // Deseleccionar cualquier ítem seleccionado
        // Limpiar ComboBox de aula
        CboHorarioAlumnoAula.setSelectedIndex(-1); // Deseleccionar cualquier ítem seleccionado
        // Limpiar ComboBox de sección
        CboHorarioAlumnoSeccion.setSelectedIndex(-1);
    }

    public void ListarHorarioDocente() {
        List<HorarioDocente> listaHorarios = horDo.ListarHorarioD();
        DefaultTableModel modelo = (DefaultTableModel) TablaHorarioDocente.getModel();
        Object[] fila = new Object[12]; // Cambia el tamaño del array según la cantidad de columnas de tu tabla

        // Limpiar la tabla antes de agregar nuevos datos
        modelo.setRowCount(0);

        // Iterar sobre la lista de horarios y agregar cada objeto a la tabla
        for (HorarioDocente horario : listaHorarios) {
            fila[0] = horario.getID_HORARIO_DOCENTE();
            fila[1] = horario.getCod_docente();
            fila[2] = horario.getNom_docente(); // Nombre del alumno
            fila[3] = horario.getApe_docente(); // Apellido del alumno
            fila[4] = horario.getNom_turno(); // Nombre del turno           
            fila[5] = horario.getNom_Aula(); // Nombre del aula 
            fila[6] = horario.getNom_secc();
            fila[7] = horario.getNom_curso();
            fila[8] = horario.getHora_ini_d();
            fila[9] = horario.getHora_fin_d();

            modelo.addRow(fila);
        }

        // Establecer el modelo actualizado en la tabla
        TablaHorarioDocente.setModel(modelo);
    }

    public void LimpiarHorarioDocente() {
        txtIdHorarioD.setText("");
        txtNomHA.setText(""); // Limpiar campo relacionado con el nombre del alumno
        txtApeHA.setText(""); // Limpiar campo relacionado con el apellido del alumno
        txtHorarioInicioHorarioD.setText("");
        txtHorarioFinHorarioD.setText("");
        // Limpiar ComboBox de alumno
        cboCodigoHD.setSelectedIndex(-1); // Deseleccionar cualquier ítem seleccionado
        // Limpiar ComboBox de turno
        cboTurnoHD.setSelectedIndex(-1); // Deseleccionar cualquier ítem seleccionado
        // Limpiar ComboBox de aula
        cboAulaHD.setSelectedIndex(-1); // Deseleccionar cualquier ítem seleccionado
        // Limpiar ComboBox de sección
        cboSeccionHD.setSelectedIndex(-1);
        cboCursoHD.setSelectedIndex(-1);
    }
    
    public void ListarNotas() {
    // Obtener la lista de notas desde la base de datos
    List<Notas> listaNotas = nota.ListarNotas(); // Suponiendo que `notaDao` es una instancia de tu clase `NotasDao`
    
    // Obtener el modelo de la tabla
    DefaultTableModel modelo = (DefaultTableModel) TablaNotas.getModel(); // Asegúrate de reemplazar `tablaNotas` con el nombre real de tu tabla
    
    // Limpiar la tabla antes de agregar nuevos datos
    modelo.setRowCount(0);
    
    // Iterar sobre la lista de notas y agregar cada objeto a la tabla
    for (Notas nota : listaNotas) {
        // Crear un nuevo array para almacenar los datos de cada fila
        Object[] fila = new Object[12]; // Ajusta el tamaño del array según la cantidad de columnas de tu tabla
        
        // Llenar el array con los datos de la nota actual
        fila[0] = nota.getCod_notas();
        fila[1] = nota.getCod_alumno();
        fila[2] = nota.getNom_alumno();
        fila[3] = nota.getApe_alumno();
        fila[4] = nota.getNom_curso(); // Aquí obtienes el nombre del curso en lugar del código
        fila[5] = nota.getNot1();
        fila[6] = nota.getNot2();
        fila[7] = nota.getNot3();
        fila[8] = nota.getNot4();
        fila[9] = nota.getExame_p();
        fila[10] = nota.getExame_f();
        fila[11] = nota.getPromedio();
        
        // Agregar la fila al modelo de la tabla
        modelo.addRow(fila);
    }
    
    // Establecer el modelo actualizado en la tabla
    TablaNotas.setModel(modelo);
}
    
    public void LimpiarNotas(){
    txtIdNota.setText("");
    cboAlumnoN.setSelectedIndex(-1);
    cboCursoN.setSelectedIndex(-1);
    txtNot1.setText("");
    txtnot2.setText("");
    txtnot3.setText("");
    txtnot4.setText("");
    txtExamP.setText("");
    txtExamF.setText("");
    txtProm.setText("");
   }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnListarAlumno = new javax.swing.JButton();
        btnDocenteL = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btnConfig = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtcodigoAlumno = new javax.swing.JTextField();
        txtNombreAlumno = new javax.swing.JTextField();
        txtApellidoAlumno = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlu = new javax.swing.JTable();
        CboSexoAlumno = new javax.swing.JComboBox<>();
        btnGuardarAlu = new javax.swing.JButton();
        btnEditarAlu = new javax.swing.JButton();
        btnEliminarAlu = new javax.swing.JButton();
        btnNuevoAlu = new javax.swing.JButton();
        btnExcelAlu = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtCodigoDoceente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNombreDocente = new javax.swing.JTextField();
        txtApellidoDocente = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaDocente = new javax.swing.JTable();
        btnGuardarDocente = new javax.swing.JButton();
        BtnEditarDocente = new javax.swing.JButton();
        btnEliminaDocente = new javax.swing.JButton();
        btnNuevoDocente = new javax.swing.JButton();
        btnExcelDocente = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtIdHorarioAlumno = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        CboHorarioAlumno = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        CboHorarioAlumnoTurno = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        CboHorarioAlumnoAula = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        CboHorarioAlumnoSeccion = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtHorarioAlumnoIni = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtHorarioAlumnoFin = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaHorarioAlumno = new javax.swing.JTable();
        btnGuardarHA = new javax.swing.JButton();
        btnEditarHA = new javax.swing.JButton();
        btnEliminarHA = new javax.swing.JButton();
        btnNuevoHA = new javax.swing.JButton();
        BTNEXCELHA = new javax.swing.JButton();
        txtNomHA = new javax.swing.JTextField();
        txtApeHA = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtIdHorarioD = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtHorarioInicioHorarioD = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtHorarioFinHorarioD = new javax.swing.JTextField();
        btnAgregarHorarioDocente = new javax.swing.JButton();
        btnEditarHORARIODocente = new javax.swing.JButton();
        btnEliminarHorarioDocente = new javax.swing.JButton();
        BTNNUEVOHORARIODOCENTE = new javax.swing.JButton();
        btnpdfhorariodocente = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TablaHorarioDocente = new javax.swing.JTable();
        cboCodigoHD = new javax.swing.JComboBox<>();
        cboTurnoHD = new javax.swing.JComboBox<>();
        cboAulaHD = new javax.swing.JComboBox<>();
        cboSeccionHD = new javax.swing.JComboBox<>();
        cboCursoHD = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtIdNota = new javax.swing.JTextField();
        cboAlumnoN = new javax.swing.JComboBox<>();
        cboCursoN = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtNot1 = new javax.swing.JTextField();
        txtnot3 = new javax.swing.JTextField();
        txtnot2 = new javax.swing.JTextField();
        txtnot4 = new javax.swing.JTextField();
        txtExamP = new javax.swing.JTextField();
        txtExamF = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        TablaNotas = new javax.swing.JTable();
        txtProm = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        btnCalcularPro = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminarNota = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        btnListarAlumno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Clientes.png"))); // NOI18N
        btnListarAlumno.setText("Alumnos");
        btnListarAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarAlumnoActionPerformed(evt);
            }
        });

        btnDocenteL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Clientes.png"))); // NOI18N
        btnDocenteL.setText("Profesores");
        btnDocenteL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocenteLActionPerformed(evt);
            }
        });

        jButton3.setText("Pension");

        jButton4.setText("Horarios de alumnos");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Horario de Profesor");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Notas de Alumnos");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        btnConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/config.png"))); // NOI18N
        btnConfig.setText("Config");

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/escuela.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(btnListarAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnDocenteL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnConfig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnListarAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDocenteL, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 620));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 930, 140));

        jLabel1.setText("Codigo ");

        jLabel2.setText("Nombre ");

        jLabel3.setText("Apellido");

        jLabel4.setText("Sexo");

        tablaAlu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "Nombre", "Apellido", "Sexo"
            }
        ));
        tablaAlu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAluMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaAlu);
        if (tablaAlu.getColumnModel().getColumnCount() > 0) {
            tablaAlu.getColumnModel().getColumn(0).setPreferredWidth(30);
            tablaAlu.getColumnModel().getColumn(1).setPreferredWidth(100);
            tablaAlu.getColumnModel().getColumn(2).setPreferredWidth(100);
            tablaAlu.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        CboSexoAlumno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "hombre", "mujer" }));

        btnGuardarAlu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnGuardarAlu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarAluActionPerformed(evt);
            }
        });

        btnEditarAlu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnEditarAlu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarAluActionPerformed(evt);
            }
        });

        btnEliminarAlu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarAlu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAluActionPerformed(evt);
            }
        });

        btnNuevoAlu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        btnNuevoAlu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoAluActionPerformed(evt);
            }
        });

        btnExcelAlu.setText("Excel");
        btnExcelAlu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelAluActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcodigoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtApellidoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(118, 118, 118)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CboSexoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(125, 125, 125))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEditarAlu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardarAlu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(btnNuevoAlu))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(btnEliminarAlu, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(181, 181, 181)
                .addComponent(btnExcelAlu, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtcodigoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CboSexoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtApellidoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardarAlu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevoAlu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnEliminarAlu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(btnEditarAlu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btnExcelAlu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("tab1", jPanel2);

        jLabel5.setText("Codigo");

        jLabel6.setText("Nombre ");

        jLabel7.setText("Apellido");

        TablaDocente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Apellido"
            }
        ));
        TablaDocente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaDocenteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TablaDocente);
        if (TablaDocente.getColumnModel().getColumnCount() > 0) {
            TablaDocente.getColumnModel().getColumn(0).setPreferredWidth(30);
            TablaDocente.getColumnModel().getColumn(1).setPreferredWidth(100);
            TablaDocente.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        btnGuardarDocente.setText("Guardar");
        btnGuardarDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarDocenteActionPerformed(evt);
            }
        });

        BtnEditarDocente.setText("Editar");
        BtnEditarDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarDocenteActionPerformed(evt);
            }
        });

        btnEliminaDocente.setText("Eliminar");
        btnEliminaDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminaDocenteActionPerformed(evt);
            }
        });

        btnNuevoDocente.setText("Nuevo");
        btnNuevoDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoDocenteActionPerformed(evt);
            }
        });

        btnExcelDocente.setText("excel");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigoDoceente, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(94, 94, 94)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(116, 116, 116)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellidoDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnGuardarDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(BtnEditarDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(btnEliminaDocente)
                .addGap(93, 93, 93)
                .addComponent(btnNuevoDocente)
                .addGap(100, 100, 100)
                .addComponent(btnExcelDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtNombreDocente)
                        .addComponent(txtApellidoDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCodigoDoceente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarDocente)
                    .addComponent(BtnEditarDocente)
                    .addComponent(btnEliminaDocente)
                    .addComponent(btnNuevoDocente)
                    .addComponent(btnExcelDocente))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel3);

        jLabel8.setText("ID");

        jLabel9.setText("Alumnos :");

        CboHorarioAlumno.setEditable(true);
        CboHorarioAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboHorarioAlumnoActionPerformed(evt);
            }
        });

        jLabel10.setText("Turno :");

        CboHorarioAlumnoTurno.setEditable(true);

        jLabel11.setText("Aula :");

        CboHorarioAlumnoAula.setEditable(true);

        jLabel12.setText("Seccion");

        CboHorarioAlumnoSeccion.setEditable(true);

        jLabel13.setText("Hora de Inicio");

        jLabel14.setText("Hora de Salida");

        TablaHorarioAlumno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "COD ALUMNO", "NOMBRE", "APELLIDO", "TURNO", "AULA", "SECCION", "HORA DE INICIO", "HORA DE SALIDAl"
            }
        ));
        TablaHorarioAlumno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaHorarioAlumnoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TablaHorarioAlumno);
        if (TablaHorarioAlumno.getColumnModel().getColumnCount() > 0) {
            TablaHorarioAlumno.getColumnModel().getColumn(0).setPreferredWidth(30);
            TablaHorarioAlumno.getColumnModel().getColumn(1).setPreferredWidth(50);
            TablaHorarioAlumno.getColumnModel().getColumn(2).setPreferredWidth(100);
            TablaHorarioAlumno.getColumnModel().getColumn(3).setPreferredWidth(100);
            TablaHorarioAlumno.getColumnModel().getColumn(4).setPreferredWidth(30);
            TablaHorarioAlumno.getColumnModel().getColumn(5).setPreferredWidth(30);
            TablaHorarioAlumno.getColumnModel().getColumn(6).setPreferredWidth(30);
            TablaHorarioAlumno.getColumnModel().getColumn(7).setPreferredWidth(100);
            TablaHorarioAlumno.getColumnModel().getColumn(8).setPreferredWidth(100);
        }

        btnGuardarHA.setText("Agregar");
        btnGuardarHA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarHAActionPerformed(evt);
            }
        });

        btnEditarHA.setText("Editar");
        btnEditarHA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarHAActionPerformed(evt);
            }
        });

        btnEliminarHA.setText("Eliminar");
        btnEliminarHA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarHAActionPerformed(evt);
            }
        });

        btnNuevoHA.setText("Nuevo");
        btnNuevoHA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoHAActionPerformed(evt);
            }
        });

        BTNEXCELHA.setText("excel");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIdHorarioAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CboHorarioAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CboHorarioAlumnoTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHorarioAlumnoIni, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CboHorarioAlumnoAula, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(49, 49, 49)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(200, 200, 200)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtNomHA)
                                            .addComponent(txtApeHA, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)))
                                    .addComponent(CboHorarioAlumnoSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtHorarioAlumnoFin, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnGuardarHA)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditarHA)
                        .addGap(28, 28, 28)
                        .addComponent(btnEliminarHA)
                        .addGap(36, 36, 36)
                        .addComponent(btnNuevoHA)
                        .addGap(33, 33, 33)
                        .addComponent(BTNEXCELHA)))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11)
                                .addComponent(jLabel12))
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(txtNomHA, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdHorarioAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CboHorarioAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CboHorarioAlumnoTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CboHorarioAlumnoAula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CboHorarioAlumnoSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(txtApeHA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHorarioAlumnoIni, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHorarioAlumnoFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarHA)
                    .addComponent(btnEditarHA)
                    .addComponent(btnEliminarHA)
                    .addComponent(btnNuevoHA)
                    .addComponent(BTNEXCELHA))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", jPanel5);

        jLabel15.setText("ID ");

        jLabel16.setText("Docente");

        jLabel17.setText("Turno");

        jLabel18.setText("Aula");

        jLabel19.setText("Seccion");

        jLabel20.setText("Curso");

        jLabel21.setText("Hora de Inicio");

        txtHorarioInicioHorarioD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHorarioInicioHorarioDActionPerformed(evt);
            }
        });

        jLabel22.setText("Hora de Salida");

        btnAgregarHorarioDocente.setText("Agregar");
        btnAgregarHorarioDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarHorarioDocenteActionPerformed(evt);
            }
        });

        btnEditarHORARIODocente.setText("Editar");
        btnEditarHORARIODocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarHORARIODocenteActionPerformed(evt);
            }
        });

        btnEliminarHorarioDocente.setText("Eliminar");
        btnEliminarHorarioDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarHorarioDocenteActionPerformed(evt);
            }
        });

        BTNNUEVOHORARIODOCENTE.setText("Nuevo");
        BTNNUEVOHORARIODOCENTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNNUEVOHORARIODOCENTEActionPerformed(evt);
            }
        });

        btnpdfhorariodocente.setText("PDF");

        TablaHorarioDocente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CODIGO", "NOMBRE", "APELLIDO", "TURNO", "AULA", "SECCION", "CURSO", "HORARIO DE INICIO", "HORARIO DE SALIDA"
            }
        ));
        TablaHorarioDocente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaHorarioDocenteMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TablaHorarioDocente);
        if (TablaHorarioDocente.getColumnModel().getColumnCount() > 0) {
            TablaHorarioDocente.getColumnModel().getColumn(0).setPreferredWidth(20);
            TablaHorarioDocente.getColumnModel().getColumn(1).setPreferredWidth(50);
            TablaHorarioDocente.getColumnModel().getColumn(2).setPreferredWidth(70);
            TablaHorarioDocente.getColumnModel().getColumn(3).setPreferredWidth(70);
            TablaHorarioDocente.getColumnModel().getColumn(4).setPreferredWidth(40);
            TablaHorarioDocente.getColumnModel().getColumn(5).setPreferredWidth(40);
            TablaHorarioDocente.getColumnModel().getColumn(6).setPreferredWidth(60);
            TablaHorarioDocente.getColumnModel().getColumn(7).setPreferredWidth(70);
            TablaHorarioDocente.getColumnModel().getColumn(8).setPreferredWidth(100);
            TablaHorarioDocente.getColumnModel().getColumn(9).setPreferredWidth(100);
        }

        cboCodigoHD.setEditable(true);

        cboTurnoHD.setEditable(true);

        cboAulaHD.setEditable(true);

        cboSeccionHD.setEditable(true);

        cboCursoHD.setEditable(true);

        jPanel9.setBackground(new java.awt.Color(0, 0, 0));

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Horario Docente");
        jLabel23.setToolTipText("");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(txtHorarioInicioHorarioD))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboCursoHD, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cboSeccionHD, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboAulaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHorarioFinHorarioD, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(btnAgregarHorarioDocente))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdHorarioD, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboCodigoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboTurnoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(btnEditarHORARIODocente)
                                .addGap(92, 92, 92)
                                .addComponent(BTNNUEVOHORARIODOCENTE)
                                .addGap(63, 63, 63)
                                .addComponent(btnEliminarHorarioDocente)
                                .addGap(61, 61, 61)
                                .addComponent(btnpdfhorariodocente))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtIdHorarioD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboCodigoHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(cboTurnoHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(cboAulaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(cboSeccionHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(cboCursoHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(txtHorarioInicioHorarioD, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(txtHorarioFinHorarioD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEditarHORARIODocente)
                        .addComponent(BTNNUEVOHORARIODOCENTE)
                        .addComponent(btnEliminarHorarioDocente)
                        .addComponent(btnpdfhorariodocente))
                    .addComponent(btnAgregarHorarioDocente))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab4", jPanel6);

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setText("ID NOTAS");
        jPanel7.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 16, 79, -1));

        jLabel25.setText("Alumno");
        jPanel7.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 16, 56, -1));

        jLabel26.setText("Curso");
        jPanel7.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 16, 43, -1));
        jPanel7.add(txtIdNota, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 45, 63, -1));

        cboAlumnoN.setEditable(true);
        jPanel7.add(cboAlumnoN, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 44, 97, -1));

        cboCursoN.setEditable(true);
        jPanel7.add(cboCursoN, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 44, 100, -1));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/list-check.png"))); // NOI18N
        jLabel27.setText("nota1");
        jPanel7.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 6, 61, -1));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/list-check.png"))); // NOI18N
        jLabel28.setText("nota2");
        jPanel7.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 61, -1));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/list-check.png"))); // NOI18N
        jLabel29.setText("nota3");
        jPanel7.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 100, 61, -1));

        jLabel30.setText("nota4");
        jPanel7.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 43, -1));

        jLabel31.setText("examen Parcial");
        jPanel7.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(538, 52, 95, -1));

        jLabel32.setText("Examen Final");
        jPanel7.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(549, 101, 89, -1));
        jPanel7.add(txtNot1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 78, 36));
        jPanel7.add(txtnot3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 77, 34));
        jPanel7.add(txtnot2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 78, 33));
        jPanel7.add(txtnot4, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 80, 30));
        jPanel7.add(txtExamP, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 50, 80, 30));
        jPanel7.add(txtExamF, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 90, 80, 30));

        TablaNotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod Nota", "Cod Alumno", "Nombre", "Apellido", "Curso", "nota 1", "nota 2", "nota 3", "nota 4", "Exam P", "Exam F", "Promedio"
            }
        ));
        TablaNotas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaNotasMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TablaNotas);
        if (TablaNotas.getColumnModel().getColumnCount() > 0) {
            TablaNotas.getColumnModel().getColumn(0).setPreferredWidth(30);
            TablaNotas.getColumnModel().getColumn(1).setPreferredWidth(50);
            TablaNotas.getColumnModel().getColumn(2).setPreferredWidth(100);
            TablaNotas.getColumnModel().getColumn(3).setPreferredWidth(100);
            TablaNotas.getColumnModel().getColumn(4).setPreferredWidth(100);
            TablaNotas.getColumnModel().getColumn(5).setPreferredWidth(30);
            TablaNotas.getColumnModel().getColumn(6).setPreferredWidth(30);
            TablaNotas.getColumnModel().getColumn(7).setPreferredWidth(30);
            TablaNotas.getColumnModel().getColumn(8).setPreferredWidth(30);
            TablaNotas.getColumnModel().getColumn(9).setPreferredWidth(30);
            TablaNotas.getColumnModel().getColumn(10).setPreferredWidth(30);
            TablaNotas.getColumnModel().getColumn(11).setPreferredWidth(50);
        }

        jPanel7.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 920, 180));
        jPanel7.add(txtProm, new org.netbeans.lib.awtextra.AbsoluteConstraints(797, 44, 70, 40));

        jLabel33.setText("Promedio");
        jPanel7.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(804, 16, 67, -1));

        btnCalcularPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/calculator.png"))); // NOI18N
        btnCalcularPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularProActionPerformed(evt);
            }
        });
        jPanel7.add(btnCalcularPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 60, 40));

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel7.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 90, 40));

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel7.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 80, 40));

        btnEliminarNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarNotaActionPerformed(evt);
            }
        });
        jPanel7.add(btnEliminarNota, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 380, 70, 40));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        jPanel7.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, -1, -1));

        jTabbedPane1.addTab("tab5", jPanel7);

        jLabel35.setText("Codigo de Pension");

        jLabel36.setText("Nombre de Alumno: ");

        jLabel37.setText("Categoria");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel38.setText("Promedio");

        jLabel39.setText("Pension");

        jButton2.setText("Calcular");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTable1);

        jButton7.setText("jButton7");

        jButton8.setText("jButton8");

        jButton9.setText("jButton9");

        jButton10.setText("jButton10");

        jButton11.setText("jButton11");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField1)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox2, 0, 80, Short.MAX_VALUE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)
                                        .addComponent(jButton2)
                                        .addGap(48, 48, 48)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addGap(29, 29, 29)
                                .addComponent(jButton8)
                                .addGap(48, 48, 48)
                                .addComponent(jButton9)
                                .addGap(32, 32, 32)
                                .addComponent(jButton10)
                                .addGap(38, 38, 38)
                                .addComponent(jButton11))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(232, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(95, 95, 95)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(jButton9)
                    .addComponent(jButton10)
                    .addComponent(jButton11))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab6", jPanel8);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab7", jPanel10);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 930, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // ALUMNO
    private void btnGuardarAluActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarAluActionPerformed
        if (!txtcodigoAlumno.getText().isEmpty() && !txtNombreAlumno.getText().isEmpty() && !txtApellidoAlumno.getText().isEmpty() && CboSexoAlumno.getSelectedItem() != null) {
            alu.setCod_alumno(txtcodigoAlumno.getText());
            alu.setNom_alumno(txtNombreAlumno.getText());
            alu.setApe_alumno(txtApellidoAlumno.getText());
            alu.setSexo(CboSexoAlumno.getSelectedItem().toString());
           if (alum.RegistrarAlumno(alu)) {
            // Actualizar ComboBox de alumnos después de agregar uno nuevo
            CboHorarioAlumno.removeAllItems();
            cboAlumnoN.removeAllItems();
            horaRI.consultarAlumno(CboHorarioAlumno, mapAlumnoId);
            nota.consultarAlumno(cboAlumnoN, mapAlumnoId);
            // Limpiar campos y actualizar lista de alumnos en la tabla
            LimpiarAlumno();
            LimpiarTabla();
            ListarAlumno();
            JOptionPane.showMessageDialog(null, "Alumno registrado");
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar el alumno");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Los campos están vacíos");
    }
    }//GEN-LAST:event_btnGuardarAluActionPerformed

    private void btnListarAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarAlumnoActionPerformed
        LimpiarTabla();
        ListarAlumno();
        LimpiarAlumno();
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_btnListarAlumnoActionPerformed

    private void tablaAluMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAluMouseClicked
        int fila = tablaAlu.rowAtPoint(evt.getPoint());
        txtcodigoAlumno.setText(tablaAlu.getValueAt(fila, 0).toString());
        txtNombreAlumno.setText(tablaAlu.getValueAt(fila, 1).toString());
        txtApellidoAlumno.setText(tablaAlu.getValueAt(fila, 2).toString());
        String sexo = tablaAlu.getValueAt(fila, 3).toString();
        if (sexo.equals("hombre")) {
            CboSexoAlumno.setSelectedIndex(0);
        } else {
            CboSexoAlumno.setSelectedIndex(1);
        }
    }//GEN-LAST:event_tablaAluMouseClicked

    private void btnEliminarAluActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarAluActionPerformed
        if (!"".equals(txtcodigoAlumno.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Estas Seguro de Eliminar");
            if (pregunta == 0) {
                String cod_alumno = txtcodigoAlumno.getText();
                alum.EliminarAlumno(cod_alumno);
                LimpiarAlumno();
                LimpiarTabla();
                ListarAlumno();
            }
        }
    }//GEN-LAST:event_btnEliminarAluActionPerformed

    private void btnEditarAluActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarAluActionPerformed
        if (txtcodigoAlumno.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
            return;
        }
        String sexoSeleccionado = CboSexoAlumno.getSelectedItem().toString();
        String nombre = txtNombreAlumno.getText();
        String apellido = txtApellidoAlumno.getText();
        if (nombre.isEmpty() || apellido.isEmpty() || sexoSeleccionado.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos obligatorios");
            return;
        }
        Alumno alu = new Alumno();
        alu.setCod_alumno(txtcodigoAlumno.getText());
        alu.setNom_alumno(nombre);
        alu.setApe_alumno(apellido);
        alu.setSexo(sexoSeleccionado);
        AlumnoDao alumnoDao = new AlumnoDao();
        if (alumnoDao.ModificarAlumno(alu)) {
            JOptionPane.showMessageDialog(null, "Alumno actualizado correctamente");
            LimpiarAlumno();
            LimpiarTabla();
            ListarAlumno();
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar el alumno");
        }
        alumnoDao.closeConnection();
    }//GEN-LAST:event_btnEditarAluActionPerformed

    private void btnNuevoAluActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoAluActionPerformed
        LimpiarAlumno();
    }//GEN-LAST:event_btnNuevoAluActionPerformed

    // DOCENTE 
    private void btnGuardarDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarDocenteActionPerformed
        if (!txtCodigoDoceente.getText().isEmpty() && !txtNombreDocente.getText().isEmpty() && !txtApellidoDocente.getText().isEmpty()) {
            doc.setCod_docente(txtCodigoDoceente.getText());
            doc.setNom_docente(txtNombreDocente.getText());
            doc.setApe_docente(txtApellidoDocente.getText());
           if( docen.RegistrarDocente(doc)){
               cboCodigoHD.removeAllItems();
               horDo.consultarDocente(cboCodigoHD, mapDocenteId);
           }
            LimpiarTabla();
            LimpiarDocente();
            ListarDocente();
            JOptionPane.showMessageDialog(null, "Docente registrado");
        } else {
            JOptionPane.showMessageDialog(null, "Los campos están vacíos");
        }
    }//GEN-LAST:event_btnGuardarDocenteActionPerformed

    private void btnDocenteLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocenteLActionPerformed
        LimpiarTabla();
        ListarDocente();
        jTabbedPane1.setSelectedIndex(1);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnDocenteLActionPerformed

    private void TablaDocenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaDocenteMouseClicked
        int fila = TablaDocente.rowAtPoint(evt.getPoint());
        txtCodigoDoceente.setText(TablaDocente.getValueAt(fila, 0).toString());
        txtNombreDocente.setText(TablaDocente.getValueAt(fila, 1).toString());
        txtApellidoDocente.setText(TablaDocente.getValueAt(fila, 2).toString());

    }//GEN-LAST:event_TablaDocenteMouseClicked

    private void btnEliminaDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminaDocenteActionPerformed
        if (!"".equals(txtCodigoDoceente.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Estas Seguro de Eliminar");
            if (pregunta == 0) {
                String cod_docente = txtCodigoDoceente.getText();
                docen.EliminarDocente(cod_docente);
                LimpiarTabla();
                LimpiarDocente();
                ListarDocente();
            }
        }
    }//GEN-LAST:event_btnEliminaDocenteActionPerformed

    private void BtnEditarDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarDocenteActionPerformed
        if (txtCodigoDoceente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
            return;
        }

        String nombre = txtNombreDocente.getText();
        String apellido = txtApellidoDocente.getText();
        if (nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos obligatorios");
            return;
        }
        Docente doc = new Docente();
        doc.setCod_docente(txtCodigoDoceente.getText());
        doc.setNom_docente(nombre);
        doc.setApe_docente(apellido);

        DocenteDao docenteDao = new DocenteDao();
        if (docenteDao.ModificarDocente(doc)) {
            JOptionPane.showMessageDialog(null, "Docente actualizado correctamente");
            LimpiarDocente();
            LimpiarTabla();
            ListarDocente();
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar el alumno");
        }
        docenteDao.closeConnection();
    }//GEN-LAST:event_BtnEditarDocenteActionPerformed

    private void btnNuevoDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoDocenteActionPerformed
        LimpiarDocente();
    }//GEN-LAST:event_btnNuevoDocenteActionPerformed

    // HORARIO ALUMNO
    private void btnGuardarHAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarHAActionPerformed
        if (!txtIdHorarioAlumno.getText().isEmpty()
                && CboHorarioAlumno.getSelectedItem() != null && CboHorarioAlumnoTurno.getSelectedItem() != null
                && CboHorarioAlumnoAula.getSelectedItem() != null && CboHorarioAlumnoSeccion.getSelectedItem() != null
                && !txtHorarioAlumnoIni.getText().isEmpty() && !txtHorarioAlumnoFin.getText().isEmpty()) {

            // Verifica que todos los campos obligatorios estén llenos
            try {
                // Crea un objeto HorarioAlumno y establece sus atributos con la información de los campos
                horA.setID_HORARIO_ALUMNO(txtIdHorarioAlumno.getText());

                // Obtiene el código del alumno seleccionado del JComboBox utilizando el Map mapAlumnoId
                String nombreAlumnoSeleccionado = CboHorarioAlumno.getSelectedItem().toString();
                String codAlumnoSeleccionado = mapAlumnoId.get(nombreAlumnoSeleccionado);
                horA.setCod_alumno(codAlumnoSeleccionado);

                // Obtiene el código del turno seleccionado del JComboBox utilizando el Map mapTurnoId
                String nombreTurnoSeleccionado = CboHorarioAlumnoTurno.getSelectedItem().toString();
                String codTurnoSeleccionado = mapTurnoId.get(nombreTurnoSeleccionado);
                horA.setCod_T(codTurnoSeleccionado);

                // Obtiene el código del aula seleccionado del JComboBox utilizando el Map mapAulaId
                String nombreAulaSeleccionado = CboHorarioAlumnoAula.getSelectedItem().toString();
                String codAulaSeleccionado = mapAulaId.get(nombreAulaSeleccionado);
                horA.setCod_Aula(codAulaSeleccionado);

                String nombreSeccionSeleccionada = CboHorarioAlumnoSeccion.getSelectedItem().toString();
                String codSeccionSeleccionada = mapSeccionId.get(nombreSeccionSeleccionada);
                horA.setCod_secc(codSeccionSeleccionada);

                // Convierte el texto de las horas de inicio y fin a objetos Time
                String horaInicioString = txtHorarioAlumnoIni.getText();
                String horaFinString = txtHorarioAlumnoFin.getText();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                java.util.Date parsedDate = sdf.parse(horaInicioString);
                java.sql.Time horaInicio = new java.sql.Time(parsedDate.getTime());
                parsedDate = sdf.parse(horaFinString);
                java.sql.Time horaFin = new java.sql.Time(parsedDate.getTime());

                // Asigna las horas convertidas a los atributos correspondientes del objeto HorarioAlumno
                horA.setHora_ini_a(horaInicio);
                horA.setHora_fin_a(horaFin);

                // Llama al método para registrar el horario del alumno
                horaRI.RegistrarHorarioAlumno(horA);

                // Limpia los campos y actualiza la tabla
                LimpiarTabla();
                LimpiarHorarioAlumno();
                ListarHorarioAlumno();

                // Muestra un mensaje de confirmación
                JOptionPane.showMessageDialog(null, "Horario del alumno registrado");
            } catch (ParseException ex) {
                // Manejo de errores si la conversión de las horas falla
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al convertir las horas");
            }

        } else {
            // Si algún campo obligatorio está vacío, muestra un mensaje de error
            JOptionPane.showMessageDialog(null, "Todos los campos obligatorios deben ser llenados");
        }

    }//GEN-LAST:event_btnGuardarHAActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        LimpiarTabla();
         jTabbedPane1.setSelectedIndex(2);
        LimpiarHorarioAlumno();
        ListarHorarioAlumno();
       

    }//GEN-LAST:event_jButton4ActionPerformed

    private void TablaHorarioAlumnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaHorarioAlumnoMouseClicked
        int fila = TablaHorarioAlumno.rowAtPoint(evt.getPoint());
        txtIdHorarioAlumno.setText(TablaHorarioAlumno.getValueAt(fila, 0).toString());

        // Obtener el nombre del alumno y seleccionarlo en el ComboBox
        String codAlumno = TablaHorarioAlumno.getValueAt(fila, 1).toString();
        CboHorarioAlumno.setSelectedItem(codAlumno);

        // Obtener el código del turno y seleccionarlo en el ComboBox
        String codigoTurno = TablaHorarioAlumno.getValueAt(fila, 4).toString();
        CboHorarioAlumnoTurno.setSelectedItem(codigoTurno);

        // Obtener el código del aula y seleccionarlo en el ComboBox
        String codigoAula = TablaHorarioAlumno.getValueAt(fila, 5).toString();
        CboHorarioAlumnoAula.setSelectedItem(codigoAula);

        // Obtener el código de la sección y seleccionarlo en el ComboBox
        String codigoSeccion = TablaHorarioAlumno.getValueAt(fila, 6).toString();
        CboHorarioAlumnoSeccion.setSelectedItem(codigoSeccion);

        // Obtener la hora de inicio y establecerla en el campo correspondiente
        String horaInicio = TablaHorarioAlumno.getValueAt(fila, 7).toString();
        txtHorarioAlumnoIni.setText(horaInicio);

        // Obtener la hora de salida y establecerla en el campo correspondiente
        String horaFin = TablaHorarioAlumno.getValueAt(fila, 8).toString();
        txtHorarioAlumnoFin.setText(horaFin);

        // TODO add your handling code here:
    }//GEN-LAST:event_TablaHorarioAlumnoMouseClicked

    private void btnEditarHAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarHAActionPerformed
        // Verificar si se ha seleccionado una fila
        if (txtIdHorarioAlumno.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
            return;
        }

        // Obtener los valores de los campos
        String nombreAlumno = CboHorarioAlumno.getSelectedItem().toString();
        String codigoTurno = CboHorarioAlumnoTurno.getSelectedItem().toString();
        String codigoAula = CboHorarioAlumnoAula.getSelectedItem().toString();
        String codigoSeccion = CboHorarioAlumnoSeccion.getSelectedItem().toString();
        String horaInicio = txtHorarioAlumnoIni.getText();
        String horaFin = txtHorarioAlumnoFin.getText();

        // Validar que todos los campos obligatorios estén completos
        if (nombreAlumno.isEmpty() || codigoTurno.isEmpty() || codigoAula.isEmpty() || codigoSeccion.isEmpty() || horaInicio.isEmpty() || horaFin.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos obligatorios");
            return;
        }

        // Crear un objeto HorarioAlumno con los valores de los campos
        HorarioAlumno horA = new HorarioAlumno();
        horA.setID_HORARIO_ALUMNO(txtIdHorarioAlumno.getText());
        horA.setCod_alumno(mapAlumnoId.get(nombreAlumno));
        horA.setCod_T(mapTurnoId.get(codigoTurno));
        horA.setCod_Aula(mapAulaId.get(codigoAula));
        horA.setCod_secc(mapSeccionId.get(codigoSeccion));
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            java.util.Date parsedDate = sdf.parse(horaInicio);
            java.sql.Time horaIni = new java.sql.Time(parsedDate.getTime());
            parsedDate = sdf.parse(horaFin);
            java.sql.Time horaF = new java.sql.Time(parsedDate.getTime());
            horA.setHora_ini_a(horaIni);
            horA.setHora_fin_a(horaF);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Error al convertir las horas");
            return;
        }

        // Modificar el horario del alumno en la base de datos
        HorarioAlumnoDao horaDao = new HorarioAlumnoDao();
        if (horaDao.ModificarHorarioAlumno(horA)) {
            JOptionPane.showMessageDialog(null, "Horario del alumno actualizado correctamente");
            LimpiarTabla();
            LimpiarHorarioAlumno();
            ListarHorarioAlumno();

        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar el horario del alumno");
        }
        horaDao.closeConnection();
    }//GEN-LAST:event_btnEditarHAActionPerformed

    private void btnEliminarHAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarHAActionPerformed
        if (txtIdHorarioAlumno.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
            return;
        }

        // Confirmar la eliminación con un mensaje de confirmación
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar este horario de alumno?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        // Obtener el ID del horario de alumno a eliminar
        String idHorarioAlumno = txtIdHorarioAlumno.getText();

        // Eliminar el horario de alumno de la base de datos
        HorarioAlumnoDao horaDao = new HorarioAlumnoDao();
        if (horaDao.EliminarHorarioAlumno(idHorarioAlumno)) {
            JOptionPane.showMessageDialog(null, "Horario de alumno eliminado correctamente");
            LimpiarTabla();
            LimpiarHorarioAlumno();
            ListarHorarioAlumno();

        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar el horario del alumno");
        }
        horaDao.closeConnection();
    }//GEN-LAST:event_btnEliminarHAActionPerformed

    private void btnNuevoHAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoHAActionPerformed
        LimpiarHorarioAlumno();        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoHAActionPerformed

    //horario docente
    private void txtHorarioInicioHorarioDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHorarioInicioHorarioDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHorarioInicioHorarioDActionPerformed

    private void btnAgregarHorarioDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarHorarioDocenteActionPerformed

        if (!txtIdHorarioD.getText().isEmpty()
                && cboCodigoHD.getSelectedItem() != null && cboTurnoHD.getSelectedItem() != null
                && cboAulaHD.getSelectedItem() != null && cboSeccionHD.getSelectedItem() != null && cboCursoHD.getSelectedItem() != null
                && !txtHorarioInicioHorarioD.getText().isEmpty() && !txtHorarioFinHorarioD.getText().isEmpty()) {

            // Verifica que todos los campos obligatorios estén llenos
            try {
                // Crea un objeto HorarioAlumno y establece sus atributos con la información de los campos
                horD.setID_HORARIO_DOCENTE(txtIdHorarioD.getText());

                // Obtiene el código del alumno seleccionado del JComboBox utilizando el Map mapAlumnoId
                String nombreDocenteSeleccionado = cboCodigoHD.getSelectedItem().toString();
                String codDocenteSeleccionado = mapDocenteId.get(nombreDocenteSeleccionado);
                horD.setCod_docente(codDocenteSeleccionado);

                // Obtiene el código del turno seleccionado del JComboBox utilizando el Map mapTurnoId
                String nombreTurnoSeleccionado = cboTurnoHD.getSelectedItem().toString();
                String codTurnoSeleccionado = mapTurnoId.get(nombreTurnoSeleccionado);
                horD.setCod_T(codTurnoSeleccionado);

                // Obtiene el código del aula seleccionado del JComboBox utilizando el Map mapAulaId
                String nombreAulaSeleccionado = cboAulaHD.getSelectedItem().toString();
                String codAulaSeleccionado = mapAulaId.get(nombreAulaSeleccionado);
                horD.setCod_Aula(codAulaSeleccionado);

                String nombreSeccionSeleccionada = cboSeccionHD.getSelectedItem().toString();
                String codSeccionSeleccionada = mapSeccionId.get(nombreSeccionSeleccionada);
                horD.setCod_secc(codSeccionSeleccionada);

                String nombCursoSeleccionada = cboCursoHD.getSelectedItem().toString();
                String codCursoSeleccionada = mapCursoId.get(nombCursoSeleccionada);
                horD.setCod_curso(codCursoSeleccionada);

                // Convierte el texto de las horas de inicio y fin a objetos Time
                String horaInicioStringD = txtHorarioInicioHorarioD.getText();
                String horaFinStringD = txtHorarioFinHorarioD.getText();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                java.util.Date parsedDate = sdf.parse(horaInicioStringD);
                java.sql.Time horaInicio = new java.sql.Time(parsedDate.getTime());
                parsedDate = sdf.parse(horaFinStringD);
                java.sql.Time horaFin = new java.sql.Time(parsedDate.getTime());

                // Asigna las horas convertidas a los atributos correspondientes del objeto HorarioAlumno
                horD.setHora_ini_d(horaInicio);
                horD.setHora_fin_d(horaFin);

                // Llama al método para registrar el horario del alumno
                horDo.RegistrarHorarioDocente(horD);

                // Limpia los campos y actualiza la tabla
                LimpiarTabla();
                LimpiarHorarioDocente();
                ListarHorarioDocente();

                // Muestra un mensaje de confirmación
                JOptionPane.showMessageDialog(null, "Horario del Docente registrado");
            } catch (ParseException ex) {
                // Manejo de errores si la conversión de las horas falla
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al convertir las horas");
            }

        } else {
            // Si algún campo obligatorio está vacío, muestra un mensaje de error
            JOptionPane.showMessageDialog(null, "Todos los campos obligatorios deben ser llenados");
        }
    }//GEN-LAST:event_btnAgregarHorarioDocenteActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        LimpiarTabla();
        LimpiarHorarioDocente();
        ListarHorarioDocente();
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnEditarHORARIODocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarHORARIODocenteActionPerformed
        // Verificar si se ha seleccionado una fila
        if (txtIdHorarioD.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
            return;
        }

        // Obtener los valores de los campos
        String nombreDocente = cboCodigoHD.getSelectedItem().toString();
        String codigoTurno = cboTurnoHD.getSelectedItem().toString();
        String codigoAula = cboAulaHD.getSelectedItem().toString();
        String codigoSeccion = cboSeccionHD.getSelectedItem().toString();
        String codigoCurso = cboCursoHD.getSelectedItem().toString();
        String horaInicio = txtHorarioInicioHorarioD.getText();
        String horaFin = txtHorarioFinHorarioD.getText();

        // Validar que todos los campos obligatorios estén completos
        if (nombreDocente.isEmpty() || codigoTurno.isEmpty() || codigoAula.isEmpty() || codigoSeccion.isEmpty() || codigoCurso.isEmpty() || horaInicio.isEmpty() || horaFin.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos obligatorios");
            return;
        }

        // Crear un objeto HorarioAlumno con los valores de los campos
        horD.setID_HORARIO_DOCENTE(txtIdHorarioD.getText());
        horD.setCod_docente(mapDocenteId.get(nombreDocente));
        horD.setCod_T(mapTurnoId.get(codigoTurno));
        horD.setCod_Aula(mapAulaId.get(codigoAula));
        horD.setCod_secc(mapSeccionId.get(codigoSeccion));
        horD.setCod_curso(mapCursoId.get(codigoCurso));
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            java.util.Date parsedDate = sdf.parse(horaInicio);
            java.sql.Time horaIni = new java.sql.Time(parsedDate.getTime());
            parsedDate = sdf.parse(horaFin);
            java.sql.Time horaF = new java.sql.Time(parsedDate.getTime());
            horD.setHora_ini_d(horaIni);
            horD.setHora_fin_d(horaF);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Error al convertir las horas");
            return;
        }

        // Modificar el horario del alumno en la base de datos
        HorarioDocenteDao horaDao = new HorarioDocenteDao();
        if (horaDao.ModificarHorarioDocente(horD)) {
            JOptionPane.showMessageDialog(null, "Horario del Docente actualizado correctamente");
            LimpiarTabla();
            LimpiarHorarioDocente();
            ListarHorarioDocente();

        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar el horario del docente");
        }
        horaDao.closeConnection();
    }//GEN-LAST:event_btnEditarHORARIODocenteActionPerformed

    private void TablaHorarioDocenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaHorarioDocenteMouseClicked
        int fila = TablaHorarioDocente.rowAtPoint(evt.getPoint());
        txtIdHorarioD.setText(TablaHorarioDocente.getValueAt(fila, 0).toString());

        // Obtener el nombre del alumno y seleccionarlo en el ComboBox
        String codDocente = TablaHorarioDocente.getValueAt(fila, 1).toString();
        cboCodigoHD.setSelectedItem(codDocente);

        // Obtener el código del turno y seleccionarlo en el ComboBox
        String codigoTurno = TablaHorarioDocente.getValueAt(fila, 4).toString();
        cboTurnoHD.setSelectedItem(codigoTurno);

        // Obtener el código del aula y seleccionarlo en el ComboBox
        String codigoAula = TablaHorarioDocente.getValueAt(fila, 5).toString();
        cboAulaHD.setSelectedItem(codigoAula);

        // Obtener el código de la sección y seleccionarlo en el ComboBox
        String codigoSeccion = TablaHorarioDocente.getValueAt(fila, 6).toString();
        cboSeccionHD.setSelectedItem(codigoSeccion);

        // Obtener el código de la sección y seleccionarlo en el ComboBox
        String codigoCurso = TablaHorarioDocente.getValueAt(fila, 7).toString();
        cboCursoHD.setSelectedItem(codigoCurso);

        // Obtener la hora de inicio y establecerla en el campo correspondiente
        String horaInicio = TablaHorarioDocente.getValueAt(fila, 8).toString();
        txtHorarioInicioHorarioD.setText(horaInicio);

        // Obtener la hora de salida y establecerla en el campo correspondiente
        String horaFin = TablaHorarioDocente.getValueAt(fila, 9).toString();
        txtHorarioFinHorarioD.setText(horaFin);
    }//GEN-LAST:event_TablaHorarioDocenteMouseClicked

    private void btnEliminarHorarioDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarHorarioDocenteActionPerformed
        if (txtIdHorarioD.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
            return;
        }

        // Confirmar la eliminación con un mensaje de confirmación
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar este horario de docente?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        // Obtener el ID del horario de alumno a eliminar
        String idHorarioDocente = txtIdHorarioD.getText();

        // Eliminar el horario de alumno de la base de datos
        if (horDo.EliminarHorarioDocente(idHorarioDocente)) {
            JOptionPane.showMessageDialog(null, "Horario de docente eliminado correctamente");
            LimpiarTabla();
            LimpiarHorarioDocente();
            ListarHorarioDocente();

        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar el horario del docente");
        }
        horDo.closeConnection();
    }//GEN-LAST:event_btnEliminarHorarioDocenteActionPerformed

    private void BTNNUEVOHORARIODOCENTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNNUEVOHORARIODOCENTEActionPerformed
    LimpiarHorarioDocente();
    }//GEN-LAST:event_BTNNUEVOHORARIODOCENTEActionPerformed

    // notas alumnos 
    private void btnCalcularProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularProActionPerformed
// Obtener las notas ingresadas
    BigDecimal not1 = new BigDecimal(txtNot1.getText().trim());
    BigDecimal not2 = new BigDecimal(txtnot2.getText().trim());
    BigDecimal not3 = new BigDecimal(txtnot3.getText().trim());
    BigDecimal not4 = new BigDecimal(txtnot4.getText().trim());
    BigDecimal examP = new BigDecimal(txtExamP.getText().trim());
    BigDecimal examF = new BigDecimal(txtExamF.getText().trim());
    
    // Calcular el promedio utilizando el método calcularPromedio de NotasDao
    BigDecimal promedio = nota.calcularPromedio(not1, not2, not3, not4, examP, examF);
    
    // Mostrar el resultado en el campo txtProm
    txtProm.setText(promedio.toString());
    }//GEN-LAST:event_btnCalcularProActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        LimpiarTabla();
        ListarNotas();
        LimpiarNotas();
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
// Verificar que los campos de notas no estén vacíos
    if (!txtNot1.getText().isEmpty() && !txtnot2.getText().isEmpty() && !txtnot3.getText().isEmpty() &&
        !txtnot4.getText().isEmpty() && !txtExamP.getText().isEmpty() && !txtExamF.getText().isEmpty()) {
        
        // Crear un objeto de la clase Notas y establecer los valores de las notas
        Notas notas = new Notas();
        notas.setCod_notas(txtIdNota.getText().trim()); // Establecer el ID de la nota desde txtIdNota
        notas.setCod_alumno(mapAlumnoId.get(cboAlumnoN.getSelectedItem().toString())); // Obtener el ID del alumno seleccionado del mapa
        notas.setCod_curso(mapCursoId.get(cboCursoN.getSelectedItem().toString())); // Obtener el ID del curso seleccionado del mapa
        notas.setNot1(new BigDecimal(txtNot1.getText().trim()));
        notas.setNot2(new BigDecimal(txtnot2.getText().trim()));
        notas.setNot3(new BigDecimal(txtnot3.getText().trim()));
        notas.setNot4(new BigDecimal(txtnot4.getText().trim()));
        notas.setExame_p(new BigDecimal(txtExamP.getText().trim()));
        notas.setExame_f(new BigDecimal(txtExamF.getText().trim()));
        
        // Calcular el promedio
BigDecimal promedio = nota.calcularPromedio(notas.getNot1(), notas.getNot2(), notas.getNot3(), notas.getNot4(), notas.getExame_p(), notas.getExame_f());
        notas.setPromedio(promedio);
        
        // Insertar las notas en la base de datos
        nota.insertarNotas(notas);
        
        // Limpiar los campos de notas después de agregarlas
        txtIdNota.setText("");
        txtNot1.setText("");
        txtnot2.setText("");
        txtnot3.setText("");
        txtnot4.setText("");
        txtExamP.setText("");
        txtExamF.setText("");
        
        // Mostrar un mensaje de éxito
        JOptionPane.showMessageDialog(null, "Notas agregadas exitosamente");
        LimpiarTabla();
        LimpiarNotas();
        ListarNotas();
        // Actualizar la lista de notas si es necesario
        // Llamar al método ListarNotas() aquí si deseas refrescar la lista después de agregar las notas
    } else {
        // Mostrar un mensaje de error si algún campo de notas está vacío
        JOptionPane.showMessageDialog(null, "Por favor ingresa todas las notas");
    }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
 // Verificar si se ha seleccionado una fila
    if (txtIdNota.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Seleccione una fila");
        return;
    }

    // Obtener los valores de los campos
    String idNota = txtIdNota.getText();
    String nombreAlumno = cboAlumnoN.getSelectedItem().toString();
    String nombreCurso = cboCursoN.getSelectedItem().toString();
    BigDecimal nota1 = new BigDecimal(txtNot1.getText().trim());
    BigDecimal nota2 = new BigDecimal(txtnot2.getText().trim());
    BigDecimal nota3 = new BigDecimal(txtnot3.getText().trim());
    BigDecimal nota4 = new BigDecimal(txtnot4.getText().trim());
    BigDecimal examenParcial = new BigDecimal(txtExamP.getText().trim());
    BigDecimal examenFinal = new BigDecimal(txtExamF.getText().trim());

    // Validar que todos los campos obligatorios estén completos
    if (nombreAlumno.isEmpty() || nombreCurso.isEmpty() || nota1 == null || nota2 == null || nota3 == null || nota4 == null || examenParcial == null || examenFinal == null) {
        JOptionPane.showMessageDialog(null, "Complete todos los campos obligatorios");
        return;
    }

    // Crear un objeto Notas con los valores de los campos
    Notas notas = new Notas();
    notas.setCod_notas(idNota);
    notas.setCod_alumno(mapAlumnoId.get(nombreAlumno));
    notas.setCod_curso(mapCursoId.get(nombreCurso));
    notas.setNot1(nota1);
    notas.setNot2(nota2);
    notas.setNot3(nota3);
    notas.setNot4(nota4);
    notas.setExame_p(examenParcial);
    notas.setExame_f(examenFinal);

    // Calcular el promedio
    BigDecimal promedio = nota.calcularPromedio(notas.getNot1(), notas.getNot2(), notas.getNot3(), notas.getNot4(), notas.getExame_p(), notas.getExame_f());
    notas.setPromedio(promedio);

    // Modificar las notas en la base de datos
    if (nota.ModificarNotas(notas)) {
        JOptionPane.showMessageDialog(null, "Notas actualizadas correctamente");
        LimpiarTabla();
        LimpiarNotas();
        ListarNotas(); // Actualizar la lista de notas en la interfaz
    } else {
        JOptionPane.showMessageDialog(null, "Error al actualizar las notas");
    }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarActionPerformed

    private void TablaNotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaNotasMouseClicked

 // Obtener la fila seleccionada
    int fila = TablaNotas.rowAtPoint(evt.getPoint());
    
    // Establecer el ID de la nota en el campo correspondiente
    txtIdNota.setText(TablaNotas.getValueAt(fila, 0).toString());

    // Obtener el código del alumno y seleccionarlo en el ComboBox
    String codAlumno = TablaNotas.getValueAt(fila, 1).toString();
    cboAlumnoN.setSelectedItem(codAlumno);

    // Obtener el nombre del curso y seleccionarlo en el ComboBox
    String nomCurso = TablaNotas.getValueAt(fila, 4).toString();
    cboCursoN.setSelectedItem(nomCurso);

    // Establecer los valores de las notas en los campos correspondientes
    txtNot1.setText(TablaNotas.getValueAt(fila, 5).toString());
    txtnot2.setText(TablaNotas.getValueAt(fila, 6).toString());
    txtnot3.setText(TablaNotas.getValueAt(fila, 7).toString());
    txtnot4.setText(TablaNotas.getValueAt(fila, 8).toString());
    txtExamP.setText(TablaNotas.getValueAt(fila, 9).toString());
    txtExamF.setText(TablaNotas.getValueAt(fila, 10).toString());

    // Obtener el promedio y establecerlo en el campo correspondiente
    String promedio = TablaNotas.getValueAt(fila, 11).toString();
    txtProm.setText(promedio);
        // TODO add your handling code here:
    }//GEN-LAST:event_TablaNotasMouseClicked

    private void btnExcelAluActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelAluActionPerformed
       Excel.reporte();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExcelAluActionPerformed

    private void btnEliminarNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarNotaActionPerformed
     
          if (txtIdNota.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
            return;
        }

        // Confirmar la eliminación con un mensaje de confirmación
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar este horario de docente?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        // Obtener el ID del horario de alumno a eliminar
        String idnotas = txtIdNota.getText();

        // Eliminar el horario de alumno de la base de datos
        if (nota.EliminarHorarioNotas(idnotas)) {
            JOptionPane.showMessageDialog(null, "Nota eliminado correctamente");
            LimpiarTabla();
            LimpiarNotas();
           ListarNotas();

        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar el horario del docente");
        }
        horDo.closeConnection();
        
    }//GEN-LAST:event_btnEliminarNotaActionPerformed

    private void CboHorarioAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboHorarioAlumnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CboHorarioAlumnoActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFmenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFmenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFmenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFmenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFmenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNEXCELHA;
    private javax.swing.JButton BTNNUEVOHORARIODOCENTE;
    private javax.swing.JButton BtnEditarDocente;
    private javax.swing.JComboBox<String> CboHorarioAlumno;
    private javax.swing.JComboBox<String> CboHorarioAlumnoAula;
    private javax.swing.JComboBox<String> CboHorarioAlumnoSeccion;
    private javax.swing.JComboBox<String> CboHorarioAlumnoTurno;
    private javax.swing.JComboBox<String> CboSexoAlumno;
    private javax.swing.JTable TablaDocente;
    private javax.swing.JTable TablaHorarioAlumno;
    private javax.swing.JTable TablaHorarioDocente;
    private javax.swing.JTable TablaNotas;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregarHorarioDocente;
    private javax.swing.JButton btnCalcularPro;
    private javax.swing.JButton btnConfig;
    private javax.swing.JButton btnDocenteL;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditarAlu;
    private javax.swing.JButton btnEditarHA;
    private javax.swing.JButton btnEditarHORARIODocente;
    private javax.swing.JButton btnEliminaDocente;
    private javax.swing.JButton btnEliminarAlu;
    private javax.swing.JButton btnEliminarHA;
    private javax.swing.JButton btnEliminarHorarioDocente;
    private javax.swing.JButton btnEliminarNota;
    private javax.swing.JButton btnExcelAlu;
    private javax.swing.JButton btnExcelDocente;
    private javax.swing.JButton btnGuardarAlu;
    private javax.swing.JButton btnGuardarDocente;
    private javax.swing.JButton btnGuardarHA;
    private javax.swing.JButton btnListarAlumno;
    private javax.swing.JButton btnNuevoAlu;
    private javax.swing.JButton btnNuevoDocente;
    private javax.swing.JButton btnNuevoHA;
    private javax.swing.JButton btnpdfhorariodocente;
    private javax.swing.JComboBox<String> cboAlumnoN;
    private javax.swing.JComboBox<String> cboAulaHD;
    private javax.swing.JComboBox<String> cboCodigoHD;
    private javax.swing.JComboBox<String> cboCursoHD;
    private javax.swing.JComboBox<String> cboCursoN;
    private javax.swing.JComboBox<String> cboSeccionHD;
    private javax.swing.JComboBox<String> cboTurnoHD;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTable tablaAlu;
    private javax.swing.JTextField txtApeHA;
    private javax.swing.JTextField txtApellidoAlumno;
    private javax.swing.JTextField txtApellidoDocente;
    private javax.swing.JTextField txtCodigoDoceente;
    private javax.swing.JTextField txtExamF;
    private javax.swing.JTextField txtExamP;
    private javax.swing.JTextField txtHorarioAlumnoFin;
    private javax.swing.JTextField txtHorarioAlumnoIni;
    private javax.swing.JTextField txtHorarioFinHorarioD;
    private javax.swing.JTextField txtHorarioInicioHorarioD;
    private javax.swing.JTextField txtIdHorarioAlumno;
    private javax.swing.JTextField txtIdHorarioD;
    private javax.swing.JTextField txtIdNota;
    private javax.swing.JTextField txtNomHA;
    private javax.swing.JTextField txtNombreAlumno;
    private javax.swing.JTextField txtNombreDocente;
    private javax.swing.JTextField txtNot1;
    private javax.swing.JTextField txtProm;
    private javax.swing.JTextField txtcodigoAlumno;
    private javax.swing.JTextField txtnot2;
    private javax.swing.JTextField txtnot3;
    private javax.swing.JTextField txtnot4;
    // End of variables declaration//GEN-END:variables

}
