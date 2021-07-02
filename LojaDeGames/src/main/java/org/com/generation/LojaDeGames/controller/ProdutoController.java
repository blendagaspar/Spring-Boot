package org.com.generation.LojaDeGames.controller;

import java.util.List;
import org.com.generation.LojaDeGames.model.Produto;
import org.com.generation.LojaDeGames.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/produto")
@CrossOrigin("*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository ProdutoRepository ;
	
	
	@GetMapping
	public ResponseEntity<List<Produto>> findAllProduto () {
		
		return ResponseEntity.ok(ProdutoRepository.findAll());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> findByIdProduto (@PathVariable long id) {
		return ProdutoRepository.findById(id)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.notFound().build());
	}	
	
	
	@GetMapping("/nome/{nome}")	
	public ResponseEntity<List<Produto>> findAllByNomeContainingIgnoreCase(@PathVariable String nome) {
		return ResponseEntity.ok(ProdutoRepository.findAllByNomeContainingIgnoreCase(nome));
	
	}
	
	
	@PostMapping
	public ResponseEntity<Produto>postProduto(@RequestBody Produto produto){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoRepository.save(produto));
	}
	
	
    @PutMapping
	public ResponseEntity<Produto>putProduto(@RequestBody Produto produto){
			
			return ResponseEntity.status(HttpStatus.OK).body(ProdutoRepository.save(produto));
			
	  }
	
    @DeleteMapping("/{id}")
	public void deleteById(@PathVariable long id) {
		
    	ProdutoRepository.deleteById(id);
			
	}
	
	
			
}
