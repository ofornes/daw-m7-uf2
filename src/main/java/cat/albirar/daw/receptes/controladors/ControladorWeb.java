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

import cat.albirar.daw.receptes.models.CategoriaPesBean;
import cat.albirar.daw.receptes.servei.IServeiReceptes;

/**
 * Controlador per a les crides de la WEB.
 * 
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 0.0.1
 */
@Controller
public class ControladorWeb {
	private static final String [] MN_CATS = { "Amanides", "Vegetarianes", "Postres"};
	@Autowired
	private IServeiReceptes servei;
	
	private static final SortedSet<String> CATS = new TreeSet<>(Arrays.asList(MN_CATS));
	
	@GetMapping("/")
	public ModelAndView index() {
		return muntarEstandard(new ModelAndView("index"));
	}
	@GetMapping("/categoria/{categoria}")
	public ModelAndView categoria(@PathVariable String categoria) {
		return muntarEstandard(new ModelAndView("categoria"))
				.addObject("categoria", servei.categoria(categoria))
				.addObject("receptes", servei.receptesPerCategoria(categoria))
				;
	}
	
	/**
	 * Monta els elements bàsics de qualsevol vista.
	 * @param model El model amb el {@link ModelAndView#getViewName() nom} de la vista informat 
	 * @return El mateix model amb les dades necessàries (menu, head, etc)
	 */
	private ModelAndView muntarEstandard(ModelAndView model) {
		List<CategoriaPesBean> catsHead, catsMenu;
		
		catsMenu = servei.categories();
		catsHead = catsMenu.stream()
				.filter(c -> CATS.contains(c.getNom()))
				.collect(Collectors.toList());
		
		return model.addObject("catsMenu", catsMenu)
			.addObject("catsHead", catsHead)
			.addObject("receptesRandom", servei.receptesAleatories(5))
			.addObject("pagina", model.getViewName());
	}
}
