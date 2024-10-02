public class StringRecursion {
    public static void printReverse(String str) {
        if (str == null || str.equals("")) {
            return;
        }
            if (str.length() == 1) {
                System.out.print(str);
            } else {
                System.out.print(str.charAt(str.length()-1));
                printReverse(str.substring(0,str.length()-1));
            }
    }
    public static String trim(String str) {
        if (str == null) {
            return null;
        } else if (str.equals("")) {
            return str;
        } else {
            String restEnd = trim(str.substring(0, str.length() - 1));
            String restBeg = trim(str.substring(1));
            if (str.charAt(0) == ' ') {
                return restBeg;
            }
            if (str.charAt(str.length() - 1) == ' ') {
                return restEnd;
            } else {
                return str;
            }
        }
    }
    public static int find(char ch, String str) {
        if (str == null || str.equals("")) {
            return -1;
        } else {
            if (str.charAt(0) == ch) {
                return 0; 
            }
            int rest = find(ch, str.substring(1));
            if ( rest != -1) {
                return rest + 1;
            } else {
                return rest;
            }
        }
    }
    public static String weave(String str1, String str2) {
        if (str1.length() == 0 && str2.length() == 0) {
            return str1;
        } else if (str1.length() > 0 && str2.length() == 0) {
            return str1;
        } else if (str1.length() == 0 && str2.length() != 0) {
            return str2;
        } else {
            String rest = weave(str1.substring(1), str2.substring(1));
            return str1.charAt(0) +  "" + str2.charAt(0) + rest;
        }
    }
    public static int indexOf(char ch, String str) {
        if (str == null || str.equals("")) {
            return -1;
        } else if (str.length() == 1 && str.charAt(0) != ch ) {
            return -1;
        } else {
            if (str.charAt(0) == ch) {
                return 0; 
            }
            int rest = find(ch, str.substring(1));
            if ( rest != -1) {
                return rest + 1;
            } else {
                return rest;
            }
        }
    }
    public static void main(String[] args) {
       printReverse("terriers");
       System.out.println();
       System.out.println();
       System.out.println(trim(" hello world    "));
       System.out.println();
       System.out.println(trim("recursion  "));
       System.out.println();
       System.out.println(find('b', "Rabbit"));
       System.out.println();
       System.out.println(find('P', "Rabbit"));
       System.out.println();
       System.out.println( weave("aaaa", "bbbb") );
       System.out.println();
       System.out.println( weave("hello", "world") );
       
    }
}