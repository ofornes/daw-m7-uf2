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
package cat.albirar.daw.receptes.test;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import cat.albirar.daw.receptes.ReceptesSaludablesConfiguration;

/**
 * Configuració dels tests.
 * 
 * @author Octavi Fornés
 *         <a href="mailto:octavi@fornes.cat">&lt;octavi@fornes.cat&gt;</a>
 * @since 0.0.1
 */
@Configuration
@Import(ReceptesSaludablesConfiguration.class)
public class ReceptesSaludablesConfigurationTest {
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.generateUniqueName(true)
				.setType(EmbeddedDatabaseType.H2)
				.setScriptEncoding("UTF-8")
				.ignoreFailedDrops(true)
				.addScript("db/esquema.sql")
				.addScripts("db/dades.sql")
				.build();
	}

}
