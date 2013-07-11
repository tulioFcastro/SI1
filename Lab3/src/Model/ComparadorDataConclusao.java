package Model;

import java.util.Comparator;

public class ComparadorDataConclusao implements Comparator<Tarefa>{
	@Override
	public int compare(Tarefa tarefa1, Tarefa tarefa2) {
		if(tarefa1.getDataConclusao().equals(tarefa2.getDataConclusao())){
			return tarefa1.getNome().compareTo(tarefa2.getNome());
		}
		return tarefa1.getDataConclusao().compareTo(tarefa2.getDataConclusao());
	}
}