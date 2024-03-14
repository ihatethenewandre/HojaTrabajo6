import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione el tipo de Mapa que desea usar:");
        System.out.println("1) HashMap");
        System.out.println("2) TreeMap");
        System.out.println("3) LinkedHashMap");
        String mapType = scanner.nextLine();

        MapFactory mapFactory;
        if (mapType.equals("1")) {
            mapFactory = new HashMapFactory();
        } else if (mapType.equals("2")) {
            mapFactory = new TreeMapFactory();
        } else if (mapType.equals("3")) {
            mapFactory = new LinkedHashMapFactory();
        } else {
            System.out.println("Tipo de mapa no válido. Usando HashMap por defecto.");
            mapFactory = new HashMapFactory();
        }

        CardCollection cardCollection = new CardCollection(mapFactory);
        cardCollection.cargarCardsFromFile("ruta_al_archivo_de_cartas.txt");

        while (true) {
            System.out.println("Seleccione una operación:");
            System.out.println("1) Agregar una carta a la colección del usuario.");
            System.out.println("2) Mostrar el tipo de una carta específica.");
            System.out.println("3) Mostrar el nombre, tipo y cantidad de cada carta que el usuario tiene en su colección.");
            System.out.println("4) Mostrar el nombre, tipo y cantidad de cada carta que el usuario tiene en su colección, ordenadas por tipo.");
            System.out.println("5) Mostrar el nombre y tipo de todas las cartas existentes.");
            System.out.println("6) Mostrar el nombre y tipo de todas las cartas existentes, ordenadas por tipo.");
            System.out.println("7) Salir.");
            String operation = scanner.nextLine();

            if (operation.equals("1")) {
                System.out.println("Ingrese el nombre de la carta que desea agregar: ");
                String cardName = scanner.nextLine();
                cardCollection.agregarUserCollection(cardName);
            } else if (operation.equals("2")) {
                System.out.println("Ingrese el nombre de la carta: ");
                String cardName = scanner.nextLine();
                System.out.println(cardCollection.mostrarCardType(cardName));
            } else if (operation.equals("3")) {
                System.out.println(cardCollection.mostrarUserCollection());
            } else if (operation.equals("4")) {
                Map<String, Integer> userCollection = cardCollection.mostrarUserCollection();
                Map<String, List<Map.Entry<String, Integer>>> sortedByType = userCollection.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .collect(Collectors.groupingBy(e -> cardCollection.mostrarCardType(e.getKey()),
                                LinkedHashMap::new, Collectors.toList()));
                System.out.println(sortedByType);
            } else if (operation.equals("5")) {
                Map<String, Card> allCards = cardCollection.showAllCards();
                allCards.forEach((name, card) -> System.out.println("Nombre: " + name + ", Tipo: " + card.getType()));
            } else if (operation.equals("6")) {
                long startTime = System.nanoTime();
                Map<String, Card> allCards = cardCollection.showAllCards();
                Map<String, List<Map.Entry<String, Card>>> sortedByType = allCards.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .collect(Collectors.groupingBy(e -> e.getValue().getType(),
                                LinkedHashMap::new, Collectors.toList()));
                System.out.println(sortedByType);
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);  // Duración en nanosegundos
                System.out.println("Tiempo de ejecución: " + duration + " nanosegundos");
            } else if (operation.equals("7")) {
                break;
            } else {
                System.out.println("Operación no válida. Por favor, intente de nuevo.");
            }
        }
        scanner.close();
    }
}