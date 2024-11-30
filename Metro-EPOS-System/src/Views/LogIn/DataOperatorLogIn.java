package Views.LogIn;

import Views.Decorate.LogInTheme;

import javax.xml.crypto.Data;
import java.io.IOException;

public class DataOperatorLogIn extends LogInTheme
{
    public DataOperatorLogIn()
    {
        super.setUnselectedAdminButton();
        super.setUnselectedManagerButton();
        super.setUnselectedCashierButton();
        super.setSelectedDataOperatorButton();
    }
}