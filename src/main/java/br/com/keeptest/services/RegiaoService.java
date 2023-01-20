package br.com.keeptest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.keeptest.domain.Regiao;
import br.com.keeptest.repositories.RegiaoRepository;
import br.com.keeptest.services.exceptions.ObjectNotFoundException;

@Service
public class RegiaoService {

	@Autowired
	private RegiaoRepository regiaoRepository;
	
	public List<Regiao> findAll() {
		return regiaoRepository.findAll();
	}
	
	public Regiao findById(Long id) {
		
		Optional<Regiao> objetoRegiao = regiaoRepository.findById(id);
		return objetoRegiao.orElseThrow(() -> new ObjectNotFoundException("Região não encontrada! Id: " + id + ", Tipo: " + Regiao.class.getName()));
		
	}
	
}
