package Melon.JSON.Crawling;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class MelonNewChart {
        public static void main(String[] args) {
                String[] str1 = new String[50];
                ArrayList<String> arr = new ArrayList<>();
                for(int i = 0; i < 50; i++) {
                        str1[i] = i+"";
                        arr.add(str1[i]);
                }

                for(int i = 0 ; i < str1.length; i++) {
                        for(int j = 0; j < str1.length; j++) {
                                if(arr.get(i).equals(str1[j])) {
                                        System.out.println("같다 - arr : " + arr.get(i) + ", str1 : " + str1[j]);
                                }
//                                System.out.println("배열 1 : " + i + "번째 : " + arr.get(i) + "\n배열 2 : " + j + "번째 : " + str1[j]);
//                                System.out.println();
                        }
                }
        }
}
