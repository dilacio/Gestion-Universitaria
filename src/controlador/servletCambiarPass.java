package controlador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocioImpl.LoginNegocioImpl;

/**
 * Servlet implementation class servletCambiarPass
 */
@WebServlet("/servletCambiarPass")
public class servletCambiarPass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public servletCambiarPass() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/cambiarPass.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("btnCambiar") != null) {

			String Contrase�aNueva = (String) request.getParameter("passnew");
			String Contrase�aViejaQueEscribe = (String) request.getParameter("pass");
			String Contrase�aViejaSession = (String) request.getParameter("passold");
			String User = (String) request.getParameter("user");
			int Legajo = Integer.parseInt(request.getParameter("legajo"));
			
			LoginNegocioImpl lnegocio = new LoginNegocioImpl();
			
			if (lnegocio.validarLogin(User, Contrase�aViejaQueEscribe) == true) {
				if (Contrase�aViejaQueEscribe.equals(Contrase�aViejaSession) == true) {
					if (Contrase�aNueva.equals(Contrase�aViejaQueEscribe) == false) {
						if (lnegocio.CambiarPass(Legajo, Contrase�aNueva) == true) {
							String mensaje4;
							mensaje4 = "La contrLa contrase�a fue cambiada correctamentease�a fue cambiada correctamente";
							request.setAttribute("mensaje4", mensaje4);
						}
					} else if (Contrase�aNueva.equals(Contrase�aViejaQueEscribe) == true) {
						String mensaje2;
						mensaje2 = "error la contrase�a nueva no puede ser igual a la antigua";
						request.setAttribute("mensaje2", mensaje2);
					}
				}

			} else {
				String mensaje1;
				mensaje1 = "error contrase�a vieja";
				request.setAttribute("mensaje1", mensaje1);
			}

		}
		RequestDispatcher rd = request.getRequestDispatcher("/cambiarPass.jsp");
		rd.forward(request, response);
	}

}
