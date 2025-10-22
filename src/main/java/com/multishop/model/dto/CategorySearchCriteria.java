package com.multishop.model.dto;

import lombok.Data;

@Data
public class CategorySearchCriteria {
    private String keySearch;      // tìm theo tên
    private Byte status;           // active/inactive
    private Long parentId;         // lấy category con của parent nào đó
    private Boolean parentOnly;    // chỉ lấy root category
    private Boolean childrenOnly;  // chỉ lấy category con
    private Integer level;         // cấp độ
    private String path;           // filter theo cây path (prefix)

    // paging & sort
    private Integer pageNo = 0;
    private Integer pageSize = 10;
    private String sortBy = "id";  // field sort
    private String sortDir = "asc"; // asc | desc
}

