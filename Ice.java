package com.company;

public class Ice extends Spell {
    public Ice() {
        super(20, 24);
    }

    @Override
    public void visit(Entity e) {
        // Aplicam potiunea pe entitatea e
        Ice spell = new Ice();
        if (!e.ice) {
            System.out.println("Abilitatea ice a fost folosita.");
            e.useSpell(spell, e);
        } else {
            System.out.println("Entitatea are protectie impotriva abilitatii ice.");
        }
    }

    @Override
    public String toString() {
        return "Ice -> " + "Damage=" + damage + ", Mana Cost=" + manaCost;
    }
}
