package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinalPage implements ActionListener {
    // Frame-ul
    JFrame page3;
    // Containerul
    Container content;
    // Elemente
    JLabel title_container;
    JLabel textInformative;
    JLabel textInformative2;
    JLabel charInfo;
    JLabel charInfo2;
    JLabel decoration1;
    JLabel decoration2;
    JLabel level;
    JLabel experience;
    JLabel enemyDefeated;
    JLabel coins;
    JLabel strength;
    JLabel dexterity;
    JLabel charisma;


    JFrame initFrame() {
        JFrame frame = new JFrame();
        frame.setTitle("Statistici caracter");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //- pentru inchiderea programului
        ImageIcon image = new ImageIcon("src\\com\\company\\swing\\logo.png");
        frame.setIconImage(image.getImage());
        frame.setResizable(false);
        // frame.getContentPane().setBackground(new Color()); - pentru a seta culoarea
        return frame;
    }


    void addLabels1() {
        // Container
        content = page3.getContentPane();
        content.setLayout(null);
        if (Grid.curent.current_life > 0) {
            // Titlul
            title_container = new JLabel("Ai terminat jocul!");
            title_container.setFont(new Font("Times New Roman", Font.PLAIN, 32));
            title_container.setSize(400, 35);
            title_container.setLocation(270, 45);

            // Text informativ
            textInformative = new JLabel("Felicitari, " + Game.accounts.get(Game.curentAccount).information.getName() + "!");
            textInformative.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            textInformative.setSize(300, 35);
            textInformative.setLocation(305, 80);
        } else {
            // Titlul
            title_container = new JLabel("Ai murit!");
            title_container.setFont(new Font("Times New Roman", Font.PLAIN, 32));
            title_container.setSize(400, 35);
            title_container.setLocation(290, 45);

            // Text informativ
            textInformative = new JLabel("Ne pare rau, " + Game.accounts.get(Game.curentAccount).information.getName() + "!");
            textInformative.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            textInformative.setSize(300, 35);
            textInformative.setLocation(272, 80);
        }
        content.add(title_container);
        content.add(textInformative);

        // Decoration 1
        decoration1 = new JLabel("========================================");
        decoration1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        decoration1.setSize(450, 35);
        decoration1.setLocation(150, 120);
        content.add(decoration1);

        // Informatii despre caracter
        charInfo = new JLabel("Tipul caracterului curent: " + Grid.curent.getCharacter());
        charInfo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        charInfo.setSize(300, 35);
        charInfo.setLocation(250, 140);
        content.add(charInfo);

        // Informatii despre caracter
        charInfo2 = new JLabel("Numele caracterului curent: " + Grid.curent.name);
        charInfo2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        charInfo2.setSize(300, 35);
        charInfo2.setLocation(250, 160);
        content.add(charInfo2);

        // Decoration 2
        decoration2 = new JLabel("========================================");
        decoration2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        decoration2.setSize(450, 35);
        decoration2.setLocation(150, 180);
        content.add(decoration2);

        // Text informativ 2
        textInformative2 = new JLabel("Ai reusit sa atingi urmatoarele:");
        textInformative2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textInformative2.setSize(300, 35);
        textInformative2.setLocation(250, 215);
        content.add(textInformative2);

        // Decoration 1
        decoration1 = new JLabel("========================================");
        decoration1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        decoration1.setSize(450, 35);
        decoration1.setLocation(150, 245);
        content.add(decoration1);

        // Level
        level = new JLabel("Nivelul curent: " + Grid.curent.level);
        level.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        level.setSize(300, 20);
        level.setLocation(200, 270);
        content.add(level);

        // Experienta
        experience = new JLabel("Experienta pentru nivelul urmator: " + Grid.curent.exp + " / 100");
        experience.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        experience.setSize(350, 20);
        experience.setLocation(200, 295);
        content.add(experience);

        // Atribute Strength
        strength = new JLabel("Strength: " + Grid.curent.strength);
        strength.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        strength.setSize(300, 20);
        strength.setLocation(200, 320);
        content.add(strength);

        // Atribute Dexterity
        dexterity = new JLabel("Dexterity: " + Grid.curent.dexterity);
        dexterity.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        dexterity.setSize(300, 20);
        dexterity.setLocation(200, 345);
        content.add(dexterity);

        // Atribute Charisma
        charisma = new JLabel("Charisma: " + Grid.curent.charisma);
        charisma.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        charisma.setSize(300, 20);
        charisma.setLocation(200, 370);
        content.add(charisma);

        // Numarul de monede
        coins = new JLabel("Monede: " + Grid.curent.inventar.coins);
        coins.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        coins.setSize(300, 20);
        coins.setLocation(200, 395);
        content.add(coins);

        // Numarul de inamici invinsi
        enemyDefeated = new JLabel("Inamici invinsi: " + Game.defeatedEnemies);
        enemyDefeated.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        enemyDefeated.setSize(300, 20);
        enemyDefeated.setLocation(200, 420);
        content.add(enemyDefeated);

        // Decoration 2
        decoration2 = new JLabel("========================================");
        decoration2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        decoration2.setSize(450, 35);
        decoration2.setLocation(150, 435);
        content.add(decoration2);

        content.setVisible(true);
    }

    void runPage() {
        // Pagina de final
        page3 = initFrame();
        addLabels1();
        page3.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
