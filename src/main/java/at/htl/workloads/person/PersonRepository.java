package at.htl.workloads.person;

import java.util.List;

public interface PersonRepository {
    Mechanic saveMechanic(Mechanic m);

    Mechanic updateMechanic(Mechanic m);

    Mechanic findMechanicById(String svNr);

    List<Mechanic> findAllMechanics();

    void deleteMechanic(Mechanic m);

    Owner saveOwner(Owner o);

    Owner updateOwner(Owner o);

    Owner findOwnerById(String svNr);

    List<Owner> findAllOwners();

    void deleteOwner(Owner o);

    Tenant saveTanant(Tenant o);

    Tenant updateTenant(Tenant o);

    Tenant findTenantById(String svNr);

    List<Tenant> findAllTenants();

    void deleteTenant(Tenant o);
}
