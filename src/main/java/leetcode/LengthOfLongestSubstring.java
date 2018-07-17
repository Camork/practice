package leetcode;

import java.util.HashSet;
import java.util.Set;

/*
 * [3] Longest Substring Without Repeating Characters
 *
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 *
 * algorithms
 * Medium (24.86%)
 * Total Accepted:    533.9K
 * Total Submissions: 2.1M
 * Testcase Example:  '"abcabcbb"'
 *
 * Given a string, find the length of the longest substring without repeating
 * characters.
 * 
 * Examples:
 * 
 * Given "ababccbb", the answer is "abc", which the length is 3.
 * 
 * Given "bbbbb", the answer is "b", with the length of 1.
 * 
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the
 * answer must be a substring, "pwke" is a subsequence and not a substring.
 * 
 */
class LengthOfLongestSubstring {


    public static int lengthOfLongestSubstring(String s) {
	    char[] chars = s.toCharArray();

	    if(chars.length==1){
	    	return 1;
	    }

	    int result=0;
	    Set<Character> set;

	    for (int i = 0; i < chars.length; i++) {
		    set = new HashSet<>();
		    set.add(chars[i]);
		    for(int j=i+1;j<chars.length;j++){
		    	                            //到达边界
			    if (!set.add(chars[j]) || j==chars.length-1) {

				    result = j - i + 1 > result ? set.size() : result;

				    break;
			    }
		    }
	    }

	    return result;
    }

	public static void main(String[] args) {
		System.out.println(lengthOfLongestSubstring("aab"));
	}
}
