package br.senac.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Lista2_04
 */
//@WebServlet("/Lista2_04")
public class Lista2_04 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Lista2_04() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] numeros = request.getParameterValues("numero");
		int num1 = Integer.parseInt(numeros[0]);
		int num2 = Integer.parseInt(numeros[1]);
		int soma = num1+num2;
			response.getWriter().println("Os numeros digitados foram "+numeros[0]+" e "+numeros[1]+
					".\nA soma dos dois numeros é "+soma);

		}
		
	}


