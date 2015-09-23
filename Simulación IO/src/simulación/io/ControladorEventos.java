/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulación.io;
import java.util.*;
import java.lang.Math;

/**
 *
 * @author Ulises
 */
public class ControladorEventos {
    
    //clase evento de la que se crean los eventos
    static class Event{
        int numEvent;   //id del evento
        double time;
        String nombre;
        
        public double getTime(){
            return time;
        }
    }
    
    //clase que compara el tiempo de reloj de los eventos para ordenarlos
    //Es usada por el priority queue para ordenar los eventos
    static class MyComparator implements Comparator<Event> {

    public int compare(Event a, Event b) {
        int result = new Double(a.getTime()).compareTo(b.getTime());
        return result;
    }
}
    //reloj global de la simulacion
    double clock; 
    MyComparator comparator = new MyComparator();  
    PriorityQueue<Event> events = new PriorityQueue<>(13, comparator);  //cola de prioridad que ordena los eventos por tiempo
    
    //genera randoms en un intervalo
    public int randomWithRange(int min, int max){
        int range = (max - min) + 1;
        return (int)(Math.random()*range) + min;
    }
    
    //inicializa los eventos en tiempo cero. 
    //Crea los eventos de las maquinas, para decidiar cual maq comienza a enviar arhivo.
    public void setEventTime(){ //define los tiempos iniciales
        //se crean los eventos
        Event maqA = new Event();
        Event maqB = new Event();
        Event maqC = new Event();
        
        //se llenan los atributos de los eventos
        maqA.numEvent = 1;
        maqA.time = timeFileA();
        maqA.nombre= "Llega archivo a maquina A";
        
        maqB.numEvent = 2;
        maqB.time = timeFileB();
        maqB.nombre = "Llega archivo a B";
       
        maqC.numEvent = 3;
        maqC.time = timeFileC();
        maqC.nombre = "Llega archivo a C";
        
        //se agregan eventos a la cola
        events.add(maqA);
        events.add(maqB);
        events.add(maqC);
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
    
    
    public void llegaArchivoA(){
        clock = (events.poll()).getTime();
    }
    
    public void llegaArchivoB(){
        clock = (events.poll()).getTime();
    }
    
    public void llegaArchivoC(){
        clock = (events.poll()).getTime();
    }
    
    public void terminaEnviarArchivo(){
    }
    
    public void recibeTokenA(){
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
