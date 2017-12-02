# tikape-runko

Tarkastuslista palautettavaan dokumentaatioon:
•	Voiko sovelluksessa mennä katsomaan listausta ainesosista (tai vastaavasta)? 
o	Kyllä, painamalla pääsivun linkistä ”muokkaa ainesosia”
•	Voiko sovelluksessa mennä katsomaan listausta annoksista (tai vastaavasta)?
o	Kyllä. Se on näkyvissä pääsivulla ja myös juomien lisäyssivulla. 
•	Voiko ainesosia lisätä?
o	Kyllä, menemällä pääsivun ”muokkaa ainesosia” linkistä oikealle sivulle ja kirjoittamalla lisättävän ainesosan nimi kenttään.
•	Voiko ainesosia poistaa?
o	Kyllä, menemällä pääsivun ”muokkaa ainesosia” linkistä oikealle sivulle ja painamalla ”poista” nappia poistettavan ainesosan kohdalla.
•	Voiko annoksia lisätä?
o	Voi. Annokset lisätään ”/drinks” sivulla, jossa voi ensin luoda uuden annoksen ja sitten lisätä sille ohjeen vaihe vaiheelta. 
•	Voiko annoksia poistaa?
o	Voi. /Drinks-sivulla on annosten listauksen ohessa nappi, jota painamalla koko juoma poistuu.
•	Voiko annokseen lisätä useita ainesosia? Voiko saman ainesosan lisätä useampaan annokseen?
o	Useita ainesosia voi lisätä. Samaa ainesosaa ei kuitenkaan voi lisätä kahta kertaa samaan annokseen. 
•	Voiko annoksen reseptiä (=ainesosia) katsoa?
o	Kyllä. Tämä tapahtuu klikkaamalla kyseisen annoksen nimessä olevaa linkkiä.
•	Näkyvätkö reseptin vaiheet oikeassa järjestyksessä?
o	Tässä on pieni bugi: Reseptin vaiheet näkyvät oikeassa järjestyksessä, jos ne lisätään oikeassa järjestyksessä. Henkilökohtaisesti en oikein tavoittanut tämän kohdan merkitystä ja se oli mielestäni hankala toteuttaa jotenkin järkevällä tavalla. Olisin mieluummin käyttänyt ordered list-tyyppistä listaa, jolloin numerojärjestys olisi väistämättä lisäämisjärjestyksen mukainen, eikä reseptiä kirjoittavan tarvitse pohtia lisättävän ainesosan järjestystä. Vaihdoin input typeksi numeron, ja rajoitin myös mahdollisia numeroita, ettei kenttään voisi lisätä mitä vaan.
•	Onko sovelluksella etusivu, joka on nähtävissä sovelluksen juuripolussa (“/”)?
o	On. Tällä sivulla on koottuina linkit muihin sovelluksen sivuihin. 


