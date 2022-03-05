package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Shop implements CellElement {
    ArrayList<Potion> potions = new ArrayList<>();

    public Shop() {
        Random rn = new Random();
        int nrPotions = rn.nextInt(3) + 2;
        int i;
        for (i = 0; i < nrPotions; i++) {
            boolean typePotion = rn.nextBoolean();
            if (typePotion) {
                HealthPotion p = new HealthPotion(150, 1, 20);
                potions.add(p);
            } else {
                ManaPotion p = new ManaPotion(100, 1, 20);
                potions.add(p);
            }
        }
    }

    public Potion getPotion(int poz) {
        if (poz < potions.size()) {
            Potion p = potions.get(poz);
            potions.remove(poz);
            return p;
        }
        return null;
    }

    public void showPotions() {
        int i;
        for (i = 0; i < potions.size(); i++) {
            System.out.println(i + 1 + ". " + potions.get(i));
        }
    }

    @Override
    public String toCharacter() {
        return "S";
    }
}
