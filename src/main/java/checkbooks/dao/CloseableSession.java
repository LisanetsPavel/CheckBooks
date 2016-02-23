package checkbooks.dao;

import org.hibernate.Session;

/**
 * Created by pc8 on 06.08.15.
 */
public class CloseableSession implements AutoCloseable {

    private final Session session;

    public CloseableSession(Session session) {
        this.session = session;
    }

    public Session delegate() {
        return session;
    }

    @Override
    public void close() {
        session.close();
    }

}
