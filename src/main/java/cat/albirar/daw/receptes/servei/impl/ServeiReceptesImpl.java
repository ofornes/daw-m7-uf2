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
package cat.albirar.daw.receptes.servei.impl;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cat.albirar.daw.receptes.jsonld.JsonLdBuilder;
import cat.albirar.daw.receptes.jsonld.constants.ConstantsElementsPerson;
import cat.albirar.daw.receptes.jsonld.constants.ConstantsElementsRecipe;
import cat.albirar.daw.receptes.markdown.ProcessadorMD;
import cat.albirar.daw.receptes.models.CategoriaPesBean;
import cat.albirar.daw.receptes.models.ComentariBean;
import cat.albirar.daw.receptes.models.KeywordPesBean;
import cat.albirar.daw.receptes.models.ReceptaBean;
import cat.albirar.daw.receptes.repositoris.IRepoReceptes;
import cat.albirar.daw.receptes.servei.IServeiReceptes;

/**
 * Implementació del {@link IServeiReceptes servei de receptes}.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 0.0.1
 */
@Service
@Transactional(readOnly = true)
public class ServeiReceptesImpl implements IServeiReceptes {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServeiReceptesImpl.class);
	
	@Autowired
	private IRepoReceptes repoReceptes;
	@Autowired
	private JsonLdBuilder jsonBuilder;
	
	@Autowired
	private ProcessadorMD processadorMd;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String produirJsonLd(@NotNull Function<ReceptaBean, String> fnUrl, @NotNull ReceptaBean recepta) {
		
		jsonBuilder.startDocument("Recipe")
			.addProperty(ConstantsElementsRecipe.NAME, recepta.getNom())
			.addPropertyDate(ConstantsElementsRecipe.DATEPUBLISHED, recepta.getPublicacio())
			.startPropertyObject(ConstantsElementsRecipe.AUTHOR, "Person")
			.addProperty(ConstantsElementsPerson.NAME, recepta.getAutor())
			.endObject()
			.addProperty(ConstantsElementsRecipe.DESCRIPTION, recepta.getDescripcio())
			.addProperty(ConstantsElementsRecipe.IMAGE, recepta.getUrlImatge())
			.addPropertyDuration(ConstantsElementsRecipe.TOTALTIME, recepta.getTempsTotal())
			.addPropertyDuration(ConstantsElementsRecipe.PREPTIME, recepta.getTempsPreparacio())
			.addProperty(ConstantsElementsRecipe.RECIPEYIELD, recepta.getNombreServeis())
			.addProperty(ConstantsElementsRecipe.RECIPECATEGORY, recepta.getCategoria())
			.addProperty(ConstantsElementsRecipe.KEYWORDS, String.join(", ", recepta.getKeywords()))
			.addProperty(ConstantsElementsRecipe.RECIPEINSTRUCTIONS, processadorMd.markdown2Html(String.join(". ", recepta.getInstruccions().split("\\|"))))
			.addProperty(ConstantsElementsRecipe.URL, fnUrl.apply(recepta))
			;
		jsonBuilder.addPropertyArray(ConstantsElementsRecipe.RECIPEINGREDIENT, 
				recepta.getIngredients().stream()
				.map(i -> i.convertirAJsonLdText())
				.collect(Collectors.toList()))
				;
				
		// Instruccions
		if(recepta.getTempsCoccio().isPresent()) {
			jsonBuilder.addPropertyDuration(ConstantsElementsRecipe.COOKTIME, recepta.getTempsCoccio().get());
		}
		if(recepta.getCuina().isPresent()) {
			jsonBuilder.addProperty(ConstantsElementsRecipe.RECIPECUISINE, recepta.getCuina().get());
		}
		
		return jsonBuilder.endDocumentHtmlInsertable();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String produirJsonLd(@NotNull Function<ReceptaBean, String> fnUrl, @NotNull Collection<ReceptaBean> receptes) {
		return jsonBuilder.resetBuilder()
			.buildDocumentItemListHtmlInsertable("url", receptes.stream().map(r -> fnUrl.apply(r)).collect(Collectors.toList()));
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReceptaBean receptaPerId(@Min(1) long id) {
		return repoReceptes.findById(id).get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ReceptaBean> receptesPerCategoria(@NotBlank String categoria) {
		List<ReceptaBean> r;
		
		LOGGER.debug("Cerco receptes per a la categoria {}", categoria);
		r = repoReceptes.findByCategoria(categoria);
		LOGGER.debug("Trobades {} receptes a la categoria {}", r.size(), categoria);
		return r;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ReceptaBean> receptesPerKeyword(@NotBlank String keyword) {
		List<ReceptaBean> r;
		
		LOGGER.debug("Cerco receptes pel keyword {}", keyword);
		r = repoReceptes.findByKeyword(keyword);
		LOGGER.debug("Trobades {} receptes a keyword {}", r.size(), keyword);
		return r;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategoriaPesBean categoria(String nom) {
		return repoReceptes.findCategoriaByNom(nom);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public KeywordPesBean keyword(String nom) {
		return repoReceptes.findKeywordByNom(nom);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ReceptaBean> receptesAleatories(@Min(1) @Max(10) int nombre) {
		return repoReceptes.findRandom(nombre);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ReceptaBean> receptes() {
		return repoReceptes.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CategoriaPesBean> categories() {
		return repoReceptes.findCategories();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addComentari(ComentariBean comentari) {
		repoReceptes.addComentari(comentari);
	}
}
