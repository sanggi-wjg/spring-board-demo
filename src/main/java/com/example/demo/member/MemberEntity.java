package com.example.demo.member;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    public void encryptPassword() {
        setPassword("-salt-" + password + "-salt-");
    }

}
