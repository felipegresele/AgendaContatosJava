package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO {
	// Modulo de Conexao

	// Parametros de Conexao
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda";
	private String user = "root";
	private String password = "@Felipe150405";

	// Metodo de conexao
	private Connection conectar() throws SQLException, ClassNotFoundException {

		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException | ClassNotFoundException e) {
			throw new SQLException("Erro ao conectar com o banco de dados " + e);
		}
		return con;
	}

	public void testeConexao() {

		try {
			Connection con = conectar();
			System.out.println(con);
			System.out.println("Conectado com o banco de dados" + con);
			con.close();
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}

	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome, fone,email) values (?,?,?)";
		try {
			// abrir a conexao com o banco
			Connection con = conectar();
			// Preparar a query para execucao no banco de dados
			PreparedStatement stmt = con.prepareStatement(create);
			// Substituir os parametros (?) pelo conteúdo das variáveis JavaBeans
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getFone());
			stmt.setString(3, contato.getEmail());

			stmt.executeUpdate();

			stmt.close();
		} catch (Exception e) {
			System.out.println();
		}
	}

	// Crud Read
	public ArrayList<JavaBeans> listarContatos() {
		// Criando obg para acessar a classe javaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "select * from contatos order by nome";

		try {
			Connection con = conectar();
			PreparedStatement stmt = con.prepareStatement(read);
			ResultSet rs = stmt.executeQuery();
			// O laço abaixo sera executado enquanto tiver contatos
			while (rs.next()) {
				// Variaveis de apoio que recebem os dados do banco
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				// populando o ArrayList

				// Adiciona nas variaveis da classe JavaBeans os dados que recebe
				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}

			con.close();
			return contatos;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/*CRUD UPDATE*/
	//selecionar o contato
	public void selecionarContato(JavaBeans contato) {
		String read2 = "select * from contatos where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement stmt = con.prepareStatement(read2);
			stmt.setString(1, contato.getIdcon());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				//setar as variaveis do JavaBeans
				
				//contato vai acessar os conteudos dos campos
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			con.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//editar contato
	public void alterarContato(JavaBeans contato) {
		String create = "update contatos set nome=?,fone=?,email=? where idcon=?";
		
		try {
			Connection con = conectar();
			PreparedStatement stmt = con.prepareStatement(create);
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getFone());
			stmt.setString(3, contato.getEmail());
			stmt.setString(4, contato.getIdcon());
			
			stmt.executeUpdate();
			con.close();
			
			} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//deletar contato
	public void deletarContato(JavaBeans contato) {
		String delete = "delete from contatos where idcon=?";
		
		try {
			Connection con = conectar();
			PreparedStatement stmt = con.prepareStatement(delete);
			stmt.setString(1,  contato.getIdcon());
			
			stmt.executeUpdate();
			
			con.close();

		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
}
