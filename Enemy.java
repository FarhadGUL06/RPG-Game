package com.company;

import java.util.Random;

public class Enemy extends Entity implements CellElement {
    public Enemy() {
        Random rn = new Random();
        // Life intre 35 si 100
        int life = rn.nextInt(65) + 35;
        // Mana intre 15 si 80
        int mana = rn.nextInt(65) + 15;
        current_life = life;
        current_mana = mana;
        // Am ales protectia random
        int protection = rn.nextInt(3) + 1;
        if (protection == 1) {
            fire = true;
            ice = false;
            earth = false;
        }
        if (protection == 2) {
            fire = false;
            ice = true;
            earth = false;
        }
        if (protection == 3) {
            fire = false;
            ice = false;
            earth = true;
        }
        int nr_spells = rn.nextInt(3) + 2;
        int i, tip_spell;
        for (i = 0; i < nr_spells; i++) {
            tip_spell = rn.nextInt(3) + 1;
            if (tip_spell == 1) {
                Spell abilitate = new Fire();
                abilitati.add(abilitate);
            }
            if (tip_spell == 2) {
                Spell abilitate = new Ice();
                abilitati.add(abilitate);
            } else {
                Spell abilitate = new Earth();
                abilitati.add(abilitate);
            }
        }
    }

    // Functie care sa limiteze damage pana la damagee
    public void receiveDamage(Integer damage) {
        Random rn = new Random();
        int sansa = rn.nextInt(5) + 5;
        // Se genereaza intre 5 si 9 adica maxim se poate scadea 50%
        current_life = current_life - sansa * damage / 10;
    }

    // Functia care calculeaza cat damage da caracterul
    public Integer getDamage() {
        int damage = 2 * damageGiven / 3; // valoare de baza
        damage = damage + Grid.curent.level * 2;
        Random rn = new Random();
        int sansa = rn.nextInt(10) + 11;
        // Se genereaza intre 11 si 20 adica poate sa dea maxim dublu
        damage = sansa * damage / 10;
        return damage;
    }

    @Override
    public void accept(Visitor<Entity> visitor) {
        // Accepta spell entitatea curenta
        visitor.visit(this);
    }

    @Override
    public String toCharacter() {
        return "E";
    }
}
