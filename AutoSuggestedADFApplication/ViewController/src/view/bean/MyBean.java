package view.bean;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import oracle.adf.view.rich.model.AutoSuggestUIHints;

import javax.naming.InitialContext;


import javax.sql.DataSource;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import javax.faces.model.SelectItem;

public class MyBean {
    public MyBean() {
    }

    public static Connection getConnection() {
        try {
            InitialContext initialContext = new InitialContext();
            DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/HRDS");
            Connection conn = ds.getConnection();
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List firstNameItems(FacesContext facesContext, AutoSuggestUIHints autoSuggestUIHints) {
        // Add event code here...
        System.out.println("Entering into firstNameItems------------------------ ");
            
        List<SelectItem> items = new ArrayList<SelectItem>();
        System.out.println("SelectItem: "+items);
        String sql =
            "select first_name from employees where first_name like '" + autoSuggestUIHints.getSubmittedValue() + "%'";
        System.out.println("SQL : "+sql);
        PreparedStatement stat = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConnection();
            System.out.println("Connection: "+conn);
            stat = conn.prepareStatement(sql);
            System.out.println("PreparedStatement: "+stat);
            rs = stat.executeQuery();
            System.out.println("executeQuery: "+rs);
            while (rs.next()) {
                items.add(new SelectItem(rs.getString(1), rs.getString(1)));


            }
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        } finally {
            try {
                System.out.println("Closed rs"+rs);
                rs.close();
                System.out.println("Closed stat"+stat);
                stat.close();
                System.out.println("Closed conn"+conn);
                conn.close();
            } catch (SQLException sqle) {
                // TODO: Add catch code
                sqle.printStackTrace();
            }

        }
        System.out.println("Exit from firstNameItems------------------");
        return items;
    }
}
