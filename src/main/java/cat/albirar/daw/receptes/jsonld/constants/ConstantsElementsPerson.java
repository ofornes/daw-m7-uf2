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
 * Constants del tipus 'Person'.
 * 
 * @author Octavi Fornés <a href="mailto:octavi@fornes.cat">&lt;octavi@fornes.cat&gt;</a>
 * @since 0.0.1
 */
public abstract class ConstantsElementsPerson extends ConstantsElementsThing {
	/**
	 * An additional name for a Person, can be used for a middle name. 
	 * tipus: Text
	 */
	public static final String ADDITIONALNAME= "additionalName";
	/**
	 * Physical address of the item. 
	 * tipus: PostalAddress  or  Text
	 */
	public static final String ADDRESS= "address";
	/**
	 * An organization that this person is affiliated with. For example, a school/university, a club, or a team. 
	 * tipus: Organization
	 */
	public static final String AFFILIATION= "affiliation";
	/**
	 * An organization that the person is an alumni of.  Inverse property: alumni 
	 * tipus: EducationalOrganization  or  Organization
	 */
	public static final String ALUMNIOF= "alumniOf";
	/**
	 * An award won by or for this item. Supersedes awards. 
	 * tipus: Text
	 */
	public static final String AWARD= "award";
	/**
	 * Date of birth. 
	 * tipus: Date
	 */
	public static final String BIRTHDATE= "birthDate";
	/**
	 * The place where the person was born. 
	 * tipus: Place
	 */
	public static final String BIRTHPLACE= "birthPlace";
	/**
	 * The brand(s) associated with a product or service, or the brand(s) maintained by an organization or business person. 
	 * tipus: Brand  or  Organization
	 */
	public static final String BRAND= "brand";
	/**
	 * A callsign, as used in broadcasting and radio communications to identify people, radio and TV stations, or vehicles. 
	 * tipus: Text
	 */
	public static final String CALLSIGN= "callSign";
	/**
	 * A child of the person. 
	 * tipus: Person
	 */
	public static final String CHILDREN= "children";
	/**
	 * A colleague of the person. Supersedes colleagues. 
	 * tipus: Person  or  URL
	 */
	public static final String COLLEAGUE= "colleague";
	/**
	 * A contact point for a person or organization. Supersedes contactPoints. 
	 * tipus: ContactPoint
	 */
	public static final String CONTACTPOINT= "contactPoint";
	/**
	 * Date of death. 
	 * tipus: Date
	 */
	public static final String DEATHDATE= "deathDate";
	/**
	 * The place where the person died. 
	 * tipus: Place
	 */
	public static final String DEATHPLACE= "deathPlace";
	/**
	 * The Dun & Bradstreet DUNS number for identifying an organization or business person. 
	 * tipus: Text
	 */
	public static final String DUNS= "duns";
	/**
	 * Email address. 
	 * tipus: Text
	 */
	public static final String EMAIL= "email";
	/**
	 * Family name. In the U.S., the last name of a Person. 
	 * tipus: Text
	 */
	public static final String FAMILYNAME= "familyName";
	/**
	 * The fax number. 
	 * tipus: Text
	 */
	public static final String FAXNUMBER= "faxNumber";
	/**
	 * The most generic uni-directional social relation. 
	 * tipus: Person
	 */
	public static final String FOLLOWS= "follows";
	/**
	 * A person or organization that supports (sponsors) something through some kind of financial contribution. 
	 * tipus: Organization  or  Person
	 */
	public static final String FUNDER= "funder";
	/**
	 * A Grant that directly or indirectly provide funding or sponsorship for this item. See also ownershipFundingInfo.  Inverse property: fundedItem 
	 * tipus: Grant
	 */
	public static final String FUNDING= "funding";
	/**
	 * Gender of something, typically a Person, but possibly also fictional characters, animals, etc. While https://schema.org/Male and https://schema.org/Female may be used, text strings are also acceptable for people who do not identify as a binary gender. The gender property can also be used in an extended sense to cover e.g. the gender of sports teams. As with the gender of individuals, we do not try to enumerate all possibilities. A mixed-gender SportsTeam can be indicated with a text value of "Mixed". 
	 * tipus: GenderType  or  Text
	 */
	public static final String GENDER= "gender";
	/**
	 * Given name. In the U.S., the first name of a Person. 
	 * tipus: Text
	 */
	public static final String GIVENNAME= "givenName";
	/**
	 * The Global Location Number (GLN, sometimes also referred to as International Location Number or ILN) of the respective organization, person, or place. The GLN is a 13-digit number used to identify parties and physical locations. 
	 * tipus: Text
	 */
	public static final String GLOBALLOCATIONNUMBER= "globalLocationNumber";
	/**
	 * A credential awarded to the Person or Organization. 
	 * tipus: EducationalOccupationalCredential
	 */
	public static final String HASCREDENTIAL= "hasCredential";
	/**
	 * The Person's occupation. For past professions, use Role for expressing dates. 
	 * tipus: Occupation
	 */
	public static final String HASOCCUPATION= "hasOccupation";
	/**
	 * Indicates an OfferCatalog listing for this Organization, Person, or Service. 
	 * tipus: OfferCatalog
	 */
	public static final String HASOFFERCATALOG= "hasOfferCatalog";
	/**
	 * Points-of-Sales operated by the organization or person. 
	 * tipus: Place
	 */
	public static final String HASPOS= "hasPOS";
	/**
	 * The height of the item. 
	 * tipus: Distance  or  QuantitativeValue
	 */
	public static final String HEIGHT= "height";
	/**
	 * A contact location for a person's residence. 
	 * tipus: ContactPoint  or  Place
	 */
	public static final String HOMELOCATION= "homeLocation";
	/**
	 * An honorific prefix preceding a Person's name such as Dr/Mrs/Mr. 
	 * tipus: Text
	 */
	public static final String HONORIFICPREFIX= "honorificPrefix";
	/**
	 * An honorific suffix following a Person's name such as M.D. /PhD/MSCSW. 
	 * tipus: Text
	 */
	public static final String HONORIFICSUFFIX= "honorificSuffix";
	/**
	 * The number of interactions for the CreativeWork using the WebSite or SoftwareApplication. The most specific child type of InteractionCounter should be used. Supersedes interactionCount. 
	 * tipus: InteractionCounter
	 */
	public static final String INTERACTIONSTATISTIC= "interactionStatistic";
	/**
	 * The International Standard of Industrial Classification of All Economic Activities (ISIC), Revision 4 code for a particular organization, business person, or place. 
	 * tipus: Text
	 */
	public static final String ISICV4= "isicV4";
	/**
	 * The job title of the person (for example, Financial Manager). 
	 * tipus: DefinedTerm  or  Text
	 */
	public static final String JOBTITLE= "jobTitle";
	/**
	 * The most generic bi-directional social/work relation. 
	 * tipus: Person
	 */
	public static final String KNOWS= "knows";
	/**
	 * Of a Person, and less typically of an Organization, to indicate a topic that is known about - suggesting possible expertise but not implying it. We do not distinguish skill levels here, or relate this to educational content, events, objectives or JobPosting descriptions. 
	 * tipus: Text  or  Thing  or  URL
	 */
	public static final String KNOWSABOUT= "knowsAbout";
	/**
	 * Of a Person, and less typically of an Organization, to indicate a known language. We do not distinguish skill levels or reading/writing/speaking/signing here. Use language codes from the IETF BCP 47 standard. 
	 * tipus: Language  or  Text
	 */
	public static final String KNOWSLANGUAGE= "knowsLanguage";
	/**
	 * A pointer to products or services offered by the organization or person.  Inverse property: offeredBy 
	 * tipus: Offer
	 */
	public static final String MAKESOFFER= "makesOffer";
	/**
	 * An Organization (or ProgramMembership) to which this Person or Organization belongs.  Inverse property: member 
	 * tipus: Organization  or  ProgramMembership
	 */
	public static final String MEMBEROF= "memberOf";
	/**
	 * The North American Industry Classification System (NAICS) code for a particular organization or business person. 
	 * tipus: Text
	 */
	public static final String NAICS= "naics";
	/**
	 * Nationality of the person. 
	 * tipus: Country
	 */
	public static final String NATIONALITY= "nationality";
	/**
	 * The total financial value of the person as calculated by subtracting assets from liabilities. 
	 * tipus: MonetaryAmount  or  PriceSpecification
	 */
	public static final String NETWORTH= "netWorth";
	/**
	 * Products owned by the organization or person. 
	 * tipus: OwnershipInfo  or  Product
	 */
	public static final String OWNS= "owns";
	/**
	 * A parent of this person. Supersedes parents. 
	 * tipus: Person
	 */
	public static final String PARENT= "parent";
	/**
	 * Event that this person is a performer or participant in. 
	 * tipus: Event
	 */
	public static final String PERFORMERIN= "performerIn";
	/**
	 * The publishingPrinciples property indicates (typically via URL) a document describing the editorial principles of an Organization (or individual e.g. a Person writing a blog) that relate to their activities as a publisher, e.g. ethics or diversity policies. When applied to a CreativeWork (e.g. NewsArticle) the principles are those of the party primarily responsible for the creation of the CreativeWork.  While such policies are most typically expressed in natural language, sometimes related information (e.g. indicating a funder) can be expressed using schema.org terminology. 
	 * tipus: CreativeWork  or  URL
	 */
	public static final String PUBLISHINGPRINCIPLES= "publishingPrinciples";
	/**
	 * The most generic familial relation. 
	 * tipus: Person
	 */
	public static final String RELATEDTO= "relatedTo";
	/**
	 * A pointer to products or services sought by the organization or person (demand). 
	 * tipus: Demand
	 */
	public static final String SEEKS= "seeks";
	/**
	 * A sibling of the person. Supersedes siblings. 
	 * tipus: Person
	 */
	public static final String SIBLING= "sibling";
	/**
	 * A person or organization that supports a thing through a pledge, promise, or financial contribution. e.g. a sponsor of a Medical Study or a corporate sponsor of an event. 
	 * tipus: Organization  or  Person
	 */
	public static final String SPONSOR= "sponsor";
	/**
	 * The person's spouse. 
	 * tipus: Person
	 */
	public static final String SPOUSE= "spouse";
	/**
	 * The Tax / Fiscal ID of the organization or person, e.g. the TIN in the US or the CIF/NIF in Spain. 
	 * tipus: Text
	 */
	public static final String TAXID= "taxID";
	/**
	 * The telephone number. 
	 * tipus: Text
	 */
	public static final String TELEPHONE= "telephone";
	/**
	 * The Value-added Tax ID of the organization or person. 
	 * tipus: Text
	 */
	public static final String VATID= "vatID";
	/**
	 * The weight of the product or person. 
	 * tipus: QuantitativeValue
	 */
	public static final String WEIGHT= "weight";
	/**
	 * A contact location for a person's place of work. 
	 * tipus: ContactPoint  or  Place
	 */
	public static final String WORKLOCATION= "workLocation";
	/**
	 * Organizations that the person works for. 
	 * tipus: Organization
	 */
	public static final String WORKSFOR= "worksFor";
}
