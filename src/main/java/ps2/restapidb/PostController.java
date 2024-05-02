package ps2.restapidb;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PostController {

	@Autowired
	private PostRepo postRepo;

	@GetMapping("/api/posts")
	Iterable<Post> getPosts() {
		return postRepo.findAll();
	}
	
	@GetMapping("/api/posts/{id}")
	Optional<Post> getPost(@PathVariable long id) {
		return postRepo.findById(id);
	}
	
	@PostMapping("/api/posts")
	Post createPost(@RequestBody Post f) {
		Post createPost = postRepo.save(f);
		return createPost;
	}
	
	@PutMapping("/api/posts/{postId}")
	Optional<Post> updatePost(@RequestBody Post postReq, @PathVariable long postId) {
		Optional<Post> opt = postRepo.findById(postId);
		if (opt.isPresent()) {
			if (postReq.getId() == postId) {
				postRepo.save(postReq);
				return opt;
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao alterar dados do post com id " + postId);
	}	
	
	@DeleteMapping("/api/posts/{id}")
	void deletePost(@PathVariable long id) {
		postRepo.deleteById(id);
	}	
	
}