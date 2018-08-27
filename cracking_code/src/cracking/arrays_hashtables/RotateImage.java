package cracking.arrays_hashtables;

public class RotateImage {
	public static void main(String[] args) {
		RotateImage obj = new RotateImage();
		int[][] image = new int[3][3];
		image[0] = new int[] { 0, 1, 2 };
		image[1] = new int[] { 3, 4, 5 };
		image[2] = new int[] { 6, 7, 8 };
		printArray(image);
		System.out.println("Rotated image: ");
		int[][] rotatedImage = obj.rotateImage(image);
		printArray(rotatedImage);

		image = new int[4][4];
		image[0] = new int[] { 0, 1, 2, 3 };
		image[1] = new int[] { 4, 5, 6, 7 };
		image[2] = new int[] { 8, 9, 10, 11 };
		image[3] = new int[] { 12, 13, 14, 15 };
		printArray(image);
		System.out.println("Rotated image: ");
		rotatedImage = obj.rotateImage(image);
		printArray(rotatedImage);
	}

	private static void printArray(int[][] rotatedImage) {
		for (int[] i : rotatedImage) {
			for (int j : i) {
				System.out.print(j + ", ");
			}
			System.out.print("\n");
		}
	}

	int[][] rotateImage(int[][] image) {
		int N = image.length;
		int layers = N / 2;
		for (int layer = 0; layer < layers; layer++) {
			int first = layer;
			int last = N - layer - 1;
			for (int i = first; i < last; i++) {
				int offset = i - first;
				// save top
				int tmpTop = image[first][i];
				// left to top
				image[first][i] = image[last - offset][first];
				// bottom to left
				image[last - offset][first] = image[last][last - offset];
				// right to bottom
				image[last][last - offset] = image[i][last];
				// top to right
				image[i][last] = tmpTop;
			}
		}
		return image;
	}
	//not in place
/*	int[][] rotateImage(int[][] image) {
		int N = image.length;
		int[][] rotated = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				rotated[j][N - i - 1] = image[i][j];
			}
		}
		return rotated;
	}*/
}