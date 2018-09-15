package WebIGo.admin.Dao;

import java.util.List;

import WebIGo.admin.Bean.Address;

public interface AddressMapper {
	List<Address> listAddresses();
	List<Address> listAddressesOfUser(Address address);
	void addAddress(Address address);
    int deleteAddress(Address address);
    int updateAddress(Address address);

}
