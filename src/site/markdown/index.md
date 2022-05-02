# DAW - M7 - UF2 - Receptes

> Generació dinàmica de pàgines web

**Continguts**

1. [Introducció](#Introducci.C3.B3)
2. [Prova UF2](#Prova_UF2)

------

## Introducció

Per a resoldre la prova, s’ha creat un projecte Java amb maven i Spring Initializr.

S’ha utilitzat Spring com a framework de desenvolupament per tal d’aprofitar els recursos i facilitats que proporciona (IoC, Dependency Injection, etc.)

Per a crear els *JavaBeans* s'ha utilitzat la llibreria [Lombok](https://projectlombok.org/) que simplifica la implementació i redueix els errors típics de la farragosa i mecànica feina de implementar *getters* i *setters*.

Aquesta utilitat disposa d'un _plugin_ per a diferents IDEs de manera que ofereix els mètodes d'accés a les propietats _on the fly_.

S’ha utilitzat el submòdul Spring MVC per a la part Web.

S’utilitza el submòdul JDBC per a les operacions amb dades.

S’han extret 34 receptes d’una coneguda WEB per tal de no haver d’inventar-les.

S’han obtingut en format JSon i es passaran a format SQL, per a gestionar amb facilitat.

Pel front-end s’ha triat [Bootstrap v5.1.3](https://getbootstrap.com/docs/5.1/getting-started/introduction/). Els mòduls s’obtenen amb el gestor de paquets de front-end per a maven webjars.

## Prova UF2

> > Aquesta pràctica està pensada per ser realitzada en un framework PHP com pot ser Laravel o Symfony. Podem ajustar els requeriments al framework que utilitzis habitualment.
>
> El nostre client ens demana una pàgina web per a la seva empresa. La seva empresa s'encarrega d'oferir receptes saludables. Es vol posicionar oferint una oferta de receptes saludables per aquestes dates, ja que principi d'any és l'època on la gent comença més dietes i està més interessada a cercar informació sobre receptes saludables.
>
> Per això ens ofereix una guia d'estil que hem de seguir per fer l'aparença de la web on ens indicaran els colors corporatius de l'empresa, la tipografia i les diferents webs que vol. (M9).
>
> Ha estat assessorant-se sobre el sector hi ha trobat en el fet que és necessari utilitzar de dades estructurades per posicionar-se correctament el cercador Google i oferir un contingut més bonic dins de les cerques.
>
> Per això la pàgina web ha de poder generar les dades estructurades.
>
> https://developers.google.com/search/docs/advanced/structured-data/recipe?hl=es
>
> ### Estructura
>
> La web ha de poder mostrar un llistat de categories de receptes, llistat de receptes d'una categoria, els usuaris han de poder deixar comentaris a les receptes.
>
> ### Dades estructurades
>
> Requeriments mínims:
>
> Poder generar les dades estructurades de la recepta amb els camps **mínims**: Name, image, author, description, prepTime, recipeIngredients, recipeInstructions.
>
> Opcionalment podem afegir: video, agregateRating
