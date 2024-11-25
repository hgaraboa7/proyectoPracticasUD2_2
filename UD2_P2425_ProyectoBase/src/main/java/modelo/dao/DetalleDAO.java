/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hector.garaboacasas
 */
public class DetalleDAO {

    public void detallar(Connection conn, String numFactura, DefaultTableModel modelotabla) throws SQLException {
           String consulta="select idproducto from productos where nomproducto like ? ";
           PreparedStatement sentencia = conn.prepareStatement(consulta);
           String insercion="INSERT INTO detalle (numfactura, numdetalle, idproducto, cantidad, precio) VALUES  (?,?,?,?,?)";
           PreparedStatement sentenciaInsercion=conn.prepareStatement(insercion);
           ResultSet rs=null;
           for(int i=0;i<modelotabla.getRowCount();i++){
               sentencia.setString(1,String.valueOf(modelotabla.getValueAt(i, 0)) );
               rs=sentencia.executeQuery();
               if(rs.next()){
                   sentenciaInsercion.setString(1, numFactura);
                   sentenciaInsercion.setInt(2, i+1);
                   sentenciaInsercion.setString(3, rs.getString(1));
                   sentenciaInsercion.setInt(4,(int) modelotabla.getValueAt(i, 1));
                   sentenciaInsercion.setDouble(5,((double)modelotabla.getValueAt(i, 2)/(int) modelotabla.getValueAt(i, 1)));
                   sentenciaInsercion.executeUpdate();
                   
               }
           }
           
        
        
        
        
        
    }
    
    
    
}
