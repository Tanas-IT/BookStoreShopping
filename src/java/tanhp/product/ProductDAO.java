/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanhp.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import tanhp.util.DBHelper;

/**
 *
 * @author ASUS
 */
public class ProductDAO {
    private List<ProductDTO> listProduct;

    public List<ProductDTO> getListProduct() {
        return listProduct;
    }
    
    public void searchNameProduct(String nameSearch) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if(con != null) {
                String sql = "Select sku, name, quantity, price, status "
                        + "from tbl_Product WHERE name LIKE ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + nameSearch + "%");
                rs = ps.executeQuery();
                while(rs.next()) {
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    boolean status = rs.getBoolean("status");
                    ProductDTO product = new ProductDTO(sku, name, quantity, price, status);
                    if(this.listProduct == null) {
                        this.listProduct = new ArrayList<>();
                    }
                    listProduct.add(product);
                }
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
            if(ps != null) {
                ps.close();
            }
            if(con != null) {
                con.close();
            }
        }
        
    }
     public ProductDTO getProductBySKU(String skuValue) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ProductDTO product = null;
        try {
            con = DBHelper.makeConnection();
            if(con != null) {
                String sql = "Select sku, name, quantity, price, status "
                        + "from tbl_Product WHERE sku = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, skuValue);
                rs = ps.executeQuery();
                while(rs.next()) {
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    boolean status = rs.getBoolean("status");
                    product = new ProductDTO(sku, name, quantity, price, status);
                }
            }
        }finally {
            if(rs != null) {
                rs.close();
            }
            if(ps != null) {
                ps.close();
            }
            if(con != null) {
                con.close();
            }
        }
        return product;
    }
}
