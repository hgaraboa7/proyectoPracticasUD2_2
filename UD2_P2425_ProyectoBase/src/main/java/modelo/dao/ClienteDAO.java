/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.vo.Cliente;

/**
 *
 * @author hector.garaboacasas
 */
public class ClienteDAO {

    public static Cliente buscar(Connection conn, String idCliente) throws SQLException {
    Cliente cliente=null;
    ResultSet rs=null;
    String consulta="{CALL BuscarCliente(?)}";
        CallableStatement sentencia=conn.prepareCall(consulta);
        sentencia.setString(1, idCliente);
        rs=sentencia.executeQuery();
        while(rs.next()){
         cliente=new Cliente(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
    }
        return cliente;
    }
    
}
//CREATE DEFINER=`root`@`localhost` PROCEDURE `BuscarCliente`(
//    IN id VARCHAR(10)
//)
//BEGIN
//    SELECT idcliente, nombrecli, apellidocli, dircli
//    FROM cliente
//    WHERE idcliente = id;
//END