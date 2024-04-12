public class PhoneRegexp {

    public static void main(String[] args) {
        String pattern = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";


        System.out.println("1167890123".matches(pattern));
        System.out.println("(116)7890123".matches(pattern));
        System.out.println("(116)789-0123".matches(pattern));
        System.out.println("116-789-0123".matches(pattern));
        System.out.println("21167890123".matches(pattern));


    }
}
