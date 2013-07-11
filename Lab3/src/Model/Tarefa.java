package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Exceptions.AnoInvalidoException;
import Exceptions.DataInvalidaException;
import Exceptions.DiaInvalidoException;
import Exceptions.HoraInvalidaException;
import Exceptions.MesInvalidoException;
import Exceptions.MinutoInvalidoException;

public class Tarefa implements Comparable<Tarefa> {

	private String nome;
	private Data dataCriacao;
	private Data dataConclusao;
	private Hora horaConclusao;
	private String descricao;
	private boolean status;
	// Pegar a hora do sistema
	private DateFormat dateFormat = new SimpleDateFormat("HH:mm");
	private Date date = new Date();
	private Hora horaCriacao;

	public Tarefa(String nome) throws NumberFormatException,
			HoraInvalidaException, MinutoInvalidoException,
			DiaInvalidoException, MesInvalidoException, AnoInvalidoException {
		this.nome = nome;
		this.status = false;
		this.dataCriacao = getDataSystem(new SimpleDateFormat("dd/MM/yyyy")
				.format(Calendar.getInstance().getTime()));
		this.dataConclusao = new Data();
		this.horaConclusao = new Hora();
		this.descricao = "";
		this.horaCriacao = new Hora(Integer.parseInt(this.dateFormat.format(
				this.date).substring(0, 2)), Integer.parseInt(this.dateFormat
				.format(this.date).substring(3, 5)));
	}

	private Data getDataSystem(String data) throws NumberFormatException,
			DiaInvalidoException, MesInvalidoException, AnoInvalidoException {
		return new Data(Integer.parseInt(data.substring(0, 2)),
				Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data
						.substring(6, 10)));
	}

	public Hora getHoraCriacao() {
		return horaCriacao;
	}

	public void setHoraCriacao(Hora horaCriacao) {
		this.horaCriacao = horaCriacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Data getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Data dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Data getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Data dataConclusao)
			throws DataInvalidaException {
		if (!dataConclusao.equals(new Data())) {
			if (this.getDataCriacao().compareTo(dataConclusao) > 0) {
				throw new DataInvalidaException();
			}
			if (this.getDataCriacao().compareTo(dataConclusao) == 0) {
				if (this.getHoraConclusao().compareTo(this.getHoraCriacao()) > 0) {
					throw new DataInvalidaException();
				}
			}
		}
		this.dataConclusao = dataConclusao;
	}

	public Hora getHoraConclusao() {
		return horaConclusao;
	}

	public void setHoraConclusao(Hora horaConclusao) {
		this.horaConclusao = horaConclusao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		if (descricao == null)
			throw new NullPointerException();
		this.descricao = descricao;
	}

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Tarefa))
			return false;
		return this.getNome().equals(((Tarefa) obj).getNome());
	}

	@Override
	public int compareTo(Tarefa tarefa) {
		if (this.getDataConclusao().compareTo(tarefa.getDataConclusao()) != 0) {
			return getDataConclusao().compareTo(tarefa.getDataConclusao());
		}
		return this.getNome().compareTo(tarefa.getNome());
	}

	@Override
	public String toString() {
		return "Nome: " + this.getNome() + ", DataCriacao: "
				+ this.getDataCriacao() + ", DataConclusao: "
				+ this.getDataConclusao();
	}
}
