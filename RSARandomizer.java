package Randomizer;
import java.lang.Math;
import java.io.*;
import java.util.Scanner;
import java.math.BigInteger;

public class RSARandomizer {
	
	private static int d;
	
	private static int e;
	
	private static int n;

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int[] randomPrimes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229};
		
		int p = randomPrimes[(int) (Math.random() * 49)];
		int q = randomPrimes[(int) (Math.random() * 49)];
	//	int p=17;
	//	int q=19;
		
		while(p == q) { 
				p = randomPrimes[(int) (Math.random() * 49)];
				q = randomPrimes[(int) (Math.random() * 49)];
		}
		n = p * q;
		int lambda = lcm(p-1, q-1);
		//int lambda = q-1*p-1;
		e = 3;
		
		
		while(!isCoprime(lambda, e)) {
			e++;
		}
		
		
		d = modInverse(e, lambda);
		
		
		System.out.println("Your private keys are d: " + d + " and lambda: " + lambda);
		System.out.println("Your public keys are e: " + e + " and n: " + n);
		
		System.out.println("Enter the message you want to be transmitted - integers for now");
		while(!in.hasNextBigInteger()) {
			System.out.println("Enter an integer");
			in.hasNext();
		}
		
		BigInteger input = in.nextBigInteger();
		BigInteger encrypted = encrypt(input);
		System.out.println("Encrypted message: " + encrypted);
		System.out.println("Decrypting the encrypted: " + decrypt(encrypted));
		
		
	}
	
	
	public static BigInteger encrypt(BigInteger a) {
		BigInteger nn = BigInteger.valueOf(n);
		a = a.pow(e);
	//	System.out.println(a + " " + e);
		a = a.mod(nn);
	//	System.out.println(a + " " + nn);
		return a;
	}
	
	public static BigInteger decrypt(BigInteger a) {
		BigInteger nn = BigInteger.valueOf(n);
		a = a.pow(d);
	//	System.out.println(a + " " + d);
		a = a.mod(nn);
		return a;
		
		
	}
	
	public static long gcd(int a, int b) {
	
		long gcd = 0;
		int r = 0;

		a = Math.abs (a);
		b = Math.abs (b);

		while (true) {
			if (b == 0) {
				gcd = a;
				break;
			}
			else {
				r = a % b;
				a = b;
				b = r;
			}
		}

		return gcd;

		}

	
	
	public static boolean isCoprime(int a, int b) {
		return gcd(a,b) == 1;
	}
	
	public static int modInverse(int a, int b)
	{
		 a = a % b; 
	        for (int x = 1; x < b; x++) 
	           if ((a * x) % b == 1) 
	              return x; 
	        return 1; 
	}
	
	public static int lcm(int a, int b) {
		int x,max=0,min=0,lcm=0;
        if(a >b)
        {
            max=a;
            min=b;
        }
        else
        {
            max=b;
            min=a;
        }
          
     
          
        for(int i=1;i<=min;i++)
           {
            x=max*i; 
            if(x%min==0) 
             {
              lcm=x; 
              break; 
             }
            }
         return lcm;
	}
	
	}

