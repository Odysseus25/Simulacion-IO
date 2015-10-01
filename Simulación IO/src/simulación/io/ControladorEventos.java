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
        double size;
    }
    
    static class Statistics{                                                    // Utilizada como nodo de una lista de estadisticas que se almacenan por iteracion
        //tiempos promedio de los archivos en el sistema 
        double generalAverageTime;
        double averageTime1;
        double averageTime2;
        //tamanos promedios de las colas de las maquinas y el antivirus
        int QSizeA;
        int QSizeB;
        int QSizeC;
        int QSizeS;
        //numero promedio de archivos enviados por tiempo de token 
        int averageSentFiles;
        //promedio de revisiones por archivo por parte del antivirus
        int averageChecks; 
        
        //calcula el tiempo promedio de los archivos en el sistema
        public void calculateAverageDouble(Vector<Double> files, int type){
            double temp = 0.0;
            for(int i = 0; i < files.size()-1; i++){
                temp += files.get(i);
            }
            switch(type){
                case 0:
                    this.generalAverageTime = temp/files.size();
                    break; 
                case 1:
                    this.averageTime1 = temp/files.size();
                    break;                
                case 2:
                    this.averageTime2 = temp/files.size();
                    break;              
                default:
                    break;
            }
        }
        
        //calcula el tamano promedio de la cola de las 3 maquinas y el servidor antivrus
        //O calcula la cantidad de archivos enviados O la cantidad de revisadas por archivo 
        public void calculateAverageInt(Vector<Integer> queue, int type){
            int temp = 0;
            for(int i = 0; i < queue.size()-1; i++){
                temp += queue.get(i);
            }
            switch(type){
                case 0:
                    this.QSizeA= temp/queue.size();
                    break; 
                case 1:
                    this.QSizeB = temp/queue.size();
                    break;                
                case 2:
                    this.QSizeC = temp/queue.size();
                    break;
                case 3:
                    this.QSizeS = temp/queue.size();
                case 4:
                     this.averageSentFiles = temp/queue.size();
                    break;
                case 5:
                    this.averageChecks = temp/queue.size();
                    break;
                default:
                    break;
            }
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
    /** Declaracion Variables globales **/
    
    MyComparator comparator = new MyComparator();  
    PriorityQueue<Event> events = new PriorityQueue<>(13, comparator);  //cola de prioridad que ordena los eventos por tiempo
    
    Statistics stats = new Statistics();       //maneja las estadisticas de cada simulacion 
    
    ArrayList <File> priorityFileA1 = new ArrayList <>();     // Cola de prioridad de archivos 1 maquina A
    ArrayList <File> priorityFileA2 = new ArrayList <>();     // Cola de prioridad de archivos 2 maquina A
        
    ArrayList <File> priorityFileB1 = new ArrayList <>();     // Cola de prioridad de archivos 1 maquina B
    ArrayList <File> priorityFileB2 = new ArrayList <>();     // Cola de prioridad de archivos 2 maquina B
    
    ArrayList <File> priorityFileC1 = new ArrayList <>();     // Cola de prioridad de archivos 1 maquina C
    ArrayList <File> priorityFileC2 = new ArrayList <>();     // Cola de prioridad de archivos 2 maquina C
    
    ArrayList <File> serverFiles = new ArrayList <>();        // Cola de archivos del servidor de antivirus
    ArrayList <File> routerFiles = new ArrayList <>();        // Cola de archivos del router
    
    ArrayList <Statistics> itStadistics = new ArrayList <>();         // Cola que almacena las estadisticas por iteracion
    ArrayList <File> finishedFiles = new ArrayList <>();
    
//vectores dedicados a calcular estadisticas 
    Vector<Double> generalFileTime = new Vector<>();              //vectores que contienen los tiempos de los archivos en sistema 
    Vector<Double> fileTime1 = new Vector<>();                     //en general y por prioridad
    Vector<Double> fileTime2 = new Vector<>();
    
    Vector<Integer> queueSizeA = new Vector<>();            //vectores que almacenan el tamano de la cola de cada maquina
    Vector<Integer> queueSizeB = new Vector<>();
    Vector<Integer> queueSizeC = new Vector<>();
    Vector<Integer> queueSizeServer = new Vector<>();
    
    Vector<Integer> filesSentByToken = new Vector<>();      //almacena los archivos enviados por token 
    
    Vector<Integer> virusCheck = new Vector<>();
    
    Vector<Statistics> simulationStats = new Vector<>();
    
    double tokenTime;                                             // Se inicializa con el tiempo que define el usuario, usando el metodo set
    double originalTokenTime;                                     // Valor original del token obtenido de la interfaz
    double clock; 
    
    int filesSend;                                                // Archivos enviados por iteracion al recibir el token
    
    boolean antivirusAvailable = true;
    boolean transmisionLine1 = true;                                            // Lineas de transmision de router
    boolean transmisionLine2 = true;
    
    // ---------------- Fin de declaracion---------------------------------------- //
    
    //Se calculan las estadisticas de por simulacion
    public void calculateStatistics(){
        //estancia de archivos en el sistema
        stats.calculateAverageDouble(generalFileTime, 0);           //tiempo general
        stats.calculateAverageDouble(fileTime1, 1);                 //prioridad 1
        stats.calculateAverageDouble(fileTime2, 2);                 //prioridad 2
        
        //tamano de cola
        stats.calculateAverageInt(queueSizeA, 0);                   //maquina A
        stats.calculateAverageInt(queueSizeB, 1);                   //maquina B
        stats.calculateAverageInt(queueSizeC, 2);                   //maquina C
        stats.calculateAverageInt(queueSizeServer, 3);              //antivirus
        
        //archivos enviados por token
        stats.calculateAverageInt(filesSentByToken, 4);
        
        //revisadas de virus por archivo
        stats.calculateAverageInt(virusCheck, 5);
        
        simulationStats.add(stats);
        
        System.out.println();
        System.out.println("Tamaño Promedio Cola A:        "+stats.QSizeA);
        System.out.println("Tamaño Promedio Cola B:        "+stats.QSizeB);
        System.out.println("Tamaño Promedio Cola C:        "+stats.QSizeC);
        System.out.println("Tamaño Promedio Cola Servidor: "+stats.QSizeS);
        System.out.println();
        System.out.println("Promedio de Permamencia General de Archivo:        "+stats.generalAverageTime);        
        System.out.println("Promedio de Permanencia de Archivo Prioridad 1:    "+stats.averageTime1);
        System.out.println("Promedio de Permanencia de Archivo Prioridad 2:    "+stats.averageTime2);
        System.out.println();
        System.out.println("Promedio Archivos Enviados por Token: "+stats.averageSentFiles);
        System.out.println("Promedio de Revisiones por Archivo:   "+stats.averageChecks);
        System.out.println();
        System.out.println();
    }
    
    // Se setea el tiempo del token con el valor obtenido de la interfaz
    public void setTokenTime(){                          
        tokenTime = originalTokenTime;
    }
    
    //genera randoms en un intervalo
    public double randomWithRange(int min, int max){
        int range = (max - min) + 1;
        return (Math.random()*range) + min;
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
        temp = maqA.getTime();
        
        maqB.numEvent = 2;
        maqB.time = timeFileB();
        maqB.nombre = "Llega archivo a B";
        if(temp < maqB.getTime()){
            temp = maqB.getTime();
        }
        
        maqC.numEvent = 3;
        maqC.time = timeFileC();
        maqC.nombre = "Llega archivo a C";
        if(temp < maqC.getTime()){
            temp = maqC.getTime();
        }
        
        //se agregan eventos a la cola
        events.add(maqA);
        events.add(maqB );
        events.add(maqC);
        
        double rand = Math.random()*100;
        //System.out.println("Intervalo generado: " + rand);
        if(rand > 0 && rand < 32){
            Event rcvTkA = new Event();
            rcvTkA.nombre = "Recibe token A";
            rcvTkA.time = temp + 1; 
            rcvTkA.numEvent = 4;
            events.add(rcvTkA);
          //          System.out.println(rcvTkA.nombre);
          //           System.out.println(rcvTkA.time);
        }
        else{
            if(rand > 33 && rand < 65){
                Event rcvTkB = new Event();
                rcvTkB.nombre = "Recibe token B";
                rcvTkB.time = temp + 1; 
                rcvTkB.numEvent = 5;
                events.add(rcvTkB);
                      //System.out.println(rcvTkB.nombre);
                     //System.out.println(rcvTkB.time);
            }
            else{
                Event rcvTkC = new Event();
                rcvTkC.nombre = "Recibe token C";
                rcvTkC.time = temp + 1; 
                rcvTkC.numEvent = 6;
                events.add(rcvTkC);
                    //System.out.println(rcvTkC.nombre);
                     //System.out.println(rcvTkC.time);
            }
        }
    }
    
    //tiempos de llegadas de archivos a las maquinas
    public double timeFileA(){
        double temp = Math.random();
        while(temp == 1){
            temp = Math.random();
        }
        return ( (-1*(Math.log(1 - temp)))/4 );
    } 
    
    public double timeFileB(){
        return (randomWithRange(8, 12)/40);//(Math.sqrt( 80 * (Math.random() + 64) ) + clock);//(randomWithRange(8, 12)/40);
    }  
    
    public double timeFileC(){
        double addedValues = 0;
        for(int i = 0; i <= 12; i++){
            addedValues += Math.random();
        }
        return ( 0.1*(addedValues-6)+5);
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
     return (int)randomWithRange(1, 64);   
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
        
        maqA.time = timeFileA() + clock;            // Se calcula el tiempo siguiente cuando se va a dar otra llegada
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
        
        maqB.time = timeFileB()+ clock;
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
        
        maqC.time = timeFileC() + clock;
        events.add(maqC);
    }
    
        
    public void receivesTokenA(){
        Event recA = events.poll();                                             // Copia del evento actual
        clock = recA.getTime();
        recA.numEvent = 4;
        setTokenTime();
        filesSend = 0;
        boolean availableSend = false;
        int i = 0;
        
        int queueSize;
        queueSize = priorityFileA1.size() + priorityFileA2.size();      //guarda el tamano de la cola 
        queueSizeA.add(queueSize);

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
            LASA.nombre = "Llega archivo a antivirus";
            events.add(LASA);
            serverFiles.add(A);                                                 // Se agrega a la cola del servidor de antivirus
            
            Event TPLAMA = new Event();                                         // Evento termina poner en linea archivo maquina A
            TPLAMA.numEvent = 7;
            TPLAMA.time = clock + (A.size * 1/2);
            TPLAMA.nombre = "Termina poner en linea maq A";
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
                LASA.nombre = "Llega archivo a antivirus";
                events.add(LASA);
                serverFiles.add(A);
                
                Event TPLAMA = new Event();                                         // Evento termina poner en linea archivo maquina A
                TPLAMA.numEvent = 7;
                TPLAMA.time = clock + (A.size * 1/2);
                TPLAMA.nombre = "Termina poner en linea maq A";
                events.add(TPLAMA);
                tokenTime -= (A.size * 1/2);
                ++filesSend;
                
                recA.time = 1000000;
                events.add(recA);
            }else{                
                recA.time = 1000000;
                events.add(recA);
                
                Event recB = new Event();
                recB.numEvent = 5;
                recB.time = clock;
                recB.nombre = "Recibe Token Maquina B";
                events.add(recB);
                
                // guardar la cantidad de archivos enviados
                        
            }
        }
        filesSentByToken.add(filesSend);
    }
    
    public void receivesTokenB(){
        Event recB = events.poll();
        clock = recB.getTime();
        setTokenTime();
        filesSend = 0;
        boolean availableSend = false;
        int i = 0;
        
        int queueSize;
        queueSize = priorityFileB1.size() + priorityFileB2.size();
        queueSizeB.add(queueSize);

        while(!availableSend && i < priorityFileB1.size()){     //itera sobre la cola 1 de archivos para ver si puede mandar alguno 
                    //System.out.println("Estoy en el while ");
                    //System.out.println("tamano de archivo " + priorityFileB1.get(i).size);
                     //System.out.println("token: " + tokenTime);
            if( (priorityFileB1.get(i).size * 1/2) <= tokenTime ){
                       //     System.out.println("mando prioridad 1");
                availableSend = true;
            }else{
                ++i;
            }
        }
        
        if(availableSend){
            File B = priorityFileB1.get(i);
            priorityFileB1.remove(i);
            
            Event LASA = new Event();                                           // Nuevo evento para este unico archivo
                            //System.out.println("LASA PUESTO EN COLA-----------------------------");
            LASA.numEvent = 10;
            LASA.time = clock + (B.size * 1/2) + (1/4);                         // Tiempo de llegada al servidor de antivirus
//                                        System.out.println("LASA TIME: " + LASA.getTime());
            LASA.nombre = "Llega archivo a antivirus";
            events.add(LASA);
            serverFiles.add(B); 
            
            Event TPLAMB = new Event();                                         // Evento termina poner en linea archivo maquina B
            TPLAMB.numEvent = 8;
            TPLAMB.time = clock + (B.size * 1/2);
            TPLAMB.nombre = "Termina poner en linea maq B";
            events.add(TPLAMB);
            tokenTime -= (B.size * 1/2);
            ++filesSend;
            
            recB.time = 1000000;                                                // Se vuelve a agregar el evento con tiempo infinito
            events.add(recB);
        }else{
            int j = 0;
            while(!availableSend && j < priorityFileB2.size()){
                if( (priorityFileB2.get(j).size * 1/2) <= tokenTime ){
                           //                     System.out.println("mando prioridad 2");
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
                LASA.nombre = "Llega archivo a antivirus";
                events.add(LASA);
                serverFiles.add(B); 

                Event TPLAMB = new Event();                                         // Evento termina poner en linea archivo maquina B
                TPLAMB.numEvent = 8;
                TPLAMB.time = clock + (B.size * 1/2);
                TPLAMB.nombre = "Termina poner en linea maq B";
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
                recC.nombre = "Recibe Token Maquina C";
                events.add(recC);
            }
        }
        filesSentByToken.add(filesSend);
    }
    
    public void receivesTokenC(){
        Event recC = events.poll();
        clock = recC.getTime();
        recC.numEvent = 6;
        setTokenTime();
        filesSend = 0;
        boolean availableSend = false;
        int i = 0;
        
        int queueSize;
        queueSize = priorityFileC1.size() + priorityFileC2.size();
        queueSizeC.add(queueSize);
        
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
            LASA.nombre = "Llega archivo a antivirus";
            events.add(LASA);
            serverFiles.add(C); 
            
            Event TPLAMC = new Event();                                         // Evento termina poner en linea archivo maquina C
            TPLAMC.numEvent = 9;
            TPLAMC.time = clock + (C.size * 1/2);
            TPLAMC.nombre = "Termina poner en linea maq C";
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
                LASA.nombre = "Llega archivo a antivirus";
                events.add(LASA);
                serverFiles.add(C); 

                Event TPLAMC = new Event();                                         // Evento termina poner en linea archivo maquina C
                TPLAMC.numEvent = 9;
                TPLAMC.time = clock + (C.size * 1/2);
                TPLAMC.nombre = "Termina poner en linea maq C";

                events.add(TPLAMC);
                tokenTime -= (C.size * 1/2);
                ++filesSend;

                recC.time = 1000000;                                               // Se vuelve a agregar el evento con tiempo infinito
                events.add(recC);
            }else{
                recC.time = 1000000;
                events.add(recC);
                
                Event recA = new Event();
                recA.numEvent = 4;
                recA.time = clock;
                recA.nombre = "Recibe Token Maquina A";
                events.add(recA);
            }
        }
        filesSentByToken.add(filesSend);
    }
    
    public void tplama(){                                                       // Metodo que termina de poner en linea el archivo de la maquina A
        Event tplama = events.poll();
        tplama.numEvent = 7;
        clock = tplama.getTime();
        
        //System.out.println("Tiempo de token: "+tokenTime);
        
        if(tokenTime <= 0){
            tplama.time = 1000000;
          //  System.out.println("Entra aqui ****************************************************************************************************");
            //almacenar cantidad de archivos enviados
            Event recB = new Event();
            recB.numEvent = 5;
            recB.time = clock;     
            recB.nombre = "Recibe Token Maquina B";
            events.add(recB);
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
                LASA.nombre = "Llega archivo a antivirus";
                events.add(LASA);
                serverFiles.add(A);                                                 // Se agrega a la cola del servidor de antivirus

                Event TPLAMA = new Event();                                         // Evento termina poner en linea archivo maquina A
                TPLAMA.numEvent = 7;
                TPLAMA.time = clock + (A.size * 1/2);
                TPLAMA.nombre = "Termina poner en linea maq A";

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
                    LASA.nombre = "Llega archivo a antivirus";
                    events.add(LASA);
                    serverFiles.add(A);

                    Event TPLAMA = new Event();                                         // Evento termina poner en linea archivo maquina A
                    TPLAMA.numEvent = 7;
                    TPLAMA.time = clock + (A.size * 1/2);
                    TPLAMA.nombre = "Termina poner en linea maq A";

                    events.add(TPLAMA);
                    tokenTime -= (A.size * 1/2);
                    ++filesSend;
                }else{                
                    
                    tplama.time = 1000000;
                    events.add(tplama);
                    
                    Event recB = new Event();
                    recB.numEvent = 2;
                    recB.time = clock;
                    recB.nombre = "Recibe Token Maquina B";
                    events.add(recB);

                    // guardar la cantidad de archivos enviados                        
                }                
            }
        }
        filesSentByToken.add(filesSend);
    }
    
    public void tplamb(){                                                       // Metodo que termina de poner en linea el archivo de la maquina B
        Event tplamb = events.poll();
        tplamb.numEvent = 8;
        clock = tplamb.getTime();
        //System.out.println("Tiempo de token: "+tokenTime);
        if(tokenTime <= 0){
            tplamb.time = 1000000;
            //almacenar cantidad de archivos enviados
            //System.out.println("Entra aqui ****************************************************************************************************");
            Event recC = new Event();
            recC.numEvent = 6;
            recC.time = clock;       
            recC.nombre = "Recibe Token Maquina C";
            events.add(recC);
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
                LASA.nombre = "Llega archivo a antivirus";
                events.add(LASA);
                serverFiles.add(B);                                                 // Se agrega a la cola del servidor de antivirus

                Event TPLAMB = new Event();                                         // Evento termina poner en linea archivo maquina A
                TPLAMB.numEvent = 8;
                TPLAMB.time = clock + (B.size * 1/2);
                TPLAMB.nombre = "Termina poner en linea maq B";

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
                    LASA.nombre = "Llega archivo a antivirus";
                    events.add(LASA);
                    serverFiles.add(B);

                    Event TPLAMB = new Event();                                         // Evento termina poner en linea archivo maquina A
                    TPLAMB.numEvent = 8;
                    TPLAMB.time = clock + (B.size * 1/2); //////
                    TPLAMB.nombre = "Termina poner en linea maq B";

                    events.add(TPLAMB);
                    tokenTime -= (B.size * 1/2);
                    ++filesSend;
                }else{                
                    
                    tplamb.time = 1000000;
                    events.add(tplamb);
                    
                    Event recC = new Event();
                    recC.numEvent = 6;
                    recC.time = clock;
                    recC.nombre = "Recibe Token Maquin C";
                    events.add(recC);

                    // guardar la cantidad de archivos enviados                        
                }                
            }
        }       
        filesSentByToken.add(filesSend);
    }
    
    public void tplamc(){
        Event tplamc = events.poll();
        tplamc.numEvent = 9;
        clock = tplamc.getTime();
        //System.out.println("Tiempo de token: "+tokenTime);
        if(tokenTime <= 0){
            tplamc.time = 1000000;
            //almacenar cantidad de archivos enviados
            //System.out.println("Entra aqui ****************************************************************************************************");
            Event recA = new Event();
            recA.numEvent = 4;
            recA.time = clock;   
            recA.nombre = "Recibe token maquina A";
            events.add(recA);
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
                LASA.nombre = "Llega archivo a antivirus";
                events.add(LASA);
                serverFiles.add(C);                                                 // Se agrega a la cola del servidor de antivirus

                Event TPLAMC = new Event();                                         // Evento termina poner en linea archivo maquina A
                TPLAMC.numEvent = 9;
                TPLAMC.time = clock + (C.size * 1/2);
                TPLAMC.nombre = "Termina poner en linea maq C";

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
                    LASA.nombre = "Llega archivo antivirus";
                    events.add(LASA);
                    serverFiles.add(C);

                    Event TPLAMC = new Event();                                         // Evento termina poner en linea archivo maquina A
                    TPLAMC.numEvent = 9;
                    TPLAMC.time = clock + (C.size * 1/2); //////
                    TPLAMC.nombre = "Termina poner en linea maq C";

                    events.add(TPLAMC);
                    tokenTime -= (C.size * 1/2);
                    ++filesSend;
                }else{                
                    
                    tplamc.time = 1000000;
                    events.add(tplamc);
                    
                    Event recA = new Event();
                    recA.numEvent = 4;
                    recA.time = clock;
                    recA.nombre = "Recibe token maquina A";
                    events.add(recA);                     
                }                
            }
        }
        filesSentByToken.add(filesSend);
    }
    
    public void lasa(){                                                         // Llega archivo a servidor de antivirus
        Event lasa = events.poll();
        lasa.numEvent = 10;
        clock = lasa.getTime();
        queueSizeServer.add(serverFiles.size());                                     //guarda el tamano de la cola 
        int passes = 0;                                                         //cantidad de veces que revisa un archivo por virus
        
        if(antivirusAvailable && serverFiles.size() != 0){
            File temp = serverFiles.get(0);
            serverFiles.remove(0);
            antivirusAvailable = false;

            int quantityVirus = (int)randomWithRange(0,3); 
            
            if(quantityVirus == 0){
                Event sla = new Event();
                sla.numEvent = 11;
                sla.time = clock + (temp.size / 8);
                sla.nombre = "SLA";
                events.add(sla);
                
                lasa.time = 1000000;
                events.add(lasa);
                
                Event laar = new Event();
                laar.numEvent = 12;
                laar.time = clock + (temp.size / 8);
                laar.nombre = "LAAR";
                events.add(laar);
                passes++;
                // almacenar revisiones
            }
            if(quantityVirus == 1){
                Event sla = new Event();
                sla.numEvent = 11;
                sla.time = clock + (3 * temp.size / 16);
                sla.nombre = "SLA";
                events.add(sla);
                
                lasa.time = 1000000;
                events.add(lasa);
                
                Event laar = new Event();
                laar.numEvent = 12;
                laar.time = clock + (3 * temp.size / 16);
                laar.nombre = "LAAR";
               
                events.add(laar);
                passes++;
            }
            if(quantityVirus == 2){
                Event sla = new Event();
                sla.numEvent = 11;
                sla.time = clock + (11 * temp.size / 48);
                sla.nombre = "SLA";
                events.add(sla);
                
                lasa.time = 1000000;
                events.add(lasa);
                
                Event laar = new Event();
                laar.numEvent = 12;
                laar.time = clock + (11 * temp.size / 48);
                laar.nombre = "LAAR";
                events.add(laar);
                passes++;
            }
            if(quantityVirus == 3){
                Event sla = new Event();
                sla.numEvent = 11;
                sla.time = clock + (11 * temp.size / 48);
                sla.nombre = "SLA";
                events.add(sla);
                
                lasa.time = 1000000;
                events.add(lasa);                
                passes++;
            }
        }else{
            lasa.time = 1000000;
            events.add(lasa);
        }
        virusCheck.add(passes);
    }
    
    public void sla(){                                                          // Se libera el antivirus
        Event sla = events.poll();
        sla.numEvent = 11;
        clock = sla.getTime();
        
        antivirusAvailable = true;
        if(serverFiles.size() != 0){
            antivirusAvailable = false;
            File temp = serverFiles.get(0);            
            int quantityVirus = (int)randomWithRange(0,3);
            
            if(quantityVirus == 0){                
                sla.numEvent = 11;
                sla.time = clock + (temp.size / 8);
                events.add(sla);
             
                Event laar = new Event();
                laar.numEvent = 12;
                laar.time = clock + (temp.size / 8);
                laar.nombre = "LAAR";
                events.add(laar);                
                routerFiles.add(temp);
                // almacenar revisiones
            }
            
            if(quantityVirus == 1){                
                sla.numEvent = 11;
                sla.time = clock + (3 * temp.size / 16);
                events.add(sla);
                
                Event laar = new Event();
                laar.numEvent = 12;
                laar.time = clock + (3 * temp.size / 16);
                laar.nombre = "LAAR";
                events.add(laar);                
                routerFiles.add(temp);
                // almacenar revisiones                
            }
            
            if(quantityVirus == 2){                
                sla.numEvent = 11;
                sla.time = clock + (11 * temp.size / 48);
                events.add(sla);
                
                Event laar = new Event();
                laar.numEvent = 12;
                laar.time = clock + (11 * temp.size / 48);
                laar.nombre = "LAAR";
                events.add(laar);                
                routerFiles.add(temp);
                // almacenar revisiones
            }
            
            if(quantityVirus == 3){
                sla.numEvent = 11;
                sla.time = clock + (11 * temp.size / 48);
                events.add(sla);
                // almacenar revisiones
            }
        }else{
            sla.time = 1000000;
            events.add(sla);
        }
    }
    
    public void laar(){                                                         // Llegada de archivo a router
        Event laar = events.poll();
        laar.numEvent = 12;
        clock = laar.getTime();
        
        if(transmisionLine1 && routerFiles.size() != 0){
            transmisionLine1 = false;
            
            File temp = routerFiles.get(0);
            routerFiles.remove(0);
            
            Event srlt1 = new Event();
            srlt1.numEvent = 13;
            srlt1.time = (temp.size / 64) + clock;
            srlt1.nombre = "Sale por linea 1";
            events.add(srlt1);
            
            laar.time = 1000000;
            events.add(laar);
            
        }else{
            if(transmisionLine2 && routerFiles.size() != 0){
                transmisionLine2 = false;
                
                File temp = routerFiles.get(0);
                
                Event srlt2 = new Event();
                srlt2.numEvent = 14;
                srlt2.time = (temp.size /64) + clock;  
                srlt2.nombre = "Sale por linea 2";
                events.add(srlt2);
                
                laar.time = 1000000;
                events.add(laar);
            }else{
                laar.time = 1000000;
                events.add(laar);
            }
        }
    }
    
    public void srlt1(){                                                        // Salida de router por linea de transmision 1
        Event srlt1 = events.poll();
        srlt1.numEvent = 13;
        clock = srlt1.time;
        transmisionLine1 = true;
        if(routerFiles.size() != 0){
            File temp = routerFiles.get(0);
            routerFiles.remove(0);
            generalFileTime.add(clock - temp.systemTime);   //agrega tiempo de archivo en el sistema 
            if(temp.priority == 1){
                System.out.println("entroooooooooooo 1");
                fileTime1.add(clock - temp.systemTime);
            }
            else{
                                                System.out.println("entroooooooooooo ");
                fileTime2.add(clock - temp.systemTime);
            }
            srlt1.time = clock + (temp.size / 64);
            events.add(srlt1);
        }else{
            srlt1.time = 1000000;
            events.add(srlt1);
        }   
        //System.out.println("Salio del router 1");
    }
    
    public void srlt2(){                                                        // Salida de router por linea de transmision 1
        Event srlt2 = events.poll();
        srlt2.numEvent = 14;
        clock = srlt2.getTime();
        transmisionLine1 = true;
        
        if(routerFiles.size() != 0){
            File temp = routerFiles.get(0);
            routerFiles.remove(0);
            generalFileTime.add(clock - temp.systemTime);   //agrega tiempo de archivo en el sistema
            if(temp.priority == 1){
                fileTime1.add(clock - temp.systemTime);
            }
            else{
                fileTime2.add(clock - temp.systemTime);
            }
            srlt2.time = clock + (temp.size / 64);
            events.add(srlt2);
        }else{
            srlt2.time = 1000000;
            events.add(srlt2);
        }        
        //System.out.println("Salio del router 2");
    }  
}
