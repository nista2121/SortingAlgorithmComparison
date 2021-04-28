import javax.swing.SwingWorker;

/**
 * The insertionDemo class serves as an example of using the insertion
 * algorithm, and thread by extending the SwingWorker class
 * 
 * @author 16165
 */
public class InsertionSortDemo extends SwingWorker {

	private Integer[] data;

	/**
	 * Constructor for the insertion sort demo
	 * 
	 * @param data, the array of integer to be sorted
	 */
	public InsertionSortDemo(Integer[] data) {
		super();
		this.data = data;
	}

	/**
	 * override the doInBackGround in the SwingWorker Call the insertion sort method
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	protected Object doInBackground() throws Exception {
		insertionSort();
		return null;
	}

	/**
	 * 
	 * 
	 */
	public void done() {
		try {
			setProgress(100);
			get();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("An execption occur while this thread were running in the backgound ");
			e.printStackTrace();
		}
	}

	/**
	 * Sort the specified array of object using the insertion algorithm
	 * 
	 * @param the data to be sorted
	 */
	private void insertionSort() {
		for (int index = 1; index < data.length; index++) {
			int currentValue = data[index];
			int position = index;
			updateProgress(index);
			while (position > 0 && currentValue < data[position - 1]) {
				data[position] = data[position - 1];
				position--;
			}
			data[position] = currentValue;
		}
	}

	// Calculate the current progress of the sorting and update the progrss
	// attribute of the SwingWorker class.
	private void updateProgress(int numberPass) {

		int result;
		double progressCount = 1 - (((double) data.length - (double) numberPass) / (double) data.length);
		result = (int) (progressCount * 100);
		setProgress(result);

	}

}
