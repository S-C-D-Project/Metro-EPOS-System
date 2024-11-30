package Views.LogIn;
import Views.Decorate.LogInTheme;
import java.io.IOException;

public class AdminLogIn extends LogInTheme {

    public AdminLogIn() throws IOException {
        super.setSelectedAdminButton();
        super.setUnselectedCashierButton();
        super.setUnselectedManagerButton();
        super.setUnselectedDataOperatorButton();
    }
}
