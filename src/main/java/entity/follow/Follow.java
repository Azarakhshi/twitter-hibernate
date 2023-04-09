package entity.follow;

import base.entity.BaseEntity;
import entity.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Follow extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "following_id")
    private User following;
    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;
    private Boolean isCloseFriend;
}