/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_2ev;
import java.io.File;
import java.sql.*;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
/**
 *
 * @author pserr
 */
public class Proyecto_2Ev {

    public static void main(String[] args)throws SQLException {       
        System.out.println("BIENVENIDO AL TRABAJO DE LA 2º EVALUACIÓN.\nAutor: Pablo Serrano Manzarbeitia\nCurso: 2º DAM 2019-2020 (UFV)");
        menu();
    }
    public static void menu() throws SQLException{
        System.err.println("------------------------------------------------");
        System.out.println("Ecoja que apartado desea realizar (Indique nº):");
        System.out.println("1)Conexión a BBDD.\n2)Acceso fichero con DOM y SAX\n3)Salir");
        Scanner sc = new Scanner(System.in);
        String respuesta = sc.nextLine();
        if(respuesta.equals("1")||respuesta.equals("Conexión")||respuesta.equals("conexion")||respuesta.equals("Conexion a BBDD")){
            conexionBBDD();
        }else if(respuesta.equals("2")||respuesta.equals("DOM y SAX")){
            domysax();
        }else if(respuesta.equals("3")){
            System.exit(0);
        }
        System.err.println("------------------------------------------------");
    }
    public static void conexionBBDD() throws SQLException{
        try{
            Scanner sc = new Scanner(System.in);
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/tienda","root", "");        
            System.out.println("Conexión a BBDD tienda realizada");
            Statement st = conexion.createStatement();
            System.out.println("¿Qué desea realizar?(Inserte nº)\n1)Insertar un nuevo cliente\n2)Insertar un nuevo producto\n3)Ver todos los clientes\n4)Ver todos los productos");
            String respuesta = sc.nextLine();
            if(respuesta.equals("1")){
                /*System.out.println("Indique el codigo del nuevo cliente:");
                String codigo = sc.nextLine();*/
                System.out.println("Indique el nombre del nuevo cliente:");
                String cliente = sc.nextLine();
                System.out.println("Indique el nº de telefono del nuevo cliente:");
                String telefono = sc.nextLine();
                System.out.println("Indique el email del nuevo cliente:");
                String email = sc.nextLine();
                st.executeUpdate("INSERT INTO `cliente`(`cod_cliente`, `nombre`, `telefono`, `email`) VALUES (NULL,'"+cliente+"','"+telefono+"','"+email+"')");
                System.out.println("\nSe ha insertado el registro:\nNombre:"+cliente+"\tTeléfono:"+telefono+"\tE-mail:"+email+"\n");
            }else if(respuesta.equals("2")){
                System.out.println("Indique el nombre del nuevo producto:");
                String producto = sc.nextLine();
                System.out.println("Indique el stock del nuevo producto:");
                String stock = sc.nextLine();
                System.out.println("Indique el precio del nuevo producto:");
                String precio = sc.nextLine();
                st.executeUpdate("INSERT INTO `producto`(`cod_producto`, `nombre`, `stock`, `precio`) VALUES (NULL,'"+producto+"','"+stock+"','"+precio+"')");
                System.out.println("\nSe ha insertado el registro:\nProducto:"+producto+"\tStock:"+stock+"\tPrecio:"+precio+"€\n");
            }else if(respuesta.equals("3")){
                ResultSet rs = st.executeQuery("SELECT * FROM cliente");
                System.out.println("CODIGO\tNOMBRE\t\tTELÉFONO\t\tE-MAIL\n--------------------------------------------------\n");
                while(rs.next()){
                    System.out.println(rs.getObject("cod_cliente")+"\t"+rs.getObject("nombre")+"\t\t"+rs.getObject("telefono")+"\t\t"+rs.getObject("email"));
                }
                rs.close();
            }else if(respuesta.equals("4")){
                ResultSet rs = st.executeQuery("SELECT * FROM producto");                
                System.out.println("CODIGO\tNOMBRE\t\tSTOCK\t\tPRECIO\n--------------------------------------------------\n");
                while(rs.next()){
                    System.out.println(rs.getObject("cod_producto")+"\t"+rs.getObject("nombre")+"\t\t"+rs.getObject("stock")+"\t\t"+rs.getObject("precio"));
                }
                rs.close();
            }
            menu();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void domysax(){
        
    }
}
