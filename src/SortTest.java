
public class SortTest {
	// 1. 병합정렬
	public static void mergeSort(int array[], int start, int end, int order) {

		if (start < end) {
			int mid = (start + end) / 2; // 중간지점 계산

			mergeSort(array, start, mid, order); // 전반부 정렬
			mergeSort(array, mid + 1, end, order); // 후반부 정렬
			merge(array, start, mid, end, order); // 병합

		}

	}

	public static void merge(int array[], int start, int mid, int end, int order) {
		int i = start;
		int j = mid + 1;
		int t = 0;

		int[] tempArray = new int[10];

		// 내림차순
		if (order == 0) {

			while (i <= mid && j <= end) {
				if (array[i] >= array[j]) {
					tempArray[t++] = array[i++];
				} else {
					tempArray[t++] = array[j++];
				}
			}

			while (i <= mid) {
				tempArray[t++] = array[i++];
			}

			while (j <= end) {
				tempArray[t++] = array[j++];
			}

			i = start;
			t = 0;

			while (i <= end) {
				array[i++] = tempArray[t++];
			}

		}
		// 오름차순
		else if (order == 1) {
			while (i <= mid && j <= end) {
				if (array[i] <= array[j]) {
					tempArray[t++] = array[i++];
				} else {
					tempArray[t++] = array[j++];
				}
			}

			while (i <= mid) {
				tempArray[t++] = array[i++];
			}

			while (j <= end) {
				tempArray[t++] = array[j++];
			}

			i = start;
			t = 0;

			while (i <= end) {
				array[i++] = tempArray[t++];
			}
		}

	}

	// 2. 퀵정렬
	public static void quickSort(int array[], int start, int end, int order) {
		if (start < end) {
			// 1) 분할
			int standIndex = partition(array, start, end, order);
			// 2) 왼쪽 부분 배열 정렬
			quickSort(array, start, standIndex - 1, order);
			// 3) 오른쪽 부분 배열 정렬
			quickSort(array, standIndex + 1, end, order);
		}
	}

	public static int partition(int array[], int start, int end, int order) {

		// 기준 원소는 마지막 값으로 정한다.
		int standard = array[end];
		int i = start - 1;
		int temp;

		if (order == 0) {
			for (int j = start; j < end; j++) {
				if (array[j] >= standard) {
					temp = array[++i];
					array[i] = array[j];
					array[j] = temp;

				}
			}

		}

		else if (order == 1) {
			for (int j = start; j < end; j++) {
				if (array[j] <= standard) {
					temp = array[++i];
					array[i] = array[j];
					array[j] = temp;

				}
			}

		}

		temp = array[i + 1];
		array[i + 1] = array[end];
		array[end] = temp;

		return i + 1;

	}

	// 3. 힙정렬
	public static void buildSort(int array[], int size, int order) {
		// 0 : 최대힙
		// 1 : 최소힙

		// 1. 힙을 만든다.
		for (int i = size / 2; i > -1; i--) {
			heapify(array, i, size, order);
		}

		int temp;
		// 2. 힙성질을 만족하도록 수선한다.
		for (int i = size; i > 0; i--) {

			temp = array[i];
			array[i] = array[0];
			array[0] = temp;

			heapify(array, 0, i - 1, order);

		}

	}

	public static void heapify(int array[], int k, int size, int order) {
		int leftChild = 2 * k + 1;
		int rightChild = 2 * k + 2;
		int temp;

		if (order == 0) {
			int smaller;

			if (rightChild <= size) {
				if (array[leftChild] < array[rightChild])
					smaller = leftChild;
				else
					smaller = rightChild;
			} else if (leftChild <= size) {
				smaller = leftChild;
			} else
				return;

			if (array[smaller] < array[k]) {
				temp = array[k];
				array[k] = array[smaller];
				array[smaller] = temp;

				heapify(array, smaller, size, order);
			}

		} else if (order == 1) {

			int bigger;

			if (rightChild <= size) {
				if (array[leftChild] > array[rightChild])
					bigger = leftChild;
				else
					bigger = rightChild;
			} else if (leftChild <= size) {
				bigger = leftChild;
			} else
				return;

			if (array[bigger] > array[k]) {
				temp = array[k];
				array[k] = array[bigger];
				array[bigger] = temp;

				heapify(array, bigger, size, order);
			}

		}

	}

	public static void main(String[] args) {
		int[] randomNum = new int[10];

		for (int i = 0; i < 10; i++) {
			randomNum[i] = (int) (Math.random() * 100);
		}

		randomNum = new int[] { 21, 30, 10, 29, 60, 68, 27, 58, 18, 4 };

		for (int n : randomNum)
			System.out.print(n + " ");
		System.out.println("");

		// 0:내림차순 1:오름차순
		// mergeSort(randomNum, 0, randomNum.length - 1, 0);
		// mergeSort(randomNum, 0, randomNum.length - 1, 1);
		// quickSort(randomNum, 0, randomNum.length - 1, 0);
		// quickSort(randomNum, 0, randomNum.length - 1, 1);

		// 0 : 최대힙 1: 최소힙
		// buildSort(randomNum, randomNum.length - 1, 0);
		buildSort(randomNum, randomNum.length - 1, 1);

		for (int n : randomNum)
			System.out.print(n + " ");

	}
}
