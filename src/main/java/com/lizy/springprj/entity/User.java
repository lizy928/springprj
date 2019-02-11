package com.lizy.springprj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created By Lizhengyuan on 19-1-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    private String userName;
    private int sex;

}
