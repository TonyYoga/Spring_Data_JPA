package com.telran.telranshopspringdata.data;

import com.telran.telranshopspringdata.data.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity,String> {
}
