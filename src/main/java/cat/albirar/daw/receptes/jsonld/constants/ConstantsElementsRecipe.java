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
 * Elements del tipus {@code Recipe} de <a href="https://schema.org/Recipe">https://schema.org</a>
 * @author Octavi Fornés <a href="mailto:octavi@fornes.cat">&lt;octavi@fornes.cat&gt;</a>
 * @since 0.0.1
 */
public abstract class ConstantsElementsRecipe extends ConstantsElementsThing {
	/**
	 * The time it takes to actually cook the dish, in ISO 8601 duration format. 
	 * tipus: Duration
	 */
	public static final String COOKTIME= "cookTime";
	/**
	 * The method of cooking, such as Frying, Steaming, ... 
	 * tipus: Text
	 */
	public static final String COOKINGMETHOD= "cookingMethod";
	/**
	 * Nutrition information about the recipe or menu item. 
	 * tipus: NutritionInformation
	 */
	public static final String NUTRITION= "nutrition";
	/**
	 * The category of the recipe—for example, appetizer, entree, etc. 
	 * tipus: Text
	 */
	public static final String RECIPECATEGORY= "recipeCategory";
	/**
	 * The cuisine of the recipe (for example, French or Ethiopian). 
	 * tipus: Text
	 */
	public static final String RECIPECUISINE= "recipeCuisine";
	/**
	 * A single ingredient used in the recipe, e.g. sugar, flour or garlic. Supersedes ingredients. 
	 * tipus: Text
	 */
	public static final String RECIPEINGREDIENT= "recipeIngredient";
	/**
	 * A step in making the recipe, in the form of a single item (document, video, etc.) or an ordered list with HowToStep and/or HowToSection items. 
	 * tipus: CreativeWork  or  ItemList  or  Text
	 */
	public static final String RECIPEINSTRUCTIONS= "recipeInstructions";
	/**
	 * The quantity produced by the recipe (for example, number of people served, number of servings, etc). 
	 * tipus: QuantitativeValue  or  Text
	 */
	public static final String RECIPEYIELD= "recipeYield";
	/**
	 * Indicates a dietary restriction or guideline for which this recipe or menu item is suitable, e.g. diabetic, halal etc. 
	 * tipus: RestrictedDiet
	 */
	public static final String SUITABLEFORDIET= "suitableForDiet";
	/**
	 * The estimated cost of the supply or supplies consumed when performing instructions. 
	 * tipus: MonetaryAmount  or  Text
	 */
	public static final String ESTIMATEDCOST= "estimatedCost";
	/**
	 * The length of time it takes to perform instructions or a direction (not including time to prepare the supplies), in ISO 8601 duration format. 
	 * tipus: Duration
	 */
	public static final String PERFORMTIME= "performTime";
	/**
	 * The length of time it takes to prepare the items to be used in instructions or a direction, in ISO 8601 duration format. 
	 * tipus: Duration
	 */
	public static final String PREPTIME= "prepTime";
	/**
	 * A single step item (as HowToStep, text, document, video, etc.) or a HowToSection. Supersedes steps. 
	 * tipus: CreativeWork  or  HowToSection  or  HowToStep  or  Text
	 */
	public static final String STEP= "step";
	/**
	 * A sub-property of instrument. A supply consumed when performing instructions or a direction. 
	 * tipus: HowToSupply  or  Text
	 */
	public static final String SUPPLY= "supply";
	/**
	 * A sub property of instrument. An object used (but not consumed) when performing instructions or a direction. 
	 * tipus: HowToTool  or  Text
	 */
	public static final String TOOL= "tool";
	/**
	 * The total time required to perform instructions or a direction (including time to prepare the supplies), in ISO 8601 duration format. 
	 * tipus: Duration
	 */
	public static final String TOTALTIME= "totalTime";
	/**
	 * The quantity that results by performing instructions. For example, a paper airplane, 10 personalized candles. 
	 * tipus: QuantitativeValue  or  Text
	 */
	public static final String YIELD= "yield";
	/**
	 * The subject matter of the content.  Inverse property: subjectOf 
	 * tipus: Thing
	 */
	public static final String ABOUT= "about";
	/**
	 * An abstract is a short description that summarizes a CreativeWork. 
	 * tipus: Text
	 */
	public static final String ABSTRACT= "abstract";
	/**
	 * The human sensory perceptual system or cognitive faculty through which a person may process or perceive information. Values should be drawn from the approved vocabulary. 
	 * tipus: Text
	 */
	public static final String ACCESSMODE= "accessMode";
	/**
	 * A list of single or combined accessModes that are sufficient to understand all the intellectual content of a resource. Values should be drawn from the approved vocabulary. 
	 * tipus: ItemList
	 */
	public static final String ACCESSMODESUFFICIENT= "accessModeSufficient";
	/**
	 * Indicates that the resource is compatible with the referenced accessibility API. Values should be drawn from the approved vocabulary. 
	 * tipus: Text
	 */
	public static final String ACCESSIBILITYAPI= "accessibilityAPI";
	/**
	 * Identifies input methods that are sufficient to fully control the described resource. Values should be drawn from the approved vocabulary. 
	 * tipus: Text
	 */
	public static final String ACCESSIBILITYCONTROL= "accessibilityControl";
	/**
	 * Content features of the resource, such as accessible media, alternatives and supported enhancements for accessibility. Values should be drawn from the approved vocabulary. 
	 * tipus: Text
	 */
	public static final String ACCESSIBILITYFEATURE= "accessibilityFeature";
	/**
	 * A characteristic of the described resource that is physiologically dangerous to some users. Related to WCAG 2.0 guideline 2.3. Values should be drawn from the approved vocabulary. 
	 * tipus: Text
	 */
	public static final String ACCESSIBILITYHAZARD= "accessibilityHazard";
	/**
	 * A human-readable summary of specific accessibility features or deficiencies, consistent with the other accessibility metadata but expressing subtleties such as "short descriptions are present but long descriptions will be needed for non-visual users" or "short descriptions are present and no long descriptions are needed." 
	 * tipus: Text
	 */
	public static final String ACCESSIBILITYSUMMARY= "accessibilitySummary";
	/**
	 * Specifies the Person that is legally accountable for the CreativeWork. 
	 * tipus: Person
	 */
	public static final String ACCOUNTABLEPERSON= "accountablePerson";
	/**
	 * Indicates a page documenting how licenses can be purchased or otherwise acquired, for the current item. 
	 * tipus: CreativeWork  or  URL
	 */
	public static final String ACQUIRELICENSEPAGE= "acquireLicensePage";
	/**
	 * The overall rating, based on a collection of reviews or ratings, of the item. 
	 * tipus: AggregateRating
	 */
	public static final String AGGREGATERATING= "aggregateRating";
	/**
	 * A secondary title of the CreativeWork. 
	 * tipus: Text
	 */
	public static final String ALTERNATIVEHEADLINE= "alternativeHeadline";
	/**
	 * Indicates a page or other link involved in archival of a CreativeWork. In the case of MediaReview, the items in a MediaReviewItem may often become inaccessible, but be archived by archival, journalistic, activist, or law enforcement organizations. In such cases, the referenced page may not directly publish the content. 
	 * tipus: URL  or  WebPage
	 */
	public static final String ARCHIVEDAT= "archivedAt";
	/**
	 * The item being described is intended to assess the competency or learning outcome defined by the referenced term. 
	 * tipus: DefinedTerm  or  Text
	 */
	public static final String ASSESSES= "assesses";
	/**
	 * A media object that encodes this CreativeWork. This property is a synonym for encoding. 
	 * tipus: MediaObject
	 */
	public static final String ASSOCIATEDMEDIA= "associatedMedia";
	/**
	 * An intended audience, i.e. a group for whom something was created. Supersedes serviceAudience. 
	 * tipus: Audience
	 */
	public static final String AUDIENCE= "audience";
	/**
	 * An embedded audio object. 
	 * tipus: AudioObject  or  Clip  or  MusicRecording
	 */
	public static final String AUDIO= "audio";
	/**
	 * The author of this content or rating. Please note that author is special in that HTML 5 provides a special mechanism for indicating authorship via the rel tag. That is equivalent to this and may be used interchangeably. 
	 * tipus: Organization  or  Person
	 */
	public static final String AUTHOR= "author";
	/**
	 * An award won by or for this item. Supersedes awards. 
	 * tipus: Text
	 */
	public static final String AWARD= "award";
	/**
	 * Fictional person connected with a creative work. 
	 * tipus: Person
	 */
	public static final String CHARACTER= "character";
	/**
	 * A citation or reference to another creative work, such as another publication, web page, scholarly article, etc. 
	 * tipus: CreativeWork  or  Text
	 */
	public static final String CITATION= "citation";
	/**
	 * Comments, typically from users. 
	 * tipus: Comment
	 */
	public static final String COMMENT= "comment";
	/**
	 * The number of comments this CreativeWork (e.g. Article, Question or Answer) has received. This is most applicable to works published in Web sites with commenting system; additional comments may exist elsewhere. 
	 * tipus: Integer
	 */
	public static final String COMMENTCOUNT= "commentCount";
	/**
	 * Conditions that affect the availability of, or method(s) of access to, an item. Typically used for real world items such as an ArchiveComponent held by an ArchiveOrganization. This property is not suitable for use as a general Web access control mechanism. It is expressed only in natural language.  For example "Available by appointment from the Reading Room" or "Accessible only from logged-in accounts ". 
	 * tipus: Text
	 */
	public static final String CONDITIONSOFACCESS= "conditionsOfAccess";
	/**
	 * The location depicted or described in the content. For example, the location in a photograph or painting. 
	 * tipus: Place
	 */
	public static final String CONTENTLOCATION= "contentLocation";
	/**
	 * Official rating of a piece of content—for example,'MPAA PG-13'. 
	 * tipus: Rating  or  Text
	 */
	public static final String CONTENTRATING= "contentRating";
	/**
	 * The specific time described by a creative work, for works (e.g. articles, video objects etc.) that emphasise a particular moment within an Event. 
	 * tipus: DateTime
	 */
	public static final String CONTENTREFERENCETIME= "contentReferenceTime";
	/**
	 * A secondary contributor to the CreativeWork or Event. 
	 * tipus: Organization  or  Person
	 */
	public static final String CONTRIBUTOR= "contributor";
	/**
	 * The party holding the legal copyright to the CreativeWork. 
	 * tipus: Organization  or  Person
	 */
	public static final String COPYRIGHTHOLDER= "copyrightHolder";
	/**
	 * Text of a notice appropriate for describing the copyright aspects of this Creative Work, ideally indicating the owner of the copyright for the Work. 
	 * tipus: Text
	 */
	public static final String COPYRIGHTNOTICE= "copyrightNotice";
	/**
	 * The year during which the claimed copyright for the CreativeWork was first asserted. 
	 * tipus: Number
	 */
	public static final String COPYRIGHTYEAR= "copyrightYear";
	/**
	 * Indicates a correction to a CreativeWork, either via a CorrectionComment, textually or in another document. 
	 * tipus: CorrectionComment  or  Text  or  URL
	 */
	public static final String CORRECTION= "correction";
	/**
	 * The country of origin of something, including products as well as creative works such as movie and TV content.  In the case of TV and movie, this would be the country of the principle offices of the production company or individual responsible for the movie. For other kinds of CreativeWork it is difficult to provide fully general guidance, and properties such as contentLocation and locationCreated may be more applicable.  In the case of products, the country of origin of the product. The exact interpretation of this may vary by context and product type, and cannot be fully enumerated here. 
	 * tipus: Country
	 */
	public static final String COUNTRYOFORIGIN= "countryOfOrigin";
	/**
	 * The status of a creative work in terms of its stage in a lifecycle. Example terms include Incomplete, Draft, Published, Obsolete. Some organizations define a set of terms for the stages of their publication lifecycle. 
	 * tipus: DefinedTerm  or  Text
	 */
	public static final String CREATIVEWORKSTATUS= "creativeWorkStatus";
	/**
	 * The creator/author of this CreativeWork. This is the same as the Author property for CreativeWork. 
	 * tipus: Organization  or  Person
	 */
	public static final String CREATOR= "creator";
	/**
	 * Text that can be used to credit person(s) and/or organization(s) associated with a published Creative Work. 
	 * tipus: Text
	 */
	public static final String CREDITTEXT= "creditText";
	/**
	 * The date on which the CreativeWork was created or the item was added to a DataFeed. 
	 * tipus: Date  or  DateTime
	 */
	public static final String DATECREATED= "dateCreated";
	/**
	 * The date on which the CreativeWork was most recently modified or when the item's entry was modified within a DataFeed. 
	 * tipus: Date  or  DateTime
	 */
	public static final String DATEMODIFIED= "dateModified";
	/**
	 * Date of first broadcast/publication. 
	 * tipus: Date  or  DateTime
	 */
	public static final String DATEPUBLISHED= "datePublished";
	/**
	 * A link to the page containing the comments of the CreativeWork. 
	 * tipus: URL
	 */
	public static final String DISCUSSIONURL= "discussionUrl";
	/**
	 * An EIDR (Entertainment Identifier Registry) identifier representing a specific edit / edition for a work of film or television.  For example, the motion picture known as "Ghostbusters" whose titleEIDR is "10.5240/7EC7-228A-510A-053E-CBB8-J", has several edits e.g. "10.5240/1F2A-E1C5-680A-14C6-E76B-I" and "10.5240/8A35-3BEE-6497-5D12-9E4F-3".  Since schema.org types like Movie and TVEpisode can be used for both works and their multiple expressions, it is possible to use titleEIDR alone (for a general description), or alongside editEIDR for a more edit-specific description. 
	 * tipus: Text  or  URL
	 */
	public static final String EDITEIDR= "editEIDR";
	/**
	 * Specifies the Person who edited the CreativeWork. 
	 * tipus: Person
	 */
	public static final String EDITOR= "editor";
	/**
	 * An alignment to an established educational framework.  This property should not be used where the nature of the alignment can be described using a simple property, for example to express that a resource teaches or assesses a competency. 
	 * tipus: AlignmentObject
	 */
	public static final String EDUCATIONALALIGNMENT= "educationalAlignment";
	/**
	 * The level in terms of progression through an educational or training context. Examples of educational levels include 'beginner', 'intermediate' or 'advanced', and formal sets of level indicators. 
	 * tipus: DefinedTerm  or  Text  or  URL
	 */
	public static final String EDUCATIONALLEVEL= "educationalLevel";
	/**
	 * The purpose of a work in the context of education; for example, 'assignment', 'group work'. 
	 * tipus: DefinedTerm  or  Text
	 */
	public static final String EDUCATIONALUSE= "educationalUse";
	/**
	 * A media object that encodes this CreativeWork. This property is a synonym for associatedMedia. Supersedes encodings.  Inverse property: encodesCreativeWork 
	 * tipus: MediaObject
	 */
	public static final String ENCODING= "encoding";
	/**
	 * Media type typically expressed using a MIME format (see IANA site and MDN reference) e.g. application/zip for a SoftwareApplication binary, audio/mpeg for .mp3 etc.).  In cases where a CreativeWork has several media type representations, encoding can be used to indicate each MediaObject alongside particular encodingFormat information.  Unregistered or niche encoding and file formats can be indicated instead via the most appropriate URL, e.g. defining Web page or a Wikipedia/Wikidata entry. Supersedes fileFormat. 
	 * tipus: Text  or  URL
	 */
	public static final String ENCODINGFORMAT= "encodingFormat";
	/**
	 * A creative work that this work is an example/instance/realization/derivation of.  Inverse property: workExample 
	 * tipus: CreativeWork
	 */
	public static final String EXAMPLEOFWORK= "exampleOfWork";
	/**
	 * Date the content expires and is no longer useful or available. For example a VideoObject or NewsArticle whose availability or relevance is time-limited, or a ClaimReview fact check whose publisher wants to indicate that it may no longer be relevant (or helpful to highlight) after some date. 
	 * tipus: Date
	 */
	public static final String EXPIRES= "expires";
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
	 * Genre of the creative work, broadcast channel or group. 
	 * tipus: Text  or  URL
	 */
	public static final String GENRE= "genre";
	/**
	 * Indicates an item or CreativeWork that is part of this item, or CreativeWork (in some sense).  Inverse property: isPartOf 
	 * tipus: CreativeWork
	 */
	public static final String HASPART= "hasPart";
	/**
	 * Headline of the article. 
	 * tipus: Text
	 */
	public static final String HEADLINE= "headline";
	/**
	 * The language of the content or performance or used in an action. Please use one of the language codes from the IETF BCP 47 standard. See also availableLanguage. Supersedes language. 
	 * tipus: Language  or  Text
	 */
	public static final String INLANGUAGE= "inLanguage";
	/**
	 * The number of interactions for the CreativeWork using the WebSite or SoftwareApplication. The most specific child type of InteractionCounter should be used. Supersedes interactionCount. 
	 * tipus: InteractionCounter
	 */
	public static final String INTERACTIONSTATISTIC= "interactionStatistic";
	/**
	 * The predominant mode of learning supported by the learning resource. Acceptable values are 'active', 'expositive', or 'mixed'. 
	 * tipus: Text
	 */
	public static final String INTERACTIVITYTYPE= "interactivityType";
	/**
	 * Used to indicate a specific claim contained, implied, translated or refined from the content of a MediaObject or other CreativeWork. The interpreting party can be indicated using claimInterpreter. 
	 * tipus: Claim
	 */
	public static final String INTERPRETEDASCLAIM= "interpretedAsClaim";
	/**
	 * A flag to signal that the item, event, or place is accessible for free. Supersedes free. 
	 * tipus: Boolean
	 */
	public static final String ISACCESSIBLEFORFREE= "isAccessibleForFree";
	/**
	 * A resource from which this work is derived or from which it is a modification or adaption. Supersedes isBasedOnUrl. 
	 * tipus: CreativeWork  or  Product  or  URL
	 */
	public static final String ISBASEDON= "isBasedOn";
	/**
	 * Indicates whether this content is family friendly. 
	 * tipus: Boolean
	 */
	public static final String ISFAMILYFRIENDLY= "isFamilyFriendly";
	/**
	 * Indicates an item or CreativeWork that this item, or CreativeWork (in some sense), is part of.  Inverse property: hasPart 
	 * tipus: CreativeWork  or  URL
	 */
	public static final String ISPARTOF= "isPartOf";
	/**
	 * Keywords or tags used to describe some item. Multiple textual entries in a keywords list are typically delimited by commas, or by repeating the property. 
	 * tipus: DefinedTerm  or  Text  or  URL
	 */
	public static final String KEYWORDS= "keywords";
	/**
	 * The predominant type or kind characterizing the learning resource. For example, 'presentation', 'handout'. 
	 * tipus: DefinedTerm  or  Text
	 */
	public static final String LEARNINGRESOURCETYPE= "learningResourceType";
	/**
	 * A license document that applies to this content, typically indicated by URL. 
	 * tipus: CreativeWork  or  URL
	 */
	public static final String LICENSE= "license";
	/**
	 * The location where the CreativeWork was created, which may not be the same as the location depicted in the CreativeWork. 
	 * tipus: Place
	 */
	public static final String LOCATIONCREATED= "locationCreated";
	/**
	 * Indicates the primary entity described in some page or other CreativeWork.  Inverse property: mainEntityOfPage 
	 * tipus: Thing
	 */
	public static final String MAINENTITY= "mainEntity";
	/**
	 * A maintainer of a Dataset, software package (SoftwareApplication), or other Project. A maintainer is a Person or Organization that manages contributions to, and/or publication of, some (typically complex) artifact. It is common for distributions of software and data to be based on "upstream" sources. When maintainer is applied to a specific version of something e.g. a particular version or packaging of a Dataset, it is always possible that the upstream source has a different maintainer. The isBasedOn property can be used to indicate such relationships between datasets to make the different maintenance roles clear. Similarly in the case of software, a package may have dedicated maintainers working on integration into software distributions such as Ubuntu, as well as upstream maintainers of the underlying work. 
	 * tipus: Organization  or  Person
	 */
	public static final String MAINTAINER= "maintainer";
	/**
	 * A material that something is made from, e.g. leather, wool, cotton, paper. 
	 * tipus: Product  or  Text  or  URL
	 */
	public static final String MATERIAL= "material";
	/**
	 * The quantity of the materials being described or an expression of the physical space they occupy. 
	 * tipus: QuantitativeValue  or  Text
	 */
	public static final String MATERIALEXTENT= "materialExtent";
	/**
	 * Indicates that the CreativeWork contains a reference to, but is not necessarily about a concept. 
	 * tipus: Thing
	 */
	public static final String MENTIONS= "mentions";
	/**
	 * An offer to provide this item—for example, an offer to sell a product, rent the DVD of a movie, perform a service, or give away tickets to an event. Use businessFunction to indicate the kind of transaction offered, i.e. sell, lease, etc. This property can also be used to describe a Demand. While this property is listed as expected on a number of common types, it can be used in others. In that case, using a second type, such as Product or a subtype of Product, can clarify the nature of the offer.  Inverse property: itemOffered 
	 * tipus: Demand  or  Offer
	 */
	public static final String OFFERS= "offers";
	/**
	 * A pattern that something has, for example 'polka dot', 'striped', 'Canadian flag'. Values are typically expressed as text, although links to controlled value schemes are also supported. 
	 * tipus: DefinedTerm  or  Text
	 */
	public static final String PATTERN= "pattern";
	/**
	 * The position of an item in a series or sequence of items. 
	 * tipus: Integer  or  Text
	 */
	public static final String POSITION= "position";
	/**
	 * The person or organization who produced the work (e.g. music album, movie, tv/radio series etc.). 
	 * tipus: Organization  or  Person
	 */
	public static final String PRODUCER= "producer";
	/**
	 * The service provider, service operator, or service performer; the goods producer. Another party (a seller) may offer those services or goods on behalf of the provider. A provider may also serve as the seller. Supersedes carrier. 
	 * tipus: Organization  or  Person
	 */
	public static final String PROVIDER= "provider";
	/**
	 * A publication event associated with the item. 
	 * tipus: PublicationEvent
	 */
	public static final String PUBLICATION= "publication";
	/**
	 * The publisher of the creative work. 
	 * tipus: Organization  or  Person
	 */
	public static final String PUBLISHER= "publisher";
	/**
	 * The publishing division which published the comic. 
	 * tipus: Organization
	 */
	public static final String PUBLISHERIMPRINT= "publisherImprint";
	/**
	 * The publishingPrinciples property indicates (typically via URL) a document describing the editorial principles of an Organization (or individual e.g. a Person writing a blog) that relate to their activities as a publisher, e.g. ethics or diversity policies. When applied to a CreativeWork (e.g. NewsArticle) the principles are those of the party primarily responsible for the creation of the CreativeWork.  While such policies are most typically expressed in natural language, sometimes related information (e.g. indicating a funder) can be expressed using schema.org terminology. 
	 * tipus: CreativeWork  or  URL
	 */
	public static final String PUBLISHINGPRINCIPLES= "publishingPrinciples";
	/**
	 * The Event where the CreativeWork was recorded. The CreativeWork may capture all or part of the event.  Inverse property: recordedIn 
	 * tipus: Event
	 */
	public static final String RECORDEDAT= "recordedAt";
	/**
	 * The place and time the release was issued, expressed as a PublicationEvent. 
	 * tipus: PublicationEvent
	 */
	public static final String RELEASEDEVENT= "releasedEvent";
	/**
	 * A review of the item. Supersedes reviews. 
	 * tipus: Review
	 */
	public static final String REVIEW= "review";
	/**
	 * Indicates (by URL or string) a particular version of a schema used in some CreativeWork. This property was created primarily to indicate the use of a specific schema.org release, e.g. 10.0 as a simple string, or more explicitly via URL, https://schema.org/docs/releases.html#v10.0. There may be situations in which other schemas might usefully be referenced this way, e.g. http://dublincore.org/specifications/dublin-core/dces/1999-07-02/ but this has not been carefully explored in the community. 
	 * tipus: Text  or  URL
	 */
	public static final String SCHEMAVERSION= "schemaVersion";
	/**
	 * Indicates the date on which the current structured data was generated / published. Typically used alongside sdPublisher 
	 * tipus: Date
	 */
	public static final String SDDATEPUBLISHED= "sdDatePublished";
	/**
	 * A license document that applies to this structured data, typically indicated by URL. 
	 * tipus: CreativeWork  or  URL
	 */
	public static final String SDLICENSE= "sdLicense";
	/**
	 * Indicates the party responsible for generating and publishing the current structured data markup, typically in cases where the structured data is derived automatically from existing published content but published on a different site. For example, student projects and open data initiatives often re-publish existing content with more explicitly structured metadata. The sdPublisher property helps make such practices more explicit. 
	 * tipus: Organization  or  Person
	 */
	public static final String SDPUBLISHER= "sdPublisher";
	/**
	 * A standardized size of a product or creative work, specified either through a simple textual string (for example 'XL', '32Wx34L'), a QuantitativeValue with a unitCode, or a comprehensive and structured SizeSpecification; in other cases, the width, height, depth and weight properties may be more applicable. 
	 * tipus: DefinedTerm  or  QuantitativeValue  or  SizeSpecification  or  Text
	 */
	public static final String SIZE= "size";
	/**
	 * The Organization on whose behalf the creator was working. 
	 * tipus: Organization
	 */
	public static final String SOURCEORGANIZATION= "sourceOrganization";
	/**
	 * The "spatial" property can be used in cases when more specific properties (e.g. locationCreated, spatialCoverage, contentLocation) are not known to be appropriate. 
	 * tipus: Place
	 */
	public static final String SPATIAL= "spatial";
	/**
	 * The spatialCoverage of a CreativeWork indicates the place(s) which are the focus of the content. It is a subproperty of contentLocation intended primarily for more technical and detailed materials. For example with a Dataset, it indicates areas that the dataset describes: a dataset of New York weather would have spatialCoverage which was the place: the state of New York. 
	 * tipus: Place
	 */
	public static final String SPATIALCOVERAGE= "spatialCoverage";
	/**
	 * A person or organization that supports a thing through a pledge, promise, or financial contribution. e.g. a sponsor of a Medical Study or a corporate sponsor of an event. 
	 * tipus: Organization  or  Person
	 */
	public static final String SPONSOR= "sponsor";
	/**
	 * The item being described is intended to help a person learn the competency or learning outcome defined by the referenced term. 
	 * tipus: DefinedTerm  or  Text
	 */
	public static final String TEACHES= "teaches";
	/**
	 * The "temporal" property can be used in cases where more specific properties (e.g. temporalCoverage, dateCreated, dateModified, datePublished) are not known to be appropriate. 
	 * tipus: DateTime  or  Text
	 */
	public static final String TEMPORAL= "temporal";
	/**
	 * The temporalCoverage of a CreativeWork indicates the period that the content applies to, i.e. that it describes, either as a DateTime or as a textual string indicating a time period in ISO 8601 time interval format. In the case of a Dataset it will typically indicate the relevant time period in a precise notation (e.g. for a 2011 census dataset, the year 2011 would be written "2011/2012"). Other forms of content e.g. ScholarlyArticle, Book, TVSeries or TVEpisode may indicate their temporalCoverage in broader terms - textually or via well-known URL. Written works such as books may sometimes have precise temporal coverage too, e.g. a work set in 1939 - 1945 can be indicated in ISO 8601 interval format format via "1939/1945".  Open-ended date ranges can be written with ".." in place of the end date. For example, "2015-11/.." indicates a range beginning in November 2015 and with no specified final date. This is tentative and might be updated in future when ISO 8601 is officially updated. Supersedes datasetTimeInterval. 
	 * tipus: DateTime  or  Text  or  URL
	 */
	public static final String TEMPORALCOVERAGE= "temporalCoverage";
	/**
	 * The textual content of this CreativeWork. 
	 * tipus: Text
	 */
	public static final String TEXT= "text";
	/**
	 * A thumbnail image relevant to the Thing. 
	 * tipus: URL
	 */
	public static final String THUMBNAILURL= "thumbnailUrl";
	/**
	 * Approximate or typical time it takes to work with or through this learning resource for the typical intended target audience, e.g. 'PT30M', 'PT1H25M'. 
	 * tipus: Duration
	 */
	public static final String TIMEREQUIRED= "timeRequired";
	/**
	 * The work that this work has been translated from. e.g. 物种起源 is a translationOf “On the Origin of Species”  Inverse property: workTranslation 
	 * tipus: CreativeWork
	 */
	public static final String TRANSLATIONOFWORK= "translationOfWork";
	/**
	 * Organization or person who adapts a creative work to different languages, regional differences and technical requirements of a target market, or that translates during some event. 
	 * tipus: Organization  or  Person
	 */
	public static final String TRANSLATOR= "translator";
	/**
	 * The typical expected age range, e.g. '7-9', '11-'. 
	 * tipus: Text
	 */
	public static final String TYPICALAGERANGE= "typicalAgeRange";
	/**
	 * The schema.org usageInfo property indicates further information about a CreativeWork. This property is applicable both to works that are freely available and to those that require payment or other transactions. It can reference additional information e.g. community expectations on preferred linking and citation conventions, as well as purchasing details. For something that can be commercially licensed, usageInfo can provide detailed, resource-specific information about licensing options.  This property can be used alongside the license property which indicates license(s) applicable to some piece of content. The usageInfo property can provide information about other licensing options, e.g. acquiring commercial usage rights for an image that is also available under non-commercial creative commons licenses. 
	 * tipus: CreativeWork  or  URL
	 */
	public static final String USAGEINFO= "usageInfo";
	/**
	 * The version of the CreativeWork embodied by a specified resource. 
	 * tipus: Number  or  Text
	 */
	public static final String VERSION= "version";
	/**
	 * An embedded video object. 
	 * tipus: Clip  or  VideoObject
	 */
	public static final String VIDEO= "video";
	/**
	 * Example/instance/realization/derivation of the concept of this creative work. eg. The paperback edition, first edition, or eBook.  Inverse property: exampleOfWork 
	 * tipus: CreativeWork
	 */
	public static final String WORKEXAMPLE= "workExample";
	/**
	 * A work that is a translation of the content of this work. e.g. 西遊記 has an English workTranslation “Journey to the West”,a German workTranslation “Monkeys Pilgerfahrt” and a Vietnamese translation Tây du ký bình khảo.  Inverse property: translationOfWork 
	 * tipus: CreativeWork
	 */
	public static final String WORKTRANSLATION= "workTranslation";
	
}
