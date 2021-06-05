package com.mariworld.mreview.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString(exclude = {"movie","member"})
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewnum;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private String text;
    private int grade;
}
