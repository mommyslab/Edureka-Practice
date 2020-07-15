package com.doehrs.firstms.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user_data")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;

    @NotBlank(message = "User name is mandatory")
    @Column(name = "user_name")
    private String userName;

}
