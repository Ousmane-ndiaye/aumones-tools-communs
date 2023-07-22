package com.aumones.tools.communs.data.repository;

import com.aumones.tools.communs.data.model.AbstractModel;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface AbstractRepository<ID, T extends AbstractModel<ID>> extends ListCrudRepository<T,ID>,
    ListPagingAndSortingRepository<T, ID> {

}
