package VOX_Giat_La.Service.CustomerStorage;

import VOX_Giat_La.DTO.CustomerStorageDTO;
import VOX_Giat_La.Exeception.DataNotFoundException;
import VOX_Giat_La.Models.CustomerStorage;
import VOX_Giat_La.Respones.Storage.CustomerStorageRespone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ICustomerStorageService {
    CustomerStorage createCustomerStorage(CustomerStorageDTO customerStorageDTO) throws Exception;
    CustomerStorage getCustomerStoragebyID(int id) throws Exception;
    CustomerStorage updateCustomerStorage(int id, CustomerStorageDTO customerStorageDTO) throws Exception;
    void deleteCustomerStorage(int id);
    Page<CustomerStorageRespone> getListCustomerStorage(PageRequest pageRequest);

    CustomerStorage getCustomerStoragebyBillDetail(int billDetailID) throws DataNotFoundException;
}
