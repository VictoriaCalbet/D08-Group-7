# D08-Group-7

Functional requirement 5.2 says "An actor who is authenticated as a user must be able to create a rendezvous, which he’s implicitly assumed to attend.":

We have assumed that when a user creates a Rendezvous in final mode, a RSVP (an entity that represents a reservation) is created between the creator and the Rendezvous. We understand that the creator is one of the assitant, so it's logical to create a RSVP as we do when another user RSVPs the Rendezvous.

Functional requirement 16.4 says "An actor who is authenticated as a user must be able to link one of the rendezvouses that he or she’s created to other similar rendezvouses."

We have assumed that a user can link his/her own Rendezvouses only with those he/she has created. If every user could link any rendezvous to his/her own ones, the creator of the similar one would not be able to control who links his/her rendezvouses. With our version, a user can only use this functionality to promotes his/her own rendezvouses.

