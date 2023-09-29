package bd.daos;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import bd.*;
import bd.core.*;
import bd.dbos.*;

public class Localizacoes{

	// Métodos de CRUD --------------------------------------------------------------------
	
    public static void incluirLocalizacao (Localizacao localizacao) throws Exception
    {
        if (localizacao==null)
            throw new Exception ("Localizacao não fornecida.");
        
        try
        {
            String sql;

            sql =  "INSERT INTO LOCALIZACAO " +
                   "(CEP, RUA, BAIRRO, CIDADE, ESTADO, NUMERO, COMPLEMENTO)" +
                   "VALUES" +
                   "(?,?,?,?,?,?,?)";

            BDSQLServer.COMANDO.prepareStatement (sql);
            BDSQLServer.COMANDO.setString  (1, localizacao.getCep());
            BDSQLServer.COMANDO.setString  (2, localizacao.getRua());
            BDSQLServer.COMANDO.setString  (3, localizacao.getBairro());
            BDSQLServer.COMANDO.setString  (4, localizacao.getCidade());
            BDSQLServer.COMANDO.setString  (5, localizacao.getEstado());
            BDSQLServer.COMANDO.setInt     (6, localizacao.getNumero());
            BDSQLServer.COMANDO.setString  (7, localizacao.getComplemento());
            
            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro){
        	  throw new Exception (erro.getCause());
            }
        }
        
       public static void excluirLocalizacao (int codLocalizacao) throws Exception {
    	   
	        if (!localizacaoEhCadastrada (codLocalizacao))
	            throw new Exception ("Localizacao não cadastrada.");

	        try
	        {
	            String sql;

	            sql = "DELETE FROM LOCALIZACAO " +
		                  "WHERE CODLOCALIZACAO =?";

	            BDSQLServer.COMANDO.prepareStatement (sql);

	            BDSQLServer.COMANDO.setInt (1, codLocalizacao);

	            BDSQLServer.COMANDO.executeUpdate ();
	            BDSQLServer.COMANDO.commit        ();
	            
	        }
	        catch (SQLException erro)
	        {
	          //BDSQLServer.COMANDO.rollback ();
	        	throw new Exception (erro.getCause());
	        }
	    }
		
        
	    public static void alterarLocalizacao (Localizacao  localizacao) throws Exception {
	        if (localizacao==null)
	            throw new Exception ("Localizacao não fornecida");
	        try
	        {
	            String sql;

	            sql = "UPDATE LOCALIZACAO " +
	                  "SET "            +
	                  "CEP=?, "         +
	                  "RUA=?, "         + 
	                  "BAIRRO=?, "      +  
	                  "CIDADE=?, "      +
	                  "ESTADO=?, "      +
	                  "NUMERO=?, "      + 
	                  "COMPLEMENTO=? "  +
	                  "WHERE CODLOCALIZACAO =?";

	            BDSQLServer.COMANDO.prepareStatement (sql);

	            BDSQLServer.COMANDO.setString (1, localizacao.getCep());
	            BDSQLServer.COMANDO.setString (2, localizacao.getRua());
	            BDSQLServer.COMANDO.setString (3, localizacao.getBairro());
	            BDSQLServer.COMANDO.setString (4, localizacao.getCidade());
	            BDSQLServer.COMANDO.setString (5, localizacao.getEstado());
	            BDSQLServer.COMANDO.setInt    (6, localizacao.getNumero());
	            BDSQLServer.COMANDO.setString (7, localizacao.getComplemento());
	            BDSQLServer.COMANDO.setInt    (8, localizacao.getCodLocalizacao());
	            
	            BDSQLServer.COMANDO.executeUpdate ();
	            BDSQLServer.COMANDO.commit        ();
	        }
	        catch (SQLException erro)
	        {
	        	throw new Exception (erro.getCause());
	        }
	    }
	
	    //Métodos de select ---------------------------------------------
	    
