/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Amigos;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.NumberFormatException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

public class Amigo {
    private String Nombre;
    private long Numero;
    String nombreNumeroString;
    
    public Amigo(String Nombre, long Numero) {
        this.Nombre = Nombre;
        this.Numero = Numero;
    }

    public Amigo() {
    }
    

    public String getNombre() {
        return Nombre;
    }

    public long getNumero() {
        return Numero;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setNumero(long Numero) {
        this.Numero = Numero;
    }
    
    protected void AgregarAmigo(){
        boolean found=false;
        ListaAmigos listaAmigos=new ListaAmigos();  
        
        //con el while buscamos si el nombre o numero ingresado ya existe en la lista
        try{
            while (listaAmigos.lectura.getFilePointer() < listaAmigos.lectura.length()) {

                    nombreNumeroString = listaAmigos.lectura.readLine();


                    String[] lineSplit
                        = nombreNumeroString.split("!");


                    String nombreLista = lineSplit[0];
                    long numeroLista = Long.parseLong(lineSplit[1]);

                    if (Numero==numeroLista){
                        found = true;
                        break;
                    }
            }
            if (found == false){
                //se organiza el nombre y numero al formato nombre!numero
                nombreNumeroString= Nombre+"!"+String.valueOf(Numero);
                //se agraga el nombre y numero al archivo
                listaAmigos.lectura.writeBytes(nombreNumeroString);
                
                listaAmigos.lectura.writeBytes(System.lineSeparator());
                //se muestra mensaje de que se añadio el nombre amigo correctamente
                JOptionPane.showMessageDialog(null, "Amigo añadido correctamente",
                        "Exito", JOptionPane.INFORMATION_MESSAGE);
                
                listaAmigos.lectura.close();
            }else{
              JOptionPane.showMessageDialog(null, "El numero introducido ya existe",
                        "Error", JOptionPane.ERROR_MESSAGE);  
            }
        }
        catch(Exception ioe){
            System.out.println("error ioe en añadir");
        }
        
    }
    
    protected void mostrar(){
        boolean found=false;
        ListaAmigos listaAmigos=new ListaAmigos();
        int indice=0;
        try{    
            while (listaAmigos.lectura.getFilePointer() < listaAmigos.lectura.length()) {
 
                // leemos cada linea del archivo
                nombreNumeroString = listaAmigos.lectura.readLine();
                indice+=1;
 
                //separamos el formato nombre!numero
                String[] lineSplit
                    = nombreNumeroString.split("!");
 
                // separamos el nombre del  numero
                String nombreLista = lineSplit[0];
                long numeroLista = Long.parseLong(lineSplit[1]);
 
                // mostramos cada amigo de la lista
                    JOptionPane.showMessageDialog(null, "Nombre: " + nombreLista + "\n"
                    + "Numero de amigo: " + numeroLista + "\n",
                        "Amigo "+indice, JOptionPane.INFORMATION_MESSAGE);
        
        }
        
    }catch(Exception ioe){}
}
    
    protected void ActualizarAmigo(){
        boolean found=false;
        ListaAmigos listaAmigos=new ListaAmigos(); 
        String nombreLista;
        long numeroLista;
        int indice;
        
        //con el while buscamos si el nombre o numero ingresado ya existe en la lista
        try{
            while (listaAmigos.lectura.getFilePointer() < listaAmigos.lectura.length()) {

                nombreNumeroString = listaAmigos.lectura.readLine();


                    String[] lineSplit
                        = nombreNumeroString.split("!");


                nombreLista = lineSplit[0];
                numeroLista = Long.parseLong(lineSplit[1]);

                if (Numero==numeroLista){
                    found = true;
                    break;
                }
            }
            
            if (found==true){
                File fileTemporal=new File("temporal.txt");
                fileTemporal.createNewFile();

                RandomAccessFile lecturaTemporal=new RandomAccessFile(fileTemporal, "rw");

                listaAmigos.lectura.seek(0);

                while (listaAmigos.lectura.getFilePointer() < listaAmigos.lectura.length()) {
                    nombreNumeroString=listaAmigos.lectura.readLine();
                    String[] lineSplit
                    = nombreNumeroString.split("!");
                    numeroLista = Long.parseLong(lineSplit[1]);
                    
                    if (numeroLista==Numero){
                        nombreNumeroString=Nombre+"!"+Numero;
                    }
                    
                    //agrego el nombre y numero al archivo temporal
                    lecturaTemporal.writeBytes(nombreNumeroString);
                    lecturaTemporal.writeBytes(System.lineSeparator());
                    
                
                
                }
                
                lecturaTemporal.seek(0);
                listaAmigos.lectura.seek(0);
                
                while(listaAmigos.lectura.getFilePointer()<listaAmigos.lectura.length()){
                    listaAmigos.lectura.writeBytes(lecturaTemporal.readLine());
                    listaAmigos.lectura.writeBytes(System.lineSeparator());
                    
                }
                listaAmigos.lectura.setLength(lecturaTemporal.length());
                
                listaAmigos.lectura.close();
                lecturaTemporal.close();
                
                fileTemporal.delete();
                JOptionPane.showMessageDialog(null, "Amigo actualizado correctamente",
                        "Exito", JOptionPane.INFORMATION_MESSAGE);
                
            }else{
               JOptionPane.showMessageDialog(null, "El numero introducido no existe, por lo cual"
                       + "no puede ser actualizado",
                        "Error", JOptionPane.ERROR_MESSAGE);  
            }   
        
        
            
        }catch(Exception ioe){
            System.out.println("error act");
        }
    }
    
    protected void eliminar(){
        ListaAmigos listaAmigos = new ListaAmigos();
        String nombreLista, nombreNumeroString;
        long numeroLista;
        boolean found=false;
        
        try{
            while(listaAmigos.lectura.getFilePointer() < listaAmigos.lectura.length()){
                nombreNumeroString=listaAmigos.lectura.readLine();
                //separamos el nombre y el numero
                String[] separador=nombreNumeroString.split("!");
                
                nombreLista=separador[0];
                numeroLista=Long.parseLong(separador[1]);
                
                if(numeroLista==Numero){
                    found=true;
                    break;
                }
                
            }
            if (found==true){
                File archivoTemporal=new File("temporal.txt");
                archivoTemporal.createNewFile();
                RandomAccessFile lecturaTemporal=new RandomAccessFile(archivoTemporal, "rw");
                listaAmigos.lectura.seek(0);
                
                while(listaAmigos.lectura.getFilePointer() < listaAmigos.lectura.length()){
                    nombreNumeroString=listaAmigos.lectura.readLine();
                    String[] separador=nombreNumeroString.split("!");
                
                    nombreLista=separador[0];
                    numeroLista=Long.parseLong(separador[1]);
                    //si encuentra el numero a eliminar, hace que el iclo siga sin ejecutar lo de abajo
                    if (numeroLista==Numero){
                        continue;
                    }
                    //escribimos en el archivo temporal cada linea que no sea para eliminar
                    
                    lecturaTemporal.writeBytes(nombreNumeroString);
                    lecturaTemporal.writeBytes(System.lineSeparator());
                    
                }
                listaAmigos.lectura.seek(0);
                lecturaTemporal.seek(0);
                
                while(lecturaTemporal.getFilePointer() < lecturaTemporal.length()){
                    listaAmigos.lectura.writeBytes(lecturaTemporal.readLine());
                    listaAmigos.lectura.writeBytes(System.lineSeparator());
                }
                
                listaAmigos.lectura.setLength(lecturaTemporal.length());
                
                listaAmigos.lectura.close();
                lecturaTemporal.close();
                
                archivoTemporal.delete();
                JOptionPane.showMessageDialog(null, "Amigo eliminado correctamente",
                        "Exito", JOptionPane.INFORMATION_MESSAGE);
                
            }else{
               JOptionPane.showMessageDialog(null, "El numero introducido no existe, por lo cual"
                       + " el amigo no puede ser eliminado",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
        
        }catch(Exception ioe){
            System.out.println("error en eliminar");
        }
    }
}