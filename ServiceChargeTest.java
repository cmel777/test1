package com.tel.service.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.tel.vo.Item;
import com.vo.service.ServiceCharge;

public class ServiceChargeTest {

	@InjectMocks
	private ServiceCharge serviceCharge;

	/** Set up. */
	@Before
	public final void setUp() {
		// Initialise the mocks
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void shouldReturnAServiceCharge() {

		/*calculateServiceCharge takes 2 parameters List of Items and tipAmount, if tipAmount not 
		provided then we need to pass 0*/
		
		String totalCharge = serviceCharge.calculateServiceCharge(getItemList(), "5");
		assertEquals("14.6", totalCharge);

		totalCharge = serviceCharge.calculateServiceCharge(getItemListOnlyDrinks(), "5");
		assertEquals("6.5", totalCharge);

		totalCharge = serviceCharge.calculateServiceCharge(getItemListOnlyDrinks(), "0");
		assertEquals("1.5", totalCharge);

		totalCharge = serviceCharge.calculateServiceCharge(getItemListServiceChargeGreaterThan20(), "0");
		assertEquals("1374.5", totalCharge);

		totalCharge = serviceCharge.calculateServiceCharge(getItemListServiceChargeGreaterThan20(), "5");
		assertEquals("1379.5", totalCharge);

		totalCharge = serviceCharge.calculateServiceCharge(getItemListServiceChargeFoodNotHot(), "5");
		assertEquals("1329.4", totalCharge);
	}

	public List<Item> getItemList() {
		List<Item> itemList = new ArrayList<Item>();
		Item item = new Item();
		item.setName("Cola");
		item.setPrice(".50");
		item.setTemperature("Cold");
		item.setType("Drink");
		itemList.add(item);

		item = new Item();
		item.setName("Coffee");
		item.setPrice("1.0");
		item.setTemperature("Hot");
		item.setType("Drink");
		itemList.add(item);

		item = new Item();
		item.setName("Cheese Sandwich ");
		item.setPrice("2.0");
		item.setTemperature("Cold");
		item.setType("Food");
		itemList.add(item);

		item = new Item();
		item.setName("Steak Sandwich ");
		item.setPrice("4.5");
		item.setTemperature("Hot");
		item.setType("Food");
		itemList.add(item);

		return itemList;
	}

	public List<Item> getItemListOnlyDrinks() {
		List<Item> itemList = new ArrayList<Item>();
		Item item = new Item();
		item.setName("Cola");
		item.setPrice(".50");
		item.setTemperature("Cold");
		item.setType("Drink");
		itemList.add(item);

		item = new Item();
		item.setName("Coffee");
		item.setPrice("1.0");
		item.setTemperature("Hot");
		item.setType("Drink");
		itemList.add(item);

		return itemList;
	}

	public List<Item> getItemListServiceChargeGreaterThan20() {
		List<Item> itemList = new ArrayList<Item>();

		for (int i = 0; i <= 300; i++) {
			Item item = new Item();
			item.setName("Steak Sandwich ");
			item.setPrice("4.5");
			item.setTemperature("Hot");
			item.setType("Food");
			itemList.add(item);
		}

		return itemList;
	}

	public List<Item> getItemListServiceChargeFoodNotHot() {
		List<Item> itemList = new ArrayList<Item>();

		for (int i = 0; i <= 300; i++) {
			Item item = new Item();
			item.setName("Cheese Sandwich ");
			item.setPrice("2.0");
			item.setTemperature("Cold");
			item.setType("Food");
			itemList.add(item);

			itemList.add(item);
		}

		return itemList;
	}
}
