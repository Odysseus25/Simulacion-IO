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
        int numEvent;                   //id del evento
        double time;
        String nombre;
        
        /** 
         * Numeracion de eventos:
         * 
         * Llega archivo a A = 1
         * Llega archivo a B = 2
         * Llega archivo a C = 3
         * 
         * Recibe token A = 4
         * Recibe token B = 5
         * Recibe token C = 6
         * 
         * TPLAMA = 7
         * TPLAMB = 8
         * TPLAMC = 9
         * 
         * LASA = 10
         * SLA = 11
         * LAAR = 12
         
         * SRLT1 = 13
         * SRLT2 = 14
         * 
         * 
         **/
        
        
        public double getTime(){
            return time;
        }
    }
    
    static class File{                   // Clase que maneja las caracteristicas de los archivos
        
        double systemTime;
        int priority;
        int size;
    }
    
    static class Stadistics{                                                    // Utilizada como nodo de una lista de estadisticas que se almacenan por iteracion
        int filesSend;
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
    
    MyComparator comparator = new MyComparator();  
    PriorityQueue<Event> events = new PriorityQueue<>(13, comparator);  //cola de prioridad que ordena los eventos por tiempo
    
    ArrayList <File> priorityFileA1 = new ArrayList <File>();     // Cola de prioridad de archivos 1 maquina A
    ArrayList <File> priorityFileA2 = new ArrayList <File>();     // Cola de prioridad de archivos 2 maquina A
        
    ArrayList <File> priorityFileB1 = new ArrayList <File>();     // Cola de prioridad de archivos 1 maquina B
    ArrayList <File> priorityFileB2 = new ArrayList <File>();     // Cola de prioridad de archivos 2 maquina B
    
    ArrayList <File> priorityFileC1 = new ArrayList <File>();     // Cola de prioridad de archivos 1 maquina C
    ArrayList <File> priorityFileC2 = new ArrayList <File>();     // Cola de prioridad de archivos 2 maquina C
    
    ArrayList <File> serverFiles = new ArrayList <File>();        // Cola de archivos del servidor de antivirus
    
    ArrayList <Stadistics> itStadistics = new ArrayList <Stadistics>();         // Cola que almacena las estadisticas por iteracion
    
    double tokenTime;                                             // Se inicializa con el tiempo que define el usuario, usando el metodo set
    double originalTokenTime;                                     // Valor original del token obtenido de la interfaz
    double clock; 
    
    int filesSend;                                                // Archivos enviados por iteracion al recibir el token
    
    // ---------------- Fin de declaracion---------------------------------------- //
    
    
    // Se setea el tiempo del token con el valor obtenido de la interfaz
    public void setTokenTime(){                          
        tokenTime = originalTokenTime;
    }
    
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
        maqA.numEvent = 1;
        maqA.time = timeFileA();
        maqA.nombre= "Llega archivo a A";
        temp =maqA.getTime();
        
        maqB.numEvent = 2;
        maqB.time = timeFileB();
        maqB.nombre = "Llega archivo a B";
        if(temp > maqB.getTime()){
            temp = maqB.getTime();
        }
        
        maqC.numEvent = 3;
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
        double temp = Math.random()*4;
        if(temp < 1){
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
        clock = maqB.getTime();
        File B = new File();
        
        B.systemTime = clock;
        B.priority = filePriority();
        B.size = fileSize();
        
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
        
        maqC.time = clock + timeFileC();
        events.add(maqC);
    }
    
        
    public void receivesTokenA(){
        Event recA = events.poll();                                             // Copia del evento actual
        clock = recA.getTime();
        recA.numEvent = 1;
        setTokenTime();
        filesSend = 0;
        boolean availableSend = false;
        int i = 0;
        
        while(!availableSend && i < priorityFileA1.size()){                     // Verifica si existe un archivo en la cola de prioridad 1 que se
            if( (priorityFileA1.get(i).size * 1/2) <= tokenTime ){              // pueda enviar en el tiempoToken
                availableSend = true;
            }else{
                ++i;
            }
        }
        
        if(availableSend){
            File A = priorityFileA1.get(i);                                     // Se copia y saca el archivo de la cola de prioridad
            priorityFileA1.remove(i);
            
            Event LASA = new Event();                                           // Nuevo evento para este unico archivo
            LASA.numEvent = 10;
            LASA.time = clock + (A.size * 1/2) + (1/4);                         // Tiempo de llegada al servidor de antivirus
            events.add(LASA);
            serverFiles.add(A);                                                 // Se agrega a la cola del servidor de antivirus
            
            Event TPLAMA = new Event();                                         // Evento termina poner en linea archivo maquina A
            TPLAMA.numEvent = 7;
            TPLAMA.time = clock + (A.size * 1/2);
            events.add(TPLAMA);
            tokenTime -= (A.size * 1/2);
            ++filesSend;
            
            recA.time = 1000000;                                               // Se vuelve a agregar el evento con tiempo infinito
            events.add(recA);
        
        }else{
            int j = 0;
            while(!availableSend && j < priorityFileA2.size()){                 // Se fija si hay un archivo que se pueda enviar de la cola de prioridad 2
                if( (priorityFileA2.get(j).size * 1/2) <= tokenTime){
                    availableSend = true;
                }else{
                    ++j;
                }
            }
            
            if(availableSend){
                File A = priorityFileA2.get(j);
                priorityFileA2.remove(j);
                
                Event LASA = new Event();
                LASA.numEvent = 10;
                LASA.time = clock + ( A.size * 1/2 ) + 1/4;
                events.add(LASA);
                serverFiles.add(A);
                
                Event TPLAMA = new Event();                                         // Evento termina poner en linea archivo maquina A
                TPLAMA.numEvent = 7;
                TPLAMA.time = clock + (A.size * 1/2);
                events.add(TPLAMA);
                tokenTime -= (A.size * 1/2);
                ++filesSend;
                
                recA.time = 1000000;
                events.add(recA);
            }else{                
                recA.time = 1000000;
                events.add(recA);
                
                Event recB = new Event();
                recB.numEvent = 2;
                recB.time = clock;
                events.add(recB);
                
                // guardar la cantidad de archivos enviados
                        
            }
        }
    }
    
    public void receivesTokenB(){
        Event recB = events.poll();
        clock = recB.getTime();
        recB.numEvent = 5;
        setTokenTime();
        filesSend = 0;
        boolean availableSend = false;
        int i = 0;
        
        while(!availableSend && i < priorityFileB1.size()){
            if( (priorityFileB1.get(i).size * 1/2) <= tokenTime ){
                availableSend = true;
            }else{
                ++i;
            }
        }
        
        if(availableSend){
            File B = priorityFileB1.get(i);
            priorityFileB1.remove(i);
            
            Event LASA = new Event();                                           // Nuevo evento para este unico archivo
            LASA.numEvent = 10;
            LASA.time = clock + (B.size * 1/2) + (1/4);                         // Tiempo de llegada al servidor de antivirus
            events.add(LASA);
            serverFiles.add(B); 
            
            Event TPLAMB = new Event();                                         // Evento termina poner en linea archivo maquina B
            TPLAMB.numEvent = 8;
            TPLAMB.time = clock + (B.size * 1/2);
            events.add(TPLAMB);
            tokenTime -= (B.size * 1/2);
            ++filesSend;
            
            recB.time = 1000000;                                                // Se vuelve a agregar el evento con tiempo infinito
            events.add(recB);
        }else{
            int j = 0;
            while(!availableSend && j < priorityFileB2.size()){
                if( (priorityFileB2.get(j).size * 1/2) <= tokenTime ){
                    availableSend = true;
                }else{
                    ++j;
                }
            }
            if(availableSend){
                File B = priorityFileB2.get(j);
                priorityFileB2.remove(j);
                
                Event LASA = new Event();                                           // Nuevo evento para este unico archivo
                LASA.numEvent = 10;
                LASA.time = clock + (B.size * 1/2) + (1/4);                         // Tiempo de llegada al servidor de antivirus
                events.add(LASA);
                serverFiles.add(B); 

                Event TPLAMB = new Event();                                         // Evento termina poner en linea archivo maquina B
                TPLAMB.numEvent = 8;
                TPLAMB.time = clock + (B.size * 1/2);
                events.add(TPLAMB);
                tokenTime -= (B.size * 1/2);
                ++filesSend;

                recB.time = 1000000;                                               // Se vuelve a agregar el evento con tiempo infinito
                events.add(recB);
            }else{
                recB.time = 1000000;
                events.add(recB);
                
                Event recC = new Event();
                recC.numEvent = 6;
                recC.time = clock;
                events.add(recC);
            }
        }                
    }
    
    public void receivesTokenC(){
        Event recC = events.poll();
        clock = recC.getTime();
        recC.numEvent = 6;
        setTokenTime();
        filesSend = 0;
        boolean availableSend = false;
        int i = 0;
        
        while(!availableSend && i < priorityFileC1.size()){
            if( (priorityFileC1.get(i).size * 1/2) <= tokenTime ){
                availableSend = true;
            }else{
                ++i;
            }
        }
        
        if(availableSend){
            File C = priorityFileC1.get(i);
            priorityFileC1.remove(i);
            
            Event LASA = new Event();                                           // Nuevo evento para este unico archivo
            LASA.numEvent = 10;
            LASA.time = clock + (C.size * 1/2) + (1/4);                         // Tiempo de llegada al servidor de antivirus
            events.add(LASA);
            serverFiles.add(C); 
            
            Event TPLAMC = new Event();                                         // Evento termina poner en linea archivo maquina C
            TPLAMC.numEvent = 9;
            TPLAMC.time = clock + (C.size * 1/2);
            events.add(TPLAMC);
            tokenTime -= (C.size * 1/2);
            ++filesSend;
            
            recC.time = 1000000;                                                // Se vuelve a agregar el evento con tiempo infinito
            events.add(recC);
        }else{
            int j = 0;
            while(!availableSend && j < priorityFileC2.size()){
                if( (priorityFileC2.get(j).size * 1/2) <= tokenTime ){
                    availableSend = true;
                }else{
                    ++j;
                }
            }
            if(availableSend){
                File C = priorityFileC2.get(j);
                priorityFileC2.remove(j);
                
                Event LASA = new Event();                                           // Nuevo evento para este unico archivo
                LASA.numEvent = 10;
                LASA.time = clock + (C.size * 1/2) + (1/4);                         // Tiempo de llegada al servidor de antivirus
                events.add(LASA);
                serverFiles.add(C); 

                Event TPLAMC = new Event();                                         // Evento termina poner en linea archivo maquina C
                TPLAMC.numEvent = 9;
                TPLAMC.time = clock + (C.size * 1/2);
                events.add(TPLAMC);
                tokenTime -= (C.size * 1/2);
                ++filesSend;

                recC.time = 1000000;                                               // Se vuelve a agregar el evento con tiempo infinito
                events.add(recC);
            }else{
                recC.time = 1000000;
                events.add(recC);
                
                Event recA = new Event();
                recA.numEvent = 6;
                recA.time = clock;
                events.add(recA);
            }
        }        
    }
    
    public void tplama(){                                                       // Metodo que termina de poner en linea el archivo de la maquina A
        Event tplama = events.poll();
        tplama.numEvent = 7;
        clock = tplama.getTime();
        
        if(tokenTime == 0){
            tplama.time = 1000000;
            //almacenar cantidad de archivos enviados
            Event recB = new Event();
            recB.numEvent = 5;
            recB.time = clock;            
        }else{
            boolean availableSend = false;
            int i = 0;
            while(!availableSend && i < priorityFileA1.size()){
                if( (priorityFileA1.get(i).size * 1/2) <= tokenTime ){
                    availableSend = true;
                }else{
                    ++i;
                }
            }
            
            if(availableSend){
                File A = priorityFileA1.get(i);
                priorityFileA1.remove(i);
                
                Event LASA = new Event();                                           // Nuevo evento para este unico archivo
                LASA.numEvent = 10;
                LASA.time = clock + (A.size * 1/2) + (1/4);                         // Tiempo de llegada al servidor de antivirus
                events.add(LASA);
                serverFiles.add(A);                                                 // Se agrega a la cola del servidor de antivirus

                Event TPLAMA = new Event();                                         // Evento termina poner en linea archivo maquina A
                TPLAMA.numEvent = 7;
                TPLAMA.time = clock + (A.size * 1/2);
                events.add(TPLAMA);
                tokenTime -= (A.size * 1/2);
                ++filesSend;
            }else{
                int j = 0;
                while(!availableSend && j < priorityFileA2.size()){                 // Se fija si hay un archivo que se pueda enviar de la cola de prioridad 2
                    if( (priorityFileA2.get(j).size * 1/2) <= tokenTime){
                        availableSend = true;
                    }else{
                        ++j;
                    }
                }   
                
                if(availableSend){
                    File A = priorityFileA2.get(j);
                    priorityFileA2.remove(j);

                    Event LASA = new Event();
                    LASA.numEvent = 10;
                    LASA.time = clock + ( A.size * 1/2 ) + 1/4;
                    events.add(LASA);
                    serverFiles.add(A);

                    Event TPLAMA = new Event();                                         // Evento termina poner en linea archivo maquina A
                    TPLAMA.numEvent = 7;
                    TPLAMA.time = clock + (A.size * 1/2);
                    events.add(TPLAMA);
                    tokenTime -= (A.size * 1/2);
                    ++filesSend;
                }else{                
                    
                    tplama.time = 1000000;
                    events.add(tplama);
                    
                    Event recB = new Event();
                    recB.numEvent = 2;
                    recB.time = clock;
                    events.add(recB);

                    // guardar la cantidad de archivos enviados                        
                }                
            }
        }
    }
    
    public void tplamb(){                                                       // Metodo que termina de poner en linea el archivo de la maquina B
        Event tplamb = events.poll();
        tplamb.numEvent = 8;
        clock = tplamb.getTime();
        
        if(tokenTime == 0){
            tplamb.time = 1000000;
            //almacenar cantidad de archivos enviados
            Event recC = new Event();
            recC.numEvent = 6;
            recC.time = clock;            
        }else{
            boolean availableSend = false;
            int i = 0;
            while(!availableSend && i < priorityFileB1.size()){
                if( (priorityFileB1.get(i).size * 1/2) <= tokenTime ){
                    availableSend = true;
                }else{
                    ++i;
                }
            }
            
            if(availableSend){
                File B = priorityFileB1.get(i);
                priorityFileB1.remove(i);
                
                Event LASA = new Event();                                           // Nuevo evento para este unico archivo
                LASA.numEvent = 10;
                LASA.time = clock + (B.size * 1/2) + (1/4);                         // Tiempo de llegada al servidor de antivirus
                events.add(LASA);
                serverFiles.add(B);                                                 // Se agrega a la cola del servidor de antivirus

                Event TPLAMB = new Event();                                         // Evento termina poner en linea archivo maquina A
                TPLAMB.numEvent = 8;
                TPLAMB.time = clock + (B.size * 1/2);
                events.add(TPLAMB);
                tokenTime -= (B.size * 1/2);
                ++filesSend;
            }else{
                int j = 0;
                while(!availableSend && j < priorityFileB2.size()){                 // Se fija si hay un archivo que se pueda enviar de la cola de prioridad 2
                    if( (priorityFileB2.get(j).size * 1/2) <= tokenTime){
                        availableSend = true;
                    }else{
                        ++j;
                    }
                }   
                
                if(availableSend){
                    File B = priorityFileB2.get(j);
                    priorityFileB2.remove(j);

                    Event LASA = new Event();
                    LASA.numEvent = 10;
                    LASA.time = clock + ( B.size * 1/2 ) + 1/4;
                    events.add(LASA);
                    serverFiles.add(B);

                    Event TPLAMB = new Event();                                         // Evento termina poner en linea archivo maquina A
                    TPLAMB.numEvent = 8;
                    TPLAMB.time = clock + (B.size * 1/2); //////
                    events.add(TPLAMB);
                    tokenTime -= (B.size * 1/2);
                    ++filesSend;
                }else{                
                    
                    tplamb.time = 1000000;
                    events.add(tplamb);
                    
                    Event recC = new Event();
                    recC.numEvent = 6;
                    recC.time = clock;
                    events.add(recC);

                    // guardar la cantidad de archivos enviados                        
                }                
            }
        }        
    }
    
    public void tplamc(){
        Event tplamc = events.poll();
        tplamc.numEvent = 9;
        clock = tplamc.getTime();
        
        if(tokenTime == 0){
            tplamc.time = 1000000;
            //almacenar cantidad de archivos enviados
            Event recA = new Event();
            recA.numEvent = 4;
            recA.time = clock;            
        }else{
            boolean availableSend = false;
            int i = 0;
            while(!availableSend && i < priorityFileC1.size()){
                if( (priorityFileC1.get(i).size * 1/2) <= tokenTime ){
                    availableSend = true;
                }else{
                    ++i;
                }
            }
            
            if(availableSend){
                File C = priorityFileC1.get(i);
                priorityFileC1.remove(i);
                
                Event LASA = new Event();                                           // Nuevo evento para este unico archivo
                LASA.numEvent = 10;
                LASA.time = clock + (C.size * 1/2) + (1/4);                         // Tiempo de llegada al servidor de antivirus
                events.add(LASA);
                serverFiles.add(C);                                                 // Se agrega a la cola del servidor de antivirus

                Event TPLAMC = new Event();                                         // Evento termina poner en linea archivo maquina A
                TPLAMC.numEvent = 9;
                TPLAMC.time = clock + (C.size * 1/2);
                events.add(TPLAMC);
                tokenTime -= (C.size * 1/2);
                ++filesSend;
            }else{
                int j = 0;
                while(!availableSend && j < priorityFileC2.size()){                 // Se fija si hay un archivo que se pueda enviar de la cola de prioridad 2
                    if( (priorityFileC2.get(j).size * 1/2) <= tokenTime){
                        availableSend = true;
                    }else{
                        ++j;
                    }
                }   
                
                if(availableSend){
                    File C = priorityFileC2.get(j);
                    priorityFileC2.remove(j);

                    Event LASA = new Event();
                    LASA.numEvent = 10;
                    LASA.time = clock + ( C.size * 1/2 ) + 1/4;
                    events.add(LASA);
                    serverFiles.add(C);

                    Event TPLAMC = new Event();                                         // Evento termina poner en linea archivo maquina A
                    TPLAMC.numEvent = 9;
                    TPLAMC.time = clock + (C.size * 1/2); //////
                    events.add(TPLAMC);
                    tokenTime -= (C.size * 1/2);
                    ++filesSend;
                }else{                
                    
                    tplamc.time = 1000000;
                    events.add(tplamc);
                    
                    Event recA = new Event();
                    recA.numEvent = 4;
                    recA.time = clock;
                    events.add(recA);

                    // guardar la cantidad de archivos enviados                        
                }                
            }
        }
    }
    
}
