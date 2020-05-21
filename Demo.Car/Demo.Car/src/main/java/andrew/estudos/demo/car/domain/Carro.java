package andrew.estudos.demo.car.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "carro")
public class Carro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	private String tipo;
	
	public Carro() {
		
	}


	    public Carro(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	    
	    

		public String getTipo() {	return tipo;}

		public void setTipo(String tipo) {this.tipo = tipo;}

		public long getId() {	return id;}

		public void setId(long id) {this.id = id;}

		public String getNome() {return nome;}

		public void setNome(String nome) {this.nome = nome;}
	    
	    
	    

}
