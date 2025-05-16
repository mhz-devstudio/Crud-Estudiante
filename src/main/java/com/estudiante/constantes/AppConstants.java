package com.estudiante.constantes;

public final class AppConstants {
    // Constante para la redirección a estudiantes
    public static final String REDIRECT_ESTUDIANTES = "redirect:/estudiantes";
    
    // Otras constantes si las necesitas
    public static final String ESTUDIANTES_ENDPOINT = "/estudiantes";
    
    // Constructor privado para evitar instanciación
    private AppConstants() {
        throw new AssertionError("Clase de utilidad, no instanciar");
    }
}