/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanhp.order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import tanhp.util.DBHelper;

/**
 *
 * @author ASUS
 */
public class OrderDAO {
   
    public boolean insertOrder(String id, Date date) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBHelper.makeConnection();
            String sql = "Insert Into tblOrder (id,date) "
                    + "VALUES(?, ?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            stm.setDate(2, date);
            int effectRows = stm.executeUpdate();
            if(effectRows > 0) {
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
    
    public boolean updateOrder(String id, double total) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBHelper.makeConnection();
            String sql = "Update tblOrder Set total = ? "
                    + "Where id = ?";
            stm = con.prepareStatement(sql);
            stm.setDouble(1, total);
            stm.setString(2, id);
            int effectRows = stm.executeUpdate();
            if(effectRows > 0) {
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
    
    public double getTotalByOrderID(String id) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        double total = 0;
        try {
            con = DBHelper.makeConnection();
            String sql = "Select total "
                    + "From tblOrder Where id = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            if(rs.next()) {
                total = rs.getDouble("total");
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
        return total;
    }
    
}
