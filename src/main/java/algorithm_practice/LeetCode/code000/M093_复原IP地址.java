package algorithm_practice.LeetCode.code000;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。

示例:

输入: "25525511135"
输出: ["255.255.11.135", "255.255.111.35"]

https://leetcode-cn.com/explore/interview/card/bytedance/242/string/1044/
https://leetcode-cn.com/problems/restore-ip-addresses/
Created by nibnait on 2020-01-06
*/
public class M093_复原IP地址 extends TestCase {

  @Test
  public void testCase() {
    String s1 = "25525511135";
    System.out.println(restoreIpAddresses(s1));

    String s2 = "25525511135";
    System.out.println(restoreIpAddresses(s2));

  }

  List<String> result = new ArrayList<>();

  public List<String> restoreIpAddresses(String s) {
    if (s == null || s.length() < 4 || s.length() > 12) {
      return result;
    }

    restoreIpAddresses(s, 0, "");

    return result;
  }

  private void restoreIpAddresses(String s, int n, String ipAddress) {
    if (n == 4) {
      if (s.isEmpty()) {
        result.add(ipAddress);
      }
      return;
    }

    for (int segLength = 1; segLength <= 3; segLength++) {
      if (s.length() < segLength) {
        break;
      }
      String currentSeg = s.substring(0, segLength);
      int value = Integer.valueOf(currentSeg);
      if (!isIpSegmentNum(value, segLength)) {
        continue;
      }
      restoreIpAddresses(s.substring(segLength), n + 1,
          ipAddress + currentSeg + (n == 3 ? "" : "."));
    }
  }

  private boolean isIpSegmentNum(int value, int length) {
    return String.valueOf(value).length() == length
        && value <= 255;
  }

  /*
  思路1:
递归+回溯法：
递归终止条件：查询点已经遍历完了整个字符串，如果这时候刚好是遍历完了获得了4个可行的字符集，
那么久将结果插入到最终的结果集合中，终止递归。如果遍历的4个IP段之后还有剩余字符，那么应该直接return终止掉递归。
特殊情况：递归过程中优先处理特殊场景，由于ip地址的首位不能为0，所以如果当前遍历到的字符串是0，
那么一定要单独把0作为当前段的ip地址，继续向下一段进行遍历。
递归条件：从当前位置开始，向后寻找每一个可能的ip段位，同时对这一段对应的ip地址整数值进行校验，
只要这一段满足 0< ip_num <= 255，就将这一段加入到最后的结果集合中，
同时将位置指针指向当前ip段位结尾的下一个位置，继续递归搜索下一段的ip地址。
   */
  int[] segment;

  public List<String> restoreIpAddresses1(String s) {
    segment = new int[4];
    dfs(s, 0, 0);
    return result;
  }

  private void dfs(String s, int sectionNumber, int startIndex) {
    if (sectionNumber == 4) {
      if (startIndex == s.length()) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
          sb.append(segment[i]);
          if (i != 3) {
            sb.append(".");
          }
        }
        result.add(sb.toString());
      }
      return;
    }

    if (startIndex == s.length()) {
      return;
    }

    if (s.charAt(startIndex) == '0') {
      segment[sectionNumber] = 0;
      dfs(s, sectionNumber + 1, startIndex + 1);
    }

    // 一般情况
    int adder = 0;
    for(int end=startIndex; end <s.length(); end++) {
      adder = adder*10 + (s.charAt(end) - '0');
      if(adder > 0 && adder <=255) {
        segment[sectionNumber] = adder;
        dfs(s, sectionNumber+1, end+1);
      } else {
        break;
      }
    }
  }


}
