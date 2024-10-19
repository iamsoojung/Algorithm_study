import java.util.*;
import java.io.*;

class Solution {
    
    static ArrayList<String> routes;
    static boolean[] visited;
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        
        routes = new ArrayList<>();
        visited = new boolean[tickets.length];
        
        dfs("ICN", "ICN", tickets, 0);
        
        Collections.sort(routes);
        answer = routes.get(0).split(" ");
        
        return answer;
    }
    
    static void dfs(String start, String route, String[][] tickets, int depth) {
        if (depth == tickets.length) {
            routes.add(route);
            return;
        }
        
        for (int i=0; i<tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(start)) {
                visited[i] = true;
                dfs(tickets[i][1], route + " " + tickets[i][1], tickets, depth+1);                
                visited[i] = false;
            }
        }
        
        
        
    }
}