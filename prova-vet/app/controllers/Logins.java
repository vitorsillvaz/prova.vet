package controllers;

import models.Operador;
import play.mvc.Controller;

public class Logins extends Controller {

	public static void form() {
		render();
	}

	public static void logar(String username, String senha) {
		Operador operador = Operador.find("userName = ?1 and senha = ?2", username, senha).first();
		if (operador == null) {
			flash.error("Login ou senha inválidos");
			form();
		} else {
			session.put("operadorLogado", operador.username);

			session.put("operadorAdmin", String.valueOf(operador.veterinario));

			flash.success("Logado com sucesso!");
			Animais.form(); 
		}

	}

	public static void sair() {
		session.clear();
		flash.success("Você saiu do sistema!");
		form();
	}

}
