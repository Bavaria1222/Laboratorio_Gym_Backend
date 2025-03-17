package org.example.Service;

import org.example.Entity.Carrera;
import org.example.Exeption.GlobalException;
import org.example.Exeption.NoDataException;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ServicioCarrera extends Servicio {

    private static final String insertarCarrera = "{call insertarcarrera(?,?,?,?)}";
    private static final String listarCarrera = "{call listarCarrera(?)}";
    private static final String buscarXId = "{?=call buscarCarrera(?)}";
    private static final String modificarCarrera = "{call modificarCarrera(?,?,?,?,?)}";
    private static final String eliminarCarrera = "{call eliminarCarrera(?)}";


    public ServicioCarrera() {

    }

    public void insertarCarrera(Carrera laCarrera) throws GlobalException, NoDataException {
        try {
            this.conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        CallableStatement pstmt = null;
        try {
            // Llamada al procedimiento almacenado insertarcarrera con 4 parámetros
            pstmt = this.conexion.prepareCall("{call insertarcarrera(?,?,?,?)}");

            // Asignar valores a cada parámetro en el orden definido en el procedimiento
            pstmt.setInt(1, laCarrera.getIdCarrera());
            pstmt.setString(2, laCarrera.getCodigo());
            pstmt.setString(3, laCarrera.getNombre());
            pstmt.setString(4, laCarrera.getTitulo());

            // Ejecutar la llamada
            boolean resultado = pstmt.execute();
            if (resultado) {
                throw new NoDataException("No se realizó la inserción");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Llave duplicada u otro error SQL");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                this.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos inválidos o nulos");
            }
        }
    }

    public Collection<Carrera> listarCarrera() throws GlobalException, NoDataException {
        try {
            this.conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        ResultSet rs = null;
        ArrayList<Carrera> coleccion = new ArrayList<>();
        CallableStatement pstmt = null;

        try {
            // Llamada a la función listarcarrera que retorna un ref cursor
            pstmt = this.conexion.prepareCall("{? = call listarcarrera()}");

            // Registrar el primer parámetro (de salida) como cursor
            // El valor -10 corresponde a OracleTypes.CURSOR (o puedes usar OracleTypes.CURSOR si importas oracle.jdbc.OracleTypes)
            pstmt.registerOutParameter(1, -10);

            // Ejecutar la llamada a la función
            pstmt.execute();

            // Recuperar el cursor convertido a ResultSet
            rs = (ResultSet) pstmt.getObject(1);

            // Recorrer el ResultSet y mapear cada fila a un objeto Carrera
            while (rs.next()) {
                Carrera carrera = new Carrera(
                        rs.getInt("idcarrera"),
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("titulo")
                );
                coleccion.add(carrera);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Sentencia no válida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                this.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos inválidos o nulos");
            }
        }

        if (!coleccion.isEmpty()) {
            return coleccion;
        } else {
            throw new NoDataException("No hay datos");
        }
    }

    public Carrera buscarCarrera(int id) throws GlobalException, NoDataException {
        try {
            this.conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        ResultSet rs = null;
        Carrera laCarrera = null;
        CallableStatement pstmt = null;

        try {
            // Llamada a la función almacenada buscarCarrera que retorna un ref cursor
            pstmt = this.conexion.prepareCall("{? = call buscarCarrera(?)}");
            // Registrar el parámetro de salida (cursor)
            pstmt.registerOutParameter(1, -10);  // -10 equivale a OracleTypes.CURSOR
            // Asignar el parámetro de entrada (el id a buscar)
            pstmt.setInt(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);

            if (rs.next()) {
                laCarrera = new Carrera(
                        rs.getInt("idcarrera"),
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("titulo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Sentencia no válida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                this.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos inválidos o nulos");
            }
        }

        return laCarrera;
    }

    public void modificarCarrera(Carrera laCarrera) throws GlobalException, NoDataException {
        try {
            this.conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        CallableStatement pstmt = null;
        try {
            pstmt = this.conexion.prepareCall(modificarCarrera);
            pstmt.setInt(1, laCarrera.getIdCarrera());
            pstmt.setString(2, laCarrera.getCodigo());
            pstmt.setString(3, laCarrera.getNombre());
            pstmt.setString(4, laCarrera.getTitulo());
            // Registrar el parámetro de salida para el update count
            pstmt.registerOutParameter(5, java.sql.Types.INTEGER);

            pstmt.execute();
            int rows = pstmt.getInt(5);
            if (rows == 0) {
                throw new NoDataException("No se realizó la actualización");
            }
            System.out.println("\nModificación Satisfactoria!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Sentencia no válida");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                this.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos inválidos o nulos");
            }
        }
    }

    public void eliminarCarrera(int id) throws GlobalException, NoDataException {
        try {
            this.conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        CallableStatement pstmt = null;

        try {
            pstmt = this.conexion.prepareCall(eliminarCarrera);
            pstmt.setInt(1, id);
            int resultado = pstmt.executeUpdate();
            if (resultado == 0) {
                throw new NoDataException("No se realizó el borrado");
            }
            System.out.println("\nEliminación Satisfactoria!");
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no válida");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                this.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos inválidos o nulos");
            }
        }
    }




}


