package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.Document;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();
	
	public Controller() {
		super();
	}

	// metodo principal
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			novoContato(request, response);
		} else if (action.equals("/select")) {
			listarContato(request, response);
		} else if (action.equals("/update")) {
			editarContato(request, response);
		} else if (action.equals("/delete")) {
			excluirContato(request, response);
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		}
		else {
			response.sendRedirect("index.html"); //se receber alguma requisicao que nao tem ele vai redirecionar para a pag principal
		}
	}

	// Lista Contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		//Criando um obj que irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();
		
		//Encaminhar a lista de documentos ao arquivo agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
		
		//para aparecer a lista no console tambem
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i).getIdcon());
			System.out.println(lista.get(i).getNome());
			System.out.println(lista.get(i).getFone());
			System.out.println(lista.get(i).getEmail());
		}
	}
	
	protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//teste de recebimento dos dados do formulário
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("fone"));
		System.out.println(request.getParameter("email"));
		
		//setar as variaveis do javaBeans
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		
		//invocar o metodo inserirContato passando o objeto contato
		dao.inserirContato(contato);
		
		//redirecionar para documento agenda.jsp
		response.sendRedirect("main");
	}
	
	//Editar contato
	protected void listarContato(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		//Recebimento do id do contato que será editado
		String idcon = request.getParameter("idcon");
		
		//setar a variável JavaBeans
		contato.setIdcon(idcon);	
		
		dao.selecionarContato(contato);
		
		//teste de recebimento
		System.out.println(contato.getIdcon());
		System.out.println(contato.getNome());
		System.out.println(contato.getFone());
		System.out.println(contato.getEmail());
		
		//setar os atributos do formulario com o conteudo JavaBeans
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());
		
		//encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
	
	protected void editarContato (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//setar as variaveis do JavaBeans
		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		
		//executar o metodo alterarContato
		dao.alterarContato(contato);
		
		//redirecionar para o documento agenda.jsp(atualizando as alterações)
		response.sendRedirect("main");
	}
	
	protected void excluirContato (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//setar as variaveis do JavaBeans
		
		//recebimento do id do contato a ser excluído (validador.js)
		String idcon = request.getParameter("idcon");
		
		contato.setIdcon(idcon);		
		
		dao.deletarContato(contato);
		
		response.sendRedirect("main");
		
	}
	
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Document documento = new Document();
		
		try {
			response.setContentType("apllication/pdf");
			//nome do documento
			response.addHeader("Content-Disposition", "inline; filename=" + "contatos.pdf");
			//criar o documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			//abrir o documento => conteúdo
			documento.open();s
			documento.add(new Paragraph("Lista de "));
			} catch(Exception e) {
			System.out.println(e);
			documento.close();
		}
	}
}
