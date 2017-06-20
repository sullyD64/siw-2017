Progetto di esame siw giugno 2017: ThreeFourFunSite [Maria Elena Madon, Lorenzo Guidaldi]

Il progetto realizza un SIW per la gestione di una federazione amatoriale di triathlon di livello nazionale, ThreeFourFun.
Il triathlon è una disciplina olimpica in cui gli atleti svolgono in immediata successione tre prove: nuoto, ciclismo e corsa. Le prove, seppur basate su distanze diverse a seconda della specialità di gara, sono comuni a tutti gli atleti; Gli atleti partecipanti vengono premiati per il tempo complessivo impiegato, sia tramite raggruppamento assoluto che per categoria di età. L'età minima per partecipare è 14 anni, uomini e donne sono parimenti ammessi alle gare.

Le specifiche seguite per lo sviluppo del SIW sono in linea con quelle per il progetto AVANZATO per l'appello di Giugno 2017;
Le tecnologie implementate sono:
  - Spring MVC
  - Spring Boot
  - Spring Security
  - JPA (Hibernate)
  - Thymeleaf
  - Bootstrap

Informazioni tecniche:
  Il sistema non consente di gestire autonomamente tutti i casi d'uso: in particolare, per creare degli amministratori è necessario un intervento SQL da applicazioni esterne di gestione di database; nel nostro caso da pgAdmin va modificata la colonna ruolo dell'utente che si vuole rendere amministratore sostituendo ROLE_UTENTE con ROLE_ADMIN.
  A tale proposito si è mostrato utile, in fase di produzione, anche editare da pgAdmin la data di svolgimento delle gare che si volevano rendere "passate" e quindi renderle "da aggiornare" (il sistema da noi realizzato non permette infatti la registrazione di gare nel passato).
  
