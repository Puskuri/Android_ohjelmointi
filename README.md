Sovelluksen toiminnallisuuden lyhyt kuvaus

Tässä sovelluksessa on tideostoina MainActivity, joka sisältää 2 nappia Start game nappi katoaa kun sitä painaa, game nappi aloittaa GameActivity luokan jossa korttipeli.
Sovelluksesta löytyy myös toolbar komponentti, joka sisältää toiminnallisuudet dashboard fragment, home fragment ja notification fragment. Home fragmentti on vain tavallinen aloitusnäkymä.
Dashboard fragmentti sisältää navigoinnin jonne ilmoitetaan koordinaatit leveys- ja pituusasteelta sekä osoitteen ja se näyttää kyseisen sijainnin kartalta. Notification fragmentti
sisältää sekunttikellon jonka voi pysäyttää, lopettaa ja aloittaa uudestaan.

Oheistiedostoina ApmReceiver joka ilmoittaa lentokonetilan (joko pois tai päällä). Toisena tiedostona on DataStoreHelper ja singleton jotka tallettavat GameActivityn pelin pisteet
laitteen muistiin. MainActivity2 on myös toiminnallisuus joka kysyy käyttäjältä lupaa sijainnin käyttöön.

