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
package cat.albirar.daw.receptes.controladors;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import cat.albirar.daw.receptes.markdown.ProcessadorMD;
import cat.albirar.daw.receptes.models.CategoriaPesBean;
import cat.albirar.daw.receptes.models.KeywordPesBean;
import cat.albirar.daw.receptes.models.ReceptaBean;
import cat.albirar.daw.receptes.servei.IServeiReceptes;

/**
 * Controlador per a les crides de la WEB.
 * 
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 0.0.1
 */
@Controller
public class ControladorWeb {
	@Autowired
	private IServeiReceptes servei;
	@Autowired
	private ProcessadorMD processadorMd;

	private static final String [] MN_CATS = { "Amanides", "Vegetarianes", "Postres"};
	private static final int RECEPTES_RANDOM_HEADER = 5;
	private static final int RECEPTES_RANDOM_CARRUSEL = 8;
	private static final SortedSet<String> CATS = new TreeSet<>(Arrays.asList(MN_CATS));
	/**
	 * Index.
	 * Afegeix el {@link #elementsComuns(ModelAndView) comú} i:
	 * <ul>
	 * <li><strong>receptesCarrusel</strong> amb {@value #RECEPTES_RANDOM_CARRUSEL} {@link ReceptaBean receptes} aleatòries.</li>
	 * </ul>
	 */
	@GetMapping("/")
	public ModelAndView index() {
		return elementsComuns(new ModelAndView("index"))
				.addObject("receptesCarrusel", servei.receptesAleatories(RECEPTES_RANDOM_CARRUSEL))
				;
	}
	/**
	 * Categoria.
	 * Afegeix el {@link #elementsComuns(ModelAndView) comú} i:
	 * <ul>
	 * <li><strong>categoria</strong> amb la informació de la categoria en forma de {@link CategoriaPesBean}.</li>
	 * <li><strong>receptes</strong> amb la llista de totes les {@link ReceptaBean} de la categoria.</li>
	 * </ul>
	 */
	@GetMapping("/categoria/{categoria}")
	public ModelAndView categoria(@PathVariable String categoria) {
		return elementsComuns(new ModelAndView("categoria"))
				.addObject("categoria", servei.categoria(categoria))
				.addObject("receptes", servei.receptesPerCategoria(categoria))
				;
	}
	/**
	 * Keyword.
	 * Afegeix el {@link #elementsComuns(ModelAndView) comú} i:
	 * <ul>
	 * <li><strong>keyword</strong> amb la informació del keyword en forma de {@link KeywordPesBean}.</li>
	 * <li><strong>receptes</strong> amb la llista de totes les {@link ReceptaBean} del keyword.</li>
	 * </ul>
	 */
	@GetMapping("/keyword/{keyword}")
	public ModelAndView keyword(@PathVariable String keyword) {
		return elementsComuns(new ModelAndView("keyword"))
				.addObject("keyword", servei.keyword(keyword))
				.addObject("receptes", servei.receptesPerKeyword(keyword))
				;
	}
	/**
	 * Categoria.
	 * Afegeix el {@link #elementsComuns(ModelAndView) comú} i:
	 * <ul>
	 * <li><strong>recepta</strong> amb la informació de la {@link ReceptaBean} i les instruccions en format HTML segons la plantilla següent:
	 * <pre>
	 *   &lt;li&gt;&lt;span&gt;_index_linia_. &lt;/span&gt; línia d'instrucció&lt;/li&gt;
	 * </pre>
	 * </li>
	 * </ul>
	 * <p>{@code _index_linia_} correspon a un índex que comença en 1 i s'incrementa a cada línia.</p>
	 */
	@GetMapping("/recepta/{idRecepta}")
	public ModelAndView recepta(@PathVariable long idRecepta) {
		ReceptaBean r;
		
		r = servei.receptaPerId(idRecepta);
		r = r.toBuilder()
			.instruccions(processadorMd.instruccions2HtmlEnLinies(r.getInstruccions()))
			.build()
			;
		return elementsComuns(new ModelAndView("recepta"))
				.addObject("recepta", r)
				;
	}
	/**
	 * Monta els elements comuns de qualsevol vista.
	 * <ul>
	 * <li><strong>catsMenu</strong> amb una llista de les categories en forma de {@link CategoriaPesBean}.</li>
	 * <li><strong>catsHead</strong> amb una llista de les categories identificades per {@link #MN_CATS} en forma de {@link CategoriaPesBean}.</li>
	 * <li><strong>receptesRandom</strong> amb una llista de {@value #RECEPTES_RANDOM_HEADER} receptes aleatòries en forma de {@link ReceptaBean} per a mostrar al header.</li>
	 * <li><strong>pagina</strong> amb el nom de la pàgina que s'està muntant (per a 'active' al header).</li>
	 * </ul>
	 * @param model El model amb el {@link ModelAndView#getViewName() nom} de la vista informat 
	 * @return El mateix model amb les dades necessàries (menu, head, etc)
	 */
	private ModelAndView elementsComuns(ModelAndView model) {
		List<CategoriaPesBean> catsHead, catsMenu;
		
		catsMenu = servei.categories();
		catsHead = catsMenu.stream()
				.filter(c -> CATS.contains(c.getNom()))
				.collect(Collectors.toList());
		
		return model
			.addObject("catsMenu", catsMenu)
			.addObject("catsHead", catsHead)
			.addObject("receptesRandom", servei.receptesAleatories(RECEPTES_RANDOM_HEADER))
			.addObject("pagina", model.getViewName());
	}
}
