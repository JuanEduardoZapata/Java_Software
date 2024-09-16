//Esta es mi evidencia Codificación de módulos del software según requerimientos del proyecto
//Mi proyecto se trata de un software de gestion de inventarios, en este caso para la actividad realize una pequeña tabla en una base de datos MySql que almacena algunos productos de ejemplo
// y me permite realizar operaciones basicas como insertar, actualizar y borrar datos.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateDB {
    
    String bd = "actividad";
    String url = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String password = "";
    Connection cx;

    public Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cx = DriverManager.getConnection(url + bd, user, password);
            System.out.println("Conectado");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cx;
    }

    public void consultarNombres() {
        try {
            Statement st = cx.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM productos");
            while (rs.next()) {
                System.out.println(rs.getString("Nombre"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   // metodo para insertar productos
   public void insertarProducto(String nombre) {
    try {
        Statement st = cx.createStatement();
        String query = "INSERT INTO Productos (Nombre) VALUES ('" + nombre + "')";
        st.executeUpdate(query);
        System.out.println("Producto insertado: " + nombre);
    }catch (Exception e) {
        e.printStackTrace();
    }
}

    //metodo para actualizar datos
    public void actualizar(int id, String nuevoNombre) {
        try {
            Statement st = cx.createStatement();
            String query = "UPDATE productos SET Nombre = '" + nuevoNombre + "' WHERE id = " + id;
            st.executeUpdate(query);
            System.out.println("Producto actualizado: " + nuevoNombre);
        } catch (Exception e) {
            Logger.getLogger(CreateDB.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // metodo para borrar un dato
    public void borrar(String nombre) {
        try {
            Statement st = cx.createStatement();
            String query = "DELETE FROM productos WHERE Nombre = '" + nombre + "'";
            st.executeUpdate(query);
            System.out.println("Producto eliminado: " + nombre);
        } catch (Exception e) {
            Logger.getLogger(CreateDB.class.getName()).log(Level.SEVERE, null, e);
        }
    }
  
    public static void main(String[] args) {
        CreateDB bd = new CreateDB();
        bd.conectar();
        System.out.println("");
        bd.insertarProducto("Sopa");
        bd.consultarNombres();
        System.out.println("");
        bd.borrar("Sopa");
        bd.actualizar(2, "Pan de maiz");
        bd.consultarNombres();
    }
}
