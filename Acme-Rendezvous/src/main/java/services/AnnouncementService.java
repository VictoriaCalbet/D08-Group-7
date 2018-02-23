
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AnnouncementRepository;
import domain.Announcement;

@Service
@Transactional
public class AnnouncementService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private AnnouncementRepository	announcementRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public AnnouncementService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Announcement create() {
		Announcement result = null;
		result = new Announcement();

		return result;
	}

	public Collection<Announcement> findAll() {
		Collection<Announcement> result = null;
		result = this.announcementRepository.findAll();
		return result;
	}

	public Announcement findOne(final int announcementId) {
		Announcement result = null;
		result = this.announcementRepository.findOne(announcementId);
		return result;
	}

	public Announcement save(final Announcement announcement) {
		Assert.notNull(announcement, "message.error.announcement.null");
		final Announcement result = null;

		return result;
	}

	public void delete(final Announcement announcement) {
		Assert.notNull(announcement, "message.error.announcement.null");
	}

	// Other business methods -------------------------------------------------
}
