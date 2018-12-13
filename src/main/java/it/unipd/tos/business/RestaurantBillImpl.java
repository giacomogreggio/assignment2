////////////////////////////////////////////////////////////////////
// Giacomo    Greggio    1142951
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.util.List;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;

public class RestaurantBillImpl implements RestaurantBill {
    public double getOrderPrice(List<MenuItem> itemsOrdered) throws IllegalArgumentException, RestaurantBillException {
        if (itemsOrdered == null) {
            throw new IllegalArgumentException("itemsOrdered not valid");
        }
        if (itemsOrdered.size() == 0) {
            return 0;
        }
        if (itemsOrdered.size() > 20) {
            throw new IllegalArgumentException("Cannot order more than 20 elements");
        }

        // Total
        double total = 0;
        for (int i = 0; i < itemsOrdered.size(); i++) {
            if (itemsOrdered.get(i).getItemType() != null) {
                total += itemsOrdered.get(i).getPrice();
            } else {
                throw new IllegalArgumentException("Found a null item");
            }
        }

        // More than 10 Pizzas
        if (itemsOrdered.size() > 10) {
            long numberP = itemsOrdered.stream().filter(s -> s.getItemType().equals(MenuItem.ItemType.Pizze)).count();
            if (numberP > 10) {
                double min = itemsOrdered.stream().filter(s -> s.getItemType().equals(MenuItem.ItemType.Pizze))
                        .mapToDouble(v -> v.getPrice()).min().orElse(0);
                total -= min;
            }
        }

        // Menu on sale
        if (total > 100) {
            double sconto = (total * 5) / 100;
            total = total - sconto;
        }
        return total;
    }

}    
