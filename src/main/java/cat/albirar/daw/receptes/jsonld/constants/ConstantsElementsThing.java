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
package cat.albirar.daw.receptes.jsonld.constants;

/**
 * Elements del tipus 'Thing'.
 * 
 * @author Octavi Fornés
 *         <a href="mailto:octavi@fornes.cat">&lt;octavi@fornes.cat&gt;</a>
 * @since 0.0.1
 */
public abstract class ConstantsElementsThing {
	/**
	 * An additional type for the item, typically used for adding more specific
	 * types from external vocabularies in microdata syntax. This is a relationship
	 * between something and a class that the thing is in. In RDFa syntax, it is
	 * better to use the native RDFa syntax - the 'typeof' attribute - for multiple
	 * types. Schema.org tools may have only weaker understanding of extra types, in
	 * particular those defined externally. tipus: URL
	 */
	public static final String ADDITIONALTYPE = "additionalType";
	/**
	 * An alias for the item. tipus: Text
	 */
	public static final String ALTERNATENAME = "alternateName";
	/**
	 * A description of the item. tipus: Text
	 */
	public static final String DESCRIPTION = "description";
	/**
	 * A sub property of description. A short description of the item used to
	 * disambiguate from other, similar items. Information from other properties (in
	 * particular, name) may be necessary for the description to be useful for
	 * disambiguation. tipus: Text
	 */
	public static final String DISAMBIGUATINGDESCRIPTION = "disambiguatingDescription";
	/**
	 * The identifier property represents any kind of identifier for any kind of
	 * Thing, such as ISBNs, GTIN codes, UUIDs etc. Schema.org provides dedicated
	 * properties for representing many of these, either as textual strings or as
	 * URL (URI) links. See background notes for more details. tipus: PropertyValue 
	 * or Text  or URL
	 */
	public static final String IDENTIFIER = "identifier";
	/**
	 * An image of the item. This can be a URL or a fully described ImageObject.
	 * tipus: ImageObject  or URL
	 */
	public static final String IMAGE = "image";
	/**
	 * Indicates a page (or other CreativeWork) for which this thing is the main
	 * entity being described. See background notes for details. Inverse property:
	 * mainEntity tipus: CreativeWork  or URL
	 */
	public static final String MAINENTITYOFPAGE = "mainEntityOfPage";
	/**
	 * The name of the item. tipus: Text
	 */
	public static final String NAME = "name";
	/**
	 * Indicates a potential Action, which describes an idealized action in which
	 * this thing would play an 'object' role. tipus: Action
	 */
	public static final String POTENTIALACTION = "potentialAction";
	/**
	 * URL of a reference Web page that unambiguously indicates the item's identity.
	 * E.g. the URL of the item's Wikipedia page, Wikidata entry, or official
	 * website. tipus: URL
	 */
	public static final String SAMEAS = "sameAs";
	/**
	 * A CreativeWork or Event about this Thing. Inverse property: about tipus:
	 * CreativeWork  or Event
	 */
	public static final String SUBJECTOF = "subjectOf";
	/**
	 * URL of the item. tipus: URL
	 */
	public static final String URL = "url";
}
