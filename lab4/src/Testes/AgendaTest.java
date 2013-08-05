package Testes;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import Bean.Agenda;
import Exceptions.InvalidNumberException;
import Model.Contato;
import Model.Telefone;


public class AgendaTest {
	Agenda agenda;
	Contato contato;
	Contato contato2;
	Telefone telefone;
	Telefone telefone2;

	@Before
	public void init() {
		this.agenda = new Agenda();
		this.contato = new Contato();
		this.contato2 = new Contato();
		this.telefone = new Telefone();
		this.telefone2 = new Telefone();
	}

	@Test
	public void testaddTelefone() {
		try {
			telefone.setOperadoraLigar("82");
			telefone.setOperadora("oi");
			contato.addTelefone(telefone);
			fail("not InvalidNumberException");
		} catch (InvalidNumberException e) {
		}

		try {
			telefone.setCodigoRegional("13");
			contato.addTelefone(telefone);
			fail("not InvalidNumberException");
		} catch (InvalidNumberException e) {
		}

		try {
			telefone.setNumero("88888888");
			contato.addTelefone(telefone);
		} catch (InvalidNumberException e) {
			fail("InvalidNumberException");
		}

	}

	@Test
	public void testContatoValido() {
		Assert.assertFalse(contato.isContatoValido());
		telefone.setOperadora("oi");
		telefone.setNumero("888888888");
		try {
			contato.addTelefone(telefone);
		} catch (InvalidNumberException e) {}
		
		Assert.assertFalse(contato.isContatoValido());
//		contato.setNome("Renan");
		Assert.assertTrue(contato.isContatoValido());

	}
	
	@Test
	public void testTelefoneValido() {
		Assert.assertFalse(telefone.ehTelefoneValido());
		telefone.setNumero("8888");
		Assert.assertFalse(telefone.ehTelefoneValido());
		telefone.setNumero("88888888");
		Assert.assertTrue(telefone.ehTelefoneValido());
		telefone2.setOperadora("31");
		Assert.assertFalse(telefone2.ehTelefoneValido());
		telefone2.setCodigoRegional("44");
		Assert.assertFalse(telefone2.ehTelefoneValido());
		telefone2.setNumero("88888888");
		Assert.assertFalse(telefone2.ehTelefoneValido());
		telefone2.setOperadoraLigar("21");
		Assert.assertTrue(telefone2.ehTelefoneValido());
		
	}
	@Test
	public void testBuscaNomeTel() {
	
		agenda.getContatos().add(contato);
		agenda.setBusca("mar");
		agenda.fazBusca(null);
		Assert.assertEquals(1, agenda.getResultadoBusca().size());
		agenda.setBusca("Rob");
		agenda.fazBusca(null);
		Assert.assertEquals(2, agenda.getResultadoBusca().size());
		agenda.setBusca("xe");
		agenda.fazBusca(null);
		Assert.assertEquals(0, agenda.getResultadoBusca().size());
		agenda.setBusca("99");
		agenda.fazBusca(null);
		Assert.assertEquals(3, agenda.getResultadoBusca().size());
	}
	@Test
	public void testBuscaIdade() {
		agenda.setTipoDeBusca("2");
		agenda.setBusca("40");
		agenda.fazBusca(null);
		Assert.assertEquals(1, agenda.getResultadoBusca().size());
		agenda.setTipoDeBusca("3");
		Assert.assertEquals(1, agenda.getResultadoBusca().size());
		agenda.setTipoDeBusca("4");
		Assert.assertEquals(1, agenda.getResultadoBusca().size());
		agenda.setTipoDeBusca("2");
		agenda.setBusca("100");
		agenda.fazBusca(null);
		Assert.assertEquals(3, agenda.getResultadoBusca().size());
		agenda.setTipoDeBusca("4");
		agenda.setBusca("10");
		agenda.fazBusca(null);
		Assert.assertEquals(3, agenda.getResultadoBusca().size());
	}

}