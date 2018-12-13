package it.unipd.tos.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class MenuItemTest {
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
    @Test
    public void GetItemType_value() {
        MenuItem item = new MenuItem(MenuItem.ItemType.Pizze, "Pizza Margherita", 5);
        assertEquals(MenuItem.ItemType.Pizze, item.getItemType());
    }
	
	@Test
    public void GetName_value() {
        MenuItem item = new MenuItem(MenuItem.ItemType.Pizze, "Bucatini all'amatriciana", 20.5);
        assertEquals("Bucatini all'amatriciana", item.getName());
    }
	
	@Test
    public void GetPrice_value() {
        MenuItem item = new MenuItem(MenuItem.ItemType.Primi, "Bucatini all'amatriciana", 20.5);
        assertEquals(20.5, item.getPrice(),0.0000001);
    }
	
    @Test
    public void NullItemTypeInMyMenu_exception() throws IllegalArgumentException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Type of food not valid");
        MenuItem item = new MenuItem(null, "Pizza Margherita", 5);
	}
	
    @Test
    public void NullNameCase_exception() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Name not valid");
        MenuItem item = new MenuItem(MenuItem.ItemType.Pizze, null, 3);
	}
	
	@Test
    public void NegativePriceCase_exception() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Incorrect price");
        MenuItem item = new MenuItem(MenuItem.ItemType.Pizze, "Pizza Capricciosa", -10);
	}
}