Informazioni e regole di dominio:
  Le entità principali del dominio sono gli Atleti, le Società e le Gare.
  Gli utenti che si registrano al sistema corrispondono agli atleti del dominio: ogni utente infatti gestisce un profilo atleta che lo rappresenta nel mondo reale. Gli atleti sono categorizzati per età e sesso secondo le normative federali della FITRI (http://www.fitri.it/il-triathlon/categorie.html)
  Una società non è altro che un gruppo di atleti, e viene creata e gestita da un atleta che assume il ruolo di Presidente di società.
  Una gara rappresenta un evento sportivo nel mondo reale: l'atleta può iscriversi alla gara e consultare, superata la data di svolgimento della gara stessa, i suoi risultati di cronometraggio relativi a tale gara.
  Quando una gara viene effettuata, ne viene creata una classifica a partire dai tempi totali di tutti gli atleti partecipanti.
  
  Ogni atleta è associato ad una sola società; questo inoltre costituisce un requisito nel momento in cui l'atleta decide di iscriversi ad una gara, in quanto non sono ammessi alla gara atleti che non facciano parte di alcuna società.
  
  Le gare e i risultati delle gare sono registrati da un particolare utente del sistema a cui sono garantiti i permessi di amministrazione. L'amministratore è di fatto un utente che tuttavia non può registrare un profilo atleta.
  
  Osservazioni: Non sono considerati costi d’iscrizione né pagamenti in alcuna forma né si fa mai riferimento a regolamenti federali e  certificati medico-sportivi. Non sono considerati altri ruoli in una società al di fuori del presidente di società e degli atleti; di   fatto il presidente e un atleta della stessa società sono la stessa persona, con lo stesso profilo sul sistema.
 

Di seguito i casi d'uso:

UC1: Registrazione utente
Attore primario: utente non registrato

  Scenario principale di successo:
  1. L'utente apre la pagina di login/registrazione, oppure vi viene reindirizzato se prova ad eseguire una qualsiasi operazione che richieda l'autorizzazione;
  2. L'utente compila i campi di registrazione fornendo uno username e una password;
  3. Il sistema registra l'utente e gli assegna il ruolo di UTENTE;
  
UC2: Registrazione profilo atleta
Attore primario: utente registrato

  Scenario principale di successo:
  1. L'utente esegue il login inserendo username e password;
  2. L'utente accede alla pagina "Registra il tuo profilo atleta";
  3. L'utente compila tutti i campi obbligatori ad eccetto del campo SOCIETÀ (che mostra un elenco delle società registrate nel sistema), che può essere lasciato vuoto;
    3.1: se lasciato vuoto, il campo società potrà successivamente essere compilato dall'Area Personale;
  4. Il sistema calcola l'età dell'atleta e la categoria a partire dalla sua data di nascita.
  5. Il sistema registra l'atleta e collega l'atleta all'utente, il sistema registra anche l'eventuale partecipazione dell'atleta nella società: l'atleta comparirà nell'elenco degli atleti della società;
  Da questo momento in poi l'utente non potrà più registrare altri profili atleta.
 
UC3: Registrazione società
Attore primario: utente registrato con profilo atleta registrato

  Scenario principale di successo:
  1. L'utente esegue il login;
  2. L'utente accede alla pagina "Registra il tuo profilo società";
  3. L'utente compila tutti i campi della società;
  4. Il sistema registra la società che da questo momento in poi comparirà nell'elenco delle società registrate al sistema; il sistema collega la società all'utente.
  Da questo momento in poi l'utente non potrà più registrare altri profili atleta.
  
 
UC4: Registrazione gara
Attore primario: amministratore

  Scenario principale di successo:
  1. L'amministratore esegue il login;
  2. L'amministratore accede alla pagina "Registra una nuova gara";
  3. L'amministratore compila tutti i campi della gara specificando una data futura;
  4. Il sistema registra la gara, che da questo momento in poi comparirà nell'elenco delle gare registrate al sistema alle quali è possibile iscriversi;
  
UC5: Iscrizione alla gara
Attore primario: utente registrato con profilo atleta iscritto ad una società

  Scenario principale di successo:
  1. L'utente esegue il login;
  2. L'utente accede alla pagina "Visualizza tutte le gare";
  3. Il sistema mostra l'elenco delle gare disponibili (con data di svolgimento posteriore a quella odierna), affianco a ciascuna delle quali è presente un bottone per prenotarsi;
  Il sistema mostra inoltre anche l'elenco delle gare passate (con data di svolgimento anteriore a quella odierna);
  4. L'utente preme il bottone "iscriviti" relativo ad una delle gare;
  5. Il sistema registra un'istanza di Risultato collegata all'atleta e alla gara; l'utente/atleta da questo momento è iscritto alla gara, e la gara comparirà nella sua "Area riservata" tra le gare a cui è iscritto.
 
UC6: Inserimento dei risultati di una gara
Attore primario: amministratore

  Scenario principale di successo:
  1. L'amministratore esegue il login;
  2. L'amministratore accede alla pagina "Aggiorna le classifiche";
  3. Il sistema mostra una lista di gare "da aggiornare", contenente le gare che sono state svolte per le quali ancora non sono presenti classifiche;
  4. L'amministratore clicca su una delle gare;
  5. Il sistema mostra un editor di classifiche: viene visualizzata una riga di input per ciascun atleta che si è iscritto alla gara;
  6. L'amministratore compila i campi inserendo i tempi delle frazioni di un atleta e poi clicca il bottone "Registra" relativo a tale atleta per registrarne il risultato.
  7. Il sistema registra il risultato relativo a quell'alteta e congela quell'input. Tuttavia non verrà ancora mostrato nel profilo dell'alteta, poichè la gara non è stata completamente aggiornata;
    7.1: L'amministratore può editare tale risultato in caso di errori, cliccando sul botone "Modifica" infatti tale risultato viene sbloccato e torna modificabile.
 - L'amministratore ripete i passi 6-7 finchè non ha registrato tutti i risultati di tutti gli atleti -
 8. L'amministratore clicca su "Conferma";
    8.1: L'amministratore può anche azzerare i risultati di ciascun atleta relativi a quella gara cliccando sul bottone "Reset". Inoltre, i dati inseriti permangono nella sessione permettendo all'amministratore loggato di navigare il sito pur mantenendo i risultati atleta già registrati.
 9. Il sistema registra la gara aggiornata e "conclusa", dalla quale sarà possibile estrapolare una classifica basata sul miglior tempo assoluto tra gli atleti partecipanti.
 
 UC7: Consultazione delle classifiche
 Attore primario: qualsiasi visitatore del sito
 
  Scenario principale di successo
  1. Il visitatore accede alla pagina "Visualizza le classifiche" e clicca su una tra le gare presenti dalla lista delle gare concluse.
      1.1: alternativamente, può raggiungere lo stesso percorso anche cliccando su una gara conclusa dalla pagina "Visualizza le gare", presente nella tabella di gare effettuate e concluse.
  2. Il sistema mostra la classifica con i risultati di tutti gli atleti partecipanti alla gara, ordinata per tempo. È possibile ordinare le linee a seconda dei migliori tempi delle singole frazioni (nuoto/bici/corsa).
  
  UC8: Consultazione dei dati personali o delle società, gare e atleti del sistema
  Attore primario: un utente registrato
  
  Un utente registrato e loggato può accedere alla pagina "Visualizza il riepilogo delle mie informazioni", anche detta "area riservata". Qui vengono mostrati i dati che l'utente ha inserito nel sistema: il suo profilo atleta, la sua (eventuale) società da lui fondata con la lista degli atleti iscritti, la lista delle gare a cui ha partecipato con i suoi risultati e la lista delle gare a cui è iscritto che devono ancora svolgersi.
  Se l'utente non ha registrato un profilo atleta, non ha registrato una società, oppure il profilo atleta non è collegato ad una società, il sistema mostra dei link e delle form per compilare i campi rimanenti.
  
  
