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
package cat.albirar.daw.receptes;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import cat.albirar.daw.receptes.controladors.ControladorWeb;
import cat.albirar.daw.receptes.repositoris.impl.RepoReceptesImpl;
import cat.albirar.daw.receptes.repositoris.mappers.CategoriaPesBeanMapper;
import cat.albirar.daw.receptes.servei.impl.ServeiReceptesImpl;

/**
 * Configuració de l'aplicació.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 0.0.1
 */
@Configuration
@ComponentScan(basePackageClasses = {
		ControladorWeb.class
		, RepoReceptesImpl.class
		, CategoriaPesBeanMapper.class
		, ServeiReceptesImpl.class
})
public class ReceptesSaludablesConfiguration {
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("db/esquema.sql")
                .addScripts("db/dades.sql")
                .build();
    }
    @Bean
    public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource) {
    	 return new JdbcTemplate(dataSource);
    }
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Autowired DataSource dataSource) {
    	 return new NamedParameterJdbcTemplate(dataSource);
    }
}
