package com.example.Library.repos.BookRegistration;

import com.example.Library.models.BookRegistration;

import javax.persistence.EntityManager;

public class BookRegistrationRepoImpl {

    final private EntityManager em;


    public BookRegistrationRepoImpl(EntityManager em) {
        this.em = em;
    }

    public BookRegistration getLastBookRegistration(Long bookId) {
        final String hql = "select br from BookRegistration br where br.bookId = :bookId order by br.date desc limit 1";
        return (BookRegistration) em.createQuery(hql)
                .setParameter("bookId", bookId)
                .setMaxResults(1)
                .getSingleResult();
    }
}
