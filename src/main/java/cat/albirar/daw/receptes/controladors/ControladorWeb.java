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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import cat.albirar.daw.receptes.markdown.ProcessadorMD;
import cat.albirar.daw.receptes.models.CategoriaPesBean;
import cat.albirar.daw.receptes.models.ComentariBean;
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
	private static final String NOM_ATRIBUT_RECEPTES = "receptes";
	private static final String NOM_ATRIBUT_RECEPTES_CARRUSEL = "receptesCarrusel";
	private static final String NOM_ATRIBUT_RECEPTA = "recepta";
	private static final String NOM_ATRIBUT_RECEPTES_RANDOM = "receptesRandom";
	
	private static final String [] ATRIBUTS_RECEPTES = {
			NOM_ATRIBUT_RECEPTES, NOM_ATRIBUT_RECEPTES_CARRUSEL, NOM_ATRIBUT_RECEPTA, NOM_ATRIBUT_RECEPTES_RANDOM
	};
	
	/**
	 * Index.
	 * Afegeix el {@link #afegirElementsComuns(Model, String) comú} i:
	 * <ul>
	 * <li><strong>receptesCarrusel</strong> amb {@value #RECEPTES_RANDOM_CARRUSEL} {@link ReceptaBean receptes} aleatòries.</li>
	 * </ul>
	 */
	@GetMapping("/")
	public String index(Model model) {
		afegirElementsComuns(model, "index");
		model.addAttribute(NOM_ATRIBUT_RECEPTES_CARRUSEL, servei.receptesAleatories(RECEPTES_RANDOM_CARRUSEL));
		afegirMetadades(model);
		extreureIAfegirKeywords(model);
		return "index";
	}
	/**
	 * Categoria.
	 * Afegeix el {@link #afegirElementsComuns(Model, String) comú} i:
	 * <ul>
	 * <li><strong>categoria</strong> amb la informació de la categoria en forma de {@link CategoriaPesBean}.</li>
	 * <li><strong>receptes</strong> amb la llista de totes les {@link ReceptaBean} de la categoria.</li>
	 * </ul>
	 */
	@GetMapping("/categoria/{categoria}")
	public String categoria(@PathVariable String categoria, Model model) {
		afegirElementsComuns(model, "categoria");
		model.addAttribute("categoria", servei.categoria(categoria))
			.addAttribute(NOM_ATRIBUT_RECEPTES, servei.receptesPerCategoria(categoria))
			;
		afegirMetadades(model);
		extreureIAfegirKeywords(model);
		return "categoria";
	}
	/**
	 * Keyword.
	 * Afegeix el {@link #afegirElementsComuns(Model, String) comú} i:
	 * <ul>
	 * <li><strong>keyword</strong> amb la informació del keyword en forma de {@link KeywordPesBean}.</li>
	 * <li><strong>receptes</strong> amb la llista de totes les {@link ReceptaBean} del keyword.</li>
	 * </ul>
	 */
	@GetMapping("/keyword/{keyword}")
	public String keyword(@PathVariable String keyword, Model model) {
		afegirElementsComuns(model, "keyword");
		model.addAttribute("keyword", servei.keyword(keyword))
			.addAttribute(NOM_ATRIBUT_RECEPTES, servei.receptesPerKeyword(keyword))
			;
		afegirMetadades(model);
		extreureIAfegirKeywords(model);
		return "keyword";
	}
	/**
	 * Recepta.
	 * Afegeix el {@link #afegirElementsComuns(Model, String) comú} i:
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
	public String recepta(@PathVariable long idRecepta, Model model) {
		carregarRecepta(idRecepta, model);
		extreureIAfegirKeywords(model);
		return "recepta";
	}
	/**
	 * Get del formulari d'afegir comentari, dins de la recepta.
	 * Només afegeix el model de l'objecte enllaçat amb el formulari, per tal de rebre'ls al post.
	 * @param idRecepta L'id de la recepta, per a construir el comentari
	 * @param model El model MVC
	 * @return El nom del template de recepta
	 */
	@GetMapping("/recepta/{idRecepta}/commentari")
	public String comentariGET(@PathVariable long idRecepta, Model model) {
		afegirBindComentari(idRecepta, model);
		extreureIAfegirKeywords(model);
		return "recepta";
	}
	/**
	 * Post de crear comentari.
	 * Afegeix el comentari i torna a carregar la pàgina de la recepta, la original, és a dir, sense el path '/comentari'
	 * @param comentari El comentari a crear
	 * @param idRecepta L'id de la recepta
	 * @param model El model MVC
	 * @return redirigeix a '/recepta/{idRecepta}'
	 */
	@PostMapping("/recepta/{idRecepta}/commentari")
	public String comentari(@ModelAttribute("comentari") ComentariBean comentari, @PathVariable long idRecepta, Model model) {
		servei.addComentari(comentari);
		carregarRecepta(idRecepta, model);
		extreureIAfegirKeywords(model);
		return String.format("redirect:/recepta/%d", idRecepta);
	}
	/**
	 * Carrega la recepta indicada i afegeix la resta d'atributs al model.
	 * @param idRecepta L'id de la recepta a carregar
	 * @param model El model MVC
	 * @return El model
	 */
	private Model carregarRecepta(long idRecepta, Model model) {
		ReceptaBean r;
		
		r = servei.receptaPerId(idRecepta);
		r = r.toBuilder()
			.instruccions(processadorMd.instruccions2HtmlEnLinies(r.getInstruccions()))
			.build()
			;
		afegirBindComentari(idRecepta, model);
		model.addAttribute(NOM_ATRIBUT_RECEPTA, r);
		afegirElementsComuns(model, "recepta");
		afegirMetadades(model);
		extreureIAfegirKeywords(model);
		return model;
	}
	/**
	 * Afegeix l'objecte per a assignar les propietats del formulari de desar comentari.
	 * @param idRecepta L'id de la recepta (per a associar amb el comentari)
	 * @param model El model MVC
	 * @return El model
	 */
	private Model afegirBindComentari(long idRecepta, Model model) {
		return model.addAttribute("comentari", ComentariBean.builder().idRecepta(idRecepta).build());
	}
	/**
	 * Monta els elements comuns de qualsevol vista.
	 * <ul>
	 * <li><strong>catsMenu</strong> amb una llista de les categories en forma de {@link CategoriaPesBean}.</li>
	 * <li><strong>catsHead</strong> amb una llista de les categories identificades per {@link #MN_CATS} en forma de {@link CategoriaPesBean}.</li>
	 * <li><strong>receptesRandom</strong> amb una llista de {@value #RECEPTES_RANDOM_HEADER} receptes aleatòries en forma de {@link ReceptaBean} per a mostrar al header.</li>
	 * <li><strong>pagina</strong> amb el nom de la pàgina que s'està muntant (per a 'active' al header).</li>
	 * </ul>
	 * @param model El model
	 * @param vista El nom de la vista (per a canviar estils en els menus per a indicar actiu) 
	 * @return El mateix model amb les dades necessàries (menu, head, etc)
	 */
	private Model afegirElementsComuns(Model model, String vista) {
		List<CategoriaPesBean> catsHead, catsMenu;
		
		catsMenu = servei.categories();
		catsHead = catsMenu.stream()
				.filter(c -> CATS.contains(c.getNom()))
				.collect(Collectors.toList());
		
		return model
			.addAttribute("pagina", vista)
			.addAttribute("catsMenu", catsMenu)
			.addAttribute("catsHead", catsHead)
			.addAttribute(NOM_ATRIBUT_RECEPTES_RANDOM, servei.receptesAleatories(RECEPTES_RANDOM_HEADER));
	}
	/**
	 * Obté els keywords dels diferents atributs que en tenen per a afegir-lo a l'atribut 'keywordList'.
	 * @param model el model, d'on extreure les dades
	 * @return El model, amb l'atribut de keywordList afegit
	 */
	@SuppressWarnings("unchecked")
	private Model extreureIAfegirKeywords(Model model) {
		Object o;
		ReceptaBean r;
		Collection<?> cl;
		Collection<ReceptaBean> rs;
		SortedSet<String> keywords;
		
		keywords = Collections.synchronizedSortedSet(new TreeSet<>());
		for(String atribut : ATRIBUTS_RECEPTES) {
			o = model.getAttribute(atribut);
			if(o != null) {
				if(ReceptaBean.class.isAssignableFrom(o.getClass())) {
					r = (ReceptaBean)o;
					keywords.addAll(r.getKeywords());
				} else {
					if(Collection.class.isAssignableFrom(o.getClass())) {
						cl = (Collection<?>)o;
						if(ReceptaBean.class.isAssignableFrom(cl.iterator().next().getClass())) {
							rs = (Collection<ReceptaBean>)o;
							for(ReceptaBean rc : rs) {
								keywords.addAll(rc.getKeywords());
							}
						}
					}
				}
			}
		}
		model.addAttribute("keywordList",String.join(", ", keywords));
		return model;
	}
	/**
	 * Cerca als atributs del model MVC tots aquells que generen metadades (Recipe o ListItems) i els genera.
	 * @param model El model MVC
	 * @return El model
	 */
	@SuppressWarnings("unchecked")
	private Model afegirMetadades(Model model) {
		Map<String, Object> map;
		Collection<ReceptaBean> receptes;
		Collection<?> co;
		ReceptaBean recepta;
		String s;
		List<String> metadades;
		List<ReceptaBean> lrecs;

		map = model.asMap();
		lrecs = Collections.synchronizedList(new ArrayList<>());
		metadades = Collections.synchronizedList(new ArrayList<>());
		for(Object o: map.values()) {
			if(Collection.class.isAssignableFrom(o.getClass())) {
				co = (Collection<?>)o;
				if(!co.isEmpty() && ReceptaBean.class.isAssignableFrom(co.iterator().next().getClass())) {
					// Possible itemlist
					try {
						receptes = (Collection<ReceptaBean>) o;
						// Afegir a la llista per a fer itemlist al final
						lrecs.addAll(receptes);
					} catch (ClassCastException e) {
						// No és llista de receptes
					}
				}
			} else {
				if(ReceptaBean.class.isAssignableFrom(o.getClass())) {
					// Recipe
					recepta = (ReceptaBean)o;
					s = servei.produirJsonLd(r -> MvcUriComponentsBuilder.fromMethodName(ControladorWeb.class, "recepta", r.getId(), new ConcurrentModel()).build().encode().toString(), recepta);
					metadades.add(s);
				}
			}
		}
		// Item list
		if(!lrecs.isEmpty()) {
			// Item list
			s = servei.produirJsonLd(r -> MvcUriComponentsBuilder.fromMethodName(ControladorWeb.class, "recepta", r.getId(), new ConcurrentModel()).build().encode().toString(), lrecs);
			metadades.add(s);
		}
		
		model.addAttribute("metadades", metadades);
		return model;
	}
}
