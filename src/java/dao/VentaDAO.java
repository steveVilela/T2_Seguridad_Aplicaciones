/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Luis
 */

import conexion.ConexionSQL;
import modelo.Vuelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import modelo.Pasajero;
import modelo.Venta;

public class VentaDAO {

    public int registrar(int idPasajero, Vuelo vuelo) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConexionSQL.getConexion();

            // Generar codigo aleatorio de 6 caracteres
            String codigo = UUID.randomUUID()
                    .toString()
                    .replace("-", "")
                    .substring(0, 6)
                    .toUpperCase();

            String sql = "INSERT INTO Venta "
                    + "(codigo, id_pasajero, id_vuelo, precio, fecha_compra) "
                    + "VALUES (?, ?, ?, ?, GETDATE())";

            ps = con.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, codigo);
            ps.setInt(2, idPasajero);
            ps.setInt(3, vuelo.getIdVuelo());
            ps.setBigDecimal(4, vuelo.getPrecio());

            int filas = ps.executeUpdate();

            if (filas > 0) {

                rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar venta: " + e.getMessage());

        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }

                ConexionSQL.closeConexion(con);

            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return -1;
        
    }
    
    
    public List<Venta> listarPorDni(String dni) {

        List<Venta> lista = new ArrayList<>();

        String sql = "SELECT "
                + "ve.id_venta, ve.codigo, ve.id_pasajero, ve.id_vuelo, "
                + "ve.precio, ve.fecha_compra, "
                + "p.dni, p.nombres, p.apellidos, p.correo, "
                + "v.numero_vuelo, v.origen, v.destino, "
                + "v.fecha, v.hora "
                + "FROM Venta ve "
                + "INNER JOIN Pasajero p "
                + "ON ve.id_pasajero = p.id_pasajero "
                + "INNER JOIN Vuelo v "
                + "ON ve.id_vuelo = v.id_vuelo "
                + "WHERE p.dni = ? "
                + "ORDER BY ve.fecha_compra DESC";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            con = ConexionSQL.getConexion();

            ps = con.prepareStatement(sql);
            ps.setString(1, dni);

            rs = ps.executeQuery();

            while (rs.next()) {

                // Datos de Venta
                Venta venta = new Venta();

                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setCodigo(rs.getString("codigo"));
                venta.setIdPasajero(rs.getInt("id_pasajero"));
                venta.setIdVuelo(rs.getInt("id_vuelo"));
                venta.setPrecio(rs.getBigDecimal("precio"));
                venta.setFechaCompra(rs.getTimestamp("fecha_compra"));

                // Datos de Pasajero
                Pasajero pasajero = new Pasajero();

                pasajero.setIdPasajero(rs.getInt("id_pasajero"));
                pasajero.setDni(rs.getString("dni"));
                pasajero.setNombres(rs.getString("nombres"));
                pasajero.setApellidos(rs.getString("apellidos"));
                pasajero.setCorreo(rs.getString("correo"));

                // Datos de Vuelo
                Vuelo vuelo = new Vuelo();

                vuelo.setIdVuelo(rs.getInt("id_vuelo"));
                vuelo.setNumeroVuelo(rs.getString("numero_vuelo"));
                vuelo.setOrigen(rs.getString("origen"));
                vuelo.setDestino(rs.getString("destino"));
                vuelo.setFecha(rs.getDate("fecha"));
                vuelo.setHora(rs.getTime("hora"));

                // Relacionamos los objetos
                venta.setPasajero(pasajero);
                venta.setVuelo(vuelo);

                lista.add(venta);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al listar ventas por DNI: " + e.getMessage()
            );

        } finally {

            try {

                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }

                ConexionSQL.closeConexion(con);

            } catch (SQLException e) {

                System.out.println(
                        "Error al cerrar recursos: " + e.getMessage()
                );
            }
        }

        return lista;
    }

    
    
    
    
        public Venta buscarPorId(int idVenta) {

        Venta venta = null;

        String sql = "SELECT id_venta, codigo, id_pasajero, id_vuelo, "
                   + "precio, fecha_compra "
                   + "FROM Venta "
                   + "WHERE id_venta = ?";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConexionSQL.getConexion();

            ps = con.prepareStatement(sql);
            ps.setInt(1, idVenta);

            rs = ps.executeQuery();

            if (rs.next()) {

                venta = new Venta();

                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setCodigo(rs.getString("codigo"));
                venta.setIdPasajero(rs.getInt("id_pasajero"));
                venta.setIdVuelo(rs.getInt("id_vuelo"));
                venta.setPrecio(rs.getBigDecimal("precio"));
                venta.setFechaCompra(rs.getTimestamp("fecha_compra"));
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar venta: " + e.getMessage());

        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }

                ConexionSQL.closeConexion(con);

            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return venta;
    }
}