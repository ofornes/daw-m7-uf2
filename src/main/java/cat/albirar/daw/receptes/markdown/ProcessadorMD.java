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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;

/**
 * Per a processar el text markdown.
 * @author Octavi Fornés <a href="mailto:ofornes.base.cat">ofornes@base.cat</a>
 * @since 0.0.1
 */
@Component
public class ProcessadorMD {
	@Autowired
	private Parser parser;
	@Autowired
	private HtmlRenderer renderer;
	/**
	 * Converteix el text markdown a HTML.
	 * @param textMarkdown El text en format Markdown
	 * @return El resultat HTML
	 */
	public String markdown2HtmlParagraphWrap(String textMarkdown) {
		Node nd;
		
		nd = parser.parse(textMarkdown);
		return renderer.render(nd);
	}
	/**
	 * Converteix el text markdown a HTML sense el 'wrapper' de paràgraf.
	 * Elimina els tags {@code &lt;p&gt;} i {@code &lt;/p&gt;} de l'inici i fi.
	 * @param textMarkdown El text en format Markdown
	 * @return El resultat HTML
	 */
	public String markdown2Html(String textMarkdown) {
		Node nd;
		String s;
		
		nd = parser.parse(textMarkdown);
		s = renderer.render(nd);
		if(s.startsWith("<p>")) {
			s = s.substring(3);
		}
		if(s.endsWith("</p>")) {
			s = s.substring(0, s.length() - 4);
		} else {
			if(s.endsWith("</p>".concat(System.lineSeparator()))) {
				s = s.substring(0, s.length() - (4 + System.lineSeparator().length()));
			}
		}
		return s;
	}
	/**
	 * Converteix les instruccions (en format Markdown) en concetanació de cada línia d'instrucció amb una barra vertical ({@code |}) de separador.
	 * @param instruccions Les instruccions en format Markdown
	 * @return Les instruccions convertides a HTML i cada línia separada per una barra vertical.
	 */
	public String instruccions2HtmlEnLinies(String instruccions) {
		StringBuilder stb;
		
		stb = new StringBuilder();
		for(String l : particionarPerPunt(instruccions)) {
			stb.append(markdown2Html(l)).append("|");
		}
		return stb.toString();
	}
	/**
	 * Parteix el text indicat en línies als signes de punts trobats. 
	 * @param text El text
	 * @return Cada línia partida pels punts (sense el punt i amb 'trim' de cada linia)
	 */
	public List<String> particionarPerPunt(String text) {
		String [] ar;
		String s;

		s = new String(text);
		if(s.endsWith(".")) {
			s = s.substring(0, s.length()-1);
		}
		ar = s.replaceAll("\\.([A-Z ])", "|$1").split("\\|");
		return Arrays.stream(ar)
							.map(str -> str.trim())
							.collect(Collectors.toList());
		
	}
}
