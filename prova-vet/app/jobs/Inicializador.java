package jobs;

import models.Operador;
import models.TipoAnimal;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {

	@Override
	public void doJob() throws Exception {
		 if (Operador.count() == 0) {
			 Operador op1 = new Operador();
			 op1.nome = "Joao Silva";
			 op1.username = "joao";
			 op1.senha = "joao";
			 op1.save();
			 
			 Operador op2 = new Operador();
			 op2.nome = "Veterinario";
			 op2.username = "vet";
			 op2.senha = "vet";
			 op2.veterinario = true;
			 op2.save();
		 }
		 
		 if (TipoAnimal.count() == 0) {
			 TipoAnimal ta1 = new TipoAnimal();
			 ta1.nome = "Cachorro";
			 ta1.save();
			 
			 TipoAnimal ta2 = new TipoAnimal();
			 ta2.nome = "Gato";
			 ta2.save();
			 
			 TipoAnimal ta3 = new TipoAnimal();
			 ta3.nome = "Hamster";
			 ta3.save();
			 
			 TipoAnimal ta4 = new TipoAnimal();
			 ta4.nome = "Periquito";
			 ta4.save();
			 
			 TipoAnimal ta5 = new TipoAnimal();
			 ta5.nome = "Cobra";
			 ta5.save();
		 }
	}
}
