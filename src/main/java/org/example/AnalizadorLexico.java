package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class AnalizadorLexico {
    // Tabla de símbolos para almacenar identificadores y sus tipos
    private static final Map<String, String> tablaDeSimbolos = new HashMap<>();
    // Lista de errores encontrados durante el análisis léxico
    private static final List<String> tablaDeErrores = new ArrayList<>();
    // Patrones de expresiones regulares para reconocer diferentes tipos de tokens
    private static final Pattern PATRON_IDENTIFICADOR = Pattern.compile("[a-zA-Z][a-zA-Z0-9]{0,14}");
    private static final Pattern PATRON_CONSTANTE_ENTERA = Pattern.compile("0|[1-9][0-9]*|100");
    private static final Pattern PATRON_OPERADOR = Pattern.compile("[+\\-*/:=]");
    private static final Pattern PATRON_OPERADOR_RELACIONAL = Pattern.compile(">|<|>=|<=|==|<>|\\{|\\}|\\[|\\]|\\(|\\)|,|;|\\.\\.");
    private static final Pattern PATRON_CADENA = Pattern.compile("\"[^\"]*\"");


    // Método para analizar el código fuente y reconocer los diferentes tipos de tokens
    public void analizar(String codigo) {
        String[] tokens = codigo.split("\\s+");

        for (String token : tokens) {
            if (esPalabraReservada(token)) {
                System.out.println("Palabra reservada: " + token);
            } else if (esIdentificador(token)) {
                System.out.println("Identificador: " + token);
                // Agregar el identificador a la tabla de símbolos si no está presente
                if (!tablaDeSimbolos.containsKey(token)) {
                    tablaDeSimbolos.put(token, "Tipo_desconocido");
                }
            } else if (esConstanteEntera(token)) {
                System.out.println("Constante entera: " + token);
            } else if (esOperador(token)) {
                System.out.println("Operador: " + token);
            } else if (esOperadorRelacional(token)) {
                System.out.println("Operador relacional: " + token);
            } else if (esCadena(token)) {
                System.out.println("Cadena de caracteres: " + token);
            } else {
                // Agregar a la tabla de errores
                tablaDeErrores.add("Error: Token no reconocido - " + token);
            }
        }

        // Imprimir la tabla de símbolos y la tabla de errores
        System.out.println("Tabla de símbolos:");
        for (Map.Entry<String, String> entrada : tablaDeSimbolos.entrySet()) {
            System.out.println("Identificador: " + entrada.getKey() + ", Tipo: " + entrada.getValue());
        }

        System.out.println("Tabla de errores:");
        for (String error : tablaDeErrores) {
            System.out.println(error);
        }
    }

    // Método para verificar si un token es una palabra reservada
    private boolean esPalabraReservada(String token) {
        // Lista de palabras reservadas
        String[] palabrasReservadas = {"if", "else", "for", "print", "int"};
        for (String palabraReservada : palabrasReservadas) {
            if (token.equals(palabraReservada)) {
                return true;
            }
        }
        return false;
    }

    // Método para verificar si un token es un identificador válido
    private boolean esIdentificador(String token) {
        Matcher matcher = PATRON_IDENTIFICADOR.matcher(token);
        return matcher.matches();
    }

    // Método para verificar si un token es una constante entera válida
    private boolean esConstanteEntera(String token) {
        Matcher matcher = PATRON_CONSTANTE_ENTERA.matcher(token);
        return matcher.matches();
    }

    // Método para verificar si un token es un operador válido
    private boolean esOperador(String token) {
        Matcher matcher = PATRON_OPERADOR.matcher(token);
        return matcher.matches();
    }

    // Método para verificar si un token es un operador relacional válido
    private boolean esOperadorRelacional(String token) {
        Matcher matcher = PATRON_OPERADOR_RELACIONAL.matcher(token);
        return matcher.matches();
    }

    // Método para verificar si un token es una cadena de caracteres válida
    private boolean esCadena(String token) {
        Matcher matcher = PATRON_CADENA.matcher(token);
        return matcher.matches();
    }
}

