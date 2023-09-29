package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaPrincipal frame = new JanelaPrincipal();
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
	public JanelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel pnLaranja = new JPanel();
		pnLaranja.setBackground(new Color(244, 164, 96));
		
		JPanel pnAzul = new JPanel();
		pnAzul.setBackground(new Color(204, 255, 255));
		contentPane.setLayout(new BorderLayout(0, 0));
		pnAzul.setPreferredSize(new Dimension(20, 40));
		
		JLabel lbRestaurantito = new JLabel("");
		lbRestaurantito.setHorizontalAlignment(SwingConstants.CENTER);
		lbRestaurantito.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/views/imgs/rlly small restaurant.png")));
		GroupLayout gl_pnLaranja = new GroupLayout(pnLaranja);
		gl_pnLaranja.setHorizontalGroup(
			gl_pnLaranja.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_pnLaranja.createSequentialGroup()
					.addGap(191)
					.addComponent(lbRestaurantito, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(185))
		);
		gl_pnLaranja.setVerticalGroup(
			gl_pnLaranja.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnLaranja.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbRestaurantito, GroupLayout.PREFERRED_SIZE, 44, Short.MAX_VALUE)
					.addContainerGap())
		);
		pnLaranja.setLayout(gl_pnLaranja);
		contentPane.add(pnLaranja, BorderLayout.NORTH);
		contentPane.add(pnAzul, BorderLayout.SOUTH);
		pnAzul.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pnAzul.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/views/imgs/barrita.jpg")));
		pnAzul.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JPanel pnVerde = new JPanel();
		pnVerde.setBackground(new Color(204, 255, 204));
		contentPane.add(pnVerde, BorderLayout.CENTER);
		pnVerde.setLayout(new BorderLayout(0, 0));
		
		JLabel lbBarraTopo = new JLabel("");
		pnVerde.add(lbBarraTopo, BorderLayout.NORTH);
		lbBarraTopo.setHorizontalAlignment(SwingConstants.CENTER);
		lbBarraTopo.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/views/imgs/barrinta.jpg")));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 204));
		pnVerde.add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 5));
		lblNewLabel_2.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/views/imgs/potefof.png")));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/views/imgs/podeArroz.png")));
		label.setFont(new Font("Tahoma", Font.PLAIN, 5));
		
		JButton btnCadastrarRestaurante = new JButton("Cadastrar meu Restaurante");
		btnCadastrarRestaurante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JanelaCadastro janela = new JanelaCadastro();
				janela.setVisible(true); 
				setVisible(false);
			}
		});
		
		JButton btnExibirRestaurantesCadastrados = new JButton("Visualizar Restaurantes cadastrados");
		btnExibirRestaurantesCadastrados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JanelaVisualizacao janela = new JanelaVisualizacao();
				janela.setVisible(true); 
				setVisible(false);
			}
		});
		
		JButton btnEsqueciMeuCodigoRestaurante = new JButton("Editar meu restaurante");
		btnEsqueciMeuCodigoRestaurante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JanelaCadastrarRestaurante janela = new JanelaCadastrarRestaurante();
				janela.setVisible(true); 
				setVisible(false);
				
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("Cadastro");
		lblNewLabel_3.setForeground(new Color(255, 153, 0));
		lblNewLabel_3.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		
		JLabel lblDe = new JLabel("de");
		lblDe.setForeground(new Color(0, 204, 0));
		lblDe.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		
		JLabel lblRestaurantes = new JLabel("Restaurantes");
		lblRestaurantes.setForeground(new Color(255, 153, 0));
		lblRestaurantes.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(label)
								.addComponent(lblNewLabel_2))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnEsqueciMeuCodigoRestaurante, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(btnCadastrarRestaurante, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnExibirRestaurantesCadastrados, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)))
							.addGap(119))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(128)
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDe)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblRestaurantes, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)))
					.addGap(0))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDe, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3)
								.addComponent(lblRestaurantes, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
							.addGap(18)
							.addComponent(btnCadastrarRestaurante))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_2)))
					.addGap(21)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnExibirRestaurantesCadastrados)
							.addGap(18)
							.addComponent(btnEsqueciMeuCodigoRestaurante))
						.addComponent(label))
					.addGap(1))
		);
		panel.setLayout(gl_panel);
	}
}
