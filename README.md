
<div align=center>

# Traffic Light Simulator

</div>

<p align=center>
<img src="./traffic_light.png" style="align: center; width: 300px">
</p>

#

<details>
<summary>German</summary>

###

Dieses Programm ist ein interaktiver Verkehrsmanagement-Simulator für die Konsole. Es nutzt **Multithreading**, um einen Echtzeit-Timer im Hintergrund laufen zu lassen, während der Benutzer über ein Menü Straßen hinzufügen oder entfernen kann. Das System berechnet dynamisch die Ampelphasen (Rot/Grün) und zeigt die verbleibende Zeit für jede Straße an, basierend auf einem benutzerdefinierten Intervall.

<div align=center>

## Zu der App
</div>

* ***Konsolen-Anwendung*** mit interaktivem Menü
* ***Multithreading:*** Ein Hintergrund-Thread (`QueueThread`) steuert die Logik und das
  Rendering, ohne die Benutzereingabe zu blockieren.
* ***Command Pattern:*** Saubere Trennung der Funktionen (Hinzufügen, Löschen, System-Status)
  durch gekapselte Befehlsklassen.
* ***Dynamische Berechnung:*** Die Wartezeiten für rote Ampeln werden mathematisch präzise
  auf Basis der aktuellen Position in der Warteschlange und der verbleibenden Zeit der
  grünen Phase berechnet.
* ***Thread-Sicherheit:*** von `AtomicInteger`, `volatile` und `CopyOnWriteArrayList`, um
  Datenkonflikte zwischen Logik-Thread und Benutzer-Thread zu vermeiden.

**Kernfunktionen:**

* Hinzufügen von Straßen bis zu einem definierten Limit.
* Löschen der ältesten Straße (FIFO-Prinzip) mit automatischer Korrektur der Ampelphasen.
* Live-Überwachung des Systems mit farblicher ANSI-Darstellung.
<br>

  ![Green Badge](https://img.shields.io/badge/OFFEN-228B22)
  ![Yellow Badge](https://img.shields.io/badge/ALS%20NÄCHSTES%20OFFEN-FFEA00)
  ![Red Badge](https://img.shields.io/badge/GESCHLOSSEN-D2042D)

<div align=center>

## Wie kann ich diese testen?
</div>

1.  Repository klonen:
    `git clone git@github.com:Dima0687/traffic-light-simulator-with-java.git`
2.  In einer IDE öffnen (z.B. IntelliJ IDEA).
3.  **Konfiguration:**
  Führe die `Main`-Klasse im Paket `de.eisner.traffic` aus. Beim Start wirst du
  aufgefordert, zwei Parameter einzugeben:
    * `Input the number of roads`: Das Maximum an Straßen, die gleichzeitig verwaltet werden 
    können.
    * `Input the interval`: Die Dauer (in Sekunden), für die eine Ampel grün bleibt.
4. **Bedienung:**
    * Wähle 1, um eine Straße hinzuzufügen.
    * Wähle 2, um die vorderste Straße zu löschen.
    * Wähle 3, um den System-Status zu öffnen. In diesem Modus siehst du die Live-Updates. 
    Drücke **Enter**, um zum Menü zurückzukehren.
    * Wähle 0, um das Programm sicher zu beenden.

<div align=center>

## Fazit

</div>

Die größte Herausforderung in diesem Projekt war das Zusammenspiel zwischen dem Hintergrund-Thread und dem Benutzer-Input. Besonders knifflig war die Logik beim Löschen einer Straße: Wenn die Liste nachrückt, verschieben sich die Indizes, was ohne Korrektur dazu geführt hätte, dass die "grüne Ampel" plötzlich an die falsche Straße springt.Ich habe viel über Thread-Sicherheit gelernt und wie man verhindert, dass ein Programm abstürzt, wenn zwei Threads gleichzeitig auf eine Liste zugreifen. Zudem war die mathematische Berechnung der Wartezeiten für die roten Ampeln ($waitTime = (dist - 1) * interval + remainingTime$) eine tolle Übung in Logik. Es ist faszinierend zu sehen, wie aus einfachen Zahlen eine funktionierende Simulation entsteht.

</br>
</br>
</details>

<details open>
<summary>English</summary>

This is an interactive traffic management simulator for the console. It utilizes multithreading to run a real-time timer in the background while the user manages roads via a menu. The system dynamically calculates traffic light phases (red/green) and displays the remaining time for each road based on a user-defined interval.

<div align=center>

## About the App
</div>

* ***Console application*** with an interactive menu.
* ***Multithreading:*** A background thread (`QueueThread`) manages logic and rendering without blocking user input.
* ***Command Pattern:*** Clean separation of functions (Add, Delete, System Status) through
 encapsulated command classes.
* ***Dynamic Calculation:*** Wait times for red lights are mathematically calculated based on the queue position and the remaining time of the current green phase.
* ***Thread Safety:*** Utilizes `AtomicInteger`, `volatile`, and `CopyOnWriteArrayList` to prevent data races between the logic thread and the user thread.

**Key Features:**
* Adding roads up to a pre-defined limit.
* Deleting the oldest road (FIFO) with automatic phase correction.
* Live system monitoring with ANSI color coding 
<br>

  ![Green Badge](https://img.shields.io/badge/OPEN-228B22)
  ![Yellow Badge](https://img.shields.io/badge/NEXT%20OPEN-FFEA00)
  ![Red Badge](https://img.shields.io/badge/CLOSED-D2042D)
  
<div align=center>

## How can I test this?
</div>

1. Clone the repository:
 `git clone git@github.com:Dima0687/traffic-light-simulator.git`
2. Open in an IDE (e.g., IntelliJ IDEA).
3. **Setup:**
  Run the `Main` class in the `de.eisner.traffic` package. Upon startup, you will be prompted for two parameters:
    * `Input the number of roads`: The maximum number of roads the system can handle.
    * `Input the interval`: The duration (in seconds) a light stays green.  
4. **Usage:**
    * Press 1 to add a road.
    * Press 2 to delete the oldest road.
    * Press 3 to open System Mode. Here you will see live updates. 
    Press **Enter** to return to the menu.
    * Press 0 to safely quit the program.
    
<div align=center>

## Conclusion
</div>

The most challenging part of this project was the synchronization between the background thread and user input. A particularly tricky aspect was the logic for deleting a road: as the list shifts, the indices change. Without careful correction, the "green light" would have jumped to the wrong road.I learned a lot about thread safety and how to prevent application crashes when two threads access a list simultaneously. Furthermore, deriving the mathematical formula for wait times ($waitTime = (dist - 1) * interval + remainingTime$) was a great exercise in logic. It is rewarding to see how simple numbers evolve into a functional simulation.

</br>
</br>
</details>