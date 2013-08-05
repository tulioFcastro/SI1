package Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import Model.Contato;
import Model.Usuario;

public class Controladora implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Usuario> usuarios;
	
	public Controladora(){
		this.usuarios = new ArrayList<Usuario>();
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public boolean addUsuario(Usuario usuario){
		if(!this.usuarios.contains(usuario)){
			this.usuarios.add(usuario);
			return true;
		} else{
			return false;
		}
	}
	
	public void removeUsuario(Usuario usuario){
		if(this.usuarios.contains(usuario)){
			this.usuarios.remove(usuario);
		}
	}
	
	public ArrayList<Contato> buscaUsuario(String login, String password){
		ArrayList<Contato> resposta = null;
		for (Usuario usuario : usuarios) {
			resposta = usuario.confereLogin(login, password);
			if(resposta != null){
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Usuário Logado", "Seja bem vindo" + login));
				return resposta;
			}
		}
		return resposta;
	}
	
	@Override
	public String toString(){
		String retorno ="";
		for (Usuario usuario : this.usuarios) {
			retorno += usuario + ",";
		}
		
		return retorno;
	}
}
