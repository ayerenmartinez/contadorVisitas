package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

@WebServlet("/contador")
public class ContadorVisitasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        int contador = 0;
        Cookie[] cookies = request.getCookies(); //Obtengo todas las cookies de mi navegador (request)

        if (cookies != null) {
            Optional<Cookie> cookieOptional =
                    // Busca una cookie con el nombre "contadorDeVisitas"
                    // y la convierte a un Optional
                    Arrays.stream(cookies)
                            .filter(cookie -> cookie.getName().equals("contadorDeVisitas"))
                            .findFirst();

            if (cookieOptional.isPresent()) {
                contador = Integer.parseInt(cookieOptional.get().getValue());
            }
        }

        //Incrementamos el contador
        contador++;

        //Creamos la cookie
        Cookie cookieContadorVisitas = new Cookie("contadorDeVisitas",String.valueOf(contador));
        response.addCookie(cookieContadorVisitas);

        out.println("Contador de Visitas: "+contador);
    }
}
