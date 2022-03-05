package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Test {
    public static void afisare(ArrayList<ArrayList<Cell>> harta) {
        int i, j;
        //harta.get(0).get(0).vizitat=true;
        for (i = 0; i < Grid.getLinii(); i++) {
            for (j = 0; j < Grid.getColoane(); j++) {
                if (i == Grid.curent_cell.Ox && j == Grid.curent_cell.Oy) {
                    System.out.print("\u001B[31m" + "P " + "\u001B[0m");
                } else {
                    System.out.print(harta.get(i).get(j) + " ");
                }

                //System.out.print(harta.get(i).get(j).Ox + " " + harta.get(i).get(j).Oy+ "   " + harta.get(i).get(j).tip_element + " ");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) throws InvalidCommandException {
        Grid.setColoane(5);
        Grid.setLinii(4);
        ArrayList<ArrayList<Cell>> harta = Grid.generare(Grid.getLinii(), Grid.getColoane());
        //System.out.println(Grid.getLinii()+" "+ Grid.getColoane());
        Grid.setMap(harta);
       /*   TESTARE BUILDER PATTERN ACCOUNT.INFORMATION
        Credentials auth = new Credentials();
        auth.setEmail("gafi@yahoo.com");
        auth.setPassword("123");
        ArrayList<String> jocuri = new ArrayList();
        jocuri.add("wow");
        jocuri.add("lol");

        Account.Information user1 = new Account.Information.Builder(auth, "Lupita")
                .jocuri(jocuri)
                .tara("Romania")
                .build();
        System.out.println(user1);*/

       /*   TESTARE PENTRU SHOP
        Shop magazin = new Shop();
        System.out.println(magazin.getPotion(0));
        System.out.println(magazin.getPotion(0));
        System.out.println(magazin.getPotion(0));
        System.out.println(magazin.getPotion(0));*/
/*
        TESTARE ACCOUNT
        Credentials auth = new Credentials();
        auth.setEmail("mail@gmail.com");
        auth.setPassword("123");
        ArrayList<String> jocuri = new ArrayList<>();
        jocuri.add("wow");
        jocuri.add("lol");
        Account.Information user = new Account.Information.Builder(auth, "Eroii Revolutiei")
                .jocuri(jocuri)
                .tara("Romania")
                .build();
        ArrayList<Character> personaje= new ArrayList<>();
        Warrior pers1 = new Warrior();
        pers1.name="Razboinic";
        personaje.add(pers1);

        Mage pers2 = new Mage();
        pers2.name="Mag";
        personaje.add(pers2);

        Rogue pers3 = new Rogue();
        pers3.name="Rog";
        personaje.add(pers3);

        Account cont = new Account(user,personaje,0);
        System.out.println(cont);
*/
        // afisare(harta);

        Game joc = Game.getInstance();
        joc.run();

        Warrior pers1 = new Warrior("Razboinic", 0, 0);
        Grid.setCurent(pers1);
        Grid.curent.startCharacter();
        int ok = 1;
        while (ok == 1) {
            Scanner comenzi = new Scanner(System.in);
            System.out.println("Introduceti urmatoarea mutare");
            String comanda = comenzi.nextLine();
            if (comanda.contains("w")) {
                //System.out.println("Mutam sus");
                Grid.goNorth();
                afisare(harta);
                //pers1.inventar.showPotions();
            } else {
                if (comanda.contains("a")) {
                    //System.out.println("Mutam stanga");
                    Grid.goWest();
                    afisare(harta);
                    //pers1.inventar.showPotions();
                } else {
                    if (comanda.contains("s")) {
                        //System.out.println("Mutam jos");
                        Grid.goSouth();
                        afisare(harta);
                        //pers1.inventar.showPotions();
                    } else {
                        if (comanda.contains("d")) {
                            //System.out.println("Mutam dreapta");
                            Grid.goEast();
                            afisare(harta);
                            //pers1.inventar.showPotions();
                        } else {
                            if (comanda.equals("0")) {
                                ok = 0;
                                System.out.println("Mutare oprita.");


                            } else {
                                //System.out.println("Comanda invalida. Reincercati.");
                                throw new InvalidCommandException("Comanda invalida. Reincercati.");
                            }
                        }

                    }
                }
            }
            if (harta.get(Grid.curent_cell.Ox).get(Grid.curent_cell.Oy).toString().equals("F")) {
                System.out.println("Ati ajuns la final.");
                ok = 0;
            }
            System.out.println(harta.get(Grid.curent_cell.Ox).get(Grid.curent_cell.Oy).toString());
            //System.out.println("Ox " + Grid.curent_cell.Ox + " Oy " + Grid.curent_cell.Oy);
        }
        //System.out.println(Game.accounts);
    }

}
