package Views;

public class UIHandler
{
    public static boolean isProductExist(int pID)
    {
        // Against pID I should get boolean value either the pID exist or not in tables
        return true;
    }

    public static String getProductPrice(int pID, int qty)
    {
        // I will provide qty -> quantity and pID -> Product ID and I should get the price of product
        // Here I assume 100 is the price I get for one product pID (later converted to string after multiply)
        return String.valueOf(qty * 100);
    }

    public static String getProductName(int pID)
    {
        // I provide the pID and I should get the name of the requested Product
        // Here I assume I get Paint as product name
        return "Paint";
    }
}