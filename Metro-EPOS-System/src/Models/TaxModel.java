package Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TaxModel {
    public static double getSalesTax(Connection connection) {
        double salesTax = 0.0;
        String sql = "EXEC GetSalesTaxPrice";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                salesTax = resultSet.getDouble("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salesTax;
    }}
