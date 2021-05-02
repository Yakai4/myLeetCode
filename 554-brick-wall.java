//my noob code out of memory limitaion
class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        int size = wall.size();
        int max=0;
        int[][] preSum = new int[size][];
        for(int i=0; i < size; ++i) {
            List e = wall.get(i); 
            preSum[i] = new int[e.size()]; 
            preSum[i] = prefix(e);
        }
        {//check the most number
            max = preSum[0][preSum[0].length-1];
            int[] cnt = new int[max+1];
            for(int i=0;i < preSum.length; ++i) {
                for(int j=0;j < preSum[i].length-1; ++j) {
                    ++cnt[preSum[i][j]];
                }
            }
            max = 0;
            for(int l:cnt) {
                max = Math.max(max,l);
            }
        }
        return size-max;

    }
    int[] prefix(List<Integer> e) {
        int size = e.size();
        int[] sum = new int[size];
        sum[0] = e.get(0);
        for(int i = 1;i<size;++i) {
            sum[i] = sum[i-1]+e.get(i);
        }
        return sum;
    }
}

//improved after the official code

class Solution {
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    public int leastBricks(List<List<Integer>> wall) {
        int size = wall.size();
        int max=0;

        for(int i=0; i < size; ++i) {
            List<Integer> widths = wall.get(i);
            int sum=0;
            for(int j=0; j < widths.size()-1; ++j) {
                sum += widths.get(j);//declare List<Integer> rather than List to widths or bad oprand type error occurs
                map.put(sum, map.getOrDefault(sum,0)+1);
            }
        }
            
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            max = Math.max(max,entry.getValue());
        }

        return size-max;
    }
}
