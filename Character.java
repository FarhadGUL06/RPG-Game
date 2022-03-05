package com.company;

import java.util.Random;

public abstract class Character extends Entity {
    String name;
    Integer Ox, Oy;
    Integer exp;
    Integer level;
    Inventory inventar;
    Integer strength, charisma, dexterity;

    void startCharacter() {
        // Setam pozitia la inceput
        this.Ox = 0;
        this.Oy = 0;
        // Maxim de mana si viata
        this.current_life = max_life;
        this.current_mana = max_mana;
        // Generam abilitatile
        if (abilitati.size() == 0) {
            Random rn = new Random();
            int nr_spells = rn.nextInt(3) + 2;
            int i, tip_spell;
            for (i = 0; i < nr_spells; i++) {
                tip_spell = rn.nextInt(3) + 1;
                if (tip_spell == 1) {
                    Spell abilitate = new Fire();
                    abilitati.add(abilitate);
                }
                Spell abilitate;
                if (tip_spell == 2) {
                    abilitate = new Ice();
                } else {
                    abilitate = new Earth();
                }
                abilitati.add(abilitate);
            }
        }
        // Scalam atributele in functie de nivelul curent
        calculateAtributes();
    }

    void calculateAtributes() {
        int i;
        for (i = 1; i < level; i++) {
            if (Grid.curent.fire) {
                // Pentru Warrior
                Grid.curent.strength = Grid.curent.strength + 4;
                if (Grid.curent.level >= 4) {
                    Grid.curent.dexterity = Grid.curent.dexterity + 4;
                    Grid.curent.charisma = Grid.curent.charisma + 4;
                } else {
                    Grid.curent.dexterity = Grid.curent.dexterity + 2;
                    Grid.curent.charisma = Grid.curent.charisma + 2;
                }
            }
            if (Grid.curent.ice) {
                // Pentru Mage
                Grid.curent.charisma = Grid.curent.charisma + 4;
                if (Grid.curent.level >= 4) {
                    Grid.curent.dexterity = Grid.curent.dexterity + 4;
                    Grid.curent.strength = Grid.curent.strength + 4;
                } else {
                    Grid.curent.dexterity = Grid.curent.dexterity + 2;
                    Grid.curent.strength = Grid.curent.strength + 2;
                }
            }
            if (Grid.curent.ice) {
                // Pentru Rogue
                Grid.curent.dexterity = Grid.curent.dexterity + 4;
                if (Grid.curent.level >= 4) {
                    Grid.curent.charisma = Grid.curent.charisma + 4;
                    Grid.curent.strength = Grid.curent.strength + 4;
                } else {
                    Grid.curent.charisma = Grid.curent.charisma + 2;
                    Grid.curent.strength = Grid.curent.strength + 2;
                }
            }
        }

    }

    boolean buyPotion(Potion p) {
        if (inventar.coins >= p.getPricePotion()) {
            if (inventar.availableWeight() >= p.getWeight()) {
                // Poate fi cumparata si exista spatiu
                inventar.addPotion(p);
                System.out.println("Ati cumparat potiunea.");
                System.out.println("Bani ramasi: " + inventar.coins);
                return true;
            } else {
                System.out.println("Inventarul dumneavoastra este plin.");
                return false;
            }
        } else {
            System.out.println("Nu aveti suficienti bani.");
            return false;
        }

    }

    abstract String getCharacter();
}
