public class Main {
    static void main(String[] args){
        for (int i=1000; i<=9999; i++) {
             String s = String.valueOf(i);
            int d1 = Character.getNumericValue(s.charAt(0));
            int d2 = Character.getNumericValue(s.charAt(1));
            int d3 = Character.getNumericValue(s.charAt(2));
            int d4 = Character.getNumericValue(s.charAt(3));
            if((d1 + d4 == d2 + d3 )&& (i % 4 == 0 )&& (i % 22 == 0)){
                System.out.println(i);
            }
        }
    }
}