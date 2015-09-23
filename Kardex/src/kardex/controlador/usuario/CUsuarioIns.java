package kardex.controlador.usuario;

import kardex.vista.UIUsuarioIns;

public class CUsuarioIns implements IUsuarioIns
{
    private UIUsuarioIns ventana;
    
    public CUsuarioIns()
    {
        ventana = new UIUsuarioIns(this);
    }
    
    public void cancelar()
    {
        CUsuario usuario = new CUsuario();
        ventana.dispose();
    }
}
