package uvg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class ModelMap {
    private Map<String, Pokemon> pokemonMap;
    private Set<String> userCollection;

    /**
     * Constructor
     * @param pokemonMap
     */
    public ModelMap(Map<String, Pokemon> pokemonMap) {
        this.pokemonMap = pokemonMap;
        this.userCollection = new HashSet<>();  
    }

    /**
     * Constructor vacio para pruebas unitarias
     */
    public ModelMap() {
        this.pokemonMap = new HashMap<>();
        this.userCollection = new HashSet<>();
    }
    
    /**
     * Cargar los datos de los Pokémon desde un archivo CSV
     * @param filename
     * @throws FileNotFoundException
     */
    public void cargarPokemones(String filename) throws FileNotFoundException {
  
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); // Saltar la primera línea
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // Expresión regular para manejar comas dentro de comillas

                if (data.length < 10) {
                    System.out.println("Error: línea inválida en el archivo CSV.");
                    continue;
                }

                try {
                    String name = data[0].trim();
                    int pokedexNumber = Integer.parseInt(data[1].trim());
                    String type1 = data[2].trim();
                    String type2 = data.length > 3 ? data[3].trim() : ""; 
                    String classification = data[4].trim();
                    double height = Double.parseDouble(data[5].trim());
                    double weight = Double.parseDouble(data[6].trim());

                    List<String> abilities = Arrays.asList(data[7].replace("\"", "").trim().split(", ")); // Quitar comillas y dividir correctamente
                    
                    int generation = Integer.parseInt(data[8].trim());
                    boolean isLegendary = data[9].trim().equalsIgnoreCase("Yes");

                    Pokemon pokemon = new Pokemon(name, pokedexNumber, type1, type2, classification, 
                                                height, weight, abilities, generation, isLegendary);
                    pokemonMap.put(pokemon.getName(), pokemon);
                } catch (NumberFormatException e) {
                    System.out.println("Error: formato inválido en el archivo CSV. Línea ignorada.");
                }
            }
            System.out.println("Basa de datos cargada desde: " + filename);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    /**
     * Agregar un Pokémon a la colección del usuario
     * @param name
     */
    public void agregarPokemonColeccion(String name) {
        if (pokemonMap.containsKey(name)) {
            if (userCollection.contains(name)) {
                System.out.println("El Pokémon ya está en tu colección.");
            } else {
                userCollection.add(name);
                System.out.println("Pokémon añadido a tu colección.");
            }
        } else {
            System.out.println("Error: Pokémon no encontrado.");
        }
    }

     public void guardarColeccionCSV(String archivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            writer.println("Name, Pokedex Number, Type1, Type2, Classification, Height, Weight, Abilities, Generation, Legendary Status");
            for (String nombre : userCollection) {
                Pokemon p = pokemonMap.get(nombre);
                writer.printf("%s,%d,%s,%s,%s,%.2f,%.2f,\"%s\",%d,%s\n",
                        p.getName(), p.getPokedexNumber(), p.getType1(), p.getType2(),
                        p.getClassification(), p.getHeight(), p.getWeight(),
                        String.join(";", p.getAbilities()), p.getGeneration(),
                        p.isLegendary() ? "Sí" : "No");
            }
            System.out.println("Colección guardada en " + archivo);
        } catch (IOException e) {
            System.out.println("Error al guardar la colección: " + e.getMessage());
        }
    }

    public void cargarColeccionCSV(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String linea = reader.readLine(); // Saltar la cabecera
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length > 0) {
                    String nombre = datos[0].trim();
                    if (pokemonMap.containsKey(nombre)) {
                        userCollection.add(nombre);
                    }
                }
            }
            System.out.println("Colección cargada desde: " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo de colección, se iniciará una nueva colección.");
        } catch (IOException e) {
            System.out.println("Error al leer la colección: " + e.getMessage());
        }
    }

    /**
     * Mostrar los datos de un Pokémon
     * @param name
     */
    public void datosPokemon(String name) {
        if (pokemonMap.containsKey(name)) {
            System.out.println(pokemonMap.get(name));
        } else {
            System.out.println("Error: Pokémon no encontrado.");
        }
    }

    /**
     * Mostrar la colección del usuario ordenada su tipo 1
     */
    public void mostrarColeccion() {
        List<Pokemon> userPokemon = new ArrayList<>();
        for (String name : userCollection) {
            userPokemon.add(pokemonMap.get(name));
        }
        userPokemon.sort(Comparator.comparing(Pokemon::getType1));
        for (Pokemon pokemon : userPokemon) {
            System.out.println(pokemon.getName() + " - " + pokemon.getType1());
        }
    }

    /**
     * Mostrar todos los Pokémon ordenados su tipo 1
     */
    public void pokemonsType1() {
        List<Pokemon> allPokemon = new ArrayList<>(pokemonMap.values());
        allPokemon.sort(Comparator.comparing(Pokemon::getType1));
        for (Pokemon pokemon : allPokemon) {
            System.out.println(pokemon.getName() + " - " + pokemon.getType1());
        }
    }

    /**
     * Mostrar todos los Pokémon que tienen una habilidad específica
     * @param ability
     */
    public void pokemonAbility(String ability) {
        for (Pokemon pokemon : pokemonMap.values()) {
            if (pokemon.getAbilities().contains(ability)) {
                System.out.println(pokemon.getName());
            }
        }
    }

    /**
     * Getters
     * @return
     */
    public Map<String, Pokemon> getPokemonMap() {
        return pokemonMap;
    }
}
    