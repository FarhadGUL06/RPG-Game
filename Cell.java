package com.company;

public class Cell {
    Integer Ox, Oy; //coordonate
    CellEnum tip_element;
    boolean vizitat;
    String story;
    CellElement object;

    public Cell(Integer Ox, Integer Oy, CellEnum tip_element, boolean vizitat) {
        // Ox - nr linie curenta
        this.Ox = Ox;
        // Oy - nr coloana curenta
        this.Oy = Oy;
        this.tip_element = tip_element;
        this.vizitat = vizitat;
    }

    public void setTip_element(CellEnum tip_element) {
        this.tip_element = tip_element;
    }

    @Override
    public String toString() {
        if (vizitat) {
            if (CellEnum.EMPTY == this.tip_element) {
                return "N";
            }
            if (CellEnum.ENEMY == this.tip_element) {
                return object.toCharacter();
            }
            if (CellEnum.SHOP == this.tip_element) {
                return object.toCharacter();
            }
            if (CellEnum.FINISH == this.tip_element) {
                return "F";
            }
            return "P";
        } else {
            return "?";
        }
    }
}
