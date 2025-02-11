package com.practicespringboot.__InventoryMangementSystem_Practice_Project.utils;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserRoleId implements Serializable {
    private Integer userId;
    private Integer roleId;
}
