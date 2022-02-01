package at.htl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import javax.inject.Inject;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

public abstract class IntTestBase {

    @Inject
    protected UserTransaction transaction;

    @BeforeEach
    public void beginTransaction() throws SystemException, NotSupportedException {
        transaction.begin();
    }

    @AfterEach
    public void rollbackTransaction() throws SystemException {
        transaction.rollback();
    }

}