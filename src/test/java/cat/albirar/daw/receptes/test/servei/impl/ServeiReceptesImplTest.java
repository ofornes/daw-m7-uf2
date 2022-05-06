/*
 * This file is part of "receptes".
 * 
 * "receptes" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * "receptes" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with calendar.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright (C) 2022 Octavi Fornés
 */
package cat.albirar.daw.receptes.test.servei.impl;

import static cat.albirar.daw.receptes.jsonld.JsonLdBuilder.htmlInsertable;
import static cat.albirar.daw.receptes.jsonld.JsonLdBuilder.unicodeEscape;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cat.albirar.daw.receptes.models.ReceptaBean;
import cat.albirar.daw.receptes.servei.IServeiReceptes;
import cat.albirar.daw.receptes.servei.impl.ServeiReceptesImpl;
import cat.albirar.daw.receptes.test.ReceptesSaludablesConfigurationTest;
import cat.albirar.daw.receptes.test.TestDadesComunsAbstracte;

/**
 * Provatures de {@link ServeiReceptesImpl}.
 * @author Octavi Fornés <a href="mailto:octavi@fornes.cat">&lt;octavi@fornes.cat&gt;</a>
 * @since 0.0.1
 */
@ContextConfiguration(classes = ReceptesSaludablesConfigurationTest.class)
@ExtendWith(SpringExtension.class)
public class ServeiReceptesImplTest extends TestDadesComunsAbstracte {
	
	@Autowired
	private IServeiReceptes servei;
	
