package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;

import bd.*;
import bd.core.*;
import bd.daos.*;
import bd.dbos.*;
import controllers.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaCadastro extends JFrame {

	protected JFrame frame;
	private JPanel contentPane;
	private JTextField txtCep;
	private JTextField txtBairro;
	private JTextField txtRua;
	private JTextField txtCidade;
	private JLabel lblBairro;
	private JLabel lblCidade;
	private JLabel lblEstado;
	private JTextField txtEstado;
	private JLabel lblNumero;
	private JTextField txtNumero;
	private JLabel lblComplemento;
	private JTextField txtComplemento;
	private JButton btnAvançar;
	private JLabel lbCodLocalizacao;
	
	
	
	//--------------------------------------------------- EXPRESSÕES REGULARES ------------------------------------------------------
	
	 private static final String regExCep = "^[1-9]{1}[0-9]{4}[-][0-9]{3}$";
	 private static final Pattern formatoCep = Pattern.compile(JanelaCadastro.regExCep);
	 
	 private String cep = null;
	 
	 //----------------------------------------------BOOLEANS DAS EXPRESSOES REGULARES------------------------------------------------
	 
	    private void oCepEhValido (String cep) throws Exception
	    {
	    	if(!JanelaCadastro.formatoCep.matcher(cep).matches())
	    	  throw new Exception("por favor, digite o CEP corretamente");
	    }
	
	    
	   

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaCadastro frame = new JanelaCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	
	//------------------------------------------------------- MÉTODOS DO CRUD -----------------------------------------------------------
	
	
	public JanelaCadastro() {
		
		frame = new JFrame();
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(244, 164, 96));
		
		JLabel lblInsiraOsDados = new JLabel("Insira os dados da localiza\u00E7\u00E3o");
		lblInsiraOsDados.setIcon(new ImageIcon(JanelaCadastro.class.getResource("/views/imgs/podeArroz.png")));
		lblInsiraOsDados.setForeground(new Color(0, 0, 0));
		lblInsiraOsDados.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(127)
					.addComponent(lblInsiraOsDados, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(90))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(21)
					.addComponent(lblInsiraOsDados, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(204, 255, 153));
		
		btnAvançar = new JButton("Avan\u00E7ar");
		btnAvançar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				insereCepDoTxt();	
				
				JanelaCadastrarRestaurante janela = new JanelaCadastrarRestaurante();
				janela.setVisible(true); 
				setVisible(false);
			}
		});
		
		lbCodLocalizacao = new JLabel("");
		lbCodLocalizacao.setForeground(new Color(51, 153, 102));
		lbCodLocalizacao.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		
		JButton btnEditarDados = new JButton("Editar Dados");
		btnEditarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				editaDadosDaLocalizacao();
				btnEditarDados.setEnabled(false);
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(144)
					.addComponent(btnEditarDados, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbCodLocalizacao, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(27)
					.addComponent(btnAvançar, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addGap(148))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(btnAvançar, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(btnEditarDados, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lbCodLocalizacao, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
							.addGap(22))))
		);
		panel_2.setLayout(gl_panel_2);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(panel, BorderLayout.NORTH);
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 255, 255));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Cep:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_1.add(lblNewLabel);
		
		txtCep = new JTextField();
		txtCep.setColumns(10);
		panel_1.add(txtCep);
		
		
		//------------------------------------------------- PEGA TEXTO DO TEXTFIELD -------------------------------------------
		
		
	    txtCep.addKeyListener(new java.awt.event.KeyAdapter() {
	    	public void keyReleased(java.awt.event.KeyEvent e) {  		
	 		   if(txtCep.getText().length() == 9)
	 			   procurarCep();
	        }
	    });
	    
	    
	 
		
		
		JLabel lblRua = new JLabel("Bairro:");
		lblRua.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_1.add(lblRua);
		
		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		panel_1.add(txtBairro);
		//txtBairro.setEnabled(false);
		
		lblBairro = new JLabel("Rua");
		lblBairro.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_1.add(lblBairro);
		
		txtRua = new JTextField();
		txtRua.setColumns(10);
		panel_1.add(txtRua);
		//txtRua.setEnabled(false);
		
		
		lblComplemento = new JLabel("Complemento:");
		lblComplemento.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_1.add(lblComplemento);
		
		txtComplemento = new JTextField();
		txtComplemento.setHorizontalAlignment(SwingConstants.LEFT);
		txtComplemento.setColumns(10);
		panel_1.add(txtComplemento);
		//txtComplemento.setEnabled(false);
		
		lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_1.add(lblCidade);
		
		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		panel_1.add(txtCidade);
		//txtCidade.setEnabled(false);
		
		lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_1.add(lblEstado);
		
		txtEstado = new JTextField();
		txtEstado.setHorizontalAlignment(SwingConstants.LEFT);
		txtEstado.setColumns(10);
		panel_1.add(txtEstado);
		//txtEstado.setEnabled(false);
		
		lblNumero = new JLabel("Numero:");
		lblNumero.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_1.add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setHorizontalAlignment(SwingConstants.LEFT);
		txtNumero.setColumns(10);
		panel_1.add(txtNumero);
		
		
		
		//----------------------------------------------------- MÉTODOS DO CRUD -------------------------------------------------------
		   
	    
		
		
		
	}
	
	
	private void procurarCep() {
		
		try {
		
			Logradouro logradouro =
	            (Logradouro)ClienteWS.getObjeto(Logradouro.class, "http://api.postmon.com.br/v1/cep", txtCep.getText());
			
			txtBairro.setText (logradouro.getBairro());
			txtRua.setText    (logradouro.getLogradouro());
			txtCidade.setText (logradouro.getCidade());
			txtEstado.setText (logradouro.getEstado());
			txtBairro.setText (logradouro.getBairro());
			
		}
		catch(Exception erro){
			
			JOptionPane.showMessageDialog(null,"NÃO FOI POSSÍVEL ENCONTRAR O CEP","ERRO", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}

	private void insereCepDoTxt() {
		
		try {	
			
		  if(txtComplemento.getText() == null)
			  txtComplemento.setText("N/A");
		  
		  int numero = Integer.parseInt(txtNumero.getText());
		  
		  Localizacao novaLocalizacao = new Localizacao(    txtCep.getText(), 
				                                            txtRua.getText(),
				                                            txtBairro.getText(),
				                                            txtCidade.getText(),
				                                            txtEstado.getText(),
				                                            numero             ,
	  	                                                    txtComplemento.getText());
		    
		    Localizacoes.incluirLocalizacao(novaLocalizacao);
		   
		}
		catch(Exception erro) {
			
			JOptionPane.showMessageDialog(null,"NÃO FOI POSSÍVEL INSERIR LOCALIZACAO POIS " + erro.getMessage() ,"ERRO", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
	private void editaDadosDaLocalizacao() {
		
		try {		
			  int numero = Integer.parseInt(txtNumero.getText());
			  
			  Localizacao novaLocalizacao = new Localizacao(    txtCep.getText(), 
					                                            txtBairro.getText(),
					                                            txtRua.getText(),
					                                            txtCidade.getText(),
					                                            txtEstado.getText(),
					                                            numero             ,
		  	                                                    txtComplemento.getText());
			    
			   Localizacoes.alterarLocalizacao(novaLocalizacao);
			   
			}
			catch(Exception erro) {
				
				JOptionPane.showMessageDialog(null,"NÃO FOI POSSÍVEL ALTERAR A LOCALIZACAO POIS " + erro.getMessage() ,"ERRO", JOptionPane.ERROR_MESSAGE);
			}
	}
}


