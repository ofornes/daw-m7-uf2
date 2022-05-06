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
package cat.albirar.daw.receptes.test.repositoris.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cat.albirar.daw.receptes.models.ComentariBean;
import cat.albirar.daw.receptes.models.ReceptaBean;
import cat.albirar.daw.receptes.repositoris.IRepoReceptes;
import cat.albirar.daw.receptes.repositoris.impl.RepoReceptesImpl;
import cat.albirar.daw.receptes.test.ReceptesSaludablesConfigurationTest;
import cat.albirar.daw.receptes.test.TestDadesComunsAbstracte;

/**
 * Provatures de {@link RepoReceptesImpl}.
 * @author Octavi Fornés <a href="mailto:octavi@fornes.cat">&lt;octavi@fornes.cat&gt;</a>
 * @since 0.0.1
 */
@ContextConfiguration(classes = ReceptesSaludablesConfigurationTest.class)
@ExtendWith(SpringExtension.class)
public class RepoReceptesImplTest extends TestDadesComunsAbstracte {
	@Autowired
	private IRepoReceptes repo;
	
	@Test
	public void whenSaveCommentAndReadRecipe_Then_TheRecipeHasTheComent() {
		ComentariBean cEx1, cEx2, cAc;
		List<ComentariBean> lComentaris;
		ReceptaBean rEx, rAc;

		rEx = mostres.get(2);
		cEx1 = ComentariBean.builder()
				.id(1L)
				.idRecepta(rEx.getId())
				.tsCreacio(Instant.now().minus(458, ChronoUnit.MINUTES))
				.autor("Autor TEST")
				.text("Contingut comentari TEST")
				.build()
				;
		cEx2 = ComentariBean.builder()
				.id(2L)
				.idRecepta(rEx.getId())
				.tsCreacio(Instant.now().minus(612, ChronoUnit.MINUTES))
				.autor("Autor 2 TEST")
				.text("Contingut 2 comentari TEST")
				.valoracio(Optional.of((short)75))
				.build()
				;
		rEx = rEx.toBuilder()
				.comentaris(Arrays.asList(cEx1, cEx2))
				.build()
				;
		repo.addComentari(cEx1);
		repo.addComentari(cEx2);
		lComentaris = repo.findComentarisByReceptaId(rEx.getId());
		assertNotNull(lComentaris);
		assertFalse(lComentaris.isEmpty());
		assertEquals(2, lComentaris.size());
		cAc = lComentaris.get(0);
		assertNotNull(cAc);
		assertEquals(cEx1, cAc);
		
		cAc = lComentaris.get(1);
		assertNotNull(cAc);
		assertEquals(cEx2, cAc);
		
		rAc = repo.findById(rEx.getId()).get();
		assertNotNull(rAc);
		assertEquals(rEx, rAc);
	}
	
	@Test
	public void whenFindByIdWithExisting_Then_TheReceptaIsGet() {
		ReceptaBean actual;
		Optional<ReceptaBean> or;
		
		for(ReceptaBean expected : mostres) {
			or = repo.findById(expected.getId());
			assertNotNull(or);
			assertTrue(or.isPresent());
			actual = or.get();
			assertEquals(expected, actual);
			
		}
	}
}
