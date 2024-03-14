import java.util.HashMap;
import java.util.Map;

public class HashMapFactory implements MapFactory {
    public Map<String, Card> createMap(){
        return new HashMap<>();
    }
}