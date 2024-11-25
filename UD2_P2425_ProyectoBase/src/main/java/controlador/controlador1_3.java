/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import controlador.factory.DAOFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.ClienteDAO;
import modelo.dao.EmpleadoDAO;
import modelo.dao.FacturaDAO;
import modelo.dao.ProductoDAO;
import modelo.vo.Cliente;
import modelo.vo.Empleado;
import modelo.vo.Factura;
import modelo.vo.Producto;
import vista.Ventana1_1;
import vista.Ventana1_3;

/**
 *
 * @author Acceso a datos
 */
public class controlador1_3 {

    public static DAOFactory mySQLFactory;
    //declara los objetos DAO

    public static Ventana1_3 ventana = new Ventana1_3();

    static DefaultComboBoxModel modelocomboEmpleado = new DefaultComboBoxModel();

    static DefaultComboBoxModel modelocomboProducto = new DefaultComboBoxModel();

    static DefaultTableModel modelotabla = new DefaultTableModel();

    static ProductoDAO producto;

    static EmpleadoDAO empleado;

    static FacturaDAO factura;

    static ClienteDAO cliente;

    public static void iniciar() {
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);

        modelotabla = (DefaultTableModel) ventana.getTblProductos().getModel();

    }

    public static void iniciaFactory() {
        mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        //inicializa los objetos DAO

        producto = mySQLFactory.getProductoDAO();
        empleado = mySQLFactory.getEmpleadoDAO();
        cliente = mySQLFactory.getClienteDAO();
        factura = mySQLFactory.getFacturaDAO();

    }

    public static void cerrarFactory() {
        try {
            mySQLFactory.shutdown();
        } catch (Exception ex) {
            Logger.getLogger(controlador1_3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void buscarCliente() {
        for (int i = modelotabla.getRowCount() - 1; i >= 0; i--) {
            modelotabla.removeRow(i);
        }
        Connection conn = null;
        Cliente cli = null;
        Factura fac = null;

        if (ventana.getTxtIdCliente().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "falta id Cliente");
            return;
        }
        try {
            conn = mySQLFactory.getConnection();
            cli = cliente.buscar(conn, ventana.getTxtIdCliente().getText());
            if (cli != null) {
                System.out.println(cli.toString());
                ventana.getTxtIdCliente().setText(cli.toString());
                if(factura.extraerDatos(conn, cli.getIdcliente(), modelotabla)){
                   JOptionPane.showMessageDialog(null, "Pedidos cargados");
                return; 
                }else{
                    JOptionPane.showMessageDialog(null, "el cliente no tiene pedidos");
                return;
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "no existe ese cliente");
                return;
            }

        } catch (Exception ex) {
            Logger.getLogger(controlador1_3.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}