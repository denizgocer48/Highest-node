import java.util.*;
import java.util.Queue;

public class Graph {
	private ArrayList<String> vertices; // to keep vertex names
	private ArrayList<Vertex> vertices1;
	private int[][] adjacency; // to keep edges
	private int size;
	private HashMap<String, Edge> edges;
	private ArrayList<String> betw;
	private ArrayList<String> betw2;
	private ArrayList<String> betw3;
	private ArrayList<String> betw4;
	private int r = 1;
	int a = 1;
	private ArrayList<String> between;
	private ArrayList<String> between1;
	private int t =0;

	public Graph(int size) {
		vertices = new ArrayList<String>();
		vertices1 = new ArrayList<Vertex>();
		adjacency = new int[size][size];
		this.size = size;
		this.edges = new HashMap<>();
		betw = new ArrayList<String>();
		betw2 = new ArrayList<String>();
		betw3 = new ArrayList<String>();
		betw4 = new ArrayList<String>();
		between = new ArrayList<String>();
		between1 = new ArrayList<String>();
	}

	public void addEdge(String source, String destination) {

		if (!vertices.contains(source))
			vertices.add(source);
		if (!vertices.contains(destination))
			vertices.add(destination);

		Vertex source_v, destination_v;
		source_v = new Vertex(source);
		destination_v = new Vertex(destination);
		int source_index = vertices.indexOf(source);
		int destination_index = vertices.indexOf(destination);
		adjacency[source_index][destination_index] = 1;
		adjacency[destination_index][source_index] = 1;
		Edge edge = new Edge(source_v, destination_v);
		source_v.addEdge(edge);
		destination_v.addEdge(edge);
		edges.put(source + "-" + destination, edge);

		if (!betw.contains(source)) {
			betw.add(source);
			betw3.add(source);
			r++;
		}
		if (!betw2.contains(destination)) {
			betw2.add(destination);
			betw4.add(destination);
			a++;
		}
		

	}

	public void getvertex() {
		for (int i = 0; i < vertices.size(); i++) {
			Vertex v = new Vertex(vertices.get(i));
			vertices1.add(v);
		}
	}

	public int size() {
		return this.size;
	}

	public int[][] getAdjacency() {
		return adjacency;
	}

	public ArrayList<String> getVertices() {
		return vertices;
	}

	public void print() {
		for (String v : vertices) {
			System.out.print("\t(" + v + ")");
		}
		System.out.println();
		for (int i = 0; i < vertices.size(); i++) {
			System.out.print("(" + vertices.get(i) + ")\t");
			for (int j = 0; j < adjacency.length; j++) {
				System.out.print(adjacency[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("Edges");
		int edge_count = 0;
		int total_weight = 0;
		for (int i = 0; i < vertices.size(); i++) {
			for (int j = 0; j < vertices.size(); j++) {
				if (adjacency[i][j] > 0) {
					System.out.println(vertices.get(i) + "-" + vertices.get(j) + ":" + adjacency[i][j]);
					edge_count++;
					total_weight += adjacency[i][j];
				}
			}
		}
		System.out.println("Total " + edge_count + " edges.");
	}

	public int unvisitedNeighbor(int index, int[] visited) {
		for (int i = 0; i < adjacency.length; i++) {
			if (adjacency[index][i] == 1 && visited[i] == 0)
				return i;
		}
		return -1;
	}

	public void Betweenness() {
		getvertex();
		for (int i = 0; i < betw.size(); i++) {
			for (int j = 0; j < betw2.size(); j++) {
				if (betw2.get(j) != null && betw.get(i) != null) {
					if (!betw.get(i).equals(betw2.get(j))) {
						getShortestPath(betw.get(i), betw2.get(j));
					}
				}

			}
		}
		for (int i = 0; i < betw2.size(); i++) {
			for (int j = 0; j < betw4.size(); j++) {
				if (betw2.get(j) != null && betw4.get(i) != null) {
					if (!betw2.get(i).equals(betw4.get(j))) {
						getShortestPath(betw2.get(i), betw4.get(j));
					}
				}

			}
		}
		int g = 0;
		int a = -1;
		for (int i = 0; i < vertices1.size(); i++) {
			if (vertices1.get(i).getBetweenness() > a) {
				a = vertices1.get(i).getBetweenness();
				g = i;
			}

		}
		System.out.println(a + " " +vertices.get(g));
	}

	public void getShortestPath(String originVertex, String endVertex) {
		boolean done = false;
		Queue<Integer> queue = new LinkedList<>();
		int root = Integer.parseInt(originVertex);
		queue.add(root);
		int[] visited = new int[size];
		visited[root] = 1;
		int[] previous = new int[size];
		Arrays.fill(previous, -1);
		while (!done && !queue.isEmpty()) {
			Integer frontVertex = queue.poll();
			int neighbornode;
			while (!done && (neighbornode = unvisitedNeighbor(frontVertex, visited)) != -1) {
				queue.add(neighbornode);
				visited[neighbornode] = 1;
				previous[neighbornode] = frontVertex;
				if (Integer.toString(neighbornode).equals(endVertex)) {
					done = true;
				}
			}
		}
		int node = Integer.parseInt(endVertex);
		List<String> path = new ArrayList<>();	
		while (node != -1) {
			path.add(vertices.get(node));
			
			t++;
			for (int i = 0; i < vertices.size(); i++) {
				if (vertices.get(i).equals(String.valueOf(node))) {
					vertices1.get(i).setBetweenness(vertices1.get(i).getBetweenness() + 1);
				}
			}
			node = previous[node];
		}
		for (int i = 0; i <vertices.size(); i++) {
			if (vertices.get(i).equals(originVertex)) {
				vertices1.get(i).setRange(vertices1.get(i).getRange()+t);
			}
		}
		t=0;
	}	
	public void closennes() {
		int g = 0;
		int a = -1;
		for (int i = 0; i < vertices1.size(); i++) {
			if (vertices1.get(i).getRange() > a) {
				a = vertices1.get(i).getRange();
				g = i;
			}

		}
		System.out.println((double)1/a + " " +vertices.get(g));
	}
}