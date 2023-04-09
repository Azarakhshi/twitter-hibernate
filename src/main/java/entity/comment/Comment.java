package entity.comment;

import base.entity.BaseEntity;
import entity.like.Like;
import entity.tweet.Tweet;
import entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {

    private String message;
    @OneToOne(cascade = CascadeType.MERGE)
    private Comment comment;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Tweet tweet;
    @OneToMany(mappedBy = "comment", cascade = CascadeType.MERGE)
    private List<Like> likes = new ArrayList<>();
    @ManyToOne
    private User user;


}