	private static final String[] JSONLD_DADES = {
		 "{\"@context\":\"http://schema.org/\",\"@type\":\"Recipe\",\"name\":\"Cómo hacer torrijas sin pan y sin azúcar: la versión más saludable baja en hidratos y sin gluten que había que probar\",\"datePublished\":\"2022-04-14\",\"author\":{\"@type\":\"Person\",\"name\":\"Liliana Fuchs\"},\"description\":\"Las torrijas no son un postre ligero, negarlo sería autoengañarse. Sí podemos hacerlas más o menos calóricas cuidando detalles y eligiendo bien los...\",\"image\":\"https://i.blogs.es/677ddb/torrijas-fit/650_1200.jpg\",\"totalTime\":\"P0DT1H10M\",\"prepTime\":\"P0DT0H20M\",\"recipeYield\":6,\"recipeCategory\":\"Postres\",\"keywords\":\"sin azúcar, proteínas, Torrijas, Recetas Saludables, Recetas sin Gluten, Recetas de Semana Santa\",\"recipeInstructions\":\"Precalentar el horno a 180ºC con calor arriba y abajo, <strong>sin aire</strong>. Forrar con papel antiadherente -sulfurizado- un molde rectangular pequeño de unos 18-20 cm de largo. Se puede usar más grande, pero quedará una masa menos alta.Escurrir el <strong>líquido o suero</strong> que pueda tener el quark o yogur. Batir con una varillas o batidora junto con los huevos, la proteína en polvo o harina de almendra o avena, el lino molido o maizena, la vainilla, canela al gusto, edulcorante -opcional- y ralladura de limón fina. Procurar que no haya grumos.Llevar al molde y hornear durante unos 40-50 minutos, o hasta que al pinchar el centro con un palillo salga limpio. Esperar unos minutos fuera del horno antes de desmoldar usando el papel; una vez frío por completo, secar el <strong>posible agua que suelte</strong> con papel de cocina -como si fuera tofu- y cortar en rebanadas más bien gruesas.<strong>Bañar en huevo batido</strong> y cocinar a la plancha con un poco de aceite. Rebozar en eritritol mezclado con canela molida como haríamos con torrijas normales y servir templadas o frías.\",\"url\":\"http://localhost:8080/recepta/19\",\"recipeIngredient\":[\"2 Huevo\",\"500g Queso quark\",\"40g Almendra molida\",\"5g Lino molido\",\"5ml Esencia de vainilla\",\"2g Ralladura de limón\",\"80g Eritritol\",\"Canela molida\",\"Huevo batido\",\"Aceite de oliva virgen extra\"],\"cookTime\":\"P0DT0H50M\",\"recipeCuisine\":\"española\"}"
		,"{\"@context\":\"http://schema.org/\",\"@type\":\"Recipe\",\"name\":\"Ensalada de salmón, mango y aguacate, receta ligera para arrancar un menú de celebración\",\"datePublished\":\"2021-12-03\",\"author\":{\"@type\":\"Person\",\"name\":\"Carmen Tía Alia\"},\"description\":\"Te explicamos paso a paso, de manera sencilla, cómo hacer la receta de ensalada de salmón, mango y aguacate. Tiempo de elaboración, ingredientes,\",\"image\":\"https://i.blogs.es/843cea/ensalada-de-salmon-aguacate-y-mango/650_1200.jpg\",\"totalTime\":\"P0DT0H15M\",\"prepTime\":\"P0DT0H15M\",\"recipeYield\":4,\"recipeCategory\":\"Amanides\",\"keywords\":\"Aguacate, Mango, Salmón, Berros, Recetas ligeras, Recetas en menos de 30 minutos, Recetas fáciles y rápidas\",\"recipeInstructions\":\"Preparamos la <strong>vinagreta de mostaza</strong> introduciendo todos los ingredientes en un cuenco y batiendo con unas varillas hasta emulsionar. También los podéis meter <a href=\"https://www.directoalpaladar.com/recetas-de-salsas-y-guarniciones/como-hacer-la-vinagreta-perfecta-y-cinco-ensaladas-ligeras-en-las-que-usarla\">en un tarro de cristal</a>, cerrarlo con su tapa de rosca y agitar con fuerza. Cualquiera de los dos métodos funciona igual de bien. Reservamos.Retiramos la piel del lomo de salmón y lo <strong>repasamos por si quedaran espinas y escamas</strong>. Cortamos en dados de aproximadamente un centímetro de lado. Pelamos la cebolla y la cortamos en juliana fina. Cortamos el mango en dados, tarea que resula sencilísima si seguís nuestros consejos y recomendaciones para <a href=\"https://www.directoalpaladar.com/curso-de-cocina/como-pelar-cortar-mango-ensuciarse\">pelar y cortar un mango sin ensuciarse</a>. Cortamos el aguacate por la mitad, retiramos el hueso y lo pelamos. Lo laminamos o cortamos en dados, como más os guste. Una vez listos todos los componentes de esta ensalada <strong>solo queda montar los platos</strong>. Para ello colocamos una base de berros, limpios y secos, sobre cada plato y repartir el salmón, el mango y el aguacate por la superficie. Terminamos espolvoreado con semillas de sésamo negro y regando con un poco de vinagreta. Servimos inmediatamente.\",\"url\":\"http://localhost:8080/recepta/7\",\"recipeIngredient\":[\"200g Salmón\",\"1 Cebolla morada\",\"1 Mango\",\"1 Aguacate\",\"150g Berros\",\"100ml Aceite de oliva virgen extra\",\"25ml Vinagre de manzana\",\"5ml Mostaza de Dijon\",\"5ml Miel\",\"Sal\"],\"recipeCuisine\":\"española\"}"
		,"{\"@context\":\"http://schema.org/\",\"@type\":\"Recipe\",\"name\":\"Pastel de chocolate proteico en microondas: receta sin gluten y sin azúcar añadido\",\"datePublished\":\"2022-04-02\",\"author\":{\"@type\":\"Person\",\"name\":\"Liliana Fuchs\"},\"description\":\"Este capricho de cacao es para quienes disfruten de dulces sin ser tan dulces, amantes del sabor a intenso chocolate y una textura jugosa que puede recordar...\",\"image\":\"https://i.blogs.es/3d3621/pastel-chocolate-fitness/650_1200.jpg\",\"totalTime\":\"P0DT0H30M\",\"prepTime\":\"P0DT0H15M\",\"recipeYield\":8,\"recipeCategory\":\"Postres\",\"keywords\":\"microondas, Cacao, sin azúcar, Chocolate negro, Recetas Saludables, Recetas sin Gluten, Postres sin horno\",\"recipeInstructions\":\"Triturar o batir en un procesador de alimentos los plátanos troceados con el yogur o queso batido -escurrido del posible suero-. Añadir los huevos y la vainilla y <strong>triturar un o poco más</strong> hasta integrar.Incorporar el cacao tamizado, la levadura, la proteína en polvo y la almendra molida, y triturar hasta lograr una masa cremosa sin grumos secos. Llevar a un <strong>molde apto para microondas</strong> -engrasado si no es de silicona- de unos 18-20 cm de diámetro, y cocer a máxima potencia unos 10 minutos.<strong>Comprobar que está cuajado</strong> en el centro pinchado con un palillo, esperar un poco antes de desmoldar y dejar enfriar sobre una rejilla. Mientras, trocear el chocolate, derretir con el aceite de coco al baño maría o en microondas a intervalos cortos y remover hasta formar una crema líquida.Verter <strong>sobre el patel frío</strong> y extender con una espátula o similar. Se puede decorar con frutos secos picados o coco rallado antes de que se solidifique. Guardar preferiblemente en la nevera si no se consume en el día.\",\"url\":\"http://localhost:8080/recepta/21\",\"recipeIngredient\":[\"400g Plátano\",\"100g Yogur natural\",\"3 Huevo\",\"5ml Esencia de vainilla\",\"60g Proteína en polvo\",\"20g Almendra molida\",\"4g Levadura química\",\"30g Cacao en polvo\",\"80g Chocolate negro\",\"10ml Aceite de coco\"],\"recipeCuisine\":\"española\"}"
		,"{\"@context\":\"http://schema.org/\",\"@type\":\"Recipe\",\"name\":\"Tortilla de brócoli con claras de huevo: receta ligera saludable rica en proteínas y muy poca grasa\",\"datePublished\":\"2022-01-26\",\"author\":{\"@type\":\"Person\",\"name\":\"Liliana Fuchs\"},\"description\":\"Las claras de huevo pasteurizadas son un recurso estupendo para tener en la nevera y aprovechar mucho más allá de la repostería. Si bien las yemas son también...\",\"image\":\"https://i.blogs.es/a86640/tortilla-brocoli-claras/650_1200.jpg\",\"totalTime\":\"P0DT0H25M\",\"prepTime\":\"P0DT0H10M\",\"recipeYield\":2,\"recipeCategory\":\"Ous i Truites\",\"keywords\":\"Tortilla, Recetas Saludables, Brócoli, Cenas ligeras, Claras de huevo, Recetas rápidas y fáciles, Recetas fáciles y rápidas\",\"recipeInstructions\":\"Cortar los 200 g de floretes del brócoli en piezas pequeñas de un tamaño semejante, aproximadamente, o dejando algunas más grandes para dar otra textura; reservar los troncos y tallos para otra preparación o aprovechar para pelar la parte más leñosa y añadirlos también. Lavar y escurrir con suavidad.Cocer brevemente <strong>al vapor o en el microondas</strong> unos 5 minutos (con un poco de agua y cubierto con tapa para este aparato o con film), para dejarlo tierno pero aún firme, un poco <em>al dente</em>. Si se prefiere más tierno, dejar más tiempo.Batir en un recipiente con unas varillas o un tenedor las claras de huevo con la leche o nata líquida y <strong>todas las especias y aderezos al gusto</strong>, con la levadura de cerveza nutricional opcional. Se puede añadir también un poco de queso rallado fino.Engrasar homogéneamente una sartén antiadherente de unos 20 cm diámetro, calentar a fuego medio y <strong>saltear el brócoli</strong> ligeramente con un poco de sal para darle un toque crujiente. Añadir la mezcla de claras y remover para extender homogéneamente todo bien.Bajar el fuego y tapar; cocinar suavemente durante unos minutos hasta que el borde esté más firme y el centro cuajado pero aún jugoso; se debe poder <strong>levantar la tortilla con una lengua</strong> o espátula fina si se introduce por el borde.Podríamos darle la vuelta a la tortilla como <a href=\"https://www.directoalpaladar.com/recetas-de-huevos-y-tortillas/empezando-en-la-cocina-receta-de-tortilla-de-patatas-con-cebolla\">al hacer una de patatas</a> y servirla boca abajo, pero queda más vistosa con el brócoli destacando. Cuando esté al punto deseado, apagar el fuego, <strong>pasar la espátula por debajo de la tortilla y deslizar</strong> sobre una fuente. Servir con perejil fresco o cilantro picado.\",\"url\":\"http://localhost:8080/recepta/27\",\"recipeIngredient\":[\"200g Brócoli\",\"300ml Clara de huevo\",\"50ml Leche\",\"Levadura de cerveza\",\"Cúrcuma molida\",\"Pimentón dulce\",\"Pimienta negra molida\",\"Aceite de oliva virgen extra\",\"Perejil fresco\",\"Sal\"],\"cookTime\":\"P0DT0H15M\",\"recipeCuisine\":\"española\"}"
		,"{\"@context\":\"http://schema.org/\",\"@type\":\"Recipe\",\"name\":\"54 cenas saludables que tener siempre en mente para mejorar nuestra dieta\",\"datePublished\":\"2022-01-23\",\"author\":{\"@type\":\"Person\",\"name\":\"Carmen Tía Alia\"},\"description\":\"La intención de comer sano está más presente en nuestras vidas en ciertas épocas del año. Suele coincidir con los periodos postvacacionales (Navidades, Semana...\",\"image\":\"https://i.blogs.es/9e8db5/cenas-saludables/650_1200.jpg\",\"totalTime\":\"P0DT0H15M\",\"prepTime\":\"P0DT0H15M\",\"recipeYield\":4,\"recipeCategory\":\"Cuina\",\"keywords\":\"Dieta, Nueces, cena, saludable, Champiñones, Recetas Saludables\",\"recipeInstructions\":\"Comenzaremos tostando ligeramente en una sartén las nueces. Cuando pasen unos minutos y las veamos tostadas, las reservamos mientras que enfrían y las partimos en trozos grandes. Para hacer la mayonesa, en un bol echamos la yema de huevo, salamos y comenzamos a batir con batidor o las varillas de una batidora incorporando el aceite en chorro fino para que se haga la emulsión. Añadimos el vinagre y reservamos en frío. Pelamos y cortamos las manzanas en dadao o bastones, el apio en trozos menudos y lavamos y centrifugamos la lechuga. Seguidamente en una ensaladera ponemos el apio, la manzana, la mitad de las nueces y las uvas pasas, echamos la mayonesa al gusto y mezclamos bien. En la fuente de servicio ponemos una cama de lechuga troceada, por encima la mezcla de apio, manzana, nueces y pasas con la mayonesa, y finalmente decoramos con el resto de las nueces. Servir rápidamente.\",\"url\":\"http://localhost:8080/recepta/29\",\"recipeIngredient\":[\"4 Cogollo de lechuga\",\"3 Apio\",\"2 Manzana\",\"125g Nueces\",\"125g Uvas pasas sultanas\",\"1 Yema de huevo\",\"30ml Vinagre\",\"150ml Aceite de oliva virgen extra\",\"Sal\"],\"recipeCuisine\":\"española\"}"
	};
	
