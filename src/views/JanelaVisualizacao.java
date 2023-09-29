package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bd.core.MeuResultSet;
import bd.daos.Localizacoes;
import bd.daos.Restaurantes;

import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaVisualizacao extends JFrame {
	
	protected JFrame frame;
	private static JTable tabela;
	protected JPanel contentPane;
	protected static JButton btnVerLocalizacoes;
	protected static JButton btnVerRestaurantes;
	
	protected static JButton btnRemoverRestaurante;
	protected static JButton btnRemoverLocalizacao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaVisualizacao frame = new JanelaVisualizacao();
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
	public JanelaVisualizacao() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 389);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel pnTopo = new JPanel();
		pnTopo.setBackground(new Color(204, 255, 153));
		
		JPanel painelTabela = new JPanel();
		painelTabela.setBackground(new Color(255, 153, 102));
		
		
		setTabela(new JTable());
		painelTabela.add(getTabela());
		getTabela().setColumnSelectionAllowed(false);
		getTabela().setRowSelectionAllowed(true);
		JScrollPane scroll = new JScrollPane(getTabela());
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		painelTabela.add(scroll); 
		
		
		JPanel pnBot = new JPanel();
		pnBot.setBackground(new Color(204, 255, 255));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(pnTopo, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
				.addComponent(painelTabela, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(pnBot, GroupLayout.PREFERRED_SIZE, 534, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(41, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(pnTopo, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(painelTabela, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(pnBot, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		pnBot.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnEditarLoc = new JButton("Editar Localizacao");
		btnEditarLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JanelaCadastro janela = new JanelaCadastro();
				janela.setVisible(true); 
				setVisible(false);
			}
		});
		pnBot.add(btnEditarLoc);
		
		JButton btnVerRestaurantes = new JButton("Exibir Restaurantes");
		btnVerRestaurantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//btnRemoverLocalizacao.setEnabled(false);

				try {	
					
					getTabela().setRowSelectionAllowed(true);
	            	String colunas[] =  {"Código", "Nome", "Codigo localizacao"};
					MeuResultSet retorno = Restaurantes.exibirRestaurantes();
					DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
					while (retorno.next())
			        {   
						modelo.addRow(new String[] {"" + retorno.getInt(1), retorno.getString(2), retorno.getString(3)});
			        }
					getTabela().setModel(modelo);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, "ERRO: " + e1.getMessage());
				}
				
			}
		});
		pnBot.add(btnVerRestaurantes);
		
		
		
		JButton btnVerLocalizacoes = new JButton("Exibir Localizacoes");
		btnVerLocalizacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//btnRemoverRestaurante.setEnabled(false);
				
				try {	
					
					getTabela().setRowSelectionAllowed(true);
	            	String colunas[] =  {"Código",  "Rua", "Bairro", "Cidade", "Estado", "Nome", "Numero", "Complemento"};
					MeuResultSet retorno = Localizacoes.exibirLocalizacoes();
					DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
					while (retorno.next())
			        {   
						modelo.addRow(new String[] {"" + retorno.getInt(1),    retorno.getString(2),
						                                 retorno.getString(3), retorno.getString(4), retorno.getString(5),
						                                 retorno.getString(6), retorno.getInt(7) + "",    retorno.getString(8)});
			        }
					getTabela().setModel(modelo);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, "ERRO: " + e1.getMessage());
				}
			}
		});
		pnBot.add(btnVerLocalizacoes);
		
		
		JButton btnRemoverLocalizacao = new JButton("Remover Localizacao");
		btnRemoverLocalizacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int linhaSelecionada = getTabela().getSelectedRow();
				if(linhaSelecionada != -1) {
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "Exclusão Localizaco", JOptionPane.YES_NO_OPTION);

				if (resposta == JOptionPane.YES_OPTION) {
					
					int codLocalizacao = Integer.parseInt((String)getTabela().getModel().getValueAt(linhaSelecionada, 0));
					
					try {
						Localizacoes.excluirLocalizacao(codLocalizacao);
						btnVerLocalizacoes.doClick();
						
					} catch (Exception e1) {
						
						JOptionPane.showMessageDialog(frame, "ERRO: " + e1.getMessage());
					}			
				} 
			}else
			{
				Object[] options = { "Confirmar" };
				JOptionPane.showOptionDialog(null, "É necessário selecionar uma localizacao para excluir.", "Selecionar localização", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			}
				btnVerLocalizacoes.doClick();
			}
				

		});
		pnBot.add(btnRemoverLocalizacao);
		
		JButton btnEditarRes = new JButton("Editar Restaurante");
		btnEditarRes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JanelaCadastrarRestaurante janela = new JanelaCadastrarRestaurante();  
				janela.setVisible(true);
				setVisible(false);
			}
		});
		pnBot.add(btnEditarRes);
		

		///////////////////////---------------------------------------------------------------------------------------

		
		JButton btnRemoverRestaurante = new JButton("Remover restaurante");
		btnRemoverRestaurante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int linhaSelecionada = getTabela().getSelectedRow();
				if(linhaSelecionada != -1) {
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "Exclusão Localizaco", JOptionPane.YES_NO_OPTION);

				if (resposta == JOptionPane.YES_OPTION) {
					int codRestaurante = Integer.parseInt((String)getTabela().getModel().getValueAt(linhaSelecionada, 0));
					try {
						Restaurantes.excluirRestaurante(codRestaurante);;
						btnVerRestaurantes.doClick();
					} catch (Exception e1) {
						
						JOptionPane.showMessageDialog(frame, "ERRO: " + e1.getMessage());
					}			
				} 
			}else
			{
				Object[] options = { "Confirmar" };
				JOptionPane.showOptionDialog(null, "É necessário selecionar um restaurante para excluir.", "Selecionar restaurante", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			}
			}
			
		});
		pnBot.add(btnRemoverRestaurante);
		pnTopo.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Visualizar Restaurantes");
		lblNewLabel.setForeground(new Color(51, 102, 51));
		lblNewLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pnTopo.add(lblNewLabel, BorderLayout.CENTER);
		painelTabela.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.setLayout(gl_contentPane);
	}
	
	public static JTable getTabela() {
		return tabela;
	}
	
	public void setTabela(JTable tabela) {
		this.tabela = tabela;
	}
	
}
