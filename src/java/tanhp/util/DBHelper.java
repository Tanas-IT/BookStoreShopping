/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanhp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ASUS
 */
public class DBHelper {
    public static Connection makeConnection() 
            throws  SQLException, NamingException {
      //1.Get current context (catch NamingExcep)
        Context currentContext = new InitialContext();
        //2. Get web app server context
        Context tomcatContext = (Context) currentContext.lookup("java:comp/env");//mat danh cua tomcat
        //3. Look up  DS from web app context // Dung dung kieu datasource cua sql.DataSource
        DataSource ds = (DataSource) tomcatContext.lookup("DBSE1729");
        //4. Get connection
        Connection con = ds.getConnection();
        return con;
    }
        
    
//        //1. Load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. Create Connection String
//        String url = "jdbc:sqlserver://"
//                +"localhost:1433;"
//                +"databasename=SE1729_PRJ301;"
//                +"instanceName=PHUOCTAN";
//        //3. Open connection
//        Connection con = DriverManager.getConnection(url, "sa", "123456");
//        //4. Return connection to caller
//        return con;
//    }

}
