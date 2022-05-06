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

import java.time.Instant;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Comentaris
 * @author Octavi Fornés <a href="mailto:octavi@fornes.cat">&lt;octavi@fornes.cat&gt;</a>
 * @since 0.0.1
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ComentariBean {
	/**
	 * Identificador sintètic.
	 */
	private long id;
	/**
	 * Identificador de la recepta associada.
	 */
	private long idRecepta;
	private String autor;
	/**
	 * Timestamp de la creació del comentari.
	 */
	private Instant tsCreacio;
	private String text;
	/**
	 * Valoració de la recepta, sobre 100.
	 */
	@Default
	private Optional<Short> valoracio = Optional.empty();
}
