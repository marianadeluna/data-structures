
public class Graph {
	private int[][] edges; // adjacency matrix
	private Vertex[] vertex;

	public Graph(int n) {
		edges = new int[n][n];
		vertex = new Vertex[n];
		for (int i = 0; i < vertex.length; i++) {
			vertex[i] = new Vertex("");
		} // end for
	} // end constructor

	public void setVertexName(int vertexNum, String name) {
		vertex[vertexNum].setName(name);
	} // end setVertexName

	public Vertex getName(int vertexNum) {
		return vertex[vertexNum];
	} // end getName

	// for undirected and unweighted
	public void addEdge(int v1, int v2) {
		edges[v1][v2] = 1;
		edges[v2][v1] = 1;
	} // end addEdge

	// add undirected with weight. Third parameter
	public void addEdgeWithWeight(int v1, int v2, int weight) {
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
	} // end addEdgeWithWeight

	// directed and unweighted edge
	public void addDiretedEdge(int v1, int v2) {
		edges[v1][v2] = 1;
	} // end addDirectedEdge

	// directed and weighted
	public void addDirectedEdgeWithWeight(int v1, int v2, int weight) {
		edges[v1][v2] = weight;
	} // end addDirectedEdgeWithWeight

	public boolean isEdge(int v1, int v2) {
		return edges[v1][v2] > 0;
	} // end boolean isEdge

	public void removeEdge(int v1, int v2) {
		// if the edge equals zero then there is no edge
		edges[v1][v2] = 0;
	} // end removeEdge

	public int getWeight(int v1, int v2) {
		return edges[v1][v2];
	} // end getWeight

	public void printWithWeight() {
		// prints the list of adjacent vertices along with the weight associated
		// to their edge
		System.out.println();
		System.out.println("------------------------------------------------");
		for (int j = 0; j < edges.length; j++) {
			System.out.print("[from] " + vertex[j] + ", [to]" + ": ");
			for (int i = 0; i < edges[j].length; i++) {
				if (edges[j][i] > 0)
					System.out.print(vertex[i] + ":" + edges[j][i] + ", ");
			} // end for
			System.out.println();
		} // end for
	} // end printWithWeight

	public void printWithoutWeight() {
		// prints the list of adjacent vertices
		System.out.println();
		System.out.println("------------------------------------------------");
		for (int j = 0; j < edges.length; j++) {
			System.out.print("[from] " + vertex[j] + ", [to]" + ": ");
			for (int i = 0; i < edges[j].length; i++) {
				if (edges[j][i] > 0)
					System.out.print(vertex[i] + ", ");
			} // end for
			System.out.println();
		} // end for
	} // end printWithOutWeight

	private void makeAllUnvisited() {
		for (int i = 0; i < edges.length; i++) {
			vertex[i].setUnvisited();
		}
	} // end makeAllUnvisited

	public String BFS(int startV) {
		// uses queue
		String output = "";

		QueueOfInt q = new QueueOfInt();

		int v1;
		int v2;

		makeAllUnvisited();
		vertex[startV].setVisited();
		q.enqueue(startV);

		while (!q.isEmpty()) {
			v1 = q.dequeue();
			v2 = 0;
			vertex[v1].setVisited();
			output += vertex[v1] + " " + v1 + ",";
			for (v2 = 0; v2 < vertex.length; v2++) {

				if (edges[v1][v2] == 1 && vertex[v2].isUnvisited()) {
					q.enqueue(v2);
					vertex[v2].setWaiting();
				} // end if

			} // end for
		} // end while
		return output;
	} // end BFS

	public String DFS(int startV) {
		// uses stack
		String output = "";
		StackOfInt stack = new StackOfInt();
		int v1;
		int v2;

		makeAllUnvisited();
		stack.push(startV);
		output += vertex[startV] + " " + startV + ", ";

		while (!stack.isEmpty()) {
			v1 = stack.pop();
			vertex[v1].setVisited();
			for (v2 = 0; v2 < vertex.length; v2++) {

				if ((edges[v1][v2] == 1) && (vertex[v2].isUnvisited())) {
					stack.push(v1);
					vertex[v2].setWaiting();
					output += vertex[v2] + " " + v2 + ",";
					v1 = v2;
				} // end if

			} // end for
		} // end while
		return output;
	} // end DFS

	public String shortestPath(int startV) {
		// assumes unweighted
		// uses queue

		String output = "";
		QueueOfInt q = new QueueOfInt();

		int v1;
		int v2;
		int pathLength = 0;
		
		makeAllUnvisited();
		
		vertex[startV].setVisited();
		
		q.enqueue(startV);

		while (!q.isEmpty()) {
			v1 = q.dequeue();
			v2 = 0;
			pathLength=0;
			vertex[v1].setVisited();

			for (v2 = 0; v2 < vertex.length; v2++) {

				if (edges[v1][v2] == 1 && vertex[v2].isUnvisited()) {
					pathLength++;
					q.enqueue(v2);
					vertex[v2].setWaiting();
				} // end if

			} // end for
			output += vertex[startV]+ " " +startV+ " to "+ vertex[v1] + " " + v1 + " path length = " + pathLength + "\n";
		} // end while
		return output;
	} // end shortest path
	
    int minDistance(int dist[], Boolean sptSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE;
        int minIndex=-1;
 
        for (int v = 0; v < vertex.length; v++)
            if (sptSet[v] == false && dist[v] <= min)  {
                min = dist[v];
                minIndex = v;
            } // end if
        return minIndex;
    }// end minDistance
	
	public String dijkstras(int start) {
		// assumes weighted
		String output = "";
		int dist[] = new int[vertex.length]; // The output array. dist[i] will hold the shortest distance from start to i

		// sptSet[i] will true if vertex i is included in shortest
		Boolean sptSet[] = new Boolean[vertex.length];

		// Initialize all distances as INFINITE and stpSet[] as false
		for (int i = 0; i < vertex.length; i++) {
			dist[i] = Integer.MAX_VALUE;
			sptSet[i] = false;
		} // end for
		dist[start] = 0;

		// Find shortest path for all vertices
		for (int count = 0; count < vertex.length; count++) {
			int u = minDistance(dist, sptSet);
			sptSet[u] = true;

			for (int v = 0; v < vertex.length; v++) {
				// Update dist[v] only if is not in sptSet, there is an
				// edge from u to v, and total weight of path from start to
				// v through u is smaller than current value of dist[v]
				if (!sptSet[v] && edges[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + edges[u][v] < dist[v]){
					dist[v] = dist[u] + edges[u][v];
				} // end if
			} // end for
			output += "Minimum weight path from " + vertex[start]+ " " +start+ " to " + vertex[count] +" "+count+" is "+dist[count] + "\n";
		} // end for
		return output;
	} // end Dijkstra's

} // end class
