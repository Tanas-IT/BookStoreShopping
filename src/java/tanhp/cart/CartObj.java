/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanhp.cart;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import tanhp.product.ProductDAO;
import tanhp.product.ProductDTO;

/**
 *
 * @author ASUS
 */
public class CartObj implements Serializable{
    private Map<String,Integer> items; // sku, quantity

    public Map<String, Integer> getItems() {
        return items;
    }
    
    public void addItemToCart(String sku) {
        if(sku == null) {
            return;
        }
        if(sku.trim().isEmpty()) {
            return;
        }
        // 1. Check existed items
        if(this.items == null) {
            items = new HashMap<>();
        }
        // 2. Check existed item
        int quantity = 1;
        if(this.items.containsKey(sku)) {
            quantity = this.items.get(sku) + 1;
        }
        // 3. Update items
        this.items.put(sku, quantity);
    }
     // về nhà add 1 món đồ (sku,quantity): Overloading lại addItemToCart
    public void addItemToCart(String sku, int quantityOfProduct) {
        if(sku == null) {
            return;
        }
        if(sku.trim().isEmpty()) {
            return;
        }
        // 1. Check existed item
        if(this.items == null) {
            items = new HashMap<>();
        }
        // 2.Check existed item
        int quantity = quantityOfProduct;
        if(this.items.containsKey(sku)) {
            quantity = this.items.get(sku) + quantityOfProduct;
        }
        this.items.put(sku, quantity);
    }
    // Hàm xóa hết các item: removeAll
    public void removeItemFromCart(String sku) {
        // 1. Check existed items
        if(this.items == null) {
            return;
        }
        // 2. Check existed item
        if(this.items.containsKey(sku)) {
            // 3. Remove item
            this.items.remove(sku);
            if(this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
    // Xóa bao nhiêu món trong giỏ, truyền vào số lượng cần xóa
    public void removeItemFromCart(String sku, int quantity) {
        // 1. Check existed items
        if(this.items == null) {
            return;
        }
        // 2. Check existed item
        if(this.items.containsKey(sku)) {
            // 3. Remove item base on quantity input
            if(quantity == 0) {
                this.removeItemFromCart(sku);
            } else {
                int newQuantity = this.items.get(sku) - quantity;
                this.items.remove(sku);
                if(newQuantity != 0) {
                    this.items.put(sku, newQuantity);
                } 
                if(this.items.isEmpty()) {
                    this.items = null;
                }
            }
        }
    }
    
    public ProductDTO getProductBySKU(String sku) throws SQLException, NamingException {
        ProductDAO productDAO = new ProductDAO();
        ProductDTO product = productDAO.getProductBySKU(sku);
        if(product != null) {
            return product;
        }
        return null;
    }
}
