import javax.swing.SwingWorker;

public class QuickSortDemo extends SwingWorker {

	private Integer[] data;
	private int numberofPass;

	public QuickSortDemo(Integer[] data) {
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
		quickSort(data, 0, data.length - 1);
		return null;
	}

	/**
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
	 * Recursively sort object array using quick sort algorithm
	 * 
	 * @param data    the array to be sorted.
	 * @param minimun index in the range
	 * @param maximum index in the range
	 */
	private void quickSort(Integer[] data, int min, int max) {
		// check for empty or null array
		if (data == null || data.length == 0) {
			return;
		}

		if (min < max) {
			int indexOfPartition = partition(data, min, max);
			quickSort(data, min, indexOfPartition - 1);
			quickSort(data, indexOfPartition + 1, max);
		}
		numberofPass++;
		updateProgress();

	}

	private void updateProgress() {

		int result;
		double progressCount = 1 - (((double) data.length - (double) numberofPass) / (double) data.length);
		result = (int) (progressCount * 100);
		if (result > 100)
			result = 100;
		setProgress(result);

	}

	/**
	 * partition data to be used by the quick sort algorithm
	 * 
	 * @param data
	 * @param min
	 * @param max
	 * @return
	 */
	private int partition(Integer[] data, int min, int max) {
		Integer partitionElement;
		int left, right, middle;
		middle = (min + max) / 2;

		partitionElement = data[middle];
		left = min;
		right = max;

		try {
			while (left < right) {

				while (left < right && data[left].compareTo(partitionElement) <= 0)
					left++;

				while (data[right].compareTo(partitionElement) > 0)
					right--;

				if (left < right)
					swap(data, left, right);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		swap(data, min, max);
		return right;
	}

//Swap two element in a specified array
	private void swap(Integer[] data2, int value1, int value2) {
		Integer temp = data2[value1];
		data[value1] = data[value2];
		data[value2] = temp;

	}

}
