package Views.LogIn;

import Views.Decorate.LogInTheme;

import java.io.IOException;

public class ManagerLogIn extends LogInTheme
{
    public ManagerLogIn()
    {
        super.setUnselectedAdminButton();
        super.setUnselectedCashierButton();
        super.setSelectedManagerButton();
        super.setUnselectedDataOperatorButton();
    }
}
