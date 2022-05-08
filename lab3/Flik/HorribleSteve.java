public class HorribleSteve {
    public static void main(String [] args) {
        int i = 0;
        for (int j = 0; i < 500; ++i, ++j) {
            boolean T = Flik.isSameNumber(i, j);
            System.out.println(T);
        }
        System.out.println("i is " + i);
    }
}
