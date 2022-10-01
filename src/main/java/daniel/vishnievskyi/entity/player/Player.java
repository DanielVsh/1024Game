package daniel.vishnievskyi.entity.player;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Player {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  private String name;
  private String password;
  @Transient
  private boolean isRated;

  public Player() {
  }

  public Player(String name, String password) {
    this.name = name;
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isRated() {
    return isRated;
  }

  public void setRated(boolean rated) {
    isRated = rated;
  }
}
