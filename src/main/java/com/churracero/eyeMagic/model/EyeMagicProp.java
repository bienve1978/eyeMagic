package com.churracero.eyeMagic.model;

import java.io.Serializable;
import java.util.List;

import javax.mail.internet.InternetAddress;

public class EyeMagicProp implements Serializable{

	private static final long serialVersionUID = 1L;
	private String host;
	private String uri;
	private String port;
	private List<InternetAddress> emails;
	private String captPath;
	private Integer captSec;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public List<InternetAddress> getEmails() {
		return emails;
	}
	public void setEmails(List<InternetAddress> emails) {
		this.emails = emails;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getCaptPath() {
		return captPath;
	}
	public void setCaptDir(String captPath) {
		this.captPath = captPath;
	}
	public Integer getCaptSec() {
		return captSec;
	}
	public void setCaptSec(Integer captSec) {
		this.captSec = captSec;
	}
	@Override
	public String toString() {
		return "EyeMagicProp [Host=" + host + ", uri=" + uri + ", port=" + port + ", emails=" + emails + ", captPath="
				+ captPath + ", captSec=" + captSec + "]";
	}
}
