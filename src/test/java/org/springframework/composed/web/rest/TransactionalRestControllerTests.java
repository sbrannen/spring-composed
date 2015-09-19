/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.composed.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Kazuki Shimizu
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class TransactionalRestControllerTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUpMockMvc() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testTransactionalRestController() throws Exception {
		mockMvc.perform(get("/TransactionalRestController"))
				.andExpect(status().isOk())
				.andExpect(content().string("{\"beanName\":\"transactionalTestController\",\"withinTransaction\":true}"));
	}

	@Configuration
	@EnableWebMvc
	@EnableTransactionManagement
	@ComponentScan(includeFilters = @ComponentScan.Filter(classes = TransactionalRestController.class), useDefaultFilters = false)
	static class Config {

		// Mock datasource
		@Bean
		public DataSource dataSource() throws SQLException {
			DataSource dataSource = mock(DataSource.class);
			when(dataSource.getConnection()).thenReturn(mock(Connection.class), mock(Connection.class));
			return dataSource;
		}

		@Bean
		public DataSourceTransactionManager transactionManager() throws SQLException {
			return new DataSourceTransactionManager(dataSource());
		}

	}

	@RequestMapping("/TransactionalRestController")
	@TransactionalRestController(value = "transactionalTestController", propagation = Propagation.REQUIRES_NEW)
	static class TestController implements BeanNameAware {

		@Autowired
		DataSource dataSource;

		private String beanName;

		@GetResource
		public Map<String, Object> get() {
			Map<String, Object> attributes = new LinkedHashMap<>();
			attributes.put("beanName", beanName);
			attributes.put("withinTransaction", DataSourceUtils.getConnection(dataSource) == DataSourceUtils.getConnection(dataSource));
			return attributes;
		}

		@Override
		public void setBeanName(String name) {
			this.beanName = name;
		}

	}

}
