/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import modelo.vo.Empleado;
import modelo.vo.Factura;

/**
 *
 * @author hector.garaboacasas
 */
public class FacturaDAO {

    public void generar(Connection conn, String numFactura, String idCliente, Empleado empleado, Date date, boolean cobrada, double iva) throws SQLException {
        String consulta="Insert into factura(numfactura, idcliente, idempleado, fecha, cobrada, iva)values(?,?,?,?,?,?)";
        PreparedStatement sentencia=conn.prepareStatement(consulta);
        sentencia.setString(1, numFactura);
        sentencia.setString(2, idCliente);
        sentencia.setString(3, empleado.getIdempleado());
        sentencia.setDate(4, date);
        sentencia.setBoolean(5, cobrada);
        sentencia.setDouble(6, iva);
        sentencia.executeUpdate();
        
    }

    public boolean extraerDatos(Connection conn, String idcliente, DefaultTableModel modelotabla) throws SQLException {
 
        boolean comp=false;
        
        
        String consulta="select numfactura, fecha, cobrada from factura where idcliente like ?";
        PreparedStatement sentencia=conn.prepareStatement(consulta);
        sentencia.setString(1, idcliente);
        
        String consulta2="select cantidad, precio from detalle where numfactura like ?";
        PreparedStatement sentencia2=conn.prepareStatement(consulta2);
                
        ResultSet rs=sentencia.executeQuery();
        while(rs.next()){
            double precio=0.0;
            modelotabla.setRowCount(modelotabla.getRowCount() + 1);

            sentencia2.setString(1, rs.getString(1));
            ResultSet rs2=sentencia2.executeQuery();
            while(rs2.next()){
                precio+=rs2.getInt(1)*rs2.getDouble(2);
            }
            modelotabla.setValueAt(rs.getDate(2), modelotabla.getRowCount() - 1, 0);
            modelotabla.setValueAt(precio, modelotabla.getRowCount() - 1, 1);
            modelotabla.setValueAt(rs.getInt(3), modelotabla.getRowCount() - 1, 2);
            
            
          comp=true;
            
        }
        
        
      return comp;  
        
    }

   
    
    
}
