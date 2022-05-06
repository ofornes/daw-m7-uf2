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

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;

import cat.albirar.daw.receptes.controladors.ControladorWeb;
import cat.albirar.daw.receptes.jsonld.JsonLdBuilder;
import cat.albirar.daw.receptes.markdown.ProcessadorMD;
import cat.albirar.daw.receptes.repositoris.impl.RepoReceptesImpl;
import cat.albirar.daw.receptes.repositoris.mappers.CategoriaPesBeanMapper;
import cat.albirar.daw.receptes.servei.impl.ServeiReceptesImpl;

/**
 * Configuració de l'aplicació.
 * 
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 0.0.1
 */
@Configuration
@ComponentScan(basePackageClasses = { ControladorWeb.class, RepoReceptesImpl.class, CategoriaPesBeanMapper.class,
		ServeiReceptesImpl.class, ProcessadorMD.class, JsonLdBuilder.class })
@EnableTransactionManagement
@EnableConfigurationProperties
public class ReceptesSaludablesConfiguration {
	@Bean
	public DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator rdp;
		
		rdp = new ResourceDatabasePopulator();
		rdp.addScript(new ClassPathResource("db/esquema.sql"));
		rdp.addScripts(new ClassPathResource("db/dades.sql"));
		return rdp;
	}
	@Bean
	public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Autowired DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	@Bean
    public PlatformTransactionManager txManager(@Autowired DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
	@Bean
	public DataHolder flexmarkOptions() {
		return new MutableDataSet();
	}

	@Bean
	public Parser parser(@Autowired DataHolder options) {
		return Parser.builder(options).build();
	}

	@Bean
	public HtmlRenderer htmlRenderer(@Autowired DataHolder options) {
		return HtmlRenderer.builder(options).build();
	}
}
