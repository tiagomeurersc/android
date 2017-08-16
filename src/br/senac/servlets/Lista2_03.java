package br.senac.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Lista2_03
 */
//@WebServlet("/Lista2_03")
public class Lista2_03 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Lista2_03() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Pega o valor de um atributo a partir da chave
		String nome = request.getParameter("nome");
		String idade = request.getParameter("idade");
		response.getWriter().println("O nome digitado foi "+nome+", e a idade é "+idade+"."
				+ "\nEsta mensagem foi passada utilizando o metodo doGet");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String nome = request.getParameter("nome");
		String idade = request.getParameter("idade");
		response.getWriter().println("O nome digitado foi "+nome+", e a idade é "+idade+""
				+ "\nEsta mensagem foi passada utilizando o metodo doPost");


	}

}