	private static final String JSONLD_LIST = "{\"@context\":\"http://schema.org/\",\"@type\":\"ItemList\",\"numberOfItems\":5,\"itemListElement\":[{\"@type\":\"ListItem\",\"position\":1,\"url\":\"http://localhost:8080/recepta/19\"},{\"@type\":\"ListItem\",\"position\":2,\"url\":\"http://localhost:8080/recepta/7\"},{\"@type\":\"ListItem\",\"position\":3,\"url\":\"http://localhost:8080/recepta/21\"},{\"@type\":\"ListItem\",\"position\":4,\"url\":\"http://localhost:8080/recepta/27\"},{\"@type\":\"ListItem\",\"position\":5,\"url\":\"http://localhost:8080/recepta/29\"}]}";
	
	@Test
	public void whenProcessARecipe_Then_AJsonLdRecipeIsGet() {
		String actual;
		for(int n=0; n < SRC_DADES.length; n++) {
			actual = servei.produirJsonLd(r -> String.format("http://localhost:8080/recepta/%d", r.getId()), mostres.get(n));
			assertEquals(htmlInsertable(unicodeEscape(JSONLD_DADES[n])), actual);
		}
	}
	
	@Test
	public void whenBuildList_Then_AJsonLdListIsGet() {
		String actual;
		
		actual = servei.produirJsonLd(recepta -> String.format("http://localhost:8080/recepta/%d", recepta.getId()), mostres);
		assertEquals(htmlInsertable(unicodeEscape(JSONLD_LIST)), actual);
	}
	@Test
	public void whenFindByIdWithExisting_Then_TheReceptaIsGet() {
		ReceptaBean actual;
		
		for(ReceptaBean expected : mostres) {
			actual = servei.receptaPerId(expected.getId());
			assertNotNull(actual);
			assertEquals(expected, actual);
			
		}
	}
}
