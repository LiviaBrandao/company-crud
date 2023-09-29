package bd.dbos;

public class Localizacao implements Cloneable{

	private int    codLocalizacao;
	private String cep;
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	private int    numero;
	private String complemento;
	
	
	
	public void setCodLocalizacao(int codLocalizacao) throws Exception{
		if(codLocalizacao == 0 || codLocalizacao <0)
			throw new Exception("insira um código válido");
		
	}
	public void setCep(String cep) throws Exception {
		if (cep == null || cep.equals(""))
			throw new Exception("Endereco não fornecido");

		this.cep = cep;
	}
	
	public void setRua(String rua) throws Exception {
		if (rua == null || rua.equals(""))
			throw new Exception("Rua não fornecida");
		
		this.rua = rua;
	}

	public void setBairro(String bairro) throws Exception{
		if (bairro == null || bairro.equals(""))
			throw new Exception("Bairro não fornecido");
		
		this.bairro = bairro;
	}
	
	public void setCidade(String cidade) throws Exception{
		if (cidade == null || cidade.equals(""))
			throw new Exception("Cidade não fornecida");
		
		this.cidade = cidade;
	}
	
	public void setEstado(String estado) throws Exception{
		if (estado == null || estado.equals(""))
			throw new Exception("Estado não fornecido");
						
		this.estado = estado;
	}
	
	public void setNumero(int numero) throws Exception {
		if (numero <= 0)
			throw new Exception("Número inválido");

		this.numero = numero;
	}
	
	public void setComplemento (String complemento) throws Exception{
		
		this.complemento = complemento;
		
	}
	
	
	public int getCodLocalizacao() {
		return this.codLocalizacao;
	}
	public String getCep() {
		return this.cep;
	}

	public String getRua() {
		return this.rua;
	}
	
	public String getBairro() {
		return this.cidade;
	}
	
	public String getCidade() {
		return this.cidade;
	}
	
	public String getEstado() {
		return this.estado;
	}
	

	public int getNumero() {
		return this.numero;
	}
	
	public String getComplemento() {
		return this.complemento;
	}
	
	public Localizacao(String cep, String rua, String bairro, String cidade, String estado, int numero, String complemento) throws Exception {
		this.setCep(cep);
		this.setRua(rua);
		this.setBairro(bairro);
		this.setCidade(cidade);
		this.setEstado(estado);
		this.setNumero(numero);
		this.setComplemento(complemento);
	}

	public Localizacao(int codLocalizacao, String cep, String rua, String bairro, String cidade, String estado, int numero, String complemento) throws Exception {
		this.setCodLocalizacao(codLocalizacao);
		this.setCep(cep);
		this.setRua(rua);
		this.setBairro(bairro);
		this.setCidade(cidade);
		this.setEstado(estado);
		this.setNumero(numero);
		this.setComplemento(complemento);
	}

	public int hashCode() {
		int ret = 666;

		ret = 13 * ret + new Integer(this.codLocalizacao).hashCode();
		ret = 13 * ret + this.cep.hashCode();
		ret = 13 * ret + this.rua.hashCode();
		ret = 13 * ret + this.bairro.hashCode();
		ret = 13 * ret + this.cidade.hashCode();
		ret = 13 * ret + this.estado.hashCode();
		ret = 13 * ret + this.cidade.hashCode();
		ret = 13 * ret + this.estado.hashCode();
		ret = 13 * ret + new Integer(this.numero).hashCode();
		ret = 13 * ret + this.complemento.hashCode();
		

		return ret;
	}

	public Localizacao(Localizacao modelo) throws Exception {
		
        this.codLocalizacao = modelo.codLocalizacao;
		this.cep            = modelo.cep;
		this.rua            = modelo.rua;
		this.bairro         = modelo.bairro;
		this.cidade         = modelo.cidade;
		this.estado         = modelo.cidade;
		this.numero         = modelo.numero;
		this.complemento    = modelo.complemento;

	}

	public Object clone() {
		Localizacao ret = null;

		try {

			ret = new Localizacao(this);
		}

		catch (Exception erro) {
		}

		return ret;
	}

	public String toString() {
		
		String ret =  "Código Localização = " + getCodLocalizacao() +
				      "\nCEP = "          + getCep() +
				      "\n Rua = "         + getRua() +
				      "\n Bairro = "      + getBairro() +
				      "\n Cidade = "      + getCidade() +
				      "\n Estado = "      + getEstado() +
				      "\n Numero = "      + getNumero() +
	                  "\n Complemento = " + getComplemento();  


		return ret;
	}

	public boolean equals(Object objeto){
		
		if (this == objeto)
			return true;
		
	   if (objeto == null)
			return false;
	   
	   if (!(objeto instanceof Localizacao))
		   return false;
	   
	   Localizacao loc = (Localizacao) objeto;
	   
	   if (this.codLocalizacao != codLocalizacao)				   
		   return false;
	   if (!loc.cep.equals(this.cep))   
		   return false;
	   if(!loc.rua.equals(this.rua))	   
		   return false;
	   if(!loc.bairro.equals(this.bairro))				   
		   return false;
	   if(!loc.cidade.equals(this.cidade))				   
		   return false;
	   if(!loc.estado.equals(this.estado))				   
		   return false;
	   if (!loc.complemento.equals(this.complemento))				   
		   return false;	
	   if (this.numero != numero)				   
		   return false;
	   
	   return true;			   
	}

}