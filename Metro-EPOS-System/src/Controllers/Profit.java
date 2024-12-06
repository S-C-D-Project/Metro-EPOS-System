package Controllers;

public class Profit {
    private int profitId;
    private int profit;

    public int getProfitId() {
        return profitId;
    }

    public void setProfitId(int profitId) {
        this.profitId = profitId;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Profit(int profitId, int profit, String date) {
        this.profitId = profitId;
        this.profit = profit;
        this.date = date;
    }

    private String date;
}
