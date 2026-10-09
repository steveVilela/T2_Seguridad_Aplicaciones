package controlador;

import dao.VueloDAO;
import modelo.Vuelo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CatalogoServlet", urlPatterns = {"/catalogo"})
public class CatalogoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        VueloDAO vueloDAO = new VueloDAO();

        List<Vuelo> vuelos = vueloDAO.listar();

        request.setAttribute("vuelos", vuelos);

        request.getRequestDispatcher("catalogo.jsp")
                .forward(request, response);
    }
}