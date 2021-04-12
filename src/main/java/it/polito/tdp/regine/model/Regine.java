package it.polito.tdp.regine.model;

import java.util.ArrayList;
import java.util.List;

public class Regine {

	// N è il numero di righe e colonne della scacchiera
	//   (righe e colonne numerate da 0 a N-1)
	// ad ogni livello posizioniamo una regina in una nuova riga
	
	// soluzione parziale: lista delle colonne in cui mettere le regine (prime righe)
	// 		List<Integer>
	// livello = quante righe sono già piene
	// livello = 0 => nessuna riga piena (devo mettere la regina nella riga 0)
	// livello = 3 => 3 righe piene (0, 1, 2), devo mettere la regina nella riga 3
	// [0]
	//     [0, 2]
	//            [0, 2, 1]
	private int N;
	private List<Integer> soluzione;
	
	public List<Integer> risolvi(int N){
		this.N=N;
		List<Integer> parziale=new ArrayList<Integer>();
		//ArrayList e non LinkedList perchè sto facendo parziale.get() che nel primo ha tempo costante
		this.soluzione=null;
		
		cerca(parziale, 0);
		
		return this.soluzione;
	}
	
	//cerca==true -> trovato | cerca==false -> cerca ancora
	private boolean cerca(List<Integer>parziale, int livello) {
		if(livello==N) {
			// caso terminale
			//System.out.println(parziale);
			this.soluzione=new ArrayList<>(parziale);
			return true;
		} else {
			for(int colonna=0; colonna<N; colonna++) {
				// if la possa nella casella [livello][colonna] è valida
				// se sì, aggiungi a parziale e fai ricorsione
				
				if(posValida(parziale, colonna, livello)) {
					
					//List<Integer> parzialeNuovo=new ArrayList<>();	
					//parzialeNuovo.add(colonna); //[0, 6, 4, 7, XXX] sono tentativi, non so se valore sarà corretto
					//cerca(parzialeNuovo, livello+1);
					
					parziale.add(colonna); //[0, 6, 4, 7, XXX] sono tentativi, non so se valore sarà corretto
					boolean trovato=cerca(parziale, livello+1);
					if(trovato)
						return true;
					parziale.remove(parziale.size()-1); //backtracking -> modifico sol parziale, quando salgo di liv rimetto tutto a posto
				}
			}
			return false;
		}
	}

	private boolean posValida(List<Integer> parziale, int colonna, int livello) {
		//int livello=parziale.size();
		
		//Controlla se viene mangiata in verticale (in orizzonatale no per costruzione)
		if(parziale.contains(colonna))
			return false;
		//Controllo in diagonale: confronyto posizione (livello, colonna) con (r,c)
		//delle regine esistenti
		for(int r=0; r<livello; r++) {
			int c=parziale.get(r);
			
			if(r+c==livello+colonna || r-c==livello-colonna)
				return false;
		}
		
		return true;
	}
	
	
	
}
