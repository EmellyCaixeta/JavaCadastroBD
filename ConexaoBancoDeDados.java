import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ConexaoBancoDeDados 
{
	private Connection conexao;
	private final String URL_Bancodedados = "jdbc:mysql://localhost:3306/nomebd";
	private final String usuario = "root";
	private final String senha = "admin"; 

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private void IniciarConexao()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexao = DriverManager.getConnection(URL_Bancodedados,usuario,senha);
			System.out.println("Conexao realizada com sucesso!");
			}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Erro ao acessar o Banco de Dados!");
		}
	}
	
	private void EncerrarConexao() throws SQLException
	{
		if(conexao != null)
			conexao.close();
	}
	
	
	public String InserirDados(Pessoa ObjetoPessoa) throws SQLException
	{
		IniciarConexao(); //Solicitar conexao com o banco de dados
		
		if(conexao != null)
		{
			//inserir os parametros Nome, Endereco e Telefone na Tabela Pessoa
			
			PreparedStatement psInsert = conexao.prepareStatement("INSERT INTO pessoa(nome, endereco, telefone, cpf, tiposanguineo, fatorrh, curso, telefoneemergencia, contatoemergencia, peso, altura, imc) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			psInsert.setString(1, ObjetoPessoa.getNome());
			psInsert.setString(2, ObjetoPessoa.getEndereco());
			psInsert.setString(3, ObjetoPessoa.getTelefone());
			psInsert.setString(4, ObjetoPessoa.getCPF()); 
			psInsert.setString(5, ObjetoPessoa.getTipoSanguineo());
			psInsert.setString(6, ObjetoPessoa.getfatorRH());
			psInsert.setString(7, ObjetoPessoa.getCurso());
			psInsert.setString(8, ObjetoPessoa.getTelefoneEmergencia());
			psInsert.setString(9, ObjetoPessoa.getContatoEmergencia());
			psInsert.setDouble(10, ObjetoPessoa.getPeso());
			psInsert.setDouble(11, ObjetoPessoa.getAltura());
			psInsert.setDouble(12, ObjetoPessoa.calcularIMC());
			psInsert.execute();
			
			EncerrarConexao(); //encerrar conexao com o Banco de dados
			
			return "Cadastro realizado com sucesso!";
		}
		else return "Erro! Inserção não realizada!";
	}

	public String AlterarDados(Pessoa ObjetoPessoa, int id) throws SQLException
	{
		IniciarConexao(); //solicitar conexao com o banco de dados
		
		if(conexao != null)
		{
			
			PreparedStatement comandoupdate = conexao.prepareStatement("UPDATE pessoa SET endereco = ?, telefone = ?, nome = ?, cpf = ?, tiposanguineo = ?, fatorrh = ?, curso = ?, telefoneemergencia = ?, contatoemergencia = ?, peso = ?, altura = ?, imc = ? WHERE id = ?");
			comandoupdate.setString(1, ObjetoPessoa.getEndereco());
			comandoupdate.setString(2, ObjetoPessoa.getTelefone());
			comandoupdate.setString(3, ObjetoPessoa.getNome());
			comandoupdate.setString(4, ObjetoPessoa.getCPF());
			comandoupdate.setString(5, ObjetoPessoa.getTipoSanguineo());
			comandoupdate.setString(6, ObjetoPessoa.getfatorRH());
			comandoupdate.setString(7, ObjetoPessoa.getCurso());
			comandoupdate.setString(8, ObjetoPessoa.getTelefoneEmergencia());
			comandoupdate.setString(9, ObjetoPessoa.getContatoEmergencia());
			comandoupdate.setDouble(10, ObjetoPessoa.getPeso());
			comandoupdate.setDouble(11, ObjetoPessoa.getAltura());
			comandoupdate.setDouble(12, ObjetoPessoa.calcularIMC());
			comandoupdate.setInt(13, id);
			comandoupdate.execute();
			
			EncerrarConexao(); //encerrar conexao com o Banco de dados
			
			return "Alteração realizada com sucesso!";
		}
		else return "Erro! Alteração não realizada!";
	}

public String RemoveDados(int id) throws SQLException
{
	IniciarConexao(); //solicitar conexao com o banco de dados
	
	if(conexao != null)
	{
		//deletar um registro do banco de dados
		
		PreparedStatement comandoupdate = conexao.prepareStatement("DELETE FROM pessoa where id = ?");
		comandoupdate.setInt(1, id);
		comandoupdate.execute();
		
		EncerrarConexao(); 
		
		return "Remoção realizada com sucesso!";
	}
	else return "Erro! Remoção não realizada!";

  }

public ArrayList<String> Relatorio() throws SQLException
{
	IniciarConexao();
	
	ArrayList<String> relatorioBancoDeDados = new ArrayList<String>();
	
	if(conexao != null)
	{
		Statement comandoconsulta = conexao.createStatement();
		ResultSet resultadoconsultaBD = comandoconsulta.executeQuery("SELECT * FROM pessoa");
		String resultado;
		String nome;
		String endereco;
		String telefone;
		String cpf;
		String tiposanguineo;
		String fatorrh;
		String curso;
		String contatoemergencia;
		String telefoneemergencia;
		String peso;
		String altura;
		String imc;
		String id;
		while(resultadoconsultaBD.next())
		{
			nome = resultadoconsultaBD.getString("nome") + " ";
			endereco = resultadoconsultaBD.getString("endereco") + " ";
			telefone = resultadoconsultaBD.getString("telefone") + " ";
			cpf = resultadoconsultaBD.getString("cpf") + " ";
			tiposanguineo = resultadoconsultaBD.getString("tiposanguineo") + " ";
			fatorrh = resultadoconsultaBD.getString("fatorrh") + " ";
			curso = resultadoconsultaBD.getString("curso") + " ";
			telefoneemergencia = resultadoconsultaBD.getString("telefoneemergencia") + " ";
			contatoemergencia = resultadoconsultaBD.getString("contatoemergencia") + " ";
			peso = resultadoconsultaBD.getString("peso") + " ";
			altura = resultadoconsultaBD.getString("altura") + " ";
			imc = resultadoconsultaBD.getString("imc");
			id = resultadoconsultaBD.getString("id");
			
			resultado = "# ID: " + id + " Nome: " + nome + " Endereco: " + endereco + " Telefone: " + telefone + "CPF: " + cpf + "Tipo Sanguineo: " + tiposanguineo + "Fator RH: " + fatorrh + "Curso: " + curso + "Telefone Emergência" + telefoneemergencia + "Contato Emergência" + contatoemergencia + "Peso " + peso + "Altura" + altura + "IMC" + imc + "\n\n";

			relatorioBancoDeDados.add(resultado);
		}
		
		EncerrarConexao();
		
		return relatorioBancoDeDados;
			
		}
		return null;
	}
	
	public Pessoa pesquisar(int id) throws SQLException
	{
		
		IniciarConexao();
		
		String nome = "";
		String endereco = "";
		String telefone = "";
		String cpf = "";
		String tiposanguineo = "";
		String fatorrh = "";
		String curso = "";
		String telefoneemergencia = "";
		String contatoemergencia = "";
		Double peso = 0.0;
		Double altura = 0.0;
		
		if(conexao != null){
			Statement comandoconsulta = conexao.createStatement();
			ResultSet resultadoconsultaBD = comandoconsulta.executeQuery(String.format("SELECT * FROM pessoa WHERE id = %d", id));
			
			while(resultadoconsultaBD.next()) {
				nome = resultadoconsultaBD.getString("nome");
				endereco = resultadoconsultaBD.getString("endereco");
				telefone = resultadoconsultaBD.getString("telefone");
				cpf = resultadoconsultaBD.getString("cpf");
				tiposanguineo = resultadoconsultaBD.getString("tiposanguineo");
				fatorrh = resultadoconsultaBD.getString("fatorrh");
				curso = resultadoconsultaBD.getString("curso");
				telefoneemergencia = resultadoconsultaBD.getString("telefoneemergencia");
				contatoemergencia = resultadoconsultaBD.getString("contatoemergencia");
				peso = Double.valueOf(resultadoconsultaBD.getString("peso"));
				altura = Double.valueOf(resultadoconsultaBD.getString("altura"));
			}
		}
		Pessoa resultado = new Pessoa(nome, endereco, telefone, cpf, tiposanguineo, fatorrh, curso, telefoneemergencia, contatoemergencia, peso, altura);
		System.out.println(resultado.getNome());
		
		return resultado;
	}

	


	public StringBuilder gerarRelatorio() {
	    StringBuilder relatorio = new StringBuilder();
	    IniciarConexao();
	        
	       try { // Maior Peso
		        String sql = "SELECT nome, peso, tiposanguineo, fatorrh FROM nomebd.pessoa ORDER BY peso DESC LIMIT 1";
		        try (PreparedStatement stmt = conexao.prepareStatement(sql);
		             ResultSet rs = stmt.executeQuery()) {
		            if (rs.next()) {
		                relatorio.append("Maior Peso: ").append(rs.getDouble("peso"))
		                    .append(" kg - ").append(rs.getString("nome"))
		                    .append(" (").append(rs.getString("tiposanguineo")).append(rs.getString("fatorrh")).append(")\n");
		            }
		        }
		
		        // Menor Peso
		        sql = "SELECT nome, peso, tiposanguineo, fatorrh FROM nomebd.pessoa ORDER BY peso ASC LIMIT 1";
		        try (PreparedStatement stmt = conexao.prepareStatement(sql);
		             ResultSet rs = stmt.executeQuery()) {
		            if (rs.next()) {
		                relatorio.append("Menor Peso: ").append(rs.getDouble("peso"))
		                    .append(" kg - ").append(rs.getString("nome"))
		                    .append(" (").append(rs.getString("tiposanguineo")).append(rs.getString("fatorrh")).append(")\n");
		            }
		        }
		
		        // Média dos Pesos
		        sql = "SELECT AVG(peso) AS media_peso FROM nomebd.pessoa";
		        try (PreparedStatement stmt = conexao.prepareStatement(sql);
		             ResultSet rs = stmt.executeQuery()) {
		            if (rs.next()) {
		                relatorio.append("Média dos Pesos: ").append(String.format("%.2f", rs.getDouble("media_peso"))).append(" kg\n");
		            }
		        }
		
		        // Maior Altura
		        sql = "SELECT nome, altura, curso FROM nomebd.pessoa ORDER BY altura DESC LIMIT 1";
		        try (PreparedStatement stmt = conexao.prepareStatement(sql);
		             ResultSet rs = stmt.executeQuery()) {
		            if (rs.next()) {
		                relatorio.append("Maior Altura: ").append(rs.getDouble("altura"))
		                    .append(" m - ").append(rs.getString("nome"))
		                    .append(" (Curso: ").append(rs.getString("curso")).append(")\n");
		            }
		        }
		
		        // Menor Altura
		        sql = "SELECT nome, altura, curso FROM nomebd.pessoa ORDER BY altura ASC LIMIT 1";
		        try (PreparedStatement stmt = conexao.prepareStatement(sql);
		             ResultSet rs = stmt.executeQuery()) {
		            if (rs.next()) {
		                relatorio.append("Menor Altura: ").append(rs.getDouble("altura"))
		                    .append(" m - ").append(rs.getString("nome"))
		                    .append(" (Curso: ").append(rs.getString("curso")).append(")\n");
		            }
		        }
		
		        // Média das Alturas
		        sql = "SELECT AVG(altura) AS media_altura FROM nomebd.pessoa";
		        try (PreparedStatement stmt = conexao.prepareStatement(sql);
		             ResultSet rs = stmt.executeQuery()) {
		            if (rs.next()) {
		                relatorio.append("Média das Alturas: ").append(String.format("%.2f", rs.getDouble("media_altura"))).append(" m\n");
		            }
		        }
		
		        // Média do IMC
		        sql = "SELECT AVG(imc) AS media_imc FROM nomebd.pessoa";
		        try (PreparedStatement stmt = conexao.prepareStatement(sql);
		             ResultSet rs = stmt.executeQuery()) {
		            if (rs.next()) {
		                relatorio.append("Média do IMC: ").append(String.format("%.2f", rs.getDouble("media_imc"))).append("\n");
		                System.out.println("Média do IMC: "+ String.format("%.2f", rs.getDouble("media_imc")) + "\n");
		            }
		        }
		
		        // Maior IMC
		        sql = "SELECT nome, imc FROM nomebd.pessoa ORDER BY imc DESC LIMIT 1";
		        try (PreparedStatement stmt = conexao.prepareStatement(sql);
		             ResultSet rs = stmt.executeQuery()) {
		            if (rs.next()) {
		                relatorio.append("Maior IMC: ").append(String.format("%.2f", rs.getDouble("imc")))
		                    .append(" - ").append(rs.getString("nome")).append("\n");
		            }
		        }
		
		        // Menor IMC
		        sql = "SELECT nome, imc FROM nomebd.pessoa ORDER BY imc ASC LIMIT 1";
		        try (PreparedStatement stmt = conexao.prepareStatement(sql);
		             ResultSet rs = stmt.executeQuery()) {
		            if (rs.next()) {
		                relatorio.append("Menor IMC: ").append(String.format("%.2f", rs.getDouble("imc")))
		                    .append(" - ").append(rs.getString("nome")).append("\n");
		            }
		        }
	       }catch(Exception e) {
	    	   e.printStackTrace();
	       }
	       return relatorio;
	}
}
