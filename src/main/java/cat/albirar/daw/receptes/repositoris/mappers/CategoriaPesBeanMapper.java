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

import cat.albirar.daw.receptes.models.CategoriaPesBean;

/**
 * Mapador de {@link CategoriaPesBean}.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 0.0.1
 */
@Component
public class CategoriaPesBeanMapper implements RowMapper<CategoriaPesBean> {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategoriaPesBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		return CategoriaPesBean.builder()
				.id(rs.getLong(ConstantsSql.COL_ID))
				.nom(rs.getString(ConstantsSql.COL_NOM))
				.pes(rs.getInt(ConstantsSql.COL_PES))
				.build()
				;
	}

}
