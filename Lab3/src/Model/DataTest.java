package Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Exceptions.AnoInvalidoException;
import Exceptions.DiaInvalidoException;
import Exceptions.MesInvalidoException;

public class DataTest {

	private Data dataTest1;
	private Data dataTest2;
	
	@Before
	public void DataTeste() throws DiaInvalidoException, MesInvalidoException, AnoInvalidoException{
		this.dataTest1 = new Data();
		this.dataTest2 = new Data(1,1,2014);
	}
	
	@Test
	public void testDataIntIntInt() {
		//Construtor com parametros maiores que 0
		Assert.assertTrue(dataTest2 != null);
		Assert.assertTrue(dataTest2.getDia() > 0);
		Assert.assertTrue(dataTest2.getAno() > 0);
		Assert.assertTrue(dataTest2.getMes() > 0);		
	}

	@Test
	public void testData() {
		//Construtor default para data cria data == 0/0/0
		Assert.assertTrue(dataTest1 != null);
		Assert.assertTrue(dataTest1.getAno() == 0);
		Assert.assertTrue(dataTest1.getMes() == 0);
		Assert.assertTrue(dataTest1.getDia() == 0);		
	}

	@Test
	public void testSetDia() {
		try {
			dataTest1.setDia(0);
			Assert.fail();
		} catch (DiaInvalidoException e) {}
		try {
			dataTest1.setDia(32);
			Assert.fail();
		} catch (DiaInvalidoException e) {}
		try {
			dataTest1.setDia(-1);
			Assert.fail();
		} catch (DiaInvalidoException e) {}		
	}

	@Test
	public void testSetAno() {
		//Ano só pode ser maior que 1900
		try {
			dataTest2.setAno(1900);
			Assert.fail();
		} catch (AnoInvalidoException e) {}
		
	}

	@Test
	public void testSetMes() {
		try {
			dataTest2.setMes(0);
			Assert.fail();
		} catch (MesInvalidoException e) {}
		try {
			dataTest2.setMes(-1);
			Assert.fail();
		} catch (MesInvalidoException e) {}
		try {
			dataTest2.setMes(13);
			Assert.fail();
		} catch (MesInvalidoException e) {}
	}

}
