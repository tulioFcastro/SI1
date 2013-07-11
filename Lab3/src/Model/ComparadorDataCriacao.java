package Model;

import java.util.Comparator;

public class ComparadorDataCriacao implements Comparator<Tarefa>{
	
	@Override
	public int compare(Tarefa tarefa1, Tarefa tarefa2) {
		if(tarefa1.getDataCriacao().equals(tarefa2.getDataCriacao())){
			return tarefa1.getNome().compareTo(tarefa2.getNome());
		}
		return tarefa1.getDataCriacao().compareTo(tarefa2.getDataCriacao());
	}
}