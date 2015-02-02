package com.spring.oxm.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.XmlMappingException;

public class CommonJaxbUtils extends AbstractCommonJaxbConfig {

	public <T> String getXMLFromObject(T object) throws XmlMappingException, IOException {
		StringWriter writer = new StringWriter();
		getMarshaller().marshal(object, new StreamResult(writer));
		return writer == null ? null : writer.toString();
	}

	@SuppressWarnings("unchecked")
	public <T> T getObjectFromXml(String inputXML) throws XmlMappingException, IOException {
		T newObject = null;
		newObject = (T) getUnmarshaller().unmarshal(new StreamSource(new StringReader(inputXML)));
		return newObject;
	}
}
