package first.second.third.fuckmylife.dao.impl;

import first.second.third.fuckmylife.Entity.PreviewImage;
import first.second.third.fuckmylife.dao.PreviewImageDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PreviewImageDaoImpl implements PreviewImageDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void save(PreviewImage previewImage) {
        try {
            getCurrentSession().merge(previewImage);
        } catch(Exception e){
            System.out.println("PREVIEW IMAGE: " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public PreviewImage findById(Long id) {
        return getCurrentSession().get(PreviewImage.class, id);
    }

    @Override
    @Transactional
    public List<PreviewImage> findAll() {
        Query<PreviewImage> query = getCurrentSession().createQuery("from PreviewImage", PreviewImage.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void delete(PreviewImage previewImage) {
        getCurrentSession().remove(previewImage);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}