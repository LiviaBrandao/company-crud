package bd.dbos;

public class Restaurante implements Cloneable{
  int codRestaurante;
  String nome;
  int codLocalizacao;
  
  
  public int getCodRestaurante() {
	  return this.codRestaurante;
  }
  
  public String getNome() {
	  return this.nome;
  }
  
  
  public int getCodLocalizacao() {
	  return this.codLocalizacao;
  }
  
  public void setCodRestaurante(int codRestaurante) throws Exception{
	  if(codRestaurante == 0 || codRestaurante <0)
		  throw new Exception ("O código inserido é inválido");

  }
  
  public void setNome(String nome) throws Exception {
	  if (nome == null || nome == "" )
		  throw new Exception ("Nome invalido");
	  
	  this.nome = nome;
  }
  
  
  public void setCodLocalizacao (int codLocalizacao)throws Exception {
	  if (codLocalizacao == 0 || codLocalizacao <0)
		  throw new Exception ("Localizacao");
	  
	  this.codLocalizacao = codLocalizacao;
  }
  
	public Restaurante (String nome, int codLocalizacao) throws Exception {
		this.setNome(nome);
		this.setCodLocalizacao(codLocalizacao);
	}
  
	public Restaurante(int codRestaurante, String nome, int codLocalizacao)throws Exception {
		
		this.setCodRestaurante(codRestaurante);
		this.setNome(nome);
		this.setCodLocalizacao(codLocalizacao);
	}
	
	public int hashCode() {
		int ret = 666;

		ret = 13 * ret + new Integer(codRestaurante).hashCode();
		ret = 13 * ret + nome.hashCode();
		ret = 13 * ret + new Integer(codLocalizacao).hashCode();

		return ret;
	}

	public Restaurante (Restaurante modelo) throws Exception {

		this.codRestaurante = modelo.codRestaurante;
		this.nome           = modelo.nome; 
		this.codLocalizacao = modelo.codLocalizacao;

	}
  
	public Object clone() {
		Restaurante ret = null;

		try {

			ret = new Restaurante(this);
		}

		catch (Exception erro) {
		}

		return ret;
	}
  
  public String toString() {
	  
	  String ret = "Código do restaurante = " + getCodRestaurante() + 
			       "\nNome = "+ getNome() +  
			       "\nCódigo da localizacao = " + codLocalizacao; 
	  return ret;
  }
  
  public boolean equals(Object obj) {
	  
	  if (this == obj)
			return true;
		
	   if (obj == null)
			return false;
	   
	   if (!(obj instanceof Localizacao))
		   return false;
	   
	   Restaurante res = (Restaurante) obj;
	   
	   if(this.codRestaurante != res.codRestaurante)
	       return false; 
	   if (!res.nome.equals(this.nome))				   
		   return false;	
	   if (this.codLocalizacao != codLocalizacao)				   
		   return false;
	   
	   return true;
  }
  
  
  
}
