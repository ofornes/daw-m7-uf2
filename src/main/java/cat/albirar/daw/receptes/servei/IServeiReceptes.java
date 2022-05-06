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
package cat.albirar.daw.receptes.servei;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import cat.albirar.daw.receptes.models.CategoriaPesBean;
import cat.albirar.daw.receptes.models.ComentariBean;
import cat.albirar.daw.receptes.models.KeywordPesBean;
import cat.albirar.daw.receptes.models.ReceptaBean;

/**
 * Servei per a obtenir dades de receptes.
 * Obté les dades i les retorna en format json segons l'especificació de google.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 0.0.1
 */
@Validated
public interface IServeiReceptes {
	/**
	 * Produeix un text json amb les dades de la recepta segons l'esquema {@code Recipe}.
	 * @param fnUrl Funció per a muntar la URL d'obtenció de la recepta
	 * @param recepta La recepta 
	 * @return El json formatat
	 */
	public String produirJsonLd(@NotNull Function<ReceptaBean, String> fnUrl, @NotNull ReceptaBean recepta);
	/**
	 * Produeix un text json amb la llista de receptes segons l'esquema {@code ItemList}.
	 * Per exemple:
	 * <pre>
	 * {
	 *   "@context": "http://schema.org/", 
	 *   "@type": "ItemList", 
	 *   "itemListElement": [
	 *     {
	 *       "@type": "ListItem", 
	 *       "position": 1, 
	 *       "url": "https://www.directoalpaladar.com/postres/como-hacer-torrijas-pan-azucar-version-saludable-baja-hidratos-gluten-que-habia-que-probar"
	 *     }, 
	 *     {
	 *       "@type": "ListItem", 
	 *       "position": 2, 
	 *       "url": "https://www.directoalpaladar.com/recetas-vegetarianas/judias-verdes-tomate-a-libanesa-receta-sencilla-para-saludable-guarnicion-comida-vegana"
	 *     }, 
	 * ...
	 *     {
	 *       "@type": "ListItem", 
	 *       "position": 20, 
	 *       "url": "https://www.directoalpaladar.com/recetas-de-legumbres-y-verduras/receta-pochas-langostinos-sepia-guiso-legumbres-marinero-facil-saludable-sabrosisimo"
	 *     }
	 *   ]
	 * }
	 * </pre> 
	 * @param fnUrl La funció per a compondre la url de cada recepta
	 * @param receptes La llista de receptes
	 * @return El json formatat
	 */
	public String produirJsonLd(@NotNull Function<ReceptaBean, String> fnUrl, @NotNull Collection<ReceptaBean> receptes); 
	/**
	 * Obté les metadades de la recepte amb l'{@code id} indicat.
	 * @param id L'id de la recepta
	 * @return Les metadades en format json
	 */
	public ReceptaBean receptaPerId(@Min(1) long id);
	/**
	 * Cerca les receptes per categoria.
	 * @param categoria El nom de la categoria
	 * @return La llista corresponent
	 */
	public List<ReceptaBean> receptesPerCategoria(@NotBlank String categoria);
	/**
	 * Cerca les receptes per keyword.
	 * @param keyword El nom del keyword
	 * @return La llista corresponent
	 */
	public List<ReceptaBean> receptesPerKeyword(@NotBlank String keyword);
	
	/**
	 * Obté una llista amb {@code nombre} de receptes de manera aleatòria.
	 * @param nombre El nombre de receptes a obtenir
	 * @return La llista amb el {@code nombre} de receptes aleatòries
	 */
	public List<ReceptaBean> receptesAleatories(@Min(1)  @Max(10) int nombre);
	/**
	 * Cerca totes les receptes.
	 * @return Les receptes
	 */
	public List<ReceptaBean> receptes();
	/**
	 * Retorna la llista de categories
	 * @return La llista amb totes les categories
	 */
	public List<CategoriaPesBean> categories();
	/**
	 * Retorna la informació de la categoria amb el {@code nom} indicat.
	 * @param nom El nom de la categoria
	 * @return La informació de la categoria
	 */
	public CategoriaPesBean categoria(String nom);
	/**
	 * Retorna la informació del keyword amb el {@code nom} indicat.
	 * @param nom El nom del keyword
	 * @return La informació del keyword
	 */
	public KeywordPesBean keyword(String nom);
	/**
	 * Afegeix un comentari a una recepta. 
	 * @param comentari El comentari
	 */
	public void addComentari(ComentariBean comentari);
}
