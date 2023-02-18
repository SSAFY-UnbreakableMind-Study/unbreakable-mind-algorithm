import java.util.ArrayList;
import java.util.Comparator;

class MyComparator implements Comparator<int[]> {
	@Override
	public int compare(int[] o1, int[] o2) {
		for(int i=10; i>-1; i--) {
			if(o1[i] > o2[i])
				return -1;
            if(o1[i] == o2[i])
                continue;    
            return 1;
		}
        return 0;
	}
}

class Solution {
	int n;
	int[] info;
	int max = 1;
	ArrayList<int[]> ans = new ArrayList<>();
	
	void check(int[] ryan) {
		int diff = 0;
		for(int i=0; i<10; i++) {
			if(info[i] == 0 && ryan[i] == 0) {
				continue;
			}
			if(info[i] < ryan[i]) {
				diff += 10-i;
			}
			else {
				diff -= 10-i;
			}
			
		}
		if(max < diff) {
			max = diff;
			ans.clear();
			ans.add(ryan);
		}
		else if(max == diff) {
			ans.add(ryan);
		}
	}
	
	void dfs(int i, int[] ryan, int cnt) {
		if(cnt == n) {
			check(ryan);
			return;
		}
		else if(cnt > n) return;
		else {
			if(i>10) return;
			
			int[] tmp1 = ryan.clone();
			
			tmp1[i] = (i==10) ? n-cnt : info[i]+1;
			
			dfs(i+1, tmp1, cnt+tmp1[i]);
			
			dfs(i+1, ryan, cnt);
		}		
	}
	
    public int[] solution(int n, int[] info) {
    	this.n = n;
    	this.info = info;
        
        dfs(0, new int[11], 0);
        
        ans.sort(new MyComparator());
        if(ans.isEmpty()) return new int[] {-1};
        return ans.get(0);
    }
    
}