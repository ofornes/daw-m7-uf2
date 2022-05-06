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
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import cat.albirar.daw.receptes.models.ComentariBean;

/**
 * Mapador SQL per a {@link ComentariBean}.
 * @author Octavi Fornés <a href="mailto:octavi@fornes.cat">&lt;octavi@fornes.cat&gt;</a>
 * @since 0.0.1
 */
@Component
public class ComentariBeanMapper implements RowMapper<ComentariBean> {

	public static final String TAULA = "COMENTARIS";
	public static final String COL_ID_COMENTARI = ConstantsSql.COL_ID + "_COMENTARI";
	public static final String COL_FK_ID_RECEPTA = ConstantsSql.COL_FK_ID_RECEPTA;
	public static final String COL_TS_CREACIO = "TS_CREACIO";
	public static final String COL_AUTOR = "AUTOR";
	public static final String COL_COMENTARI = "COMENTARI";
	public static final String COL_RATING = "RATING";
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ComentariBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		Short v;
		
		v = Short.valueOf(rs.getShort(COL_RATING));
		if(rs.wasNull()) {
			v = null;
		}
		return ComentariBean.builder()
				.id(rs.getLong(COL_ID_COMENTARI))
				.idRecepta(rs.getLong(COL_FK_ID_RECEPTA))
				.tsCreacio(rs.getTimestamp(COL_TS_CREACIO).toInstant())
				.autor(rs.getString(COL_AUTOR))
				.text(rs.getNString(COL_COMENTARI))
				.valoracio(Optional.ofNullable(v))
				.build()
				;
	}
}
