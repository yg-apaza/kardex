package kardex.controlador.usuario;

import kardex.vista.UIUsuarioMod;

public class CUsuarioMod implements IUsuarioMod
{
    private UIUsuarioMod ventana;
    
    public CUsuarioMod()
    {
        ventana = new UIUsuarioMod(this);
    }
    
    public void cancelar()
    {
        CUsuario usuario = new CUsuario();
        ventana.dispose();
    }
}
