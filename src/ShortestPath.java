import java.io.*;
import java.util.*;

public class ShortestPath {
    public static void main(String[] args) throws IOException {
        String start = "Jack";
        String end = "END";

        var g = new HashMap<String, List<String>>();
        try (var br = new BufferedReader(new FileReader("graph-F25.txt"))) {
            br.readLine();
            for (String l; (l = br.readLine()) != null;) {
                var v = l.trim().split("\\s+");
                if (v.length < 2) continue;
                g.computeIfAbsent(v[0], k -> new ArrayList<>()).add(v[1]);
                g.computeIfAbsent(v[1], k -> new ArrayList<>()).add(v[1]);
            }
        }

        var q = new ArrayDeque<String>();
        var p = new HashMap<String, String>();
        q.add(start);
        p.put(start, null);

        while (!q.isEmpty() && !p.containsKey(end)) {
            String c = q.poll();
            for (String n: g.getOrDefault(c, List.of())) {
                if (!p.containsKey(n)) {
                    p.put(n, c);
                    q.add(n);
                }
            }
        }

        if (!p.containsKey(end)) {
            System.out.println("No path.txt found.");
            return;
        }

        var path = new ArrayList<String>();
        for (String at = end; at != null; at = p.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        for (String v : path) {
            System.out.println(v);
        }
    }
}
