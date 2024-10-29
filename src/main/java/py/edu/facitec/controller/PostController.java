package py.edu.facitec.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.edu.facitec.model.Post;
import py.edu.facitec.model.Post;
import py.edu.facitec.repository.PostRepository;
import py.edu.facitec.repository.SuscritoRepository;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostRepository postRepository;
	
	//api
	@GetMapping("/posts")
	
	public ResponseEntity<List<Post>> getPosts(){
	List<Post> posts = new ArrayList<>();
	
	posts = postRepository.findAll();
	return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	
	
	}
	//post se utiliza para crear un elemento
		@PostMapping("/post")									
		public ResponseEntity<Post> 
		//JSON JAVA
		guardarPost(@RequestBody Post post){
			
			postRepository.save(post);
			return new ResponseEntity
					//devolver el objeto nuevo creado
					//id nuevo
					<Post>(post, HttpStatus.OK);
			
		}
		//Consulta api/suscrit{codigo
		//buscar un suscrito por codigo
		
		@GetMapping("/post/{codigo}")
		public ResponseEntity<Post>
						//Recibir por parametro el valor
		getOnePost(@PathVariable Long codigo){
			
			Optional<Post> post=
			postRepository.findById(codigo);
			
				//comparar si se encontro
			if (post.isPresent()) {
				return new ResponseEntity<Post>
				(post.get(),HttpStatus.OK);
				
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}

}
