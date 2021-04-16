/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oldUpdater;

import acapy.online.AcapyOnline;
import assets.classes.statics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AHMED
 */
public class go {

    private static String url = "";
    private static Connection con;

    public static boolean canCoonect() {
        try {
            setURL();
            con = DriverManager.getConnection(url, "acapytradeahmedsaleh", "as01203904426");
            return true;
        } catch (SQLException ex) {
            return false;

        }
    }
    Preferences prefs;

    private static void setURL() {
        Preferences prefs = Preferences.userNodeForPackage(AcapyOnline.class);
        try {

            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://" + prefs.get(statics.DATABASE_IP, statics.DEFAULT_DATABASE_IP) + ":3306/" + statics.DATABASE_NAME + "?useUnicode=true&characterEncoding=UTF-8";

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(go.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static Connection getReportCon() {
        try {
            setURL();
            con = DriverManager.getConnection(url, "acapytradeahmedsaleh", "as01203904426");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error in connection to database ERROR Code: \n" + ex.getMessage());

        }

        return con;
    }

    public static void setConnection() {

        try {
            setURL();
            con = DriverManager.getConnection(url, "acapytradeahmedsaleh", "as01203904426");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error in connection to database ERROR Code: \n" + ex.getMessage());

        }
    }

    public static boolean checkSignin(String user, String pass) {
        try {
            setConnection();
            Statement stm = con.createStatement();
            String str = "SELECT `user`, `password` FROM `users` WHERE user='" + user + "' and password='" + pass + "'";

            stm.execute(str);
            while (stm.getResultSet().next()) {
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error check");
        }
        return false;

    }

    public static void addUser(String user, String pass) {
        try {
            //setConnection();
            Statement stm = con.createStatement();
            String str = "INSERT INTO `users`(`user`, `pass`) VALUES ('" + user + "','" + pass + "')";
            stm.executeUpdate(str);

            JOptionPane.showMessageDialog(null, "User Added Successfuly");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Add User ");

        }

    }

    public static JTable getTableData(String statement) {

        try {

            Statement st = con.createStatement();
            ResultSet rs;
            rs = st.executeQuery(statement);
            ResultSetMetaData rsmt = rs.getMetaData();
            int c = rsmt.getColumnCount();

            JTable table = new JTable(0, c);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            while (rs.next()) {
                Object rows[] = new Object[c];

                for (int i = 0; i < c; i++) {
                    rows[i] = rs.getString(i + 1);
                }
                model.addRow(rows);
            }

            return table;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            JTable tableError = new JTable(0, 0);
            return tableError;

        }

    }

    public static boolean runNonQuery(String statement) {
        try {
            //setConnection();
            Statement stmt = con.createStatement();
            stmt.execute(statement);
            //  con.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }

    }

    public static boolean runNonQueryPrepare(PreparedStatement statement) {
        try {
            //setConnection();
            statement.execute();
            //  con.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }

    }

    public static PreparedStatement Prepare(String statement) {
        try {
            //setConnection();
            PreparedStatement stat = con.prepareStatement(statement);

            return stat;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }
        return null;
    }

}
