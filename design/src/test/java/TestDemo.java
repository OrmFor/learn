public class TestDemo {

    public static boolean isIsomorphic(String s, String t) {
        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if(s.indexOf(ch1[i]) != t.indexOf(ch2[i])){
                return false;
            }
        }
        return true;
    }

//    public static void main(String[] args){
//        String s = "abb";
//        String t = "cdd";
//        boolean result = TestDemo.isIsomorphic(s,t);
//        System.out.println(result);
//    }
//

    //获取所有非负整数的质数
    public static int countPrimes(int n){
        int count = 0;
        // 直接用boolean数组，元素值直接作为判断条件，不用在做==比较(==的结果就是boolean)
        // 这样的数组，元素值默认是false
        // 所有数字初始标记：false
        boolean[] nums = new boolean[n];

        // 清除标记
        for (int i = 2; i < n; i++){
            // 质数基数
            if (!nums[i]) {
                for (int j = 2; i * j < n; j++) {
                    // 非质数标记清除(false-->true)
                    nums[i * j] = true;
                }
                // 能进来条件的都是质数，所以统计变量自增
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args){
       int count = countPrimes(10);
       System.out.println(count);
    }

}
