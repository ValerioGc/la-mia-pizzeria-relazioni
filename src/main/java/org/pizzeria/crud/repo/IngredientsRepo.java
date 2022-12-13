package org.pizzeria.crud.repo;

import org.pizzeria.crud.pojo.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientsRepo extends JpaRepository<Ingredients, Integer> {

}
