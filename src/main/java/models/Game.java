package models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="games")
public class Game {

    private int id;
    private String title;
    private GenreType genre;
    private List<Console> consoles;
//    private List<Owner> owners;

    public Game(){

    }

    public Game(String title, GenreType genre) {
        this.title = title;
        this.genre = genre;
        this.consoles = new ArrayList<Console>();
//        this.owners = new ArrayList<Owner>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Enumerated(value = EnumType.STRING)
    public GenreType getGenre() {
        return genre;
    }

    public void setGenre(GenreType genre) {
        this.genre = genre;
    }

    @ManyToMany
    @JoinTable(name="console_game", joinColumns = {@JoinColumn(name="game_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="console_id", nullable=false, updatable = false)})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    public List<Console> getConsoles() {
        return consoles;
    }

    public void setConsoles(List<Console> consoles) {
        this.consoles = consoles;
    }

    public void addConsole(Console console){
        this.consoles.add(console);
    }

//    @OneToMany(mappedBy="favouriteGame", fetch = FetchType.LAZY)
//    public List<Owner> getOwners() {
//        return owners;
//    }
}
