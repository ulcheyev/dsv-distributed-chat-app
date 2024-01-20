# dsva-chat-leader-election
Semestral work from Distributed Computing subject
## What?

**EN version**

*Chat program* </br>
The semester project involves developing a distributed chat application for exchanging messages between nodes. The primary focus will be on addressing the Leader Election and Mutual Exclusion problems.

- **Problem type**: the semester project will address the Leader Election and Mutual Exclusion problems.
- **Implemented algorithms**: for Leader Election, the project will utilize the Chang-Roberts algorithm, providing a symmetric solution. To tackle the Mutual Exclusion problem, the Ricart-Agrawala algorithm will be implemented.
- **Programming language and message transport**: the semester project will be implemented in the Java programming language, using the gRPC framework for communication between nodes.
- **Functionality of the semester project**: the semester project will simulate a chat application in a distributed environment. System nodes will be able of sending/receiving messages, logging in/out of the system, exiting without logging out and joining room.

**CZ version**

*Chatovací program* </br>
Semestrální projekt zahrnuje vývoj distribuované chatovací aplikace pro výměnu zpráv mezi uzly. Primárně se zaměří na řešení problémů Volby Vůdce a Vzájemného Vyloučení.

- **Typ problému**: semestrální projekt se bude zabývat problémy volby vůdce a vzájemného vyloučení.
- **Využívané algoritmy**: pro řešení problému volby vůdce bude použit Chang-Roberts algoritmus, který poskytuje symetrické řešení. Pro řešení problému vzájemného vyloučení bude implementován algoritmus Ricart-Agrawala.
- **Programovací jazyk a přenos zpráv**: semestrální projekt bude implementována v programovacím jazyce Java s využitím frameworku gRPC pro komunikaci mezi uzly.
- **Funkčnost semestrálního projektu**: semestrální projekt bude simulovat chatovací aplikaci v distribuovaném prostředí. Uzly systému budou moci odesílat/přijímat zprávy, přihlašovat se/odhlašovat se ze systému, skončení bez odhlášení a připojovat se k místnosti.

## Why?

The semester project serves as a comprehensive learning opportunity, combining theoretical knowledge with practical implementation, and equipping us with the skills and insights necessary for the distributed systems and software development. :rage1:

## Key features

1. Supports dynamic creation and participation in rooms, fostering interactive discussions.
2. Chang-Roberts algorithm ensures a fair and reliable mechanism for electing a leader among system nodes.
3. Ricart-Agrawala algorithm for mutual exclusion guarantees that critical sections of code are executed by only one node at a time, preventing conflicts.
4. Maintains detailed logs for system activities, aiding in debugging and analysis.
5. Ensures the system's robustness and availability under varying conditions.

## Prerequisites
To run the distributed chat application you will need several prerequisites:
- JDK 17
- Maven
- gRPC framework
- Bash
- Internet connection

## Install
1. Clone this project;
2. Go to the /scripts directory;
3. Run script to start initial node in topology
```
./dsvRun [node name] [current node port] [current node IP]
```
4. Run script to start additional nodes in topology
```
./dsvRun [node name] [current node port] [current node IP] [the IP address of the node to connect to] [the port of the node to connect to]
```

## Usage
Type !help in node console for more information about supported commands.
