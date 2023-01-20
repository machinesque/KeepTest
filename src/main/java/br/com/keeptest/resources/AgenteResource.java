package br.com.keeptest.resources;

import java.io.IOException;
import java.nio.file.Files;import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.keeptest.domain.Agente;
import br.com.keeptest.services.AgenteService;

@RestController
@RequestMapping(value = "/agentes", produces = {"application/json"})
public class AgenteResource {

	@Autowired
	private AgenteService agenteService;
	
	@PostMapping("/arquivo")
	public ResponseEntity<String> salvarArquivo(@RequestParam("file") MultipartFile file){
		
		var path = "/home/machinesque/Documentos/";
		var caminho = path + "exemplo_" + UUID.randomUUID() + agenteService.extrairExtensao(file.getOriginalFilename());
		
		try {
			Files.copy(file.getInputStream(), Path.of(caminho), StandardCopyOption.REPLACE_EXISTING);
			agenteService.xmlReader2(caminho);
			listAgentes();
			
			return new ResponseEntity<>("{\"mensagem\": \"Arquivo Carregado com sucesso!\"}", HttpStatus.OK);
			
			
		} catch (IOException e) {
			return new ResponseEntity<>("{\"mensagem\": \"Erro ao carregar o arguivo!\"}", HttpStatus.OK);
		}
		
	}
	
	@GetMapping("/codigo")
    public List<Agente> listAgentes() {
        return agenteService.findAll();
    }
	
}
