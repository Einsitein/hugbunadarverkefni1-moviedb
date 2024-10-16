package is.hi.moviedb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import is.hi.moviedb.controller.UserController;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class MoviedbApplicationTests {

  @Autowired
  private UserController userController;

	@Test
	void contextLoads() throws Exception {
    assertThat(userController).isNotNull();
  }
}
