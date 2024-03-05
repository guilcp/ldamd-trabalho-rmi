import javax.swing.*;
import java.awt.*;
class MainFrame extends JFrame {
    JTextField tfPalavra;
    public void initialize() {
        JLabel lbPalavra = new JLabel("Palavra");

        tfPalavra = new JTextField();

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 1, 5, 5));
        formPanel.add(lbPalavra);
        formPanel.add(tfPalavra);

        JButton btnAdd = new JButton("Adicionar");
        JButton btnGet = new JButton("Consultar");
        JButton btnDel = new JButton("Remover");
        JPanel btPanel = new JPanel();
        btPanel.setLayout(new GridLayout(1, 2, 5, 5));
        btPanel.add(btnAdd);
        btPanel.add(btnGet);
        btPanel.add(btnDel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout()); 
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(btPanel, BorderLayout.SOUTH);
        
        setTitle("Dicionario");
        setSize(500, 600);
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}