package dev.sun.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.sun.entities.Topping;
import dev.sun.repositories.ToppingRepository;

@Component
@Service
public class ToppingServiceImpl implements ToppingService {

	@Autowired
	ToppingRepository tr;
	
	@Autowired
    EntityManagerFactory emf;
	
	@Override
	public Topping createTopping(Topping topping) {
		return tr.save(topping);
	}

	@Override
	public List<Topping> getAllToppings() {
		return (List<Topping>) tr.findAll();
	}

	@Override
	public Topping getToppingById(int id) {
		return tr.findById(id).get();
	}

	@Override
	public Topping updateTopping(Topping topping) {
		Topping toppingFromdb  = tr.findById(topping.getToppingId()).get();
		toppingFromdb.setToppingCost(topping.getToppingCost());
		toppingFromdb.setToppingName(topping.getToppingName());
		return tr.save(toppingFromdb);
	}

	@Override
	public boolean deleteTopping(Topping topping) {
		tr.delete(topping);
		return true;
	}

	/*
	 * @Override public List<Topping> getMostPopularToppings() { EntityManager
	 * em=emf.createEntityManager(); Query
	 * query=em.createQuery("SELECT t.topping_id,t.topping_name FROM topping " +
	 * "JOIN pizza_topping pt ON pt.pt_topping = t.topping_id " +
	 * "GROUP BY pt.pt_topping" + "    ORDER BY COUNT(*) DESC");
	 * 
	 * //@SuppressWarnings("unchecked") List<Topping>
	 * list=(List<Topping>)query.getResultList();
	 * System.out.println("Most popular toppings"); em.close(); return list; }
	 */

}
