package controlador;

import dao.PasajeroDAO;
import dao.VentaDAO;
import dao.VueloDAO;
import modelo.Pasajero;
import modelo.Venta;
import modelo.Vuelo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CompraServlet", urlPatterns = {"/compra"})
public class CompraServlet extends HttpServlet {

    private VueloDAO vueloDAO = new VueloDAO();
    private PasajeroDAO pasajeroDAO = new PasajeroDAO();
    private VentaDAO ventaDAO = new VentaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.trim().isEmpty()) {
            response.sendRedirect("catalogo");
            return;
        }

        try {
            int idVuelo = Integer.parseInt(idParam);
            Vuelo vuelo = vueloDAO.buscar(idVuelo);

            if (vuelo == null) {
                response.sendRedirect("catalogo");
                return;
            }

            request.setAttribute("vuelo", vuelo);
            request.getRequestDispatcher("compra.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect("catalogo");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Soporte para caracteres especiales (tildes, ñ)
        request.setCharacterEncoding("UTF-8");

        String idVueloParam = request.getParameter("idVuelo");
        String dni = request.getParameter("dni");
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String correo = request.getParameter("correo");

        // Manejo y recuperación del vuelo seguro desde BD
        int idVuelo = 0;
        Vuelo vuelo = null;
        try {
            idVuelo = Integer.parseInt(idVueloParam);
            vuelo = vueloDAO.buscar(idVuelo);
        } catch (Exception e) {
            response.sendRedirect("catalogo");
            return;
        }

        if (vuelo == null) {
            response.sendRedirect("catalogo");
            return;
        }

        // --- VALIDACIÓN EN EL SERVIDOR (OWASP) ---
        String error = null;
        if (dni == null || !dni.trim().matches("^[0-9]{8}$")) {
            error = "El DNI ingresado no es válido. Debe contener exactamente 8 dígitos numéricos.";
        } else if (nombres == null || nombres.trim().isEmpty()) {
            error = "El campo nombres es obligatorio.";
        } else if (apellidos == null || apellidos.trim().isEmpty()) {
            error = "El campo apellidos es obligatorio.";
        } else if (correo == null || correo.trim().isEmpty()) {
            error = "El campo correo electrónico es obligatorio.";
        }

        // Si falla alguna validación, vuelve al formulario con los datos ingresados
        if (error != null) {
            request.setAttribute("error", error);
            request.setAttribute("vuelo", vuelo);
            request.setAttribute("dni", dni);
            request.setAttribute("nombres", nombres);
            request.setAttribute("apellidos", apellidos);
            request.setAttribute("correo", correo);
            request.getRequestDispatcher("compra.jsp").forward(request, response);
            return;
        }

        // --- REGISTRO SEGURO ---
        // 1. Guardar o reutilizar pasajero
        Pasajero pasajero = new Pasajero();
        pasajero.setDni(dni.trim());
        pasajero.setNombres(nombres.trim());
        pasajero.setApellidos(apellidos.trim());
        pasajero.setCorreo(correo.trim());

        int idPasajero = pasajeroDAO.guardar(pasajero);

        if (idPasajero > 0) {
            // 2. Registrar venta (utilizando el precio del objeto Vuelo recuperado de la BD)
            int idVenta = ventaDAO.registrar(idPasajero, vuelo);

            if (idVenta > 0) {
                // Recuperar la venta completa registrada
                Venta ventaRealizada = ventaDAO.buscarPorId(idVenta);
                pasajero.setIdPasajero(idPasajero);
                ventaRealizada.setPasajero(pasajero);
                ventaRealizada.setVuelo(vuelo);

                request.setAttribute("venta", ventaRealizada);
                request.getRequestDispatcher("exito.jsp").forward(request, response);
                return;
            }
        }

        // Fallo inesperado en BD
        request.setAttribute("error", "Ocurrió un error al procesar la compra en la base de datos.");
        request.setAttribute("vuelo", vuelo);
        request.getRequestDispatcher("compra.jsp").forward(request, response);
    }
}