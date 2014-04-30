package edu.sjsu.cmpe.cache.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.google.common.hash.Hashing;

public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Cache Client...");
              
        HashMap<DistributedCacheService, Integer> ConsMap = new HashMap<DistributedCacheService, Integer>();
        
        ConsMap.put(new DistributedCacheService("http://localhost:3000"), 0);
        ConsMap.put(new DistributedCacheService("http://localhost:3001"), 1);
        ConsMap.put(new DistributedCacheService("http://localhost:3002"), 2);
       
              
        int size = ConsMap.size();
        
        //System.out.println(size);
        
        for (int i=1; i<=10; i++){
        	
        	int bucket = Hashing.consistentHash( Hashing.md5().hashInt(i), size);
           // System.out.println(bucket);
            
            for (Integer value : ConsMap.values()) {
            	if ( bucket == value) {
            	String[] str ={"x", "a", "b", "c", "d", "e","f", "g", "h", "i", "j",};
            	//System.out.println(strChars[i]);
            		
            		          	//System.out.println("Value = " + value);
            	
            	
            	Iterator<Map.Entry<DistributedCacheService, Integer>> entries = ConsMap.entrySet().iterator();
                while (entries.hasNext()) {
                	
                	
                    Map.Entry<DistributedCacheService, Integer> entry = entries.next();
                    if ( bucket == entry.getValue()) {
                   // System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                    
                    entry.getKey().put(i, str[i]);
                    System.out.println("put(" + i + ") => " + str[i]);

                    String values = entry.getKey().get(i);
                    System.out.println("get(" + i + ") => " + values);
                    
                    }
                }
                
            	}
            }       	
            }
          
        

        System.out.println("Existing Cache Client...");
    }

}
