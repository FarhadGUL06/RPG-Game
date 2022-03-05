package com.company;

public class Fire extends Spell {
    public Fire() {
        super(25, 20);
    }

    @Override
    public void visit(Entity e) {
        // Aplicam potiunea pe entitatea e
        Fire spell = new Fire();
        if (!e.fire) {
            System.out.println("Abilitatea fire a fost folosita.");
            e.useSpell(spell, e);
        } else {
            System.out.println("Entitatea are protectie impotriva abilitatii fire.");
        }
    }

    @Override
    public String toString() {
        return "Fire -> " + "Damage=" + damage + ", Mana Cost=" + manaCost;
    }
}
