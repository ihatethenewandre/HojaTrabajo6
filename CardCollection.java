import java.util.*;
import java.io.*;
public class CardCollection {
    private Map<String, Card> cardMap;
    private Map<String, Integer> userCollection;

    public CardCollection(MapFactory mapFactory){
        this.cardMap = mapFactory.createMap();
        this.userCollection = new HashMap<>();
    }

    public void addCard(String cardName, String cardType){
        this.cardMap.put(cardName, new Card(cardName, cardType));
    }

    public void agregarUserCollection(String cardName){
        if(this.cardMap.containsKey(cardName)){
            this.userCollection.put(cardName, this.userCollection.getOrDefault(cardName, 0)+1);
        } else {
            System.out.println("ERROR: la carta no se encuentra entre las cartas disponibles");
        }
    }

    public String mostrarCardType(String cardName){
        if (this.cardMap.containsKey(cardName)){
            return this.cardMap.get(cardName).getType();
        } else {
            return "ERROR: la carta no se encuentra entre las cartas disponibles";
        }
    }

    public Map<String, Integer> mostrarUserCollection(){
        return this.userCollection;
    }

    public Map<String, Card> showAllCards(){
        return this.cardMap;
    }

    public void cargarCardsFromFile(String filepath){
        File file = new File(filepath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                String cardName = parts[0];
                String cardType = parts[1];
                addCard(cardName, cardType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}