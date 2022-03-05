package com.company;

import java.util.ArrayList;

public class Inventory {
    ArrayList<Potion> potions;
    Integer maxWeight;
    Integer coins;

    // Warrior - maxim 6 potiuni in inventar
    // Rogue - maxim 4 potiuni in inventar
    // Mage - maxim 2 potiuni in inventar
    public Inventory(Integer maxWeight, Integer coins) {
        this.maxWeight = maxWeight;
        this.coins = coins;
        this.potions = new ArrayList<>();
    }

    void addPotion(Potion potion) {
        potions.add(potion);
        coins = coins - potion.getPricePotion();
    }

    void addCoins(Integer nr) {
        coins = coins + nr;
    }

    void delPotion(int poz) {
        if (poz > potions.size()) {
            System.out.println("Potiunea cu indexul indicat nu exista.");
            return;
        }
        potions.remove(poz);
    }

    Integer availableWeight() {
        int i;
        Integer weight = 0;
        for (i = 0; i < potions.size(); i++) {
            Integer curent = potions.get(i).getWeight();
            weight = weight + curent;
        }
        return (maxWeight - weight);
    }

    void showPotions() {
        int i;
        for (i = 0; i < potions.size(); i++) {
            System.out.println(i + 1 + ". " + potions.get(i));
        }
    }
}
