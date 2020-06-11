public class Palindrome {
   public static void main (String[] args){
       for (int i = 0; i < args.length; i++){
           String s = args[i];
           if (isPalindrome(s) == true) {
               System.out.println(s);
           }
       }
   }
   public static String reverseString(String s)
   {
       String word ="";
       for (int i = s.length()-1; i>=0; i--)
       {
           word= word + s.charAt(i);
       }
       return word;
   }
   public static boolean isPalindrome(String s)
   {
       boolean k = s.equals(reverseString(s));
       return k;
   }
}