package com.quickCart.coffeeShopMenu;

import com.quickCart.coffeeShopMenu.Model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuickCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickCartApplication.class, args);

		Product expresso = new Product(1,"Expresso",2.99);
		Product mocha = new Product(2,"Mocha",4.99);

		System.out.println("Product ID :"+expresso.getId()+" Name "+expresso.getName()+" Price "+expresso.getPrice());
	}

}
