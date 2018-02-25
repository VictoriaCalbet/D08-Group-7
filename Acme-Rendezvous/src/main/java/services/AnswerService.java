
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AnswerRepository;
import domain.Answer;

@Service
@Transactional
public class AnswerService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private AnswerRepository	answerRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public AnswerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Answer create() {
		Answer result = null;
		result = new Answer();

		return result;
	}

	public Collection<Answer> findAll() {
		Collection<Answer> result = null;
		result = this.answerRepository.findAll();
		return result;
	}

	public Answer findOne(final int answerId) {
		Answer result = null;
		result = this.answerRepository.findOne(answerId);
		return result;
	}

	public Answer save(final Answer answer) {
		Assert.notNull(answer, "message.error.answer.null");
		final Answer result = null;

		return result;
	}

	public void delete(final Answer answer) {
		Assert.notNull(answer, "message.error.answer.null");

	}

	// Other business methods -------------------------------------------------

	// Dashboard methods ------------------------------------------------------

	public Double findAvgNoAnswersToTheQuestionsPerRendezvous() {
		Double result = null;
		result = this.answerRepository.findAvgNoAnswersToTheQuestionPerRendezvous();
		return result;
	}

	public Double findStdNoAnswersToTheQuestionsPerRendezvous() {
		Double result = null;
		result = this.answerRepository.findStdNoAnswersToTheQuestionPerRendezvous();
		return result;
	}
}
