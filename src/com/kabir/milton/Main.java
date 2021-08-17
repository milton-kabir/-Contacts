//package contacts;
package com.kabir.milton;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        var mp1 = new ArrayList<String>();
        var mp2 = new ArrayList<String>();
        var mp3 = new ArrayList<String>();

        var sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter action (add, remove, edit, count, list, exit):");
            String ck = sc.nextLine();
            if (ck.equals("exit")) {
                break;
            }
            if (ck.equals("add")) {
                System.out.println("Enter the name:");
                String fn = sc.nextLine();
                System.out.println("Enter the surname:");
                String sn = sc.nextLine();
                System.out.println("Enter the number:");
                String num = sc.nextLine();
                num = validateNumber(num);
                mp1.add(fn);
                mp2.add(sn);
                mp3.add(num);
                System.out.println("The record added.");
            } else if (ck.equals("list")) {
                for (int i = 0; i < mp1.size(); i++) {
                    System.out.println((i + 1) + ". " + mp1.get(i) + " " + mp2.get(i) + ", " + mp3.get(i));
                }
            } else if (ck.equals("edit")) {
                if (mp1.size() == 0) {
                    System.out.println("No records to edit!");
                    continue;
                }
                printList(mp1, mp2, mp3);
                System.out.println("Select a record:");
                int x = Integer.parseInt(sc.nextLine());
                System.out.println("Select a field (name, surname, number):");
                String st = sc.nextLine();
                System.out.println("Enter " + st + ":");
                if (st.equals("name")) {
                    mp1.set(x - 1, sc.nextLine());
                } else if (st.equals("surname")) {
                    mp2.set(x - 1, sc.nextLine());
                } else {
                    String num = sc.nextLine();
                    num = validateNumber(num);
                    mp3.set(x - 1, num);
                }
                System.out.println("The record updated!");
            } else if (ck.equals("remove")) {
                if (mp1.size() == 0) {
                    System.out.println("No records to remove!");
                    continue;
                }
                printList(mp1, mp2, mp3);
                System.out.println("Select a record:");
                int x = Integer.parseInt(sc.nextLine());
                mp1.remove(x - 1);
                mp2.remove(x - 1);
                mp3.remove(x - 1);

            } else {
                System.out.println("The Phone Book has " + mp1.size() + " records.");
            }

        }
    }

    private static String validateNumber(String num) {
        String ss = num.replace('-', ' ');
        String[] ar = ss.split(" ");
        boolean ckk = true;
        int idx = 0;
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == '+') {
                idx = i;
            }
        }
        if (idx != 0) {
            ckk = false;
        }
        idx = 0;
        int cnt = 0;
        for (int i = 0; i < ar.length; i++) {
            System.out.println(ar[i]);
            if (i == 0 || i == 1) {
                if ((ar[i].contains("(") && !ar[i].contains(")")) || (!ar[i].contains("(") && ar[i].contains(")"))) {
                    ckk = false;
                } else if (ar[i].contains("(") && ar[i].contains(")")) {
                    if (ar[i].indexOf(')') - ar[i].indexOf('(') == 1) {
                        ckk = false;
                    }
                    cnt++;
                }
            } else {
                if (ar[i].contains("(") || ar[i].contains(")")) {
                    ckk = false;
                }
            }
            for (int j = 0; j < ar[i].length(); j++) {
                if (ar[i].charAt(j) != '+' && ar[i].charAt(j) != '(' && ar[i].charAt(j) != ')' && !Character.isLetter(ar[i].charAt(j)) && !Character.isDigit(ar[i].charAt(j))) {
                    idx = -1;
                }
            }
        }
        if (cnt > 1) {
            ckk = false;
        }
        if (idx != 0) {
            ckk = false;
        }


        idx = 0;
        int[] brcid0 = new int[2];
        int[] brcid1 = new int[2];
        brcid0[0] = -1;
        brcid0[1] = -1;
        brcid1[0] = -1;
        brcid1[1] = -1;

        if (ar[0].charAt(0) == '(') {
            brcid0[0] = 0;
            if (ar[0].charAt(ar[0].length() - 1) == ')') {
                idx++;
                brcid0[1] = ar[0].length() - 1;
            } else {
                ckk = false;
            }

        }
        if (ar.length > 1) {
            if (ar[1].charAt(0) == '(') {
                brcid1[0] = 0;
                if (ar[1].charAt(ar[1].length() - 1) == ')') {
                    idx++;
                    brcid1[0] = ar[1].length() - 1;
                } else {
                    ckk = false;
                }

            }
        }
        if (idx > 1) {
            ckk = false;
        }
        idx = 0;
        for (int i = brcid0[0] + 1; i < brcid0[1]; i++) {
            if (ar[0].charAt(i) == '(' || ar[0].charAt(i) == ')') {
                idx++;
            }
        }
        if (ar.length > 1) {
            for (int i = brcid1[0]; i < brcid1[1]; i++) {
                if (i >= 0 && i <= ar[1].length()) {
                    if (ar[1].charAt(i) == '(' || ar[1].charAt(i) == ')') {
                        idx++;
                    }
                }
            }
        }
        if (idx != 0) {
            ckk = false;
        }

        idx = 0;
        for (int i = 0; i < ar.length; i++) {
            if (i > 1) {
                if (ar[i].contains("(") || ar[i].contains(")")) {
                    idx = 1;
                }
            }
            if (i == 0) {
                if (ar[i].contains("+") && ar[i].length() < 2) {
                    idx = 1;
                }
            } else {
                if (ar[i].length() < 2) {
                    idx = 1;
                }
            }
        }
        if (idx == 1) {
            ckk = false;
        }
        if (!ckk) {
            System.out.println("Wrong number format!");
            return "[no number]";
        }
        return num;
    }

    private static void printList(ArrayList<String> mp1, ArrayList<String> mp2, ArrayList<String> mp3) {
        for (int i = 0; i < mp1.size(); i++) {
            System.out.println((i + 1) + ". " + mp1.get(i) + " " + mp2.get(i) + ", " + mp3.get(i));
        }

    }
}
