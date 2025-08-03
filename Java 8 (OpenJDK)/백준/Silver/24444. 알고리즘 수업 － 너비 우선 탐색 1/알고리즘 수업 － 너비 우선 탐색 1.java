import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
	static List<LinkedList<Integer>> graph = new ArrayList<>();
	static int[] visited;
	static int visit = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nmr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = nmr[0];
		int M = nmr[1];
		int r = nmr[2];

		graph = new ArrayList<>(N+1);
		for(int i = 0 ; i <= N;i++ ){
			graph.add(new LinkedList<>());
		}
		visited = new int[N+1];
		for(int i =0 ; i< M;i++){
			int[] nodes = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			graph.get(nodes[0]).add(nodes[1]);
			graph.get(nodes[1]).add(nodes[0]);
		}
		for(int i = 1 ; i <=N;i++){
			Collections.sort(graph.get(i));
		}
		bfs(r);
		for (int i = 1; i < visited.length; i++){
			System.out.println(visited[i]);
		}


	}
	static void bfs(int start){
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		visited[start] = ++visit;
		while (!queue.isEmpty()){
			Integer current = queue.poll();
			for (int v : graph.get(current)){
				if(visited[v] == 0){
					visited[v] = ++visit;
					queue.add(v);
				}
			}
		}
	}
}