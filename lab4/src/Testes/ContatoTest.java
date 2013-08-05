package Testes;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Exceptions.InvalidNumberException;
import Model.Contato;
import Model.Telefone;

public class ContatoTest {

	private Contato contato1;
	private Contato contato3;

	private Telefone telefone1;
	private Telefone telefone2;
	private Telefone telefone3;

	@Before
	public void init() {
		this.contato1 = new Contato();
		new Contato();

		this.telefone1 = new Telefone();
		this.telefone2 = new Telefone();
		
		this.telefone3 = new Telefone();
		this.telefone3.setNumero("12345678");
		
	}



	
	@Test
	public void testConstrutor() {
		//Inicializado Contato default;
		Assert.assertEquals(contato1.getNome(), "");
		Assert.assertEquals(contato1.getIdade(), "");
		Assert.assertEquals(contato1.getDescricao(), "Nao existe descricao para este contato.");
		Assert.assertEquals(contato1.getEmails(), new ArrayList<String>());
		Assert.assertEquals(contato1.getTelefones(), new ArrayList<Telefone>());
		Assert.assertEquals(contato1.getQuantidadeTelefones(), (new Integer(0)).toString());
		
		//Inicializando Contato com entrada;
		try {
			this.contato3 = new Contato("Teste", telefone3);
		} catch (InvalidNumberException e) {
			Assert.fail();
		}
		Assert.assertEquals(contato3.getNome(), "Teste");
		Assert.assertEquals(contato3.getIdade(), "");
		Assert.assertEquals(contato3.getDescricao(), "Nao existe descricao para este contato.");
		Assert.assertEquals(contato3.getEmails(), new ArrayList<String>());
		//Testando os telefones cadastrados
		ArrayList<Telefone> teste = new ArrayList<Telefone>();
		teste.add(telefone3);
		Assert.assertEquals(contato3.getTelefones(), teste);
		Assert.assertEquals(contato3.getQuantidadeTelefones(), (new Integer(1)).toString());
	}

	@Test
	public void testAddsERemoves(){
		List<Telefone> telefones = new ArrayList<Telefone>();
		//Telefone1 é inválido pois não tem número
		try {
			contato1.addTelefone(telefone1);
			Assert.fail();
		} catch (InvalidNumberException e) {
		}
		//Telefone3 é válido pois tem número
		try {
			contato1.addTelefone(telefone3);
			telefones.add(telefone3);
		} catch (InvalidNumberException e) {
			Assert.fail();
		}

		Assert.assertEquals(contato1.getTelefones(), telefones);
		
		//Como é o único telefone, não poderá remover
		contato1.removeTelefone(telefone3);
		Assert.assertEquals(contato1.getTelefones(), telefones);
		
		//Adicionando outro telefone válido
		telefone1.setNumero("12345677");
		try {
			contato1.addTelefone(telefone1);
			telefones.add(telefone1);
		} catch (InvalidNumberException e) {
			Assert.fail();
		}
		
		Assert.assertEquals(contato1.getTelefones(), telefones);
		
		//Não adiciona telefones repetidos
		telefone2.setNumero("12345678"); 
		try {
			contato1.addTelefone(telefone2);
			Assert.fail();
		} catch (InvalidNumberException e) {
		}
		
		Assert.assertEquals(contato1.getTelefones(), telefones);
		contato1.removeTelefone(telefone3);
		
		contato1.addEmail("EMAIL");
		List<String> emails = new ArrayList<String>();
		emails.add("EMAIL");
		Assert.assertEquals(contato1.getEmails(), emails);
		
		contato1.removeEmail("EMAIL");
		emails.remove("EMAIL");
		Assert.assertEquals(contato1.getEmails(), emails);
		
	}
	
}
