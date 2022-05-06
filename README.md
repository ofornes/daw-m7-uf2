# DAW - M7 - UF2 - Receptes

Proposta de treball per a la UF2 del mòdul 7

Continguts:

* [Pla de treball](#pla-de-treball)
* [Origen de les dades](#origen-de-les-dades)
* [Execució de l’aplicació](#execució-de-laplicació)
* [Anàlisi, descripcions i observacions](#anàlisi-descripcions-i-observacions)
  * [Configuració de la aplicació](#configuració-de-la-aplicació)
  * [Base de dades](#base-de-dades)
  * [MVC](#mvc)
  * [Json+LD](#jsonld)
  * [Altres elements](#altres-elements)
* [Pàgines](#pàgines)
* [Metadades](#metadades)

## Pla de treball

1. Obtenir informació sobre receptes, per a partir d’una base coneguda
2. Amb aquesta informació, definir un esquema SQL i generar les sentències DDL i SQL per a muntar la base de dades i nodrir-la amb la informació trobada
3. Cercar una plantilla bootstrap 4 específica de webs de receptes per adaptar-la als objectius
4. Muntar aplicació spring mvc  amb thymeleaf per a templating, amb base de dades encastada (per a facilitar les operacions i provatures)
5. Desenvolupar la part operativa sense la part de metadades:
   * Repositori SQL
   * Servei per a operacions del controlador
   * Beans de dades
   * Adaptació de la plantilla html
6. Un cop acabada la part operativa, implementar la part de metadades:
   * Per a la conversió a `json+ld` es descarta utilitzar llibreries de tercers, ja que no s’ha trobat cap amb la documentació suficient per a assumir aquest camí
   * Disseny i implementació de la classe per a generar dades en format `json+ld`
   * Implementació de dos mètodes al servei per a produir `Recipe` i `ItemList`, el primer amb el bean de recepta i el segon amb una llista de receptes
   * Encastament de les metadades a la pàgina amb elements `scripts`
7. Provatures finals i documentació

## Origen de les dades

S’ha fet una cerca per internet per tal de buscar pàgines de receptes amb metadades del tipus objectiu de la pràctica. El motiu és doble, per una banda disposar d’un «patró» per a verificar l’assoliment de l’objectiu i per l’altra, poder extreure la informació d’una manera senzilla per tal de generar els scripts SQL.

Desprès de molt remenar i cercar, he trobat una web amb receptes i que exposa les metadades requerides:

​		https://www.directoalpaladar.com

## Execució de l’aplicació

Anar a la release v0.0.1 i descarregar el paquet jar .

Executar des de línia de comandes amb:

```bash
java -jar receptes-0.0.1.jar
```

Ara podeu visitar la pàgina a http://localhost:8080

## Anàlisi, descripcions i observacions

L’aplicació s’ha fet amb el framework spring-boot.

S’ha utilitzat la capacitat d’execució autònoma, que no requereix de cap servidor d’aplicacions.

Com a eina de construcció s’ha utilitzat MAVEN.

### Configuració de la aplicació

Com és tradicional a Spring, l’aplicació utilitza el patró d’injecció de dependències, de manera que amb directrius de *conveni* i elements de *configuració* es resolen totes les dependències de cada classe: accés a base de dades, serveis, validació, etc.

La configuració és divideix en dos elements:

* [src/main/resources/application.yml](src/main/resources/application.yml) Arxiu de configuració d’Spring-Boot
* [cat.albirar.daw.receptes.ReceptesSaludablesConfiguration](src/main/java/cat/albirar/daw/receptes/ReceptesSaludablesConfiguration.java#L60) Classe anotada per a que Spring la utilitzi en la resolució dels elements

### Base de dades

S’ha utilitzat la capacitat de base de dades encastada per tal de simplificar la execució. Les dades s’emmagatzemen en memòria, però no es persisteixen, així doncs, cada nova execució parteix de la mateixa base de dades, amb les receptes però sense els comentaris desats.

La base de dades és inicialitzada al començament de la execució amb la estructura i les dades necessàries.

Això es fa amb dos arxius:

* [src/main/resources/db/esquema.sql](src/main/resources/db/esquema.sql) per a la definició de la estructura
* [src/main/resources/db/dades.sql](src/main/resources/db/dades.sql) per a poblar la base de dades

L’element que fa possible la inicialització és la classe que implementa l’accés a la mateixa base de dades: [cat.albirar.daw.receptes.repositoris.impl.RepoReceptesImpl](src/main/java/cat/albirar/daw/receptes/repositoris/impl/RepoReceptesImpl.java#L58)

Aprofitant la característica de _post-inicialització_ de Spring, amb l’anotació `@Postconstruct`, es fa la inicialització. En concret al mètode [prepararBD()](src/main/java/cat/albirar/daw/receptes/repositoris/impl/RepoReceptesImpl.java#L64). Aquest mètode utilitza un _DatabasePopulator_ que s’ha preparat a la classe de configuració i Spring ha injectat a la propietat amb l’anotació `@Autowired`.

Per a la materialització de _beans_ des de la base de dades, s’utilitzen classe que implementen el patró _mapper_ d’Spring. Aquestes classes són al paquet [cat.albirar.daw.receptes.repositoris.mappers](src/main/java/cat/albirar/daw/receptes/repositoris/mappers). Aquest patró és de cooperació i les classes només han d’obtenir les dades del _Resultset_ obtingut en l’execució SQL i instanciar i poblar el bean corresponent. Podeu veure un exemple a [cat.albirar.daw.receptes.repositoris.mappers.ReceptaBeanSimpleMapper.mapRow(ResultSet, int)](src/main/java/cat/albirar/daw/receptes/repositoris/mappers/ReceptaBeanSimpleMapper.java#L66).

La classe d’accés a la base de dades implementa el contracte definit per la interfície [cat.albirar.daw.receptes.repositoris.IRepoReceptes](src/main/java/cat/albirar/daw/receptes/repositoris/IRepoReceptes.java#L40). Així, doncs, utilitzem aquest patró per a separar la definició de la implementació.

Tant la classe de implementació com la interfície estan anotats amb indicadors transaccionals. Spring implementa la transacció amb un sistema basat en proxies i que simplifica enormement la gestió de transaccions. Aquest sistema redueix els errors al mínim, només aquells per error de definició o anàlisi.

### MVC

L’aplicació segueix el consolidat patró MVC. Utilitza [thymeleaf](https://www.thymeleaf.org) per a la generació de les pàgines dinàmiques. S’ha triat aquest sistema de plantilles perquè s’insereix perfectament a l’html, de manera que es pot fer la maquetació de l’arxiu al navegador sense dades reals.

Les plantilles es troben a [src/main/resources/templates](src/main/resources/templates) i els elements estàtics (javascript, css, etc) es troben a [src/main/resources/static](src/main/resources/static)

**Els models** s’implementa amb java beans creats amb la llibreria [lombok](https://projectlombok.org/), ja que estalvia la feina de generar els getters i setters, així com constructors, equals, hashcode, etc. També proporciona uns «builders» que permeten la construcció de beans de manera senzilla.

Els models es creen al directori especial [src/main/lombok](src/main/lombok), al paquet [cat.albirar.daw.receptes.models](src/main/lombok/cat/albirar/daw/receptes/models).

**El controlador** és la classe [cat.albirar.daw.receptes.controladors.ControladorWeb](src/main/java/cat/albirar/daw/receptes/controladors/ControladorWeb.java#L56). Els mètodes estan anotats per a que spring-mvc els vinculi amb les URLs de la nostra aplicació.

S’utilitza l’objecte ‘Model’ per tal d’afegir els valors de les propietats que necessitarem a la pàgina.

El controlador utilitza un **servei** per a accedir a la operativa de les dades, lectura, transformació, persistència, etc.

La interfície [cat.albirar.daw.receptes.servei.IServeiReceptes](src/main/java/cat/albirar/daw/receptes/servei/IServeiReceptes.java#L44) defineix el contracte del servei.

El servei s’implementa amb la classe [cat.albirar.daw.receptes.servei.impl.ServeiReceptesImpl](src/main/java/cat/albirar/daw/receptes/servei/impl/ServeiReceptesImpl.java#L55). Aquest patró permet que aïllar la presentació de les dades, de manera que l’origen d’aquestes sigui absolutament irrellevant per al controlador. És el servei qui s’encarrega d’obtenir-les i transformar-les (quan calgui).

### Json+LD

Un dels requeriments de l’aplicació és la d’oferir metadades en format *Json-LD* per tal d’acomplir amb els requeriments de Google pel que fa a indexació de pàgines.

S’ha cercat llibreries _java_ amb implementacions de transformadors *JavaBeans->Json+LD*, però no s’ha trobat cap que estigui prou documentat o madur com per a que sigui fiable.

Donat que la transformació no és veritablement complexa, s’ha decidit implementar una classe per a fer aquesta transformació.

La classe és [cat.albirar.daw.receptes.jsonld.JsonLdBuilder](src/main/java/cat/albirar/daw/receptes/jsonld/JsonLdBuilder.java#L38). De manera genèrica permet compondre _documents_ de tipus _json_ amb les especificitats definides als esquemes http://schema.org/. Aquests esquemes són els utilitzats per Google per a les seves especificacions.

### Altres elements

A més dels elements esmentats, s’ha fet una utilitat per a convertir text marcat _markdown_ en HTML, ja que les instruccions d’elaboració de les receptes utilitzen aquest llenguatge de marques per a la redacció.

La classe que implementa aquesta transformació és: [cat.albirar.daw.receptes.markdown.ProcessadorMD](src/main/java/cat/albirar/daw/receptes/markdown/ProcessadorMD.java).

Especialment notori és el mètode que converteix el text de les instruccions en una llista de passes en HTML separades per una barra vertical. Aquesta estratègia permet que la plantilla de mostrar la recepta, separi cada passa d’instrucció en element separat que es mostrarà amb un numeral.

El mètode és [cat.albirar.daw.receptes.markdown.ProcessadorMD.instruccions2HtmlEnLinies(String)](src/main/java/cat/albirar/daw/receptes/markdown/ProcessadorMD.java#L83). I a la plantilla, la transformació es fa a [src/main/resources/templates/recepta.html linia 90](src/main/resources/templates/recepta.html#L90).

## Pàgines

Podeu veure les categories disponibles al menú de la esquerra, que es desplega quan cliqueu a sobre del botó *hamburger*.

Quan passeu el ratolí per sobre del títol _Receptes_, es desplega un quadre amb cinc receptes aleatòries.

A la **pàgina principal** es pot veure un _carrusel_ amb dos grups de quatre receptes cadascú. Cada grup es mostra amb una recepta que ocupa la meitat esquerra i les altres tres es reparteixen la meitat dreta, en dues línies.

Si cliqueu al títol de qualsevol recepta, anireu a la **pàgina de la recepta**.

Hi ha dues pàgines més: **keywords** i **categoria**. Totes dues mostren una llista de receptes associades al respectiu element (keyword o categoria).

L’accés a aquestes pàgines es:

* **keywords** Des de la pàgina de **recepta**, a la part inferior es mostren els botons d’accés amb cada *keyword*.
* **categoria** Des del menú lateral o bé a la pàgina de la **recepta**, hi ha un enllaç just sobre el títol de la recepta.

## Metadades

L’aplicació exposa dos tipus de metadada:

* *ItemList* per a la relació de receptes
* *Recipe* per a la descripció d’una recepta

El primer tipus s’exposa a totes les pàgines i l’origen és les receptes que s’hi troben al carrusel, al menú flotant del títol *Receptes* i a la llista de receptes de **keywords** o **categoria**.

El tipus *Recipe* només s’exposa a la pàgina de recepta.

