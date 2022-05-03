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

/**
 * Constants SQL.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 0.0.1
 */
public final class ConstantsSql {
	private ConstantsSql() {
		// Per a evitar instàncies
	}
	
	public static final String COL_ID = "ID";
	public static final String COL_NOM = "NOM";
	
	public static final String TAULA_CATEGORIES = "CATEGORIES";
	public static final String TAULA_ING_RECEPTES = "ING_RECEPTES";
	
	public static final String VISTA_VRKEYWORDS = "VRKEYWORDS";
	public static final String VISTA_VRECEPTESK = "VRECEPTESK";
	public static final String VISTA_VRINGREDIENTS = "VRINGREDIENTS";
	public static final String VISTA_VPES_CATEGORIES = "VPES_CATEGORIES";

	public static final String COL_FK_ID_RECEPTA = "FK_ID_RECEPTA";
	public static final String COL_FK_ID_KEYWORD = "FK_ID_KEYWORD";
	public static final String COL_FK_ID_INGREDIENT = "FK_ID_INGREDIENT";
	public static final String COL_QUANTITAT = "QUANTITAT";
	public static final String COL_MESURA = "MESURA";
	
	public static final String COL_INGREDIENT = "INGREDIENT";
	
	public static final String COL_NOM_AS_AUTOR = "AUTOR";
	public static final String COL_NOM_AS_CATEGORIA = "CATEGORIA";
	public static final String COL_NOM_AS_CUINA = "CUINA";

	public static final String COL_PES = "PES";
	public static final String COL_KEYWORD = "KEYWORD";
}
