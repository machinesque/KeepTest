package br.com.keeptest.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.keeptest.domain.Regiao;
import br.com.keeptest.services.RegiaoService;

@RestController
@RequestMapping(value = "/regioes", produces = {"application/json"})
public class RegiaoResource {

	@Autowired
	private RegiaoService regiaoService;
	
	@GetMapping(value = "/{id}")
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Long id) {
		
		Regiao regiao = regiaoService.findById(id);
		return ResponseEntity.ok().body(regiao);
		
	}
	
}
