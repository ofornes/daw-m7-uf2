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
package cat.albirar.daw.receptes.servei;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

import cat.albirar.daw.receptes.models.CategoriaPesBean;
import cat.albirar.daw.receptes.models.ReceptaBean;

/**
 * Servei per a obtenir dades de receptes.
 * Obté les dades i les retorna en format json segons l'especificació de google.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 0.0.1
 */
@Validated
public interface IServeiReceptes {
	/**
	 * Obté les metadades de la recepte amb l'{@code id} indicat.
	 * @param id L'id de la recepta
	 * @return Les metadades en format json
	 */
	public String metadadesRecepta(@Min(1) long id);
	/**
	 * Cerca les receptes per categoria.
	 * @param categoria
	 * @return
	 */
	public List<ReceptaBean> receptesPerCategoria(@NotBlank String categoria);
	/**
	 * Cerca totes les receptes.
	 * @return Les receptes
	 */
	public List<ReceptaBean> receptes();
	/**
	 * Retorna la llista de categories
	 * @return La llista amb totes les categories
	 */
	public List<CategoriaPesBean> categories();
}
