package com.jmk.bjjd.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.SerializationUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

import com.jmk.bjjd.models.MemberModel;
import com.jmk.bjjd.web.config.SpringWebMvcConfig;
import com.jmk.bjjd.web.config.SpringWebSecurityConfig;
import com.jmk.bjjd.web.facade.MemberMgmtServiceFacade;
import com.jmk.bjjd.web.facade.SevaCategoryMgmtServiceFacade;
import com.jmk.bjjd.web.validators.MemberFormModelValidator;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SpringWebMvcConfig.class,SpringWebSecurityConfig.class})
@WebAppConfiguration
@Ignore
public class MemberControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	@Mock
	private MemberMgmtServiceFacade memberMgmtServiceFacade;
	
	@Mock
	private SevaCategoryMgmtServiceFacade sevaCategoryMgmtServiceFacade;
	
	@Mock
	private MemberFormModelValidator sevaModelValidator;
	
	@InjectMocks
	private MemberController memberController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		/**
		 * Approach 1 - Not Good
		 * The Standalone configuration allows us to register one or more controllers (classes annotated with the @Controller annotation) and
		 *  configure the Spring MVC infrastructure programatically. This approach is a viable option if our Spring MVC configuration is simple and straight-forward.
		 */
		//mockMvc=MockMvcBuilders.standaloneSetup(memberController).build();
		/**
		 * Approach 2 - Good
		 * The WebApplicationContext based configuration allows us the configure Spring MVC infrastructure by using a fully initialized WebApplicationContext. 
		 * This approach is better if our Spring MVC configuration is so complicated that using standalone configuration does not make any sense.
		 */
		mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
		
	}
	
	
	@Test
	@Ignore
	public void testMemberSavePage() throws Exception{
		
		MemberModel memberModel=(MemberModel)SerializationUtils.deserialize(new FileInputStream("F:\\Member1.ser"));
		System.out.println(memberModel);
		mockMvc.perform(MockMvcRequestBuilders.post("/member/saveMember")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("description", "sdfs")
                .param("title", "title")
                .sessionAttr("memberModel", memberModel)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
               ;
	}
	
	@Test
	public void testMemberSavePage1() throws Exception{
		
		try (FileInputStream fis = new FileInputStream(new File("F:/JAVA/apache-tomcat-8.0.36/bjjd/member_photos/RAJIV.jpeg"));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
			int b;
			byte[] buffer = new byte[1024*10];
			while ((-1!= fis.read(buffer))) {
				baos.write(buffer);
			}

			byte[] fileBytes = baos.toByteArray();
			String encodedString = Base64.encodeBase64String(fileBytes);
			System.out.println("FILEPATH2: "+fileBytes);
			System.out.println("FILEPATH3: "+encodedString);
			ModelMap map = new ModelMap();
			map.put("imageContent", encodedString);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@After
	public void destroy(){
	}
	
	
}
