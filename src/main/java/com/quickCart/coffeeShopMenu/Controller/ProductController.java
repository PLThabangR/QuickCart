package com.quickCart.coffeeShopMenu.Controller;

import com.quickCart.coffeeShopMenu.Model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private List<Product> productList = List.of(
            new Product(1, "Espresso", 2.50),
            new Product(2, "Latte", 3.50),
            new Product(3, "Croissant", 2.00),
            new Product(4, "Chocolate Muffin", 2.25),
            new Product(5, "Americano", 2.75)
    );

    //    @GetMapping("/")
//    public String getAllProduct(){
//
//        return "product created";
//    }
    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Welcome to the coffe shop";
    }

    @RequestMapping("/list") // This maps to the URL http://localhost:8080/products/list
    public String listProducts(Model productlistModel) {
        String name="Thabang";
       productlistModel.addAttribute("products",productList);
        productlistModel.addAttribute("myName",name);

       return "menu";
    }

    @RequestMapping("/details/{id}") // This maps to the URL http://localhost:8080/products/details/{id}
    @ResponseBody
    public String getProductDetailsByID(@PathVariable int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return "<strong>Requested Product Details: </strong> <hr> Product ID: " + product.getId() + "<br> Name: " + product.getName() + "<br> Price: $" + product.getPrice();
            }
        }
        return "Product not found!";
    }
}


//    @PostMapping("/new")
//    public String CreateProduct(){
//
//        return "new";
//    }





