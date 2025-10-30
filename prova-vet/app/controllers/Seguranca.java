package controllers;

import play.mvc.Before;
import play.mvc.Controller;
import security.Veterinario;

public class Seguranca extends Controller{
	@Before
	static void verificarAutenticacao() {
		if (!session.contains("operadorLogado")) {
			flash.error("Você deve logar no sistema.");
			Logins.form();
		}
	}
	
	@Before
 	static void verificarAdministrador() {
  	    String perfil = session.get("operadorPerfil");
  	    Veterinario vetAnnotation = getActionAnnotation(Veterinario.class);
  	
  	    if (vetAnnotation != null) {
  	        String isAdmin = session.get("operadorAdmin"); // vem da sessão

  	        if (!"true".equals(isAdmin)) {
  	            flash.error("Acesso restrito aos veterinarios do sistema.");
  	            Animais.listar(null, null);
  	        }
  	    }
 	}
}
