
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

/**
 * Provide a graphical interface for threading sorting comparison demo.
 * 
 * @author 16165
 *
 */
public class View implements ActionListener, PropertyChangeListener {

	private JFrame frame = new JFrame("Thread Sort Demo");
	private JPanel pane = new JPanel(new GridLayout(6, 1));

	// text input
	int minimunNumberInput = 1000;
	int maximumNumberInput = 1000000;
	private JLabel inputLable = new JLabel("Enter a number between 1000, 1000000");
	private JTextField input = new JTextField();

	private JButton go = new JButton("Go");
	private JLabel finished = new JLabel("Process Status");
	private JLabel insertionLabel = new JLabel("Insertion Sort: ");
	private JLabel quickLable = new JLabel("Quick Sort: ");

	private JPanel inputPanel = new JPanel(new FlowLayout());
	private JPanel insertionPanel = new JPanel(new FlowLayout());
	private JPanel quickPanel = new JPanel(new FlowLayout());

	private JProgressBar insertionProgressbar = new JProgressBar();
	private JProgressBar quickProgressbar = new JProgressBar();

	// user input
	private int userInput;

	private Integer[] data;
	private Integer[] insertionData;
	private Integer[] quickData;

	private InsertionSortDemo insertionSort;
	private QuickSortDemo quickSort;

	public View() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		input.setPreferredSize(new Dimension(150, 25));// set the size of the textArea

		// add component to the inputPanel
		inputPanel.add(inputLable);
		inputPanel.add(input);
		inputPanel.add(go);

		// add component to the insertion panel
		insertionPanel.add(insertionLabel);
		insertionPanel.add(insertionProgressbar);

		// add component to the quick panel
		quickPanel.add(quickLable);
		quickPanel.add(quickProgressbar);

		pane.add(inputPanel);
		pane.add(insertionPanel);
		pane.add(quickPanel);
		pane.add(finished);

		go.addActionListener(this);
		frame.add(pane);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getSource().equals(insertionSort) )
		{
			if("progress" == evt.getPropertyName() ) {
				int progress = (Integer) evt.getNewValue();
				if(progress == 100) {
					insertionProgressbar.setValue(progress);
					finished.setText("Process Status: complete " );
				}else {
					finished.setText("Process status : running" );
					insertionProgressbar.setValue(progress);
				}
			}
		}else if(evt.getSource().equals(quickSort) ) {
			if("progress" == evt.getPropertyName() ) {
				int progress = (Integer) evt.getNewValue();
				if(progress == 100) {
					quickProgressbar.setValue(progress);
					finished.setText("Process status : complete");
				}else {
					finished.setText("Process status : running" );
					quickProgressbar.setValue(progress);
				}
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == go) {
			try {
				if (Integer.parseInt(input.getText()) > maximumNumberInput || Integer.parseInt(input.getText()) < minimunNumberInput)
					JOptionPane.showMessageDialog(null, "Enter an integer between 1000 and 1000000");
				else {
					userInput = Integer.parseInt(input.getText());
					createArray();
					runThreads();
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	private void runThreads() {

		// pass the copies into a runnable object
		insertionSort = new InsertionSortDemo(insertionData);
		quickSort = new QuickSortDemo(quickData);
		// add change listener
		insertionSort.addPropertyChangeListener(this);
		quickSort.addPropertyChangeListener(this);
		//start the thread
		insertionSort.execute();
		quickSort.execute();
	}

	// create an array of random numbers
	private void createArray() {
		data = new Integer[userInput];
		Random rand = new Random();

		// load the array with random numbers
		for (int i = 0; i < data.length; i++) {
			data[i] = rand.nextInt(maximumNumberInput);
		}

		// copy the array to the sorting method
		insertionData = data.clone();
		quickData = data.clone();

	}


}
