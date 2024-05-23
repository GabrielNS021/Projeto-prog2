package ps2.restapidb;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PostController {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @GetMapping("/api/posts")
    Iterable<Post> getPosts() {
        return postRepo.findAll();
    }

    @GetMapping("/api/posts/{id}")
    Optional<Post> getPost(@PathVariable long id) {
        return postRepo.findById(id);
    }

    @PostMapping("/api/posts")
    Post createPost(@RequestBody Post post) {
        Usuario usuario = usuarioRepo.findById(post.getUsuario().getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        post.setUsuario(usuario);
        return postRepo.save(post);
    }

    @PutMapping("/api/posts/{postId}")
    Optional<Post> updatePost(@RequestBody Post postReq, @PathVariable long postId) {
        return postRepo.findById(postId).map(existingPost -> {
            Usuario usuario = usuarioRepo.findById(postReq.getUsuario().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
            existingPost.setUsuario(usuario);
            existingPost.setConteudo(postReq.getConteudo());
            existingPost.setAnexos(postReq.getAnexos());
            existingPost.setLikes(postReq.getLikes());
            existingPost.setViews(postReq.getViews());
            postRepo.save(existingPost);
            return Optional.of(existingPost);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post não encontrado"));
    }

    @DeleteMapping("/api/posts/{id}")
    void deletePost(@PathVariable long id) {
        postRepo.deleteById(id);
    }
}