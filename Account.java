package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class Account {
    Information information;
    ArrayList<Character> characters; //Lista cu toate personajele contului
    Integer maps_completed; //Numarul de jocuri jucate

    public Account(Information information, ArrayList<Character> characters, Integer maps_completed) {
        this.information = information;
        this.characters = characters;
        this.maps_completed = maps_completed;
    }

    public static class Information {
        private final Credentials credentials; //obligatoriu
        private final ArrayList<String> games; //Colectie, jocurile preferate ale jucatorului
        private final String name; //Numele jucatorului // obligatoriu
        private final String country; //Tara jucatorului

        private Information(Builder builder) {
            this.credentials = builder.credentials;
            this.games = builder.games;
            this.name = builder.name;
            this.country = builder.country;
        }

        public ArrayList<String> getGames() {
            return games;
        }

        public Credentials getCredentials() {
            return credentials;
        }

        public String getName() {
            return name;
        }

        public String getCountry() {
            return country;
        }

        @Override
        public String toString() {
            return "Information{" +
                    "autentificare=" + credentials +
                    ", jocuri=" + games +
                    ", nume='" + name + '\'' +
                    ", tara='" + country + '\'' +
                    '}';
        }

        public static class Builder {
            private final Credentials credentials;
            private ArrayList<String> games = new ArrayList<>(); // Colectie, jocurile preferate ale jucatorului
            private final String name; //Numele jucatorului
            private String country; // Tara jucatorului

            public Builder(Credentials credentials, String name) {
                this.credentials = credentials;
                this.name = name;
            }

            // Campuri optionale
            public Builder Games(ArrayList<String> games) {
                this.games = games;
                Collections.sort(games);
                return this;
            }

            public Builder Country(String country) {
                this.country = country;
                return this;
            }

            public Information build() {
                return new Information(this);
            }
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "informatii=" + information +
                ", personaje=" + characters +
                ", nr_jocuri=" + maps_completed +
                '}';
    }
}
