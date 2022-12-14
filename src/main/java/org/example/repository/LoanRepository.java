package org.example.repository;

import org.example.DataBaseConnection.SingleTonConnection;
import org.example.entity.Loans;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class LoanRepository extends RepositoryImpl<Loans, Long> {
    public static final Logger logger = LoggerFactory.getLogger(LoanRepository.class);

    public static boolean checkLoanExists(long id, String type) {
        Session session = SingleTonConnection.getInstance().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            final NativeQuery nativeQuery = session.createNativeQuery("select typeloan from loans where daneshjo_id = ? and typeloan = ?;");
            nativeQuery.setParameter(1, id);
            nativeQuery.setParameter(2, type);
            final Optional first = nativeQuery.getResultList().stream().findFirst();
            logger.info("check {} loged sucessfully",type);
            return first.isPresent();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException();
        }
    }

    public static List<Loans> daneshjoLoans(Long id) {
        Session session = SingleTonConnection.getInstance().openSession();
        Transaction transaction = null;
        try {
            Query q = session.createQuery("select l from Loans l where l.daneshjo.id = :id");
            q.setParameter("id", id);
            List<Loans> list = q.list();
            logger.info("check {} loged sucessfully",id);
            return list;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException();
        }finally {
            session.close();
        }
    }
    //section list Loans
    public static List<Loans> allLoans(){
        Session session = SingleTonConnection.getInstance().openSession();
        Transaction transaction = null;
        try {
            final Query<Loans> from_loans = session.createQuery("FROM Loans", Loans.class);
            final List<Loans> list = from_loans.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            session.close();
        }
    }



}

