package com.webapp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value="com.webapp.domain")
@MapperScan("com.webapp.domain.dao")
public class DemoApplication {

    private final  Collection stamps = new ArrayList<>();

//	public static void main(String[] args) {
//
//	 String  string = "lejyqjcpluiggwlmnumqpxljlcwdsirzwlygexejhvojztcztectzrepsvwssiixfmpbzshpilmojehqyqpzdylxptsbvkgatzdlzphohntysrbrcdgeaiypmaaqilthipjbckkfbxtkreohabrjpmelxidlwdajmkndsdbbaypcemrwlhwbwaljacijjmsaqembgtdcskejplifnuztlmvasbqcyzmvczpkimpbbwxdtviptzaenkbddaauyvqppagvqfpednnckooxzcpuudckakutqyknuqrxjgfdtsxsoztjkqvfvelrklforpjnrbvyyvxigjhkjmxcphjzzilvbjbvwiwnnkbmboiqamgoimujtswdqesighoxsprhnsceshotakvmoxqkqjvbpqucvafiuqwmrlfjpjijbctfupywkbawquchbclgvhxbanybret";
//	 //longestPalindrome(string);
//
//	}

	
//	 public static String longestPalindrome(String s) {
//	        
//	    
//	    if (s.length()> 1000) {
//	        return null;
//	    }
//	    int max = s.length();
//	    String maxStr = "";
//	   
//	    List<String> reStrings = new ArrayList<>();
//	    
//	    for (int i=s.length();i>=1;i--) {
//            for (int j =0;j<s.length()-i+1;j++) {
//                String str = s.substring(j,j+i);
//                reStrings.add(str);
//            }
//        }
//	    for (String str : reStrings ) {
//	        StringBuilder sb = new StringBuilder(str);
//	        if (sb.toString().equals(sb.reverse().toString())) {
//                System.out.println(sb.toString());
//                return sb.toString();
//            }
//	    }
//	    
//	    return maxStr;
//	}
	
	
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) != null) {
                return new int[]{map.get(nums[i]), i};
            }
            int v = target - nums[i];
            map.put(v, i);
        }
//        
        //       return null;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{nums[i], map.get(nums[i])};
            }
            int v = target - nums[i];
            map.put(v, nums[i]);
        }
return null;

    }
    
    
}

/**
 * Definition for singly-linked list.
 *
 */

 class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; 
    }
 }
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode tmp = l1;
        
        StringBuilder bull = new StringBuilder();
        while (tmp.next != null) {
            String i = new Integer(tmp.val).toString();
            bull.append(i);
            tmp= tmp.next; 
        }
        ListNode tmp2 = l2;
        StringBuilder bull2 = new StringBuilder();
        while (tmp2.next != null) {
            String j = new Integer(tmp2.val).toString();
            bull2.append(j);
            tmp2= tmp2.next; 
        }
        String value1 = bull.reverse().toString();
        String value2 = bull2.reverse().toString();
        
        String resoult = String.valueOf(Long.parseLong(value1) + Long.parseLong(value2));
        
        
        ArrayList<ListNode> list = new ArrayList<ListNode>();
        char[] res1 = resoult.toCharArray();
        for(int i = 0;i<res1.length;i++) {
            ListNode resremp = new ListNode(Integer.parseInt(String.valueOf(res1[res1.length-i-1])));
            list.add(resremp);
        }
        for (int i=0;i< list.size()-1;i++) {
            list.get(i).next=list.get(i+1);
        }
      
       return list.get(0);
        
    }

    public static void main(String [] args) {
        List<Integer> list = new ArrayList<>();
        for(int i = 100; i <200;i++) {
            list.add(i);
        }
        list.stream().forEach(t -> t.equals(""));
        System.out.println("hello world");

    }

}
