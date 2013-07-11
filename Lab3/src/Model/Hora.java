package Model;

import Exceptions.HoraInvalidaException;
import Exceptions.MinutoInvalidoException;

public class Hora implements Comparable<Hora>{

	private int hora;
	private int minutos;
	
	public Hora(int hora, int minutos) throws HoraInvalidaException, MinutoInvalidoException{
		if(hora > 23 || hora < 0) throw new HoraInvalidaException();
		if(minutos > 59 || minutos < 0) throw new MinutoInvalidoException();
		this.hora = hora;
		this.minutos = minutos;
	}

	public Hora() {		
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) throws HoraInvalidaException {
		if(hora > 23 || hora < 0) throw new HoraInvalidaException();
		this.hora = hora;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) throws MinutoInvalidoException {
		if(minutos > 59 || minutos < 0) throw new MinutoInvalidoException();
		this.minutos = minutos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hora;
		result = prime * result + minutos;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Hora)) return false;
		return this.hora==((Hora)obj).hora && this.minutos==((Hora)obj).minutos;
	}
	
	@Override
	public String toString(){
		return this.getHora() + ":" + this.getMinutos();
	}	
	
	@Override
	public int compareTo(Hora hora) {
		if(this.getHora() < hora.getHora()) return -1;
		else if(this.getHora() > hora.getHora()) return 1;
		else{ //horas iguais
			if(this.getMinutos() > hora.getMinutos()) return 1;
			if(this.getMinutos() < hora.getMinutos()) return -1;
			else return 0;
		}
	}
}
