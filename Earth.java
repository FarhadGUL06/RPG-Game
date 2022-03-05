package com.company;

public class Earth extends Spell {
    public Earth() {
        super(15, 20);
    }

    @Override
    public void visit(Entity e) {
        // Aplicam potiunea pe entitatea e
        Earth spell = new Earth();
        if (!e.earth) {
            System.out.println("Abilitatea earth a fost folosita.");
            e.useSpell(spell, e);
        } else {
            System.out.println("Entitatea are protectie impotriva abilitatii earth.");
        }
    }

    @Override
    public String toString() {
        return "Earth -> " + "Damage=" + damage + ", Mana Cost=" + manaCost;
    }
}
