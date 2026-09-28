package com.estudiante;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Tarea tarea1 = new Tarea(1L, "Comprar alimentos", "Comprar productos para la semana", "ALTA", false);
        Tarea tarea2 = new Tarea(2L, "Realizar ejercicios", "Rutina de cardio y pesas", "MEDIA", true);
        Tarea tarea3 = new Tarea(3L, "Estudiar Programación II", "Repasar conceptos de Maven y REST", "ALTA", false);


        ArrayList<Tarea> listaTareas = new ArrayList<>();
        listaTareas.add(tarea1);
        listaTareas.add(tarea2);
        listaTareas.add(tarea3);


        int pendientes = 0;
        int completadas = 0;

        System.out.println("===== LISTADO DE TAREAS =====\n");


        for (Tarea tarea : listaTareas) {
            System.out.println(tarea.obtenerEstadoFormateado());

            if (tarea.isCompletada()) {
                completadas++;
            } else {
                pendientes++;
            }
        }

        System.out.println("\nTareas pendientes: " + pendientes);
        System.out.println("Tareas completadas: " + completadas);
    }
}