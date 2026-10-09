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
import modelo.Pasajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PasajeroDAO {

    public int guardar(Pasajero pasajero) {

        Connection con = null;
        PreparedStatement psBuscar = null;
        PreparedStatement psInsertar = null;
        ResultSet rs = null;

        try {
            con = ConexionSQL.getConexion();

            // Primero verificamos si el DNI ya existe
            String sqlBuscar = "SELECT id_pasajero "
                    + "FROM Pasajero "
                    + "WHERE dni = ?";

            psBuscar = con.prepareStatement(sqlBuscar);
            psBuscar.setString(1, pasajero.getDni());

            rs = psBuscar.executeQuery();

            // Si ya existe, reutilizamos su ID
            if (rs.next()) {
                return rs.getInt("id_pasajero");
            }

            rs.close();
            rs = null;

            // Si no existe, registramos al nuevo pasajero
            String sqlInsertar = "INSERT INTO Pasajero "
                    + "(dni, nombres, apellidos, correo) "
                    + "VALUES (?, ?, ?, ?)";

            psInsertar = con.prepareStatement(
                    sqlInsertar,
                    Statement.RETURN_GENERATED_KEYS
            );

            psInsertar.setString(1, pasajero.getDni());
            psInsertar.setString(2, pasajero.getNombres());
            psInsertar.setString(3, pasajero.getApellidos());
            psInsertar.setString(4, pasajero.getCorreo());

            int filas = psInsertar.executeUpdate();

            if (filas > 0) {

                rs = psInsertar.getGeneratedKeys();

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar pasajero: " + e.getMessage());

        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }

                if (psBuscar != null) {
                    psBuscar.close();
                }

                if (psInsertar != null) {
                    psInsertar.close();
                }

                ConexionSQL.closeConexion(con);

            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return -1;
    }
}