package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Exceptions.AnoInvalidoException;
import Exceptions.DataInvalidaException;
import Exceptions.DiaInvalidoException;
import Exceptions.HoraInvalidaException;
import Exceptions.MesInvalidoException;
import Exceptions.MinutoInvalidoException;
import Exceptions.NomeInvalidoException;
import Model.Tarefa;

public class Controller {

	private List<Tarefa> tarefas;
	private List<Tarefa> tarefasCompletas;
	private List<Tarefa> tarefasIncompletas;

	public Controller() {
		this.tarefas = new ArrayList<Tarefa>();
		this.tarefasCompletas = new ArrayList<Tarefa>();
		this.tarefasIncompletas = new ArrayList<Tarefa>();
		try {
			Tarefa tarefa1 = new Tarefa("Tarefa1");
			Tarefa tarefa2 = new Tarefa("Tarefa2");
			Tarefa tarefa3 = new Tarefa("Tarefa3");
			Tarefa tarefa4 = new Tarefa("Tarefa4");
			Tarefa tarefa5 = new Tarefa("Tarefa5");
			adicionaTarefa(tarefa1);
			adicionaTarefa(tarefa2);
			adicionaTarefa(tarefa5);
			adicionaTarefa(tarefa3);
			adicionaTarefa(tarefa4);
		} catch (Exception e) {
		}
	}

	public void mudaStatusDaTarefa(Tarefa tarefa) {
		for (Tarefa tarefa1 : this.tarefas) {
			if (tarefa.equals(tarefa)) {
				if (!tarefa1.getStatus()) {
					tarefa1.setStatus(true);
					addTarefaCompleta(tarefa1);
					removeTarefaIncompleta(tarefa1);
				}
				break;
			}
		}
	}

	public List<Tarefa> getTarefasCompletas() {
		return tarefasCompletas;
	}

	public void setTarefasCompletas(List<Tarefa> tarefasCompletas) {
		this.tarefasCompletas = tarefasCompletas;
	}

	public List<Tarefa> getTarefasIncompletas() {
		return tarefasIncompletas;
	}

	public void setTarefasIncompletas(List<Tarefa> tarefasIncompletas) {
		this.tarefasIncompletas = tarefasIncompletas;
	}

	public void adicionaTarefa(Tarefa tarefa) {
		this.tarefas.add(tarefa);
		this.addTarefaIncompleta(tarefa);
	}

	public List<Tarefa> getTarefas() {
		return this.tarefas;
	}

	public void ordena(Comparator<Tarefa> comparador) {
		Collections.sort(this.tarefas, comparador);
	}

	public void ordenaCompletas(Comparator<Tarefa> comparador) {
		Collections.sort(this.tarefasCompletas, comparador);
	}

	public void ordenaIncompletas(Comparator<Tarefa> comparador) {
		Collections.sort(this.tarefasIncompletas, comparador);
	}

	public void addTarefaIncompleta(Tarefa tarefa) {
		this.tarefasIncompletas.add(tarefa);
	}

	public void addTarefaCompleta(Tarefa tarefa) {
		this.tarefasCompletas.add(tarefa);
	}

	public void removeTarefaIncompleta(Tarefa tarefa) {
		this.tarefasIncompletas.remove(tarefa);
	}

	public void removeTarefaCompleta(Tarefa tarefa) {
		this.tarefasCompletas.remove(tarefa);
	}

	public void removeTarefa(Tarefa tarefa) {
		if (tarefa.getStatus() == true) {
			this.removeTarefaCompleta(tarefa);
		} else {
			this.removeTarefaIncompleta(tarefa);
		}
		this.getTarefas().remove(tarefa);
	}

	public void editTarefa(Tarefa oldTarefa, Tarefa newTarefa)
			throws DataInvalidaException, NomeInvalidoException,
			NumberFormatException, HoraInvalidaException,
			MinutoInvalidoException, DiaInvalidoException,
			MesInvalidoException, AnoInvalidoException {

		if (this.getTarefas().contains(newTarefa)) {
			if (!oldTarefa.getNome().equals(newTarefa.getNome())) {
				throw new NomeInvalidoException();
			}
		}
		for (int i = 0; i < this.getTarefas().size(); i++) {
			if (oldTarefa.equals(this.getTarefas().get(i))) {
				this.removeTarefa(oldTarefa);
				this.getTarefas().add(newTarefa);
				if (newTarefa.getStatus()) {
					this.addTarefaCompleta(newTarefa);
				} else {
					this.addTarefaIncompleta(newTarefa);
				}
				break;
			}
		}
	}
}