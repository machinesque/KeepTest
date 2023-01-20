package br.com.keeptest.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Regiao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String sigla;
	
	@Column
    @ElementCollection(targetClass=Double.class)
	private List<Double> geracao = new ArrayList<>();
	
	@Column
    @ElementCollection(targetClass=Double.class)
	private List<Double> compra = new ArrayList<>();
	
	@JsonIgnore
	@Column
    @ElementCollection(targetClass=Double.class)
	private List<Double> precoMedio = new ArrayList<>();
	
	@JsonIgnore
    @ManyToOne
    @JoinColumn(name="agente_id")
    private Agente agente;
	
}
