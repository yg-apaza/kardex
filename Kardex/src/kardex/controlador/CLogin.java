package kardex.controlador;

import kardex.vista.UILogin;

public class CLogin implements ILogin
{
    private UILogin ventana;
    
    public CLogin()
    {
        this.ventana = new UILogin(this);
    }
}
