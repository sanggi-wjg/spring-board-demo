package com.example.demo.member;

import com.example.demo.article.ArticleEntity;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "members")
@Entity
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 100)
    private String fullname;

    @Column(unique = true, length = 100)
    private String email;

    @Column(length = 250)
    private String password;

    // relation
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<ArticleEntity> articles = new ArrayList<>();


    public void encryptPassword() {
        setPassword("-salt-" + password + "-salt-");
    }

}
