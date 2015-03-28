/**
 * OrganizationFacade.java Created on 12-Aug-2014, 22:01:03
 *
 * petshelter-webapp petshelter-webapp
 *
 * Copyright (c) Pet Shelter - www.petshelter.info
 */
package eu.lpinto.petshelter.facades;

import eu.lpinto.petshelter.entities.Organization;
import eu.lpinto.petshelter.entities.User;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * EJB facade for organizations.
 *
 * @author Luís Pinto - mail@lpinto.eu
 */
@Stateless
public class OrganizationFacade extends AbstractFacade<Organization> {

    @PersistenceContext(unitName = "eu.lpinto.petshelter_petshelter-webapp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    /* Constructors */
    public OrganizationFacade() {
        super(Organization.class);
    }

    /* Operations */
    public List<Organization> findAll(int actionUserID) {
        User user = em.find(User.class, actionUserID);

        if (user == null) {
            return null;
        }

        return user.getOrganizations();
    }

    public Organization retrieve(int actionUserID, int orgID) {
        /* Check if User can do action */
        User user = em.find(User.class, actionUserID);
        if (user == null) {
            return null;
        }
        /* Find Organization */
        return user.getOrganization(orgID);
    }

    public void create(int actionUserID, Organization entity) {
        /* Check if User can do action */
        User user = em.find(User.class, actionUserID);
        if (user == null) {
            return;
        }

        /* Create Organization */
        entity.setCreated(new GregorianCalendar());
        entity.setUpdated(new GregorianCalendar());
        em.persist(entity);

        user.addOrg(entity);
        em.merge(user);
    }

    public void update(int actionUserID, Organization entity) {
        /* Check if User can do action */
        User user = em.find(User.class, actionUserID);
        if (user == null || entity == null || !user.hasOrganization(entity.getId())) {
            return;
        }

        entity.setUpdated(new GregorianCalendar());
        em.merge(entity);
    }

    public void associateUser(int actionUserID, int orgID, int targetUserID) {
        /* Check if User can do action */
        User user = em.find(User.class, actionUserID);
        if (user == null || !user.hasOrganization(orgID)) {
            return;
        }

        /* Check if Target User exists */
        User target = em.find(User.class, targetUserID);
        if (target == null) {
            return;
        }

        /* Check if Target User already exists on Organization */
        if (target.hasOrganization(orgID)) {
            return;
        }

        /* Associate Target User to Organization */
        target.addOrg(super.retrieve(orgID));
        em.merge(target);
    }

    public void dissociateUser(int actionUserID, int orgID, int targetUserID) {
        /* Check if User can do action */
        User user = em.find(User.class, actionUserID);
        if (user == null || !user.hasOrganization(orgID)) {
            return;
        }

        /* Check if Organization exists */
        Organization org = em.find(Organization.class, orgID);
        em.refresh(org);
        if (org == null) {
            return;
        }

        /* Check if Target User exists */
        User target = em.find(User.class, targetUserID);
        em.refresh(target);
        if (target == null) {
            return;
        }

        /* Check if Organization would be empty after action */
        if (org.getUsers() != null && org.getUsers().size() <= 1) {
            return;
        }

        /* Dissociate Target User from Organization */
        target.rmOrg(orgID);
        em.merge(target);
    }


    /* Getters/Setters */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
