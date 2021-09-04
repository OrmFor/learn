public class TestException {


    public String test() {
        String ss = new String();
        try {
            int i = 1 / 0;
        } catch (Exception e) {
           // e.printStackTrace();
            ss = "error";
        }
        return ss;
    }

    public static void main(String[] args) {
        String s = new TestException().test();
        System.out.println(s);

    }
}
