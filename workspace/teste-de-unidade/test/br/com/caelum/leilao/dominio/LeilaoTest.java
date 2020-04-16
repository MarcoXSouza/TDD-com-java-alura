package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LeilaoTest {

	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("Lustre do Castelo lustre");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("professor Abobrinha"), 1000.0));
		
		assertEquals(1,	leilao.getLances().size());
		assertEquals(1000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("Lustre do Castelo Lustre");
		leilao.propoe(new Lance(new Usuario("professor Abobrinha"), 1000.0));
		leilao.propoe(new Lance(new Usuario("Nino"), 2000.0));
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(1000, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(2000, leilao.getLances().get(1).getValor(), 0.00001);
		
	}

	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("Whisky a cavalo");
		Usuario zecaPagodinho = new Usuario("Zeca Pagodinho");
		
		leilao.propoe(new Lance(zecaPagodinho, 200.0));
		leilao.propoe(new Lance(zecaPagodinho, 300.0));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(200, leilao.getLances().get(0).getValor(), 0.00001);
	}

	@Test
	public void naoDeveAceitarMaisDe5LancesDeUmMesmoUsuario() {
		Leilao leilao = new Leilao("prato feito");
		Usuario usuarioFaminto = new Usuario("Faminto");
		Usuario mortoDeFome = new Usuario("Morto de Fome");
		
		leilao.propoe(new Lance(usuarioFaminto, 1.5));
		leilao.propoe(new Lance(mortoDeFome, 2.5));

		leilao.propoe(new Lance(usuarioFaminto, 3.5));
		leilao.propoe(new Lance(mortoDeFome, 4.5));
		
		leilao.propoe(new Lance(usuarioFaminto, 6.5));
		leilao.propoe(new Lance(mortoDeFome, 7.5));
		
		leilao.propoe(new Lance(usuarioFaminto, 8.5));
		leilao.propoe(new Lance(mortoDeFome, 9.5));
		
		leilao.propoe(new Lance(usuarioFaminto, 10.5));
		leilao.propoe(new Lance(mortoDeFome, 11.5));

		leilao.propoe(new Lance(usuarioFaminto, 12.0));
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(11.5, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
		
	}

	@Test
	public void deveDobrarOUltimoLanceDado() {
		Leilao leilao = new Leilao("mentos");
		Usuario jofrey = new Usuario("Jofrey");
		Usuario sansa = new Usuario("Sansa");
		
		leilao.propoe(new Lance(sansa, 20));
		leilao.propoe(new Lance(jofrey, 30));
		leilao.dobraLance(sansa);
		
		assertEquals(40, leilao.getLances().get(2).getValor(), 0.00001);	
		
	}

	@Test
	public void naoDeveDobrarOUltimoLanceDado() {
		Leilao leilao = new Leilao("mentos");
		Usuario jofrey = new Usuario("Jofrey");
		
		leilao.dobraLance(jofrey);

		assertEquals(0, leilao.getLances().size());	
		
	}
}