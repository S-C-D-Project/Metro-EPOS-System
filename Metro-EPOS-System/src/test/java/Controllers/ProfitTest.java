package Controllers;

import Controllers.Profit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProfitTest {

    @Test
    public void testConstructorAndGetters() {
        // Initialize test data
        int profitId = 1;
        int profit = 1000;
        String date = "2024-12-09";

        // Create Profit object
        Profit profitObj = new Profit(profitId, profit, date);

        // Verify the constructor initializes the fields correctly
        assertEquals(profitId, profitObj.getProfitId());
        assertEquals(profit, profitObj.getProfit());
        assertEquals(date, profitObj.getDate());
    }
    @Test
    public void testSettersAndGetters() {
        // Create a Profit object with initial values
        Profit profitObj = new Profit(1, 1000, "2024-12-09");

        // Update the fields using setter methods
        profitObj.setProfitId(2);
        profitObj.setProfit(2000);
        profitObj.setDate("2024-12-10");

        // Verify that the setter methods update the fields correctly
        assertEquals(2, profitObj.getProfitId());
        assertEquals(2000, profitObj.getProfit());
        assertEquals("2024-12-10", profitObj.getDate());
    }
    @Test
    public void testProfitId() {
        // Create a Profit object
        Profit profitObj = new Profit(1, 1000, "2024-12-09");

        // Verify the initial profit ID
        assertEquals(1, profitObj.getProfitId());

        // Update the profit ID using setter method
        profitObj.setProfitId(2);

        // Verify that the profit ID is updated
        assertEquals(2, profitObj.getProfitId());
    }
    @Test
    public void testProfit() {
        // Create a Profit object
        Profit profitObj = new Profit(1, 1000, "2024-12-09");

        // Verify the initial profit
        assertEquals(1000, profitObj.getProfit());

        // Update the profit using setter method
        profitObj.setProfit(1500);

        // Verify the updated profit
        assertEquals(1500, profitObj.getProfit());
    }
    @Test
    public void testDateField() {
        // Create a Profit object
        Profit profitObj = new Profit(1, 1000, "2024-12-09");

        // Verify the initial date
        assertEquals("2024-12-09", profitObj.getDate());

        // Update the date using setter method
        profitObj.setDate("2024-12-10");

        // Verify the updated date
        assertEquals("2024-12-10", profitObj.getDate());
    }
}
