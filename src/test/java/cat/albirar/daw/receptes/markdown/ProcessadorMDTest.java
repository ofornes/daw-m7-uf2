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
package cat.albirar.daw.receptes.markdown;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cat.albirar.daw.receptes.ReceptesSaludablesApplication;

/**
 * Provatures del processador MD.
 * @author Octavi Fornés <a href="mailto:ofornes.base.cat">ofornes@base.cat</a>
 * @since 0.0.1
 */
@ContextConfiguration(classes = ReceptesSaludablesApplication.class)
@ExtendWith(SpringExtension.class)
public class ProcessadorMDTest {
	
	@Autowired
	private ProcessadorMD processador;
	
	private static final String TEXT_MD_MOSTRA = "Precalentar el horno a **220ºC con aire si tiene**. Forrar con papel de hornear una bandeja grande. Cortar el brócoli separando los floretes en porciones no mucho más grandes de un bocado, dejando parte de los tallos y conservando el tronco.Es difícil sacar floretes idénticos, pero incluso positivo que tengamos porciones de **distinto tamaño** para obtener diferentes puntos de cocción. Los floretes más grandes habrá que cortarlos por la mitad, atravedando su tallo.Para el tronco, cortar la parte más externa y dura hasta que se vea más **blanco y tierno**, no fibroso. Cortar en bastones de un bocado. Lavar todo bien con agua, con suavidad, y escurrir y secar con un paño limpio.Extender en una sola capa en la bandeja, rociar con un **chorro generoso de aceite de oliva y salpimentar**. Añadir los dientes de ajo pelados enteros, cortados por la mitad o laminados gruesos, según se prefiera dar más o menos sabor. Para ahorrar tiempo se puede aderezar con ajo en polvo. Remover bien con las manos.Hornear **15 minutos**; si el horno no es muy homogéneo, girar la bandeja a mitad del tiempo. Sacar y retirar las piezas más pequeñas. Voltear los más grandes y volver a hornear unos 5 minutos, bajando la potencia a 200ºC. Aliñar con el zumo de medio limón y servir con ralladura de la otra mitad; añadir parmesano rallado si se desea y más pimienta.";
	private static final String TEXT_HTML_MOSTRA = "Precalentar el horno a <strong>220ºC con aire si tiene</strong>. Forrar con papel de hornear una bandeja grande. Cortar el brócoli separando los floretes en porciones no mucho más grandes de un bocado, dejando parte de los tallos y conservando el tronco.Es difícil sacar floretes idénticos, pero incluso positivo que tengamos porciones de <strong>distinto tamaño</strong> para obtener diferentes puntos de cocción. Los floretes más grandes habrá que cortarlos por la mitad, atravedando su tallo.Para el tronco, cortar la parte más externa y dura hasta que se vea más <strong>blanco y tierno</strong>, no fibroso. Cortar en bastones de un bocado. Lavar todo bien con agua, con suavidad, y escurrir y secar con un paño limpio.Extender en una sola capa en la bandeja, rociar con un <strong>chorro generoso de aceite de oliva y salpimentar</strong>. Añadir los dientes de ajo pelados enteros, cortados por la mitad o laminados gruesos, según se prefiera dar más o menos sabor. Para ahorrar tiempo se puede aderezar con ajo en polvo. Remover bien con las manos.Hornear <strong>15 minutos</strong>; si el horno no es muy homogéneo, girar la bandeja a mitad del tiempo. Sacar y retirar las piezas más pequeñas. Voltear los más grandes y volver a hornear unos 5 minutos, bajando la potencia a 200ºC. Aliñar con el zumo de medio limón y servir con ralladura de la otra mitad; añadir parmesano rallado si se desea y más pimienta.";
	private static final String [] TEXT_HTML_LINIES_MOSTRA = {
			"Precalentar el horno a <strong>220ºC con aire si tiene</strong>"
			,"Forrar con papel de hornear una bandeja grande"
			,"Cortar el brócoli separando los floretes en porciones no mucho más grandes de un bocado, dejando parte de los tallos y conservando el tronco"
			,"Es difícil sacar floretes idénticos, pero incluso positivo que tengamos porciones de <strong>distinto tamaño</strong> para obtener diferentes puntos de cocción"
			,"Los floretes más grandes habrá que cortarlos por la mitad, atravedando su tallo"
			,"Para el tronco, cortar la parte más externa y dura hasta que se vea más <strong>blanco y tierno</strong>, no fibroso"
			,"Cortar en bastones de un bocado"
			,"Lavar todo bien con agua, con suavidad, y escurrir y secar con un paño limpio"
			,"Extender en una sola capa en la bandeja, rociar con un <strong>chorro generoso de aceite de oliva y salpimentar</strong>"
			,"Añadir los dientes de ajo pelados enteros, cortados por la mitad o laminados gruesos, según se prefiera dar más o menos sabor"
			,"Para ahorrar tiempo se puede aderezar con ajo en polvo"
			,"Remover bien con las manos"
			,"Hornear <strong>15 minutos</strong>; si el horno no es muy homogéneo, girar la bandeja a mitad del tiempo"
			,"Sacar y retirar las piezas más pequeñas"
			,"Voltear los más grandes y volver a hornear unos 5 minutos, bajando la potencia a 200ºC"
			,"Aliñar con el zumo de medio limón y servir con ralladura de la otra mitad; añadir parmesano rallado si se desea y más pimienta"
			,""
	};
	
	private static final String TEXT_PUNTS = "Prova de Línies. Amb punts.I [altres elements](https://www.google.com). De proves.";
	private static final String [] LINIES_PUNTS = {
			"Prova de Línies"
			, "Amb punts"
			, "I [altres elements](https://www.google.com)"
			, "De proves"
	};

	@Test
	public void whenMarkdown2HtmlWithNoMD_Then_SameStringIsGet() {
		String plantilla, expected, s;
		
		plantilla = "Aquest és un text normal";
		expected = plantilla;
		s = processador.markdown2Html(plantilla);
		assertEquals(expected, s);
	}

	@Test
	public void whenMarkdown2HtmlParagraphWrapWithNoMD_Then_SameStringIsGet() {
		String plantilla, expected, s;
		
		plantilla = "Aquest és un text normal";
		expected = "<p>".concat(plantilla).concat("</p>").concat(System.lineSeparator());
		s = processador.markdown2HtmlParagraphWrap(plantilla);
		assertEquals(expected, s);
	}

	@Test
	public void whenMarkdown2HtmlWithMD_Then_HtmlStringIsGet() {
		String s;
		
		s = processador.markdown2Html(TEXT_MD_MOSTRA);
		assertEquals(TEXT_HTML_MOSTRA, s);
	}
	
	@Test
	public void whenParticionarPerPuntWithMultiPoint_Then_LinesAreGet() {
		List<String> s1;
		
		s1 = processador.particionarPerPunt(TEXT_PUNTS);
		assertNotNull(s1);
		assertEquals(LINIES_PUNTS.length, s1.size());
		assertArrayEquals(LINIES_PUNTS, s1.toArray(new String[] {}));
		
	}

	@Test
	public void whenInstruccions2HtmlEnLiniesWithMDLines_Then_FormatedLinesIsGet() {
		String expected, s;
		
		s = processador.instruccions2HtmlEnLinies(TEXT_MD_MOSTRA);
		expected = String.join("|", TEXT_HTML_LINIES_MOSTRA);
		assertEquals(expected, s);
	}
}
