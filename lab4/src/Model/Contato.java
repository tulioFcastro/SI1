package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import Exceptions.InvalidNumberException;

public class Contato implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private String descricao;
	private String idade;
	private List<Telefone> telefones;
	private List<String> emails;
	private Integer quantidadeTelefones;

	public Contato() {
		this.nome = "";
		this.idade = "";
		this.descricao = "Não existe descrição para este contato.";
		this.emails = new ArrayList<String>();
		this.telefones = new ArrayList<Telefone>();
		this.quantidadeTelefones = 0;
	}

	public Contato(String name, Telefone number) throws InvalidNumberException {
		this.nome = name;
		this.idade = "";
		this.descricao = "";
		this.emails = new ArrayList<String>();
		this.telefones = new ArrayList<Telefone>();
		this.quantidadeTelefones = 0;
		this.addTelefone(number);
	}

	public boolean isContatoValido() {
		boolean contatoValido = !(this.nome.equals("") || this.telefones
				.isEmpty());
		return contatoValido;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public String getQuantidadeTelefones() {
		return quantidadeTelefones.toString();
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String name) {
		if (!name.equals("")) {
			this.nome = name;
		} else{
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Nome inválido", "Digite um nome válido"));
		}
	}

	public void addTelefone(Telefone t) throws InvalidNumberException {
		if (!t.ehTelefoneValido() || telefones.contains(t)) {
			throw new InvalidNumberException();
		}
		telefones.add(t);
		quantidadeTelefones++;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Contato) {
			boolean isNameEqual = this.nome.equals(((Contato) o).getNome());
			return isNameEqual;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return nome.hashCode();
	}

	public void addEmail(String email) {
		this.emails.add(email);

	}

	public void removeEmail(String email) {
		this.emails.remove(email);

	}

	public void removeTelefone(Telefone tel) {
		if (telefones.size() > 1) {
			this.telefones.remove(tel);
			this.quantidadeTelefones--;
		}
	}

	@Override
	public String toString() {
		return nome;
	}

}
