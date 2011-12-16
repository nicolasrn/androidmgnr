package com.android.metier;

public class DataConnexion {
	private String pseudo, ip, port;
	private String idAvatar;
	
	public DataConnexion(String pseudo, String ip, String port, String idAvatar) {
		this.pseudo = pseudo;
		this.ip = ip;
		this.port = port;
		this.idAvatar = idAvatar;
	}

	@Override
	public String toString() {
		return "DataConnexion [pseudo=" + pseudo + ", ip=" + ip + ", port="
				+ port + ", idAvatar=" + idAvatar + "]";
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getIdAvatar() {
		return idAvatar;
	}

	public void setIdAvatar(String idAvatar) {
		this.idAvatar = idAvatar;
	}
}
