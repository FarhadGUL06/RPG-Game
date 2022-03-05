package com.company;

import java.util.ArrayList;

public abstract class Entity implements Element<Entity> {
    ArrayList<Spell> abilitati = new ArrayList<>();
    Integer current_life, current_mana;
    Integer max_life = 100, max_mana = 100;
    Boolean fire;
    Boolean ice;
    Boolean earth;

    // Valoare fixa pentru damage dat
    final Integer damageGiven = 10;

    // Functia de regenerare viata
    public void regenerateLife(Integer newLife) {
        if (newLife >= max_life) {
            current_life = max_life;
        } else {
            current_life = newLife;
        }
    }

    // Functia de regenerare mana
    public void regenerateMana(Integer newMana) {
        if (newMana >= max_mana) {
            current_mana = max_mana;
        } else {
            current_mana = newMana;
        }
    }

    // Functia pentru folosirea unei abilitati
    public void useSpell(Spell abilitate, Entity inamic) {
        // Aplicam deja damage-ul
        inamic.current_life = inamic.current_life - abilitate.damage;
    }

    // Inregistrare pierdere de viata
    public abstract void receiveDamage(Integer damage);

    // Calcul damage
    public abstract Integer getDamage();
}
