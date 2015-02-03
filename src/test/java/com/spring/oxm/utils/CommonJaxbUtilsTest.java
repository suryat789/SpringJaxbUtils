package com.spring.oxm.utils;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.MarshallingFailureException;
import org.springframework.oxm.UnmarshallingFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.oxm.objects.Person;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class CommonJaxbUtilsTest {

	@Autowired
	private CommonJaxbUtils jaxbUtils;
	private static Person person;

	@BeforeClass
	public static void setupPersonObject() {
		person = new Person();
		person.setFirstname("Phil");
		person.setLastname("Steffensen");
		person.setDeveloper(true);
		
		// Friend 1
		Person friend1 = new Person();
		friend1.setFirstname("Steve");
		friend1.setLastname("Parker");
		friend1.setDeveloper(false);
		
		// Friend 2
		Person friend2 = new Person();
		friend2.setFirstname("Neena");
		friend2.setLastname("Kochhar");
		friend2.setDeveloper(true);
		
		// Friend 3
		Person friend3 = new Person();
		friend3.setFirstname("King");
		friend3.setLastname("Pearson");
		friend3.setDeveloper(true);
		
		person.addFriend(friend1);
		person.addFriend(friend2);
		person.addFriend(friend3);
	}

	@Test
	public void testGetXMLFromObject() throws Exception {
		String strXML = jaxbUtils.getXMLFromObject(person);
		Assert.assertNotNull("Output XML is Null", strXML);
		Assert.assertNotEquals("Output XML is Blank", strXML, "");
	}
	
	@Test(expected = MarshallingFailureException.class)
	public void testGetXMLFromNull() throws Exception {
		String strXML = jaxbUtils.getXMLFromObject(null);
		Assert.assertNull("Output XML is Not Null", strXML);
	}
	
	@Test
	public void testGetObjectFromXml1() throws Exception {
		Person personTest = null;
		String strXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><person developer=\"true\"><lastname>Steffensen</lastname><firstname>Phil</firstname></person>";
		personTest = jaxbUtils.getObjectFromXml(strXML);
		Assert.assertNotNull("Person object is Null", personTest);
	}
	
	@Test
	public void testGetObjectFromXml2() throws Exception {
		Person personTest = null;
		String strXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><person developer=\"true\"><friends xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" developer=\"false\" xsi:type=\"java:com.spring.oxm.objects.Person\"><lastname>Parker</lastname><firstname>Steve</firstname></friends><friends xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" developer=\"true\" xsi:type=\"java:com.spring.oxm.objects.Person\"><lastname>Kochhar</lastname><firstname>Neena</firstname></friends><friends xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" developer=\"true\" xsi:type=\"java:com.spring.oxm.objects.Person\"><lastname>Pearson</lastname><firstname>King</firstname></friends><lastname>Steffensen</lastname><firstname>Phil</firstname></person>";
		personTest = jaxbUtils.getObjectFromXml(strXML);
		Assert.assertNotNull("Person object is Null", personTest);
		Assert.assertEquals("Person objects are not same", person, personTest);
	}

	public void testGetObjectFromXml3() throws Exception {
		Person personTest = null;
		String strXML = "<person developer=\"true\"><friends xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" developer=\"false\" xsi:type=\"java:com.spring.oxm.objects.Person\"><lastname>Parker</lastname><firstname>Steve</firstname></friends><friends xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" developer=\"true\" xsi:type=\"java:com.spring.oxm.objects.Person\"><lastname>Kochhar</lastname><firstname>Neena</firstname></friends><friends xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" developer=\"true\" xsi:type=\"java:com.spring.oxm.objects.Person\"><lastname>Pearson</lastname><firstname>King</firstname></friends><lastname>Steffensen</lastname><firstname>Phil</firstname></person>";
		personTest = jaxbUtils.getObjectFromXml(strXML);
		Assert.assertNotNull("Person object is Null", personTest);
		System.out.println(personTest.equals(person));
		Assert.assertEquals("Person objects are not same", person, personTest);
	}
	
	@Test(expected = UnmarshallingFailureException.class)
	public void testGetObjectFromXml4() throws Exception {
		Person personTest = null;
		String strXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		personTest = jaxbUtils.getObjectFromXml(strXML);
		Assert.assertNull("Person object is Not Null", personTest);
		Assert.assertNotEquals("Person objects are same", person, personTest);
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetObjectFromXml5() throws Exception {
		Person personTest = null;
		String strXML = null;
		personTest = jaxbUtils.getObjectFromXml(strXML);
		Assert.assertNull("Person object is Not Null", personTest);
		Assert.assertNotEquals("Person objects are same", person, personTest);
	}
	
}
