package Codeforces;

import java.math.BigInteger;
import java.util.Scanner;

public class CrackingTheCode {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		char[] c = sc.next().toCharArray();
		String s = "" + c[0] + c[2] + c[4] + c[3] + c[1];
		BigInteger b = new BigInteger(s);
		b = b.pow(5);
		b = b.mod(BigInteger.valueOf(100000));
		int ans = b.intValue();
		int zeros = 5 - (ans+"").length();
		String res = ""+ans;
		for (int i = 0; i < zeros; i++)
			res = '0' + res;
		System.out.println(res);
		sc.close();
	}
}
