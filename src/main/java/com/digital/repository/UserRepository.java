package com.digital.repository;

import com.digital.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author babacoul
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByApiKey(String query);

    //User findByPseudo(String query);

  User findFirstByUsername(String username);
  User findByPassword(String passord);
  User findByEmail(String email);

  List<User> findAllByStateAndUsernameIsNot(String state, String username);
  List<User> findAllByStateAndUsernameIsNotOrderByMessageNonLuAsc(String state, String username);
  List<User> findAllByStateAndLastnameLikeOrFirstnameLikeAndUsernameIsNotOrderByMessageNonLuAsc(String state, String nom, String prenom, String username);
  List<User> findAllByStateAndLastnameLikeOrFirstnameLike(String state, String nom, String prenom);
  List<User> findAllByStateAndLastnameContainingOrFirstnameContaining(String state, String nom, String prenom);
  List<User> findAllByStateAndLastnameContainingIgnoreCaseOrFirstnameContainingIgnoreCase(String state, String nom, String prenom);
  List<User> findAllByUsernameIsNotOrderByState(String username);
  List<User> findAllByState(String etat);
  //List<User> findAllByStateAndMessagesFalse(String etat);
 // List<User> findByStateOrMessages
/*
* SELECT u.id, u.civilite, u.nom, u.prenoms, u.username, u.state, m.user_id, m.content, m.state
FROM user_account u ,message m
WHERE u.state ='connected'
AND m.state IS FALSE
AND u.id = m.user_id;
*/
  User findByChangePasswordToken(String query);

  User findByPasswordAndUsername(String password, String username);

  User findByUsernameAndPassword(String username, String password);

}
