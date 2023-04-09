package entity.user;

import base.entity.BaseEntity;
import entity.comment.Comment;
import entity.follow.Follow;
import entity.like.Like;
import entity.tweet.Tweet;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String firstname;
    private String lastname;
    private String username;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Like> likes = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Tweet> tweets = new ArrayList<>();
    @OneToMany(mappedBy = "following")
    private List<Follow> following = new ArrayList<>();
    @OneToMany(mappedBy = "follower")
    private List<Follow> follower = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    public User(String firstname, String lastname, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public User(String username) {
        this.username = username;
    }
}
