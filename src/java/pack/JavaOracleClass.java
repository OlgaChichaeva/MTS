package pack;

import java.io.*;
import java.sql.*;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.pool.OracleDataSource;

public class JavaOracleClass {

    static String user = "MTS";
    static String pass = "MTS"; // Укажите здесь свой пароль;
    static String url = "jdbc:oracle:thin:@localhost";
    static Connection con;

    static {
        try {
            Locale.setDefault(Locale.ENGLISH);

            OracleDataSource sour = new OracleDataSource();
            sour.setURL(url);
            sour.setUser(user);
            sour.setPassword(pass);
            con = sour.getConnection();
        } catch (SQLException ex) {
            //добавить log
        }
    }

    /* private static void add(String tableName, String[] collName, String[] value) {


     StringBuilder collNames = new StringBuilder();
     StringBuilder values = new StringBuilder();
     for (int i = 0; i < collName.length; i++) {
     collNames.append(collName[i]).append(",");
     values.append("?,");
     }
     collNames.deleteCharAt(collNames.length() - 1);
     values.deleteCharAt(values.length() - 1);
     String query = "Insert Into " + tableName + "(" + collNames + ") values(" + values + ")";
     try {

     PreparedStatement ps = con.prepareStatement(query);
     for (int i = 0; i < collName.length; i++) {
     ps.setString(i, value[i]);
     ps.executeUpdate();
     }

     } catch (SQLException ex) {
     //добавить log
     }
     }
     private static String[] typeCollName = {"name_type", "measure"};

     public static void addType(String name_type, String measure) {//значения полей
     String[] value = {name_type, measure};
     add("type_service", typeCollName, value);

     }*/
    public static void addTypeService(String name_type, String measure)
            throws SQLException {

        PreparedStatement ps = null;
        ps = con.prepareStatement("INSERT INTO type_service (name_type, measure) VALUES (?,?)");
        ps.setString(1, name_type);
        ps.setString(2, measure);
        ps.executeUpdate();
        ps.close();
    }

    public static void addService(String name_service, double cost)
            throws SQLException {

        PreparedStatement ps = null;
        ps = con.prepareStatement("INSERT INTO Service (name_service, cost) VALUES (?,?)");
        ps.setString(1, name_service);
        ps.setDouble(2, cost);
        ps.executeUpdate();
        ps.close();
    }

    public static void addTariffList(String name_tariff, String description)
            throws SQLException {

        PreparedStatement ps = null;
        ps = con.prepareStatement("INSERT INTO tariff_list (name_tariff, description) VALUES (?,?)");
        ps.setString(1, name_tariff);
        ps.setString(2, description);
        ps.executeUpdate();
        ps.close();
    }

    public static void addServiceInTariff(int ID_tariff, int ID_service)
            throws SQLException {

        PreparedStatement ps = null;
        ps = con.prepareStatement("INSERT INTO service_in_tariff (ID_tariff, ID_service) VALUES (?,?)");
        ps.setInt(1, ID_tariff);
        ps.setInt(2, ID_service);
        ps.executeUpdate();
        ps.close();
    }

    public static void addSim(int ID_tariff, double account)
            throws SQLException {

        PreparedStatement ps = null;
        ps = con.prepareStatement("INSERT INTO Sim ( ID_tariff,account) VALUES (?,?)");
        ps.setInt(1, ID_tariff);
        ps.setDouble(2, account);
        ps.executeUpdate();

        ps.close();
    }

    public static void addTraffic(int sim_id, int ID_service, double amount, double cost, Date time)
            throws SQLException {

        PreparedStatement ps = null;
        ps = con.prepareStatement("INSERT INTO traffic(sim_id, ID_service,amount,cost,time) VALUES (?,?,?,?,?)");
        ps.setInt(1, sim_id);
        ps.setInt(2, ID_service);
        ps.setDouble(3, amount);
        ps.setDouble(4, cost);
        ps.setDate(5, time);
        ps.executeUpdate();

        ps.close();
    }

    public static void addSimContr(int sim_id, int contr_id)
            throws SQLException {

        PreparedStatement ps = null;
        ps = con.prepareStatement("INSERT INTO traffic(sim_id,contr_id ) VALUES (?,?)");
        ps.setInt(1, sim_id);
        ps.setInt(2, contr_id);
        ps.executeUpdate();
        ps.close();
    }

     
    public static void addLegalEntity(String name_company, String address, String telephone, String e_mail, String details)
            throws Exception {

        PreparedStatement ps = null;
        ps = con.prepareStatement("INSERT INTO legal_entity (name_company, address, telephone, e_mail, details) VALUES (?,?,?,?,?)");
        ps.setString(1, name_company);
        ps.setString(2, address);
        ps.setString(3, telephone);
        ps.setString(4, e_mail);
        ps.setString(5, details);
        ps.executeUpdate();
        ps.close();
    }

    public static void addLegalContr(int company_id, String contr_doc, Date date)
            throws Exception {
        PreparedStatement ps = null;
        ps = con.prepareStatement("INSERT INTO legal_contr (company_id, contr_doc, begin_date) VALUES (?,?,?)");
        ps.setInt(1, company_id);
        ps.setString(2, contr_doc);
        ps.setDate(3, date);
        ps.executeUpdate();
        ps.close();
    }

    public static void addcClient(int passport_series, int passport_number,
            String firstname, String lastname, String middlename, long telephone_number)
            throws Exception {
        PreparedStatement ps = null;
        ps = con.prepareStatement("INSERT INTO client (passport_series, passport_number,"
                + "firstname, lastname, middlename, telephone_number) VALUES (?,?,?,?,?,?)");
        ps.setInt(1, passport_series);
        ps.setInt(2, passport_number);
        ps.setString(3, firstname);
        ps.setString(4, lastname);
        ps.setString(5, middlename);
        ps.setLong(6, telephone_number);
        ps.executeUpdate();
        ps.close();
    }

    public static void addClientContr(int client_id, int sim_id, String contr_doc, Date begin_date)
            throws Exception {
        PreparedStatement ps = null;
        ps = con.prepareStatement("INSERT INTO client_contr (client_id, sim_id, contr_doc, begin_date) VALUES (?,?,?,?)");
        ps.setInt(1, client_id);
        ps.setInt(2, sim_id);
        ps.setString(3, contr_doc);
        ps.setDate(4, begin_date);
        ps.executeUpdate();
        ps.close();
    }

   
    
    public static void main(String[] args) throws SQLException {
        addTypeService("Услуга", "Единица");
    }
}