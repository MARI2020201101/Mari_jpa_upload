package com.mariworld.mreview.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString(exclude = "movie")
@NoArgsConstructor
@AllArgsConstructor
public class MovieImage extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;

    private String uuid;
    private String imgName;
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;
}
