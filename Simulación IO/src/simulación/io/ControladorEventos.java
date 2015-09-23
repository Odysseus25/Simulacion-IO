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
        int numEvent;   //id del evento
        double time;
        String nombre;
        
        public double getTime(){
            return time;
        }
    }
    
    static class File{                   // Clase que maneja las caracteristicas de los archivos
        
        double tiempoEnSistema;
        int prioridad;
        int tamanio;
    }
    
    
    /** Declaracion Variables globales **/
    
    //reloj global de la simulacion
    double clock; 
    MyComparator comparator = new MyComparator();  
    PriorityQueue<Event> events = new PriorityQueue<>(13, comparator);  //cola de prioridad que ordena los eventos por tiempo
    
    ArrayList <Archive> priorityFileA1 = new ArrayList <Archive>();     // Cola de prioridad de archivos 1 maquina A
    ArrayList <Archive> priorityFileA2 = new ArrayList <Archive>();     // Cola de prioridad de archivos 2 maquina A
        
    ArrayList <Archive> priorityFileB1 = new ArrayList <Archive>();     // Cola de prioridad de archivos 1 maquina B
    ArrayList <Archive> priorityFileB2 = new ArrayList <Archive>();     // Cola de prioridad de archivos 2 maquina B
    
    ArrayList <Archive> priorityFileC1 = new ArrayList <Archive>();     // Cola de prioridad de archivos 1 maquina C
    ArrayList <Archive> priorityFileC2 = new ArrayList <Archive>();     // Cola de prioridad de archivos 2 maquina C
    
    
    
    // ---------------- Fin de declaracion---------------------------------------- //
    
    
    
    //clase que compara el tiempo de reloj de los eventos para ordenarlos
    //Es usada por el priority queue para ordenar los eventos
    static class MyComparator implements Comparator<Event> {

    public int compare(Event a, Event b) {
        int result = new Double(a.getTime()).compareTo(b.getTime());
        return result;
    }
}
    
    //genera randoms en un intervalo
    public int randomWithRange(int min, int max){
        int range = (max - min) + 1;
        return (int)(Math.random()*range) + min;
    }
    
    //inicializa los eventos en tiempo cero. 
    //Crea los eventos de las maquinas, para decidiar cual maq comienza a enviar arhivo.
    public void setTiemposEventos(){ //define los tiempos iniciales
        //se crean los eventos
        Event maqA = new Event();
        Event maqB = new Event();
        //Event maqC = new Event();
        
        //se llenan los atributos de los eventos
        maqA.numEvent = 1;
        maqA.time = (-1*(Math.log(1- Math.random())))/5;
        maqA.nombre= "Llega archivo a maquina A";
        
        maqB.numEvent = 2;
        maqB.time = randomWithRange(8, 12)/40;
        maqB.nombre = "Llega archivo a B";
       
        //averiguar como sacar tiempo de dist normal
        /*maqC.numEvent = 2;
        maqC.time = ;
        maqC.nombre = "Llega archivo a C"; */ 
        
        //se agregan eventos a la cola
        events.add(maqA);
        events.add(maqB);
        //events.add(timeC);
    }
    
    //falta la logica para programar los eventos
    public void llegaArchivoA(){
        //clock = (events.poll()).getTime();
        
        
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
