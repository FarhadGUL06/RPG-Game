package com.company;

public class Warrior extends Character {
    Warrior(String name, Integer experience, Integer level) {
        this.name = name;
        this.exp = experience;
        this.level = level;
        fire = true;
        ice = false;
        earth = false;
        inventar = new Inventory(6, 250);
        strength = 10;
        dexterity = 2;
        charisma = 2;
    }

    // Functie care sa limiteze damage pana la damagee
    public void receiveDamage(Integer damage) {
        int sansa = 0;
        if ((dexterity + charisma) / 4 >= damage / 2) {
            damage = damage * 9 / 10;
        } else {
            damage = damage - (dexterity + charisma) / 4;
        }
        if (dexterity > strength / 2) {
            sansa++;
        }
        if (dexterity >= 10) {
            sansa++;
        }
        if (charisma > strength / 2) {
            sansa++;
        }
        if (charisma >= 10) {
            sansa++;
        }
        if (dexterity >= 20 || charisma >= 20) {
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
        int damage = damageGiven + 5 + strength / 3; // valoare de baza
        // Level 1 - damage standard
        if (strength < 14) {
            return damage;
        }
        // Intre level 2 si 3 - 1.2 * damage
        if (strength < 20) {
            damage = 6 * damage / 5;
            return damage;
        }
        // Level 4 - 1.6 * damage
        if (strength < 24) {
            damage = 8 * damage / 5;
            return damage;
        }
        // > Level 5 - 2 * damage
        damage = 2 * damage;
        return damage;
    }

    @Override
    String getCharacter() {
        return "Warrior";
    }

    @Override
    public void accept(Visitor<Entity> visitor) {
        // Accepta spellul entitatea curenta
        visitor.visit(this);
    }

    @Override
    public String toString() {
        String print = "(Warrior -> ";
        print = print + " " + "Nume: " + name + " | Experience: " + exp + " | Level: " + level + ")";
        return print;
    }
}
