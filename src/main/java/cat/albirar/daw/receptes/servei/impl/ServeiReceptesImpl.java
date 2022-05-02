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

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cat.albirar.daw.receptes.models.CategoriaPesBean;
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
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String metadadesRecepta(@Min(1) long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ReceptaBean> receptesPerCategoria(@NotBlank String categoria) {
		List<ReceptaBean> r;
		
		LOGGER.debug("Cerco receptes per a la categoria {}", categoria);
		r = repoReceptes.findByCategoria(categoria);
		LOGGER.debug("Trobades {} receptes", r.size());
		return r;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ReceptaBean> receptesPerCategoriaId(@Min(1) long idCategoria) {
		// TODO Auto-generated method stub
		return null;
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
	public List<ReceptaBean> receptesAleatories(@Min(1) @Max(5) int nombre) {
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

}
