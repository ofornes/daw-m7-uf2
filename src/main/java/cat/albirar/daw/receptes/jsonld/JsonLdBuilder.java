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
package cat.albirar.daw.receptes.jsonld;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Component per a generar json-ld.
 * @author Octavi Fornés <a href="mailto:octavi@fornes.cat">octavi@fornes.cat</a>
 * @since 0.0.1
 */
@Component
@Scope("prototype")
public class JsonLdBuilder {
	private StringBuilder builder;
	
	/**
	 * Context per defecte.
	 */
	public static final String SCHEMA_CONTEXT = "http://schema.org/";
	/** P0DT1H14M */
	public static final String DURATION_ISO_FORMAT_PATTERN = "'P0DT'H'H'm'M'";
	private static final String NOM_CONTEXT = "@context";
	private static final String NOM_TIPUS = "@type";
	/**
	 * Control d'equilibri de claus: &lbrace; i &rbrace;.
	 */
	private int braces;
	/**
	 * Control d'equilibri de claudators: &lbrack; i &rbrack;.
	 */
	private int brackets;
	/**
	 * Control de separador de propietats (coma).
	 */
	private boolean appendSeparator;
	/**
	 * Indicador de document iniciat o no.
	 */
	private boolean documentStarted;
	/**
	 * Format per a data {@code YYYY-MM-DD}.
	 */
	private DateTimeFormatter dataFormat = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(DateTimeFormatter.ISO_LOCAL_DATE)
            .optionalStart()
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT)
            .withChronology(IsoChronology.INSTANCE)
            .withZone(ZoneId.from(ZoneOffset.UTC));
	/**
	 * Format per a data i hora {@code YYYY-MM-DDThh:mm:ss+00:00}.
	 */
	private DateTimeFormatter dataHoraFormat = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.from(ZoneOffset.UTC));
	/**
	 * Genera un inici de document.
	 * <pre>
	 * {
	 *    "@context":"_context_",
	 *    "@type":"_type_"
	 * </pre>
	 * @param context El context
	 * @param type El tipus, com ara {@code Recipe}
	 */
	public JsonLdBuilder startDocument(String context, String type) {
		if(documentStarted) {
			throw new MalformedDocumentException("El document ja estava iniciat!");
		}
		documentStarted = true;
		startBrace();
		addProperty(NOM_CONTEXT, SCHEMA_CONTEXT);
		addType(type);
		return this;
	}
	/**
	 * Genera un inici de document a l'esquema per defecte: {@value #SCHEMA_CONTEXT}.
	 * <pre>
	 * {
	 *    "@context":{@value #SCHEMA_CONTEXT},
	 *    "@type":"_type_"
	 * </pre>
	 * @param type El tipus, com ara {@code Recipe}
	 */
	public JsonLdBuilder startDocument(String type) {
		return startDocument(SCHEMA_CONTEXT, type);
	}
	/**
	 * Genera un inici d'objecte com a propietat.
	 * <pre>
	 * "propertyName":{
	 *    "@type":"tipus"
	 * </pre>
	 * @param propertyName El nom de la propietat
	 * @param type El tipus d'objecte al context de l'objecte actual (que pot ser el document)
	 */
	public JsonLdBuilder startPropertyObject(String propertyName, String type) {
		checkPropertyOrElementSeparator();
		builder.append("\"").append(propertyName).append("\":");
		startBrace();
		addType(type);
		return this;
	}
	/**
	 * Afegeix una propietat en format text.
	 * @param name el nom de la propietat
	 * @param value El valor
	 */
	public JsonLdBuilder addProperty(String name, String value) {
		checkPropertyOrElementSeparator();
		builder.append("\"").append(name).append("\":\"").append(value).append("\"");
		appendSeparator = true;
		return this;
	}
	/**
	 * Construeix un document 'HTML INSERTABLE' format per una sola propietat de tipus {@code ItemList}.
	 * <p><strong>ATENCIÓ!!!!</strong>Implica un {@link #resetBuilder()}</p>
	 * El document construit és del tipus:
	 * <pre>
	 * {
	 *   "@context":"http://schema.org/", 
	 *   "@type":"ItemList", 
	 *   "itemListElement":[
	 *     {
	 *       "@type":"ListItem", 
	 *       "position":1, 
	 *       "_listItemProperty_":"_values[0]_"
	 *     }, 
	 *     {
	 *       "@type":"ListItem", 
	 *       "position":2, 
	 *       "_listItemProperty_":"_values[1]_"
	 *     }, 
	 * ...
	 *     {
	 *       "@type":"ListItem", 
	 *       "position":n, 
	 *       "_listItemProperty_":"_values[n-1]_"
	 *     }
	 *   ]
	 * }
	 * </pre>
	 * @param listItemProperty El nom de la propietat de cada element de l'array
	 * @param values Els valors de la {@code listItemProperty} de cada entrada de la llista
	 * @return El resultat
	 */
	public String buildDocumentItemListHtmlInsertable(String listItemProperty, Collection<String> values) {
		return htmlInsertable(unicodeEscape(buildDocumentItemList(listItemProperty, values)));
	}
	/**
	 * Construeix un document format per una sola propietat de tipus {@code ItemList}.
	 * <p><strong>ATENCIÓ!!!!</strong>Implica un {@link #resetBuilder()}</p>
	 * El document construit és del tipus:
	 * <pre>
	 * {
	 *   "@context":"http://schema.org/", 
	 *   "@type":"ItemList", 
	 *   "itemListElement":[
	 *     {
	 *       "@type":"ListItem", 
	 *       "position":1, 
	 *       "_listItemProperty_":"_values[0]_"
	 *     }, 
	 *     {
	 *       "@type":"ListItem", 
	 *       "position":2, 
	 *       "_listItemProperty_":"_values[1]_"
	 *     }, 
	 * ...
	 *     {
	 *       "@type":"ListItem", 
	 *       "position":n, 
	 *       "_listItemProperty_":"_values[n-1]_"
	 *     }
	 *   ]
	 * }
	 * </pre>
	 * @param listItemProperty El nom de la propietat de cada element de l'array
	 * @param values Els valors de la {@code listItemProperty} de cada entrada de la llista
	 * @return El resultat
	 */
	public String buildDocumentItemList(String listItemProperty, Collection<String> values) {
		int pos;
		
		resetBuilder();
		startDocument("ItemList");
		addProperty("numberOfItems", values.size());
		checkPropertyOrElementSeparator();
		builder.append("\"itemListElement\":");
		startBracket();
		pos = 1;
		for(String value: values) {
			startBrace();
			addType("ListItem");
			addProperty("position", pos);
			pos++;
			addProperty(listItemProperty, value);
			endBrace();
		}
		endBracket();
		return endDocument();
	}
	/**
	 * Afegeix una propietat en format d'array de text.
	 * @param name el nom de la propietat
	 * @param value El valor
	 */
	public JsonLdBuilder addPropertyArray(String name, Collection<String> value) {
		boolean sep;
		
		checkPropertyOrElementSeparator();
		builder.append("\"").append(name).append("\":");
		startBracket();
		sep = false;
		for(String s : value) {
			builder.append(sep ? "," : "").append("\"").append(s).append("\"");
			sep = true;
		}
		endBracket();
		appendSeparator = true;
		return this;
	}
	/**
	 * Afegeix una propietat en format d'array de text.
	 * @param name el nom de la propietat
	 * @param value El valor
	 */
	public JsonLdBuilder addPropertyArray(String name, String [] value) {
		return addPropertyArray(name, Arrays.asList(value));
	}
	/**
	 * Afegeix una propietat int en format text.
	 * @param name el nom de la propietat
	 * @param value El valor
	 */
	public JsonLdBuilder addProperty(String name, int value) {
		checkPropertyOrElementSeparator();
		builder.append("\"").append(name).append("\":").append(value);
		appendSeparator = true;
		return this;
	}
	/**
	 * Afegeix una propietat en el format data adient.
	 * @param name el nom de la propietat
	 * @param value El valor
	 */
	public JsonLdBuilder addPropertyDate(String name, TemporalAccessor value) {
		return addProperty(name, dataFormat.format(value));
	}
	/**
	 * Afegeix una propietat en el format data-hora adient.
	 * @param name el nom de la propietat
	 * @param value El valor
	 */
	public JsonLdBuilder addPropertyDateTime(String name, TemporalAccessor value) {
		return addProperty(name, dataHoraFormat.format(value));
	}
	/**
	 * Afegeix una propietat en el format data-hora adient.
	 * @param name el nom de la propietat
	 * @param value El valor
	 */
	public JsonLdBuilder addPropertyDuration(String name, Duration value) {
		return addProperty(name, DurationFormatUtils.formatDuration(value.toMillis(), DURATION_ISO_FORMAT_PATTERN));
	}
	/**
	 * Tanca l'objecte actual.
	 * @return
	 */
	public JsonLdBuilder endObject() {
		if(!documentStarted) {
			throw new MalformedDocumentException("No s'ha iniciat el document!");
		}
		endBrace();
		return this;
	}
	/**
	 * Conclou la construcció el document i el torna a inicialitzar.
	 * <ul>
	 * <li><strong>ATENCIÓ!!!</strong> aquest constructor resta inicialitzat un cop torni d'aquesta funció.</li>
	 * <li>Automàticament tanca qualsevol claudator pendent de tancar</li>
	 * </ul>
	 * @return El document construit
	 */
	public String endDocument() {
		String s;
		
		if(!documentStarted) {
			throw new MalformedDocumentException("No s'ha iniciat el document!");
		}
		
		while(braces > 0) {
			endBrace();
		}
		s = builder.toString();
		resetBuilder();
		return s;
	}
	/**
	 * Conclou la construcció del document i el torna a inicialitzar.
	 * <ul>
	 * <li><strong>ATENCIÓ!!!</strong> aquest constructor resta inicialitzat un cop torni d'aquesta funció.</li>
	 * <li>Automàticament tanca qualsevol claudator pendent de tancar</li>
	 * </ul>
	 * @return El document construit amb els caràcters internacionals en format unicode (\\uxxx) i les barres (/) escapades (\\/)
	 */
	public String endDocumentHtmlInsertable() {
		return htmlInsertable(unicodeEscape(endDocument()));
	}
	/**
	 * Verifica si s'ha d'afegir comma o no.
	 */
	private void checkPropertyOrElementSeparator() {
		if(!documentStarted) {
			throw new MalformedDocumentException("El document no està iniciat!");
		}
		if(appendSeparator) {
			builder.append(",");
			appendSeparator=false;
		}
	}
	
	private void addType(String tipus) {
		checkPropertyOrElementSeparator();
		addProperty(NOM_TIPUS, tipus);
	}
	/**
	 * Obre una clau.
	 */
	private void startBrace() {
		checkPropertyOrElementSeparator();
		builder.append("{");
		braces++;
		appendSeparator = false;
	}
	/**
	 * Obre un claudàtor.
	 */
	private void startBracket() {
		builder.append("[");
		brackets++;
		appendSeparator = false;
	}
	/**
	 * Tanca amb una clau <strong>NOMÉS</strong> si està obert.
	 * @return El nombre de claus pendents de tancar
	 */
	private int endBrace() {
		if(braces > 0) {
			builder.append("}");
			braces--;
			appendSeparator = true;
		}
		if(braces == 0) {
			documentStarted=false;
		}
		return braces;
	}
	/**
	 * Tanca amb un claudàtor <strong>NOMÉS</strong> si està obert.
	 * @return El nombre de claudators pendents de tancar
	 */
	private int endBracket() {
		if(brackets > 0) {
			builder.append("]");
			brackets--;
			appendSeparator = true;
		}
		return brackets;
	}
	/**
	 * Fa un reset del builder, retornant a l'estat inicial abans d'iniciar el document.
	 */
	@PostConstruct
	public JsonLdBuilder resetBuilder() {
		builder = new StringBuilder();
		braces = 0;
		brackets = 0;
		appendSeparator = false;
		documentStarted = false;
		return this;
	}
	private static final char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	/**
	 * Fa escape unicode del text {@code source} per a convertir els caracters 'extesos' en unicode (només caràcters de fora del primer bloc).
	 * @param source El text
	 * @return El text convertit
	 */
    public static String unicodeEscape(String source) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            if ((c >> 7) > 0) {
                sb.append("\\u");
                sb.append(hexChar[(c >> 12) & 0xF]); // append the hex character for the left-most 4-bits
                sb.append(hexChar[(c >> 8) & 0xF]); // hex for the second group of 4-bits from the left
                sb.append(hexChar[(c >> 4) & 0xF]); // hex for the third group
                sb.append(hexChar[c & 0xF]); // hex for the last group, e.g., the right most 4-bits
            } else {
                sb.append(c);/*from w ww .j  a va  2s. c o  m*/
            }
        }
        return sb.toString();
    }
    /**
     * Fa el text 'html insertable', tot escapant les barres {@code / } amb {@code \\ }. 
     * @param source El text a canviar
     * @return El text canviat
     */
    public static String htmlInsertable(String source) {
    	return source.replace("/", "\\/");
    }
}
