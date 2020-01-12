# **Assignment 3 : BooksLoan**

*Ivo Junior Bettini - 806878, Umberto Cocca - 807191, Silvia Traversa - 816435*

# Breve descrizione dell'applicazione
L’applicazione BooksLoan permette agli utenti di visualizzare il catalogo di una biblioteca. 
Essi possono visualizzare le copie disponibili e non, ed eventualmente richiederne una in prestito, sia quelle non disponibili e prenotarle per quando ritorneranno presso la biblioteca. È inoltre possibile visualizzare le informazioni relative ad ogni singolo libro, quali l’autore o la presenza di sequel.
Gli amministratori, che si dividono in amministratori con contratto a tempo indeterminato e amministratori con contratto a tempo determinato, diﬀeriscono per la possibilità di eliminare i libri. Possono entrambi interagire con un’area CMS eseguendo il CRUD delle principali entità.

# Esecuzione dell'applicazione

Prima di eseguire l’applicazione, è necessario importare i dati e la struttura del database, creato con MySQL.
Per eﬀettuare questa operazione bisogna eﬀettuare su MySQLWorkbench un Data Import del ﬁle DumpFinale presente nella cartella dumps. 
Successivamente, per permettere la connessione al database, è richiesta la modiﬁca del ﬁle src/main/resources/application.properties
inserendo le proprie credenziali MySQL nei campi spring.datasource.username e spring.datasource.password.

Per far partire l’applicazione, dopo aver clonato il repository, eseguire le seguenti righe di comando:

- `./mvnw clean package spring-boot:repackage`

- `java -Djava.security.egd=file:/dev/./urandom -jar target/BooksLoan-1.jar`

Se dà problemi di accesso "Permission denied" su OSX eseguire prima:

- `chmod +x mvnw`

L’applicazione sarà disponibile all’indirizzo *http://localhost:8080*.

Per accedere alle funzionalità dell’applicazione è rischiesto un login, che è possibile eﬀettuare con tre tipi diversi di account, rappresentativi di categorie:<br />
- Amministratore con contratto a tempo indeterminato:
    * username : 123 
    * password : 1234
- Amministratore con contratto a tempo determinato:
    * username : 456 
    * password : 4567
- Cliente: 
    * username : 321 
    * password : 1234
