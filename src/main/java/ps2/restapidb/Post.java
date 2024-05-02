package ps2.restapidb;

import javax.persistence.*;

@Entity
@Table(name="posts")
public class Post {
	@Id @GeneratedValue
	private long id;
	private String conteudo;
	private String anexos;
    private int likes;
    private int views;
    
	public Post() {
		super();
	}

	public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getAnexos() {
        return anexos;
    }
    public void setAnexos(String anexos) {
        this.anexos = anexos;
    }

    public int getLikes() {
        return likes;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getViews() {
        return views;
    }
    public void setViews(int views) {
        this.views = views;
    }
}