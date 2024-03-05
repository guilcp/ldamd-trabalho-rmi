
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DicionarioClient extends JFrame{

	public void frame() {
		JLabel lbEndereco = new JLabel("Endereço");
		JTextField tfEndereco = new JTextField();
		JLabel lbPalavra = new JLabel("Palavra");
		JTextField tfPalavra = new JTextField();
		JLabel lbSignificado = new JLabel("Sigfinicado");
		JTextField tfSignificado = new JTextField();

		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(4, 1, 5, 5));
		formPanel.add(lbEndereco);
		formPanel.add(tfEndereco);
		formPanel.add(lbPalavra);
		formPanel.add(tfPalavra);
		formPanel.add(lbSignificado);
		formPanel.add(tfSignificado);

		JButton btnAdd = new JButton("Adicionar");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// String servidor = "rmi://localhost/";
				String servidor = tfEndereco.getText();
				String nome = "DicionarioService";
				try {
					Dicionario c = (Dicionario) Naming.lookup("rmi://" + servidor + "/" + nome);
					System.out.println("Objeto remoto \'"+ nome + "\' encontrado no servidor.");

					JOptionPane.showMessageDialog(null, "Resultado Adição: " +  c.adicionar(tfPalavra.getText(), tfSignificado.getText()));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		JButton btnGet = new JButton("Consultar");
		btnGet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// String servidor = "rmi://localhost/";
				String servidor = tfEndereco.getText();
				String nome = "DicionarioService";
				try {
					Dicionario c = (Dicionario) Naming.lookup("rmi://" + servidor + "/" + nome);
					System.out.println("Objeto remoto \'"+ nome + "\' encontrado no servidor.");

					JOptionPane.showMessageDialog(null, "Resultado Consulta: " + c.consultar(tfPalavra.getText()));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		JButton btnDel = new JButton("Remover");
		btnDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// String servidor = "rmi://localhost/";
				String servidor = tfEndereco.getText();
				String nome = "DicionarioService";
				try {
					Dicionario c = (Dicionario) Naming.lookup("rmi://" + servidor + "/" + nome);
					System.out.println("Objeto remoto \'"+ nome + "\' encontrado no servidor.");

					JOptionPane.showMessageDialog(null, "Resultado Remover: " + c.remover(tfPalavra.getText()));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		JPanel btPanel = new JPanel();
		btPanel.setLayout(new GridLayout(1, 2, 5, 5));
		btPanel.add(btnAdd);
		btPanel.add(btnGet);
		btPanel.add(btnDel);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout()); 
		mainPanel.add(formPanel, BorderLayout.NORTH);
		mainPanel.add(btPanel, BorderLayout.SOUTH);
		
		add(mainPanel);

		setTitle("Dicionario");
		setSize(300, 200);
		setMinimumSize(new Dimension(300, 200));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		DicionarioClient dc = new DicionarioClient();
		dc.frame();
	}
}
