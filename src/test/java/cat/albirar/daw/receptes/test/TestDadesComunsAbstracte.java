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
package cat.albirar.daw.receptes.test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.util.StringUtils;

import cat.albirar.daw.receptes.models.IngredientReceptaBean;
import cat.albirar.daw.receptes.models.ReceptaBean;

/**
 * Dades comunes per a provatures.
 * @author Octavi Fornés <a href="mailto:octavi@fornes.cat">&lt;octavi@fornes.cat&gt;</a>
 * @since 0.0.1
 */
@Profile("test")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public abstract class TestDadesComunsAbstracte {

	protected List<ReceptaBean> mostres;
	protected DateTimeFormatter instantFormater = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
	/**
	 * Dades de cada recepta, l'array de la recepta conté aquesta informació:
	 * <pre>
	 * author/name
	 * datePublished
	 * description
	 * image
	 * keywords (text separat per barra vertical)
	 * name
	 * prepTime
	 * recipeInstructions
	 * recipeYield
	 * totalTime
	 * cookTime
	 * recipeCategory
	 * recipeCuisine
	 * recipeIngredient (text en format {@link IngredientReceptaBean#textAIngredient(String)} amb cada ingredient separat per barra vertical)
	 * </pre>
	 * Els identificadors són:
	 * <ul>
	 * <li>19</li>
	 * <li>7</li>
	 * <li>21</li>
	 * <li>27</li>
	 * <li>29</li>
	 * </ul>
	 */
	protected static final String [][] SRC_DADES = {
			{ "19", "Liliana Fuchs", "14/04/22 10:00:48", "Las torrijas no son un postre ligero, negarlo sería autoengañarse. Sí podemos hacerlas más o menos calóricas cuidando detalles y eligiendo bien los...", "https://i.blogs.es/677ddb/torrijas-fit/650_1200.jpg", "sin azúcar|proteínas|Torrijas|Recetas Saludables|Recetas sin Gluten|Recetas de Semana Santa", "Cómo hacer torrijas sin pan y sin azúcar: la versión más saludable baja en hidratos y sin gluten que había que probar", "P0DT0H20M", "Precalentar el horno a 180ºC con calor arriba y abajo, **sin aire**. Forrar con papel antiadherente -sulfurizado- un molde rectangular pequeño de unos 18-20 cm de largo. Se puede usar más grande, pero quedará una masa menos alta.Escurrir el **líquido o suero** que pueda tener el quark o yogur. Batir con una varillas o batidora junto con los huevos, la proteína en polvo o harina de almendra o avena, el lino molido o maizena, la vainilla, canela al gusto, edulcorante -opcional- y ralladura de limón fina. Procurar que no haya grumos.Llevar al molde y hornear durante unos 40-50 minutos, o hasta que al pinchar el centro con un palillo salga limpio. Esperar unos minutos fuera del horno antes de desmoldar usando el papel; una vez frío por completo, secar el **posible agua que suelte** con papel de cocina -como si fuera tofu- y cortar en rebanadas más bien gruesas.**Bañar en huevo batido** y cocinar a la plancha con un poco de aceite. Rebozar en eritritol mezclado con canela molida como haríamos con torrijas normales y servir templadas o frías.", "6", "P0DT1H10M", "P0DT0H50M", "Postres", "española", "2count Huevo|500g Queso quark|40g Almendra molida|5g Lino molido|5ml Esencia de vainilla|2g Ralladura de limón|80g Eritritol|1undef Canela molida|1undef Huevo batido|1undef Aceite de oliva virgen extra" },
			{ "7", "Carmen Tía Alia", "03/12/21 11:00:32", "Te explicamos paso a paso, de manera sencilla, cómo hacer la receta de ensalada de salmón, mango y aguacate. Tiempo de elaboración, ingredientes,", "https://i.blogs.es/843cea/ensalada-de-salmon-aguacate-y-mango/650_1200.jpg", "Aguacate|Mango|Salmón|Berros|Recetas ligeras|Recetas en menos de 30 minutos|Recetas fáciles y rápidas", "Ensalada de salmón, mango y aguacate, receta ligera para arrancar un menú de celebración", "P0DT0H15M", "Preparamos la **vinagreta de mostaza** introduciendo todos los ingredientes en un cuenco y batiendo con unas varillas hasta emulsionar. También los podéis meter [en un tarro de cristal](https://www.directoalpaladar.com/recetas-de-salsas-y-guarniciones/como-hacer-la-vinagreta-perfecta-y-cinco-ensaladas-ligeras-en-las-que-usarla), cerrarlo con su tapa de rosca y agitar con fuerza. Cualquiera de los dos métodos funciona igual de bien. Reservamos.Retiramos la piel del lomo de salmón y lo **repasamos por si quedaran espinas y escamas**. Cortamos en dados de aproximadamente un centímetro de lado. Pelamos la cebolla y la cortamos en juliana fina. Cortamos el mango en dados, tarea que resula sencilísima si seguís nuestros consejos y recomendaciones para [pelar y cortar un mango sin ensuciarse](https://www.directoalpaladar.com/curso-de-cocina/como-pelar-cortar-mango-ensuciarse). Cortamos el aguacate por la mitad, retiramos el hueso y lo pelamos. Lo laminamos o cortamos en dados, como más os guste. Una vez listos todos los componentes de esta ensalada **solo queda montar los platos**. Para ello colocamos una base de berros, limpios y secos, sobre cada plato y repartir el salmón, el mango y el aguacate por la superficie. Terminamos espolvoreado con semillas de sésamo negro y regando con un poco de vinagreta. Servimos inmediatamente.", "4", "P0DT0H15M", "", "Amanides", "española", "200g Salmón|1count Cebolla morada|1count Mango|1count Aguacate|150g Berros|100ml Aceite de oliva virgen extra|25ml Vinagre de manzana|5ml Mostaza de Dijon|5ml Miel|1undef Sal" },
			{ "21", "Liliana Fuchs", "02/04/22 16:01:19", "Este capricho de cacao es para quienes disfruten de dulces sin ser tan dulces, amantes del sabor a intenso chocolate y una textura jugosa que puede recordar...", "https://i.blogs.es/3d3621/pastel-chocolate-fitness/650_1200.jpg", "microondas|Cacao|sin azúcar|Chocolate negro|Recetas Saludables|Recetas sin Gluten|Postres sin horno", "Pastel de chocolate proteico en microondas: receta sin gluten y sin azúcar añadido", "P0DT0H15M", "Triturar o batir en un procesador de alimentos los plátanos troceados con el yogur o queso batido -escurrido del posible suero-. Añadir los huevos y la vainilla y **triturar un o poco más** hasta integrar.Incorporar el cacao tamizado, la levadura, la proteína en polvo y la almendra molida, y triturar hasta lograr una masa cremosa sin grumos secos. Llevar a un **molde apto para microondas** -engrasado si no es de silicona- de unos 18-20 cm de diámetro, y cocer a máxima potencia unos 10 minutos.**Comprobar que está cuajado** en el centro pinchado con un palillo, esperar un poco antes de desmoldar y dejar enfriar sobre una rejilla. Mientras, trocear el chocolate, derretir con el aceite de coco al baño maría o en microondas a intervalos cortos y remover hasta formar una crema líquida.Verter **sobre el patel frío** y extender con una espátula o similar. Se puede decorar con frutos secos picados o coco rallado antes de que se solidifique. Guardar preferiblemente en la nevera si no se consume en el día.", "8", "P0DT0H30M", "", "Postres", "española", "400g Plátano|100g Yogur natural|3count Huevo|5ml Esencia de vainilla|60g Proteína en polvo|20g Almendra molida|4g Levadura química|30g Cacao en polvo|80g Chocolate negro|10ml Aceite de coco" },
			{ "27", "Liliana Fuchs", "26/01/22 11:01:59", "Las claras de huevo pasteurizadas son un recurso estupendo para tener en la nevera y aprovechar mucho más allá de la repostería. Si bien las yemas son también...", "https://i.blogs.es/a86640/tortilla-brocoli-claras/650_1200.jpg", "Tortilla|Recetas Saludables|Brócoli|Cenas ligeras|Claras de huevo|Recetas rápidas y fáciles|Recetas fáciles y rápidas", "Tortilla de brócoli con claras de huevo: receta ligera saludable rica en proteínas y muy poca grasa", "P0DT0H10M", "Cortar los 200 g de floretes del brócoli en piezas pequeñas de un tamaño semejante, aproximadamente, o dejando algunas más grandes para dar otra textura; reservar los troncos y tallos para otra preparación o aprovechar para pelar la parte más leñosa y añadirlos también. Lavar y escurrir con suavidad.Cocer brevemente **al vapor o en el microondas** unos 5 minutos (con un poco de agua y cubierto con tapa para este aparato o con film), para dejarlo tierno pero aún firme, un poco *al dente*. Si se prefiere más tierno, dejar más tiempo.Batir en un recipiente con unas varillas o un tenedor las claras de huevo con la leche o nata líquida y **todas las especias y aderezos al gusto**, con la levadura de cerveza nutricional opcional. Se puede añadir también un poco de queso rallado fino.Engrasar homogéneamente una sartén antiadherente de unos 20 cm diámetro, calentar a fuego medio y **saltear el brócoli** ligeramente con un poco de sal para darle un toque crujiente. Añadir la mezcla de claras y remover para extender homogéneamente todo bien.Bajar el fuego y tapar; cocinar suavemente durante unos minutos hasta que el borde esté más firme y el centro cuajado pero aún jugoso; se debe poder **levantar la tortilla con una lengua** o espátula fina si se introduce por el borde.Podríamos darle la vuelta a la tortilla como [al hacer una de patatas](https://www.directoalpaladar.com/recetas-de-huevos-y-tortillas/empezando-en-la-cocina-receta-de-tortilla-de-patatas-con-cebolla) y servirla boca abajo, pero queda más vistosa con el brócoli destacando. Cuando esté al punto deseado, apagar el fuego, **pasar la espátula por debajo de la tortilla y deslizar** sobre una fuente. Servir con perejil fresco o cilantro picado.", "2", "P0DT0H25M", "P0DT0H15M", "Ous i Truites", "española", "200g Brócoli|300ml Clara de huevo|50ml Leche|1undef Levadura de cerveza|1undef Cúrcuma molida|1undef Pimentón dulce|1undef Pimienta negra molida|1undef Aceite de oliva virgen extra|1undef Perejil fresco|1undef Sal" },
			{ "29", "Carmen Tía Alia", "23/01/22 18:01:11", "La intención de comer sano está más presente en nuestras vidas en ciertas épocas del año. Suele coincidir con los periodos postvacacionales (Navidades, Semana...", "https://i.blogs.es/9e8db5/cenas-saludables/650_1200.jpg", "Dieta|Nueces|cena|saludable|Champiñones|Recetas Saludables", "54 cenas saludables que tener siempre en mente para mejorar nuestra dieta", "P0DT0H15M", "Comenzaremos tostando ligeramente en una sartén las nueces. Cuando pasen unos minutos y las veamos tostadas, las reservamos mientras que enfrían y las partimos en trozos grandes. Para hacer la mayonesa, en un bol echamos la yema de huevo, salamos y comenzamos a batir con batidor o las varillas de una batidora incorporando el aceite en chorro fino para que se haga la emulsión. Añadimos el vinagre y reservamos en frío. Pelamos y cortamos las manzanas en dadao o bastones, el apio en trozos menudos y lavamos y centrifugamos la lechuga. Seguidamente en una ensaladera ponemos el apio, la manzana, la mitad de las nueces y las uvas pasas, echamos la mayonesa al gusto y mezclamos bien. En la fuente de servicio ponemos una cama de lechuga troceada, por encima la mezcla de apio, manzana, nueces y pasas con la mayonesa, y finalmente decoramos con el resto de las nueces. Servir rápidamente.", "4", "P0DT0H15M", "", "Cuina", "española", "4count Cogollo de lechuga|3count Apio|2count Manzana|125g Nueces|125g Uvas pasas sultanas|1count Yema de huevo|30ml Vinagre|150ml Aceite de oliva virgen extra|1undef Sal" }
		};

	@BeforeEach
	public void prepararTest() {
		int n;
		String s;
		
		n = 0;
		mostres = Collections.synchronizedList(new ArrayList<>(5));
		for(String [] recepta : SRC_DADES) {
			n=0;
			mostres.add(ReceptaBean.builder()
					.id(Long.parseLong(recepta[n++]))
					.autor(recepta[n++])
					.publicacio(LocalDateTime.parse(recepta[n++], instantFormater).atZone(ZoneId.systemDefault()).toInstant())
					.descripcio(recepta[n++])
					.urlImatge(recepta[n++])
					.keywords(Arrays.asList(recepta[n++].split("\\|")))
					.nom(recepta[n++])
					.tempsPreparacio(Duration.parse(recepta[n++]))
					.instruccions(recepta[n++])
					.nombreServeis(Integer.parseInt(recepta[n++]))
					.tempsTotal(Duration.parse(recepta[n++]))
					.tempsCoccio(StringUtils.hasText((s = recepta[n++])) ? Optional.of(Duration.parse(s)) : Optional.empty())
					.categoria(recepta[n++])
					.cuina(StringUtils.hasText((s = recepta[n++])) ? Optional.of(s) : Optional.empty())
					.ingredients(Arrays.asList(recepta[n++].split("\\|")).stream().map(t -> IngredientReceptaBean.textAIngredient(t)).collect(Collectors.toList()))
					.build());
		}
	}

}
