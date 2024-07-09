# Baze_podataka
Zadatak

Napraviti napredni, GUI-orijentisan alat koji izvršava SQL upit koji korisnik unese i rezultujući skup podataka prikazuje u tabeli. Neophodno je podržati EXEC, INSERT, UPDATE, DELETE; SELECT iskaze (sa spajanjima tabela i podupitima), kao i pravljenje uskladištenih procedura i funkcija. Alat ima nekoliko naprednih funkcija:

	1. (2) Bulk import - korisnik može da odabere CSV fajl i tabelu u koju će importovati podatke iz njega. U ovom slučaju, SQL se generiše u pozadini.
	2. (1) Result Set export - rezultujući skup podataka se eksportuje u CSV formatu na željenu destinaciju na računaru.
	3. (3) sql.pretty - formatiranje SQL upita tako da su ključne reči obojene i velikim slovima i da je svaki deo iskaza u zasebnom predu. Primer:
		
		SELECT column_name(s)
		FROM table1
		RIGHT JOIN table2
		ON table1.column_name = table2.column_name;


	4. (14 = 1 + 10 + 3) Query checker - provera sintakse i validnost upita:
*da li kolone i tabele koje su navedene postoje u bazi
*da li je kolona odabrana za spajanje zapravo strani ključ
	Select last_name, first_name, department_name from hr.employees e join hr.departments d on (e.department_name = d.department_name)
*da li postoje obavezni delovi upita
*da li je redosled iskaza ispravan
*da li aliasi imaju navodnike ako su više reči
*da li funkcije agregacije imaju GROUP BY ako im je potreban)
*da u WHERE iskazu nije funkcija agregacije
*da su istovetni tipovi podataka za promenljive i oni koji se selektuju u pravljenju procedure/funkcije
*da se sve definisane promenljive koriste u kodu procedure/funkcije
*da li je CSV fajl koji se učitava za Bulk Import u skladu sa strukturom izabrane tabele

Definisati konfiguracioni fajl u JSON formatu, koji sadrži ime pravila, opis greške i predlog za ispravku. Konfiguracioni fajl se učitava prilikom pokretanja aplikacije i koristi za formiranje stack_trace. Opis greške ne treba da bude uopšten već konkretan, npr.: “Kolona DEPARTMENT_NAME nije strani ključ za spajanje tabela EMPLOYEES i DEPARTMENTS”. Isto važi i za sugestiju, npr. “Pokušajte sa DEPARTMENT_ID”. Svaki upit se validira pre nego što se pošalje bazi na izvršavanje (šalje se samo ukoliko nema grešaka). Ako greške postoje, ispisuju se sve greške (stack) sa predlozima za korekcije. Jedini izuzetak može da bude poziv da se izvrši procedura/funkcija. 

Sve 4 stavke definisati kao komponente (imaju interfejs i implementaciju, deklarisanje izvoditi isključivo preko interfejsa). Koristiti Java SWING biblioteku. Bridge šablon je obavezujući, kao i MVC+O arhitektura. 

