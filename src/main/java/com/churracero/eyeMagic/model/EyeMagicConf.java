package com.churracero.eyeMagic.model;

import java.io.Serializable;

public class EyeMagicConf implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Boolean active;

	public EyeMagicConf() {
		super();
		this.active = Boolean.FALSE;
	}
	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Configuration [active=" + active + "]";
	}
}
