package entity.like;

import base.entity.BaseEntity;
import entity.comment.Comment;
import entity.tweet.Tweet;
import entity.user.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "likes")
public class Like extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Tweet tweet;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Comment comment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Like(Tweet tweet, User user) {
        this.tweet = tweet;
        this.user = user;
    }
}
