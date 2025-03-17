package org.example;

import org.example.Controller.*;
import org.example.Entity.*;
import org.example.Exeption.GlobalException;
import org.example.Exeption.NoDataException;
import org.example.Service.ServicioCarrera;

import java.time.LocalDate;
import java.util.Collection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ControlCarrera controller = new ControlCarrera();
        ControlUsuario controlUsuario = new ControlUsuario();
        ControlCurso controlCurso = new ControlCurso();
        ControlPlanEstudio controlPlan = new ControlPlanEstudio();
        ControlProfesor controlProfesor = new ControlProfesor();
        ControlAlumno controlAlumno = new ControlAlumno();
        ControlCiclo controlCiclo = new ControlCiclo();
        ControlGrupo controlGrupo = new ControlGrupo();
        ControlMatricula controlMatricula = new ControlMatricula();

        //Testing  Carrera

            // 1. Insertar una nueva carrera
            Carrera nuevaCarrera = new Carrera(3, "COD_NUEVO", "Ingeniería de Computadores", "Título en Ingeniería de Computadores");
            try {
                controller.insertarCarrera(nuevaCarrera);
                System.out.println("Inserción exitosa: " + nuevaCarrera);
            } catch (GlobalException | NoDataException e) {
                System.err.println("Error insertando carrera: " + e.getMessage());
            }

            // 2. Listar todas las carreras
            try {
                Collection<Carrera> carreras = controller.listarCarreras();
                System.out.println("Listado de carreras:");
                carreras.forEach(System.out::println);
            } catch (GlobalException | NoDataException e) {
                System.err.println("Error listando carreras: " + e.getMessage());
            }

            // 3. Buscar una carrera por id
            try {
                Carrera encontrada = controller.buscarCarrera(1);
                if (encontrada != null) {
                    System.out.println("Carrera encontrada: " + encontrada);
                } else {
                    System.out.println("No se encontró la carrera con id 3");
                }
            } catch (GlobalException | NoDataException e) {
                System.err.println("Error buscando carrera: " + e.getMessage());
            }

            // 4. Modificar la carrera
            Carrera carreraModificada = new Carrera(3, "COD_MOD", "Ingeniería de Software Avanzada", "Título Modificado");
            try {
                controller.modificarCarrera(carreraModificada);
                System.out.println("Modificación exitosa: " + carreraModificada);
            } catch (GlobalException | NoDataException e) {
                System.err.println("Error modificando carrera: " + e.getMessage());
            }

            // 5. Eliminar la carrera
            try {
                controller.eliminarCarrera(3);
                System.out.println("Eliminación exitosa de la carrera con id 3");
            } catch (GlobalException | NoDataException e) {
                System.err.println("Error eliminando la carrera: " + e.getMessage());
            }


        // Testing  Curso
        // 1. Insertar un nuevo curso
        Curso nuevoCurso = new Curso(0, "COD101", "Introducción a la Programación", 4, 3);
        try {
            controlCurso.insertarCurso(nuevoCurso);
            System.out.println("Curso insertado: " + nuevoCurso);
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al insertar curso: " + e.getMessage());
        }

        // 2. Listar todos los cursos
        try {
            Collection<Curso> cursos = controlCurso.listarCurso();
            System.out.println("\nListado de cursos:");
            for (Curso c : cursos) {
                System.out.println(c);
            }
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al listar cursos: " + e.getMessage());
        }

        // 3. Buscar un curso por id
        try {
            Curso cursoEncontrado = controlCurso.buscarCurso(1);
            if (cursoEncontrado != null) {
                System.out.println("\nCurso encontrado: " + cursoEncontrado);
            } else {
                System.out.println("\nNo se encontró curso con id 1");
            }
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al buscar curso: " + e.getMessage());
        }

        // 4. Modificar un curso
        Curso cursoModificado = new Curso(1, "COD101", "Programación Avanzada", 4, 4);
        try {
            controlCurso.modificarCurso(cursoModificado);
            System.out.println("\nCurso modificado: " + cursoModificado);
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al modificar curso: " + e.getMessage());
        }

        // 5. Eliminar un curso
        try {

            controlCurso.eliminarCurso(3);
            System.out.println("\nCurso eliminado: id = 3");
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al eliminar curso: " + e.getMessage());
        }

        //Testing  PlanEstudio
        // 1. Insertar un nuevo PlanEstudio
        PlanEstudio nuevoPlan = new PlanEstudio(0, 1, 1, 2023, 1);
        try {
            controlPlan.insertarPlanEstudio(nuevoPlan);
            System.out.println("Plan de estudios insertado: " + nuevoPlan);
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al insertar plan: " + e.getMessage());
        }

        // 2. Listar todos los PlanEstudio
        try {
            Collection<PlanEstudio> planes = controlPlan.listarPlanEstudio();
            System.out.println("\nListado de Planes de Estudios:");
            for (PlanEstudio plan : planes) {
                System.out.println(plan);
            }
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al listar planes: " + e.getMessage());
        }

        // 3. Buscar un PlanEstudio por id (por ejemplo, idPlanEstudio=1)
        try {
            PlanEstudio planEncontrado = controlPlan.buscarPlanEstudio(1);
            if (planEncontrado != null) {
                System.out.println("\nPlan de estudios encontrado: " + planEncontrado);
            } else {
                System.out.println("\nNo se encontró plan de estudios con id 1");
            }
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al buscar plan: " + e.getMessage());
        }


         //4. Modificar un PlanEstudio
        PlanEstudio planModificado = new PlanEstudio(2, 1, 1, 2023, 2); // Cambiamos el ciclo de 1 a 2.
        try {
            controlPlan.modificarPlanEstudio(planModificado);
            System.out.println("\nPlan de estudios modificado: " + planModificado);
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al modificar plan: " + e.getMessage());
        }

        // 5. Eliminar un PlanEstudio por id (por ejemplo, idPlanEstudio=1)
        try {
            controlPlan.eliminarPlanEstudio(3);
            System.out.println("\nPlan de estudios eliminado con id = 2");
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al eliminar plan: " + e.getMessage());
        }
        //Testing  Profesor
            Profesor profesorNuevo = new Profesor("987654", "Juan Pérez", "555-1234", "juan.perez@universidad.edu");

            try {
                // La clave se pasa por separado en el controlador o se asume por defecto en el constructor de Profesor
                controlProfesor.insertarProfesor(profesorNuevo, "passProfesor");
                System.out.println("Profesor insertado: " + profesorNuevo);
            } catch (GlobalException | NoDataException e) {
                System.err.println("Error al insertar profesor: " + e.getMessage());
                e.printStackTrace();
            }

            // 2. Listar todos los profesores
            try {
                Collection<Profesor> listaProfesores = controlProfesor.listarProfesor();
                System.out.println("\nListado de profesores:");
                for (Profesor prof : listaProfesores) {
                    System.out.println(prof);
                }
            } catch (GlobalException | NoDataException e) {
                System.err.println("Error al listar profesores: " + e.getMessage());
            }

            // 3. Buscar un profesor por cédula
            try {
                Profesor profEncontrado = controlProfesor.buscarProfesor("987654");
                if (profEncontrado != null) {
                    System.out.println("\nProfesor encontrado: " + profEncontrado);
                } else {
                    System.out.println("\nNo se encontró profesor con cédula 987654");
                }
            } catch (GlobalException | NoDataException e) {
                System.err.println("Error al buscar profesor: " + e.getMessage());
            }

            // 4. Modificar un profesor (por ejemplo, cambiar nombre, teléfono y email, y la clave)
            Profesor profesorModificado = new Profesor("987654", "Juan Carlos Pérez", "555-5678", "juan.c.perez@universidad.edu");
            try {
                // Se envía la nueva clave si se desea modificarla
                controlProfesor.modificarProfesor(profesorModificado, "newPassProfesor");
                System.out.println("\nProfesor modificado: " + profesorModificado);
            } catch (GlobalException | NoDataException e) {
                System.err.println("Error al modificar profesor: " + e.getMessage());
            }

            // 5. Eliminar el profesor
            try {
                controlProfesor.eliminarProfesor("987654");
                System.out.println("\nProfesor eliminado con cédula 987654");
            } catch (GlobalException | NoDataException e) {
                System.err.println("Error al eliminar profesor: " + e.getMessage());
            }
        //Testing  Alumno
        Alumno alumnoNuevo = new Alumno("111222", "María González", "555-7890", "maria.gonzalez@universidad.edu", LocalDate.of(2000, 5, 15), 1);
        try {
            controlAlumno.insertarAlumno(alumnoNuevo, "claveAlumno");
            System.out.println("Alumno insertado: " + alumnoNuevo);
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al insertar alumno: " + e.getMessage());
        }

        // 2. Listar todos los alumnos
        try {
            Collection<Alumno> listaAlumnos = controlAlumno.listarAlumno();
            System.out.println("\nListado de alumnos:");
            for (Alumno a : listaAlumnos) {
                System.out.println(a);
            }
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al listar alumnos: " + e.getMessage());
        }

        // 3. Buscar un alumno por cédula
        try {
            Alumno alumnoBuscado = controlAlumno.buscarAlumno("111222");
            if (alumnoBuscado != null) {
                System.out.println("\nAlumno encontrado: " + alumnoBuscado);
            } else {
                System.out.println("\nNo se encontró alumno con cédula 111222");
            }
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al buscar alumno: " + e.getMessage());
        }

        // 4. Modificar un alumno
        Alumno alumnoModificado = new Alumno("111222", "María López", "555-1234", "maria.lopez@universidad.edu", LocalDate.of(2000, 5, 15), 1);
        try {
            // Se pasa la nueva clave, en caso de modificarla (por ejemplo, "nuevaClaveAlumno")
            controlAlumno.modificarAlumno(alumnoModificado, "nuevaClaveAlumno");
            System.out.println("\nAlumno modificado: " + alumnoModificado);
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al modificar alumno: " + e.getMessage());
        }

        // 5. Eliminar un alumno
        try {
            controlAlumno.eliminarAlumno("111222");
            System.out.println("\nAlumno eliminado con cédula 111222");
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al eliminar alumno: " + e.getMessage());
        }

        //Test para ciclo

        // 1. Insertar un nuevo ciclo
        Ciclo nuevoCiclo = new Ciclo(2023, 1, LocalDate.of(2023, 1, 15), LocalDate.of(2023, 6, 15));
        try {
            controlCiclo.insertarCiclo(nuevoCiclo);
            System.out.println("Ciclo insertado: " + nuevoCiclo);
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al insertar ciclo: " + e.getMessage());
        }

        // 2. Listar todos los ciclos
        try {
            Collection<Ciclo> ciclos = controlCiclo.listarCiclo();
            System.out.println("\nListado de ciclos:");
            ciclos.forEach(System.out::println);
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al listar ciclos: " + e.getMessage());
        }

        // 3. Buscar un ciclo por id
        try {
            Ciclo cicloEncontrado = controlCiclo.buscarCiclo(3);
            if (cicloEncontrado != null) {
                System.out.println("\nCiclo encontrado: " + cicloEncontrado);
            } else {
                System.out.println("\nNo se encontró ciclo con id 3");
            }
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al buscar ciclo: " + e.getMessage());
        }

        // 4. Modificar un ciclo
        Ciclo cicloModificado = new Ciclo(1, 2023, 2, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 12, 15));
        try {
            controlCiclo.modificarCiclo(cicloModificado);
            System.out.println("\nCiclo modificado: " + cicloModificado);
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al modificar ciclo: " + e.getMessage());
        }

        // 5. Eliminar un ciclo id 3 (recien creado)
        try {
            controlCiclo.eliminarCiclo(3);
            System.out.println("\nCiclo eliminado con id = 3");
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al eliminar ciclo: " + e.getMessage());
        }

        //Test para grupo
        // 1. Insertar un nuevo grupo
        Grupo nuevoGrupo = new Grupo(0, 2, 2, 1, "Lunes y Miércoles 10:00-12:00", "P001");
        try {
            controlGrupo.insertarGrupo(nuevoGrupo);
            System.out.println("Grupo insertado: " + nuevoGrupo);
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al insertar grupo: " + e.getMessage());
        }

        // 2. Listar todos los grupos
        try {
            Collection<Grupo> grupos = controlGrupo.listarGrupo();
            System.out.println("\nListado de grupos:");
            for (Grupo g : grupos) {
                System.out.println(g);
            }
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al listar grupos: " + e.getMessage());
        }

        // 3. Buscar un grupo por id
        try {
            Grupo grupoEncontrado = controlGrupo.buscarGrupo(1);
            if (grupoEncontrado != null) {
                System.out.println("\nGrupo encontrado: " + grupoEncontrado);
            } else {
                System.out.println("\nNo se encontró grupo con id 1");
            }
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al buscar grupo: " + e.getMessage());
        }

        // 4. Modificar un grupo
        Grupo grupoModificado = new Grupo(1, 1, 2, 2, "Martes y Jueves 14:00-16:00", "P002");
        try {
            controlGrupo.modificarGrupo(grupoModificado);
            System.out.println("\nGrupo modificado: " + grupoModificado);
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al modificar grupo: " + e.getMessage());
        }

        // 5. Eliminar un grupo
        // Se elimina el grupo con id = 1
        try {
            controlGrupo.eliminarGrupo(2);
            System.out.println("\nGrupo eliminado con id = 2");
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al eliminar grupo: " + e.getMessage());
        }


        //Test Matricula
        // 1. Insertar una nueva matrícula
        Matricula nuevaMatricula = new Matricula("S001", 1, null);
        try {
            controlMatricula.insertarMatricula(nuevaMatricula);
            System.out.println("Matrícula insertada: " + nuevaMatricula);
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al insertar matrícula: " + e.getMessage());
        }

        // 2. Listar todas las matrículas
        try {
            Collection<Matricula> listaMatriculas = controlMatricula.listarMatricula();
            System.out.println("\nListado de matrículas:");
            for (Matricula m : listaMatriculas) {
                System.out.println(m);
            }
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al listar matrículas: " + e.getMessage());
        }

        // 3. Buscar una matrícula por id
        try {
            Matricula matriculaEncontrada = controlMatricula.buscarMatricula(1);
            if (matriculaEncontrada != null) {
                System.out.println("\nMatrícula encontrada: " + matriculaEncontrada);
            } else {
                System.out.println("\nNo se encontró matrícula con id 1");
            }
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al buscar matrícula: " + e.getMessage());
        }

        // 4. Modificar una matrícula
        Matricula matriculaModificada = new Matricula(1, "S002", 1, 15.5f);
        try {
            controlMatricula.modificarMatricula(matriculaModificada);
            System.out.println("\nMatrícula modificada: " + matriculaModificada);
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al modificar matrícula: " + e.getMessage());
        }

        // 5. Eliminar una matrícula
        try {
            controlMatricula.eliminarMatricula(2);
            System.out.println("\nMatrícula eliminada con id = 2");
        } catch (GlobalException | NoDataException e) {
            System.err.println("Error al eliminar matrícula: " + e.getMessage());
        }


    }
}





