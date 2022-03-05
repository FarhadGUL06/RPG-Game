package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class AuthPage implements ActionListener {
    // Frame-ul
    JFrame page1;
    // Containerul
    Container content;
    // Pentru titlu
    JLabel title_container;
    // Pentru mail
    JLabel mailText;
    JTextField mailField;
    // Pentru parola
    JLabel passText;
    JPasswordField passField;
    JCheckBox passShow;
    char defaultPassField;
    // Pentru login, inregistrare si reset
    JButton login;
    JButton newAccount;
    JButton reset;

    // Pentru pagina 2
    JLabel textInformative;
    JLabel textInformative2;

    // Pentru inregistrare
    JLabel passTextConfirm;
    JPasswordField passFieldConfirm;
    JLabel nameAccountText;
    JTextField nameAccountField;
    JLabel countryText;
    JComboBox<String> countryField;
    // Inregistrare jocuri preferate
    JLabel gamesText;
    JComboBox<String> gamesField;
    JButton gamesAdd;
    JLabel gamesAddText;
    JTextField gamesAddField;

    // Pentru alegerea caracterului
    JLabel charText;
    JComboBox<Character> charField;
    JComboBox<String> charField2;
    // Pentru submit caracter ales si creare caracter
    JButton submitCharacter;
    JButton addCharacter;

    // Pentru crearea unui nou caracter
    JLabel nameText;
    JTextField nameField;

    JFrame initFrame() {
        JFrame frame = new JFrame();
        frame.setTitle("Pagina de autentificare");
        frame.setSize(800, 600);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); - pentru inchiderea programului
        ImageIcon image = new ImageIcon("src\\com\\company\\swing\\logo.png");
        frame.setIconImage(image.getImage());
        frame.setResizable(false);
        // frame.getContentPane().setBackground(new Color()); - pentru a seta culoarea
        return frame;
    }

    public int loginAuth(String email, String pass) {
        int i;
        email = email.toLowerCase();
        while (true) {
            for (i = 0; i < Game.accounts.size(); i++) {
                Credentials auth2 = Game.accounts.get(i).information.getCredentials();
                if (auth2.getEmail().equals(email)) {
                    // S-a gasit un mail
                    if (auth2.getPassword().equals(pass)) {
                        // Cont corect, autentificare efectuata
                        return i;
                    }
                } else {
                    if (i >= Game.accounts.size() - 1) {
                        return -1;
                    }
                }
            }
        }
    }

    void resetContentFrame() {
        // Stergem elementele
        content.remove(title_container);
        content.remove(mailText);
        content.remove(mailField);
        content.remove(passText);
        content.remove(passField);
        content.remove(passShow);
        content.remove(login);
        content.remove(reset);
        content.remove(newAccount);
        // Curatam contentul
        content.setLayout(new GridLayout());
        content.hide();
    }

    void resetContentFrame2() {
        // Stergem elementele
        content.remove(title_container);
        content.remove(textInformative);
        content.remove(textInformative2);
        content.remove(charText);
        content.remove(charField);
        content.remove(addCharacter);
        content.remove(submitCharacter);
        // Curatam contentul
        content.setLayout(new GridLayout());
        content.hide();
    }

    void resetContentFrame3() {
        // Stergem elementele
        content.remove(title_container);
        content.remove(textInformative);
        content.remove(textInformative2);
        content.remove(charText);
        content.remove(charField2);
        content.remove(nameText);
        content.remove(nameField);
        content.remove(addCharacter);
        content.remove(submitCharacter);
        // Curatam contentul
        content.setLayout(new GridLayout());
        content.hide();
    }

    void resetContentFrame4() {
        // Stergem elementul de confirmare parola
        content.remove(passTextConfirm);
        content.remove(passFieldConfirm);
        // Stergem elementul de nume
        content.remove(nameAccountText);
        content.remove(nameAccountField);
        content.remove(countryText);
        content.remove(countryField);
        content.remove(gamesText);
        content.remove(gamesField);
        content.remove(gamesAdd);
        content.remove(gamesAddText);
        content.remove(gamesAddField);
    }

    void addLabels1(JFrame auth) {
        // Container
        content = auth.getContentPane();
        content.setLayout(null);

        // Titlul
        title_container = new JLabel("Autentificare");
        title_container.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        title_container.setSize(300, 35);
        title_container.setLocation(270, 45);
        content.add(title_container);

        // Text + camp mail
        mailText = new JLabel("Adresa de mail");
        mailText.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        ImageIcon mailImage = new ImageIcon("src\\com\\company\\swing\\mail.png");
        mailText.setIcon(mailImage);
        mailText.setSize(130, 20);
        mailText.setLocation(170, 100);
        content.add(mailText);

        mailField = new JTextField();
        mailField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        mailField.setSize(190, 20);
        mailField.setLocation(310, 100);
        content.add(mailField);

        // Text + camp parola + show pass
        passText = new JLabel("Parola");
        passText.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        ImageIcon passImage = new ImageIcon("src\\com\\company\\swing\\pass.png");
        passText.setIcon(passImage);
        passText.setSize(130, 20);
        passText.setLocation(172, 150);
        content.add(passText);

        passField = new JPasswordField();
        passField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        passField.setSize(190, 20);
        passField.setLocation(310, 150);
        content.add(passField);

        passShow = new JCheckBox("Arata parola");
        passShow.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        passShow.setSize(135, 20);
        passShow.setLocation(300, 180);
        passShow.addActionListener(this);
        content.add(passShow);

        // Reset
        reset = new JButton("Reset");
        reset.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        reset.setSize(80, 20);
        reset.setLocation(420, 210);
        reset.addActionListener(e -> {
            // Resetarea campurilor
            if (e.getSource() == reset) {
                passField.setText("");
                mailField.setText("");
            }
        });
        content.add(reset);

        // Login
        login = new JButton("Login");
        login.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        login.setLocation(310, 250);
        login.setSize(80, 20);
        login.addActionListener(e -> {
            // Trimiterea datelor si verificarea lor
            String mailSubmitted = mailField.getText();
            String passSubmitted = passField.getText();
            int rez = loginAuth(mailSubmitted, passSubmitted);
            if (rez == -1) {
                JOptionPane.showMessageDialog(auth, "Adresa de mail sau parola gresite.");
            } else {
                JOptionPane.showMessageDialog(auth, "Te-ai logat cu succes, " + Game.accounts.get(rez).information.getName() + "!");
                // Setam contul curent
                Game.curentAccount = rez;
                // Acum stergem field-urile de logat
                resetContentFrame();
                // Adaugam field-urile corespunzatoare alegerii eroului
                // A doua parte - alegerea caracterului
                addLabels2(page1, rez);
            }
        });
        content.add(login);

        // Inregistrare
        newAccount = new JButton("Cont nou");
        newAccount.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        newAccount.setSize(100, 20);
        newAccount.setLocation(400, 250);
        newAccount.addActionListener(e -> {
            // Resetarea campurilor
            if (e.getSource() == newAccount) {
                // Mergem pe pagina de inregistrare
                resetContentFrame();
                addLabels4(auth);

            }
        });
        content.add(newAccount);
        content.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Afisare pass cand este apasat butonul
        if (e.getSource() == passShow) {
            if (passShow.isSelected()) {
                defaultPassField = passField.getEchoChar();
                passField.setEchoChar((char) 0);
            } else {
                passField.setEchoChar(defaultPassField);
            }
        }
    }

    void addAccount(String mail, String pass, String name, String country, JComboBox<String> gamesField) {
        Credentials curentCredentials = new Credentials();
        curentCredentials.setEmail(mail);
        curentCredentials.setPassword(pass);

        // Luam jocurile preferate
        ArrayList<String> favorite_games = new ArrayList<>();
        int i;
        for (i = 0; i < gamesField.getItemCount(); i++) {
            favorite_games.add(gamesField.getItemAt(i));
        }

        // Facem un arraylist de caractere
        ArrayList<Character> listCharacters = new ArrayList<>();

        // Construim informatiile pe baza datelor
        Account.Information userInfo = new Account.Information.Builder(curentCredentials, name)
                .Games(favorite_games)
                .Country(country)
                .build();
        // Adaugam user-ul curent in lista de conturi cu 0 jocuri jucate
        Account user = new Account(userInfo, listCharacters, 0);
        Game.accounts.add(user);
    }

    boolean verifMail(String email) {
        int i;
        for (i = 0; i < Game.accounts.size(); i++) {
            if (Game.accounts.get(i).information.getCredentials().getEmail().equals(email)) {
                // Exista deja
                return true;
            }
        }
        // Nu exista mail-ul
        return false;
    }

    boolean verifName(String name) {
        int i;
        for (i = 0; i < Game.accounts.size(); i++) {
            if (Game.accounts.get(i).information.getName().equals(name)) {
                // Exista deja
                return true;
            }
        }
        // Nu exista mail-ul
        return false;
    }

    void addLabels4(JFrame inreg) {
        // Container
        content = inreg.getContentPane();
        content.setLayout(null);

        // Titlul
        title_container = new JLabel("Cont nou");
        title_container.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        title_container.setSize(300, 35);
        title_container.setLocation(300, 45);
        content.add(title_container);

        // Text + camp mail
        nameAccountText = new JLabel("Nume utilizator");
        nameAccountText.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        ImageIcon nameImage = new ImageIcon("src\\com\\company\\swing\\name.png");
        nameAccountText.setIcon(nameImage);
        nameAccountText.setSize(130, 20);
        nameAccountText.setLocation(176, 100);
        content.add(nameAccountText);

        nameAccountField = new JTextField();
        nameAccountField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        nameAccountField.setSize(190, 20);
        nameAccountField.setLocation(310, 100);
        content.add(nameAccountField);

        // Text + camp mail
        countryText = new JLabel("Regiunea");
        countryText.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        ImageIcon countryImage = new ImageIcon("src\\com\\company\\swing\\country.png");
        countryText.setIcon(countryImage);
        countryText.setSize(130, 20);
        countryText.setLocation(175, 150);
        content.add(countryText);
        String[] countryCodes = java.util.Locale.getISOCountries();
        countryField = new JComboBox<>();
        countryField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        countryField.setSize(190, 20);
        countryField.setLocation(310, 150);
        int i;
        Locale locale;
        String countryName;
        for (i = 0; i < countryCodes.length; i++) {
            locale = new Locale("", countryCodes[i]);
            countryName = locale.getDisplayName();
            countryField.addItem(countryName);
        }
        content.add(countryField);

        // Text + camp mail
        mailText = new JLabel("Adresa de mail");
        mailText.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        ImageIcon mailImage = new ImageIcon("src\\com\\company\\swing\\mail.png");
        mailText.setIcon(mailImage);
        mailText.setSize(130, 20);
        mailText.setLocation(170, 200);
        content.add(mailText);

        mailField = new JTextField();
        mailField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        mailField.setSize(190, 20);
        mailField.setLocation(310, 200);
        content.add(mailField);

        // Text + camp parola + show pass
        passText = new JLabel("Parola");
        passText.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        ImageIcon passImage = new ImageIcon("src\\com\\company\\swing\\pass.png");
        passText.setIcon(passImage);
        passText.setSize(130, 20);
        passText.setLocation(172, 250);
        content.add(passText);

        passField = new JPasswordField();
        passField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        passField.setSize(190, 20);
        passField.setLocation(310, 250);
        content.add(passField);

        // Text + camp confirma parola
        passTextConfirm = new JLabel("Confirma parola");
        passTextConfirm.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        passTextConfirm.setIcon(passImage);
        passTextConfirm.setSize(130, 20);
        passTextConfirm.setLocation(172, 300);
        content.add(passTextConfirm);

        passFieldConfirm = new JPasswordField();
        passFieldConfirm.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        passFieldConfirm.setSize(190, 20);
        passFieldConfirm.setLocation(310, 300);
        content.add(passFieldConfirm);

        passShow = new JCheckBox("Arata parola");
        passShow.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        passShow.setSize(135, 20);
        passShow.setLocation(300, 320);
        passShow.addActionListener(e -> {
            if (passShow.isSelected()) {
                //JOptionPane.showMessageDialog(auth,"Parola este " + pass );
                defaultPassField = passField.getEchoChar();
                passField.setEchoChar((char) 0);
                passFieldConfirm.setEchoChar((char) 0);
            } else {
                passField.setEchoChar(defaultPassField);
                passFieldConfirm.setEchoChar(defaultPassField);
            }
        });
        content.add(passShow);

        // Reset
        reset = new JButton("Reset");
        reset.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        reset.setSize(80, 20);
        reset.setLocation(420, 350);
        reset.addActionListener(e -> {
            // Resetarea campurilor
            if (e.getSource() == reset) {
                nameAccountField.setText("");
                passField.setText("");
                mailField.setText("");
                passFieldConfirm.setText("");
            }
        });
        content.add(reset);

        // Jocuri preferate
        gamesText = new JLabel("Jocuri preferate");
        gamesText.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        ImageIcon gamesImage = new ImageIcon("src\\com\\company\\swing\\country.png");
        gamesText.setIcon(gamesImage);
        gamesText.setSize(130, 20);
        gamesText.setLocation(120, 390);
        content.add(gamesText);
        gamesField = new JComboBox<>();
        gamesField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        gamesField.setSize(190, 20);
        gamesField.setLocation(310, 390);
        content.add(gamesField);

        // Adaugare joc preferat
        gamesAddText = new JLabel("Adauga joc");
        gamesAddText.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        gamesAddText.setSize(130, 20);
        gamesAddText.setLocation(170, 420);
        content.add(gamesAddText);

        gamesAddField = new JTextField();
        gamesAddField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        gamesAddField.setSize(190, 20);
        gamesAddField.setLocation(310, 420);
        content.add(gamesAddField);

        // Buton pentru add joc
        gamesAdd = new JButton("Adauga joc");
        gamesAdd.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        gamesAdd.setSize(120, 20);
        gamesAdd.setLocation(310, 450);
        gamesAdd.addActionListener(e -> {
            // Resetarea campurilor
            if (e.getSource() == gamesAdd) {
                // Luam textul de la adaugare
                String game;
                game = gamesAddField.getText();
                if (game.length() > 1) {
                    // Golim inputul
                    gamesAddField.setText("");
                    // Adaugam in lista de jocuri
                    gamesField.addItem(game);
                }
            }
        });
        content.add(gamesAdd);

        // Inregistrare cont nou
        newAccount = new JButton("Inregistreaza-te");
        newAccount.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        newAccount.setLocation(250, 510);
        newAccount.setSize(140, 20);
        newAccount.addActionListener(e -> {
            // Trimiterea datelor si verificarea lor
            String mailSubmitted = mailField.getText();
            // Punem doar caractere mici in mail
            mailSubmitted = mailSubmitted.toLowerCase();
            String passSubmitted = passField.getText();
            String passSubmittedConf = passFieldConfirm.getText();
            if (nameAccountField.getText().length() < 3) {
                JOptionPane.showMessageDialog(inreg, "Numele trebuie sa contina mai mult de 2 caractere.\n");
            } else {
                // Verificam daca numele de utilizator exista deja
                if (verifName(nameAccountField.getText())) {
                    JOptionPane.showMessageDialog(inreg, "Numele introdus exista deja!\n");
                } else {
                    if (mailSubmitted.length() < 3 || (!(mailSubmitted.contains("@")))) {
                        JOptionPane.showMessageDialog(inreg, "Adresa de mail nu este valida!\n");
                    } else {
                        // Verificam daca mail ul este deja folosit
                        if (verifMail(mailSubmitted)) {
                            JOptionPane.showMessageDialog(inreg, "Adresa de mail este deja folosita!\n");
                        } else {
                            if (passSubmitted.length() < 6) {
                                JOptionPane.showMessageDialog(inreg, "Parola trebuie sa contina minim 6 caractere!\n");
                            } else {
                                if (!(passSubmitted.equals(passSubmittedConf))) {
                                    JOptionPane.showMessageDialog(inreg, "Parolele trebuie sa fie identice!");
                                } else {
                                    JOptionPane.showMessageDialog(inreg, "Te-ai inregistrat cu succes, " + nameAccountField.getText() + "!");
                                    addAccount(mailSubmitted, passSubmitted, nameAccountField.getText(), Objects.requireNonNull(countryField.getSelectedItem()).toString(), gamesField);
                                    Game.saveProgressJSON();
                                    resetContentFrame();
                                    resetContentFrame4();
                                    addLabels1(inreg);
                                }
                            }
                        }
                    }
                }
            }
        });
        content.add(newAccount);

        // Inapoi la autentificare
        login = new JButton("Cont existent");
        login.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        login.setSize(120, 20);
        login.setLocation(400, 510);
        login.addActionListener(e -> {
            // Resetarea campurilor
            if (e.getSource() == login) {
                // Mergem pe pagina de inregistrare
                resetContentFrame4();
                resetContentFrame();
                addLabels1(inreg);
            }
        });
        content.add(login);
        content.setVisible(true);
    }

    void addLabels3(JFrame pickChar, int pos) {
        // Container
        content = pickChar.getContentPane();
        content.setLayout(null);

        // Titlul
        title_container = new JLabel("Creeaza un caracter");
        title_container.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        title_container.setSize(300, 35);
        title_container.setLocation(270, 45);
        content.add(title_container);


        // Text informativ
        textInformative = new JLabel("Bine ai venit, " + Game.accounts.get(pos).information.getName() + "!");
        textInformative.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textInformative.setSize(300, 35);
        textInformative.setLocation(305, 80);
        content.add(textInformative);

        // Text informativ 2
        textInformative2 = new JLabel("Regiunea: " + Game.accounts.get(pos).information.getCountry());
        textInformative2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textInformative2.setSize(300, 35);
        textInformative2.setLocation(310, 110);
        content.add(textInformative2);

        // Alegerea caracterului
        charText = new JLabel("Tipuri de caractere: ");
        charText.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        charText.setSize(170, 30);
        charText.setLocation(170, 150);
        content.add(charText);

        charField2 = new JComboBox<>();
        charField2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        charField2.setSize(150, 30);
        charField2.setLocation(310, 150);
        charField2.addItem("Warrior");
        charField2.addItem("Rogue");
        charField2.addItem("Mage");
        content.add(charField2);

        // Numele caracterului
        nameText = new JLabel("Numele caracterului: ");
        nameText.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        nameText.setSize(130, 30);
        nameText.setLocation(170, 190);
        content.add(nameText);

        nameField = new JTextField("Hero");
        nameField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        nameField.setSize(190, 30);
        nameField.setLocation(310, 190);
        content.add(nameField);

        // Trimite alegere sau creaza un nou caracter
        submitCharacter = new JButton("Creeaza caracterul");
        submitCharacter.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        submitCharacter.setSize(170, 30);
        submitCharacter.setLocation(190, 240);
        submitCharacter.addActionListener(e -> {
            // Aici se selecteaza caracterul
            JOptionPane.showMessageDialog(pickChar, "Ati ales: \n" + "Tip caracter: " + charField2.getSelectedItem() + "\n" + "Nume caracter: " + nameField.getText());
            // Cream un caracter de tipul si numele indicat si cu experienta 0 si level 1
            Character characterNew = FactoryCharacter.createCharacter(Objects.requireNonNull(charField2.getSelectedItem()).toString(), nameField.getText(), 0, 1);
            Game.accounts.get(pos).characters.add(characterNew);
            // Ne intoarcem la alegerea caracterului
            resetContentFrame3();
            addLabels2(pickChar, pos);
        });
        content.add(submitCharacter);

        addCharacter = new JButton("Alege unul existent");
        addCharacter.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        addCharacter.setSize(170, 30);
        addCharacter.setLocation(420, 240);
        addCharacter.addActionListener(e -> {
            // Ne intoarcem inapoi
            resetContentFrame3();
            addLabels2(pickChar, pos);
        });
        content.add(addCharacter);

        content.setVisible(true);
    }

    void addLabels2(JFrame pickChar, int pos) {
        // Container
        content = pickChar.getContentPane();
        content.setLayout(null);

        // Titlul
        title_container = new JLabel("Alege un caracter");
        title_container.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        title_container.setSize(300, 35);
        title_container.setLocation(280, 45);
        content.add(title_container);


        // Text informativ
        textInformative = new JLabel("Bine ai venit, " + Game.accounts.get(pos).information.getName() + "!");
        textInformative.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textInformative.setSize(300, 35);
        textInformative.setLocation(305, 80);
        content.add(textInformative);

        // Text informativ 2
        textInformative2 = new JLabel("Regiunea: " + Game.accounts.get(pos).information.getCountry());
        textInformative2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textInformative2.setSize(300, 35);
        textInformative2.setLocation(310, 110);
        content.add(textInformative2);

        // Alegerea caracterului
        charText = new JLabel("Caractere disponibile: ");
        charText.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        charText.setSize(170, 30);
        charText.setLocation(50, 150);
        content.add(charText);

        charField = new JComboBox<>();
        charField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        charField.setSize(500, 30);
        charField.setLocation(190, 150);
        int i;
        for (i = 0; i < Game.accounts.get(pos).characters.size(); i++) {
            charField.addItem(Game.accounts.get(pos).characters.get(i));
        }
        content.add(charField);

        // Trimite alegere sau creaza un nou caracter
        submitCharacter = new JButton("Trimite alegerea");
        submitCharacter.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        submitCharacter.setSize(170, 30);
        submitCharacter.setLocation(190, 210);
        submitCharacter.addActionListener(e -> {
            // Aici se selecteaza caracterul
            JOptionPane.showMessageDialog(pickChar, "Ati ales: \n" + charField.getSelectedIndex() + ". " + charField.getSelectedItem());
            Grid.curent = Game.accounts.get(pos).characters.get(charField.getSelectedIndex());
            // Inchidem fereastra
            pickChar.setVisible(false);
            Grid.curent.startCharacter();
            // Apelam in ce mod se doreste jocul
            Game.gameMethod();
        });
        content.add(submitCharacter);


        addCharacter = new JButton("Creeaza caracter nou");
        addCharacter.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        addCharacter.setSize(170, 30);
        addCharacter.setLocation(420, 210);
        addCharacter.addActionListener(e -> {
            // Acum stergem field-urile
            resetContentFrame2();
            addLabels3(pickChar, pos);
        });
        content.add(addCharacter);

        content.setVisible(true);
    }

    void runPage() {
        page1 = initFrame();
        // Prima parte - autentificarea
        addLabels1(page1);
        page1.setVisible(true);
    }
}
