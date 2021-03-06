package Codeforces;

import java.math.BigInteger;
import java.util.Scanner;

public class SelectionOfPersonnel {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		BigInteger[] factorial = new BigInteger[n+1];
		
		factorial[0] = BigInteger.ONE;
		for (int i = 1; i < n+1; i++)
			factorial[i] = BigInteger.valueOf(i).multiply(factorial[i-1]);
		
		BigInteger c5 = factorial[n].divide((factorial[5].multiply(factorial[n-5])));
		BigInteger c6 = factorial[n].divide((factorial[6].multiply(factorial[n-6])));
		BigInteger c7 = factorial[n].divide((factorial[7].multiply(factorial[n-7])));
		System.out.println(c5.add(c6).add(c7));
		sc.close();
	}
	
}
