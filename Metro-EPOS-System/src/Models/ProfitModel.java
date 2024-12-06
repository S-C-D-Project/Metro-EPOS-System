package Models;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ProfitModel {
    public static ArrayList<Integer> getProfitData(String timePeriod) {
        ArrayList<Integer> profitData = new ArrayList<>();
        String query = "";

        // Set the query based on the time period
        switch (timePeriod.toLowerCase()) {
            case "daily":
                query = "SELECT profit FROM graph WHERE profitDate = ?";
                break;
            case "weekly":
                query = "SELECT profit FROM graph WHERE profitDate >= DATEADD(DAY, -7, GETDATE())";
                break;
            case "monthly":
                query = "SELECT profit FROM graph WHERE profitDate >= DATEADD(DAY, -30, GETDATE())";
                break;
            case "yearly":
                query = "SELECT profit FROM graph WHERE profitDate >= DATEADD(DAY, -365, GETDATE())";
                break;
            default:
                System.out.println("Invalid time period");
                return profitData;
        }

        // Fetch data based on the query
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // If it's a daily query, we need to specify the exact date
            if (timePeriod.equalsIgnoreCase("daily")) {
                stmt.setDate(1, Date.valueOf(java.time.LocalDate.now()));
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                profitData.add(rs.getInt("profit"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profitData;
    }

}
