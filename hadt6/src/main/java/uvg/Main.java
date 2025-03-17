package uvg;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 * Clase Main
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione el tipo de mapa:");
        System.out.println("1. HashMap");
        System.out.println("2. TreeMap");
        System.out.println("3. LinkedHashMap");

        int tipoMapa = 0;
        while (true) {
            try {
                System.out.print("Ingrese una opción: ");
                tipoMapa = scanner.nextInt();
                scanner.nextLine();
                if (tipoMapa < 1 || tipoMapa > 3) {
                    System.out.println("Opción inválida. Intente nuevamente.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine(); // Limpiar el buffer
            }
        }

        Map<String, Pokemon> mapaPokemon = MapFactory.crearMap(tipoMapa);
        ModelMap modelo = new ModelMap(mapaPokemon);

        try {
            modelo.cargarPokemones("pokemon_data.csv");
            modelo.cargarColeccionCSV("coleccion_personal.csv");
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: Archivo de datos no encontrado.");
            scanner.close();
            return;
        }

        int opcion = 0;
        while (opcion != 6) { 
            System.out.println("\nOperaciones disponibles:");
            System.out.println("1. Agregar un Pokémon a la colección");
            System.out.println("2. Mostrar datos de un Pokémon");
            System.out.println("3. Mostrar colección ordenada por tipo"); 
            System.out.println("4. Mostrar todos los Pokémon ordenados por tipo");
            System.out.println("5. Buscar Pokémon por habilidad");
            System.out.println("6. Salir");
            
            try {
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del Pokémon: ");
                    String nombre = scanner.nextLine();
                    modelo.agregarPokemonColeccion(nombre);
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del Pokémon: ");
                    nombre = scanner.nextLine();
                    modelo.datosPokemon(nombre);
                    break;
                case 3:
                    modelo.mostrarColeccion();
                    break;
                case 4:
                    modelo.pokemonsType1();
                    break;
                case 5:
                    System.out.print("Ingrese la habilidad: ");
                    String habilidad = scanner.nextLine();
                    modelo.pokemonAbility(habilidad);
                    break;
                case 6:
                    System.out.println("Guardando colección y saliendo...\n");
                    modelo.guardarColeccionCSV("coleccion_personal.csv");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }        
}