	    public static Localizacao selecionarPorCodigoLocalizacao (String cep, int numero) throws Exception {
	    	
	    	Localizacao localizacao = null;
	    	
	    	if(!cepEhCadastrado(cep))
	    		throw new Exception("O CEP não existe");
	    	
	    	try {
	    		
	    		String sql; 
	    		
	    		sql = "SELECT * FROM LOCALIZACAO " +
	    		      "WHERE CEP = ? AND " +
	    			  "NUMERO = ?";
	    		
	    		BDSQLServer.COMANDO.prepareStatement(sql);
	    		
	    		BDSQLServer.COMANDO.setString(1, cep);
	    		BDSQLServer.COMANDO.setInt   (2, numero);
	    		
	    		MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

	            if (!resultado.first())
	                throw new Exception ("Não cadastrado");
	            
	    		
	    		localizacao = new Localizacao (resultado.getInt  ("CODLOCALIZACAO"),
	    				                      resultado.getString("CEP"),
	    				                      resultado.getString("RUA"),
	    				                      resultado.getString("BAIRRO"),
	    				                      resultado.getString("CIDADE"),
	    				                      resultado.getString("ESTADO"),
	    				                      resultado.getInt   ("NUMERO"),
	    				                      resultado.getString("COMPLEMENTO")); 
	    		
	    	}
	    	catch(Exception err) {
	    		
	    		throw new Exception (err.getMessage());
	    	}
	    	
	    	return localizacao;
	    	
	    }
	    
		
		 public static boolean localizacaoEhCadastrada (int codLocalizacao) throws Exception
		 {
		        boolean retorno = false;

		        try
		        {
		            String sql;

		            sql = "SELECT * " +
		                  "FROM LOCALIZACAO " +
		                  "WHERE CODLOCALIZACAO =?";

		            BDSQLServer.COMANDO.prepareStatement (sql);

		            BDSQLServer.COMANDO.setInt (1, codLocalizacao);

		            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

		            retorno = resultado.first(); 
		        }
		        catch (SQLException erro)
		        {
		            throw new Exception (erro.getMessage());
		        }

		        return retorno;
		 }
		
		 public static boolean cepEhCadastrado (String cep) throws Exception
		 {
		        boolean retorno = false;

		        try
		        {
		            String sql;

		            sql = "SELECT * " +
		                  "FROM LOCALIZACAO " +
		                  "WHERE CEP =?";

		            BDSQLServer.COMANDO.prepareStatement (sql);

		            BDSQLServer.COMANDO.setString(1, cep);

		            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

		            retorno = resultado.first(); 
		        }
		        catch (SQLException erro)
		        {
		            throw new Exception (erro.getMessage());
		        }

		        return retorno;
		}
		 
		 public static boolean numeroEhCadastrado (int numero, String cep) throws Exception
		 {
		        boolean retorno = false;

		        try
		        {
		            String sql;

		            sql = "SELECT * " +
		                  "FROM LOCALIZACAO " +
		                  "WHERE "  +
		                  "CEP =? AND " +
		                  "NUMERO =?" ;

		            BDSQLServer.COMANDO.prepareStatement (sql);

		            BDSQLServer.COMANDO.setInt   (1, numero);
		            BDSQLServer.COMANDO.setString(1, cep);

		            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

		            retorno = resultado.first(); 
		        }
		        catch (SQLException erro)
		        {
		            throw new Exception (erro.getMessage());
		        }

		        return retorno;
		} 
		 
		public static Localizacao getLocalizacao (int ra) throws Exception
		{
		      Localizacao localizacao = null;

		      try
		      {
		          String sql;

		          sql = "SELECT * " +
		                "FROM LOCALIZACAO " +
		                "WHERE CODLOCALIZACAO =?";

		          BDSQLServer.COMANDO.prepareStatement (sql);

		          BDSQLServer.COMANDO.setInt (1, ra);

		          MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

		          if (!resultado.first())
		              throw new Exception ("Não cadastrado");
		            

		          localizacao = new Localizacao (resultado.getInt  ("CODLOCALIZACAO"),
	                      resultado.getString("CEP"),
	                      resultado.getString("RUA"),
	                      resultado.getString("BAIRRO"),
	                      resultado.getString("CIDADE"),
	                      resultado.getString("ESTADO"),
	                      resultado.getInt   ("NUMERO"),
	                      resultado.getString("COMPLEMENTO"));
		        }
		        catch (SQLException erro)
		        {
		        	throw new Exception (erro.getMessage());
		        }

		        return localizacao;
		    }
	    
		public static int getNumeroLocalizacao(String cep, int numero) throws Exception{
			
			int retorno;
			
			try {
	          String sql;

	          sql = "SELECT CODLOCALIZACAO " +
	                "FROM LOCALIZACAO " +
	        		"WHERE CEP =? " +
	                "AND NUMERO =?";

	          BDSQLServer.COMANDO.prepareStatement (sql);

	          BDSQLServer.COMANDO.setString (1, cep);
	          BDSQLServer.COMANDO.setInt(2, numero);

	          MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

	          if (!resultado.first())
	              throw new Exception ("Não cadastrado");
	            

	          retorno = resultado.getInt("CODLOCALIZACAO");
	          
	        }
	        catch (SQLException erro){
	        	throw new Exception (erro.getMessage());
	        }

	        return retorno;
			
		}
		
		
	    public static MeuResultSet exibirLocalizacoes() throws Exception
		{
			MeuResultSet resultado = null;
			
			try {
				String sql;
				sql = "select * from LOCALIZACAO";
				BDSQLServer.COMANDO.prepareStatement (sql);	
			    resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
			    
			}catch (SQLException erro)
	        {
				throw new Exception (erro.getMessage());
	        }
			
			return resultado;
		}
}