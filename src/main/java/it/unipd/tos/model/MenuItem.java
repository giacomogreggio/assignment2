////////////////////////////////////////////////////////////////////
// Giacomo    Greggio    1142951
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import java.lang.String;

public class MenuItem {
    public enum ItemType {
        Pizze, Primi
    };

    private ItemType itemType;
    private String name;
    private double price;

    public MenuItem(ItemType c, String n, double p) throws IllegalArgumentException {
        if (c == null) {
            throw new IllegalArgumentException("Type of food not valid");
        }
        if (n == null) {
            throw new IllegalArgumentException("Name not valid");
        }
        if (p <= 0) {
            throw new IllegalArgumentException("Incorrect price");
        }
        itemType = c;
        name = n;
        price = p;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
