package WebIGo.admin.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import WebIGo.admin.Bean.Address;
import WebIGo.admin.utils.MybatisUtil;

public class AddressDao {
	private SqlSessionFactory sessionFactory = MybatisUtil.getInstance();

	public List<Address> listAddresses() {
		// TODO Auto-generated method stub
		SqlSession session = sessionFactory.openSession();
		AddressMapper addressMapper = session.getMapper(AddressMapper.class);
		List<Address> addressesList = addressMapper.listAddresses();
		return addressesList;
	}
	
	public List<Address> listAddressesOfUser(Address address) {
		// TODO Auto-generated method stub
		SqlSession session = sessionFactory.openSession();
		AddressMapper addressMapper = session.getMapper(AddressMapper.class);
		List<Address> addressesList = addressMapper.listAddressesOfUser(address);
		return addressesList;
	}

	public int addAddress(Address address) {
		// TODO Auto-generated method stub
		SqlSession session = sessionFactory.openSession();
		AddressMapper addressMapper = session.getMapper(AddressMapper.class);
		addressMapper.addAddress(address);
		session.commit();
		return 0;
	}
	
	public int deleteAddress(Address address)
    {
        SqlSession session = sessionFactory.openSession();
        AddressMapper addressMapper = session.getMapper(AddressMapper.class);
        addressMapper.deleteAddress(address);
        session.commit();
        return 0;
    }
	
	public int updateAddress(Address address)
	{
		SqlSession session = sessionFactory.openSession();
        AddressMapper addressMapper = session.getMapper(AddressMapper.class);
        addressMapper.updateAddress(address);
        session.commit();
        return 0;
	}

}
