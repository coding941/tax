package com.trjs.swing;

import java.io.*;  

public class Resource {  
    public  void getResource() throws IOException{  
        File file=new File("bin/resource/res.txt");  
        BufferedReader br=new BufferedReader(new FileReader(file));  
        String s="";  
        while((s=br.readLine())!=null)  
            System.out.println(s);  
    }  
}  



