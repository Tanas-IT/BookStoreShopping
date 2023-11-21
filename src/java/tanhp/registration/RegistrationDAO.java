/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanhp.registration;

import java.io.Serializable;
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

public class RegistrationDAO implements Serializable{
//    public boolean checkLogin(String username, String password)
//                            throws SQLException, NamingException{
    public RegistrationDTO checkLogin(String username, String password)
                            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
//        boolean result = false;
        RegistrationDTO result = null;
        try {
            //1. Make Connection
            con = DBHelper.makeConnection();
            if(con != null) {
                //2. Create SQL Statement
                String sql = "Select  lastname, isAdmin "
                        + "From Registration "
                        + "Where username = ? "
                        + "And password = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Excute query
                rs = stm.executeQuery();
                //5. Process
                if(rs.next()) {
                    // map: get data from ResultSet & set data to properties's DTO
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    
                    result = new RegistrationDTO(username, null, fullName, role);
                } // end username and password is verified
            }//end connection is available
            
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
        return result;
    }
    private List<RegistrationDTO> accountList;

    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }
    public void searchLastName(String searchValue) 
            throws SQLException,NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Make Connection
            con = DBHelper.makeConnection();
            if(con != null) {
                //2. Create SQL Statement
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname LIKE ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Excute query
                rs = stm.executeQuery();
                //5. Process
                while(rs.next()) {
                    // 5.1 map
                    // 5.1.1 Get data from Result Set
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    // 5.1.2 set properties of DTO
                    RegistrationDTO dto = new RegistrationDTO(username, 
                            password, fullname, role);
                    // 5.2 add data to list
                    if(this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    }// end account List has not existed
                    this.accountList.add(dto);
                }
            }//end connection is available
            
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
    
    public boolean deleteAccount(String username)
                        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. Make Connection
            con = DBHelper.makeConnection();
            if(con != null) {
                //2. Create SQL Statement
                String sql = "Delete From Registration "
                        + "Where username = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. Excute query
                int effectRows = stm.executeUpdate();
                //5. Process
                if(effectRows > 0) {
                    result = true;
                }
            }//end connection is available
            
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
    
    public boolean updateAccount(String username, String password, boolean role) 
                   throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            con = DBHelper.makeConnection();
            if(con != null) {
                String sql = "Update Registration "
                        + "SET password = ?, isAdmin = ? "
                        + "Where username = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, password);
                ps.setBoolean(2, role);
                ps.setString(3, username);
                
                int updateRow = ps.executeUpdate();
                
                if(updateRow > 0) {
                    result = true;
                }
            }
        }finally {
            if(ps != null) {
                ps.close();
            }
            if(con != null) {
                con.close();
            }
        }
        return result;
    }
    
    public boolean createAccount(RegistrationDTO account)  
            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. Make Connection
            con = DBHelper.makeConnection();
            if(con != null) {
                //2. Create SQL Statement
                String sql = "Insert Into Registration("
                        + "username, password, lastname, isAdmin"
                        + ") VALUES("
                        + "?, ?, ?, ?"
                        + ")";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, account.getUsername());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getFullName());
                stm.setBoolean(4, account.isRole());
                //4. Excute query
                int effectRows = stm.executeUpdate();
                //5. Process
                if(effectRows > 0) {
                    result = true;
                }
            }//end connection is available
            
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
}
