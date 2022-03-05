package com.company;

public class ManaPotion implements Potion {
    private final Integer price;
    private final Integer weight;
    private final Integer regenValue;

    ManaPotion(Integer price, Integer weight, Integer regenValue) {
        this.price = price;
        this.weight = weight;
        this.regenValue = regenValue;
    }

    @Override
    public int usePotion(Character current) {
        int regen = this.getRegenValue();
        if (current.current_mana == current.max_mana) {
            System.out.println("Aveti deja maximul de mana disponibil.");
            return 0;
        }
        current.regenerateMana(current.current_mana + regen);
        return 1;
    }

    @Override
    public Integer getPricePotion() {
        return this.price;
    }

    @Override
    public Integer getRegenValue() {
        return this.regenValue;
    }

    @Override
    public Integer getWeight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return "ManaPotion -> " + "Price=" + price + ", Weight=" + weight + ", RegenValue=" + regenValue;
    }
}
