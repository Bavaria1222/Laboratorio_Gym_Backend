package org.example.Service;

import org.example.Entity.Profesor;
import org.example.Exeption.GlobalException;
import org.example.Exeption.NoDataException;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ServicioProfesor extends Servicio {

    // Definición de las cadenas de llamada a los procedimientos y funciones de Profesor.
    private static final String insertarProfesor = "{call insertarProfesorCompleto(?,?,?,?,?,?)}";
    private static final String listarProfesor = "{? = call listarProfesor()}";
    private static final String buscarProfesor = "{? = call buscarProfesor(?)}";
    private static final String modificarProfesor = "{call modificarProfesor(?,?,?,?,?,?)}";
    private static final String eliminarProfesor = "{call eliminarProfesor(?)}";

    public ServicioProfesor() {
    }


    public void insertarProfesor(Profesor profesor, String clave) throws GlobalException, NoDataException {
        try {
            this.conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        CallableStatement pstmt = null;
        try {
            pstmt = this.conexion.prepareCall(insertarProfesor);
            pstmt.setString(1, profesor.getCedula());
            pstmt.setString(2, clave);
            pstmt.setString(3, "PROFESOR"); // Se asigna el rol por defecto
            pstmt.setString(4, profesor.getNombre());
            pstmt.setString(5, profesor.getTelefono());
            pstmt.setString(6, profesor.getEmail());

            pstmt.execute();
            System.out.println("\nInserción de Profesor Satisfactoria!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Error en la inserción de Profesor: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                this.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Error cerrando recursos: " + e.getMessage());
            }
        }
    }


    public Collection<Profesor> listarProfesor() throws GlobalException, NoDataException {
        try {
            this.conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        ArrayList<Profesor> lista = new ArrayList<>();
        ResultSet rs = null;
        CallableStatement pstmt = null;
        try {
            pstmt = this.conexion.prepareCall(listarProfesor);
            pstmt.registerOutParameter(1, -10); // -10 equivale a OracleTypes.CURSOR
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                Profesor profesor = new Profesor(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
                // Si es necesario, también se puede recuperar la clave o rol, aunque normalmente no se muestra.
                lista.add(profesor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Error al listar profesores: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                this.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Error cerrando recursos: " + e.getMessage());
            }
        }
        if (!lista.isEmpty()) {
            return lista;
        } else {
            throw new NoDataException("No hay datos");
        }
    }


    public Profesor buscarProfesor(String cedula) throws GlobalException, NoDataException {
        try {
            this.conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        Profesor profesor = null;
        ResultSet rs = null;
        CallableStatement pstmt = null;
        try {
            pstmt = this.conexion.prepareCall(buscarProfesor);
            pstmt.registerOutParameter(1, -10); // Registramos el cursor
            pstmt.setString(2, cedula);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            if (rs.next()) {
                profesor = new Profesor(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Error en la búsqueda de Profesor: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                this.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Error cerrando recursos: " + e.getMessage());
            }
        }
        return profesor;
    }


    public void modificarProfesor(Profesor profesor, String clave) throws GlobalException, NoDataException {
        // Se actualizarán datos en la tabla usuario (clave) y profesor (nombre, teléfono, email)
        try {
            this.conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        CallableStatement pstmt = null;
        try {
            pstmt = this.conexion.prepareCall(modificarProfesor);
            pstmt.setString(1, profesor.getCedula());
            pstmt.setString(2, clave);
            pstmt.setString(3, profesor.getNombre());
            pstmt.setString(4, profesor.getTelefono());
            pstmt.setString(5, profesor.getEmail());
            pstmt.registerOutParameter(6, java.sql.Types.INTEGER);
            pstmt.execute();
            int rows = pstmt.getInt(6);
            if (rows == 0) {
                throw new NoDataException("No se realizó la actualización");
            }
            System.out.println("\nModificación Satisfactoria!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Error en la modificación de Profesor: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                this.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Error cerrando recursos: " + e.getMessage());
            }
        }
    }


    public void eliminarProfesor(String cedula) throws GlobalException, NoDataException {
        try {
            this.conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        CallableStatement pstmt = null;
        try {
            pstmt = this.conexion.prepareCall(eliminarProfesor);
            pstmt.setString(1, cedula);
            int resultado = pstmt.executeUpdate();
            if (resultado == 0) {
                throw new NoDataException("No se realizó el borrado");
            }
            System.out.println("\nEliminación Satisfactoria!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Error en la eliminación de Profesor: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                this.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Error cerrando recursos: " + e.getMessage());
            }
        }
    }
}
