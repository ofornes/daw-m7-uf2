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
package cat.albirar.daw.receptes.test.jsonld;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cat.albirar.daw.receptes.jsonld.JsonLdBuilder;
import cat.albirar.daw.receptes.jsonld.MalformedDocumentException;
import cat.albirar.daw.receptes.test.ReceptesSaludablesConfigurationTest;

/**
 * Provatures de {@link JsonLdBuilder}.
 * @author Octavi Fornés <a href="mailto:octavi@fornes.cat">&lt;octavi@fornes.cat&gt;</a>
 * @since 0.0.1
 */
@ContextConfiguration(classes = ReceptesSaludablesConfigurationTest.class)
@ExtendWith(SpringExtension.class)
public class JsonLdBuilderTest {
	@Autowired
	private JsonLdBuilder jsonBuilder;

	@Test
	public void whenCallInUnstartedStateWithoutStartDocument_Then_AMalformedDocumentExceptionIsThrown() {
		jsonBuilder.resetBuilder();
		
		Assertions.assertThrows(MalformedDocumentException.class, () -> jsonBuilder.endDocument());
		jsonBuilder.resetBuilder();
		Assertions.assertThrows(MalformedDocumentException.class, () -> jsonBuilder.addProperty("x", "xx"));
		jsonBuilder.resetBuilder();
		Assertions.assertThrows(MalformedDocumentException.class, () -> jsonBuilder.addPropertyArray("XX", new String[] { "A", "B", "C"}));
	}
	@Test
	public void whenCallInStartedStateWithoutStartDocument_Then_AMalformedDocumentExceptionIsThrown() {
		jsonBuilder.resetBuilder();
		jsonBuilder.startDocument("Recipe");
		Assertions.assertThrows(MalformedDocumentException.class, () -> jsonBuilder.startDocument("XXX"));
		
		jsonBuilder.resetBuilder();
		jsonBuilder.startDocument("Recipe");
		Assertions.assertThrows(MalformedDocumentException.class, () -> jsonBuilder.startDocument("xxx", "www"));
	}

