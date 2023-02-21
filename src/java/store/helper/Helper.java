/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package store.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author HIMANSHU MEHRA
 */
public class Helper {
        public static boolean deleteFile(String path){
        boolean f=false;
        try{
            File file= new File(path);
            f= file.delete();
            
        }
        catch(Exception e){
            System.out.println(e);
        }
        return f;
    }
    
    public static boolean saveFile(InputStream is, String path){
        boolean f=false;
        try{
            byte[] b= new byte[is.available()];
            is.read(b);
            
            FileOutputStream fos= new FileOutputStream(path);
            fos.write(b);
            fos.flush();
            fos.close();
            
            f=true;
            
        }
        catch(IOException e){
            System.out.println(e);
        }
        return f;
    }
}
