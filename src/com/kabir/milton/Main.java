//package contacts;
package com.kabir.milton;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        var mp1=new HashMap<String, String>();
        var mp2=new HashMap<String,String>();
        var mp3=new HashMap<String, String>();
        var sc=new Scanner(System.in);
        System.out.println("Enter the name of the person:");
        String fn=sc.nextLine();
        System.out.println("Enter the surname of the person:");
        String sn=sc.nextLine();
        System.out.println("Enter the number:");
        String num=sc.nextLine();

        mp1.put(fn,sn);
        mp2.put(fn,num);
        mp3.put(sn,num);

        System.out.println("\nA record created!\n" +
                "A Phone Book with a single record created!");
    }
}
