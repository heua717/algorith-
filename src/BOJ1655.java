import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ1655 {
    static int N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
    static PriorityQueue<Integer> pq2 = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            if(pq.size() == pq2.size()) {
                pq.add(num);
            }
            else{
                pq2.add(num);
            }
            if(!pq.isEmpty() && !pq2.isEmpty()) {
                if(pq.peek() > pq2.peek()) {
                    int tmp = pq.poll();
                    pq.offer(pq2.poll());
                    pq2.offer(tmp);
                }
            }
            sb.append(pq.peek()+"\n");
        }
        System.out.println(sb);

    }
}
