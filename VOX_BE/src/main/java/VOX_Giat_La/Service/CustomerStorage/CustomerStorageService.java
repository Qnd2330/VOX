package VOX_Giat_La.Service.CustomerStorage;

import VOX_Giat_La.DTO.CustomerStorageDTO;
import VOX_Giat_La.Exeception.DataNotFoundException;
import VOX_Giat_La.Models.BillDetails;
import VOX_Giat_La.Models.CustomerStorage;
import VOX_Giat_La.Models.Storage;
import VOX_Giat_La.Repositories.BillDetailsRepos;
import VOX_Giat_La.Repositories.CustomerStorageRepos;
import VOX_Giat_La.Repositories.StorageRepos;
import VOX_Giat_La.Respones.Storage.CustomerStorageRespone;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerStorageService implements ICustomerStorageService{
    private final StorageRepos storageRepos;
    private final BillDetailsRepos billDetailsRepos;
    private final CustomerStorageRepos customerStorageRepos;

    @Override
    public CustomerStorage createCustomerStorage(CustomerStorageDTO customerStorageDTO) throws Exception {
        Storage storage =  storageRepos.findById(customerStorageDTO.getStorageID()).orElseThrow(()->new DataNotFoundException("Không tìm thấy vị trí kho "+ customerStorageDTO.getStorageID()));
        BillDetails billDetails =  billDetailsRepos.findById(customerStorageDTO.getBillDetailID()).orElseThrow(()->new DataNotFoundException("Không tìm thấy ID bill detail "+ customerStorageDTO.getBillDetailID()));
        CustomerStorage newCustomerStorage = CustomerStorage.builder()
                .storage(storage)
                .billDetail(billDetails)
                .itemDescription(customerStorageDTO.getItemDescription())
                .itemPicture(String.valueOf(customerStorageDTO.getItemPicture()))
                .cusStoredStatus(customerStorageDTO.getCusStoredStatus())
                .build();
        return customerStorageRepos.save(newCustomerStorage);
    }

    @Override
    public CustomerStorage getCustomerStoragebyID(int cusItemID) throws DataNotFoundException{
        return customerStorageRepos.findById(cusItemID).orElseThrow(()-> new DataNotFoundException("Không tìm thấy customer storage"));
    }

    @Override
    public CustomerStorage updateCustomerStorage(int id, CustomerStorageDTO customerStorageDTO) throws Exception {
        CustomerStorage customerStorageUpdate = getCustomerStoragebyID(id);
        Storage storage =  storageRepos.findById(customerStorageDTO.getStorageID()).orElseThrow(()->new DataNotFoundException("Không tìm thấy vị trí kho "+ customerStorageDTO.getStorageID()));
        BillDetails billDetails =  billDetailsRepos.findById(customerStorageDTO.getBillDetailID()).orElseThrow(()->new DataNotFoundException("Không tìm thấy ID bill detail "+ customerStorageDTO.getBillDetailID()));
        customerStorageUpdate.setStorage(storage);
        customerStorageUpdate.setBillDetail(billDetails);
        customerStorageUpdate.setItemDescription(customerStorageDTO.getItemDescription());
        customerStorageUpdate.setItemPicture(String.valueOf(customerStorageDTO.getItemPicture()));
        customerStorageUpdate.setCusStoredStatus(customerStorageDTO.getCusStoredStatus());
        return customerStorageRepos.saveAndFlush(customerStorageUpdate);
    }

    @Override
    public void deleteCustomerStorage(int id) {

    }

    @Override
    public Page<CustomerStorageRespone> getListCustomerStorage(PageRequest pageRequest) {
        return customerStorageRepos.findAll(pageRequest).map(customerStorage -> CustomerStorageRespone.fromCustomerStorage(customerStorage));
    }

    @Override
    public CustomerStorage getCustomerStoragebyBillDetail(int billDetailID) throws DataNotFoundException {
        BillDetails billDetails =  billDetailsRepos.findById(billDetailID).orElseThrow(()->new DataNotFoundException("Không tìm thấy ID bill detail "));
        return customerStorageRepos.findByBillDetail(billDetails);
    }


}
