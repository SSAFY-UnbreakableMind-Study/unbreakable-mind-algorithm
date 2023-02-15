import java.util.*;

class PG_L2_택배상자{

    class Solution {

        public int solution(int[] order) {
            
            //보조 컨테이너 역할 스택
            Stack<Integer> stack = new Stack<>();
            
            //정답
            int answer = 0;

            //메인 컨테이너, 배달 순서
            int container = 1, pointer = 0;
    
            //더미 데이터
            stack.push(0);
            
            //마지막 배달순서 까지만 확인
            while(pointer < order.length){
                
                //현재 컨테이너와 배달 순서가 같을때
                if(container == order[pointer]){
                    answer++; pointer++; container++;
                }
                
                //현재 컨테이너보다 배달 순서의 크기가 클때
                else if(container < order[pointer]){
                    stack.push(container);
                    container++;
                }

                //현재 컨테이너보다 배달 순서의 크기가 작을때
                else{
                    if(stack.pop() == order[pointer]){
                        answer++; pointer++;
                    }
                    else break;
                }
            }     
            
            return answer;
        }
    }
}

