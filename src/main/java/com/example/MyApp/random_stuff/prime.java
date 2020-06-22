package com.example.MyApp.random_stuff;
import java.util.Scanner;

public class prime {
    public  boolean is_prime(int n)
    {
        int k= (int) Math.sqrt(n);
        int no_factors=0;
        for(int i=1;i<=k;k++)
        {
            if(n%i==0)
                no_factors++;
        }
        if(no_factors==2)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a number");
        prime obj=new prime();
        int val=sc.nextInt();
        if(obj.is_prime(val))
            System.out.println("number is prime");
        else
            System.out.println("number is not prime");
    }
}
