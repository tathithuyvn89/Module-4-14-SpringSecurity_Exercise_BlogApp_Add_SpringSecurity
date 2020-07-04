package myapp.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String name;
    private String content;
    private Timestamp date = new Timestamp(System.currentTimeMillis());
    private int likes;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Blog() {
    }

//    public Blog(String author, String name, String content, Timestamp date, int likes, Category category) {
//        this.author = author;
//        this.name = name;
//        this.content = content;
//        this.date = date;
//        this.likes = likes;
//        this.category = category;
//    }
    public Blog(Long id, String author, String name, String content, Timestamp date, int likes, Category category) {
        this.id =  id;
        this.author = author;
        this.name = name;
        this.content = content;
        this.date = date;
        this.likes = likes;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int like) {
        this.likes = like;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
