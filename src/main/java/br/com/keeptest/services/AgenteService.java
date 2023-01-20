package br.com.keeptest.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.keeptest.domain.Agente;
import br.com.keeptest.domain.Regiao;
import br.com.keeptest.repositories.AgenteRepository;
import br.com.keeptest.repositories.RegiaoRepository;

@Service
public class AgenteService {

	@Autowired
	private AgenteRepository agenteRepository;
	
	@Autowired
	private RegiaoRepository regiaoRepository;

	public void xmlReader2(String caminho) {

		try {

			File arquivoXml = new File(caminho);
			
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			Document document = documentBuilder.parse(arquivoXml);
			System.out.printf("Raiz " + document.getDocumentElement().getNodeName());

			NodeList nodeListAgente = document.getElementsByTagName("agente");

			printNodeListByTagName(nodeListAgente);

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Diretório " + System.getProperty("user.dir"));

		}
	}

	public void printNodeListByTagName(NodeList nodeList) {

		int tamanhoLista = nodeList.getLength();
		
		for (int itr = 0; itr < tamanhoLista; itr++) {

			Node node = nodeList.item(itr);
			System.out.println("\nNode Name :" + node.getNodeName());

			if (node.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) node;
//				NamedNodeMap nodeMap = node.getAttributes();
				Agente agente = new Agente();
				Regiao regiao = new Regiao();
				
				agente.setCodigo(Integer.parseInt(eElement.getElementsByTagName("codigo").item(0).getTextContent()));
				
				OffsetDateTime offsetDateTime = OffsetDateTime.parse(eElement.getElementsByTagName("data").item(0).getTextContent(), DateTimeFormatter.ISO_DATE_TIME);
				agente.setData(offsetDateTime);
				
				String geracao = eElement.getElementsByTagName("geracao").item(0).getTextContent();
				String compra = eElement.getElementsByTagName("compra").item(0).getTextContent();
				String precoMedioString = eElement.getElementsByTagName("precoMedio").item(0).getTextContent();
				
				String[] listaGeracao = geracao.split("\n");
				String[] listaCompra = compra.split("\n");
				String[] listaPrecoMedio = precoMedioString.split("\n");
				
				for (int i = 0; i < listaGeracao.length; i++) {
					
					if (!listaGeracao[i].isBlank() && !listaGeracao[i].isEmpty()) {
						regiao.getGeracao().add(Double.parseDouble(listaGeracao[i]));
					}
					
				}
				
				for (int i = 0; i < listaCompra.length; i++) {
					
					if (!listaCompra[i].isBlank() && !listaCompra[i].isEmpty()) {
						regiao.getCompra().add(Double.parseDouble(listaCompra[i]));
					}
					
				}

				for (int i = 0; i < listaPrecoMedio.length; i++) {
					
					if (!listaPrecoMedio[i].isBlank() && !listaPrecoMedio[i].isEmpty()) {
						regiao.getPrecoMedio().add(Double.parseDouble(listaPrecoMedio[i]));
					}
					
				}

				regiao.setAgente(agente);
				
				agente.getRegioes().add(regiao);
				
				agenteRepository.save(agente);
				regiaoRepository.save(regiao);
				
			}

		}

	}

//===========================================================================================================================================================	

	public String extrairExtensao(String nomeArquivo) {
		
		int i = nomeArquivo.lastIndexOf(".");
		return nomeArquivo.substring(i + 1);
		
	}
	
	public List<Agente> findAll() {
		
		List<Agente> listaAgente = agenteRepository.findAll();
		
		for (Agente agente : listaAgente) {
			System.out.println("Agente código: " + agente.getCodigo());
		}
		
		return listaAgente;
		
	}
	
}
