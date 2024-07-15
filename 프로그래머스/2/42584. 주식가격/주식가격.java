import java.util.Stack;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack<>();
        
        for (int i=0; i<prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {   // 빈스택아니고, 가격 떨어졌을때
                answer[stack.peek()] = i - stack.peek();    // 가격이 떨어지지 않은 기간
                stack.pop();
            }
            stack.push(i);
        }
        
        while (!stack.isEmpty()) {  // 끝까지 남아있다면 == 절대 가격 안떨어지는 주식임
            answer[stack.peek()] = prices.length - stack.peek() - 1;    // 나 뒤에 있는 갯수가 답
            stack.pop();
        }
        
        return answer;
    }
}