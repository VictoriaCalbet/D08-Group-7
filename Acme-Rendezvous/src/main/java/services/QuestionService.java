
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.QuestionRepository;
import domain.Question;

@Service
@Transactional
public class QuestionService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private QuestionRepository	questionRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public QuestionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Question create() {
		Question result = null;
		result = new Question();

		return result;
	}

	public Collection<Question> findAll() {
		Collection<Question> result = null;
		result = this.questionRepository.findAll();
		return result;
	}

	public Question findOne(final int questionId) {
		Question result = null;
		result = this.questionRepository.findOne(questionId);
		return result;
	}

	public Question save(final Question question) {
		Assert.notNull(question, "message.error.question.null");
		final Question result = null;

		return result;
	}

	public void delete(final Question question) {
		Assert.notNull(question, "message.error.question.null");

	}

	// Other business methods -------------------------------------------------

	// Dashboard methods ------------------------------------------------------

	public Double avgAnnouncementPerRendezvous() {
		Double result = null;
		result = this.questionRepository.avgAnnouncementPerRendezvous();
		return result;
	}

	public Double stdAnnouncementPerRendezvous() {
		Double result = null;
		result = this.questionRepository.stdAnnouncementPerRendezvous();
		return result;
	}
}
