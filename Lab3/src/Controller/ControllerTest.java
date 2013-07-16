package Controller;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Model.Tarefa;

public class ControllerTest {

	private Controller controller;
	
	@Before
	public void ControllerTest1() {
		this.controller = new Controller();
	}

	@Test
	public void testController() {
		Assert.assertTrue(controller != null);
		Assert.assertTrue(controller.getTarefas().size() == 5); //Foram adicionadas 5 tarefas default para rodar o programa no servidor.
		Assert.assertTrue(controller.getTarefasCompletas().size() == 0); //Todas as tarefas adicionadas inicialmente são incompletas.
		Assert.assertTrue(controller.getTarefasIncompletas().size() == 5); 
	}
	
	@Test
	public void testAdd(){
		try {
			controller.adicionaTarefa(new Tarefa("TarefaQualquer"));
			//Adicionando corretamente uma tarefa;
		} catch (Exception e) {
			Assert.fail();
		}
		Assert.assertTrue(controller.getTarefas().size() == 6);
		Assert.assertTrue(controller.getTarefasCompletas().size() == 0);
		Assert.assertTrue(controller.getTarefasIncompletas().size() == 6);
		try {
			controller.adicionaTarefa(new Tarefa("TarefaQualquer"));
			Assert.fail();
			//Só será adicionada se a tarefa tiver nome diferente das que já são cadastradas, senão lança exceção.
		} catch (Exception e) {
		}
		Assert.assertTrue(controller.getTarefas().size() == 6);
		Assert.assertTrue(controller.getTarefasCompletas().size() == 0);
		Assert.assertTrue(controller.getTarefasIncompletas().size() == 6);
	}
	
	@Test
	public void testRemove(){
		try {
			controller.removeTarefa(new Tarefa("tarefa"));
			//Só será removida se houver cadastrada nas tarefas
			Assert.fail();			
		} catch (Exception e) { }
		Assert.assertTrue(controller.getTarefas().size() == 5);
		try {
			controller.removeTarefa(controller.getTarefas().get(0));
			//Como tem algumas tarefas cadastradas, estou retirando a primeira;
		} catch (Exception e) {
			Assert.fail();			
		}
		Assert.assertTrue(controller.getTarefas().size() == 4);
		Assert.assertTrue(controller.getTarefasIncompletas().size() == 4);
	}

	@Test
	public void testMudaStatus(){
		Assert.assertTrue(controller.getTarefas().get(0).getStatus() == false);
		Assert.assertTrue(controller.getTarefasIncompletas().size() == 5);
		Assert.assertTrue(controller.getTarefasCompletas().size() == 0);
		
		//Mudando o status do primeiro aplicativo da lista.
		controller.mudaStatusDaTarefa(controller.getTarefas().get(0));
		
		Assert.assertTrue(controller.getTarefas().get(0).getStatus() == true);
		Assert.assertTrue(controller.getTarefasIncompletas().size() == 4);
		Assert.assertTrue(controller.getTarefasCompletas().size() == 1);
	}
}
