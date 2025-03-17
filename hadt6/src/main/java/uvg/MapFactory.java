package uvg;

import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.LinkedHashMap;

/**
 * Clase MapFactory
 */
public class MapFactory {
    public static Map<String, Pokemon> crearMap(int type) {
        switch (type) {
            case 1:
                return new HashMap<>();
            case 2:
                return new TreeMap<>();
            case 3:
                return new LinkedHashMap<>();
            default:
                throw new IllegalArgumentException("Tipo de mapa inválido");
        }
    }
}