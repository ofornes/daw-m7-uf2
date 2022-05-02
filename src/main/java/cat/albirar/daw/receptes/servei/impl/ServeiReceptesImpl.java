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

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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
		return repoReceptes.findByCategoria(categoria);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ReceptaBean> receptes() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CategoriaPesBean> categories() {
		// TODO Auto-generated method stub
		return null;
	}

}
