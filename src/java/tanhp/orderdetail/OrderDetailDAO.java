/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanhp.orderdetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tanhp.util.DBHelper;

/**
 *
 * @author ASUS
 */
public class OrderDetailDAO {
    
    private List<OrderDetailDTO> listOrder;

    public List<OrderDetailDTO> getListOrder() {
        return listOrder;
    }
    
    public boolean insertToOrderDetails(String sku, String orderID, double price,
            int quantity, double total) throws SQLException, NamingException 
    {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBHelper.makeConnection();
            String sql = "Insert Into OrderDetail (sku, orderID, price, quantity, total) "
                    + "VALUES(? , ? , ? , ? , ?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, sku);
            stm.setString(2, orderID);
            stm.setDouble(3, price);
            stm.setInt(4, quantity);
            stm.setDouble(5, total);
            int effectRow = stm.executeUpdate();
            if(effectRow > 0) {
                result = true;
            }
        } finally {
            if(stm != null) {
                stm.close();
            }
            if(con != null) {
                con.close();
            }
        }
        return result;
    }
    
    public void getAllOrder() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "Select id, sku, orderID, price, quantity, total "
                    + "from OrderDetail";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String sku = rs.getString("sku");
                String orderID = rs.getString("orderID");
                Double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                Double total = rs.getDouble("total");
                OrderDetailDTO orderDTO = new OrderDetailDTO(id, sku, orderID, price, quantity, total);
                 if(this.listOrder == null) {
                        this.listOrder = new ArrayList<>();
                    }
                    listOrder.add(orderDTO);
            }
        } finally {
            if(rs != null) {
                rs.close();
            }
            if(stm != null) {
                stm.close();
            }
            if(con != null) {
                con.close();
            }
        }
    }
}
