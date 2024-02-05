# fabrick-test

## TODO
1. tutta la parte di Test
2. mancano alcune validazioni sui DTO di request che sono state segnate con il commento TODO
3. sviluppo della scrittura su DB e storicizzazione dei movimenti ottenuti con la lista delle transazioni.

## API esposte:

Swagger:
http://localhost:8080/swagger-ui/index.html#/fabrick-controller

- GET http://localhost:8080/swagger-ui/index.html#/fabrick-controller/letturaSaldo
  - INPUT:
    - accountId : 14537780
      
- GET http://localhost:8080/swagger-ui/index.html#/fabrick-controller/listaTransazioni
  - INPUT:
    - accountId : 14537780
    - fromAccountingDate : 2019-01-01
    - toAccountingDate : 2019-12-01

- POST http://localhost:8080/swagger-ui/index.html#/fabrick-controller/effettuaBonifico
  - INPUT:
    - accountId : 14537780
    - RequestBody :
	{
	  "creditor": {
	    "name": "John Doe",
	    "account": {
	      "accountCode": "IT23A0336844430152923804660",
	      "bicCode": "SELBIT2BXXX"
	    }
	  },
	  "executionDate": "2019-04-01",
	  "uri": "REMITTANCE_INFORMATION",
	  "description": "Payment invoice 75/2017",
	  "amount": 800,
	  "currency": "EUR",
	  "isUrgent": false,
	  "isInstant": false,
	  "feeType": "SHA",
	  "feeAccountId": "45685475",
	  "taxRelief": {
	    "taxReliefId": "L449",
	    "isCondoUpgrade": false,
	    "creditorFiscalCode": "56258745832",
	    "beneficiaryType": "NATURAL_PERSON",
	    "naturalPersonBeneficiary": {
	      "fiscalCode1": "MRLFNC81L04A859L"
	    }
	  }
	}


## Testo esercizio

Realizzare un controller Rest che permetta di gestire le seguenti operazioni sul conto:
- 	Lettura saldo;
- 	Lista di transazioni
- 	Bonifico;

L’applicativo dovrà essere sviluppato utilizzando le API esposte da Fabrick
https://developers.fabrick.com/hc/it

Per la fase di sviluppo e test sarà sufficiente l’utilizzo della versione SANDBOX della API.

Non è necessario lo sviluppo di un interfaccia utente;

Facoltativo:
Verrà valutato positivamente, ma non obbligatorio al fine del test, la scrittura su DB e storicizzazione dei movimenti ottenuti con la lista delle transazioni.

Credenziali e input
L’esercizio dovrà essere svolto usando l’ambiente di Sandbox con le seguenti credenziali:

- BaseUrl: 		https://sandbox.platfr.io
- Auth-Schema: 	        S2S
- Api-Key: 		FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP
- Id chiave: 		3202 (per uso interno, non utile al fine del test)
- accountId: 		14537780

Fare sempre riferimento alla documentazione delle API e popolare tutti i campi “required”
I parametri di “input” sono da intendersi parametri da utilizzare per il json di input/URI, in caso di mismatch di nomi o formati (che possono evolvere) fa fede la documentazione online di cui trovate link sulle singole chiamate 
Proprietà/Costanti applicativo
- {accountId} : Long, è il numero conto di riferimento, nelle API è sempre indicato come {accountId}, usare valore 14537780

### Operazione: Lettura saldo
	API: https://docs.fabrick.com/platform/apis/gbs-banking-account-cash-v4.0
- Input:
	- {accountId}:Long è un parametro dell’applicazione;
- Output:
	- Visualizzare il saldo

### Operazione: Bonifico
	API: https://docs.fabrick.com/platform/apis/gbs-banking-payments-moneyTransfers-v4.0
- Input (usare per valorizzare il json della chiamata):
	- {accountId}:Long è un parametro dell’applicazione;
	- {creditor.name}:String, è il beneficiario del bonifico;
	- {creditor.account.accountCode}:String, iban del beneficiario;
	- {description}: String, descrizione del bonifico
	- {currency}:String
	- {amount}:String 
	- {executionDate}:String YYYY-MM-DD
- Output:
	- Stato dell’operazione, il bonifico avrà esito KO per una limitazione del conto di prova. L’output atteso dovrà essere:
	- "code": "API000", "description": "Errore tecnico  La condizione BP049 non e' prevista per il conto id 14537780"

### Operazione: Lettura Transazioni
	API: https://docs.fabrick.com/platform/apis/gbs-banking-account-cash-v4.0
- Input:
	- {accountId}:Long è un parametro dell’applicazione;
	- {fromAccountingDate}=2019-01-01
	- {toAccountingDate}=2019-12-01
- Output:
	- Lista delle transazioni, nelle date suggerite su esempio sono presenti movimenti.

Tecnologie
Il test deve essere svolto in Java. Si richiede l’uso di Spring + Spring Boot.
Il risultato deve essere auto-contenuto (non dipendenze a db o sistemi esterni, si per db in memory)

Artefatto
Il codice sorgente dell’artefatto prodotto dovrà essere pubblicato e condiviso tramite:
- 	GitHub;
- 	Bitbucket;
- 	Altri strumenti similari di version control;

L’obiettivo è valutare la capacità di sviluppo software con particolare attenzione a:
		
- 	Struttura del codice;
- 	E’ mandatorio l’uso di un sistema di version control;
- 	L’artefatto deve esporre una serie di endpoint REST CRUD che, una volta invocati, consumino le API di Fabrick sopra descritte;
- 	Approccio al problema;
- 	Test automatizzati (assenza di unit test è fortemente penalizzante in fase di valutazione);
- 	Gestione errori;
- 	Corretto uso di logging




