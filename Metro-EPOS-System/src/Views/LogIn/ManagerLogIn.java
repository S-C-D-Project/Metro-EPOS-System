package Views.LogIn;

import Views.Decorate.LogInTheme;

import java.io.IOException;

public class ManagerLogIn extends LogInTheme
{
    public ManagerLogIn() throws IOException
    {
        super.setUnselectedAdminButton();
        super.setUnselectedCashierButton();
        super.setSelectedManagerButton();
        super.setUnselectedDataOperatorButton();
    }
}
