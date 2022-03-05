package com.company;

public abstract class Spell implements Visitor<Entity> {
    Integer damage;
    Integer manaCost;

    public Spell(Integer damage, Integer manaCost) {
        this.damage = damage;
        this.manaCost = manaCost;
    }
}
