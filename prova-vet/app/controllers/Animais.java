package controllers;

import java.util.List;

import models.Animal;
import models.TipoAnimal;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;
import security.Veterinario;
@With(Seguranca.class)
public class Animais extends Controller {
	@Veterinario
	public static void form() {
		List<TipoAnimal> tipos = TipoAnimal.findAll();
		render(tipos);
	}
	@Veterinario
	public static void editar(Long id) {
		Animal a = Animal.findById(id);
		if(a == null) {
			flash.error("Animal não encontrado!");
			listar(null, null);
		}
		a.refresh();
		List<TipoAnimal> tipos = TipoAnimal.findAll();
		renderTemplate("Animais/form.html", a, tipos);
	}
	
	public static void listar(String nomeBusca, String mensagem) {
		List<Animal> animais = null;
		if (nomeBusca == null || nomeBusca.isEmpty()) {
			animais = Animal.findAll();
		} else {
			animais = Animal.find("lower(nome) = ?1", 
					nomeBusca.toLowerCase()).fetch();
		}
		render(animais, nomeBusca, mensagem);
	}
	@Veterinario
	public static void salvar(@Valid Animal animal) {
		if (validation.hasErrors()) {
			params.flash();
			validation.keep();
			form();
		}
		
		Animal animalBanco = Animal.find("nome = ?1 and tipoAnimal.id = ?2", 
				animal.nome, animal.tipoAnimal.id).first();
		if (animalBanco != null && animal.id != animalBanco.id) {
			String mensagem = "Já existe animal cadastrado com esse nome e tipo";
			listar(null, mensagem);
		}

		flash.success(animal.nome + " cadastrado com sucesso!");
		animal.save();
		listar(null, "");
	}
	@Veterinario
	public static void deletar(Long id) {
		Animal animal = Animal.findById(id);
		
		String mensagem = "";
		if (!animal.extincao) {
			animal.delete();			
		} else {
			mensagem = "Não é possível deletar animal em extinção";
		}
		listar(null, mensagem);
	}
}
