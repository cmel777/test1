package com.vo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.tel.vo.Item;

public class ServiceCharge {

	Float totalCharge = (float) 0;
	boolean itemIsFood = false;
	boolean itemIsHotFood = false;

	public String calculateServiceCharge(List<Item> itemList, String tipAmount) {

		itemIsFood = false;
		itemIsHotFood = false;
		totalCharge = (float) 0;
		List<Boolean> allDrinks = new ArrayList<Boolean>();
		itemList.stream().forEach(x -> {
			totalCharge = totalCharge
					+ Float.parseFloat((x.getPrice() != null && !"".equals(x.getPrice().trim()) ? x.getPrice() : "0"));
			if (x.getType().equals("Drink")) {
				allDrinks.add(new Boolean(true));
				return;
			}

			if (x.getType().equals("Food")) {
				itemIsFood = true;
				allDrinks.add(new Boolean(false));
			}

			if (x.getType().equals("Food") && x.getTemperature().equals("Hot")) {
				allDrinks.add(new Boolean(false));
				itemIsHotFood = true;
			}

		});

		if (testBool(allDrinks, n -> true)) {
			// do nothing
			System.out.println("All Drinks");
		} else if (itemIsFood == false && itemIsHotFood == true) {
			totalCharge = applyServiceChargeForHotFood(totalCharge);
		} else if (itemIsFood == true && itemIsHotFood == false) {
			totalCharge = round(totalCharge + totalCharge * 10 / 100, 2);
		} else if (itemIsFood == true && itemIsHotFood == true) {
			totalCharge = applyServiceChargeForHotFood(totalCharge);
		}

		Float returnVal = totalCharge + Float.parseFloat(tipAmount.trim());
		return String.valueOf(returnVal);

	}

	public boolean testBool(List<Boolean> list, Predicate<Boolean> predicate) {
		for (Boolean n : list) {
			if (n.equals(new Boolean(true))) {
			} else {
				return false;
			}
		}
		return true;
	}

	public static float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}

	public float applyServiceChargeForHotFood(float totalCharge) {
		if ((totalCharge * 20 / 100) > new Float(19)) {
			totalCharge = totalCharge + new Float(20);
		} else {
			totalCharge = totalCharge + totalCharge * 20 / 100;
		}
		return totalCharge;
	}

}
