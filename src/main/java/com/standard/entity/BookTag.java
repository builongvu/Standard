package com.standard.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book_tag")
public class BookTag extends BaseEntity {

    private Integer bookId;
    private Integer tagId;

}
