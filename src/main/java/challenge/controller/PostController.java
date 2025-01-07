package challenge.controller;

import challenge.model.Post;
import challenge.model.User;
import challenge.security.AuthenticationFacade;
import challenge.security.IAuthenticationFacade;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import challenge.repository.PostRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	IAuthenticationFacade userLogado;
	@Autowired
	private PostRepository repository;

	@Autowired
	private AuthenticationFacade authentication;

	@GetMapping
	public List<Post> listPosts() {

		return Lists.newArrayList(repository.findByUser(userLogado.getUser()));
	}

	@PostMapping
	public Post newPost(@Valid @RequestBody Post post) {

		post.setUser(authentication.getUser());
		post.setDate(new Date());
		return repository.save(post);
	}

	@GetMapping("/{postId}")
	public ResponseEntity<Post> listPostDatails(@PathVariable Long postId){

		Post post = repository.findByUserAndId(userLogado.getUser(), idPost);

		return ResponseEntity.ok(post);

	}

	@PutMapping("/{postId}")
	public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody Post updatepost){


		Post postExiste = repository.findByUserAndId(this.userLogado.getUser(), postId);

		if(postExiste == null){
			return ResponseEntity.status(403).build();
		}

		if(updatepost.getTitle() != null){
			postExiste.setTitle(updatepost.getTitle());
		}

		if(updatepost.getText() != null){
			postExiste.setText(updatepost.getText());
		}

		Post postSave = repository.save(postExiste);

		return ResponseEntity.ok(postSave);

	}


}
