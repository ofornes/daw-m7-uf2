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
package cat.albirar.daw.receptes.repositoris;

import java.util.List;
import java.util.Optional;

import cat.albirar.daw.receptes.models.CategoriaPesBean;
import cat.albirar.daw.receptes.models.IngredientReceptaBean;
import cat.albirar.daw.receptes.models.KeywordPesBean;
import cat.albirar.daw.receptes.models.ReceptaBean;

/**
 * Repositori de receptes.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 0.0.1
 */
public interface IRepoReceptes {
	/**
	 * Cerca la recepta per {@code id}.
	 * @param id L'id de la recepta
	 * @return La {@link ReceptaBean recepta} o {@link Optional#empty()} si no existeix pas
	 */
	public Optional<ReceptaBean> findById(long id);
	/**
	 * Cerca totes les receptes registrades.
	 * @return La llista amb totes les receptes
	 */
	public List<ReceptaBean> findAll();
	/**
	 * Cerca {@code howmany} receptes de manera aleatòria.
	 * @param howmany El nombre de receptes a obtenir
	 * @return La llista amb les receptes obtingudes
	 */
	public List<ReceptaBean> findRandom(int howmany);
	/**
	 * Cerca les receptes associades amb la {@code categoria} indicada.
	 * @param categoria La categoria
	 * @return La llista de receptes associades
	 */
	public List<ReceptaBean> findByCategoria(String categoria);
	/**
	 * Cerca els ingredients de la recepta amb l'{@code id} indicat.
	 * @param id L'identificador de la recepta
	 * @return La llista d'ingredients
	 */
	public List<IngredientReceptaBean> findIngredientsByReceptaId(long id);
	/**
	 * Cerca els <i>keywords</i> de la recepta amb l'{@code id} indicat.
	 * @param id L'identificador de la recepta
	 * @return La llista de keywords
	 */
	public List<String> findKeywordsByReceptaId(long id);
	/**
	 * Cerca la informació del keyword amb el {@code nom} indicat.
	 * @param nom El nom del keyword
	 * @return El {@link KeywordPesBean}
	 */
	public KeywordPesBean findKeywordByNom(String nom);
	/**
	 * Cerca les receptes associades amb el keyword amb el {@code nom} indicat.
	 * @param nom El nom del keyword
	 * @return La llista de receptes associades
	 */
	public List<ReceptaBean> findByKeyword(String nom);
	/**
	 * Cerca totes les categories registrades.
	 * @return La llista
	 */
	public List<CategoriaPesBean> findCategories();
	/**
	 * Retorna la informació de la categoria amb el nom indicat.
	 * @param nom El nom
	 * @return La categoria
	 */
	public CategoriaPesBean findCategoriaByNom(String nom);
}
