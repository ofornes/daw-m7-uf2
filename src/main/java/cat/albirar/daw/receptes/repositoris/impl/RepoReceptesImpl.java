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
package cat.albirar.daw.receptes.repositoris.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cat.albirar.daw.receptes.models.CategoriaPesBean;
import cat.albirar.daw.receptes.models.IngredientReceptaBean;
import cat.albirar.daw.receptes.models.ReceptaBean;
import cat.albirar.daw.receptes.repositoris.IRepoReceptes;
import cat.albirar.daw.receptes.repositoris.mappers.ConstantsSql;
import cat.albirar.daw.receptes.repositoris.mappers.ReceptaBeanSimpleMapper;

/**
 * Implementació del {@link IRepoReceptes repositori de receptes}.
 * 
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 0.0.1
 */
@Repository
@Transactional(readOnly = true)
public class RepoReceptesImpl implements IRepoReceptes {
	/**
	 * SQL per a {@link #findById(long)}.
	 */
	private static final String SQL_FIND_BY_ID = "SELECT *"
			+ " FROM " + ReceptaBeanSimpleMapper.VISTA
			+ " WHERE " + ReceptaBeanSimpleMapper.COL_ID + "=:" + ReceptaBeanSimpleMapper.COL_ID
			;
	/**
	 * SQL per a {@link #findAll()}.
	 */
	private static final String SQL_FIND_ALL = "SELECT * "
			+ " FROM " + ReceptaBeanSimpleMapper.VISTA
			+ " ORDER BY "
			+ ReceptaBeanSimpleMapper.COL_NOM
			;
	/**
	 * SQL per a {@link #findByCategoria(String)}.
	 */
	private static final String SQL_FIND_BY_CAT = "SELECT *"
			+ " FROM " + ReceptaBeanSimpleMapper.VISTA
			+ " WHERE " + ReceptaBeanSimpleMapper.COL_FK_ID_CATEGORIA + "=:" + ReceptaBeanSimpleMapper.COL_FK_ID_CATEGORIA
			+ " ORDER BY " + ReceptaBeanSimpleMapper.COL_NOM
			;
	/**
	 * SQL per a {@link #testReceptaById(long)}.
	 */
	private static final String SQL_TEST_FIND_BY_ID = "SELECT COUNT(*)>0"
			+ " FROM " + ReceptaBeanSimpleMapper.TAULA
			+ " WHERE " + ReceptaBeanSimpleMapper.COL_ID + "=:" + ReceptaBeanSimpleMapper.COL_ID
			;
	/**
	 * SQL per a {@link #idOfCategoriaByName(String)}.
	 */
	private static final String SQL_CAT_BY_NAME = "SELECT "
			+ ConstantsSql.COL_ID
			+ " FROM " + ConstantsSql.TAULA_CATEGORIES
			+ " WHERE " 
			+ ConstantsSql.COL_NOM + "=:" + ConstantsSql.COL_NOM
			;
	/**
	 * SQL per a {@link #findIngredientsByReceptaId(long)}.
	 */
	private static final String SQL_ING_RECEPTES = "SELECT *"
			+ " FROM " + ConstantsSql.VISTA_VRINGREDIENTS
			+ " WHERE "
			+ ConstantsSql.COL_FK_ID_RECEPTA + "=:" + ConstantsSql.COL_FK_ID_RECEPTA
			;
	/**
	 * SQL per a {@link #findKeywordsByReceptaId(long)}.
	 */
	private static final String SQL_KEYWORDS = "SELECT "
			+ ConstantsSql.COL_NOM
			+ " FROM " + ConstantsSql.VISTA_VRKEYWORDS
			+ " WHERE "
			+ ConstantsSql.COL_FK_ID_RECEPTA + "=:" + ConstantsSql.COL_FK_ID_RECEPTA
			;
	/**
	 * SQL per a {@link #findCategories()}
	 */
	private static final String SQL_CATEGORIES = "SELECT "
			+ ConstantsSql.COL_ID
			+ ", " + ConstantsSql.COL_NOM
			+ ", " + ConstantsSql.COL_PES
			+ " FROM " + ConstantsSql.VISTA_VPES_CATEGORIES
			;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private RowMapper<ReceptaBean> mapadorRecepta;
	
	@Autowired
	private RowMapper<IngredientReceptaBean> mapadorIngredient;
	
	@Autowired
	private RowMapper<CategoriaPesBean> mapadorCategoriaPes;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<ReceptaBean> findById(long id) {
		if(testReceptaById(id)) {
			return Optional.of(completarReceptaBean(namedParameterJdbcTemplate.queryForObject(SQL_FIND_BY_ID
					, new MapSqlParameterSource(ReceptaBeanSimpleMapper.COL_ID, id)
					, mapadorRecepta)));
		} else {
			return Optional.empty();
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ReceptaBean> findAll() {
		return namedParameterJdbcTemplate.query(SQL_FIND_ALL, mapadorRecepta)
				.stream()
				.map(r -> completarReceptaBean(r))
				.collect(Collectors.toList())
		;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ReceptaBean> findByCategoria(String categoria) {
		long l;
		
		l = idOfCategoriaByName(categoria);
		if(l > 0L) {
			return namedParameterJdbcTemplate.query(SQL_FIND_BY_CAT
						, new MapSqlParameterSource(ReceptaBeanSimpleMapper.COL_FK_ID_CATEGORIA, l)
						, mapadorRecepta)
					.stream()
					.map( r -> completarReceptaBean(r))
					.collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IngredientReceptaBean> findIngredientsByReceptaId(long id) {
		return namedParameterJdbcTemplate.query(SQL_ING_RECEPTES
				, new MapSqlParameterSource(ConstantsSql.COL_FK_ID_RECEPTA, id)
				, mapadorIngredient);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> findKeywordsByReceptaId(long id) {
		return namedParameterJdbcTemplate.queryForList(SQL_KEYWORDS
				, new MapSqlParameterSource(ConstantsSql.COL_FK_ID_RECEPTA, id)
				, String.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CategoriaPesBean> findCategories() {
		return namedParameterJdbcTemplate.query(SQL_CATEGORIES, mapadorCategoriaPes);
	}

	private ReceptaBean completarReceptaBean(ReceptaBean recepta) {
		return recepta.toBuilder()
			.ingredients(findIngredientsByReceptaId(recepta.getId()))
			.keywords(findKeywordsByReceptaId(recepta.getId()))
			.build()
			;
	}
	private boolean testReceptaById(long id) {
		return namedParameterJdbcTemplate.queryForObject(SQL_TEST_FIND_BY_ID
				, new MapSqlParameterSource(ReceptaBeanSimpleMapper.COL_ID, id), Boolean.class);
	}
	private long idOfCategoriaByName(String name) {
		Long l;
		
		l = namedParameterJdbcTemplate.queryForObject(SQL_CAT_BY_NAME
				, new MapSqlParameterSource(ConstantsSql.COL_NOM, name), Long.class);
		if(l == null) {
			return 0L;
		}
		return l.longValue();
	}
}
