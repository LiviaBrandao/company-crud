package bd.daos;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import bd.*;
import bd.core.*;
import bd.dbos.*;

public class Restaurantes {
	
	
	// Métodos de CRUD --------------------------------------------------------------------
	
	 public static boolean restauranteEhCadastrado (int codRestaurante) throws Exception
	 {
	        boolean retorno = false;

	        try
	        {
	            String sql;

	            sql = "SELECT * " +
		              "FROM RESTAURANTE " +
		              "WHERE CODRESTAURANTE = ?";

	            BDSQLServer.COMANDO.prepareStatement (sql);

	            BDSQLServer.COMANDO.setInt (1, codRestaurante);

	            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

	            retorno = resultado.first(); 
	        }
	        catch (SQLException erro)
	        {
	            //throw new Exception ("O restaurante não está cadastrado no sistema");
	        	throw new Exception (erro.getMessage());
	        }

	        return retorno;
	    }
	
	
	 public static boolean existeNomeRestaurante (String nome) throws Exception {
		 
	        boolean retorno = false;

	        try
	        {
	            String sql;

	            sql = "SELECT * " +
		              "FROM RESTAURANTE " +
		              "WHERE NOME = ?";

	            BDSQLServer.COMANDO.prepareStatement (sql);

	            BDSQLServer.COMANDO.setString (1, nome);

	            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

	            retorno = resultado.first(); 
	            
	        }
	        catch (SQLException erro) {
	        	
	        	throw new Exception (erro.getMessage());
	        }

	        return retorno;
	    
	 }
	 
	 public static boolean existeCep (String cep) throws Exception
	 {
	        boolean retorno = false;

	        try
	        {
	            String sql;

	            sql = "SELECT * " +
		              "FROM RESTAURANTE " +
		              "WHERE CEP = ?";

	            BDSQLServer.COMANDO.prepareStatement (sql);

	            BDSQLServer.COMANDO.setString (1, cep);

	            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

	            retorno = resultado.first(); 
	        }
	        catch (SQLException erro)
	        {
	        	throw new Exception (erro.getMessage());
	        }

	        return retorno;
	    }
	 
    public static void incluirRestaurante (Restaurante restaurante) throws Exception
    {
        if (restaurante==null)
            throw new Exception ("Aluno não fornecido.");     
        
        try
        {
            String sql;

            sql = "INSERT INTO RESTAURANTE " +
                  "VALUES" +
                  "(?,?)";

            BDSQLServer.COMANDO.prepareStatement (sql);
            BDSQLServer.COMANDO.setString  (1, restaurante.getNome());
            BDSQLServer.COMANDO.setInt     (2, restaurante.getCodLocalizacao());
            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro){
          //BDSQLServer.COMANDO.rollback ();
            //throw new Exception ("Erro, insira os dados corretamente");
        	throw new Exception (erro.getCause());
            }
        }
        
        public static void excluirRestaurante (int codRestaurante) throws Exception {
	        if (!restauranteEhCadastrado (codRestaurante))
	            throw new Exception ("Restaurante não cadastrado.");

	        try
	        {
	            String sql;

	            sql = "DELETE FROM RESTAURANTE " +
	                  "WHERE CODRESTAURANTE = ?";

	            BDSQLServer.COMANDO.prepareStatement (sql);

	            BDSQLServer.COMANDO.setInt (1, codRestaurante);

	            BDSQLServer.COMANDO.executeUpdate ();
	            BDSQLServer.COMANDO.commit        ();
	        }
	        catch (SQLException erro)
	        {
	          //BDSQLServer.COMANDO.rollback ();
	          //throw new Exception ("Erro ao excluir o restaurante.");
	        	throw new Exception (erro.getMessage());
	        }
	    }
		
        
	    public static void alterarRestaurante (Restaurante  restaurante) throws Exception
	    {
	        if (restaurante==null)
	            throw new Exception ("Restaurante não fornecido.");
	        try
	        {
	            String sql;

	            sql = "UPDATE RESTAURANTE " +
	                  "SET NOME=? " +
	                  "WHERE CODRESTAURANTE =?";

	            BDSQLServer.COMANDO.prepareStatement (sql);

	            BDSQLServer.COMANDO.setString (1, restaurante.getNome());
	            BDSQLServer.COMANDO.setInt    (2, restaurante.getCodRestaurante());
	            
	            BDSQLServer.COMANDO.executeUpdate ();
	            BDSQLServer.COMANDO.commit        ();
	        }
	        catch (SQLException erro)
	        {
	          //BDSQLServer.COMANDO.rollback ();
	          //throw new Exception ("Erro ao atualizar dados do restaurante.");
	        	throw new Exception (erro.getMessage());
	        }
	    }
        
