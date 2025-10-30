package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Min;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Animal extends Model {
	@Required(message="O nome deve ser preenchido.")
	public String nome;
	
	@Required(message="O peso deve ser preenchido.")
	@Min(value=1, message="O peso deve ser maior que zero.")
	public Integer peso;
	
	@Required(message="A data de nascimento deve ser preenchida.")
	public Date dataNascimento;
	
	public Boolean extincao;
	
	public Animal() {
		this.extincao = false;
	}
	
	@ManyToOne
	public TipoAnimal tipoAnimal;
	
	@Override
	public String toString() {
		return "Nome: " +nome + " | Peso: " + peso + " | " + dataNascimento;
	}

}
