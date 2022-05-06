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
import java.time.Duration;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import cat.albirar.daw.receptes.models.ReceptaBean;

/**
 * Mapador simple de {@link ReceptaBean}.
 * Només fa mapping de les dades de la taula i de les relacions 1 a 1:
 * <ul>
 * <li>{@link ReceptaBean#getAutor()}</li>
 * <li>{@link ReceptaBean#getCategoria()}</li>
 * <li>{@link ReceptaBean#getCuina()}</li>
 * </ul>
 * Per tant, {@link ReceptaBean#getKeywords()} i {@link ReceptaBean#getIngredients()} no són mapats.
 * 
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 0.0.1
 */
@Component
public class ReceptaBeanSimpleMapper implements RowMapper<ReceptaBean> {
	public static final String TAULA = "RECEPTES";
	public static final String VISTA = "VRECEPTES";
	public static final String COL_ID = "ID";
	public static final String COL_NOM = "NOM";
	public static final String COL_TS_PUBLICACIO = "TS_PUBLICACIO";
	public static final String COL_FK_ID_AUTOR = "FK_ID_AUTOR";
	public static final String COL_DESCRIPCIO = "DESCRIPCIO";
	public static final String COL_IMATGE = "IMATGE";
	public static final String COL_D_PREPARACIO = "D_PREPARACIO";
	public static final String COL_FK_ID_CATEGORIA = "FK_ID_CATEGORIA";
	public static final String COL_FK_ID_CUINA = "FK_ID_CUINA";
	public static final String COL_INSTRUCCIONS = "INSTRUCCIONS";
	public static final String COL_NOM_SERVEIS = "NOM_SERVEIS";
	public static final String COL_D_TOTAL = "D_TOTAL";
	public static final String COL_D_COC = "D_COC";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReceptaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		Duration d;
		Optional<Duration> od;
		
		d = rs.getObject(COL_D_COC, Duration.class);
		if(rs.wasNull()) {
			od = Optional.empty();
		} else {
			od = Optional.of(d);
		}
		return ReceptaBean.builder()
				.id(rs.getLong(COL_ID))
				.nom(rs.getNString(COL_NOM))
				.publicacio(rs.getTimestamp(COL_TS_PUBLICACIO).toInstant())
				.autor(rs.getNString(ConstantsSql.COL_NOM_AS_AUTOR))
				.descripcio(rs.getNString(COL_DESCRIPCIO))
				.urlImatge(rs.getString(COL_IMATGE))
				.tempsPreparacio(rs.getObject(COL_D_PREPARACIO, Duration.class))
				.categoria(rs.getNString(ConstantsSql.COL_NOM_AS_CATEGORIA))
				.cuina(Optional.ofNullable(rs.getNString(ConstantsSql.COL_NOM_AS_CUINA)))
				.instruccions(rs.getNString(COL_INSTRUCCIONS))
				.nombreServeis(rs.getInt(COL_NOM_SERVEIS))
				.tempsTotal(rs.getObject(COL_D_TOTAL, Duration.class))
				.tempsCoccio(od)
				.build()
				;
	}
}
