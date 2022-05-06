# DAW - M7 - UF2 - Receptes

Proposta de treball per a la UF2 del mòdul 7

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
   * Encastament de les meta-dades a la pàgina amb elements `scripts`
7. Provatures finals i documentació

## Execució de l’aplicació

Anar a la release v0.1.1 i descarregar el paquet jar .

Executar des de línia de comandes amb:

```bash
java -jar receptes-0.0.1.jar
```

Ara podeu visitar la pàgina a http://localhost:8080

## Anàlisi, descripcions i observacions

L’aplicació s’ha fet amb el framework spring-boot.

S’ha utilitzat la capacitat d’execució autònoma, que no requereix de cap servidor d’aplicacions.

També s’ha utilitzat la capacitat de base de dades encastada per tal de simplificar la execució. Les dades s’emmagatzemen en memòria, però no es persisteixen, així doncs, cada nova execució parteix de la mateixa base de dades, amb les receptes però sense els comentaris desats.

Com a eina de construcció s’ha utilitzat MAVEN.

### MVC

L’aplicació segueix el consolidat patró MVC. Utilitza [thymeleaf](https://www.thymeleaf.org) per a la generació de les pàgines dinàmiques. S’ha triat aquest sistema de plantilles perquè s’insereix perfectament a l’html, de manera que es pot fer la maquetació de l’arxiu al navegador sense dades reals.

**Els models** s’implementa amb java beans creats amb la llibreria [lombok](https://projectlombok.org/), ja que estalvia la feina de generar els getters i setters, així com constructors, equals, hashcode, etc. També proporciona uns «builders» que permeten la construcció de beans de manera senzilla.

Els models es creen al directori especial [src/main/lombok](src/main/lombok), al paquet [cat.albirar.daw.receptes.models](src/main/lombok/cat/albirar/daw/receptes/models).

**El controlador** és 