	   // Métodos de SELECT ----------------------------------------------------
	    
	    
	    
	    public static MeuResultSet exibirRestaurantes() throws Exception{
	    	
			MeuResultSet resultado = null;
			try {
				
				String sql;
				sql = "SELECT * FROM RESTAURANTE ORDER BY CODRESTAURANTE";
				BDSQLServer.COMANDO.prepareStatement (sql);	
			    resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();  
			    
			}
			catch (SQLException erro)
	        {
	            throw new Exception ("Falha ao selecionar os restaurantes.");
	        }
			return resultado;
		}
        
	    public static Restaurante getRestaurante (int codRestaurante) throws Exception
	    {
	        Restaurante aluno = null;

	        try
	        {
	            String sql;

	            sql = "SELECT * " +
	                  "FROM RESTAURANTE " +
	                  "WHERE CODRESTAURANTE = ?";

	            BDSQLServer.COMANDO.prepareStatement (sql);

	            BDSQLServer.COMANDO.setInt (1, codRestaurante);

	            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

	            if (!resultado.first())
	                throw new Exception ("Não cadastrado");
	            

	            aluno = new Restaurante (resultado.getInt    ("CODRESTAURANTE"),
	                                     resultado.getString ("NOME"),
	                                     resultado.getInt    ("CODLOCALIZACAO"));
	        }
	        catch (SQLException erro)
	        {
	        	throw new Exception (erro.getMessage());
	        }

	        return aluno;
	    }
	    
	    
	    public static Restaurante selecionarPorNomeCep(String nome, String cep) throws Exception {
	    	if(nome == ""||  nome== null || cep == "" || cep == null
	    			|| !existeCep(cep) || !existeNomeRestaurante(nome))
	    		
	    		throw new Exception ("Por favor, forneça um nome/cep existente");
	    	
	    	Restaurante restaurante = null;
	    	
	    	try {
	    		
	    		String sql;

	            sql = "SELECT * " +
	                  "FROM RESTAURANTE " +
	                  "WHERE NOME = ? "   +
	                  "AND CEP =?";

	            BDSQLServer.COMANDO.prepareStatement (sql);

	            BDSQLServer.COMANDO.setString (1, nome);
	            BDSQLServer.COMANDO.setString (1, cep);

	            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

	            if (!resultado.first())
	                throw new Exception ("Não cadastrado");
	            

	            restaurante = new Restaurante (resultado.getInt    ("CODRESTAURANTE"),
	                                           resultado.getString ("NOME"),
	                                           resultado.getInt    ("CODLOCALIZACAO"));
	    		
	    	}
	    	
	    	catch(SQLException erro) {
	    		
	    		throw new Exception (erro.getMessage());
	    		
	    	}
	    	
	    	return restaurante;
	    }
	    
	    
 
		public static int getCodRestaurante(String nome) throws Exception{
			
			int retorno;
			
			try {
	          String sql;

	          sql = "SELECT CODRESTAURANTE " +
	                "FROM RESTAURANTE " +
	        		"WHERE NOME =?";

	          BDSQLServer.COMANDO.prepareStatement (sql);

	          BDSQLServer.COMANDO.setString (1, nome);

	          MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

	          if (!resultado.first())
	              throw new Exception ("Não cadastrado");
	            

	          retorno = resultado.getInt("CODRESTAURANTE");
	          
	        }
	        catch (SQLException erro){
	        	throw new Exception (erro.getMessage());
	        }

	        return retorno;
			
		}
	
}