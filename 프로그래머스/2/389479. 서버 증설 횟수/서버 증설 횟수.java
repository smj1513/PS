import java.util.*;
import java.lang.*;
class Server{
    int time;
    
    public Server(int k){
        time = k;
    }
    
    boolean isExpired(){
        return time == 0;
    } 
}

class Solution {
    //m = 서버 증설 바운더리
    public int solution(int[] players, int m, int k) {
        int x = 0;//증설 횟수
        List<Server> servers = new ArrayList<>();
        for(int player: players){
            if(player >= m*(servers.size()+1)){
                while(servers.size() < player/m){
                    x++;
                    servers.add(new Server(k));
                }
            }
            if(!servers.isEmpty()){
                Iterator<Server> itor = servers.iterator();
                while(itor.hasNext()){
                    Server server = itor.next();
                    server.time--;
                    if(server.isExpired()){
                        itor.remove();
                    }
                }
                
            }
//            System.out.println(player+" : " + m* (servers.size()+1));
        }
        return x;
    }
}