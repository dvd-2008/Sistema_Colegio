-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3315
-- Tiempo de generación: 22-05-2024 a las 23:58:40
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `colegio`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE `alumno` (
  `cod_alumno` varchar(5) NOT NULL,
  `nom_alumno` varchar(30) DEFAULT NULL,
  `ape_alumno` varchar(30) DEFAULT NULL,
  `sexo` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`cod_alumno`, `nom_alumno`, `ape_alumno`, `sexo`) VALUES
('al01', 'Yeico', 'Pinillos', 'hombre'),
('al02', 'Junior', 'Tejadas', 'hombre'),
('al03', 'Karla', 'Vargas', 'mujer'),
('al04', 'Ricardo', 'González', 'hombre'),
('al05', 'Sandra', 'López', 'mujer'),
('al06', 'Pedro', 'Sánchez', 'hombre'),
('al07', 'David', 'Huaman', 'hombre'),
('al08', 'Daniel', 'Hardman', 'hombre'),
('al09', 'maria', 'sanchez', 'mujer'),
('al10', 'gerard', 'huaman', 'hombre'),
('al11', 'geremy', 'malpica', 'hombre'),
('al12', 'daniel', 'julca', 'hombre'),
('al13', 'pereira', 'pinedo', 'hombre'),
('al14', 'patrick', 'perez', 'hombre'),
('al15', 'Luisa', 'González', 'mujer'),
('al16', 'Martín', 'Sánchez', 'hombre'),
('al17', 'Laura', 'Pérez', 'mujer'),
('al18', 'Mario', 'Martínez', 'hombre'),
('al19', 'Carolina', 'Gómez', 'mujer'),
('al20', 'Gabriel', 'Rodríguez', 'hombre'),
('al21', 'Valeria', 'Hernández', 'mujer'),
('al22', 'Miguel', 'López', 'hombre'),
('al23', 'Alejandra', 'Díaz', 'mujer'),
('al24', 'Eduardo', 'Fernández', 'hombre'),
('al25', 'Natalia', 'Torres', 'mujer'),
('al26', 'Carlos', 'Rivera', 'hombre'),
('al27', 'Verónica', 'Ramírez', 'mujer'),
('al28', 'Javier', 'Alvarez', 'hombre'),
('al29', 'Sofía', 'Santos', 'mujer'),
('al30', 'Andrés', 'Vargas', 'hombre'),
('al31', 'Ana', 'Jiménez', 'mujer'),
('al32', 'Fernando', 'Gutiérrez', 'hombre'),
('al33', 'Patricia', 'Ortega', 'mujer'),
('al34', 'Diego', 'Navarro', 'hombre'),
('al36', 'geraldine', 'maxi', 'mujer'),
('al37', 'kkk', 'kkkk', 'hombre'),
('al40', 'carlos', 'quesada', 'hombre'),
('al41', 'nancy', 'lopez', 'mujer'),
('al50', 'jorge', 'cuadroz', 'hombre');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aula`
--

CREATE TABLE `aula` (
  `cod_Aula` varchar(5) NOT NULL,
  `nom_Aula` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `aula`
--

INSERT INTO `aula` (`cod_Aula`, `nom_Aula`) VALUES
('a001', '1 Primaria'),
('a002', '2 Primaria'),
('a003', '3 Primaria'),
('a004', '4 Primaria'),
('a005', '5 Primaria'),
('a006', '6 Primaria'),
('a007', '1 Secundaria'),
('a008', '2 Secundaria'),
('a009', '3 Secundaria'),
('a010', '4 Secundaria'),
('a011', '5 Secundaria');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `cod_cate` varchar(5) NOT NULL,
  `nom_cate` varchar(30) DEFAULT NULL,
  `cat_pension` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`cod_cate`, `nom_cate`, `cat_pension`) VALUES
('cat01', 'A', 550.00),
('cat02', 'B', 500.00),
('cat03', 'C', 460.00),
('cat04', 'D', 400.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso`
--

