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
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import lombok.Builder.Default;
import lombok.Value;
import lombok.experimental.SuperBuilder;

/**
 * Bean d'una recepta.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 0.0.1
 */
@Value
@SuperBuilder(toBuilder = true)
public class ReceptaBean implements Serializable {
	private static final long serialVersionUID = -1057682129307987102L;
	
	private long id;
	private String nom;
	@Default
	Instant publicacio = Instant.now();
	String autor;
	String descripcio;
	String urlImatge;
	Duration tempsPreparacio;
	Duration tempsTotal;
	@Default
	Optional<Duration> tempsCoccio = Optional.empty();
	int nombreServeis;
	String categoria;
	@Default
	Optional<String> cuina = Optional.empty();
	String instruccions;
	List<IngredientReceptaBean> ingredients;
	List<String> keywords;
	@Default
	List<ComentariBean> comentaris = Collections.emptyList();
}
