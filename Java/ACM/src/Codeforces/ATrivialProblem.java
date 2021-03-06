package Codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ATrivialProblem {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int m = Integer.parseInt(br.readLine());
	    int twos[] = new int[10000000];
	    int fives[] = new int[10000000];
	    twos[0] = fives[0] = 0;
	    twos[2] = 1;
	    fives[5] = 1;

	    for (int i = 1; i < 10000000; i++)
	    {
	        if (i == 2)
	            continue;

	        if (i%2 == 0)
	            twos[i] = twos[i/2] + twos[2];
	        else
	            twos[i] = 0;

	        if (i == 5)
	            continue;
	        if (i%5 == 0)
	            fives[i] = fives[i/5] + fives[5];
	        else
	            fives[i] = 0;

	    }

	    for (int i = 1; i < 10000000; i++)
	    {
	        twos[i] += twos[i-1];
	        fives[i] += fives[i-1];
	    }


	    ArrayList<Integer> res = new ArrayList<>();
	    for (int i = 1; i < 10000000; i++)
	        if (Math.min(twos[i], fives[i]) == m)
	            res.add(i);

	    int s = res.size();
	    System.out.println(s);
	    for (int i = 0; i < s; i++)
	    	System.out.print(res.get(i) + " ");

	}
	    
}
