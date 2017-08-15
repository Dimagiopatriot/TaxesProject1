package model.dao.connection;

import java.sql.*;

/**
 * Created by troll on 16.08.2017.
 */

//TODO make flexible
public class SQLConnector {

    private final static String URL = "jdbc:mysql://localhost:3306/firstproject";
    private final static String USER = "root";
    private final static String PASSWORD = "";

    public void executeUpdate(String query){
        try( Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement()) {

            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeSelect(String query){
        try( Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()){
                //TODO add functionality
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
