package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;

public class TestFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame();
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
	public TestFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 250, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[][grow][grow][][grow]", "[85px][14px]"));
		
		JLabel lblCreateGame = new JLabel("Create Game");
		panel.add(lblCreateGame, "cell 0 0 5 1,alignx center");
		
		JLabel lblAdd = new JLabel("Add");
		panel.add(lblAdd, "cell 0 1");
		
		JLabel lblName = new JLabel("Name");
		panel.add(lblName, "cell 1 1,alignx left,aligny top");
		
		JLabel lblStartingIncome = new JLabel("Starting Income");
		panel.add(lblStartingIncome, "cell 2 1,alignx left,aligny top");
		
		JLabel lblColor = new JLabel("Color");
		panel.add(lblColor, "cell 3 1");
		
		JLabel lblObjectives = new JLabel("Objectives");
		panel.add(lblObjectives, "cell 4 1");
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new MigLayout("", "[][][grow]", "[]"));
		
		JButton btnBack = new JButton("Back");
		panel_2.add(btnBack, "cell 0 0");
		
		JButton btnReset = new JButton("Reset");
		panel_2.add(btnReset, "cell 1 0");
		
		JButton btnAddGameTo = new JButton("Add Game to New Game Screen");
		panel_2.add(btnAddGameTo, "cell 2 0,alignx right");
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new MigLayout("", "[grow]", "[][]"));
		
		JPanel center_1 = new JPanel();
		panel_1.add(center_1, "cell 0 0,grow");
		center_1.setLayout(new BoxLayout(center_1, BoxLayout.X_AXIS));
		
		JButton btnNewButton = new JButton("New button");
		center_1.add(btnNewButton);
		
		textField = new JTextField();
		center_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		center_1.add(textField_1);
		textField_1.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		center_1.add(comboBox);
		
		JButton btnObjectives = new JButton("Objectives");
		center_1.add(btnObjectives);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, "cell 0 1,grow");
		
		JButton btnNewButton_1 = new JButton("New button");
		panel_4.add(btnNewButton_1);
	}
}
