/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Luis
 */

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Venta {

    private int idVenta;
    private String codigo;
    private int idPasajero;
    private int idVuelo;
    private BigDecimal precio;
    private Timestamp fechaCompra;
    
    private Pasajero pasajero;
    private Vuelo vuelo;

    // Constructor vacio
    public Venta() {
    }

    // Constructor con parametros
    public Venta(int idVenta, String codigo, int idPasajero,
                 int idVuelo, BigDecimal precio, Timestamp fechaCompra) {
        this.idVenta = idVenta;
        this.codigo = codigo;
        this.idPasajero = idPasajero;
        this.idVuelo = idVuelo;
        this.precio = precio;
        this.fechaCompra = fechaCompra;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Timestamp getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Timestamp fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    
    //////////
    
    public Pasajero getPasajero() {
    return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }
}