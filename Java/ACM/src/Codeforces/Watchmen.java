package Codeforces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Watchmen {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int n = sc.nextInt();

		TreeMap<Pair, Integer> tm = new TreeMap<Pair, Integer>();
		TreeMap<Integer, Integer> tm_X = new TreeMap<Integer, Integer>();
		TreeMap<Integer, Integer> tm_Y = new TreeMap<Integer, Integer>();
		
		for (int i = 0; i < n; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();

			Pair p = new Pair(x, y);
			
			if (tm.containsKey(p)) {
				int c = tm.get(p);
				tm.put(p, c+1);
			}
			else 
				tm.put(p, 1);
			
			if (tm_X.containsKey(x)) {
				int c = tm_X.get(x);
				tm_X.put(x, c+1);
			}
			else 
				tm_X.put(x, 1);
			
			if (tm_Y.containsKey(y)) {
				int c = tm_Y.get(y);
				tm_Y.put(y, c+1);
			}
			else 
				tm_Y.put(y, 1);
		}
		
		long ans = 0;
		for (Entry<Integer, Integer> entry : tm_X.entrySet()) {
			long value = (long) entry.getValue();
			
			ans += (value * (value-1))/2;
		}
		
		for (Entry<Integer, Integer> entry : tm_Y.entrySet()) {
			long value = (long) entry.getValue();
			
			ans += (value * (value-1))/2;
		}
		
		for (Entry<Pair, Integer> entry : tm.entrySet()) {
			long value = (long)entry.getValue();
			
			ans -= (value * (value-1))/2;
		}
		
		out.println(ans);

		out.flush();
		out.close();

	}

	static class Pair implements Comparable<Pair>{
		int first;
		int second;

		public Pair(int f, int s) {
			first = f;
			second = s;
		}

		@Override
		public int compareTo(Pair o) {
			if (Integer.compare(first, o.first) != 0)
				return Integer.compare(first, o.first);
			return Integer.compare(second, o.second);
		}

		@Override
		public String toString() {
			return "(" + first + ", " + second + ")";
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
