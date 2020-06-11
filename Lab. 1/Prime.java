public class Prime {
    public static void main(String[] args) {
        for (int j = 2; j <= 100; j++) {
            if (IsPrime(j)) {
                System.out.println(j + "простое число");
            }
        }
    }
        public static boolean IsPrime ( int n)
        {
            for (int i = 2; i < n; i++) {
                if (n % i== 0) {
                    return false;
                }
            }
            return true;
        }
    }