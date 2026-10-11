package controlador;

import dao.VentaDAO;
import modelo.Venta;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ConsultaServlet", urlPatterns = {"/consulta"})
public class ConsultaServlet extends HttpServlet {

    private VentaDAO ventaDAO = new VentaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("consulta.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String dni = request.getParameter("dni");

        if (dni != null && !dni.trim().isEmpty()) {
            dni = dni.trim();
            List<Venta> listaVentas = ventaDAO.listarPorDni(dni);
            
            request.setAttribute("consultado", true);
            request.setAttribute("dniBuscado", dni);
            request.setAttribute("listaVentas", listaVentas);
        }

        request.getRequestDispatcher("consulta.jsp").forward(request, response);
    }
}