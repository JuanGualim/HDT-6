package uvg;

import java.util.List;

/**
 * Representa un Pokémon con diversas características como nombre, tipo, clasificación,
 * altura, peso, generación y habilidades.
 */
public class Pokemon {
    private String name, type1, type2, classification;
    private int pokedexNumber, generation;
    private double height, weight;
    private boolean isLegendary;
    private List<String> abilities;

    /**
     * Constructor de la clase Pokemon.
     * 
     * @param name Nombre del Pokémon
     * @param pokedexNumber Número en la Pokédex
     * @param type1 Primer tipo del Pokémon
     * @param type2 Segundo tipo del Pokémon (puede ser null si solo tiene un tipo)
     * @param classification Clasificación del Pokémon
     * @param height Altura del Pokémon en metros
     * @param weight Peso del Pokémon en kilogramos
     * @param abilities Lista de habilidades del Pokémon
     * @param generation Generación a la que pertenece el Pokémon
     * @param isLegendary Indica si el Pokémon es legendario o no
     */
    public Pokemon(String name, int pokedexNumber, String type1, String type2, String classification, 
                   double height, double weight, List<String> abilities, int generation, boolean isLegendary) {
        this.name = name;
        this.pokedexNumber = pokedexNumber;
        this.type1 = type1;
        this.type2 = type2;
        this.classification = classification;
        this.height = height;
        this.weight = weight;
        this.abilities = abilities;
        this.generation = generation;
        this.isLegendary = isLegendary;
    }

    /**
     * @return Nombre del Pokémon
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Establece el nombre del Pokémon
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Primer tipo del Pokémon
     */
    public String getType1() {
        return type1;
    }

    /**
     * @param type1 Establece el primer tipo del Pokémon
     */
    public void setType1(String type1) {
        this.type1 = type1;
    }

    /**
     * @return Segundo tipo del Pokémon (puede ser null)
     */
    public String getType2() {
        return type2;
    }

    /**
     * @param type2 Establece el segundo tipo del Pokémon
     */
    public void setType2(String type2) {
        this.type2 = type2;
    }

    /**
     * @return Clasificación del Pokémon
     */
    public String getClassification() {
        return classification;
    }

    /**
     * @param classification Establece la clasificación del Pokémon
     */
    public void setClassification(String classification) {
        this.classification = classification;
    }

    /**
     * @return Número en la Pokédex
     */
    public int getPokedexNumber() {
        return pokedexNumber;
    }

    /**
     * @param pokedexNumber Establece el número en la Pokédex
     */
    public void setPokedexNumber(int pokedexNumber) {
        this.pokedexNumber = pokedexNumber;
    }

    /**
     * @return Generación del Pokémon
     */
    public int getGeneration() {
        return generation;
    }

    /**
     * @param generation Establece la generación del Pokémon
     */
    public void setGeneration(int generation) {
        this.generation = generation;
    }

    /**
     * @return Altura del Pokémon en metros
     */
    public double getHeight() {
        return height;
    }

    /**
     * @param height Establece la altura del Pokémon
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * @return Peso del Pokémon en kilogramos
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @param weight Establece el peso del Pokémon
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * @return Verdadero si el Pokémon es legendario, falso en caso contrario
     */
    public boolean isLegendary() {
        return isLegendary;
    }

    /**
     * @param isLegendary Define si el Pokémon es legendario o no
     */
    public void setLegendary(boolean isLegendary) {
        this.isLegendary = isLegendary;
    }

    /**
     * @return Lista de habilidades del Pokémon
     */
    public List<String> getAbilities() {
        return abilities;
    }

    /**
     * @param abilities Establece la lista de habilidades del Pokémon
     */
    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    /**
     * @return Representación en cadena del objeto Pokémon
     */
    @Override
    public String toString() {
        String legendry;
        if(isLegendary) legendry = "Si";
        else legendry = "No";
        return "\nPokemon: \nname = " + name + "\npokedexNumber = " + pokedexNumber +  
            "\ntype1 = " + type1 + "\ntype2 = " + type2 + "\nclassification = " + classification
            + "\nheight = " + height + "\nweight = " + weight + "\nabilities = " + abilities 
            + "\ngeneration = " + generation  + "\nLegendary = " + legendry ;
    }

}
