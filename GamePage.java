package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class GamePage implements ActionListener {
    // Frame-ul
    JFrame page2;
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
    JLabel currentLife;
    JLabel currentMana;
    JLabel level;
    JLabel experience;
    JLabel enemyDefeated;
    JLabel coins;
    JLabel strength;
    JLabel dexterity;
    JLabel charisma;
    JPanel map;
    JButton cellMap;
    // Controale
    JButton moveUp;
    JButton moveDown;
    JButton moveLeft;
    JButton moveRight;
    // Povesti
    JLabel storyText;
    // Optiuni
    JLabel typeCell;


    JFrame initFrame() {
        JFrame frame = new JFrame();
        frame.setTitle("Statistici caracter");
        frame.setSize(1600, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //- pentru inchiderea programului
        ImageIcon image = new ImageIcon("src\\com\\company\\swing\\logo.png");
        frame.setIconImage(image.getImage());
        frame.setResizable(false);
        // frame.getContentPane().setBackground(new Color()); - pentru a seta culoarea
        return frame;
    }

    void resetContentFrame() {
        // Stergem elementele
        content.remove(title_container);
        content.remove(textInformative);
        content.remove(decoration1);
        content.remove(charInfo);
        content.remove(charInfo2);
        content.remove(decoration2);
        content.remove(textInformative2);
        content.remove(decoration1);
        content.remove(currentLife);
        content.remove(currentMana);
        content.remove(level);
        content.remove(experience);
        content.remove(strength);
        content.remove(dexterity);
        content.remove(charisma);
        content.remove(coins);
        content.remove(enemyDefeated);
        content.remove(decoration2);
        content.remove(typeCell);
        // Controale
        content.remove(moveUp);
        content.remove(moveDown);
        content.remove(moveLeft);
        content.remove(moveRight);
        content.remove(map);
        // Curatam harta
        int i;
        for (i = 0; i < map.getComponentCount(); i++) {
            map.remove(i);
        }
        // Curatam povestea
        content.remove(storyText);
        // Curatam contentul
        content.setLayout(new GridLayout());
        content.hide();
    }

    void addLabels1() {
        // Container
        content = page2.getContentPane();
        content.setLayout(null);

        // Titlul
        title_container = new JLabel("World of Marcel");
        title_container.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        title_container.setSize(400, 35);
        title_container.setLocation(270, 45);
        content.add(title_container);

        // Text informativ
        textInformative = new JLabel("Succes, " + Game.accounts.get(Game.curentAccount).information.getName() + "!");
        textInformative.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textInformative.setSize(300, 35);
        textInformative.setLocation(305, 80);
        content.add(textInformative);

        // Decoration 1
        decoration1 = new JLabel("==============================");
        decoration1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        decoration1.setSize(450, 35);
        decoration1.setLocation(10, 120);
        content.add(decoration1);

        // Informatii despre caracter
        charInfo = new JLabel("Tipul caracterului curent: " + Grid.curent.getCharacter());
        charInfo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        charInfo.setSize(300, 35);
        charInfo.setLocation(30, 140);
        content.add(charInfo);

        // Informatii despre caracter
        charInfo2 = new JLabel("Numele caracterului curent: " + Grid.curent.name);
        charInfo2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        charInfo2.setSize(300, 35);
        charInfo2.setLocation(30, 160);
        content.add(charInfo2);

        // Decoration 2
        decoration2 = new JLabel("==============================");
        decoration2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        decoration2.setSize(450, 35);
        decoration2.setLocation(10, 180);
        content.add(decoration2);

        // Text informativ 2
        textInformative2 = new JLabel("Statistici curente:");
        textInformative2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        textInformative2.setSize(300, 35);
        textInformative2.setLocation(75, 215);
        content.add(textInformative2);

        // Decoration 1
        decoration1 = new JLabel("==============================");
        decoration1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        decoration1.setSize(450, 35);
        decoration1.setLocation(10, 245);
        content.add(decoration1);

        // Viata caracterului
        currentLife = new JLabel("Viata curenta: " + Grid.curent.current_life + " / 100");
        currentLife.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        currentLife.setSize(300, 20);
        currentLife.setLocation(30, 270);
        content.add(currentLife);

        // Mana caracterului
        currentMana = new JLabel("Mana curenta: " + Grid.curent.current_mana + " / 100");
        currentMana.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        currentMana.setSize(350, 20);
        currentMana.setLocation(30, 295);
        content.add(currentMana);

        // Level
        level = new JLabel("Nivelul curent: " + Grid.curent.level);
        level.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        level.setSize(300, 20);
        level.setLocation(30, 320);
        content.add(level);

        // Experienta
        experience = new JLabel("Experienta pentru nivelul urmator: " + Grid.curent.exp + " / 100");
        experience.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        experience.setSize(350, 20);
        experience.setLocation(30, 345);
        content.add(experience);

        // Atribute Strength
        strength = new JLabel("Strength: " + Grid.curent.strength);
        strength.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        strength.setSize(300, 20);
        strength.setLocation(30, 370);
        content.add(strength);

        // Atribute Dexterity
        dexterity = new JLabel("Dexterity: " + Grid.curent.dexterity);
        dexterity.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        dexterity.setSize(300, 20);
        dexterity.setLocation(30, 395);
        content.add(dexterity);

        // Atribute Charisma
        charisma = new JLabel("Charisma: " + Grid.curent.charisma);
        charisma.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        charisma.setSize(300, 20);
        charisma.setLocation(30, 420);
        content.add(charisma);

        // Numarul de monede
        coins = new JLabel("Monede: " + Grid.curent.inventar.coins);
        coins.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        coins.setSize(300, 20);
        coins.setLocation(30, 445);
        content.add(coins);

        // Numarul de inamici invinsi
        enemyDefeated = new JLabel("Inamici invinsi: " + Game.defeatedEnemies);
        enemyDefeated.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        enemyDefeated.setSize(300, 20);
        enemyDefeated.setLocation(30, 470);
        content.add(enemyDefeated);

        // Decoration 2
        decoration2 = new JLabel("==============================");
        decoration2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        decoration2.setSize(450, 35);
        decoration2.setLocation(10, 485);
        content.add(decoration2);

        // Tipul celulei
        typeCell = new JLabel(Grid.curent_cell.tip_element.toString());
        typeCell.setFont(new Font("Times New Roman", Font.BOLD, 20));
        typeCell.setSize(300, 35);
        typeCell.setLocation(1170, 200);
        content.add(typeCell);

        moveUp = new JButton("UP");
        moveUp.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        moveUp.setSize(90, 90);
        moveUp.setLocation(655, 550);
        // Verificam daca se poate deplasa in sus
        if (Grid.curent_cell.Ox == 0) {
            moveUp.setVisible(false);
        } else {
            moveUp.addActionListener(e -> {
                // Mergem in sus
                Grid.curent_cell = Grid.map.get(Grid.curent_cell.Ox - 1).get(Grid.curent_cell.Oy);
                Game.getOptions(Grid.curent_cell);
                Grid.giveCoins();
                Grid.curent_cell.vizitat = true;
                resetContentFrame();
                updateMap();
                if (Grid.curent_cell.tip_element.equals(CellEnum.FINISH)) {
                    Game.saveProgressJSON();
                    page2.setVisible(false);
                    // Afisam pagina de final
                    FinalPage finalPage = new FinalPage();
                    finalPage.runPage();
                } else {
                    addLabels1();
                }
            });
        }
        content.add(moveUp);

        moveDown = new JButton("DOWN");
        moveDown.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        moveDown.setSize(90, 90);
        moveDown.setLocation(655, 690);
        // Verificam daca se poate deplasa in jos
        if (Grid.curent_cell.Ox == Grid.getLinii() - 1) {
            moveDown.setVisible(false);
        } else {
            moveDown.addActionListener(e -> {
                // Ne mutam in jos
                Grid.curent_cell = Grid.map.get(Grid.curent_cell.Ox + 1).get(Grid.curent_cell.Oy);
                Game.getOptions(Grid.curent_cell);
                Grid.giveCoins();
                Grid.curent_cell.vizitat = true;
                resetContentFrame();
                updateMap();
                if (Grid.curent_cell.tip_element.equals(CellEnum.FINISH)) {
                    Game.saveProgressJSON();
                    page2.setVisible(false);
                    // Afisam pagina de final
                    FinalPage finalPage = new FinalPage();
                    finalPage.runPage();
                } else {
                    addLabels1();
                }

            });
        }
        content.add(moveDown);

        moveLeft = new JButton("LEFT");
        moveLeft.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        moveLeft.setSize(90, 90);
        moveLeft.setLocation(555, 620);
        if (Grid.curent_cell.Oy == 0) {
            moveLeft.setVisible(false);
        } else {
            moveLeft.addActionListener(e -> {
                // Mergem la stanga
                Grid.curent_cell = Grid.map.get(Grid.curent_cell.Ox).get(Grid.curent_cell.Oy - 1);
                Game.getOptions(Grid.curent_cell);
                Grid.giveCoins();
                Grid.curent_cell.vizitat = true;
                resetContentFrame();
                updateMap();
                if (Grid.curent_cell.tip_element.equals(CellEnum.FINISH)) {
                    Game.saveProgressJSON();
                    page2.setVisible(false);
                    // Afisam pagina de final
                    FinalPage finalPage = new FinalPage();
                    finalPage.runPage();
                } else {
                    addLabels1();
                }

            });
        }
        content.add(moveLeft);

        moveRight = new JButton("RIGHT");
        moveRight.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        moveRight.setSize(90, 90);
        moveRight.setLocation(755, 620);
        if (Grid.curent_cell.Oy == Grid.getColoane() - 1) {
            moveRight.setVisible(false);
        } else {
            moveRight.addActionListener(e -> {
                // Mergem la dreapta
                Grid.curent_cell = Grid.map.get(Grid.curent_cell.Ox).get(Grid.curent_cell.Oy + 1);
                resetContentFrame();
                updateMap();
                Game.getOptions(Grid.curent_cell);
                Grid.giveCoins();
                Grid.curent_cell.vizitat = true;
                if (Grid.curent_cell.tip_element.equals(CellEnum.FINISH)) {
                    Game.saveProgressJSON();
                    page2.setVisible(false);
                    // Afisam pagina de final
                    FinalPage finalPage = new FinalPage();
                    finalPage.runPage();
                } else {
                    addLabels1();
                }
            });
        }
        content.add(moveRight);

        content.add(map);

        // Decoration 1
        decoration1 = new JLabel("===========================================================");
        decoration1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        decoration1.setSize(600, 35);
        decoration1.setLocation(920, 120);
        content.add(decoration1);

        // Informatii despre caracter
        Random rn = new Random();
        ArrayList<String> listStories = Game.stories.get(Grid.curent_cell.tip_element);
        int poz = rn.nextInt(listStories.size());
        String story = listStories.get(poz);
        Grid.curent_cell.story = story;
        //listStories.remove(poz);
        Game.stories.put(Grid.curent_cell.tip_element, listStories);
        storyText = new JLabel("Story: " + story);
        storyText.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        storyText.setSize(600, 35);
        storyText.setLocation(950, 140);
        content.add(storyText);

        // Decoration 2
        decoration2 = new JLabel("===========================================================");
        decoration2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        decoration2.setSize(600, 35);
        decoration2.setLocation(920, 160);
        content.add(decoration2);


        content.setVisible(true);
    }

    void updateMap() {
        map = new JPanel();
        map.setLayout(new GridLayout(Grid.getLinii(), Grid.getColoane()));
        map.setSize(400, 400);
        map.setLocation(500, 130);
        int i;
        int j;
        for (i = 0; i < Grid.getLinii(); i++) {
            for (j = 0; j < Grid.getColoane(); j++) {
                if (Grid.curent_cell.Ox == i && Grid.curent_cell.Oy == j) {
                    cellMap = new JButton();
                    ImageIcon heroImage = new ImageIcon("src\\com\\company\\swing\\hero.png");
                    cellMap.setIcon(heroImage);
                } else {
                    // Extragem ce avem pe pozitie si plasam in map
                    Cell cellCurrent = Grid.map.get(i).get(j);
                    if (cellCurrent.vizitat) {
                        if (cellCurrent.tip_element.equals(CellEnum.SHOP)) {
                            cellMap = new JButton();
                            ImageIcon shopImage = new ImageIcon("src\\com\\company\\swing\\shop.png");
                            cellMap.setIcon(shopImage);
                        }
                        if (cellCurrent.tip_element.equals(CellEnum.ENEMY)) {
                            cellMap = new JButton();
                            ImageIcon enemyImage = new ImageIcon("src\\com\\company\\swing\\enemy.png");
                            cellMap.setIcon(enemyImage);
                        }
                        if (cellCurrent.tip_element.equals(CellEnum.EMPTY)) {
                            cellMap = new JButton();
                        }
                    } else {
                        cellMap = new JButton("" + cellCurrent);
                    }
                }
                cellMap.setSize(20, 20);
                map.add(cellMap);
            }
        }
    }

    void runPage() {
        // Pagina de final
        page2 = initFrame();
        updateMap();
        addLabels1();
        page2.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
