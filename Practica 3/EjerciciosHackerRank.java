public class EjerciciosHackerRank {

    public static int longestPeak(int[] array) {
        int maxPeakLength = 0;
        int i = 1;

        while (i < array.length - 1) {
            boolean isPeak = array[i - 1] < array[i] && array[i] > array[i + 1];

            if (!isPeak) {
                i++;
                continue;
            }

            int leftIdx = i - 2;
            while (leftIdx >= 0 && array[leftIdx] < array[leftIdx + 1]) {
                leftIdx--;
            }

            int rightIdx = i + 2;
            while (rightIdx < array.length && array[rightIdx] < array[rightIdx - 1]) {
                rightIdx++;
            }

            int currentPeakLength = rightIdx - leftIdx - 1;
            if (currentPeakLength > maxPeakLength) {
                maxPeakLength = currentPeakLength;
            }

            i = rightIdx;
        }

        return maxPeakLength;
    }

    public static String compress(String data) {
        if (data == null || data.isEmpty()) {
            return "";
        }

        StringBuilder compressed = new StringBuilder();
        int currentRunLength = 1;

        for (int i = 1; i < data.length(); i++) {
            char currentCharacter = data.charAt(i);
            char previousCharacter = data.charAt(i - 1);

            if (currentCharacter != previousCharacter || currentRunLength == 9) {
                compressed.append(currentRunLength);
                compressed.append(previousCharacter);
                currentRunLength = 0;
            }

            currentRunLength++;
        }

        compressed.append(currentRunLength);
        compressed.append(data.charAt(data.length() - 1));

        return compressed.toString();
    }

    public static void main(String[] args) {
        int[] testArray = {1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3};
        System.out.println("Longest Peak: " + longestPeak(testArray)); // Esperado: 6

        String testString = "AAAAAAAAAAAAABBCCCCDD";
        System.out.println("Compress: " + compress(testString)); // Esperado: 9A4A2B4C2D
    }
}