package com.standard.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "unit")
public class Unit extends BaseEntity {

    private String name;
    private String type;
//    @JsonManagedReference
    @OneToMany(mappedBy = "unit", fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<>();

}
