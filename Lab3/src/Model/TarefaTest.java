package Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Exceptions.AnoInvalidoException;
import Exceptions.DiaInvalidoException;
import Exceptions.HoraInvalidaException;
import Exceptions.MesInvalidoException;
import Exceptions.MinutoInvalidoException;

public class TarefaTest {

	private Tarefa tarefa;
	private Data dataDefault;
	private Hora horaDefault;
	private Data dataDoSistema;

	@Before
	public void Tarefa() throws NumberFormatException, HoraInvalidaException,
			MinutoInvalidoException, DiaInvalidoException,
			MesInvalidoException, AnoInvalidoException {
		this.tarefa = new Tarefa("Tarefa");
		this.dataDefault = new Data();
		this.horaDefault = new Hora();
		this.dataDoSistema = getDataSystem(new SimpleDateFormat("dd/MM/yyyy")
				.format(Calendar.getInstance().getTime()));
	}

	private Data getDataSystem(String data) throws NumberFormatException,
			DiaInvalidoException, MesInvalidoException, AnoInvalidoException {
		return new Data(Integer.parseInt(data.substring(0, 2)),
				Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data
						.substring(6, 10)));
	}

	@Test
	public void testTarefa() {
		Assert.assertTrue(tarefa != null);
		Assert.assertEquals(tarefa.getNome(), "Tarefa");
		// Atributos sempre inicializados default em sua criação
		Assert.assertTrue(tarefa.getDescricao() != null);
		Assert.assertTrue(tarefa.getStatus() == false); // Tarefa sempre
														// inicializada com
														// status de Conclusão
														// false
		Assert.assertTrue(tarefa.getDataConclusao() != null);
		Assert.assertTrue(tarefa.getDataCriacao() != null);
		Assert.assertTrue(tarefa.getHoraConclusao() != null);
		Assert.assertTrue(tarefa.getHoraCriacao() != null);
	}

	@Test
	public void testSetData() {
		Assert.assertTrue(tarefa.getDataConclusao().equals(dataDefault));
		Assert.assertTrue(tarefa.getDataCriacao().equals(dataDoSistema));
		try {
			tarefa.setDataConclusao(new Data(0, 0, 0));
			Assert.fail();
		} catch (Exception e) {
		}
		try {
			// Alterar a data de conclusão para uma data anterior a da criação
			tarefa.setDataConclusao(new Data(dataDoSistema.getDia(),
					dataDoSistema.getMes(), dataDoSistema.getAno() - 1));
			Assert.fail();
		} catch (Exception e) {
		}
		try {
			// Alterar a data de conclusão para null
			tarefa.setDataConclusao(null);
			Assert.fail();
		} catch (Exception e) {
		}

		try {
			// Alterar a data de conclusão para data inválida
			tarefa.setDataConclusao(new Data(2, 2, -1000));
			Assert.fail();
		} catch (Exception e) {
		}

		try {
			// Alterando para uma data válida
			tarefa.setDataConclusao(new Data(20, 07, 2015));
		} catch (Exception e) {
			Assert.fail();
		}

		Assert.assertTrue(tarefa.getDataConclusao().getDia() == 20);
		Assert.assertTrue(tarefa.getDataConclusao().getMes() == 7);
		Assert.assertTrue(tarefa.getDataConclusao().getAno() == 2015);
	}

	@Test
	public void testSetHora() {
		Assert.assertTrue(tarefa.getHoraConclusao().equals(horaDefault));
		try {
			tarefa.setHoraConclusao(new Hora(-1, 0));
			Assert.fail();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			tarefa.setHoraConclusao(new Hora(0, -1));
			Assert.fail();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			tarefa.setHoraConclusao(new Hora(-1, -1));
			Assert.fail();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			tarefa.setHoraConclusao(new Hora(24, 0));
			Assert.fail();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			tarefa.setHoraConclusao(new Hora(12, 60));
			Assert.fail();
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			//Alterando para uma hora válida
			tarefa.setHoraConclusao(new Hora(12, 13));
		} catch (Exception e) {
			Assert.fail();
		}
		Assert.assertTrue(tarefa.getHoraConclusao().getHora() == 12);
		Assert.assertTrue(tarefa.getHoraConclusao().getMinutos() == 13);
	}
	
	@Test
	public void testStatus(){
		Assert.assertFalse(tarefa.getStatus());
		tarefa.setStatus(true);
		Assert.assertTrue(tarefa.getStatus());
	}
	
	@Test
	public void testDescricao(){
		Assert.assertTrue(tarefa.getDescricao().isEmpty());
		tarefa.setDescricao("Descricao");
		Assert.assertEquals(tarefa.getDescricao(), "Descricao");
	}

}
