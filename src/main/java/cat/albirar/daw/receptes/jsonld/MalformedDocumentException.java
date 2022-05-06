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
package cat.albirar.daw.receptes.jsonld;

/**
 * Indica un error de documents, per exemple que s'ha afegit propietat sense
 * iniciar el document.
 * 
 * @author Octavi Fornés
 *         <a href="mailto:octavi@fornes.cat">octavi@fornes.cat</a>
 * @since 0.0.1
 */
@SuppressWarnings("serial")
public class MalformedDocumentException extends RuntimeException {
	/**
	 * Constructor únic.
	 */
	public MalformedDocumentException(String message) {
		super(message);
	}
}
