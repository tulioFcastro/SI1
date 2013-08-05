package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String login;
	private String password;
	private ArrayList<Contato> contatos;
	
	public Usuario(String login, String password){
		this.login = login;
		this.password = password;
		this.contatos = new ArrayList<Contato>();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Contato> getContatos() {
		return contatos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Usuario)) return false;
		return ((Usuario)obj).getLogin().equals(this.getLogin());
		
	}

	public void addContato(Contato contato){
		if(!this.contatos.contains(contato)){
			this.contatos.add(contato);
		}
	}
	
	public void removeContato(Contato contato){
		if(this.contatos.contains(contato)){
			this.contatos.remove(contato);
		}
	}
	
	public ArrayList<Contato> confereLogin(String login, String password){
		if(this.login.equals(login) && this.password.equals(password)) {
			return this.contatos;
		}
		return null;
		
	}
	
	@Override
	public String toString(){
		return this.login;
	}
	
}
