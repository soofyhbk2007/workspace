package UVa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BitMaps {
	static char[][] grid;
	static StringBuilder s;
	static int index, len, w, h;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true) {
			char format = sc.next().charAt(0);
			if (format == '#') break;

			w = sc.nextInt();
			h = sc.nextInt();

			grid = new char[w][h];
			s = new StringBuilder();
			String width = w + "";
			String height = h + "";

			int spacesW = 4 - width.length();
			int spacesH = 4 - height.length();
			out.print(format == 'D' ? 'B' : 'D');
			for (int i = 0; i < spacesW; i++)
				out.print(" ");
			out.print(w);
			for (int i = 0; i < spacesH; i++)
				out.print(" ");
			out.println(h);
			if (format == 'B') {
				int count = 0;
				int row = 0;
				int col = 0;
				while(count < w*h) {
					String line = sc.nextLine();
					for (int i = 0; i < line.length(); i++) {
						grid[row][col] = line.charAt(i);
						count++;
						col++;
						if (col == h) {
							col = 0;
							row++;
						}
					}
				}
				

				solve1(0, 0, w-1, h-1);
				int coun = 0;
				for (int i = 0; i < s.length(); i++) {
					if (coun == 50) {
						out.println();
						coun = 0;
					}
					out.print(s.charAt(i));
					coun++;
				}
				out.println();
			}
			else {
				s = new StringBuilder(sc.nextLine());
				index = 0;
				len = s.length();
				for (int i = 0; i < w; i++) 
					Arrays.fill(grid[i], '!');
				solve2(0, 0, w-1, h-1);
				int count = 0;
				for (int i = 0; i < w; i++) 
					for (int j = 0; j < h; j++) {
						if (count == 50) {
							out.println();
							count = 0;
						}
						out.print(grid[i][j]);
						count++;
					}
				out.println();
			}
		}

		out.flush();
		out.close();
	}
	
	public static boolean valid(int x, int y, int a, int b) {
		return x >= 0 && x < w && y >= 0 && y < h && a >= 0 && a < w && b >= 0 && b < h && x <= a && y <= b;
	}

	public static void solve2(int startRow, int startCol, int endRow, int endCol) {
		if (valid(startRow, startCol, endRow, endCol)) {
			char c = s.charAt(index++);
			if (c == '0' || c == '1') {
				for (int i = startRow; i <= endRow; i++)
					for (int j = startCol; j <= endCol; j++)
						grid[i][j] = c;
			}
			else {
				solve2(startRow, startCol, Ceil((endRow-startRow+1), 2)-1+startRow, Ceil((endCol-startCol+1), 2)-1+startCol);
				solve2(startRow, Ceil((endCol-startCol+1), 2)+startCol, Ceil((endRow-startRow+1), 2)-1+startRow, endCol);
				solve2(Ceil((endRow-startRow+1), 2)+startRow, startCol, endRow, Ceil((endCol-startCol+1), 2)-1+startCol);
				solve2(Ceil((endRow-startRow+1), 2)+startRow, Ceil((endCol-startCol+1), 2)+startCol, endRow, endCol);
			}
		}
	}

	public static void solve1(int startRow, int startCol, int endRow, int endCol) {
		boolean all0s = true;
		boolean all1s = true;
		
		for (int i = startRow; i <= endRow; i++) {
			for (int j = startCol; j <= endCol; j++) {
				if (grid[i][j] != '0')
					all0s = false;
				if (grid[i][j] != '1')
					all1s = false;
			}
		}
	
	
		if (!(all0s && all1s)) {
			if (all0s) 
				s.append('0');
			else if (all1s) 
				s.append('1');
			else {
				s.append('D');
				solve1(startRow, startCol, Ceil((endRow-startRow+1), 2)-1+startRow, Ceil((endCol-startCol+1), 2)-1+startCol);
				solve1(startRow, Ceil((endCol-startCol+1), 2)+startCol, Ceil((endRow-startRow+1), 2)-1+startRow, endCol);
				solve1(Ceil((endRow-startRow+1), 2)+startRow, startCol, endRow, Ceil((endCol-startCol+1), 2)-1+startCol);
				solve1(Ceil((endRow-startRow+1), 2)+startRow, Ceil((endCol-startCol+1), 2)+startCol, endRow, endCol);
			}
		}
	}



	public static int Ceil(int a, int b) {
		return a%b == 0 ? a/b : a/b + 1;
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
