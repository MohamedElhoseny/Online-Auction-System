package Models;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by lenovo on 4/15/2017.
 */
public class DBInterface {
    //Attributes
    private static DBInterface instance;
    private String dbName = "swf2_db";
    private String dbURL = "jdbc:mysql://localhost:3306/" + dbName;
    private String dbUsername = "root";
    private String dbPassword = "12131234";
    private Connection connection = null;
    private String query = null;
    private ArrayList<Object[]> resultList = null;


    //Constructor
    private DBInterface() {
        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Getters and Setters
    public static DBInterface getInstance() {
        //Singleton Class
        if (instance == null) {
            instance = new DBInterface();
        }

        return instance;
    }

    //Methods
    public void insert(String table, String attributes, String values) {
        query = "INSERT INTO " + table + "(" + attributes + ") VALUES(" + values + ")";

        //for checking
        System.out.println(query);

        executeQuery(query, false);
    }

    public void delete(String table, int objectId) {
        query = "DELETE FROM " + table + " WHERE ID = " + objectId;

        //for checking
        System.out.println(query);

        executeQuery(query, false);
    }

    public void update(String table, String options, int objectId, String whereStatement) {
        query = "UPDATE " + table + " SET " + options + " WHERE ID = " + objectId;

        if(!whereStatement.equals("")) {
            query += " AND " + whereStatement;
        }

        //for checking
        System.out.println(query);

        executeQuery(query, false);
    }
    public void update(String table, String attr, String values , String Where){
        query = "Update "+table+" SET "+attr+ " = "+values+" Where "+Where;
        System.out.println(query);
        executeQuery(query,false);
    }

    public ArrayList<Object[]> select(String table) {
        query = prepareQuery(table, "*", "");

        //for checking
        System.out.println(query);

        executeQuery(query, true);

        return resultList;
    }

    public ArrayList<Object[]> select(String table, String options) {
        query = prepareQuery(table, options, "");

        //for checking
        System.out.println(query);

        executeQuery(query, true);

        return resultList;
    }

    public ArrayList<Object[]> select(String table, String options, String whereStatement) {
        query = prepareQuery(table, options, whereStatement);

        //for checking
        System.out.println(query);

        executeQuery(query, true);

        return resultList;
    }
    public ArrayList<Object[]> select(String table, String options ,int userID ){
        table += " INNER JOIN User_notification on Notification.ID = User_notification.notification_ID WHERE User_notification.User_ID = "+userID;
        query = prepareQuery(table,options,"");
        executeQuery(query, true);
        return resultList;
    }

    //Helper Methods
    private String prepareQuery(String table,String options, String whereStatement) {
        query = "SELECT " + options + " FROM " + table;

        if (!whereStatement.equals("")) {
            query += " WHERE " + whereStatement;
        }

        return query;
    }

    private void executeQuery(String query, Boolean dataRetireval) {
//        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet results = null;

        try {
//            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(query);


//            String enclosingMethod = this.getClass().getEnclosingMethod().getName();
//            if (enclosingMethod == "select") {
//                //only for select statements
//                statement.executeQuery(query);
//                results = statement.getResultSet();
//                //return results;
//            }
//            else {
//                statement.executeUpdate(query);
//            }
//            String enclosingMethod = null;
//            try {
//                enclosingMethod = this.getClass().getEnclosingMethod().;
//            }
//            catch (NullPointerException ex) {
//                System.out.println("Error: " + ex.getMessage());
//                System.out.println("Stack state: " + ex.getStackTrace());
//            }

            if (dataRetireval) {
                //only for select statements
                preparedStatement.executeQuery();
                results = preparedStatement.getResultSet();
                //return results;

                //extracting objectArrays from ResultSet
                extractResults(results);
            }
            else {
                preparedStatement.executeUpdate();
            }


        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            // release resources
            // in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (results != null) {
                try {
                    results.close();
                } catch (SQLException sqlEx) {} // ignore
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqlEx) {} // ignore
            }

//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException sqlEx) {} // ignore
//            }

        }
    }

    private void extractResults(ResultSet resultSet) throws SQLException{
        resultList = new ArrayList<>();
        while(resultSet.next()) {
            int columns = resultSet.getMetaData().getColumnCount();
            Object[] array = new Object[columns];
            for(int i=0; i < columns; i++){
                array[i] = resultSet.getObject(i+1);
            }
            resultList.add(array);
        }
    }
}
