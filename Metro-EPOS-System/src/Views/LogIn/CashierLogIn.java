package Views.LogIn;

import Views.Decorate.LogInTheme;

import java.io.IOException;

public class CashierLogIn extends LogInTheme {
    public CashierLogIn() {
        super.setUnselectedAdminButton();
        super.setUnselectedManagerButton();
        super.setSelectedCashierButton();
        super.setUnselectedDataOperatorButton();
    }
}
