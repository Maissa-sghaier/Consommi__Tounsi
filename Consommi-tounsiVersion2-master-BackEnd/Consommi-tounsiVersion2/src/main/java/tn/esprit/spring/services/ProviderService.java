package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Provider;
import tn.esprit.spring.repository.ProviderRepository;
@Service
public class ProviderService implements IProviderService {
	@Autowired
	ProviderRepository providerRep;
	
	@Override
	public int addProvider(Provider provider) {
		providerRep.save(provider);
		return provider.getId_provider();
	}

	@Override
	public Provider updateProvider( int id,Provider provider) {
		
		Provider protoupdate = getProviderById(id);
		protoupdate.setId_provider(provider.getId_provider());
		protoupdate.setEmail(provider.getEmail());
		protoupdate.setId_provider(provider.getId_provider());
		protoupdate.setName_provider(provider.getName_provider());
		protoupdate.setPhone_number(provider.getPhone_number());
		protoupdate.setRib(provider.getRib());
		//Provider p=providerRep.save(provider);
		//final Provider modify_provider=providerRep.save(protoupdate);
		 providerRep.save(protoupdate);
		 return protoupdate ;
		
		// protoupdate.getId_provider();
	}

	@Override
	public void deleteProvider(int id) {
		providerRep.deleteById(id);
		
	}

	@Override
	public List<Provider> getAllProvider() {
		List<Provider> providers = new ArrayList<Provider>();
		providerRep.findAll().forEach(prov-> providers.add(prov));;
		return providers;
	}

	@Override
	public Provider getProviderById(int id) {
		 return providerRep.findById(id).get();		
	}

}
