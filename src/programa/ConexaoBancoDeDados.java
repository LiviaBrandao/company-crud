package programa;

import java.sql.*;
import bd.*;

import com.sun.jdi.connect.spi.Connection;

public class ConexaoBancoDeDados {
	
	static String driverJDBC = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
	static String url        = "";
	static String user       = "";
	static String senha      = "";
	
	public static void main(String[] args) {
	  
		try {
			
			System.out.println("Carregando o driver JDBC...");
			Class.forName(driverJDBC);
			System.out.println("Driver carregado com sucesso!!");
			
		}catch(Exception err1) {
			System.out.println("Falha no carregamento!!");
		}
		
		try {
			
			System.out.println("Conectando ao banco...");
			java.sql.Connection conexao = DriverManager.getConnection(url, user, senha);
			System.out.println("Conexão efetuada com sucesso!!");
			
		}catch(Exception err2) {
			System.out.println("Falha na conexao!!");
		}

		try {
			
			System.out.println("Conectando ao bdsql server...");
			BDSQLServer.COMANDO.execute();
			
		}catch(Exception err3) {
			System.out.println("Falha na conexao!!");
		}
	}
}
