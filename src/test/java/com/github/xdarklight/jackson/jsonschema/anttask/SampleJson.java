package com.github.xdarklight.jackson.jsonschema.anttask;

import org.junit.Ignore;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@Ignore
@JsonAutoDetect
public class SampleJson {

	private String string;
	private int integer;

	public String getString() {
		return string;
	}

	public void setString(final String string) {
		this.string = string;
	}

	public int getInteger() {
		return integer;
	}

	public void setInteger(final int integer) {
		this.integer = integer;
	}

}
