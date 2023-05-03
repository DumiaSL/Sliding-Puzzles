import java.util.*;

class Graph implements Map<Integer, List<Integer>> {
    private final Map<Integer, List<Integer>> map = new HashMap<>();

    public void addVertex(int vertex) {
        if (!map.containsKey(vertex)) {
            map.put(vertex, new ArrayList<>());
        }
    }

    public void addEdge(int start, int end) {
        addVertex(start);
        addVertex(end);
        map.get(start).add(end);
    }

    public void removeVertex(int vertex) {
        map.remove(vertex);
    }

    public void removeEdge(int start, int end) {
        List<Integer> neighbors = map.get(start);
        if (neighbors != null) {
            neighbors.remove((Integer) end);
        }
    }

    // Methods from the Map interface
    public int size() { return map.size(); }
    public boolean isEmpty() { return map.isEmpty(); }
    public boolean containsKey(Object key) { return map.containsKey(key); }
    public boolean containsValue(Object value) { return map.containsValue(value); }
    public List<Integer> get(Object key) { return map.get(key); }
    public List<Integer> put(Integer key, List<Integer> value) { return map.put(key, value); }
    public List<Integer> remove(Object key) { return map.remove(key); }
    public void putAll(Map<? extends Integer, ? extends List<Integer>> m) { map.putAll(m); }
    public void clear() { map.clear(); }
    public Set<Integer> keySet() { return map.keySet(); }
    public Collection<List<Integer>> values() { return map.values(); }
    public Set<Map.Entry<Integer, List<Integer>>> entrySet() { return map.entrySet(); }
}
