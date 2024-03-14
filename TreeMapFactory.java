import java.util.Map;
import java.util.TreeMap;

public class TreeMapFactory implements MapFactory{
    public Map<String, Card> createMap(){
        return new TreeMap<>();
    }
}