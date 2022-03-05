package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Game {
    private static Game joc = null;
    static ArrayList<Account> accounts = new ArrayList<>();
    static HashMap<CellEnum, ArrayList<String>> stories = new HashMap<>();
    static int curentAccount; // pentru a creste numarul de jocuri jucate
    static int defeatedEnemies = 0;

    private Game() {
        // Adaugam cate o lista de stringuri la fiecare cheie
        ArrayList<String> empty = new ArrayList<>();
        stories.put(CellEnum.EMPTY, empty);
        ArrayList<String> enemy = new ArrayList<>();
        stories.put(CellEnum.ENEMY, enemy);
        ArrayList<String> shop = new ArrayList<>();
        stories.put(CellEnum.SHOP, shop);
        ArrayList<String> finish = new ArrayList<>();
        stories.put(CellEnum.FINISH, finish);
    }

    public static Game getInstance() {
        if (joc == null) {
            joc = new Game();
        }
        return joc;
    }

    public static void typeStories(JSONObject story) {
        // Luam tipul
        String type = (String) story.get("type");
        //System.out.println(type);

        // Luam povestea
        String value = (String) story.get("value");
        //System.out.println(value);
        ArrayList curent;
        // Adaugam in HashMap
        if (type.equals("EMPTY")) {
            //povesti.put(CellEnum.EMPTY,value);
            curent = stories.get(CellEnum.EMPTY);
            curent.add(value);
            stories.put(CellEnum.EMPTY, curent);
        }
        if (type.equals("ENEMY")) {
            //povesti.put(CellEnum.ENEMY, value);
            curent = stories.get(CellEnum.ENEMY);
            curent.add(value);
            stories.put(CellEnum.ENEMY, curent);
        }
        if (type.equals("SHOP")) {
            //povesti.put(CellEnum.SHOP, value);
            curent = stories.get(CellEnum.SHOP);
            curent.add(value);
            stories.put(CellEnum.SHOP, curent);
        }
        if (type.equals("FINISH")) {
            // povesti.put(CellEnum.FINISH, value);
            curent = stories.get(CellEnum.FINISH);
            curent.add(value);
            stories.put(CellEnum.FINISH, curent);
        }
    }

    public static void storiesJSON() {
        JSONArray stringStories;
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("src\\com\\company\\json\\stories.json")) {
            // Citim fisierul
            Object obj = jsonParser.parse(reader);
            // Castam la JSONArray
            stringStories = (JSONArray) obj;
            // Parcurgem fiecare pereche
            stringStories.forEach(pov -> typeStories((JSONObject) pov));

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

    }

    public static void typeAccounts(JSONObject account) {

        // Luam credentialele
        JSONObject credentials = (JSONObject) account.get("credentials");
        Credentials curentCredentials = new Credentials();
        if (credentials != null) {
            // Preluam mail-ul si parola
            // Campurile sunt obligatorii
            String email = (String) credentials.get("email");
            String password = (String) credentials.get("password");
            curentCredentials.setEmail(email);
            curentCredentials.setPassword(password);
        }

        // Luam numele
        String name = (String) account.get("name");

        // Luam tara
        String country = (String) account.get("country");

        // Luam jocurile preferate
        ArrayList<String> favorite_games = (ArrayList<String>) account.get("favorite_games");

        // Luam nr de jocuri jucate
        String maps_completed_s = (String) account.get("maps_completed");
        int maps_completed = Integer.parseInt(maps_completed_s);
        ArrayList<JSONObject> characters = (ArrayList<JSONObject>) account.get("characters");
        // Facem un arraylist de caractere
        ArrayList<Character> listCharacters = new ArrayList<>();
        // Trecem prin fiecare caracter si il retinem
        int i;
        for (i = 0; i < characters.size(); i++) {
            // characters.get(i) - caracter curent
            JSONObject character = characters.get(i);
            String name_c = (String) character.get("name");
            String profession = (String) character.get("profession");
            String level_s = (String) character.get("level");
            int level = Integer.parseInt(level_s);
            Long experience_l = (Long) character.get("experience");
            int experience = experience_l.intValue();
            Character characterCurent = FactoryCharacter.createCharacter(profession, name_c, experience, level);
            listCharacters.add(characterCurent);
        }
        // Construim informatiile pe baza datelor
        Account.Information userInfo = new Account.Information.Builder(curentCredentials, name)
                .Games(favorite_games)
                .Country(country)
                .build();
        // Adaugam user-ul curent in lista de conturi
        Account user = new Account(userInfo, listCharacters, maps_completed);
        accounts.add(user);
    }

    public static void accountsJSON() {
        JSONArray stringAccounts;
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("src\\com\\company\\json\\accounts.json")) {
            // Citim fisierul
            Object obj = jsonParser.parse(reader);
            // Castam la JSONArray
            stringAccounts = (JSONArray) obj;
            // Parcurgem fiecare pereche
            stringAccounts.forEach(account -> typeAccounts((JSONObject) account));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveProgressJSON() {
        //First Employee
        int sizeAccounts = accounts.size();
        int i;
        JSONArray FileJSON = new JSONArray();
        for (i = 0; i < sizeAccounts; i++) {
            // Generam obiectul in care punem un cont
            Map FileObjectJSON = new LinkedHashMap();
            // Punem credentialele
            Map credentials = new LinkedHashMap();
            // Preluam credentialele si le adaugam
            credentials.put("email", accounts.get(i).information.getCredentials().getEmail());
            credentials.put("password", accounts.get(i).information.getCredentials().getPassword());
            FileObjectJSON.put("credentials", credentials);
            // Punem numele
            FileObjectJSON.put("name", accounts.get(i).information.getName());

            // Punem tara
            FileObjectJSON.put("country", accounts.get(i).information.getCountry());

            // Punem jocurile preferate
            FileObjectJSON.put("favorite_games", accounts.get(i).information.getGames());

            // Punem nr de jocuri jucate
            FileObjectJSON.put("maps_completed", accounts.get(i).maps_completed.toString());

            // Facem un array de caractere
            JSONArray listofCharacters = new JSONArray();
            // Pastram numarul de caractere
            int nrCharacters = accounts.get(i).characters.size();
            // Parcurgem fiecare caracter si construim un obiect
            int j;
            for (j = 0; j < nrCharacters; j++) {
                //JSONObject currentCharacter = new JSONObject();
                Map currentCharacter = new LinkedHashMap();
                currentCharacter.put("name", accounts.get(i).characters.get(j).name);
                currentCharacter.put("profession", accounts.get(i).characters.get(j).getClass().getSimpleName());
                currentCharacter.put("level", accounts.get(i).characters.get(j).level.toString());
                currentCharacter.put("experience", accounts.get(i).characters.get(j).exp);
                // Adaugam caracterul in array-ul de caractere
                listofCharacters.add(currentCharacter);
            }
            // Punem array-ul de caractere
            FileObjectJSON.put("characters", listofCharacters);
            FileJSON.add(FileObjectJSON);
        }

        try (FileWriter file = new FileWriter("src\\com\\company\\json\\accounts.json")) {
            // Scriem in fisier
            file.write(FileJSON.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getStory(Cell current) {
        if (!current.vizitat) {
            Random rn = new Random();
            ArrayList<String> listStories = stories.get(current.tip_element);
            int poz = rn.nextInt(listStories.size());
            String story = listStories.get(poz);
            current.story = story;
            // listStories.remove(poz);
            stories.put(current.tip_element, listStories);
            System.out.println(story);
        }
    }

    public static void buyShop(Shop shop) {
        Scanner potionBuy = new Scanner(System.in);
        // Doreste sa cumpere o potiune
        System.out.println("Introduceti indicele potiunii.");
        int poz;
        try {
            poz = potionBuy.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Introduceti un numar valid!");
            buyShop(shop);
            return;
        }
        if (poz <= shop.potions.size() && poz > 0) {
            if (Grid.curent.buyPotion(shop.potions.get(poz - 1))) {
                shop.getPotion(poz - 1);
                askBuyShop(shop);
            }
        } else {
            // A introdus un numar invalid
            try {
                throw new InvalidCommandException("Raspuns invalid. Reintroduceti raspunsul.");
            } catch (InvalidCommandException e) {
                System.out.println(e);
                buyShop(shop);
            }
        }
    }

    public static void askBuyShop(Shop shop) {
        Scanner askPotionBuy = new Scanner(System.in);
        int response;
        System.out.println("Doriti sa cumparati potiuni? (1 - DA / 0 - NU)");
        try {
            response = askPotionBuy.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Nu ati introdus un raspuns valid deci voi considera ca nu doresti sa cumperi!");
            return;
        }
        if (response == 0) {
            System.out.println("Nu doresti sa cumperi. Te asteptam cand te razgandesti!");
            return;
        }
        if (response == 1) {
            if (shop.potions.size() > 0) {
                buyShop(shop);
            }
        } else {
            // Nu doreste sa cumpere
            try {
                throw new InvalidCommandException("Raspuns invalid. Reintroduceti raspunsul.");
            } catch (InvalidCommandException e) {
                System.out.println(e);
                if (shop.potions.size() > 0) {
                    askBuyShop(shop);
                }

            }
        }
    }

    public static void getOptionsShop(Cell current) {
        // Daca am vizitat deja casuta, inseamna ca avem deja un shop acolo
        // Daca nu am vizitat, trebuie sa generam un shop
        Shop shop;
        if (!current.vizitat) {
            shop = new Shop();
            current.object = shop;
        } else {
            shop = (Shop) current.object;
        }
        System.out.println("Bani: " + Grid.curent.inventar.coins);
        System.out.println("Potiuni disponibile in shop:");
        shop.showPotions();
        if (shop.potions.size() == 0) {
            System.out.println("Shop gol.");
            return;
        }
        askBuyShop(shop);
    }

    public static void usePotioninCombat() {
        Scanner input = new Scanner(System.in);
        System.out.println("Alegeti una din potiunile din inventar:");
        Grid.curent.inventar.showPotions();
        int rasp;
        // Verificam daca ceea ce s-a introdus este numar
        try {
            rasp = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Introduceti un numar valid!");
            usePotioninCombat();
            return;
        }
        // Verificam daca numarul introdus face parte din lista de potiuni
        if (rasp > Grid.curent.inventar.potions.size() || rasp <= 0) {
            try {
                throw new InvalidCommandException("Indicele introdus nu face parte din optiuni");
            } catch (InvalidCommandException e) {
                System.out.println(e);
                usePotioninCombat();
                return;
            }
        }
        // Se foloseste potiunea
        Potion p = Grid.curent.inventar.potions.get(rasp - 1);
        int rez = p.usePotion(Grid.curent);
        if (rez == 1) {
            System.out.println("Ati folosit potiunea.");
            statsCharacter();
            Grid.curent.inventar.delPotion(rasp - 1);
        }
        askUsePotion();
    }

    public static void askUsePotion() {
        if (Grid.curent.inventar.potions.size() > 0) {
            Scanner input = new Scanner(System.in);
            System.out.println("Doriti sa folositi o potiune? (1 - pentru da / 0 - pentru nu)");
            int rasp;
            try {
                rasp = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Trebuie sa introduceti 1 pentru da sau 0 pentru nu.");
                askUsePotion();
                return;
            }

            if (rasp == 1) {
                usePotioninCombat();
            } else {
                if (rasp == 0) {
                    System.out.println("Nu folositi potiune.");
                } else {
                    try {
                        throw new InvalidCommandException("Numarul introdus nu este valid!");
                    } catch (InvalidCommandException e) {
                        System.out.println(e);
                        askUsePotion();
                    }
                }
            }
        }
    }

    public static void statsCharacter() {
        System.out.println("=========================================== Caracter ===========================================");
        System.out.println("Viata curenta: " + Grid.curent.current_life);
        System.out.println("Mana curenta: " + Grid.curent.current_mana);
        System.out.println("Level curent: " + Grid.curent.level);
        System.out.println("Monede: " + Grid.curent.inventar.coins);
        if (Grid.curent.earth) {
            System.out.println("Aveti Earth-Protection.");
        }
        if (Grid.curent.fire) {
            System.out.println("Aveti Fire-Protection.");
        }
        if (Grid.curent.ice) {
            System.out.println("Aveti Ice-Protection.");
        }
        if (Grid.curent.abilitati.size() > 0) {
            System.out.print("Abilitatile dvs: ");
            int i;
            for (i = 0; i < Grid.curent.abilitati.size(); i++) {
                System.out.print("[" + Grid.curent.abilitati.get(i).getClass().getSimpleName() + " (D:" + Grid.curent.abilitati.get(i).damage + ",C:" + Grid.curent.abilitati.get(i).manaCost + ")] ");
            }
        } else {
            System.out.println("Nu aveti abilitati");
        }
        System.out.println("");
        System.out.println("================================================================================================");
    }

    public static void statsEnemy(Enemy enemy) {
        System.out.println("============================================ Inamic ============================================");
        System.out.println("Viata curenta: " + enemy.current_life);
        System.out.println("Mana curenta: " + enemy.current_mana);
        // System.out.println("");
        if (enemy.earth) {
            System.out.println("Inamicul are Earth-Protection.");
        }
        if (enemy.fire) {
            System.out.println("Inamicul are Fire-Protection.");
        }
        if (enemy.ice) {
            System.out.println("Inamicul are Ice-Protection.");
        }
        if (enemy.abilitati.size() > 0) {
            System.out.print("Abilitatile inamicului: ");
            int i;
            for (i = 0; i < enemy.abilitati.size(); i++) {
                System.out.print("[" + enemy.abilitati.get(i).getClass().getSimpleName() + " (D:" + enemy.abilitati.get(i).damage + ",C:" + enemy.abilitati.get(i).manaCost + ")] ");
            }
        } else {
            System.out.println("Inamicul nu are abilitati.");
        }
        System.out.println("");
        System.out.println("================================================================================================");
    }

    public static void EnemyAttackType(Enemy enemy) {
        Random rn = new Random();
        // Numere intre 0 si 4
        int attack = rn.nextInt(5);
        // Aleg un nr random pt sansa de 25%
        if (attack == 2) {
            // 25% folosire abilitate
            int spellRn = rn.nextInt(enemy.abilitati.size());
            Spell spell = enemy.abilitati.get(spellRn);
            if (enemy.current_mana >= spell.manaCost) {
                System.out.println("Inamicul a folosit abilitatea: " + spell.getClass().getSimpleName());
                Grid.curent.accept(spell);
                enemy.current_mana = enemy.current_mana - spell.manaCost;
                statsCharacter();
                statsEnemy(enemy);
            } else {
                attack = 1;
            }
        }
        if (attack != 2) {
            // 75% atac normal
            int damage = enemy.getDamage();
            System.out.println("Inamicul te-a atacat! Damage dat: " + damage + ". ");
            Grid.curent.receiveDamage(damage);
            statsCharacter();
        }
    }

    public static void spellAttack(Enemy enemy) {
        Scanner input = new Scanner(System.in);
        System.out.println("Alegeti una din abilitatile pe care le detineti:");
        int i;
        for (i = 0; i < Grid.curent.abilitati.size(); i++) {
            System.out.println(i + 1 + ". " + Grid.curent.abilitati.get(i).getClass().getSimpleName());
        }
        // AICI SE FOLOSESTE ABILITATE
        int rasp;
        // Verificam sa fie numar
        try {
            rasp = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Introduceti un numar valid!");
            spellAttack(enemy);
            return;
        }
        // Verificam daca indicele introdus nu face parte din lista de abilitati
        if (rasp <= 0 || rasp > Grid.curent.abilitati.size()) {
            try {
                throw new InvalidCommandException("Indicele introdus nu exista!");
            } catch (InvalidCommandException e) {
                System.out.println(e);
                spellAttack(enemy);
                return;
            }
        }
        // Daca abilitatea exista
        Spell spell = Grid.curent.abilitati.get(rasp - 1);
        // Daca exista suficienta mana
        if (spell.manaCost <= Grid.curent.current_mana) {
            // Foloseste spell si scadem mana
            System.out.println("Folositi abilitatea: " + spell.getClass().getSimpleName());
            enemy.accept(spell);
            Grid.curent.current_mana = Grid.curent.current_mana - spell.manaCost;
            // Crestem xp jucatorului
            Grid.curent.exp = Grid.curent.exp + spell.damage / 2;
            // Caracterul primeste bani cu fiecare spell
            Random rn = new Random();
            int coins = rn.nextInt(spell.damage) + 1;
            System.out.println("Ai primit " + coins + " monede.");
            Grid.curent.inventar.addCoins(coins);
            statsEnemy(enemy);
        } else {
            System.out.println("Nu aveti suficienta mana.");
            statsCharacter();
            askUsePotion();
            askAttackType(enemy);
        }
    }

    public static void askAttackType(Enemy enemy) {
        Scanner input = new Scanner(System.in);
        System.out.println("Doriti sa atacati normal (1) sau sa folositi o abilitate (2)? (1 / 2)");
        int rasp;
        // Verificam ca ceea ce se introduce sa fie numar
        try {
            rasp = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Introduceti 1 pentru atac normal sau 2 pentru a folosi abilitate!");
            askAttackType(enemy);
            return;
        }
        // Trebuie sa sa introduca 1 sau 2
        if (!(rasp == 1 || rasp == 2)) {
            try {
                throw new InvalidCommandException("Introduceti 1 pentru atac normal sau 2 pentru a folosi abilitate!");
            } catch (InvalidCommandException e) {
                System.out.println(e);
                askAttackType(enemy);
                return;
            }
        }
        if (rasp == 1) {
            // ATAC NORMAL
            int damage = Grid.curent.getDamage();
            System.out.println("Ai atacat inamicul! Damage dat: " + damage + ". ");
            enemy.receiveDamage(damage);
            statsEnemy(enemy);
            // Caracterul primeste bani cu fiecare atac
            Random rn = new Random();
            int coins = rn.nextInt(damage) + 1;
            System.out.println("Ai primit " + coins + " monede.");
            Grid.curent.inventar.addCoins(coins);
            // Crestem xp
            Grid.curent.exp = Grid.curent.exp + damage / 2;
        } else {
            // Atac cu abilitati
            spellAttack(enemy);
        }
    }

    public static void levelUp() {
        Grid.curent.level++;
        System.out.println("Ati avansat un nivel! Nivelul actual: " + Grid.curent.level);
        Grid.curent.exp = Grid.curent.exp - 100;
        System.out.println("Nivel de experienta pentru nivelul urmator " + Grid.curent.exp + " / 100.");
         /* Idee de crestere:
            Atribut principal:
            level 2 -> 14
            level 3 -> 18
            level 4 -> 22
            level 5 -> 26
            level 6 -> 30

            Atribut secundar:
            level 2 -> 4
            level 3 -> 6
            level 4 -> 8
            level 5 -> 12
            level 6 -> 16
            level 7 -> 20
        */
        // Actualizam atributele
        if (Grid.curent.fire) {
            // Pentru Warrior
            Grid.curent.strength = Grid.curent.strength + 4;
            if (Grid.curent.level >= 4) {
                Grid.curent.dexterity = Grid.curent.dexterity + 4;
                Grid.curent.charisma = Grid.curent.charisma + 4;
            } else {
                Grid.curent.dexterity = Grid.curent.dexterity + 2;
                Grid.curent.charisma = Grid.curent.charisma + 2;
            }
        }
        if (Grid.curent.ice) {
            // Pentru Mage
            Grid.curent.charisma = Grid.curent.charisma + 4;
            if (Grid.curent.level >= 4) {
                Grid.curent.dexterity = Grid.curent.dexterity + 4;
                Grid.curent.strength = Grid.curent.strength + 4;
            } else {
                Grid.curent.dexterity = Grid.curent.dexterity + 2;
                Grid.curent.strength = Grid.curent.strength + 2;
            }
        }
        if (Grid.curent.ice) {
            // Pentru Rogue
            Grid.curent.dexterity = Grid.curent.dexterity + 4;
            if (Grid.curent.level >= 4) {
                Grid.curent.charisma = Grid.curent.charisma + 4;
                Grid.curent.strength = Grid.curent.strength + 4;
            } else {
                Grid.curent.charisma = Grid.curent.charisma + 2;
                Grid.curent.strength = Grid.curent.strength + 2;
            }
        }
    }

    public static void combatEnemy(Cell current, Enemy enemy) {
        System.out.println("Ati intrat in lupta cu inamicul!");
        while (Grid.curent.current_life > 0 && enemy.current_life > 0) {
            askUsePotion();
            askAttackType(enemy);
            // Daca inamicul mai traieste, ataca
            if (enemy.current_life > 0) {
                EnemyAttackType(enemy);
            }
        }
        if (Grid.curent.current_life <= 0) {
            System.out.println("Ati murit!");
            // Mutam jucatorul la final
            current.tip_element = CellEnum.FINISH;
        }
        if (enemy.current_life <= 0) {
            System.out.println("Ati invins inamicul!");
            // Resetam tipul celulei
            current.tip_element = CellEnum.EMPTY;
            // Verificam exp
            if (Grid.curent.exp > 100) {
                // Daca exp creste peste 100, caracterul face level up
                levelUp();

            } else {
                System.out.println("Nivel de experienta: " + Grid.curent.exp);
            }
            // Crestem numarul de inamici invinsi
            Game.defeatedEnemies++;
        }
    }

    public static void getOptionsEnemy(Cell current) {
        Enemy enemy = new Enemy();
        if (!current.vizitat) {
            current.object = enemy;
            System.out.println("Ati intalnit un inamic nou!");
        } else {
            enemy = (Enemy) current.object;
            // DE ADAUGAT SCHIMBARE IN EMPTY CELL DUPA CE MOARE INAMICU'
            System.out.println("Aici este un inamic pe care l-ati evitat.");
        }
        statsCharacter();
        statsEnemy(enemy);

        System.out.println("Doriti sa va luptati cu acest inamic? (1 - da / 0 - nu)");
        Scanner input = new Scanner(System.in);
        int rasp;
        try {
            rasp = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Valoarea introdusa nu este valabila. Nu te vei lupta cu acest inamic acum.");
            return;
        }
        if (!(rasp == 0 || rasp == 1)) {
            try {
                throw new InvalidCommandException("Indicele introdus nu este valid. Nu te vei lupta cu inamicul acum.");
            } catch (InvalidCommandException e) {
                System.out.println(e);
                return;
            }
        }
        if (rasp == 1) {
            combatEnemy(current, enemy);
        } else {
            System.out.println("Am inteles. Poate data viitoare!");
            // NEXT MOVE
        }

    }

    public static void getOptions(Cell current) {
        if (current.tip_element.equals(CellEnum.EMPTY)) {
            return;
        }
        if (current.tip_element.equals(CellEnum.SHOP)) {
            getOptionsShop(current);
        }
        if (current.tip_element.equals(CellEnum.ENEMY)) {
            // Daca am intalnit un enemy
            getOptionsEnemy(current);
        }
    }

    public static void pickCharacter(int accountNumber) throws InvalidCommandException {
        System.out.println("Alegeti un caracter din cele de mai jos.");
        int i;
        Account current = accounts.get(accountNumber);
        ArrayList<Character> listCharacter = current.characters;
        for (i = 0; i < listCharacter.size(); i++) {
            System.out.println(i + ". " + listCharacter.get(i));
        }
        System.out.println("Sctieti numarul corespunzator caracterului dorit.");
        Scanner input = new Scanner(System.in);
        int number = 0;
        try {
            number = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Introduceti un numar!");
            pickCharacter(accountNumber);
        }

        if (number < listCharacter.size() && number >= 0) {
            Grid.curent = listCharacter.get(number);
            Grid.curent.startCharacter();
            System.out.println("Ati ales: " + Grid.curent);
        } else {
            try {
                throw new InvalidCommandException("Nu ati ales un caracter corect.");
            } catch (InvalidCommandException e) {
                System.out.println(e);
                pickCharacter(accountNumber);
            }
        }
    }

    public static void login() {
        Scanner input = new Scanner(System.in);
        int i, contor;
        while (true) {
            System.out.println("Introduceti adresa de mail.");
            String email = input.nextLine();
            email = email.toLowerCase();
            for (i = 0; i < accounts.size(); i++) {
                Credentials auth = accounts.get(i).information.getCredentials();
                if (auth.getEmail().equals(email)) {
                    // S-a gasit un mail
                    contor = 0;
                    while (contor < 3) {
                        System.out.println("Introduceti parola asociata contului.");
                        String pass = input.nextLine();
                        if (auth.getPassword().equals(pass)) {
                            // Cont corect, autentificare efectuata
                            System.out.println("Bine ai venit, " + accounts.get(i).information.getName() + "!");
                            System.out.println("Regiunea: " + accounts.get(i).information.getCountry());
                            curentAccount = i;
                            try {
                                pickCharacter(i);
                            } catch (InvalidCommandException e) {
                                e.printStackTrace();
                            }
                            return;
                        } else {
                            contor++;
                            int ramase = 3 - contor;
                            System.out.println("Parola introdusa nu este corecta. Mai aveti " + ramase);
                        }
                    }
                    if (contor == 3) {
                        System.out.println("Ati depasit numarul maxim de incercati. Incercati alt mail.");
                    }
                } else {
                    if (i >= accounts.size() - 1) {
                        System.out.println("Adresa introdusa nu exista.");
                    }
                }
            }
        }
    }

    public static void initGame() {
        Grid.setColoane(5);
        Grid.setLinii(5);
        ArrayList<ArrayList<Cell>> map = Grid.generare(Grid.getLinii(), Grid.getColoane());
        Grid.setMap(map);
    }

    public static void showMap(ArrayList<ArrayList<Cell>> harta) {
        int i, j;
        for (i = 0; i < Grid.getLinii(); i++) {
            for (j = 0; j < Grid.getColoane(); j++) {
                if (i == Grid.curent_cell.Ox && j == Grid.curent_cell.Oy) {
                    System.out.print("\u001B[31m" + "P " + "\u001B[0m");
                } else {
                    System.out.print(harta.get(i).get(j) + " ");
                }
            }
            System.out.print("\n");
        }
    }

    public static void movement() {
        if (Grid.map.get(Grid.curent_cell.Ox).get(Grid.curent_cell.Oy).tip_element.equals(CellEnum.FINISH)) {
            if (Grid.curent.current_life <= 0) {
                System.out.println("Ati pierdut jocul!");

            } else {
                System.out.println(Grid.map.get(Grid.curent_cell.Ox).get(Grid.curent_cell.Oy).toString());
                System.out.println("Ati ajuns la final.");
                // Crestem nr de jocuri jucate
                accounts.get(curentAccount).maps_completed++;
                saveProgressJSON();
            }
            // Afisam pagina de final
            FinalPage finalPage = new FinalPage();
            finalPage.runPage();
        } else {
            System.out.println(Grid.map.get(Grid.curent_cell.Ox).get(Grid.curent_cell.Oy).toString());
            Scanner comenzi = new Scanner(System.in);
            System.out.println("Introduceti urmatoarea mutare");
            String comanda = comenzi.nextLine();
            comanda = comanda.toLowerCase();
            if (comanda.equals("w")) {
                // Mutare in sus
                Grid.goNorth();
                showMap(Grid.map);
                movement();
            } else {
                if (comanda.equals("a")) {
                    // Mutare in stanga
                    Grid.goWest();
                    showMap(Grid.map);
                    movement();
                } else {
                    if (comanda.equals("s")) {
                        // Mutare in jos
                        Grid.goSouth();
                        showMap(Grid.map);
                        movement();
                    } else {
                        if (comanda.equals("d")) {
                            // Mutare in dreapta
                            Grid.goEast();
                            showMap(Grid.map);
                            movement();
                        } else {
                            if (comanda.equals("0")) {
                                // Oprire joc (pentru developer mode)
                                System.out.println("Mutare oprita.");
                            } else {
                                try {
                                    throw new InvalidCommandException("Comanda invalida. Reincercati.");
                                } catch (InvalidCommandException e) {
                                    System.out.println(e);
                                    movement();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void loginAuth() {
        System.out.println("Introduceti indicele corespunzator modului de autentificare.");
        System.out.println("1 - Autentificare de la terminal");
        System.out.println("2 - Autentificarea de pe interfata grafica");
        Scanner input = new Scanner(System.in);
        int rasp;
        try {
            rasp = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Introduceti un numar!");
            loginAuth();
            return;
        }
        if (!(rasp == 1 || rasp == 2)) {
            try {
                throw new InvalidCommandException("Introduceti 1 pentru autentificarea de la terminal sau 2 pentru autentificarea de pe interfata grafica.");
            } catch (InvalidCommandException e) {
                System.out.println(e);
                loginAuth();
                return;
            }
        }
        if (rasp == 1) {
            // Autentificarea de la terminal
            login();
            // Alegem in ce mod se va juca
            gameMethod();
        } else {
            AuthPage authPage = new AuthPage();
            authPage.runPage();
        }
    }

    public static void gameMethod() {
        System.out.println("Introduceti indicele corespunzator modului de joc.");
        System.out.println("1 - Joc in terminal");
        System.out.println("2 - Joc pe interfata grafica");
        Scanner input = new Scanner(System.in);
        int rasp;
        try {
            rasp = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Introduceti un numar!");
            gameMethod();
            return;
        }
        if (!(rasp == 1 || rasp == 2)) {
            try {
                throw new InvalidCommandException("Introduceti 1 pentru a juca in terminal sau 2 pentru a juca in interfata grafica.");
            } catch (InvalidCommandException e) {
                System.out.println(e);
                gameMethod();
                return;
            }
        }
        if (rasp == 1) {
            // Efectuam mutarile in terminal
            movement();
        } else {
            GamePage gamePage = new GamePage();
            gamePage.runPage();
        }
    }

    public void run() {
        // Generam povestile
        storiesJSON();
        // Extragem conturile din json
        accountsJSON();
        // Initializam jocul
        initGame();
        // Jucatorul isi alege contul fie din interfata grafica fie din terminal
        loginAuth();

    }
}
