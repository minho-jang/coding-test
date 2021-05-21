package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14889_스타트와링크 {
  static int[][] power;
  static int N, answer;
  
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer stk;
    
    answer = Integer.MAX_VALUE;
    N = Integer.parseInt(br.readLine());
    power = new int[N][N];
    for (int i=0; i<N; i++) {
      stk = new StringTokenizer(br.readLine());
      for (int j=0; j<N; j++) {
        power[i][j] = Integer.parseInt(stk.nextToken());
      }
    }
    
    comb(0, 0, new int[N/2]);
    
    System.out.println(answer);
  }
  
  static void comb(int cnt, int start, int[] res) {
    if (cnt==N/2) {
      int[] resRev = new int[N/2];
      int idx = 0;
      for (int i=0; i<N; i++) {
        boolean isContains = false;
        for (int j=0; j<N/2; j++) {
          if (res[j] == i) {
            isContains = true;
            break;
          }
        }
        if (!isContains)
          resRev[idx++] = i; 
      }
      
      int a = getPower(res);
      int b = getPower(resRev);
      int diff = Math.abs(a-b);
      if (answer > diff)
        answer = diff;
      
      return;
    }
    
    for (int i=start; i<N; i++) {
      res[cnt] = i;
      comb(cnt+1, i+1, res);
    }
  }
  
  static int getPower(int[] arr) {
    int result = 0;
    int len = arr.length;
    for (int i=0; i<len; i++) {
      for (int j=i+1; j<len; j++) {
        result += power[arr[i]][arr[j]];
        result += power[arr[j]][arr[i]];
      }
    }
    return result;
  }
} 

//public class Main_BOJ_14889_스타트와링크 {
//	static int N, answer;
//	static int[][] stat;
//	
//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer stk;
//		
//		answer = Integer.MAX_VALUE;
//		N = Integer.parseInt(br.readLine());
//		stat = new int[N][N];
//		for (int i = 0; i < N; i++) {
//			stk = new StringTokenizer(br.readLine());
//			for (int j = 0; j < N; j++) {
//				stat[i][j] = Integer.parseInt(stk.nextToken());
//			}
//		}
//		
//		comb(0, 0, new int[N/2]);
//		System.out.println(answer);
//	}
//	
//	private static void comb(int cnt, int start, int[] res) {
//		if (cnt == N/2) {
//			// 조합완성
//			
//			int[] res2 = new int[N/2];
//			int idx = 0;
//			for (int i = 0; i < N; i++) {
//				boolean flag = true;
//				
//				for (int j = 0; j < N/2; j++) {
//					if (i == res[j])
//						flag = false;
//				}
//				if (flag)
//					res2[idx++] = i;
//			}
//			
////			System.out.println(Arrays.toString(res));
////			System.out.println(Arrays.toString(res2));
//			
//			int sum1 = getStatSum(res);
//			int sum2 = getStatSum(res2);
////			System.out.println(sum1 + " " + sum2);
//			
//			answer = Math.min(answer, Math.abs(sum1-sum2));
//			
//			return;
//		}
//		
//		for (int i = start; i < N; i++) {
//			res[cnt] = i;
//			comb(cnt+1, i+1, res);
//		}
//	}
//
//	private static int getStatSum(int[] team) {
//		int sum = 0;
//		for (int i = 0; i < team.length; i++) {
//			for (int j = 0; j < team.length; j++) {
//				sum += stat[team[i]][team[j]];
//			}
//		}
//		
//		return sum;
//	}
//}
