package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Grid extends ArrayList<ArrayList<Cell>> {
    static Integer linii, coloane;
    static ArrayList<ArrayList<Cell>> map = new ArrayList<>();
    static Character curent; //Referinta la caracterul curent
    static Cell curent_cell; //Celula curenta

    private Grid() {

    }

    public static void setMap(ArrayList<ArrayList<Cell>> newMap) {
        map = newMap;
        curent_cell = map.get(0).get(0);
        curent_cell.vizitat = true;
    }

    public static void setCurent(Character newCurent) {
        curent = newCurent;
    }

    public static void setColoane(Integer newColoane) {
        coloane = newColoane;
    }

    public static void setLinii(Integer newLinii) {
        linii = newLinii;
    }

    public static Integer getLinii() {
        return linii;
    }

    public static Integer getColoane() {
        return coloane;
    }

    public static ArrayList<ArrayList<Cell>> generare(Integer lungime, Integer coloane) {
        int i, j;
        for (i = 0; i < lungime; i++) {
            ArrayList<Cell> rand = new ArrayList<>();
            for (j = 0; j < coloane; j++) {
                Cell nou = new Cell(i, j, CellEnum.EMPTY, false);
                rand.add(nou);
            }
            map.add(rand);
        }
        System.out.println("Introduceti indicele corespunzator modului generare a hartii.");
        System.out.println("1 - Harta de testare");
        System.out.println("2 - Harta generata random");
        Scanner input = new Scanner(System.in);
        int rasp;
        rasp = input.nextInt();
        if (rasp == 1) {
            // Harta va fi de testare
            map.get(0).get(3).setTip_element(CellEnum.SHOP);
            map.get(1).get(3).setTip_element(CellEnum.SHOP);
            map.get(2).get(0).setTip_element(CellEnum.SHOP);
            map.get(3).get(4).setTip_element(CellEnum.ENEMY);
            map.get(4).get(4).setTip_element(CellEnum.FINISH);
        } else {
            // Harta va fi generata random
            Random rn = new Random();
            int contor = 0, lin, col;
            Cell curent;
            // Plasam in 4 pozitii random inamici
            while (contor < 4) {
                lin = rn.nextInt(Grid.getLinii());
                col = rn.nextInt(Grid.getColoane());
                curent = map.get(lin).get(col);
                if (curent.tip_element.equals(CellEnum.EMPTY) && !(lin == 0 && col == 0)) {
                    map.get(lin).get(col).setTip_element(CellEnum.ENEMY);
                    contor++;
                }
            }
            contor = 0;
            // Plasam in 2 pozitii random shopurile
            while (contor < 2) {
                lin = rn.nextInt(Grid.getLinii());
                col = rn.nextInt(Grid.getColoane());
                curent = map.get(lin).get(col);
                if (curent.tip_element.equals(CellEnum.EMPTY) && !(lin == 0 && col == 0)) {
                    map.get(lin).get(col).setTip_element(CellEnum.SHOP);
                    contor++;
                }
            }
            // Punem pozitia de final random
            map.get(Grid.getLinii() - 1).get(Grid.getColoane() - 1).setTip_element(CellEnum.FINISH);
        }

        return map;
    }

    static void giveCoins() {
        if (!curent_cell.vizitat && curent_cell.tip_element == CellEnum.EMPTY) {
            // Alegem un nr random intre 1 si 5
            // Sansa de 20% == sansa de a fi nr. 3
            Random rn = new Random();
            int nr = rn.nextInt(5) + 1;
            if (nr == 3) {
                // Se pot gasi intre 30 si 60 de monede
                int c = rn.nextInt(31) + 30;
                curent.inventar.addCoins(c);
                System.out.println("Ai primit " + c + ". Noua balanta: " + curent.inventar.coins);
            }
        }
    }

    static void goNorth() {
        // curent_cell.Ox - deplasare sus jos
        if (curent_cell.Ox == 0) {
            System.out.println("Nu se poate merge mai sus.");
        } else {
            curent_cell = map.get(curent_cell.Ox - 1).get(curent_cell.Oy);
            Game.getStory(curent_cell);
            Game.getOptions(curent_cell);
            giveCoins();
            curent_cell.vizitat = true;
        }
    }

    static void goSouth() {
        if (curent_cell.Ox == Grid.getLinii() - 1) {
            System.out.println("Nu se poate merge mai jos.");
        } else {
            curent_cell = map.get(curent_cell.Ox + 1).get(curent_cell.Oy);
            Game.getStory(curent_cell);
            Game.getOptions(curent_cell);
            giveCoins();
            curent_cell.vizitat = true;
        }
    }

    static void goWest() {
        // Deplasare stanga - dreapta
        if (curent_cell.Oy == 0) {
            System.out.println("Nu se poate merge la stanga");
        } else {
            curent_cell = map.get(curent_cell.Ox).get(curent_cell.Oy - 1);
            Game.getStory(curent_cell);
            Game.getOptions(curent_cell);
            giveCoins();
            curent_cell.vizitat = true;
        }
    }

    static void goEast() {
        if (curent_cell.Oy == Grid.getColoane() - 1) {
            System.out.println("Nu se poate merge la dreapta");
        } else {
            curent_cell = map.get(curent_cell.Ox).get(curent_cell.Oy + 1);
            Game.getStory(curent_cell);
            Game.getOptions(curent_cell);
            giveCoins();
            curent_cell.vizitat = true;
        }
    }
}
