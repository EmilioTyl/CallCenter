# CallCenter

## Objetivo

Esta aplición Java modela un Call Center donde existen 3 tipos de empleados: operador, supervisor y director. Al ingresar una llamada, ésta es atendida por cualquier operador disponible. Si no hay nadie disponible, la llamada será atendidada por cualquier supervisor. Y por último si no hay uno disponible, la llamada será atendida por un director.

### Requerimientos base y extra implementados

* Se debe atender como máximo 10 llamadas de forma concurrente
* Toda llamada tiene duración entre 5 a 10 segundos
* De no haber empleado libre, la llamada queda en espera
* La llamada entrante queda en espera si en el momento de ser realizada se está atendiendo el máximo de 10 llamadas concurrentes

## Implementación y consideraciones

La clase CallDispatcher se encarga de asignar un empleado a una llamada, modelados por las clases CallEmployee y Call respectivamente.  CallDispatcher usa una PriorityBlockingQueue, que es una cola thread safe que se encarga de mantener el orden de prioiridad de los CallEmployee. 
El problema modela en sí el patrón de productor y multiple consumidores concurrentes, donde el productor genera llamadas y el consumidor se encarga de asignarlas a un empleado. Al ser las llamadas procesos concurrentes, la asignacion de una llamada a un empleado y su procesamiento se reaaliza en un thread particular. Como parte de los requerimientos, deben existir como máximo 10 llamadas en curso. Para eso se utilizó un pool trhead y una cola de espera, mediante la interface de ExecutorService. En consecuencia al recibir una llamada, el CallDispatcher crea una tarea (Runnable) que se encarga de conseguir un empleado disponible para atenderla. 

### Diagrama UML de clases

![alt DiagramaUML](https://raw.githubusercontent.com/EmilioTyl/CallCenter/master/uml-diagram.png)

## Build

Colocarse en la raiz del proyecto, y realizar los siguientes comandos:

```bash
 mvn clean package
```

## Demo interactiva

La demostración interactiva consiste en una aplicación de consola en el cual se crean 6 empleados con distintos roles. 
Para realizar llamadas, se debe escribir tantos caracteres 'c' como llamadas se quieran hacer. Luego se puede observar las llamadas creadas, qué empledo las va atendiendo y cuáles de éstas quedan en espera.
Escribiendo el caracter 'e' se finaliza el proceso.

```bash
  java -jar ./target/AlmundoCallCenter-FINAL.jar
```

## Test


```bash
 mvn test
```
* El primer Test modela un caso particular de funcionamiento del Call Center.
* El segundo Test realiza 20 llamados y verifica mediante el CallDispatcher que la cantidad de llamados simultáneos son 10.
* El tercer Test verifica que el CallDispatcher asigna al primer llamado a un operador, al segundo a un supervisor y al tercero a un director.
