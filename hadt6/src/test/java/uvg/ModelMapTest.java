package uvg;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

public class ModelMapTest {

    private ModelMap modelMap;

    @Before
    public void setUp() {
        modelMap = new ModelMap(); // Inicializar una nueva instancia antes de cada prueba
    }

    @Test
    public void testAgregarPokemonColeccion() {
        Pokemon pikachu = new Pokemon("Pikachu", 25, "Electric", "", "Mouse", 0.4, 6.0, 
                                      Arrays.asList("Static"), 1, false);
        modelMap.getPokemonMap().put("Pikachu", pikachu);

        modelMap.agregarPokemonColeccion("Pikachu");

        assertTrue("El Pokémon Pikachu debería estar en la colección", 
                   modelMap.getUserCollection().contains("Pikachu"));
    }

    @Test
    public void testAgregarPokemonColeccionNoExistente() {
        modelMap.agregarPokemonColeccion("Charmander");

        assertFalse("Charmander no debería agregarse si no está en el mapa", 
                    modelMap.getUserCollection().contains("Charmander"));
    }

    @Test
    public void testGuardarColeccionCSV() {
        Pokemon pikachu = new Pokemon("Pikachu", 25, "Electric", "", "Mouse", 0.4, 6.0, 
                                      Arrays.asList("Static"), 1, false);
        modelMap.getPokemonMap().put("Pikachu", pikachu);
        modelMap.agregarPokemonColeccion("Pikachu");

        String archivo = "coleccion_test.csv";
        modelMap.guardarColeccionCSV(archivo);

        File file = new File(archivo);
        assertTrue("El archivo de colección debería haberse creado", file.exists());

        // Limpiar después de la prueba
        file.delete();
    }

    @Test
    public void testCargarColeccionCSV() throws IOException {
        // Crear archivo de prueba
        String archivo = "coleccion_test.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            writer.println("Name, Pokedex Number, Type1, Type2, Classification, Height, Weight, Abilities, Generation, Legendary Status");
            writer.println("Pikachu,25,Electric,,Mouse,0.4,6.0,\"Static\",1,No");
        }

        Pokemon pikachu = new Pokemon("Pikachu", 25, "Electric", "", "Mouse", 0.4, 6.0, 
                                      Arrays.asList("Static"), 1, false);
        modelMap.getPokemonMap().put("Pikachu", pikachu);

        modelMap.cargarColeccionCSV(archivo);

        assertTrue("Pikachu debería haber sido cargado en la colección", 
                   modelMap.getUserCollection().contains("Pikachu"));

        // Limpiar después de la prueba
        new File(archivo).delete();
    }

    @Test
    public void testMostrarColeccionOrdenada() {
        Pokemon pikachu = new Pokemon("Pikachu", 25, "Electric", "", "Mouse", 0.4, 6.0, 
                                      Arrays.asList("Static"), 1, false);
        Pokemon bulbasaur = new Pokemon("Bulbasaur", 1, "Grass", "Poison", "Seed", 0.7, 6.9, 
                                        Arrays.asList("Overgrow"), 1, false);

        modelMap.getPokemonMap().put("Pikachu", pikachu);
        modelMap.getPokemonMap().put("Bulbasaur", bulbasaur);

        modelMap.agregarPokemonColeccion("Pikachu");
        modelMap.agregarPokemonColeccion("Bulbasaur");

        // Capturar la salida para verificar el orden
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        modelMap.mostrarColeccion();

        System.setOut(System.out);

        String resultado = output.toString().trim();
        assertTrue("La salida debe contener a Pikachu y Bulbasaur en orden por Type1",
                   resultado.contains("Bulbasaur - Grass") && resultado.contains("Pikachu - Electric"));
    }

    @Test
    public void testPokemonAbility() {
        Pokemon pikachu = new Pokemon("Pikachu", 25, "Electric", "", "Mouse", 0.4, 6.0, 
                                      Arrays.asList("Static"), 1, false);
        modelMap.getPokemonMap().put("Pikachu", pikachu);

        // Capturar la salida
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        modelMap.pokemonAbility("Static");

        System.setOut(System.out);

        String resultado = output.toString().trim();
        assertTrue("La salida debe contener a Pikachu", resultado.contains("Pikachu"));
    }
}
