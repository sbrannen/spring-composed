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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Kazuki Shimizu
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class RestTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUpMockMvc() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void getMessage() throws Exception {
		mockMvc.perform(
				get("/messages/MSG001"))
				.andExpect(status().isOk())
				.andExpect(content().string("get MSG001"));

	}

	@Test
	public void postMessage() throws Exception {
		mockMvc.perform(
				post("/messages").contentType(MediaType.TEXT_PLAIN).content("MSG002"))
				.andExpect(status().isCreated())
				.andExpect(content().string("post MSG002"));

	}

	@Test
	public void putMessage() throws Exception {
		mockMvc.perform(
				put("/messages/MSG003").contentType(MediaType.TEXT_PLAIN).content("MSG003"))
				.andExpect(status().isNoContent())
				.andExpect(content().string(""));

	}


	@Test
	public void deleteMessage() throws Exception {
		mockMvc.perform(
				delete("/messages/MSG003"))
				.andExpect(status().isNoContent())
				.andExpect(content().string(""));

	}


	@Configuration
	@EnableWebMvc
	@ComponentScan
	static class Config {}

	@RequestMapping("messages")
	@RestController
	static class MessageController {

		@GetResource("{id}")
		String getMessage(@PathVariable String id) {
			return "get " + id;
		}

		@PostResource
		String postMessage(@RequestBody String message) {
			return "post " + message;
		}


		@PutResource("{id}")
		void putMessage(@PathVariable String id, @RequestBody String message) {
		}


		@DeleteResource("{id}")
		void deleteMessage(@PathVariable String id) {
		}

	}


}
