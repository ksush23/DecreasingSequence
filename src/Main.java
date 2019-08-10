import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[]A = new int[n];

        for (int i = 0; i < n; i++){
            A[i] = scanner.nextInt();
        }

        int[]T = new int[n];
        int[]prev = new int[n];
        int len = 0;
        int curIndex = 0;

        prev[0] = -1;

        for (int i = 1; i < n; i++){
            prev[i] = -1;

            if (A[i] <= A[T[curIndex]]){
                curIndex++;
                T[curIndex] = i;
                len++;
                prev[i] = T[curIndex - 1];
            }

            else if (A[i] > A[T[0]]){
                T[0] = i;
            }

            else {
                int index = bisect_right(T, len + 1, A, A[i]);
                T[index] = i;
                prev[i] = T[index - 1];
            }
        }

        System.out.println(len + 1);

        int[]L = new int[len + 1];
        int j = len;
        int index = T[len];

        while (index >= 0){
            L[j] = index + 1;
            j--;
            index = prev[index];
        }

        for (int i = 0; i <= len; i++){
            System.out.print(L[i] + " ");
        }
    }

    public static int bisect_right(int[] T, int len, int[]A, int x) {
        return bisect_right(T, A, x, 0, len);
    }

    public static int bisect_right(int[]T, int[] A, int x, int lo, int hi) {
        int N = hi;
        if (N == 0) {
            return 0;
        }
        if (x > A[T[lo]]) {
            return lo;
        }
        if (x < A[T[hi - 1]]) {
            return hi;
        }
        for (;;) {
            if (lo + 1 == hi) {
                return lo + 1;
            }
            int mi = (hi + lo) / 2;
            if (x > A[T[mi]]) {
                hi = mi;
            } else {
                lo = mi;
            }
        }
    }
}