CREATE TABLE `curso` (
  `cod_curso` varchar(5) NOT NULL,
  `nom_curso` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `curso`
--

INSERT INTO `curso` (`cod_curso`, `nom_curso`) VALUES
('c001', 'Matemática'),
('c002', 'Álgebra'),
('c003', 'Geometría'),
('c004', 'Física'),
('c005', 'Química'),
('c006', 'Biología'),
('c007', 'Historia'),
('c008', 'Geografía'),
('c009', 'Educación Cívica'),
('c010', 'Lengua y Literatura'),
('c011', 'Idioma Extranjero'),
('c012', 'Filosofía'),
('c013', 'Aritmética'),
('c014', 'Comunicación'),
('c015', 'Literatura'),
('c016', 'Educación Física'),
('c017', 'Arte'),
('c018', 'Música'),
('c019', 'Tecnología'),
('c020', 'Computación');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `docente`
--

CREATE TABLE `docente` (
  `cod_docente` varchar(5) NOT NULL,
  `nom_docente` varchar(30) DEFAULT NULL,
  `ape_docente` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `docente`
--

INSERT INTO `docente` (`cod_docente`, `nom_docente`, `ape_docente`) VALUES
('d001', 'Juan', 'Perez'),
('d002', 'Herson', 'Huaman'),
('d003', 'María', 'Gómez'),
('d004', 'Luis', 'Rodríguez'),
('d005', 'Ana', 'Martínez'),
('d006', 'Jorge', 'García'),
('d007', 'Carla', 'López'),
('d008', 'Pedro', 'Sánchez'),
('d009', 'Sofía', 'Díaz'),
('d010', 'Diego', 'Fernández'),
('d011', 'David', 'HuMANA'),
('d012', 'pepe', 'grillo'),
('d013', 'geraldine', 'maxi');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horario_alumno`
--

CREATE TABLE `horario_alumno` (
  `ID_HORARIO_ALUMNO` varchar(5) NOT NULL,
  `cod_alumno` varchar(5) DEFAULT NULL,
  `cod_T` varchar(5) DEFAULT NULL,
  `cod_Aula` varchar(5) DEFAULT NULL,
  `cod_secc` varchar(5) DEFAULT NULL,
  `hora_ini_a` time DEFAULT NULL,
  `hora_fin_a` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `horario_alumno`
--

INSERT INTO `horario_alumno` (`ID_HORARIO_ALUMNO`, `cod_alumno`, `cod_T`, `cod_Aula`, `cod_secc`, `hora_ini_a`, `hora_fin_a`) VALUES
('ha001', 'al01', 't001', 'a001', 's001', '08:00:00', '12:00:00'),
('ha002', 'al04', 't001', 'a006', 's003', '15:00:02', '18:00:03'),
('ha003', 'al03', 't001', 'a003', 's003', '09:00:00', '13:00:00'),
('ha004', 'al09', 't002', 'a007', 's004', '15:00:02', '18:00:03'),
('ha009', 'al04', 't003', 'a008', 's005', '09:00:02', '15:30:30'),
('ha018', 'al15', 't001', 'a004', 's002', '08:00:00', '12:00:00'),
('ha019', 'al16', 't002', 'a005', 's003', '10:00:00', '14:00:00'),
('ha020', 'al17', 't003', 'a006', 's001', '13:00:00', '17:00:00'),
('ha021', 'al18', 't001', 'a007', 's002', '08:00:00', '12:00:00'),
('ha022', 'al19', 't002', 'a008', 's003', '10:00:00', '14:00:00'),
('ha023', 'al20', 't003', 'a009', 's001', '13:00:00', '17:00:00'),
('ha024', 'al21', 't001', 'a010', 's002', '08:00:00', '12:00:00'),
('ha025', 'al22', 't002', 'a011', 's003', '10:00:00', '14:00:00'),
('ha026', 'al23', 't003', 'a001', 's001', '13:00:00', '17:00:00'),
('ha027', 'al24', 't001', 'a002', 's002', '08:00:00', '12:00:00'),
('ha028', 'al25', 't002', 'a003', 's003', '10:00:00', '14:00:00'),
('ha029', 'al26', 't003', 'a004', 's001', '13:00:00', '17:00:00'),
('ha030', 'al27', 't001', 'a005', 's002', '08:00:00', '12:00:00'),
('ha031', 'al28', 't002', 'a006', 's003', '10:00:00', '14:00:00'),
('ha032', 'al29', 't003', 'a007', 's001', '13:00:00', '17:00:00'),
('ha033', 'al30', 't001', 'a008', 's002', '08:00:00', '12:00:00'),
('ha034', 'al31', 't002', 'a009', 's003', '10:00:00', '14:00:00'),
('ha035', 'al32', 't003', 'a010', 's001', '13:00:00', '17:00:00'),
('ha036', 'al33', 't001', 'a011', 's002', '08:00:00', '12:00:00'),
('ha037', 'al34', 't002', 'a001', 's003', '10:00:00', '14:00:00'),
('ha038', 'al15', 't003', 'a002', 's001', '13:00:00', '17:00:00'),
('ha039', 'al16', 't001', 'a003', 's002', '08:00:00', '12:00:00'),
('ha040', 'al17', 't002', 'a004', 's003', '10:00:00', '14:00:00'),
('ha041', 'al18', 't003', 'a005', 's001', '13:00:00', '17:00:00'),
('ha042', 'al19', 't001', 'a006', 's002', '08:00:00', '12:00:00'),
('ha043', 'al20', 't002', 'a007', 's003', '10:00:00', '14:00:00'),
('ha044', 'al21', 't003', 'a008', 's001', '13:00:00', '17:00:00'),
('ha045', 'al22', 't001', 'a009', 's002', '08:00:00', '12:00:00'),
('ha046', 'al23', 't002', 'a010', 's003', '10:00:00', '14:00:00'),
('ha047', 'al24', 't003', 'a011', 's001', '13:00:00', '17:00:00'),
('ha048', 'al25', 't001', 'a001', 's002', '08:00:00', '12:00:00'),
('ha049', 'al26', 't002', 'a002', 's003', '10:00:00', '14:00:00'),
('ha050', 'al27', 't003', 'a003', 's001', '13:00:00', '17:00:00'),
('ha051', 'al50', 't001', 'a007', 's002', '07:00:00', '15:00:00'),
('ha17', 'al01', 't001', 'a001', 's001', '12:00:12', '15:02:16'),
('ha51', 'al41', 't002', 'a010', 's004', '07:00:00', '15:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horario_docente`
--

CREATE TABLE `horario_docente` (
  `ID_HORARIO_DOCENTE` varchar(5) NOT NULL,
  `cod_docente` varchar(5) DEFAULT NULL,
  `cod_T` varchar(5) DEFAULT NULL,
  `cod_Aula` varchar(5) DEFAULT NULL,
  `cod_secc` varchar(5) DEFAULT NULL,
  `cod_curso` varchar(5) DEFAULT NULL,
  `hora_ini_d` time DEFAULT NULL,
  `hora_fin_d` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `horario_docente`
--

INSERT INTO `horario_docente` (`ID_HORARIO_DOCENTE`, `cod_docente`, `cod_T`, `cod_Aula`, `cod_secc`, `cod_curso`, `hora_ini_d`, `hora_fin_d`) VALUES
('hd001', 'd001', 't001', 'a001', 's001', 'c001', '08:00:00', '10:00:00'),
('hd002', 'd002', 't001', 'a002', 's002', 'c002', '10:00:00', '12:00:00'),
('hd003', 'd011', 't002', 'a003', 's003', 'c003', '13:00:00', '15:00:00'),
('hd006', 'd001', 't001', 'a001', 's001', 'c007', '08:00:00', '10:00:00'),
('hd007', 'd003', 't001', 'a001', 's001', 'c008', '08:00:00', '10:00:00'),
('hd008', 'd004', 't002', 'a002', 's002', 'c009', '10:00:00', '12:00:00'),
('hd009', 'd005', 't003', 'a003', 's003', 'c010', '13:00:00', '15:00:00'),
('hd010', 'd006', 't001', 'a004', 's001', 'c011', '08:00:00', '10:00:00'),
('hd011', 'd007', 't002', 'a005', 's002', 'c012', '10:00:00', '12:00:00'),
('hd012', 'd008', 't003', 'a006', 's003', 'c013', '13:00:00', '15:00:00'),
('hd013', 'd009', 't001', 'a007', 's001', 'c014', '08:00:00', '10:00:00'),
('hd014', 'd010', 't002', 'a008', 's002', 'c015', '10:00:00', '12:00:00'),
('hd015', 'd011', 't003', 'a009', 's003', 'c016', '13:00:00', '15:00:00'),
('hd016', 'd012', 't001', 'a010', 's001', 'c017', '08:00:00', '10:00:00'),
('hd017', 'd001', 't002', 'a011', 's002', 'c018', '10:00:00', '12:00:00'),
('hd018', 'd002', 't003', 'a001', 's003', 'c019', '13:00:00', '15:00:00'),
('hd019', 'd003', 't001', 'a002', 's001', 'c020', '08:00:00', '10:00:00'),
('hd020', 'd004', 't002', 'a003', 's002', 'c001', '10:00:00', '12:00:00'),
('hd021', 'd005', 't003', 'a004', 's003', 'c002', '13:00:00', '15:00:00'),
('hd022', 'd006', 't001', 'a005', 's001', 'c003', '08:00:00', '10:00:00'),
('hd023', 'd007', 't002', 'a006', 's002', 'c004', '10:00:00', '12:00:00'),
('hd024', 'd008', 't003', 'a007', 's003', 'c005', '13:00:00', '15:00:00'),
('hd025', 'd009', 't001', 'a008', 's001', 'c006', '08:00:00', '10:00:00'),
('hd026', 'd010', 't002', 'a009', 's002', 'c007', '10:00:00', '12:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notas`
--

CREATE TABLE `notas` (
  `cod_notas` varchar(5) NOT NULL,
  `cod_alumno` varchar(5) DEFAULT NULL,
  `cod_curso` varchar(5) DEFAULT NULL,
  `not1` decimal(5,2) DEFAULT NULL,
  `not2` decimal(5,2) DEFAULT NULL,
  `not3` decimal(5,2) DEFAULT NULL,
  `not4` decimal(5,2) DEFAULT NULL,
  `exame_p` decimal(5,2) DEFAULT NULL,
  `exame_f` decimal(5,2) DEFAULT NULL,
  `promedio` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `notas`
--

INSERT INTO `notas` (`cod_notas`, `cod_alumno`, `cod_curso`, `not1`, `not2`, `not3`, `not4`, `exame_p`, `exame_f`, `promedio`) VALUES
('n001', 'al01', 'c001', 15.50, 16.20, 14.80, 17.30, 16.00, 18.50, 16.38),
('n002', 'al02', 'c002', 12.30, 13.80, 14.50, 15.70, 14.00, 16.50, 14.47),
('n003', 'al03', 'c003', 10.00, 15.20, 16.80, 13.90, 17.50, 16.20, 14.93),
('n004', 'al04', 'c004', 17.20, 16.50, 18.30, 15.60, 19.00, 17.80, 17.40),
('n005', 'al05', 'c005', 13.90, 14.70, 16.20, 15.30, 15.00, 14.90, 15.00),
('n006', 'al06', 'c006', 15.80, 16.30, 15.70, 17.50, 16.90, 18.20, 16.73),
('n007', 'al07', 'c007', 16.50, 17.80, 15.60, 16.90, 17.30, 16.80, 16.82),
('n008', 'al08', 'c008', 14.20, 15.90, 16.70, 14.80, 15.50, 16.30, 15.57),
('n009', 'al09', 'c009', 17.70, 18.50, 17.20, 16.90, 18.30, 17.60, 17.70),
('n010', 'al10', 'c010', 13.60, 14.80, 15.20, 14.50, 15.90, 14.70, 14.78),
('n011', 'al11', 'c011', 16.90, 17.30, 18.00, 17.50, 17.80, 16.70, 17.37),
('n012', 'al12', 'c012', 15.40, 16.70, 15.80, 16.30, 17.20, 16.50, 16.32),
('n013', 'al13', 'c013', 17.80, 16.90, 18.50, 17.20, 17.90, 18.30, 17.77),
('n014', 'al14', 'c014', 14.50, 15.20, 13.80, 14.90, 15.30, 16.20, 14.98),
('n015', 'al01', 'c001', 16.20, 17.30, 16.50, 17.80, 17.00, 16.50, 16.88),
('n016', 'al02', 'c002', 14.80, 15.50, 14.20, 15.90, 15.50, 16.00, 15.32),
('n017', 'al03', 'c003', 15.70, 16.20, 15.90, 16.30, 16.80, 17.50, 16.40),
('n018', 'al04', 'c004', 17.00, 18.20, 17.50, 18.00, 17.50, 18.30, 17.75),
('n019', 'al05', 'c005', 14.20, 15.60, 14.80, 15.30, 15.60, 14.90, 15.07),
('n020', 'al06', 'c006', 16.50, 17.20, 16.80, 17.50, 17.00, 16.70, 16.95),
('n026', 'al31', 'c014', 12.00, 15.00, 10.00, 16.00, 15.00, 20.00, 14.67),
('no01', 'al01', 'c001', 12.00, 15.00, 16.00, 18.00, 15.00, 12.00, 14.67),
('no02', 'al03', 'c004', 12.00, 19.00, 18.00, 12.00, 16.00, 10.00, 14.50),
('no36', NULL, 'c001', 20.00, 10.00, 14.00, 15.00, 20.00, 12.00, 15.17);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pension_alumno`
--

CREATE TABLE `pension_alumno` (
  `cod_pension` varchar(5) NOT NULL,
  `cod_alumno` varchar(5) DEFAULT NULL,
  `cod_cate` varchar(5) DEFAULT NULL,
  `cod_notas` varchar(5) DEFAULT NULL,
  `pension` decimal(5,2) DEFAULT NULL,
  `pension_actual` decimal(5,2) DEFAULT NULL,
  `pension_nueva` decimal(5,2) DEFAULT NULL,
  `fecha_cancelar` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seccion`
--

CREATE TABLE `seccion` (
  `cod_secc` varchar(5) NOT NULL,
  `nom_secc` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `seccion`
--

INSERT INTO `seccion` (`cod_secc`, `nom_secc`) VALUES
('s001', 'A'),
('s002', 'B'),
('s003', 'C'),
('s004', 'D'),
('s005', 'E'),
('s006', 'F');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `turno`
--

CREATE TABLE `turno` (
  `cod_T` varchar(5) NOT NULL,
  `nom_turno` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `turno`
--

INSERT INTO `turno` (`cod_T`, `nom_turno`) VALUES
('t001', 'Mañana'),
('t002', 'Tarde'),
('t003', 'Noche');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD PRIMARY KEY (`cod_alumno`);

--
-- Indices de la tabla `aula`
--
ALTER TABLE `aula`
  ADD PRIMARY KEY (`cod_Aula`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`cod_cate`);

--
-- Indices de la tabla `curso`
--
ALTER TABLE `curso`
  ADD PRIMARY KEY (`cod_curso`);

--
-- Indices de la tabla `docente`
--
ALTER TABLE `docente`
  ADD PRIMARY KEY (`cod_docente`);

--
-- Indices de la tabla `horario_alumno`
--
ALTER TABLE `horario_alumno`
  ADD PRIMARY KEY (`ID_HORARIO_ALUMNO`),
  ADD KEY `cod_alumno` (`cod_alumno`),
  ADD KEY `cod_T` (`cod_T`),
  ADD KEY `cod_Aula` (`cod_Aula`),
  ADD KEY `cod_secc` (`cod_secc`);

--
-- Indices de la tabla `horario_docente`
--
ALTER TABLE `horario_docente`
  ADD PRIMARY KEY (`ID_HORARIO_DOCENTE`),
  ADD KEY `cod_docente` (`cod_docente`),
  ADD KEY `cod_T` (`cod_T`),
  ADD KEY `cod_Aula` (`cod_Aula`),
  ADD KEY `cod_secc` (`cod_secc`),
  ADD KEY `cod_curso` (`cod_curso`);

--
-- Indices de la tabla `notas`
--
ALTER TABLE `notas`
  ADD PRIMARY KEY (`cod_notas`),
  ADD KEY `cod_alumno` (`cod_alumno`),
  ADD KEY `cod_curso` (`cod_curso`);

--
-- Indices de la tabla `pension_alumno`
--
ALTER TABLE `pension_alumno`
  ADD PRIMARY KEY (`cod_pension`),
  ADD KEY `cod_alumno` (`cod_alumno`),
  ADD KEY `cod_cate` (`cod_cate`),
  ADD KEY `cod_notas` (`cod_notas`);

--
-- Indices de la tabla `seccion`
--
ALTER TABLE `seccion`
  ADD PRIMARY KEY (`cod_secc`);

--
-- Indices de la tabla `turno`
--
ALTER TABLE `turno`
  ADD PRIMARY KEY (`cod_T`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `horario_alumno`
--
ALTER TABLE `horario_alumno`
  ADD CONSTRAINT `horario_alumno_ibfk_1` FOREIGN KEY (`cod_alumno`) REFERENCES `alumno` (`cod_alumno`),
  ADD CONSTRAINT `horario_alumno_ibfk_2` FOREIGN KEY (`cod_T`) REFERENCES `turno` (`cod_T`),
  ADD CONSTRAINT `horario_alumno_ibfk_3` FOREIGN KEY (`cod_Aula`) REFERENCES `aula` (`cod_Aula`),
  ADD CONSTRAINT `horario_alumno_ibfk_4` FOREIGN KEY (`cod_secc`) REFERENCES `seccion` (`cod_secc`);

--
-- Filtros para la tabla `horario_docente`
--
ALTER TABLE `horario_docente`
  ADD CONSTRAINT `horario_docente_ibfk_1` FOREIGN KEY (`cod_docente`) REFERENCES `docente` (`cod_docente`),
  ADD CONSTRAINT `horario_docente_ibfk_2` FOREIGN KEY (`cod_T`) REFERENCES `turno` (`cod_T`),
  ADD CONSTRAINT `horario_docente_ibfk_3` FOREIGN KEY (`cod_Aula`) REFERENCES `aula` (`cod_Aula`),
  ADD CONSTRAINT `horario_docente_ibfk_4` FOREIGN KEY (`cod_secc`) REFERENCES `seccion` (`cod_secc`),
  ADD CONSTRAINT `horario_docente_ibfk_5` FOREIGN KEY (`cod_curso`) REFERENCES `curso` (`cod_curso`);

--
-- Filtros para la tabla `notas`
--
ALTER TABLE `notas`
  ADD CONSTRAINT `notas_ibfk_1` FOREIGN KEY (`cod_alumno`) REFERENCES `alumno` (`cod_alumno`),
  ADD CONSTRAINT `notas_ibfk_2` FOREIGN KEY (`cod_curso`) REFERENCES `curso` (`cod_curso`);

--
-- Filtros para la tabla `pension_alumno`
--
ALTER TABLE `pension_alumno`
  ADD CONSTRAINT `pension_alumno_ibfk_1` FOREIGN KEY (`cod_alumno`) REFERENCES `alumno` (`cod_alumno`),
  ADD CONSTRAINT `pension_alumno_ibfk_2` FOREIGN KEY (`cod_cate`) REFERENCES `categoria` (`cod_cate`),
  ADD CONSTRAINT `pension_alumno_ibfk_3` FOREIGN KEY (`cod_notas`) REFERENCES `notas` (`cod_notas`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
