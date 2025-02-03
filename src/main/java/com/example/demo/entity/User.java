package com.example.demo.entity;

import com.example.demo.activerecord.ActiveRecord;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Query;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "userx")
public class User extends ActiveRecord<User> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;
  private String email;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Post> posts;

  @SuppressWarnings("unchecked")
  public List<User> findUserByUsername(final String username) {
    Query userQuery =
        getEntityManager().createQuery("SELECT u FROM User u WHERE u.username=:username");
    userQuery.setParameter("username", username);
    return userQuery.getResultList();
  }

  public Optional<User> findUserByEmail(final String email) {
    CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
    Root<User> userRoot = criteriaQuery.from(User.class);
    try {
      User queryResult =
          getEntityManager()
              .createQuery(
                  criteriaQuery
                      .select(userRoot)
                      .where(criteriaBuilder.equal(userRoot.get("email"), email)))
              .getSingleResult();
      return Optional.of(queryResult); // Wrap the user in an Optional
    } catch (jakarta.persistence.NoResultException e) {
      return Optional.empty(); // Return an empty Optional if no user is found
    }
  }

  public Map<String, Object> getUserMap() {
    Map<String, Object> userMap = new HashMap<>();
    userMap.put("username", this.getUsername());
    userMap.put("email", this.getEmail());
    return userMap;
  }
}
