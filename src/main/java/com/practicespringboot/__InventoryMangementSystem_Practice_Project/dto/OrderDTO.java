package com.practicespringboot.__InventoryMangementSystem_Practice_Project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Integer id;
    private UserDTO userDTO;
    private List<OrderItemDTO> itemDTOS;

}
