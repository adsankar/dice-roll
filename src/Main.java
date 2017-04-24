import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class Main {

	private static int diceSize = 6;
	private static int numRolls = 1;
	private static JFrame jf;
	private static JTextField inputDice = new JTextField("Dice Size (default: 6)");
	private static JTextField inputRolls = new JTextField("Number of Rolls (default: 1)");

	private static JButton make = new JButton("Roll Dice!");
	private static ActionListener al;
	private static JLabel rollResults = new JLabel("");

	//private Font big = new Font("Verdana", Font.PLAIN  , 20);


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();
		try{	
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());	
		}
		catch(Exception ex){
			System.out.println("Couldn't set Look & Feel");
		}

		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new	JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.add(new JLabel("Dice Roll Simulator"));
		panel.add(inputDice);
		panel.add(inputRolls);
		rollResults.setText("Click to Generate!");


		make.addActionListener(al);
		inputRolls.addActionListener(al);
		inputDice.addActionListener(al);

		panel.add(make);
		panel.add(rollResults);

		JPanel mid = new JPanel();
		mid.setLayout(new FlowLayout(FlowLayout.LEFT));
		//mid.setMaximumSize(new Dimension(Integer.MAX_VALUE,400));
		mid.add(panel);
		jf.add(mid);
		//jf.add(panel);

		jf.pack();
		jf.setPreferredSize(new Dimension(200,400));
		jf.setVisible(true);


	}


	private Main() {
		super();

		jf = new JFrame("Dice Roll Simulator Version 1.0");

		al = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == make){

					try {

						diceSize = Integer.parseInt(inputDice.getText());
						
					} catch (NumberFormatException n){
						if (inputDice.getText().equals("Dice Size (default: 6)")){
							diceSize = 6;
							
							//myPassword.setFont(big);
							//rollResults.setText(rollString());

							return;
						}
						Frame tempframe = new Frame();
						JOptionPane.showMessageDialog(tempframe,
								"That is not a valid input!",
								"Format Error",
								JOptionPane.ERROR_MESSAGE);
						inputDice.setText("Dice Size (default: 6)");
						inputRolls.setText("Number of Rolls (default: 1)");
					}
					try {
						numRolls = Integer.parseInt(inputRolls.getText());
					} catch (NumberFormatException n){
						if (inputRolls.getText().equals("Number of Rolls (default: 1)" )){
							numRolls = 1;
							return;
						}
						Frame tempframe = new Frame();
						JOptionPane.showMessageDialog(tempframe,
								"That is not a valid input!",
								"Format Error",
								JOptionPane.ERROR_MESSAGE);
						inputDice.setText("Dice Size (default: 6)");
						inputRolls.setText("Number of Rolls (default: 1)");
						
					}
					//myPassword.setFont(big);
					rollResults.setText(rollString());

				}
			}
		};


	}

	private static String rollString(){
		String s ="Your Rolls Are: ";
		int[] temp = generateRolls(diceSize, numRolls);
		for (int i=0; i< temp.length; i++){
			s += temp[i]+"  ";
		}
		return s;
	}
	
	
	private static int[] generateRolls(int diceSize, int numRolls){
		int[] rolls = new int[numRolls];
		Random r = new Random();
		Random s = new Random(r.nextInt());

		for (int i=0; i< numRolls; i++){
			rolls[i] = s.nextInt(diceSize+1)+1;
		}



		return rolls;
	}//end method

}