	@Test
	public void whenCreateDoc_Then_AJsonLdIsGet() {
		String r, expected;
		
		expected = "{\"@context\":\"http://schema.org/\",\"@type\":\"Test\",\"nom1\":\"XX\",\"temps\":\"P0DT1H14M\"}";
		r = jsonBuilder.startDocument("Test")
			.addProperty("nom1", "XX")
			.addPropertyDuration("temps", Duration.of(74, ChronoUnit.MINUTES))	// 1 hora i 14 minuts
			.endDocument()
			;
		assertEquals(expected, r);
	}
	@Test
	public void whenCreateDocWihthEscapableCharsAndHtmlInsertable_Then_AJsonLdHtmlInsertableIsGet() {
		String r, expected;
		
		expected = "{\"@context\":\"http:\\/\\/schema.org\\/\",\"@type\":\"Test\",\"nom1\":\"X\\u00E0X\",\"url\":\"https:\\/\\/i.blogs.es\\/677ddb\\/torrijas-fit\\/650_1200.jpg\"}";
		r = jsonBuilder.startDocument("Test")
			.addProperty("nom1", "XàX")
			.addProperty("url", "https://i.blogs.es/677ddb/torrijas-fit/650_1200.jpg")
			.endDocumentHtmlInsertable()
			;
		assertEquals(expected, r);
	}
	public void whenBuildDocumentListAndHtmlInsertable_Then_AJsonLdHtmlInsertableIsGet() {
		String actual, expected;
		List<String> origen;
		
		origen = Arrays.asList(new String[]{
			"https://www.directoalpaladar.com/postres/como-hacer-torrijas-pan-azucar-version-saludable-baja-hidratos-gluten-que-habia-que-probar"
			, "https://www.directoalpaladar.com/recetas-vegetarianas/judias-verdes-tomate-a-libanesa-receta-sencilla-para-saludable-guarnicion-comida-vegana"
			, "https://www.directoalpaladar.com/postres/pastel-chocolate-proteico-microondas-receta-gluten-azucar-anadido"
			, "https://www.directoalpaladar.com/recetas-de-carnes-y-aves/pechugas-pollo-quinoa-brocoli-a-naranja-receta-saludable-completa-sencilla-para-toda-familia"
		});
		
		expected = "{\"@context\":\"http:\\/\\/schema.org\\/\",\"@type\":\"ItemList\",\"itemListElement\":[{\"@type\":\"ListItem\",\"position\":1,\"url\":\"https:\\/\\/www.directoalpaladar.com\\/postres\\/como-hacer-torrijas-pan-azucar-version-saludable-baja-hidratos-gluten-que-habia-que-probar\"},{\"@type\":\"ListItem\",\"position\":2,\"url\":\"https:\\/\\/www.directoalpaladar.com\\/recetas-vegetarianas\\/judias-verdes-tomate-a-libanesa-receta-sencilla-para-saludable-guarnicion-comida-vegana\"},{\"@type\":\"ListItem\",\"position\":3,\"url\":\"https:\\/\\/www.directoalpaladar.com\\/postres\\/pastel-chocolate-proteico-microondas-receta-gluten-azucar-anadido\"},{\"@type\":\"ListItem\",\"position\":4,\"url\":\"https:\\/\\/www.directoalpaladar.com\\/recetas-de-carnes-y-aves\\/pechugas-pollo-quinoa-brocoli-a-naranja-receta-saludable-completa-sencilla-para-toda-familia\"},{\"@type\":\"ListItem\",\"position\":5,\"url\":\"https:\\/\\/www.directoalpaladar.com\\/recetas-de-pescados-y-mariscos\\/receta-boquerones-al-horno-como-cocinarlos-crujientes-jugosos-ahorrando-calorias-extra-fritura\"},{\"@type\":\"ListItem\",\"position\":6,\"url\":\"https:\\/\\/www.directoalpaladar.com\\/recetas-vegetarianas\\/pimientos-rellenos-soja-texturizada-al-horno-receta-vegetariana-saludable-completa-saciante\"},{\"@type\":\"ListItem\",\"position\":7,\"url\":\"https:\\/\\/www.directoalpaladar.com\\/recetario\\/33-recetas-a-plancha-que-nos-ayudaran-a-cuidar-dieta-adelgazar-renunciar-al-sabor\"},{\"@type\":\"ListItem\",\"position\":8,\"url\":\"https:\\/\\/www.directoalpaladar.com\\/videos-recetas\\/montaditos-saludables-berenjena-tomate-receta-para-solucionar-cena-sana-santiamen-video-incluido\"},{\"@type\":\"ListItem\",\"position\":9,\"url\":\"https:\\/\\/www.directoalpaladar.com\\/recetas-de-huevos-y-tortillas\\/tortilla-brocoli-claras-huevo-receta-ligera-saludable-rica-proteinas-muy-poca-grasa\"},{\"@type\":\"ListItem\",\"position\":10,\"url\":\"https:\\/\\/www.directoalpaladar.com\\/recetario\\/12-recetas-aguacate-ligeras-saludables-e-ideales-para-dieta-baja-carbohidratos\"},{\"@type\":\"ListItem\",\"position\":11,\"url\":\"https:\\/\\/www.directoalpaladar.com\\/recetario\\/54-cenas-saludables-que-tener-siempre-mente-para-mejorar-nuestra-dieta\"},{\"@type\":\"ListItem\",\"position\":12,\"url\":\"https:\\/\\/www.directoalpaladar.com\\/recetario\\/bolitas-energeticas-tarta-zanahoria-carrot-cake-energy-balls-receta-horno-azucar-anadido\"},{\"@type\":\"ListItem\",\"position\":13,\"url\":\"https:\\/\\/www.directoalpaladar.com\\/recetas-de-aperitivos\\/cinco-recetas-aperitivo-ricas-vitamina-d-para-combatir-falta-sol-picoteo-finde-invierno\"},{\"@type\":\"ListItem\",\"position\":14,\"url\":\"https:\\/\\/www.directoalpaladar.com\\/recetas-de-sopas-y-cremas\\/sopa-pollo-garbanzos-jengibre-curcuma-receta-super-nutritiva-saludable-para-combatir-frio\"},{\"@type\":\"ListItem\",\"position\":15,\"url\":\"https:\\/\\/www.directoalpaladar.com\\/recetas-de-ensaladas\\/barquitas-endivias-ensalada-pera-nueces-queso-receta-ligera-aperitivo-fresco-saludable\"},{\"@type\":\"ListItem\",\"position\":16,\"url\":\"https:\\/\\/www.directoalpaladar.com\\/gastronomia-en-internet\\/12-recetas-saludables-reconfortantes-para-dieta-despues-navidad-paseo-gastronomia-red\"},{\"@type\":\"ListItem\",\"position\":17,\"url\":\"https:\\/\\/www.directoalpaladar.com\\/recetario\\/101-mejores-recetas-ligeras-para-adelgazar-navidad-sufrir-recuperar-rutina-saludable\"},{\"@type\":\"ListItem\",\"position\":18,\"url\":\"https:\\/\\/www.directoalpaladar.com\\/recetario\\/12-recetas-ligeras-saludables-solo-tres-ingredientes-para-para-adelgazar-matarse-cocina\"},{\"@type\":\"ListItem\",\"position\":19,\"url\":\"https:\\/\\/www.directoalpaladar.com\\/recetario\\/menu-semanal-para-sacar-maximo-partido-a-crock-pot-recetas-sencillas-saludables-para-dieta-despues-navidad\"},{\"@type\":\"ListItem\",\"position\":20,\"url\":\"https:\\/\\/www.directoalpaladar.com\\/recetas-de-legumbres-y-verduras\\/receta-pochas-langostinos-sepia-guiso-legumbres-marinero-facil-saludable-sabrosisimo\"}]}";
		
		actual = jsonBuilder.buildDocumentItemListHtmlInsertable("url", origen);
		assertEquals(expected, actual);
		
	}
}
