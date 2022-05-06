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
package cat.albirar.daw.receptes.models;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Value;
import lombok.experimental.SuperBuilder;

/**
 * Ingredient dins una recepta.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 0.0.1
 */
@Value
@SuperBuilder(toBuilder = true)
public class IngredientReceptaBean implements Serializable {
	private static final long serialVersionUID = -2860081866777856281L;
	
	private String ingredient;
	private int quantitat;
	private String mesura;

	/**
	 * Converteix aquest ingredient en text segons el valor de {@link #getMesura()}.
	 * <ul>
	 * <li>Si {@link #getMesura()} és {@code 'undef'}, només s'hi afegeix {@link #getIngredient()}</li>
	 * <li>Si {@link #getMesura()} no és pas {@code 'undef'}, s'hi afegeix {@link #getQuantitat()} + {@link #getMesura()} + ' ' + {@link #getIngredient()}</li>
	 * @return El text
	 */
	public String convertirAJsonLdText() {
		if(mesura.equals("undef")) {
			return ingredient;
		}
		if(mesura.equals("count")) {
			return String.format("%d %s", quantitat, ingredient);
		}
		return String.format("%d%s %s", quantitat, mesura, ingredient);
	}
	/**
	 * Converteix un text amb el patró indicat en un {@link IngredientReceptaBean}.
	 * El patró és:
	 * <pre>
	 * ^([0-9]+)([^ ]+) (.+)$
	 * </pre>
	 * El grup 1 és la quantitat, el 2 la mesura i el 3 l'ingredient
	 * @param text
	 * @return
	 */
	public static IngredientReceptaBean textAIngredient(String text) {
		Pattern p = Pattern.compile("^([0-9]+)([^ ]+) (.+)$");
		Matcher m = p.matcher(text);
		if(!m.matches()) {
			throw new IllegalArgumentException(String.format("El text '%s' no és compatible amb un ingredient!", text));
		}
		return IngredientReceptaBean.builder()
				.quantitat(Integer.parseInt(m.group(1))).mesura(m.group(2)).ingredient(m.group(3))
				.build()
				;
	}
}
