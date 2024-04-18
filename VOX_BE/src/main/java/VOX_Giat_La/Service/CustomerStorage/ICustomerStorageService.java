package VOX_Giat_La.Service.CustomerStorage;

import VOX_Giat_La.DTO.CustomerStorageDTO;
import VOX_Giat_La.Models.CustomerStorage;

import java.util.List;

public interface ICustomerStorageService {
    CustomerStorage createCustomerStorage(CustomerStorageDTO customerStorageDTO) throws Exception;
    CustomerStorage getCustomerStoragebyID(int id) throws Exception;
    CustomerStorage updateCustomerStorage(int id, CustomerStorageDTO customerStorageDTO) throws Exception;
    void deleteCustomerStorage(int id);
    List<CustomerStorage> getListCustomerStorage();
}
