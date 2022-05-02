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
package cat.albirar.daw.receptes.repositoris.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import cat.albirar.daw.receptes.models.IngredientReceptaBean;

/**
 * Mapador d' {@link IngredientReceptaBean}.
 * Mapa, també, la columna {@link ConstantsSql#COL_INGREDIENT} a {@link IngredientReceptaBean#getIngredient()}.
 * 
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 0.0.1
 */
@Component
public class IngredientReceptaBeanMapper implements RowMapper<IngredientReceptaBean> {
	public static final String TAULA = "ING_RECEPTES";

	public static final String COL_FK_ID_RECEPTA = "FK_ID_RECEPTA";
	public static final String COL_FK_ID_INGREDIENT = "FK_ID_INGREDIENT";
	public static final String COL_QUANTITAT = "QUANTITAT";
	public static final String COL_MESURA = "MESURA";
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IngredientReceptaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		return IngredientReceptaBean.builder()
				.ingredient(rs.getNString(ConstantsSql.COL_INGREDIENT))
				.quantitat(rs.getInt(COL_QUANTITAT))
				.mesura(rs.getString(COL_MESURA))
				.build()
				;
	}

}
