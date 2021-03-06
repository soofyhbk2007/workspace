package UVa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class MoneyMatters {

	public static void main (String[] args) throws java.lang.Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		while(t-->0){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); //number of friends
			int m = Integer.parseInt(st.nextToken()); //number of friendships

			int arr[] = new int[n];
			for (int i = 0; i < n; i++) 
				arr[i] = Integer.parseInt(br.readLine());

			DisjointSets ds = new DisjointSets(n, arr);

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				ds.union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			boolean f = true;
			for (int i = 0; i < ds.money.length && f; i++) {
				if (ds.money[i] != 0)
					f = false;
			}

			if (f)
				System.out.println("POSSIBLE");
			else
				System.out.println("IMPOSSIBLE");
		}
	}
	static class DisjointSets {
		int representative[];
		int rank[];
		int money[];

		public DisjointSets(int n, int m[]) {
			representative = new int[n];
			rank = new int[n];
			for (int i = 0; i < representative.length; i++)
				representative[i] = i;
			Arrays.fill(rank, 1);
			money = new int[n];

			for (int i = 0; i < m.length; i++) 
				money[i] = m[i];

		}

		int findSet(int x) {
			if (x == representative[x])
				return x;
			return representative[x] = findSet(representative[x]);
		}

		void union(int x, int y) {
			int x1 = findSet(x);
			int y1 = findSet(y);
			if (x1 != y1)
				if (rank[x1] > rank[y1]) {
					representative[y1] = x1;
					int sum = money[x1] + money[y1];
					money[x1] = sum;
					money[y1] = 0;
				} else if (rank[x1] < rank[y1]) {
					representative[x1] = y1;
					int sum = money[x1] + money[y1];
					money[y1] = sum;
					money[x1] = 0;
				} else {
					representative[x1] = y1;
					rank[y1]++;
					int sum = money[x1] + money[y1];
					money[y1] = sum;
					money[x1] = 0;
				}
		}

	}
}













