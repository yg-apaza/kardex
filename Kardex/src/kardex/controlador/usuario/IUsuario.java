package kardex.controlador.usuario;

import javax.swing.JTable;

public interface IUsuario
{
    public void menu();
    public void registrar();
    public void cargar(JTable tblRegistros);
}
