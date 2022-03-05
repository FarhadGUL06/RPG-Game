package com.company;

public class Mage extends Character {
    Mage(String name, Integer experience, Integer level) {
        this.name = name;
        this.exp = experience;
        this.level = level;
        ice = true;
        fire = false;
        earth = false;
        inventar = new Inventory(2, 250);
        strength = 2;
        dexterity = 2;
        charisma = 10;
    }

    public void receiveDamage(Integer damage) {
        int sansa = 0;
        if ((dexterity + strength) / 4 >= damage / 2) {
            damage = damage * 9 / 10;
        } else {
            damage = damage - (dexterity + strength) / 4;
        }
        if (dexterity > charisma / 2) {
            sansa++;
        }
        if (dexterity >= 10) {
            sansa++;
        }
        if (strength > charisma / 2) {
            sansa++;
        }
        if (strength >= 10) {
            sansa++;
        }
        if (strength >= 20 || dexterity >= 20) {
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
        int damage = damageGiven + 5 + charisma / 3; // valoare de baza
        // Level 1 - damage standard
        if (charisma < 14) {
            return damage;
        }
        // Intre level 2 si 3 - 1.2 * damage
        if (charisma < 20) {
            damage = 6 * damage / 5;
            return damage;
        }
        // Level 4 - 1.6 * damage
        if (charisma < 24) {
            damage = 8 * damage / 5;
            return damage;
        }
        // > Level 5 - 2 * damage
        damage = 2 * damage;
        return damage;
    }

    @Override
    String getCharacter() {
        return "Mage";
    }

    @Override
    public void accept(Visitor<Entity> visitor) {
        // Accepta spellul entitatea curenta
        visitor.visit(this);
    }

    @Override
    public String toString() {
        String print = "(Mage -> ";
        print = print + " " + "Nume: " + name + " | Experience: " + exp + " | Level: " + level + ")";
        return print;
    }
}
