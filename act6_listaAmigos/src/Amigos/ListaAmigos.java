/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Amigos;

    import java.io.File;
    import java.io.IOException;
    import java.io.RandomAccessFile;
    

public class ListaAmigos { 
    File archivo= new File("Amigos.txt");
    RandomAccessFile lectura;
    public ListaAmigos() {
        try{
            
            if (!archivo.exists()){
                archivo.createNewFile();
            }
            lectura= new RandomAccessFile(archivo, "rw");
            
        
        }catch(Exception ioe){
            System.out.println("error ioe en lista");
        }
    }
    
    
    
    

    
    
    
    
}
