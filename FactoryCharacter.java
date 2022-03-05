package com.company;

public class FactoryCharacter {
    public static Character createCharacter(String Type, String name, Integer experience, Integer level) {
        if (Type == null) {
            return null;
        }
        if (Type.equals("Warrior")) {
            return new Warrior(name, experience, level);
        }
        if (Type.equals("Rogue")) {
            return new Rogue(name, experience, level);
        }
        if (Type.equals("Mage")) {
            return new Mage(name, experience, level);
        }
        System.out.println("Caracterul nu exista.");
        return null;
    }
}
