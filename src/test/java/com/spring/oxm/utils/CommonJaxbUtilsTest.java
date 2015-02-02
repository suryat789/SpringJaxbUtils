package com.spring.oxm.utils;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.XmlMappingException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.spring.oxm.objects.Person;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class CommonJaxbUtilsTest {

	@Autowired
	private CommonJaxbUtils jaxbUtils;
	private static String strXML;
	private static Person person;

	@BeforeClass
	public static void setupPersonObject() {
		person = new Person();
		person.setFirstname("Phil");
		person.setLastname("Steffensen");
		person.setDeveloper(true);
	}

	@Test
	public void firstTestGetXMLFromObject() throws XmlMappingException, IOException {
		strXML = jaxbUtils.getXMLFromObject(person);
		// System.out.println(strXML);
		Assert.notNull(strXML, "Output XML is Null");
	}

	@Test
	public void secondTestGetObjectFromXml() throws XmlMappingException, IOException {
		Person personTest = null;
		personTest = jaxbUtils.getObjectFromXml(strXML);
		// System.out.println(person);
		Assert.notNull(personTest, "Person object is Null");
	}

}
