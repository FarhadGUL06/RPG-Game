package com.company;

public class Rogue extends Character {
    Rogue(String name, Integer experience, Integer level) {
        this.name = name;
        this.exp = experience;
        this.level = level;
        earth = true;
        ice = false;
        fire = false;
        inventar = new Inventory(4, 250);
        strength = 2;
        dexterity = 10;
        charisma = 2;
    }

    public void receiveDamage(Integer damage) {
        int sansa = 0;
        if ((strength + charisma) / 4 >= damage / 2) {
            damage = damage * 9 / 10;
        } else {
            damage = damage - (strength + charisma) / 4;
        }
        if (strength > dexterity / 2) {
            sansa++;
        }
        if (strength >= 10) {
            sansa++;
        }
        if (charisma > dexterity / 2) {
            sansa++;
        }
        if (charisma >= 10) {
            sansa++;
        }
        if (strength >= 20 || charisma >= 20) {
            sansa = sansa + 2;
        }
        if (sansa < 2) {
            current_life = current_life - damage;
        } else {
            if (sansa < 4) {
                current_life = current_life - 3 * damage / 4;
            } else {
                current_life = current_life - damage / 2;
            }
        }
    }

    // Functia care calculeaza cat damage da caracterul
    public Integer getDamage() {
        int damage = damageGiven + 5 + dexterity / 3; // valoare de baza
        // Level 1 - damage standard
        if (dexterity < 14) {
            return damage;
        }
        // Intre level 2 si 3 - 1.2 * damage
        if (dexterity < 20) {
            damage = 6 * damage / 5;
            return damage;
        }
        // Level 4 - 1.6 * damage
        if (dexterity < 24) {
            damage = 8 * damage / 5;
            return damage;
        }
        // > Level 5 - 2 * damage
        damage = 2 * damage;
        return damage;
    }

    @Override
    public void accept(Visitor<Entity> visitor) {
        // Accepta spellul entitatea curenta
        visitor.visit(this);
    }

    @Override
    String getCharacter() {
        return "Rogue";
    }

    @Override
    public String toString() {
        String print = "(Rogue -> ";
        print = print + " " + "Nume: " + name + " | Experience: " + exp + " | Level: " + level + ")";
        return print;
    }
}
