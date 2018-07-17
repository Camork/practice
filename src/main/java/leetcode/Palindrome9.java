package leetcode;

import org.junit.jupiter.api.Test;

/*
 * [9] Palindrome Number
 *
 * https://leetcode.com/problems/palindrome-number/description/
 *
 * algorithms
 * Easy (38.63%)
 * Total Accepted:    402.6K
 * Total Submissions: 1M
 * Testcase Example:  '121'
 *
 * Determine whether an integer is a palindrome. An integer is a palindrome
 * when it reads the same backward as forward.
 * 
 * Example 1:
 * 
 * 
 * Input: 121
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it
 * becomes 121-. Therefore it is not a palindrome.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a
 * palindrome.
 * 
 * 
 * Follow up:
 * 
 * Coud you solve it without converting the integer to a string?
 *
 *    index   01234567      01234
 *    num     12344321      12321
 */
class Palindrome9 extends TestCase {
    public boolean isPalindrome(int x) {

        char[] chars = String.valueOf(x).toCharArray();

        if(chars[0]=='-'){
            return false;
        }

        for(int i=0;i<=chars.length/2-1;i++){
            if(chars[i]!=chars[chars.length-i-1]){
                return false;
            }
        }

        return true;
    }


    @Test
    void testCase(){
        System.out.println(isPalindrome(-121));
        System.out.println(isPalindrome(10));
        System.out.println(isPalindrome(12321));
        System.out.println(isPalindrome(1000030001));
    }
}
