public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        int curr = 0;
        int prev = 0;
        while (x < 10) {
            curr = prev + x;
            System.out.print(curr + " ");
            prev = curr;
            x = x + 1;
        }
    }
}