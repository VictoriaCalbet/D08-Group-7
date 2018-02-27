
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AnswerServiceTest extends AbstractTest {

	@Autowired
	private QuestionService	questionService;

	@Autowired
	private UserService		userService;

	@Autowired
	private AnswerService	answerService;


	@Test
	public void testSavedFromCreate() {

	}
	@Test
	public void testSavedFromEdit() {

	}
	@Test
	public void testDelete() {

	}
}