/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulación.io;
import java.util.*;
import java.lang.Math;
import java.util.ArrayList;

/**
 *
 * @author Ulises
 */
public class ControladorEventos {
    
    //clase evento de la que se crean los eventos
    static class Event{
        //int numEvent;   //id del evento
        double time;
        String nombre;
        
        public double getTime(){
            return time;
        }
    }
    
    static class File{                   // Clase que maneja las caracteristicas de los archivos
        
        double systemTime;
        int priority;
        int size;
    }
    
    //clase que compara el tiempo de reloj de los eventos para ordenarlos
    //Es usada por el priority queue para ordenar los eventos
    static class MyComparator implements Comparator<Event> {

    public int compare(Event a, Event b) {
        int result = new Double(a.getTime()).compareTo(b.getTime());
        return result;
    }
}
    /** Declaracion Variables globales **/
    
    //reloj global de la simulacion
    double clock; 
    MyComparator comparator = new MyComparator();  
    PriorityQueue<Event> events = new PriorityQueue<>(13, comparator);  //cola de prioridad que ordena los eventos por tiempo
    
    ArrayList <File> priorityFileA1 = new ArrayList <File>();     // Cola de prioridad de archivos 1 maquina A
    ArrayList <File> priorityFileA2 = new ArrayList <File>();     // Cola de prioridad de archivos 2 maquina A
        
    ArrayList <File> priorityFileB1 = new ArrayList <File>();     // Cola de prioridad de archivos 1 maquina B
    ArrayList <File> priorityFileB2 = new ArrayList <File>();     // Cola de prioridad de archivos 2 maquina B
    
    ArrayList <File> priorityFileC1 = new ArrayList <File>();     // Cola de prioridad de archivos 1 maquina C
    ArrayList <File> priorityFileC2 = new ArrayList <File>();     // Cola de prioridad de archivos 2 maquina C
    
    
    
    // ---------------- Fin de declaracion---------------------------------------- //
    
    //genera randoms en un intervalo
    public int randomWithRange(int min, int max){
        int range = (max - min) + 1;
        return (int)(Math.random()*range) + min;
    }
    
    //inicializa los eventos en tiempo cero. 
    //Crea los eventos de las maquinas, para decidiar cual maq comienza a enviar arhivo.
    public void setEventTime(){ //define los tiempos iniciales
        double temp;
        //se crean los eventos
        Event maqA = new Event();
        Event maqB = new Event();
        Event maqC = new Event();
        
        //se llenan los atributos de los eventos
        //maqA.numEvent = 1;
        maqA.time = timeFileA();
<<<<<<< HEAD
        maqA.nombre= "Llega archivo a maquina A";
        temp =maqA.getTime();
=======
        maqA.nombre= "Llega archivo a A";
>>>>>>> origin/master
        
        //maqB.numEvent = 2;
        maqB.time = timeFileB();
        maqB.nombre = "Llega archivo a B";
        if(temp > maqB.getTime()){
            temp = maqB.getTime();
        }
        
        //maqC.numEvent = 3;
        maqC.time = timeFileC();
        maqC.nombre = "Llega archivo a C";
        if(temp > maqC.getTime()){
            temp = maqC.getTime();
        }
        
        //se agregan eventos a la cola
        events.add(maqA);
        events.add(maqB );
        events.add(maqC);
        
        double rand = Math.random()*100;
        if(rand > 0 && rand < 32){
            Event rcvTkA = new Event();
            rcvTkA.nombre = "Recibe token A";
            rcvTkA.time = temp + 3; 
            events.add(rcvTkA);
        }
        else{
            if(rand > 33 && rand < 65){
                Event rcvTkB = new Event();
                rcvTkB.nombre = "Recibe token B";
                rcvTkB.time = temp + 3; 
                events.add(rcvTkB);
            }
            else{
                Event rcvTkC = new Event();
                rcvTkC.nombre = "Recibe token C";
                rcvTkC.time = temp + 3; 
                events.add(rcvTkC);
            }
        }
    }
    
    //tiempos de llegadas de archivos a las maquinas
    public double timeFileA(){
        double temp = Math.random();
        while(temp == 1){
            temp = Math.random();
        }
        return (-1*(Math.log(1-temp)))/5;
    }
    
    public double timeFileB(){
        return randomWithRange(8, 12)/40;
    }  
    
    public double timeFileC(){
        double addedValues = 0;
        for(int i = 0; i <= 12; i++){
            addedValues += Math.random();
        }
        return 0.1*(addedValues-6)+5;
    } 
    
    //define la prioridad del archivo
    public int filePriority(){
        int temp = Math.random()*4;
        if(temp == 0){
            return 1;
        }
        return 2;
    }
    
    //define el tamano de cada archivo
    public int fileSize(){
     return randomWithRange(1, 64);   
    }
    
    public void FileArrivesA(){
        
        Event maqA = events.poll();                 // Se saca el evento de la priority queue
        clock = maqA.getTime();
        File A = new File();
        
        A.systemTime = clock;                       // Se modifican los valores del nuevo evento
        A.priority = filePriority();
        A.size = fileSize();
        
        if(A.priority == 1){                        // Se agrega a la lista de prioridad de la maquina el archivo
            priorityFileA1.add(A);
        }else{
            priorityFileA2.add(A);
        }
        
        maqA.time = clock + timeFileA();            // Se calcula el tiempo siguiente cuando se va a dar otra llegada
        events.add(maqA);                           // Se vuelve a agregar el evento con nuevos valores
    }
    
    public void fileArrivesB(){
        Event maqB = events.poll();
        clock = maB.getTime();
        File B = new File();
        
        B.systemTime = clock;
        B.priority = filePriority();
        A.size = fileSize();
        
        if(B.priority == 1){
            priorityFileB1.add(B);
        }else{
            priorityFileB2.add(B);
        }
        
        maqB.time = clock + timeFileB();
        events.add(maqB);
    }
    
    public void fileArrivesC(){
        Event maqC = events.poll();
        clock = maqC.getTime();
        File C = new File();
        
        C.systemTime = clock;
        C.priority = filePriority();
        C.size = fileSize();
        
        if(C.priority == 1){
            priorityFileC1.add(C);
        }else{
            priorityFileC2.add(C);
        }
        
        maqC = clock + timeFileC();
        events.add(maqC);
    }
    
    public void terminaEnviarArchivo(){
    }
    
    public void ReceivesTokenA(){
        clock = 
    }
    
    public void recibeTokenB(){
    }
    
    public void recibeTokenC(){
    }
    
    public void routerHilo1(){
    }
    
    public void routerHilo2(){
    }
    
    public void recibeTokenC(){
    }
}
