package com.qtum.walletkeystore;

import com.qtum.walletkeystore.utils.KeyStorage;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args){
        System.out.println("Qtum Mobile Wallet Creater & Show Prive Key");
        int menu;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            System.out.println("1. Create Wallet");
            System.out.println("2. Import Wallet");
            System.out.println("3. Exit");
            System.out.println("=> Please enter a number:");
            try {
                menu =  Integer.parseInt(in.readLine());
                if (menu == 1) {
                    System.out.println("Crate Qtum Wallet");
                    KeyStorage keyStorage = new KeyStorage();
                    keyStorage.createWallet();
                } else if (menu == 2) {
                    System.out.println("Import Qtum Wallet");
                    System.out.println("=> Please enter words:");
                    String words = in.readLine();
                    KeyStorage keyStorage = new KeyStorage();
                    keyStorage.importWallet(words);
                }else if(menu == 3){
                    System.exit(0);
                }else {
                    System.out.println("Invalid menu number.");
                }
            } catch (Exception e) {
                System.out.println("Invalid menu number.");
            }
        }

    }




}
