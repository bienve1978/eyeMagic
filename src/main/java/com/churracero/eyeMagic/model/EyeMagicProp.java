package com.churracero.eyeMagic.model;

import java.io.Serializable;
import java.util.List;

import javax.mail.internet.InternetAddress;

public class EyeMagicProp implements Serializable{

	private static final long serialVersionUID = 1L;
	private String uri;
	private String port;
	private String logFile;
	private List<InternetAddress> emails;
	private String captDir;
	private Integer captSec;
	
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getLogFile() {
		return logFile;
	}
	public void setLogFile(String logFile) {
		this.logFile = logFile;
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
	public String getCaptDir() {
		return captDir;
	}
	public void setCaptDir(String captDir) {
		this.captDir = captDir;
	}
	public Integer getCaptSec() {
		return captSec;
	}
	public void setCaptSec(Integer captSec) {
		this.captSec = captSec;
	}
	@Override
	public String toString() {
		return "EyeMagicProp [uri=" + uri + ", port=" + port + ", logFile=" + logFile + ", emails=" + emails
				+ ", captDir=" + captDir + ", captSec=" + captSec + "]";
	}
}
