package Controller;

import java.util.Scanner;

import Exceptions.AnoInvalidoException;
import Exceptions.DiaInvalidoException;
import Exceptions.HoraInvalidaException;
import Exceptions.MesInvalidoException;
import Exceptions.MinutoInvalidoException;
import Exceptions.TarefaNaoCadastradaException;
import Model.ComparadorDataConclusao;
import Model.ComparadorDataCriacao;
import Model.Data;
import Model.Hora;
import Model.Tarefa;

public class Main{

	private static Controller controller = new Controller();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out
				.println("Bem vindo ao Gerenciamento eletrônico de Tarefas \n");
		while (true) {
			System.out.println("Escolha uma ação: \n "
					+ "1-Cadastrar nova tarefa \n "
					+ "2-Listar Tarefas Completas \n "
					+ "3-Listar Tarefas Incompletas \n "
					+ "4-Remover Tarefa \n " + "5-Editar Tarefa \n "
					+ "6-Concluir Tarefa \n " + "7-Sair");

			String escolha = sc.next();
			switch (escolha) {
			case "1":
				cadastraTarefa();
				break;
			case "2":
				listarTarefasCompletas();
				break;
			case "3":
				listarTarefasIncompletas();
				break;
			case "4":
				remover();
				break;
			case "5":
				editaTarefa();
				break;
			case "6":
				concluirTarefa();
				break;
			case "7":
				System.exit(0);
			}
		}
	}

	private static void remover() {
		Tarefa tarefa = buscarTarefaRemocao();
		if (tarefa != null) {
			try {
				controller.removeTarefa(tarefa);
			} catch (TarefaNaoCadastradaException e) {	}
			System.out.println("Tarefa Removida");
		} else {
			System.out.println("Operacao cancelada");
		}
	}

	public static void cadastraTarefa() {
		System.out.println("Nome: ");
		String nome = sc.next();

		System.out.println("Descricao: ");
		String descricao = sc.next();

		System.out.println("Data(dd/MM/yyyy): ");
		String data = sc.next();

		System.out.println("Hora(HH:mm):");
		String hora = sc.next();
		System.out.println("Confirma? (s/n)");

		if (sc.next().equals("s"))
			try {
				Tarefa tarefa = new Tarefa(nome);
				tarefa.setDescricao(descricao);
				tarefa.setDataConclusao(transformeData(data));
				tarefa.setHoraConclusao(transformeHora(hora));

				controller.adicionaTarefa(tarefa);
				System.out.println("Tarefa cadastrada \n");
			} catch (Exception e) {
				System.out
						.println("Erro no cadastro, alguma informação inválida.");
			}
	}

	private static Data transformeData(String data)
			throws NumberFormatException, DiaInvalidoException,
			MesInvalidoException, AnoInvalidoException {
		return new Data(Integer.parseInt(data.substring(0, 2)),
				Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data
						.substring(6, 10)));
	}

	private static Hora transformeHora(String hora)
			throws NumberFormatException, HoraInvalidaException,
			MinutoInvalidoException {
		return new Hora(Integer.parseInt(hora.substring(0, 2)),
				Integer.parseInt(hora.substring(3, 5)));
	}

	public static void listarTarefasCompletas() {
		if (controller.getTarefasCompletas().size() > 0) {
			System.out
					.println("Listar por: \n 1 - Data de Conclusão \n 2 - Data de Criacao");
			String escolha = sc.next();
			if (escolha.equals("1")) {
				controller.ordenaCompletas(new ComparadorDataConclusao());
			} else if (escolha.equals("2")) {
				controller.ordenaCompletas(new ComparadorDataCriacao());
			}
			System.out.println("Tarefas concluidas: \n");
			for (Tarefa tarefa : controller.getTarefasCompletas()) {
				System.out.println(tarefa);
			}
		} else {
			System.out.println("Nao ha nenhuma tarefa completa");
		}
	}

	public static void listarTarefasIncompletas() {
		if (controller.getTarefasIncompletas().size() > 0) {
			System.out
					.println("Listar por: \n 1 - Data de Conclusao \n 2 - Data de Criacao");
			String escolha = sc.next();
			if (escolha.equals("1")) {
				controller.ordenaIncompletas(new ComparadorDataConclusao());
			} else if (escolha.equals("2")) {
				controller.ordenaIncompletas(new ComparadorDataCriacao());
			}
			System.out.println("Tarefas Incompletas: \n");
			for (Tarefa tarefa : controller.getTarefasIncompletas()) {
				System.out.println(tarefa);
			}
		} else {
			System.out.println("Nao ha nenhuma tarefa incompleta");
		}

	}

	private static Tarefa buscarTarefaEdicao() {
		System.out.println("Digite o nome da tarefa: \n");
		String nome = sc.next();
		for (Tarefa tarefa : controller.getTarefas()) {
			if (tarefa.getNome().equals(nome)) {
				System.out.println("É esta tarefa que deseja editar? (s/n) \n"
						+ tarefa);
				String escolha = sc.next();
				if (escolha.equals("s")) {
					return tarefa;
				} else if (escolha.equals("n")) {
					System.out.println("Operação cancelada");
					return null;
				}
			}
		}
		System.out.println("Tarefa não foi encontrada");
		return null;
	}

	private static Tarefa buscarTarefaRemocao() {
		System.out.println("Digite o nome da tarefa: \n");
		String nome = sc.next();
		for (Tarefa tarefa : controller.getTarefas()) {
			if (tarefa.getNome().equals(nome)) {
				System.out.println("É esta tarefa que deseja remover? (s/n) \n"
						+ tarefa);
				String escolha = sc.next();
				if (escolha.equals("s")) {
					return tarefa;
				} else if (escolha.equals("n")) {
					System.out.println("Operação cancelada");
					return null;
				}
			}
		}
		System.out.println("Tarefa nao encontrada");
		return null;
	}

	private static Tarefa buscarTarefaConclusao() {
		System.out.println("Digite o nome da tarefa: \n");
		String nome = sc.next();
		for (Tarefa tarefa : controller.getTarefas()) {
			if (tarefa.getNome().equals(nome)) {
				System.out
						.println("É esta tarefa que deseja concluir? (s/n) \n"
								+ tarefa);
				String escolha = sc.next();
				if (escolha.equals("s")) {
					return tarefa;
				} else if (escolha.equals("n")) {
					System.out.println("Operação cancelada");
					return null;
				}
			}
		}
		System.out.println("Tarefa nao encontrada");
		return null;
	}

	private static void concluirTarefa() {
		Tarefa tarefa = buscarTarefaConclusao();
		if (tarefa != null) {
			controller.mudaStatusDaTarefa(tarefa);
		}
	}

	private static void editaTarefa() {
		Tarefa tarefa = buscarTarefaEdicao();
		if (tarefa != null) {
			System.out.println("Nome:");
			String nome = sc.next();

			System.out.println("Descricao:");
			String descricao = sc.next();

			System.out.println("Data:");
			String data = sc.next();

			System.out.println("Hora:");
			String hora = sc.next();

			System.out.println("Confirma ? (s/n)");
			if (sc.next().equals("s")) {
				try {
					tarefa.setNome(nome);
					tarefa.setDescricao(descricao);
					tarefa.setDataConclusao(transformeData(data));
					tarefa.setHoraConclusao(transformeHora(hora));
				} catch (Exception ex) {
					System.out
							.println("Erro ao editar, alguma informação inválida");
				}
			}
		}
	}
}