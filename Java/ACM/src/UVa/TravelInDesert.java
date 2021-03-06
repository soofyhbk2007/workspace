package UVa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class TravelInDesert {
	static ArrayList<Node> adjlist[];
	static ArrayList<Node> temp[];
	static Edge[] edgelist;
	static int s, t, n;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.Ready()) {
			n = sc.nextInt();
			int e = sc.nextInt();

			s = sc.nextInt()-1;
			t = sc.nextInt()-1;

			adjlist = new ArrayList[n];
			for (int i = 0; i < n; i++)
				adjlist[i] = new ArrayList<>();
			edgelist = new Edge[e];

			for (int i = 0; i < e; i++) {
				int u = sc.nextInt()-1, v = sc.nextInt()-1;
				double r = sc.nextDouble(), d = sc.nextDouble();

				edgelist[i] = new Edge(u, v, r);
				adjlist[u].add(new Node(v, r, d));
				adjlist[v].add(new Node(u, r, d));
			}

			double minimax = mm();

			double[] dist = new double[n];
			Arrays.fill(dist, -1);

			PriorityQueue<Entry> pq = new PriorityQueue<>();
			dist[s] = 0;
			int[] parent = new int[n];
			parent[s] = s;
			pq.add(new Entry(s, 0));
			while(!pq.isEmpty()) {
				Entry cur = pq.remove();
				if (cur.node == t)
					break;
				if (cur.cost > dist[cur.node])
					continue;

				for (Node nxt : adjlist[cur.node])
					if (nxt.temperature <= minimax && (dist[nxt.name] == -1 || cur.cost + nxt.length < dist[nxt.name])) {
						dist[nxt.name] = cur.cost + nxt.length;
						parent[nxt.name] = cur.node;
						pq.add(new Entry(nxt.name, dist[nxt.name]));
					}
			}

			Stack<Integer> s = new Stack<>();
			int tmp = t;
			while(true) {
				s.push(tmp);
				if (tmp == parent[tmp])
					break;
				tmp = parent[tmp];
			}
			int i = 0;
			while(!s.isEmpty()) {
				if (i++>0)
					out.print(" " + (s.pop()+1));
				else
					out.print(s.pop()+1);
			}
			out.printf("\n%.1f %.1f\n", dist[t], minimax);
		}

		out.flush();
		out.close();
	}

	public static double mm() {
		temp = new ArrayList[n];
		for (int i = 0; i < n; i++)
			temp[i] = new ArrayList<>();

		Arrays.sort(edgelist);
		DisjointSets ds = new DisjointSets(n);

		for (Edge e : edgelist)
			if (!ds.inSameSet(e.from, e.to)) {
				ds.union(e.from, e.to);
				temp[e.from].add(new Node(e.to, e.temperature, 0));
				temp[e.to].add(new Node(e.from, e.temperature, 0));
			}

		return dfs(s, -1, 0);
	}

	public static double dfs(int u, int parent, double maxSoFar) {
		if (u == t)
			return maxSoFar;

		for (Node v : temp[u]) 
			if (v.name != parent) {
				double d = dfs(v.name, u, Math.max(maxSoFar, v.temperature));
				if (d > 0)
					return d;
			}

		return 0;
	}

	static class Entry implements Comparable<Entry> {
		int node;
		double cost;
		public Entry(int n, double c) {
			node = n;
			cost = c;
		}	

		@Override
		public int compareTo(Entry o) {
			if (Double.compare(cost, o.cost) != 0)
				return Double.compare(cost, o.cost);
			return Integer.compare(node, o.node);
		}
	}

	static class Node {
		int name;
		double length;
		double temperature;

		public Node(int n, double t, double l) {
			name = n;
			length = l;
			temperature = t;
		}
	}

	static class Edge implements Comparable<Edge> {
		int from, to;
		double temperature;
		public Edge(int f, int t, double temp) {
			from = f;
			to = t;
			temperature = temp;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(temperature, o.temperature);
		}
	}

	static class DisjointSets {
		int representative[];
		int rank[];

		public DisjointSets(int n) {
			representative = new int[n];
			rank = new int[n];
			for (int i = 0; i < representative.length; i++)
				representative[i] = i;
			Arrays.fill(rank, 1);
		}

		int findSet(int x) {
			if (x == representative[x])
				return x;
			return representative[x] = findSet(representative[x]);
		}

		boolean inSameSet(int x,int y){
			return (findSet(x) == findSet(y));
		}

		void union(int x, int y) {
			int x1 = findSet(x);
			int y1 = findSet(y);
			if (x1 != y1)
				if (rank[x1] > rank[y1]) {
					representative[y1] = x1;
				} else if (rank[x1] < rank[y1]) {
					representative[x1] = y1;
				} else {
					representative[x1] = y1;
					rank[y1]++;
				}
		}
	}


	static class Scanner {
		BufferedReader br;
		StringTokenizer st;

		public Scanner(FileReader f) {
			br = new BufferedReader(f);
		}

		public Scanner(InputStream in) {
			br = new BufferedReader(new InputStreamReader(in));
		}

		public String next() throws IOException {
			while (st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public String nextLine() throws IOException {
			return br.readLine();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		public long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		public double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}

		public boolean Ready() throws IOException {
			return br.ready();
		}

		public void waitForInput(long time) {
			long ct = System.currentTimeMillis();
			while(System.currentTimeMillis() - ct < time) {};
		}

	}

}
