package Models;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ProfitModel {
    public static ArrayList<Integer> getProfitData(String timePeriod) {
        ArrayList<Integer> profitData = new ArrayList<>();
        String query = "";

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


        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {


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

    public static ArrayList<Integer> getProfitDataForTimeSlot(String startDate, String endDate) {
        ArrayList<Integer> profitData = new ArrayList<>();
        String query = "SELECT profit FROM graph WHERE profitDate BETWEEN ? AND ?";

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            java.sql.Date sqlStartDate = new java.sql.Date(sdf.parse(startDate).getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(sdf.parse(endDate).getTime());

            stmt.setDate(1, sqlStartDate);
            stmt.setDate(2, sqlEndDate);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                profitData.add(rs.getInt("profit"));
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");

        } catch (SQLException e) {

        }
        return profitData;
    }

}
