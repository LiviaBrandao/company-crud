package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bd.daos.Localizacoes;
import bd.daos.Restaurantes;
import bd.dbos.Localizacao;
import bd.dbos.Restaurante;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaCadastrarRestaurante extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCodLocalizacao;
	//private JTextField txtCodRestaurante;
	private JTextField txtCepProcura;
	private JTextField txtNumeroProcura;
	private final JButton btnMostrarCodRestaurante = new JButton("Mostrar cod restaurante");
	private JTextField txtCodRes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaCadastrarRestaurante frame = new JanelaCadastrarRestaurante();
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
	public JanelaCadastrarRestaurante() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBlue = new JPanel();
		panelBlue.setPreferredSize(new Dimension(20, 40));
		panelBlue.setBackground(new Color(204, 255, 255));
		contentPane.add(panelBlue, BorderLayout.SOUTH);
		
		JButton btnMostrarCodLocalizacao = new JButton("Mostrar Cod Localizacao");
		btnMostrarCodLocalizacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int setarCod;
				txtNome.setEnabled(true);
				
				try {
					
					if(txtCepProcura.getText() == null || txtCepProcura.getText() == " " || txtNumeroProcura.getText() == null || txtNumeroProcura.getText() == " ")
						JOptionPane.showMessageDialog(null,"VALORES NULOS " ,"ERRO", JOptionPane.ERROR_MESSAGE);
					
					setarCod = Localizacoes.getNumeroLocalizacao(txtCepProcura.getText(), Integer.parseInt(txtNumeroProcura.getText()));
					
					txtCodLocalizacao.setText("" + setarCod);
					
				}
				catch(Exception erro) {
					
					JOptionPane.showMessageDialog(null,"NÃO FOI POSSÍVEL PROCURAR POIS " + erro.getMessage() ,"ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		panelBlue.setLayout(new GridLayout(0, 4, 0, 0));
		panelBlue.add(btnMostrarCodLocalizacao);
		
		
		JButton btnEditarRestaurante = new JButton("EditarRestaurante");
		btnEditarRestaurante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtNome.getText() == null || txtNome.getText() == " ")
					JOptionPane.showMessageDialog(null,"DIGITE VALORES CORRETOS ","ERRO", JOptionPane.INFORMATION_MESSAGE);
				
				try {
					 
					Restaurante restaurante = new Restaurante(Integer.parseInt(txtCodRes.getText()), txtNome.getText(), Integer.parseInt(txtCodLocalizacao.getText()));

					Restaurantes.alterarRestaurante(restaurante);
					btnEditarRestaurante.setEnabled(false);
				}
				catch(Exception erro) {
					
					JOptionPane.showMessageDialog(null,"NÃO FOI POSSÍVEL EDITAR RESTAURANTE POIS " + erro.getMessage() ,"ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		panelBlue.add(btnEditarRestaurante);
		
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtNome.getText() == null || txtNome.getText() == " ")
					JOptionPane.showMessageDialog(null,"DIGITE VALORES CORRETOS ","ERRO", JOptionPane.INFORMATION_MESSAGE);
				
				try {
					 
					if(btnEditarRestaurante.isEnabled() == false)
					{
						JanelaVisualizacao janela = new JanelaVisualizacao();
					    janela.setVisible(true); 
					    setVisible(false);
					}
					Restaurante restaurante = new Restaurante(txtNome.getText(), Integer.parseInt(txtCodLocalizacao.getText()));
					Restaurantes.incluirRestaurante(restaurante);
					
					
				}
				catch(Exception erro) {
					
					JOptionPane.showMessageDialog(null,"NÃO FOI POSSÍVEL FINALIZAR, POIS " + erro.getMessage() ,"ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
				JanelaVisualizacao janela = new JanelaVisualizacao();
				janela.setVisible(true); 
				setVisible(false);
			}
		});
		btnMostrarCodRestaurante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int setarCod = 0;

				txtNome.setEnabled(true);
				
				try {
					
					if(txtCepProcura.getText() == null || txtCepProcura.getText() == " " || txtNumeroProcura.getText() == null || txtNumeroProcura.getText() == " ")
						JOptionPane.showMessageDialog(null,"VALORES NULOS " ,"ERRO", JOptionPane.ERROR_MESSAGE);
					
					setarCod = Restaurantes.getCodRestaurante(txtNome.getText());
					txtCodRes.setText("" + setarCod);
					
					
				}
				catch(Exception erro) {
					
					JOptionPane.showMessageDialog(null, "NÃO FOI POSSÍVEL PROCURAR POIS " + erro.getMessage() ,"ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
			
		});
		
		panelBlue.add(btnMostrarCodRestaurante);
		panelBlue.add(btnFinalizar);
		btnMostrarCodRestaurante.setEnabled(false);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(244, 164, 96));
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCadastreORestaurante = new JLabel("Insira dados do restaurante");
		lblCadastreORestaurante.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastreORestaurante.setForeground(new Color(0, 153, 0));
		lblCadastreORestaurante.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		panel_1.add(lblCadastreORestaurante);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(204, 255, 204));
		contentPane.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(10, 1, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Digite o CEP ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_4.add(lblNewLabel_2);
		
		txtCepProcura = new JTextField();
		panel_4.add(txtCepProcura);
		txtCepProcura.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Digite o n\u00FAmero");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_4.add(lblNewLabel_3);
		
		txtNumeroProcura = new JTextField();
		panel_4.add(txtNumeroProcura);
		txtNumeroProcura.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nome do Restaurante");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_4.add(lblNewLabel);
		
		txtNome = new JTextField();
		panel_4.add(txtNome);
		txtNome.setColumns(10);
		txtNome.setEnabled(false);
	    txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
	    	public void keyReleased(java.awt.event.KeyEvent e) {  		
	 		   
	    		btnMostrarCodRestaurante.setEnabled(true);
	        }
	    });
		
		
		
		JLabel lblNewLabel_1 = new JLabel("C\u00F3digo da localiza\u00E7\u00E3o");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_4.add(lblNewLabel_1);
		
		txtCodLocalizacao = new JTextField();
		panel_4.add(txtCodLocalizacao);
		txtCodLocalizacao.setColumns(10);
		txtCodLocalizacao.setEnabled(false);
		
		JLabel lblCdigoDoRestaurante = new JLabel("C\u00F3digo do Restaurante");
		lblCdigoDoRestaurante.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_4.add(lblCdigoDoRestaurante);
		
		txtCodRes = new JTextField();
		panel_4.add(txtCodRes);
		txtCodRes.setColumns(10);
		txtCodRes.setEnabled(false);
	}
	
	
	
}
