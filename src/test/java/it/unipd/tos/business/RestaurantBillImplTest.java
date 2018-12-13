package it.unipd.tos.business;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RestaurantBillImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void Regular_ordered_menu_total_to_pay() throws RestaurantBillException{
        List<MenuItem> firstMenu = new ArrayList<>();
        firstMenu.add(new MenuItem(MenuItem.ItemType.Pizze,"Pizza Margherita",5));
        firstMenu.add(new MenuItem(MenuItem.ItemType.Pizze,"Pizza Capricciosa",10));
        firstMenu.add(new MenuItem(MenuItem.ItemType.Primi,"Pasta alla carbonara",15));
        firstMenu.add(new MenuItem(MenuItem.ItemType.Primi,"Bucatini all'amatriciana",20.50));
        assertEquals(50.5, new RestaurantBillImpl().getOrderPrice(firstMenu), 0);
    }
    
    @Test
    public void Menu_with_more_than_ten_pizzas_total_to_pay() throws RestaurantBillException{
        List<MenuItem> secondMenu = new ArrayList<>();
        secondMenu.add(new MenuItem(MenuItem.ItemType.Pizze,"Pizza Marinara",4));
        secondMenu.add(new MenuItem(MenuItem.ItemType.Pizze,"Pizza Marinara",4));
        secondMenu.add(new MenuItem(MenuItem.ItemType.Pizze,"Pizza Marinara",4));
        secondMenu.add(new MenuItem(MenuItem.ItemType.Pizze,"Pizza Marinara",4));
        secondMenu.add(new MenuItem(MenuItem.ItemType.Pizze,"Pizza Marinara",4));
        secondMenu.add(new MenuItem(MenuItem.ItemType.Pizze,"Pizza Marinara",4));
        secondMenu.add(new MenuItem(MenuItem.ItemType.Pizze,"Pizza Marinara",4));
        secondMenu.add(new MenuItem(MenuItem.ItemType.Primi,"Pasta alla carbonara",15));
        secondMenu.add(new MenuItem(MenuItem.ItemType.Pizze,"Pizza Marinara",4));
        secondMenu.add(new MenuItem(MenuItem.ItemType.Pizze,"Pizza Marinara",4));
        secondMenu.add(new MenuItem(MenuItem.ItemType.Pizze,"Pizza Marinara",4));
        secondMenu.add(new MenuItem(MenuItem.ItemType.Pizze,"Pizza Margherita",5));
        secondMenu.add(new MenuItem(MenuItem.ItemType.Pizze,"Pizza Capricciosa",10));
        assertEquals(66, new RestaurantBillImpl().getOrderPrice(secondMenu), 0);
    }
    
    
    @Test
    public void Menu_with_null_item() throws RestaurantBillException {
        List<MenuItem> thirdMenu = new ArrayList<>();
        thirdMenu.add(new MenuItem(MenuItem.ItemType.Primi,"Pasta allo scoglio",15));
        thirdMenu.add(new MenuItem(MenuItem.ItemType.Primi,"Strozzapreti alle erbette",20.5));
        thirdMenu.add(new MenuItem(MenuItem.ItemType.Primi,"Vellutata di pomodoro",18.5));
        thirdMenu.add(null);
        thirdMenu.add(new MenuItem(MenuItem.ItemType.Primi,"Lasagne alla bolognese",25));

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Found a null item");
        new RestaurantBillImpl().getOrderPrice(thirdMenu);
    }
	
	@Test
    public void Empty_list_menu() throws RestaurantBillException {
        List<MenuItem> fourthMenu = new ArrayList<>();
        assertEquals(0, new RestaurantBillImpl().getOrderPrice(fourthMenu), 0);
	}
	
	@Test
    public void High_Price_Menu() throws RestaurantBillException {
        List<MenuItem> fifthMenu = new ArrayList<>();
		fifthMenu.add(new MenuItem(MenuItem.ItemType.Primi,"Pasta allo scoglio",15));
        fifthMenu.add(new MenuItem(MenuItem.ItemType.Primi,"Strozzapreti alle erbette",20.5));
        fifthMenu.add(new MenuItem(MenuItem.ItemType.Primi,"Vellutata di pomodoro",18.5));
		fifthMenu.add(new MenuItem(MenuItem.ItemType.Primi,"Lasagne alla bolognese",25));
		fifthMenu.add(new MenuItem(MenuItem.ItemType.Primi,"Lasagne alla bolognese",25));
        assertEquals(98.8, new RestaurantBillImpl().getOrderPrice(fifthMenu), 0);
	}
	
	@Test
    public void Menu_with_more_than_20_element() throws RestaurantBillException {
        List<MenuItem> sixthMenu = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            sixthMenu.add(new MenuItem(MenuItem.ItemType.Primi, "Pasta all'olio", 4));
        }
        thrown.expect(RestaurantBillException.class);
        thrown.expectMessage("Cannot order more than 20 elements");
        new RestaurantBillImpl().getOrderPrice(sixthMenu);
	}
	
	
}    
