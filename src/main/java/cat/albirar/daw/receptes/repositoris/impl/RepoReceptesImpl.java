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
import cat.albirar.daw.receptes.models.KeywordPesBean;
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
	private static final String SQL_FIND_ALL = "SELECT *"
			+ " FROM " + ReceptaBeanSimpleMapper.VISTA
			+ " ORDER BY "
			+ ReceptaBeanSimpleMapper.COL_NOM
			;
	/**
	 * Plantilla (per a resoldre amb {@link String#format(String, Object...)} d'SQL per a {@link #findRandom(int)}.
	 */
	private static final String TPL_SQL_FIND_RANDOM = "SELECT *"
			+ " FROM " + ReceptaBeanSimpleMapper.VISTA
			+ " ORDER BY RANDOM() LIMIT %d"
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
	private static final String SQL_ID_CAT_BY_NAME = "SELECT "
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
	 * SQL per a {@link #findKeywordByNom(String)}.
	 */
	private static final String SQL_KEYWORD_BY_NOM = "SELECT COUNT(*) AS "
			+ ConstantsSql.COL_PES
			+ ", " + ConstantsSql.COL_FK_ID_KEYWORD
			+ ", " + ConstantsSql.COL_KEYWORD
			+ " FROM "
			+ ConstantsSql.VISTA_VRECEPTESK
			+ " WHERE "
			+ ConstantsSql.COL_KEYWORD + "=:" + ConstantsSql.COL_KEYWORD
			+ " GROUP BY "
			+ ConstantsSql.COL_FK_ID_KEYWORD
			+ ", " + ConstantsSql.COL_KEYWORD
			;
	/**
	 * SQL per a {@link #findByKeyword(String)}.
	 */
	private static final String SQL_RECEPTES_BY_KEYWORD = "SELECT *"
			+ " FROM "
			+ ConstantsSql.VISTA_VRECEPTESK
			+ " WHERE "
			+ ConstantsSql.COL_KEYWORD + "=:" + ConstantsSql.COL_KEYWORD
			+ " ORDER BY "
			+ ReceptaBeanSimpleMapper.COL_TS_PUBLICACIO + " DESC"
			+ ", " + ReceptaBeanSimpleMapper.COL_NOM
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
	/**
	 * SQL per a {@link #findCategoriaByNom(String)}
	 */
	private static final String SQL_CATEGORIA_BY_NOM = "SELECT "
			+ ConstantsSql.COL_ID
			+ ", " + ConstantsSql.COL_NOM
			+ ", " + ConstantsSql.COL_PES
			+ " FROM " + ConstantsSql.VISTA_VPES_CATEGORIES
			+ " WHERE "
			+ ConstantsSql.COL_NOM + "=:" + ConstantsSql.COL_NOM
			;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private RowMapper<ReceptaBean> mapadorRecepta;
	
	@Autowired
	private RowMapper<IngredientReceptaBean> mapadorIngredient;

	@Autowired
	private RowMapper<CategoriaPesBean> mapadorCategoriaPes;
	@Autowired
	private RowMapper<KeywordPesBean> mapadorKeywordPes;
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
	public List<ReceptaBean> findRandom(int howmany) {
		return namedParameterJdbcTemplate.query(String.format(TPL_SQL_FIND_RANDOM, howmany), mapadorRecepta)
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
	public KeywordPesBean findKeywordByNom(String nom) {
		return namedParameterJdbcTemplate.queryForObject(SQL_KEYWORD_BY_NOM
			, new MapSqlParameterSource(ConstantsSql.COL_KEYWORD, nom)
			, mapadorKeywordPes);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ReceptaBean> findByKeyword(String nom) {
		return namedParameterJdbcTemplate.query(SQL_RECEPTES_BY_KEYWORD
					, new MapSqlParameterSource(ConstantsSql.COL_KEYWORD, nom)
					, mapadorRecepta)
				.stream().map(r -> completarReceptaBean(r))
				.collect(Collectors.toList())
				;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CategoriaPesBean> findCategories() {
		return namedParameterJdbcTemplate.query(SQL_CATEGORIES, mapadorCategoriaPes);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategoriaPesBean findCategoriaByNom(String nom) {
		return namedParameterJdbcTemplate.queryForObject(SQL_CATEGORIA_BY_NOM
				, new MapSqlParameterSource(ConstantsSql.COL_NOM, nom)
				, mapadorCategoriaPes)
				;
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
		
		l = namedParameterJdbcTemplate.queryForObject(SQL_ID_CAT_BY_NAME
				, new MapSqlParameterSource(ConstantsSql.COL_NOM, name), Long.class);
		if(l == null) {
			return 0L;
		}
		return l.longValue();
	}
}
