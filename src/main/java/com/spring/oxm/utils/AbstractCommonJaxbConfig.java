package com.spring.oxm.utils;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.castor.CastorMarshaller;

public abstract class AbstractCommonJaxbConfig {

	private Marshaller marshaller;
	private Unmarshaller unmarshaller;

	private CastorMarshaller castorMarshaller;

	public Marshaller getMarshaller() {
		return marshaller;
	}

	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	@Bean
	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	public CastorMarshaller getCastorMarshaller() {
		return castorMarshaller;
	}

	public void setCastorMarshaller(CastorMarshaller castorMarshaller) {
		this.castorMarshaller = castorMarshaller;
	}

	abstract public <T> String getXMLFromObject(T object) throws XmlMappingException, IOException;

	abstract public <T> T getObjectFromXml(String inputXML) throws XmlMappingException, IOException;

}
