package Bean;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import Controller.Controladora;
import Exceptions.Emptyfieldexception;
import Exceptions.InvalidNumberException;
import Model.Contato;
import Model.Telefone;
import Model.Usuario;

@ManagedBean(name = "agendaBean", eager = true)
@SessionScoped
public class Agenda implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String NOME_DO_ARQUIVO = "Agenda.txt";
	private String login;
	private String password;

	private String newLogin;
	private String newPassword;

	private ArrayList<Contato> contatos;
	private Controladora controladora;
	
	
	private String email;
	private Contato contato;
	private Telefone telefone;
	private Contato contatoSelecionado;
	private boolean telefoneInvalido;
	private String contatoValido;
	private String tipoDeBusca;
	private String busca;
	private ArrayList<Contato> resultadoBusca;

	public Agenda() {
		try {
			lerDados();
		} catch (IOException e) { }
		inicializarBean();
	}
	
	private void inicializarBean() {		
		this.login = "";
		this.password = "";
		this.newLogin = "";
		this.newPassword = "";

		this.email = "";
		this.contatoValido = "false";
		this.telefoneInvalido = false;
		this.contato = new Contato();
		this.telefone = new Telefone();
		this.contatoSelecionado = new Contato();
//		contatoSelecionado.setNome("Renan");
		this.busca = "";
		this.tipoDeBusca = "1";
		this.resultadoBusca = new ArrayList<Contato>();
	}

	public void persistirDados() throws IOException {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(NOME_DO_ARQUIVO);
			out = new ObjectOutputStream(fos);
			out.writeObject(contatos);
			out.writeObject(controladora.getUsuarios());
			out.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public void lerDados() throws IOException {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(NOME_DO_ARQUIVO);
			in = new ObjectInputStream(fis);
//			contatos = (ArrayList<Contato>) in.readObject();
			controladora = (Controladora) in.readObject();			
			in.close();
		} catch (IOException ex) {
			this.controladora = new Controladora();
			persistirDados();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	
	public String getNewLogin() {
		return newLogin;
	}

	public void setNewLogin(String newLogin) {
		this.newLogin = newLogin;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public String saveButton() {
		Usuario novoUsuario = new Usuario(newLogin, newPassword);
		if(this.controladora.addUsuario(novoUsuario)){
			msgUsuario("Usuário Cadastrado", "Seja Bem-vindo " + newLogin);
			inicializarBean();
			this.contatos = novoUsuario.getContatos();
			try {
				persistirDados();
			} catch (Exception e) {
			}
			return "index.seam";
		} else{
			msgUsuario("Usuário já existente", "Escolha outro login");
			return "";
		}
		
		
		
//		if(this.controladora.getUsuarios() == null){
//			this.controladora.setUsuarios(new ArrayList<Usuario>());
//		}
//		if (!this.controladora.getUsuarios().contains(temp)) {
//			this.controladora.addUsuario(temp);
//			msgUsuario("Usuário Cadastrado", "Seja Bem-vindo " + newLogin);
//			inicializarBean();
//			this.contatos = temp.getContatos();
//			try {
//				persistirDados();
//				return "index.seam";
//			} catch (Exception e) {
//			}
//		}
//		msgUsuario("Usuário já existente", "Escolha outro login");
//		return "";
	}

	public String loginButton() {
		ArrayList<Contato> contatos = this.controladora.buscaUsuario(login, password);
		if(contatos != null){
			this.contatos=contatos;
//			msgUsuario("Usuário logado", "Seja Bem-vindo " + login);
			return "index.seam";
		} else{
			msgUsuario("Login não realizado", "Senha errada ou Login inexistente");
			return "";	
		}
		
//		if (this.controladora.getUsuarios() != null) {
//			for (Usuario user : this.controladora.getUsuarios()) {
//				ArrayList<Contato> temp = user.confereLogin(login, password);
//				if (temp != null) {
//					this.contatos = temp;
//					msgUsuario("Usuário logado", "Seja Bem-vindo " + login);
//					return "index.seam";
//				}
//			}
//		}
//		System.out.println("CCCCCCCCCCCCCCCC");
//		msgUsuario("Login não realizado", "Senha errada ou Login inexistente");
//		return "";
	}

	public String logoffButton() {
		inicializarBean();
		return "Login.seam";
	}

	private void msgUsuario(String string1, String string2) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(string1, string2));
	}

//	public void inicializeArrays() {
//
//		Contato c1 = new Contato();
//		Contato c2 = new Contato();
//		Contato c3 = new Contato();
//		Contato c4 = new Contato();
//		Contato c5 = new Contato();
//		Telefone t = new Telefone();
//		Telefone t2 = new Telefone();
//		Telefone t3 = new Telefone();
//		Telefone t4 = new Telefone();
//		Telefone t5 = new Telefone();
//		t.setNumero("88221533");
//		t2.setNumero("88332343");
//		t3.setNumero("99832343");
//		t4.setNumero("99990000");
//		t5.setNumero("89343231");
//		t.setOperadora("OI");
//		t3.setCodigoRegional("81");
//		t3.setOperadoraLigar("31");
//		c1.setNome("Robert Silva Alves");
//		c2.setNome("Marta");
//		c3.setNome("Chris Santos");
//		c4.setNome("Roberto Carlos Campos da Silva");
//		c5.setNome("Gilberto");
//		c1.addEmail("RobertSA@gmail.com");
//		c3.addEmail("ChrisSantos@gmail.com");
//		c1.setIdade("40");
//		c2.setIdade("20");
//		c5.setIdade("60");
//		try {
//			c1.addTelefone(t);
//			c1.addTelefone(t3);
//			c1.addTelefone(t4);
//			c2.addTelefone(t2);
//			c3.addTelefone(t3);
//			c3.addTelefone(t2);
//			c4.addTelefone(t4);
//			c5.addTelefone(t5);
//		} catch (InvalidNumberException e) {
//		}
//		this.contatos = new ArrayList<Contato>(
//				Arrays.asList(c1, c2, c3, c4, c5));
//
//	}

	public void addTelefone(ActionEvent event) {
		this.telefoneInvalido = false;
		try {
			this.contato.addTelefone(telefone);
			persistirDados();
		} catch (InvalidNumberException e) {
			this.telefoneInvalido = true;
		} catch (IOException e) {
		}
		this.telefone = new Telefone();
	}

	

	public void removeEmail(String email) {
		this.contato.removeEmail(email);
		try {
			persistirDados();
		} catch (Exception e) {
		}
	}

	public void cancelarContato(ActionEvent event) {
		this.telefoneInvalido = false;
		this.contato = new Contato();
		try {
			persistirDados();
		} catch (Exception e) {
		}

	}

	public void addEmail() {
		this.contato.addEmail(this.email);
		this.email = "";
		try {
			persistirDados();
		} catch (Exception e) {
		}
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void addContato(ActionEvent event) throws Emptyfieldexception,
			InvalidNumberException {
		if (!contatos.contains(contato)) {
			contatos.add(contato);
		}
		contato = new Contato();
		this.telefone = new Telefone();
		try {
			this.persistirDados();
		} catch (Exception e) {
		}
	}

	public void removeTelefone(Telefone tel) {
		contato.removeTelefone(tel);
		try {
			persistirDados();
		} catch (Exception e) {
		}

	}

	public void setContato(Contato contato) {
		this.contato = contato;

	}

	public void setContatoSelecionado(Contato contatoSelecionado) {
		this.contatoSelecionado = contatoSelecionado;

	}

	public ArrayList<Contato> getContatos() {
		return contatos;
	}

	public Contato getContatoSelecionado() {
		return contatoSelecionado;
	}

	public void setSelectedContact(Contato selectedContact) {
		this.contatoSelecionado = selectedContact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;

	}

	public Contato getContato() {
		return contato;
	}

	public String getTipoDeBusca() {
		return tipoDeBusca;
	}

	public void setTipoDeBusca(String tipoDeBusca) {
		this.tipoDeBusca = tipoDeBusca;

	}

	public boolean getTelefoneInvalido() {
		return telefoneInvalido;
	}

	public String getContatoValido() {
		boolean temp = !(this.contato.getNome().equals("") || this.contato
				.getTelefones().isEmpty());
		this.contatoValido = new Boolean(temp).toString();
		return contatoValido;
	}

	public void fazBusca(AjaxBehaviorEvent event) {
		resultadoBusca = new ArrayList<Contato>();
		for (Contato cont : contatos) {

			boolean idadeBuscaValida = !(cont.getIdade().equals("") || this.busca
					.equals(""));

			if (tipoDeBusca.equals("1")) {
				boolean ehsubnome = cont.getNome().toLowerCase()
						.contains(busca.toLowerCase());
				boolean ehsubnumero = false;

				for (Telefone tel : cont.getTelefones()) {
					if (tel.getNumero().contains(busca.toLowerCase())) {
						ehsubnumero = true;
						break;
					}
				}
				if (ehsubnome || ehsubnumero) {
					resultadoBusca.add(cont);
				}
			}
			if (tipoDeBusca.equals("2") && idadeBuscaValida) {
				boolean menor = new Integer(cont.getIdade()) < new Integer(
						this.busca);
				if (menor) {
					resultadoBusca.add(cont);
				}
			}
			if (tipoDeBusca.equals("3")) {
				if (cont.getIdade().equals(this.busca) && idadeBuscaValida) {
					resultadoBusca.add(cont);
				}
			}
			if (tipoDeBusca.equals("4") && idadeBuscaValida) {
				boolean maior = new Integer(cont.getIdade()) > new Integer(
						this.busca);
				if (maior) {
					resultadoBusca.add(cont);
				}
			}

		}
	}

	public ArrayList<Contato> getResultadoBusca() {
		return resultadoBusca;
	}
}
