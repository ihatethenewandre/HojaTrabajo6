import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapFactory implements MapFactory{
    public Map<String, Card> createMap(){
        return new LinkedHashMap<>();
    }
}