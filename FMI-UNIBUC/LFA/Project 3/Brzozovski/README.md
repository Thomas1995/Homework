**Cum se ruleaza proiectul?**

Din folderul LFA-Laborator/Project 3/Brzozovski/ in care se afla Makefile-ul, se vor folosi urmatoarele comenzi pe linux:
```
  make
  make run
```

**Formatul datelor de intrare**

Datele de intrare se vor afla in NFA.data, fisier aflat in acelasi folder. Formatul acestora este:
```
  Numar_total_stari Marime_alfabet Stare_initiala
  Numar_stari_finale Stare_finala1 Stre_finala2 Stare_finala3 ...
  Stare1 Stare2 Litera_de_tranzitie_intre_stare1_si_stare2(in format numeric)
  ...
```

Exemplele de pe site si din laborator se pot gasi aici: [exemple]
[exemple]: <https://github.com/Thomas1995/LFA-Laborator/blob/master/Project%203/examples.txt>

**Formatul datelor de iesire**

Datele de iesire se vor afisa in consola, avand un format intuitiv. De exemplu:
```
  Numar stari: 5
  Alfabet: 0..1
  Stari initiale: 4 
  Stari finale: 3 1 
  0 -> 4 (a)
  0 -> 1 (b)
  1 -> 0 (b)
  2 -> 3 (a)
  2 -> 4 (b)
  3 -> 2 (a)
  4 -> 3 (a)
  4 -> 1 (b)
```

**Algoritmul folosit**

Am implementat algoritmul Brzozovski, prin acceptarea a mai multe stari initiale.
