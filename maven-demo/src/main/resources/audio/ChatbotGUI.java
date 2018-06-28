import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class ChatbotGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtQdqdeq;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatbotGUI frame = new ChatbotGUI();
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
	public ChatbotGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1900, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(434, 16, 1429, 912);
		contentPane.add(panel);
		
		txtQdqdeq = new JTextField();
		txtQdqdeq.setBounds(0, 800, 1900, 1000);
		txtQdqdeq.setText("qdqdeq");
		txtQdqdeq.setColumns(50);
		panel.add(txtQdqdeq);

		
		JTextArea ChatHistory = new JTextArea();
		ChatHistory.setText("hi m rchatbot");
		ChatHistory.setBounds(20, 20, 2000, 800);
		panel.add(ChatHistory);
		
		JLabel lblPic = new JLabel("PIC");
		lblPic.setIcon(new ImageIcon("E:\\Chatbots\\Chatbot1\\src\\Cyr3con.JPG"));
		lblPic.setBounds(15, 16, 415, 362);
		contentPane.add(lblPic);
		
		JLabel lblInteractWithWiredin = new JLabel("Interact with WiredIN...");
		lblInteractWithWiredin.setForeground(Color.BLUE);
		lblInteractWithWiredin.setFont(new Font("Verdana", Font.BOLD, 25));
		lblInteractWithWiredin.setBounds(59, 478, 360, 53);
		contentPane.add(lblInteractWithWiredin);
		
		JLabel lblKnowMoreAbout = new JLabel("Know more about CYR3CON !!!");
		lblKnowMoreAbout.setForeground(new Color(0, 100, 0));
		lblKnowMoreAbout.setBackground(Color.BLACK);
		lblKnowMoreAbout.setFont(new Font("Lucida Handwriting", Font.BOLD | Font.ITALIC, 19));
		lblKnowMoreAbout.setBounds(59, 583, 360, 34);
		contentPane.add(lblKnowMoreAbout);
	}
}
