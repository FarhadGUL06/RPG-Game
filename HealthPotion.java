package com.company;

public class HealthPotion implements Potion {
    private final Integer price;
    private final Integer weight;
    private final Integer regenValue;

    HealthPotion(Integer price, Integer weight, Integer regenValue) {
        this.price = price;
        this.weight = weight;
        this.regenValue = regenValue;
    }

    @Override
    public int usePotion(Character current) {
        int regen = this.getRegenValue();
        if (current.current_life == current.max_life) {
            System.out.println("Aveti deja maximul de viata disponibil.");
            return 0;
        }
        current.regenerateLife(current.current_life + regen);
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
        return "HealthPotion -> " + "Price=" + price + ", Weight=" + weight + ", RegenValue=" + regenValue;
    }
}
