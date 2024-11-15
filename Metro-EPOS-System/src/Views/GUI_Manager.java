package Views;
import Views.Frame.frame;

public class GUI_Manager
{
    public GUI_Manager()
    {
        frame f = new frame();
        f.show();
    }

    public static void main(String[] args) {
        GUI_Manager g = new GUI_Manager();
    }
}
