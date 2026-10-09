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
import java.util.ArrayList;
import java.util.List;

public class VueloDAO {

    // Lista los vuelos desde la fecha actual en adelante
    public List<Vuelo> listar() {

        List<Vuelo> lista = new ArrayList<>();

        String sql = "SELECT id_vuelo, numero_vuelo, origen, destino, "
                   + "fecha, hora, precio "
                   + "FROM Vuelo "
                   + "WHERE fecha >= CAST(GETDATE() AS DATE) "
                   + "ORDER BY fecha, hora";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConexionSQL.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                Vuelo vuelo = new Vuelo();

                vuelo.setIdVuelo(rs.getInt("id_vuelo"));
                vuelo.setNumeroVuelo(rs.getString("numero_vuelo"));
                vuelo.setOrigen(rs.getString("origen"));
                vuelo.setDestino(rs.getString("destino"));
                vuelo.setFecha(rs.getDate("fecha"));
                vuelo.setHora(rs.getTime("hora"));
                vuelo.setPrecio(rs.getBigDecimal("precio"));

                lista.add(vuelo);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar vuelos: " + e.getMessage());

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

        return lista;
    }


    // Busca un vuelo por su ID
    public Vuelo buscar(int id) {

        Vuelo vuelo = null;

        String sql = "SELECT id_vuelo, numero_vuelo, origen, destino, "
                   + "fecha, hora, precio "
                   + "FROM Vuelo "
                   + "WHERE id_vuelo = ?";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConexionSQL.getConexion();

            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                vuelo = new Vuelo();

                vuelo.setIdVuelo(rs.getInt("id_vuelo"));
                vuelo.setNumeroVuelo(rs.getString("numero_vuelo"));
                vuelo.setOrigen(rs.getString("origen"));
                vuelo.setDestino(rs.getString("destino"));
                vuelo.setFecha(rs.getDate("fecha"));
                vuelo.setHora(rs.getTime("hora"));
                vuelo.setPrecio(rs.getBigDecimal("precio"));
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar vuelo: " + e.getMessage());

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

        return vuelo;
    }
}

