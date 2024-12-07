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
                // Correctly format the monthlyid in YYYYMM format
                query = "SELECT \n"+
                "FORMAT(YEAR(time), '0000') + FORMAT(MONTH(time), '00') AS monthlyid,\n"+
               " SUM(profit) AS profit,\n"+
                "DATENAME(MONTH, time) AS month\n"+
                "FROM monthlyprofit\n"+
                "WHERE time >= DATEADD(MONTH, -12, GETDATE())\n"+
                "GROUP BY YEAR(time), MONTH(time), DATENAME(MONTH, time)\n"+
                "ORDER BY YEAR(time) DESC, MONTH(time) DESC\n";
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
            System.out.println(e.getMessage());
        }
        return profitData;
    }
    public static ArrayList<Integer> getMonthlyProfitData(String startDate, String endDate) {
        ArrayList<Integer> profitData = new ArrayList<>();
        String query = "SELECT profit FROM monthlyprofit WHERE time BETWEEN ? AND ?";

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            java.sql.Date sqlStartDate = new java.sql.Date(sdf.parse(startDate).getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(sdf.parse(endDate).getTime());

            stmt.setDate(1, sqlStartDate);
            stmt.setDate(2, sqlEndDate);

            ResultSet rs = stmt.executeQuery();
            int i=0;
            while (rs.next()) {
                if(i==12)
                    break;
                profitData.add(rs.getInt("profit"));
                i++;

            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return profitData;
    }

    public static ArrayList<String> getProfitAndTimeForToday() {
        ArrayList<String> profitTimeList = new ArrayList<>();

        String query = "SELECT " +
                "(b.totalbill - b.returnamount - b.salesTaxAmount - b.discount + b.additionalcharges) AS profit, " +
                "DATEDIFF(HOUR, b.billdate, GETDATE()) AS hours_since_bill " +
                "FROM bill b " +
                "WHERE CONVERT(DATE, b.billdate) = CONVERT(DATE, GETDATE()) " +
                "ORDER BY b.billdate DESC";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                int profit = rs.getInt("profit");
                int hoursSinceBill = rs.getInt("hours_since_bill");

                String result = "Profit: " + profit + ", Hours: " + hoursSinceBill;
                profitTimeList.add(result);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return profitTimeList;
    }

    public static ArrayList<String> getProfitAndTimeForSpecificDay(String date) {
        ArrayList<String> result = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = "";
        try {
            java.util.Date parsedDate = sdf.parse(date);
            formattedDate = dbDateFormat.format(parsedDate);
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        String query = "SELECT " +
                "DATEPART(HOUR, b.billdate) AS hour, " +
                "bp.price * bp.quantity AS profit " +
                "FROM bill b " +
                "JOIN billProduct bp ON b.billid = bp.billid " +
                "WHERE CONVERT(DATE, b.billdate, 103) = ? " +
                "ORDER BY hour ASC, b.billdate ASC;";


        try (Connection conn =DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {


            ps.setString(1, formattedDate);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int hour = rs.getInt("hour");
                    int profit = rs.getInt("profit");

                    String resultString = "Profit: " + profit+ ", Hour: " + hour ;
                    result.add(resultString);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }

        return result;
    }


}
